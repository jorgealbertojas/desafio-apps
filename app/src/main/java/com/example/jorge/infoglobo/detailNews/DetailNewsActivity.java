package com.example.jorge.infoglobo.detailNews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import com.example.jorge.infoglobo.R;
import com.example.jorge.infoglobo.data.source.cloud.news.model.News;
import com.example.jorge.infoglobo.util.Common;

import static com.example.jorge.infoglobo.news.NewsFragment.EXTRA_NEWS;

public class DetailNewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);

        News moviesDetail = (News) getIntent().getExtras().getSerializable(EXTRA_NEWS);

        if (null == savedInstanceState) {
            if (Common.isOnline(this)) {
                initFragment(moviesDetail);
            }
        }
    }

    private void initFragment(News news) {
        DetailNewsFragment detailNewsFragment =
                (DetailNewsFragment) getSupportFragmentManager().findFragmentById(R.id.fl_detail_news);

        if (detailNewsFragment == null) {
            // Create the fragment
            detailNewsFragment = DetailNewsFragment.newInstance(news);
            Common.addFragmentToActivity(
                    getSupportFragmentManager(), detailNewsFragment, R.id.fl_detail_news);
        }
    }
}
