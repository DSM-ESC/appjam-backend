package me.mocha.appjam.retrofit;

import me.mocha.appjam.payload.request.action.SendIotServerRequest;
import me.mocha.appjam.payload.response.IOTResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IOTService {

    @POST("/iot")
    Call<IOTResponse> sendAction(@Body SendIotServerRequest request);

}
