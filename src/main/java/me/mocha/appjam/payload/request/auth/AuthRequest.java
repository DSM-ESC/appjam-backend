package me.mocha.appjam.payload.request.auth;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthRequest {

    @NotNull
    @Min(4)
    private String username;

    @NotNull
    @Min(8)
    private String password;

}
