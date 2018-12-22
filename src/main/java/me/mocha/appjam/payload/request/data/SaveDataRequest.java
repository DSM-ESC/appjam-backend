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

    @Getter
    @Setter
    private Double x;

    @Getter
    @Setter
    private Double y;

    @Getter
    @Setter
    private Double z;
    
}
