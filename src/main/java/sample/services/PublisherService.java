package sample.services;

import retrofit2.Call;
import retrofit2.http.*;
import sample.dto.in.PublisherBookDto;
import sample.dto.out.PublisherAddRequest;

import java.util.List;

public interface PublisherService {

    @POST("/publishers")
    Call<Void> addPublisher(@Body PublisherAddRequest publisherAddRequest);

    @GET("/publishers")
    Call<List<PublisherBookDto>> getPublishers(@Query("name") String name);
}