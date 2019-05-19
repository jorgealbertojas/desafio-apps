package com.example.jorge.infoglobo.detailNews;

import com.example.jorge.infoglobo.data.source.cloud.news.model.News;

public class DetailNewsContract {

    interface View {

        void setLoading(boolean isActive);

        void showDetailNews(News news);

    }

    interface UserActionsListener {

        void loadingDetailNews(News news);


    }
}

