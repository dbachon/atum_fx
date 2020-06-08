package sample.services;

import retrofit2.Call;
import retrofit2.http.*;
import sample.dto.in.CopyDto;
import sample.dto.out.CopyAddRequest;
import sample.dto.out.CopySettingsRequest;

import java.util.List;


public interface CopyService {


    @GET("/copies/title")
    Call<List<CopyDto>> getCopy(@Query("title") String title);

    @GET("/copies/all")
    Call<List<CopyDto>> getAllCopy(@Query("title") String title, @Query("code") String code);


    @POST("/copies")
    Call<Void> addCopy(@Body CopyAddRequest copyAddRequest);


    @PATCH("/copies")
    Call<Void> changeCopySettings(@Body CopySettingsRequest copySettingsRequest);



}
