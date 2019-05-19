package com.example.jorge.infoglobo.data.source.cloud.news;

import com.example.jorge.infoglobo.data.source.cloud.news.model.Contents;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

import static com.example.jorge.infoglobo.util.PathForApi.NEWS;

public interface NewsEndpoint {

    /**
     * Get order Popular API Retrofit
     */
    @GET(NEWS)
    Call<List<Contents>> getNews();

}
