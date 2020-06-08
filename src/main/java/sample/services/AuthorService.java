package sample.services;

import retrofit2.Call;
import retrofit2.http.*;
import sample.dto.in.AuthorDto;
import sample.dto.out.AuthorAddRequest;
import sample.dto.out.AuthorSettingRequest;

import java.util.List;

public interface AuthorService {


    @GET("/authors")
    Call<List<AuthorDto>> getAuthors(@Query("firstName") String firstName, @Query("lastName") String lastName);


    @POST("/authors")
    Call<Void> addAuthor(@Body AuthorAddRequest authorAddRequest);

    @PATCH("/authors")
    Call<Void> changeAuthorSettings(@Body AuthorSettingRequest authorSettingRequest);


}
