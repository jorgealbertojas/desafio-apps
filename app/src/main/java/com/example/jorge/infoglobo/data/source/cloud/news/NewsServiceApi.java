package com.example.jorge.infoglobo.data.source.cloud.news;

import com.example.jorge.infoglobo.data.source.cloud.news.model.Contents;
import com.example.jorge.infoglobo.data.source.cloud.news.model.News;

import java.util.List;

public interface NewsServiceApi {
        /**
         * Interface for signature Cars Service Callback
         * @param <T>
         */
        interface NewsServiceCallback<T> {

            void onLoaded(List<News> newsList);

        }

        void getNews(NewsServiceCallback<List<Contents>> callback);

    }

