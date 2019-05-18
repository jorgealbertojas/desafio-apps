package com.example.jorge.infoglobo.data.source.cloud.news;

import com.example.jorge.infoglobo.data.source.cloud.news.model.Contents;
import com.example.jorge.infoglobo.data.source.cloud.news.model.News;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.jorge.infoglobo.util.PathForApi.NEWS;

public interface NewsEndpoint {

    /**
     * Get order Popular API Retrofit
     */
    @GET(NEWS)
    Call<News<Contents>> getNews();

}
