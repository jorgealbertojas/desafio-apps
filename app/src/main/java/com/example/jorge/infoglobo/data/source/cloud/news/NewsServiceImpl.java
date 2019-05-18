package com.example.jorge.infoglobo.data.source.cloud.news;

import com.example.jorge.infoglobo.data.source.cloud.news.model.Contents;
import com.example.jorge.infoglobo.data.source.cloud.news.model.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsServiceImpl implements NewsServiceApi {

    NewsEndpoint mRetrofit;
    private static int page = 1;

    public NewsServiceImpl(){
        mRetrofit = NewsClient.getNews().create(NewsEndpoint.class);
    }

    @Override
    public void getNews(final NewsServiceApi.NewsServiceCallback<News<Contents>> callback) {

        Call<News<Contents>> callNews  = mRetrofit.getNews();

        callNews.enqueue(new Callback<News<Contents>>() {
            @Override
            public void onResponse(Call<News<Contents>> call, Response<News<Contents>> response) {
                if(response.code()==200){
                    News<Contents> resultSearch = response.body();
                    callback.onLoaded(resultSearch);
                }
            }

            @Override
            public void onFailure(Call<News<Contents>> call, Throwable t) {

            }


        });
    }
}

