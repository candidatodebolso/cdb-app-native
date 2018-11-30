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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.candidatodebolso.app.R;
import br.com.candidatodebolso.app.adapter.CandidateAdapter;
import br.com.candidatodebolso.app.model.Candidate;
import br.com.candidatodebolso.app.webservice.CandidateWebService;
import br.com.candidatodebolso.app.webservice.WebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class CandidateListFragment extends Fragment {

    private List<Candidate> mCandidateList;

    private RecyclerView mRecyclerView;

    private View mView;

    public CandidateListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_candidate_list, container, false);
        initCandidateList();
        return mView;
    }

    private void initRecyclerView() {
        mRecyclerView = mView.findViewById(R.id.candidateRecyclerView);
        CandidateAdapter candidateAdapter = new CandidateAdapter(getContext(), mCandidateList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(candidateAdapter);
    }

    private void initCandidateList() {
        CandidateWebService candidateWebService = WebService.getInstance().create(CandidateWebService.class);
        Call<List<Candidate>> call = candidateWebService.listAll();
        call.enqueue(new Callback<List<Candidate>>() {
            @Override
            public void onResponse(Call<List<Candidate>> call, Response<List<Candidate>> response) {
                if (response.code() == 200) {
                    mCandidateList = response.body();
                    Collections.sort(mCandidateList, new Comparator<Candidate>() {
                        @Override
                        public int compare(Candidate o1, Candidate o2) {
                            return o1.getNickname().compareTo(o2.getNickname());
                        }
                    });
                    initRecyclerView();
                    ProgressBar progressBar = mView.findViewById(R.id.candidateListProgressBar);
                    progressBar.setVisibility(View.INVISIBLE);
                } else if (response.code() == 503) {
                    Toast.makeText(getContext(), "503 Error", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            initCandidateList();
                        }
                    }, 1000);
                }
            }

            @Override
            public void onFailure(Call<List<Candidate>> call, Throwable t) {
                Toast.makeText(getContext(), "Falhou!", Toast.LENGTH_LONG).show();
            }
        });
    }

}
