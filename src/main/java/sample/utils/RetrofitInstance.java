package sample.utils;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    public static Retrofit getInstance() {
        return ourInstance;
    }

    private RetrofitInstance() {

    }

    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build();



    private static Retrofit ourInstance = new  Retrofit.Builder()
            .baseUrl("http://localhost/")

            .addConverterFactory(GsonConverterFactory.create(
                    new GsonBuilder()
                            .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) ->
                                    LocalDateTime.parse(json.getAsString()))
                            .create()
            ))
            .client(okHttpClient)
            .build();
}
