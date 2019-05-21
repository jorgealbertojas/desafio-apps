package com.example.jorge.infoglobo.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.jorge.infoglobo.R;
import com.example.jorge.infoglobo.data.source.cloud.news.NewsServiceImpl;
import com.example.jorge.infoglobo.data.source.cloud.news.model.News;
import com.example.jorge.infoglobo.detailNews.DetailNewsActivity;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class NewsFragment extends Fragment implements NewsContract.View{


    public final static String EXTRA_NEWS = "EXTRA_NEWS";
    private NewsAdapter mListAdapter;
    private RecyclerView mRecyclerView;
    private static NewsContract.UserActionsListener mActionsListener;
    private NewsContract.View mNewsContract;

    LinearLayoutManager mLinearLayoutManager;

    private static Bundle mBundleRecyclerViewState;
    private final String KEY_RECYCLER_STATE = "RECYCLER_VIEW_STATE";
    private final String KEY_ADAPTER_STATE = "ADAPTER_STATE";
    private Parcelable mListState;

    private List<News> mListNews;

    /**
     * Constructor
     */
    public NewsFragment() {
    }

    /**
     * Constructor with new Instance
     * @return
     */
    public static NewsFragment newInstance() {
        return new NewsFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mNewsContract = this;
        if (savedInstanceState == null) {
            mListAdapter = new NewsAdapter(new ArrayList<News>(), mItemListener);
            mActionsListener = new NewsPresenter(new NewsServiceImpl(), this);
            mActionsListener.loadingNews();
            mActionsListener.start();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mBundleRecyclerViewState != null) {
            mListState = mBundleRecyclerViewState.getParcelable(KEY_RECYCLER_STATE);
            mListNews = (List<News>) mBundleRecyclerViewState.getSerializable(KEY_ADAPTER_STATE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_news, container, false);

        SwipeRefreshLayout swipeRefreshLayout =
                (SwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                mActionsListener.loadingNews();
            }
        });


        if (savedInstanceState== null){
            initRecyclerView(root);
            mBundleRecyclerViewState = new Bundle();
            Parcelable listState = mRecyclerView.getLayoutManager().onSaveInstanceState();
            mBundleRecyclerViewState.putParcelable(KEY_RECYCLER_STATE, listState);
        }else{
            initRecyclerView(root);
            mListState = mBundleRecyclerViewState.getParcelable(KEY_RECYCLER_STATE);
            mListNews = (List<News>) mBundleRecyclerViewState.getSerializable(KEY_ADAPTER_STATE);
            mRecyclerView.getLayoutManager().onRestoreInstanceState(mListState);
            mListAdapter = new NewsAdapter(mListNews,mItemListener);
            mRecyclerView.setAdapter(mListAdapter);

        }

        return root;
    }

    /**
     * Init RecyclerView fro show list car
     * @param root
     */
    private void initRecyclerView(View root){
        mRecyclerView= (RecyclerView) root.findViewById(R.id.rv_news_list);
        mRecyclerView.setAdapter(mListAdapter);

        mRecyclerView.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

    }

    @Override
    public void onPause() {
        super.onPause();

        mBundleRecyclerViewState = new Bundle();
        mListState = mRecyclerView.getLayoutManager().onSaveInstanceState();
        mListNews = (List<News>) mListAdapter.newsList;
        mBundleRecyclerViewState.putParcelable(KEY_RECYCLER_STATE, mListState);
        mBundleRecyclerViewState.putSerializable(KEY_ADAPTER_STATE, (Serializable) mListNews);
    }

    @Override
    public void setLoading(final boolean isActive) {
        if (getView() == null) {
            return;
        }
        final SwipeRefreshLayout srl =
                (SwipeRefreshLayout) getView().findViewById(R.id.refresh_layout);

        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(isActive);
            }
        });
    }

    @Override
    public void showNews(List<News> newsList) {
        if (mRecyclerView.getAdapter().getItemCount() > 0) {
            newsList.addAll(0,(List<News>) mListAdapter.newsList);
        }
        mListAdapter.replaceData(newsList);
    }


    @Override
    public void setPresenter(NewsContract.UserActionsListener presenter) {
        mActionsListener = checkNotNull(presenter);

    }

    /**
     * Adapter for fill the list of the News
     */
    private class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

        private List<News> newsList;
        private ItemListener mItemListener;

        public NewsAdapter(List<News> newsList, ItemListener itemListener) {
            setList(newsList);
            mItemListener = itemListener;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View noteView = inflater.inflate(R.layout.item_news, parent, false);

            return new ViewHolder(noteView, mItemListener);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            News news = newsList.get(position);


            int imageDimension =
                    (int) viewHolder.newsImage.getContext().getResources().getDimension(R.dimen.image_height_size);

            int imageWight =
                    (int) viewHolder.newsImage.getContext().getResources().getDimension(R.dimen.image_width_size);

            if (news.getImage().size() > 0) {
                Picasso.with(viewHolder.newsImage.getContext())
                        .load(news.getImage().get(0).getUrl())
                        .resize(imageWight, imageDimension)
                        .onlyScaleDown()
                        .error(R.drawable.ic_error_black_24dp)
                        .into(viewHolder.newsImage);
            }

            viewHolder.title.setText(news.getTitle());
            viewHolder.editoria.setText(news.getEditoria().getName());

        }

        public void replaceData(List<News> newsList) {
            setList(newsList);
            mRecyclerView.getAdapter().notifyDataSetChanged();
        }



        private void setList(List<News> newsList) {
            this.newsList = newsList;
        }

        @Override
        public int getItemCount() {
            if (newsList != null) {
                return newsList.size();
            }else{
                return 0;
            }
        }

        public News getItem(int position) {
            return newsList.get(position);
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            public ImageView newsImage;
            public TextView title;
            public TextView editoria;
            private ItemListener mItemListener;

            public ViewHolder(View itemView, ItemListener listener) {
                super(itemView);
                mItemListener = listener;
                title = (TextView) itemView.findViewById(R.id.tv_title);
                editoria = (TextView) itemView.findViewById(R.id.tv_editoria);
                newsImage = (ImageView) itemView.findViewById(R.id.iv_image_news);

                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                News news = getItem(position);
                mItemListener.onNewsClick(news);

                Intent intent = new Intent(getContext(), DetailNewsActivity.class);
                intent.putExtra(EXTRA_NEWS, news);
                startActivity(intent);

            }
        }
    }

    /**
     * Listener which car click
     */
    ItemListener mItemListener = new ItemListener() {
        @Override
        public void onNewsClick(News clickedNews) {
            mActionsListener.loadingNews();
        }


    };

    public interface ItemListener {

        void onNewsClick(News clickedNews);
    }
}

