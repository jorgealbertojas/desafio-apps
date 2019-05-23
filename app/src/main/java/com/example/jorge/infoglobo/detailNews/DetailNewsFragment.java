package com.example.jorge.infoglobo.detailNews;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
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

    TextView name;

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

        AppCompatImageView AppCompatImageView = root.findViewById(R.id.aciv_back);
        AppCompatImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        name = root.findViewById(R.id.tv_name);

        AppCompatImageView share = root.findViewById(R.id.iv_share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                if (mNews.getTitle() != null) {
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, mNews.getTitle());
                }else{
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "O GLOBO");
                }
                if (mNews.getText() != null) {
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, mNews.getText());
                }else{
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "O GLOBO");
                }
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

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

        name.setText(mNews.getEditoria().getName());

        mTitle.setText(mNews.getTitle());
        mSubtitle.setText(mNews.getSubTitle());
        if (!Common.isNullOrEmpty(mNews.getActors())){
            mEditoria.setText(mNews.getActors().get(0));
        }
        mDateTime.setText(Common.formatDate(mNews.getPublishedIn()));
        if (!Common.isNullOrEmpty(mNews.getImage())) {
            mSubtitleSource.setText(mNews.getImage().get(0).getSubtitle() + " " + mNews.getImage().get(0).getSource());
        }
        mText.setText(mNews.getText());

        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/" + "times_new_roman.ttf");
        mText.setTypeface(tf);


       int imageDimension =
                (int) mMovieImage.getContext().getResources().getDimension(R.dimen.image_height_size);

        int imageWight =
                (int) mMovieImage.getContext().getResources().getDimension(R.dimen.image_width_size);

        if (!Common.isNullOrEmpty(mNews.getImage())) {
            Picasso.with(mMovieImage.getContext())
                    .load(mNews.getImage().get(0).getUrl())
                    .resize(imageWight, imageDimension)
                    .onlyScaleDown()
                    .error(R.drawable.ic_error_black_24dp)
                    .into(mMovieImage);
        }else{
            Picasso.with(mMovieImage.getContext())
                    .load(R.mipmap.ic_globo)
                    .resize(imageWight, imageDimension)
                    .onlyScaleDown()
                    .error(R.drawable.ic_error_black_24dp)
                    .into(mMovieImage);
        }
    }

}


