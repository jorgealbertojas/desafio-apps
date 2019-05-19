package com.example.jorge.infoglobo.news;

import com.example.jorge.infoglobo.BasePresenter;
import com.example.jorge.infoglobo.BaseView;
import com.example.jorge.infoglobo.data.source.cloud.news.model.News;

import java.util.List;

/**
 * Contract of the News with signature method for View and User actions   .
 */
public class NewsContract {

    public interface View extends BaseView<UserActionsListener> {

        void setLoading(boolean isActive);

        void showNews(List<News> newsList);

    }

    public interface UserActionsListener extends BasePresenter {

        void loadingNews();


    }
}
