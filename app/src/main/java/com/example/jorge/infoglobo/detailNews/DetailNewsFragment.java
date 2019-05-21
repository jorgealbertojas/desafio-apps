package com.example.jorge.infoglobo.detailNews;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.jorge.infoglobo.R;
import com.example.jorge.infoglobo.data.source.cloud.news.model.News;
import com.example.jorge.infoglobo.util.Common;
import com.squareup.picasso.Picasso;

public class DetailNewsFragment extends Fragment implements DetailNewsContract.View {

    private DetailNewsContract.UserActionsListener mActionsListener;

    private ImageView mMovieImage;
    private TextView mTitle;
    private TextView mSubtitle;
    private TextView mEditoria;
    private TextView mDateTime;
    private TextView mSubtitleSource;
    private TextView mText;


    public static News mNews;

    public DetailNewsFragment() {
    }

    public static DetailNewsFragment newInstance(News news) {
        mNews = news;
        return new DetailNewsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActionsListener = new DetailNewsPresenter(this, getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        mActionsListener.loadingDetailNews(mNews);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_detail_news, container, false);

        mMovieImage = (ImageView) root.findViewById(R.id.iv_image_news_detail);
        mTitle = (TextView) root.findViewById(R.id.tv_title);
        mSubtitle = (TextView) root.findViewById(R.id.tv_subtitle);
        mEditoria = (TextView) root.findViewById(R.id.tv_editoria);
        mDateTime = (TextView) root.findViewById(R.id.tv_date_time);
        mSubtitleSource = (TextView) root.findViewById(R.id.tv_subtitle_source);
        mText = (TextView) root.findViewById(R.id.tv_text);

        return root;
    }

    @Override
    public void setLoading(boolean isActive) {

    }

    @Override
    public void showDetailNews(News news) {

        mNews = news;

        getActivity().setTitle(mNews.getEditoria().getName());

        mTitle.setText(mNews.getTitle());
        mSubtitle.setText(mNews.getSubTitle());
        if (mNews.getActors() != null){
            mEditoria.setText(mNews.getActors().get(0));
        }
        mDateTime.setText(Common.formatDate(mNews.getPublishedIn()));
        mSubtitleSource.setText(mNews.getImage().get(0).getSubtitle() + " " + mNews.getImage().get(0).getSource());
        mText.setText(mNews.getText());

        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/" + "times_new_roman.ttf");
        mText.setTypeface(tf);


        int imageDimension =
                (int) mMovieImage.getContext().getResources().getDimension(R.dimen.card_height);

        int imageWight =
                (int) mMovieImage.getContext().getResources().getDimension(R.dimen.image_wight);

        Picasso.with(mMovieImage.getContext())
                .load(mNews.getImage().get(0).getUrl())
                .resize(imageWight,imageDimension)
                .onlyScaleDown()
                .error(R.drawable.ic_error_black_24dp)
                .into(mMovieImage);
    }

}


