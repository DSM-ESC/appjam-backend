package me.mocha.appjam.payload.request.action;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplyActionRequest {

    @NotNull
    private Integer type;

    @NotNull
    private Boolean power;

}
