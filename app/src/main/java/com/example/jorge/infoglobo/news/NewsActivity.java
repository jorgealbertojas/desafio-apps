package com.example.jorge.infoglobo.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.jorge.infoglobo.R;
import com.example.jorge.infoglobo.util.Common;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        if (null == savedInstanceState) {
            if (Common.isOnline(this)) {
                initFragment(NewsFragment.newInstance());
            }
        }
    }


    /**
     * Init Fragment for news
     * @param newsFragment
     */
    private void initFragment(Fragment newsFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_news, newsFragment);
        transaction.commit();

    }
}
