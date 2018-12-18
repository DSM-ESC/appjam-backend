package me.mocha.appjam.payload.request.data;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
public class SaveDataRequest {

    @NotNull
    @Min(0)
    private Integer dust;

    @NotNull
    @Min(0)
    @Max(100)
    private Integer humidity;

    @NotNull
    @Min(-100)
    @Max(100)
    private Integer temperature;

    @NotNull
    @Min(0)
    private Long unixTime;

}
