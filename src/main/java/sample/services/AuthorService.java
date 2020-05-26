package sample.services;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import sample.dto.in.AuthorDto;
import sample.dto.out.AuthorAddRequest;


import java.util.List;

public interface AuthorService {


    @GET("/authors")
    Call<List<AuthorDto>> getAuthors(@Query("firstName") String firstName, @Query("lastName") String lastName);


    @POST("/authors")
    Call<Void> addAuthor(@Body AuthorAddRequest authorAddRequest);
}
