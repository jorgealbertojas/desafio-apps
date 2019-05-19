package com.example.jorge.infoglobo.data.source.cloud.news;

import com.example.jorge.infoglobo.data.source.cloud.news.model.Contents;
import com.example.jorge.infoglobo.data.source.cloud.news.model.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class NewsServiceImpl implements NewsServiceApi {

    NewsEndpoint mRetrofit;

    public NewsServiceImpl(){
        mRetrofit = NewsClient.getNews().create(NewsEndpoint.class);
    }

    @Override
    public void getNews(final NewsServiceApi.NewsServiceCallback<List<Contents>> callback) {

        Call<List<Contents>> callNews  = mRetrofit.getNews();

        callNews.enqueue(new Callback<List<Contents>>() {
            @Override
            public void onResponse(Call<List<Contents>> call, Response<List<Contents>> response) {
                if(response.code()==200){
                    List<News> resultSearch = response.body().get(0).getContents();
                    callback.onLoaded(resultSearch);
                }
            }

            @Override
            public void onFailure(Call<List<Contents>> call, Throwable t) {

            }


        });
    }
}

