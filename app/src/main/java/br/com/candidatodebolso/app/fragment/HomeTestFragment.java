package br.com.candidatodebolso.app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.com.candidatodebolso.app.R;


public class HomeTestFragment extends Fragment {

    private Button mStartNowButton;

    public HomeTestFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_test, container, false);

        mStartNowButton = view.findViewById(R.id.startNowButton);

        mStartNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.navigationFrameLayout, new QuestionFragment()).commit();
            }
        });
        return view;
    }

}
