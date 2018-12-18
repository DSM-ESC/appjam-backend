package me.mocha.appjam.controller;

import me.mocha.appjam.model.entiity.Data;
import me.mocha.appjam.model.repository.DataRepository;
import me.mocha.appjam.payload.request.data.SaveDataRequest;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

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

    @PostMapping
    public void saveData(@RequestBody SaveDataRequest request) {
        dataRepository.save(Data.builder()
                .dust(request.getDust())
                .humidity(request.getHumidity())
                .temperature(request.getTemperature())
                .unixTime(request.getUnixTime())
                .build());
    }

    @GetMapping("/{year}")
    public List<Data> getYearData(@PathVariable("year") int year) {
        return dataRepository.findAll().stream().filter(data -> {
            LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(data.getUnixTime()), ZoneId.systemDefault());
            return dateTime.getYear() == year;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{year}/{month}")
    public List<Data> getMonthData(@PathVariable("year") int year, @PathVariable("month") int month) {
        return dataRepository.findAll().stream().filter(data -> {
            LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(data.getUnixTime()), ZoneId.systemDefault());
            return dateTime.getYear() == year && dateTime.getMonthValue() == month;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{year}/{month}/{day}")
    public List<Data> getDayData(@PathVariable("year") int year, @PathVariable("month") int month, @PathVariable("day") int day) {
        return dataRepository.findAll().stream().filter(data -> {
            LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(data.getUnixTime()), ZoneId.systemDefault());
            return dateTime.getYear() == year && dateTime.getMonthValue() == month && dateTime.getDayOfMonth() == day;
        }).collect(Collectors.toList());
    }

}
