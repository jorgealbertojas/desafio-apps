package com.example.jorge.infoglobo.data.source.cloud.news;

import com.example.jorge.infoglobo.data.source.cloud.news.model.Contents;
import com.example.jorge.infoglobo.data.source.cloud.news.model.News;

public interface NewsServiceApi {
        /**
         * Interface for signature Cars Service Callback
         * @param <T>
         */
        interface NewsServiceCallback<T> {

            void onLoaded(News<Contents> contentsNews);

        }

        void getNews(NewsServiceCallback<News<Contents> > callback);

    }

