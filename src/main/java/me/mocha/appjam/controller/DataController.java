package me.mocha.appjam.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
import me.mocha.appjam.annotation.CurrentUser;
import me.mocha.appjam.model.entiity.Data;
import me.mocha.appjam.model.entiity.User;
import me.mocha.appjam.model.repository.DataRepository;
import me.mocha.appjam.payload.request.data.SaveDataRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/data")
public class DataController {

    private final DataRepository dataRepository;
    private static final String exampleToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

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
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "Bearer token", required = true, dataType = "string", paramType = "header", example = "Bearer " + exampleToken)})
    public void saveData(@CurrentUser User user, @Valid @RequestBody SaveDataRequest request) {
        Data data = dataRepository.save(Data.builder()
                .dust(request.getDust())
                .humidity(request.getHumidity())
                .temperature(request.getTemperature())
                .year(request.getYear())
                .month(request.getMonth())
                .day(request.getDay())
                .hour(request.getHour())
                .minute(request.getMinute())
                .build());
        log.info("save data - {},", data.getId());
    }

    @GetMapping("/{year}")
    public Map<Integer, List<Data>> getYearData(@PathVariable("year") int year) {
        Map<Integer, List<Data>> result = new HashMap<>();
        dataRepository.findAllByYear(year).forEach(d -> {
            List<Data> tmp = result.get(d.getMonth());
            if (tmp == null) {
                tmp = new ArrayList<>();
            }
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
            if (tmp == null) {
                tmp = new ArrayList<>();
            }
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
            if (tmp == null) {
                tmp = new ArrayList<>();
            }
            tmp.add(d);
            result.put(d.getHour(), tmp);
        });
        return result;
    }

}
