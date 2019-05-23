package com.example.jorge.infoglobo;

import com.example.jorge.infoglobo.data.source.cloud.news.model.Contents;
import com.example.jorge.infoglobo.data.source.local.LocalApi;
import com.example.jorge.infoglobo.news.NewsContract;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class FakeNewsServiceApi implements NewsContract.UserActionsListener {

    private final NewsContract.View mNewsContractView;

    public FakeNewsServiceApi(NewsContract.View mNewsContractView) {
        this.mNewsContractView = mNewsContractView;
    }

    @Override
    public void loadingNews() {
        String jsonstr = LocalApi.localApi;
        final Gson gson = new Gson();
        Type category = new TypeToken<Contents>(){}.getType();
        Contents contents = gson.fromJson(jsonstr, category);

        mNewsContractView.showNews(contents.getContents());
    }

    @Override
    public void start() {

    }

}
