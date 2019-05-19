package com.example.jorge.infoglobo.news;

import com.example.jorge.infoglobo.data.source.cloud.news.NewsServiceApi;
import com.example.jorge.infoglobo.data.source.cloud.news.model.Contents;
import com.example.jorge.infoglobo.data.source.cloud.news.model.News;

import java.util.List;

public class NewsPresenter implements NewsContract.UserActionsListener {

    private final NewsServiceApi mNewsServiceApi;
    private final NewsContract.View mNewsContractView;


    public NewsPresenter(NewsServiceApi mNewsServiceApi,NewsContract.View mNewsContract_View) {
        this.mNewsContractView = mNewsContract_View;
        this.mNewsServiceApi = mNewsServiceApi;
    }


    /**
     * Loading the news call Service Api with data
     */
    @Override
    public void loadingNews() {
        mNewsContractView.setLoading(true);
        mNewsServiceApi.getNews(new NewsServiceApi.NewsServiceCallback<List<Contents>>(){

            @Override
            public void onLoaded(List<News> listNews) {
                mNewsContractView.setLoading(false);
                mNewsContractView.showNews(listNews);
            }
        });

    }


    @Override
    public void start() {

    }
}
