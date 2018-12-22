package me.mocha.appjam.payload.request.data;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveDataRequest {

    @NotNull
    @Min(0)
    private Double dust;

    @NotNull
    @Min(0)
    @Max(100)
    private Double humidity;

    @NotNull
    @Min(-100)
    @Max(100)
    private Double temperature;

    @NotNull
    @Getter
    @Min(2018)
    @Max(2300)
    private Integer year;

    @NotNull
    @Getter
    @Min(1)
    @Max(12)
    private Integer month;

    @NotNull
    @Getter
    @Min(1)
    @Max(31)
    private Integer day;

    @NotNull
    @Getter
    @Min(0)
    @Max(23)
    private Integer hour;

    @NotNull
    @Getter
    @Min(0)
    @Max(59)
    private Integer minute;

}
