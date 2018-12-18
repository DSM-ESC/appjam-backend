package me.mocha.appjam.model.entiity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "data")
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
    private Long unixTime;

    @Builder
    public Data(int dust, int humidity, int temperature, long unixTime) {
        this.dust = dust;
        this.humidity = humidity;
        this.temperature = temperature;
        this.unixTime = unixTime;
    }

}
