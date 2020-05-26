package sample.services;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import retrofit2.http.Query;
import sample.dto.in.CopyDto;
import sample.dto.out.CopyAddRequest;

import java.util.List;


public interface CopyService {


    @GET("/copies/title")
    Call<List<CopyDto>> getCopy(@Query("title") String title);

    @POST("/copies")
    Call<Void> addCopy(@Body CopyAddRequest copyAddRequest);


}
