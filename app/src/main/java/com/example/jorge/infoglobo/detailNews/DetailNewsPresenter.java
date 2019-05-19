package com.example.jorge.infoglobo.detailNews;

import android.content.Context;
import com.example.jorge.infoglobo.data.source.cloud.news.model.News;

public class DetailNewsPresenter implements DetailNewsContract.UserActionsListener{
    private DetailNewsContract.View mDetailNewsContractView;
    private Context mContext;

    @Override
    public void loadingDetailNews(News news) {
        mDetailNewsContractView.setLoading(false);
        mDetailNewsContractView.showDetailNews(news);
    }

    public DetailNewsPresenter( DetailNewsContract.View mDetailNewsContract_View, Context context) {
        this.mContext = context;
        this.mDetailNewsContractView = mDetailNewsContract_View;

    }


}
