package br.com.candidatodebolso.app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import br.com.candidatodebolso.app.R;
import br.com.candidatodebolso.app.activity.CandidateDetailsActivity;
import br.com.candidatodebolso.app.model.Candidate;

public class TestResultFragment extends Fragment {
    private static final String ARG_CANDIDATE = "candidate";

    private Candidate mCandidate;

    private TextView mNickname;
    private ImageView mPhoto;

    public TestResultFragment() {
        // Required empty public constructor
    }

    public static TestResultFragment newInstance(Candidate candidate) {
        TestResultFragment fragment = new TestResultFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_CANDIDATE, candidate);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCandidate = (Candidate) getArguments().getSerializable(ARG_CANDIDATE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test_result, container, false);
        mNickname = view.findViewById(R.id.nicknameCandidateResultTextView);
        mPhoto = view.findViewById(R.id.photoCandidateResultImageView);

        Glide.with(getContext()).asBitmap().load(mCandidate.getPhotoUrl()).into(mPhoto);

        mNickname.setText(mCandidate.getNickname());

        mPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CandidateDetailsActivity.class);
                intent.putExtra("candidate", mCandidate);
                getContext().startActivity(intent);
            }
        });
        return view;
    }
}
