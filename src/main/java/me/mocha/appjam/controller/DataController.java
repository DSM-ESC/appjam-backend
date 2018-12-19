package me.mocha.appjam.controller;

import me.mocha.appjam.model.entiity.Data;
import me.mocha.appjam.model.repository.DataRepository;
import me.mocha.appjam.payload.request.data.SaveDataRequest;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/data")
public class DataController {

    private final DataRepository dataRepository;

    public DataController(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @GetMapping
    public List<Data> getAllData() {
        return dataRepository.findAll();
    }

    @GetMapping("/recent")
    public Data getRecentData() {
        Data recent = null;
        for (Data data : dataRepository.findAll()) {
            if (recent == null) {
                recent = data;
            } else {
                LocalDateTime rDate = LocalDateTime.of(recent.getYear(), recent.getMonth(), recent.getDay(), recent.getHour(), recent.getMinute());
                LocalDateTime dDate = LocalDateTime.of(data.getYear(), data.getMonth(), data.getDay(), data.getHour(), data.getMinute());

                if (rDate.isBefore(dDate)) {
                    recent = data;
                }
            }
        }
        return recent;
    }

    @PostMapping
    public void saveData(@RequestBody SaveDataRequest request) {
        dataRepository.save(Data.builder()
                .dust(request.getDust())
                .humidity(request.getHumidity())
                .temperature(request.getTemperature())
                .year(request.getYear())
                .month(request.getMonth())
                .day(request.getDay())
                .hour(request.getHour())
                .minute(request.getMinute())
                .build());
    }

    @GetMapping("/{year}")
    public Map<Integer, List<Data>> getYearData(@PathVariable("year") int year) {
        Map<Integer, List<Data>> result = new HashMap<>();
        dataRepository.findAllByYear(year).forEach(d -> {
            List<Data> tmp = result.get(d.getMonth());
            tmp.add(d);
            result.put(d.getMonth(), tmp);
        });
        return result;
    }

    @GetMapping("/{year}/{month}")
    public Map<Integer, List<Data>> getMonthData(@PathVariable("year") int year, @PathVariable("month") int month) {
        Map<Integer, List<Data>> result = new HashMap<>();
        dataRepository.findAllByYearAndMonth(year, month).forEach(d -> {
            List<Data> tmp = result.get(d.getDay());
            tmp.add(d);
            result.put(d.getDay(), tmp);
        });
        return result;
    }

    @GetMapping("/{year}/{month}/{day}")
    public Map<Integer, List<Data>> getDayData(@PathVariable("year") int year, @PathVariable("month") int month, @PathVariable("day") int day) {
        Map<Integer, List<Data>> result = new HashMap<>();
        dataRepository.findAllByYearAndMonthAndDay(year, month, day).forEach(d -> {
            List<Data> tmp = result.get(d.getHour());
            tmp.add(d);
            result.put(d.getHour(), tmp);
        });
        return result;
    }

}
