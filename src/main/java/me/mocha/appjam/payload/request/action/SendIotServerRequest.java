package me.mocha.appjam.payload.request.action;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendIotServerRequest {

    private Integer type;

    private Boolean power;

}
