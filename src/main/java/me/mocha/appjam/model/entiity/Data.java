package me.mocha.appjam.model.entiity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "data")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Data {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Getter
    @Setter
    @Min(0)
    private Double dust;

    @Getter
    @Setter
    @Min(0)
    @Max(100)
    private Double humidity;

    @Getter
    @Setter
    @Min(-100)
    @Max(100)
    private Double temperature;

    @Getter
    @Setter
    private Double x;

    @Getter
    @Setter
    private Double y;

    @Getter
    @Setter
    private Double z;

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

}
