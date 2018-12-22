package me.mocha.appjam.scheduler;

import lombok.extern.slf4j.Slf4j;
import me.mocha.appjam.model.entiity.Data;
import me.mocha.appjam.model.repository.DataRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@EnableScheduling
@Component
public class JobScheduler {

    public static boolean force = false;
    private final DataRepository dataRepository;

    public JobScheduler(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Scheduled(cron = "0 * * * * *")
    public void sendJob() {
        LocalDateTime dateTime = LocalDateTime.now();
        List<Data> data = dataRepository.findAllByYearAndMonthAndDayAndHourAndMinute(
                dateTime.getYear(),
                dateTime.getMonthValue(),
                dateTime.getDayOfMonth(),
                dateTime.getHour(),
                dateTime.getMinute()
        );
        if (!force) {
            Map<String, Double> ave = getAve(data);
            //TODO: send action to iot server
        }
    }

    private Map<String, Double> getAve(List<Data> data) {
        Map<String, Double> result = new HashMap<>();
        double dSum = 0.0, hSum = 0.0, tSum = 0.0;
        for (Data d : data) {
            dSum += d.getDay();
            hSum += d.getHumidity();
            tSum += d.getTemperature();
        }
        result.put("dust", dSum / data.size());
        result.put("humidity", hSum / data.size());
        result.put("temperature", tSum / data.size());
        return result;
    }

}
