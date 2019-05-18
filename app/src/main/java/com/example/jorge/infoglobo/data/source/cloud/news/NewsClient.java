package com.example.jorge.infoglobo.data.source.cloud.news;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.jorge.infoglobo.util.PathForApi.BASE_URL;

/**
 * Client for get data with retrofit
 */
public class NewsClient {


    private static Retrofit retrofit = null;

    private static Gson gson = new GsonBuilder()
            .create();

    /**
     * This function get Retrofit for get Json
     * @return
     */
    public static Retrofit getNews() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

}
