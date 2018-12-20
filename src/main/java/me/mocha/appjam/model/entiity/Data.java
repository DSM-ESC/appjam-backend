package me.mocha.appjam.model.entiity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "data")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Data {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Getter
    @Setter
    @Min(0)
    private Integer dust;

    @Getter
    @Setter
    @Min(0)
    @Max(100)
    private Integer humidity;

    @Getter
    @Setter
    @Min(-100)
    @Max(100)
    private Integer temperature;

    @Getter
    @Min(2018)
    @Max(2300)
    private Integer year;

    @Getter
    @Min(1)
    @Max(12)
    private Integer month;

    @Getter
    @Min(1)
    @Max(31)
    private Integer day;

    @Getter
    @Min(0)
    @Max(23)
    private Integer hour;

    @Getter
    @Min(0)
    @Max(59)
    private Integer minute;

    @Builder
    public Data(int dust, int humidity, int temperature, int year, int month, int day, int hour, int minute) {
        this.dust = dust;
        this.humidity = humidity;
        this.temperature = temperature;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

}
