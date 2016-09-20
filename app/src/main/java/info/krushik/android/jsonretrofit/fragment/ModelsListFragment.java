package info.krushik.android.jsonretrofit.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import java.util.ArrayList;

import info.krushik.android.jsonretrofit.Const;
import info.krushik.android.jsonretrofit.R;
import info.krushik.android.jsonretrofit.adapter.ModelsListRecyclerAdapter;
import info.krushik.android.jsonretrofit.model.Video;
import info.krushik.android.jsonretrofit.retrofit.RetrofitSingleton;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ModelsListFragment extends Fragment {
    private static final String TAG = ModelsListFragment.class.getSimpleName();
    private Subscription subscription;
    private ImageView loadingIndicator;
    private RecyclerView recyclerView;
    private ArrayList<Video> videos = new ArrayList<>();
    private boolean isLoading;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_models_list, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.refreshVideo:
                Log.d(TAG, "refresh clicked");
                RetrofitSingleton.resetModelsObservable();
                showLoadingIndicator(true);
                getModelsList();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_models_list, container, false);

        if (savedInstanceState != null) {
            videos = savedInstanceState.getParcelableArrayList(Const.KEY_MODELS);
            isLoading = savedInstanceState.getBoolean(Const.KEY_IS_LOADING);
        }

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler);
        loadingIndicator = (ImageView) v.findViewById(R.id.loading_indicator);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new ModelsListRecyclerAdapter(videos));

//        if (videos.size() == 0 || isLoading) {
//            showLoadingIndicator(true);
//            getModelsList();
//        }

        return v;
    }

    private void showLoadingIndicator(boolean show) {
        isLoading = show;
        if (isLoading) {
            loadingIndicator.setVisibility(View.VISIBLE);
            loadingIndicator.animate().setInterpolator(new AccelerateDecelerateInterpolator()).rotationBy(360).setDuration(500).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    loadingIndicator.animate().setInterpolator(new AccelerateDecelerateInterpolator()).rotationBy(360).setDuration(500).setListener(this);
                }
            });
        }
        else {
            loadingIndicator.animate().cancel();
            loadingIndicator.setVisibility(View.GONE);
        }
    }

    private void getModelsList() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        subscription = RetrofitSingleton.getModelsObservable().
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber<ArrayList<Video>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError", e);
                        isLoading = false;
                        if (isAdded()) {
                            showLoadingIndicator(false);
                            Snackbar.make(recyclerView, R.string.connection_error, Snackbar.LENGTH_SHORT)
                                    .setAction(R.string.try_again, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            RetrofitSingleton.resetModelsObservable();
                                            showLoadingIndicator(true);
                                            getModelsList();
                                        }
                                    })
                                    .show();
                        }
                    }

                    @Override
                    public void onNext(ArrayList<Video> newVideos) {
                        Log.d(TAG, "onNext: " + newVideos.size());
                        int prevSize = videos.size();
                        isLoading = false;
                        if (isAdded()) {
                            recyclerView.getAdapter().notifyItemRangeRemoved(0, prevSize);
                        }
                        videos.clear();
                        videos.addAll(newVideos);
                        if (isAdded()) {
                            recyclerView.getAdapter().notifyItemRangeInserted(0, videos.size());
                            showLoadingIndicator(false);
                        }
                    }
                });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(Const.KEY_MODELS, videos);
        outState.putBoolean(Const.KEY_IS_LOADING, isLoading);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}