package me.mocha.appjam.payload.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthResponse {

    private String access;
    private String refresh;

}
