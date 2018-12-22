package me.mocha.appjam.payload.request.auth;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthRequest {

    @NotNull
    private String username;

    @NotNull
    private String password;

}
