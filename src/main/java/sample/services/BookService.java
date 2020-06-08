package sample.services;

import retrofit2.Call;
import retrofit2.http.*;
import sample.dto.in.BookDto;
import sample.dto.out.BookAddRequest;
import sample.dto.out.BookSettingsRequest;

import java.util.List;


public interface BookService {

    @GET("/books")
    Call<List<BookDto>> getAllBooks(@Query("title") String title);

    @POST("/books")
    Call<Void> addBook(@Body BookAddRequest bookAddRequest);

    @PATCH("/books")
    Call<Void> changeBookSettings(@Body BookSettingsRequest bookSettingsRequest);


}
