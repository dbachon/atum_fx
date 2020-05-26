package sample.services;

import retrofit2.Call;
import retrofit2.http.*;
import sample.AppState;
import sample.dto.in.BookDto;
import sample.dto.in.BorrowingDto;
import sample.dto.out.BookAddRequest;
import sample.dto.out.BorrowingAcceptRequest;
import sample.dto.out.BorrowingAddRequest;
import sample.dto.out.BorrowingReturnRequest;
import sample.utils.enums.Status;

import java.util.List;

public interface BorrowingService {

    @GET("/borrowings")
    Call<List<BorrowingDto>> getMyBorrowings(@Header ("Authorization") String token, @Query("status") Status status);

    @GET("/borrowings/all")
    Call<List<BorrowingDto>> getBorrowings(@Query("email") String email, @Query("status") Status status);

    @GET("/borrowings/all")
    Call<List<BorrowingDto>> getAllBorrowings();

    @POST("/borrowings")
    Call<Void> createBorrowing(@Header ("Authorization") String token, @Body BorrowingAddRequest borrowingAddRequest);

    @POST("/borrowings/settings")
    Call<Void> borrowingChangeSettings(@Body BorrowingAcceptRequest borrowingAcceptRequest);

    @PATCH("/borrowings")
    Call<Void> returnBorrowing(@Body BorrowingReturnRequest borrowingReturnRequest);


}
