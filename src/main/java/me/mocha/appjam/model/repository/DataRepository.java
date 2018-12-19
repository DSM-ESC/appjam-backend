package me.mocha.appjam.model.repository;

import me.mocha.appjam.model.entiity.Data;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataRepository extends JpaRepository<Data, Long> {
    List<Data> findAllByYear(int year);
    List<Data> findAllByYearAndMonth(int year, int month);
    List<Data> findAllByYearAndMonthAndDay(int year, int month, int day);
    List<Data> findAllByYearAndMonthAndDayAndHour(int year, int month, int day, int hour);
}
