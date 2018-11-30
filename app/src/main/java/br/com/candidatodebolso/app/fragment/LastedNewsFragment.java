package br.com.candidatodebolso.app.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.candidatodebolso.app.R;
import br.com.candidatodebolso.app.adapter.NewsAdapter;
import br.com.candidatodebolso.app.model.News;
import br.com.candidatodebolso.app.webservice.NewsWebService;
import br.com.candidatodebolso.app.webservice.WebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class LastedNewsFragment extends Fragment {

    private List<News> mNewsList;
    private RecyclerView mRecyclerView;
    private View mView;

    public LastedNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_lasted_news, container, false);
        initNewsList();
        return mView;
    }


    private void initRecyclerView() {
        mRecyclerView = mView.findViewById(R.id.lastedNewsRecyclerView);

        NewsAdapter newsAdapter = new NewsAdapter(getContext(), mNewsList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(newsAdapter);
    }

    private void initNewsList() {
        NewsWebService newsWebService = WebService.getInstance().create(NewsWebService.class);
        Call<List<News>> call = newsWebService.listAll();
        call.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (response.code() == 200) {
                    mNewsList = response.body();
                    initRecyclerView();
                    ProgressBar progressBar = mView.findViewById(R.id.lastedNewsProgressBar);
                    progressBar.setVisibility(View.INVISIBLE);
                } else if (response.code() == 503) {
                    Toast.makeText(getContext(), "503 Error", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            initNewsList();
                        }
                    }, 1000);
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                Toast.makeText(getContext(), "Falhou!", Toast.LENGTH_LONG).show();
            }
        });

    }

}
