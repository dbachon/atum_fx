package sample.services;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import sample.dto.in.AuthResponse;
import sample.dto.in.BookDto;
import sample.dto.in.RegisterRequest;
import sample.dto.out.BookAddRequest;

import java.util.List;


public interface BookService {

    @GET("/books")
    Call<List<BookDto>> getAllBooks(@Query("title") String title);

    @POST("/books")
    Call<Void> addBook(@Body BookAddRequest bookAddRequest);


}
