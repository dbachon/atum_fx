package sample.services;

import retrofit2.Call;
import retrofit2.http.*;
import sample.dto.in.UserDto;
import sample.dto.out.UserChangeSettingsRequest;

import java.util.List;

public interface UserService {

    @GET("/users")
    Call<List<UserDto>> getUsers(@Query("firstName") String lastName,  @Query("lastName") String firstName, @Query("email") String email);

    @GET("/users/my")
    Call<UserDto> getUser(@Header ("Authorization") String token);

    @PATCH("/users")
    Call<Void> changeUserSettings(@Body UserChangeSettingsRequest userChangeSettingsRequest);
}

