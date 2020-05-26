package sample.services;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import sample.dto.in.AuthResponse;
import sample.dto.in.BookDto;
import sample.dto.in.LoginRequest;
import sample.dto.in.RegisterRequest;

import java.util.List;


public interface AuthService {

    @POST("/auth/login")
    Call<AuthResponse> login(@Body LoginRequest loginRequest);

    @POST("/auth/register")
    Call<AuthResponse> register(@Body RegisterRequest registerRequest);

}
