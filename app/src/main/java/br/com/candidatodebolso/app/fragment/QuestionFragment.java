package br.com.candidatodebolso.app.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.candidatodebolso.app.R;
import br.com.candidatodebolso.app.model.Answer;
import br.com.candidatodebolso.app.model.Candidate;
import br.com.candidatodebolso.app.model.Question;
import br.com.candidatodebolso.app.webservice.QuestionWebService;
import br.com.candidatodebolso.app.webservice.WebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {

    private List<Question> mQuestions;
    private TextView mQuestionTextView;
    private Button mYesButton;
    private Button mNoButton;
    private Button mDependsButton;
    private ProgressBar mProgressBar;
    private int mIndex;
    private QuestionWebService mQuestionWebService;

    public QuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question, container, false);

        mQuestionWebService = WebService.getInstance().create(QuestionWebService.class);

        mQuestionTextView = view.findViewById(R.id.questionTextView);
        mYesButton = view.findViewById(R.id.yesButton);
        mNoButton = view.findViewById(R.id.noButton);
        mDependsButton = view.findViewById(R.id.dependsButton);
        mProgressBar = view.findViewById(R.id.questionProgressBar);

        setQuestionViewVisibility(View.INVISIBLE);

        mIndex = -1;

        initQuestions();

        mYesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mQuestions != null) {
                    mQuestions.get(mIndex).setAnswer("YES");
                    nextQuestion();
                }
            }
        });

        mNoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mQuestions != null) {
                    mQuestions.get(mIndex).setAnswer("NO");
                    nextQuestion();
                }
            }
        });

        mDependsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mQuestions != null) {
                    mQuestions.get(mIndex).setAnswer("DEPENDS");
                    nextQuestion();
                }
            }
        });
        return view;
    }

    private void setQuestionViewVisibility(int visibility) {
        mQuestionTextView.setVisibility(visibility);
        mYesButton.setVisibility(visibility);
        mNoButton.setVisibility(visibility);
        mDependsButton.setVisibility(visibility);
    }

    private void initQuestions() {


        mQuestionWebService.listAll().enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(@NonNull Call<List<Question>> call, @NonNull Response<List<Question>> response) {
                if (response.code() == 200) {
                    mQuestions = response.body();
                    nextQuestion();
                    mProgressBar.setVisibility(View.INVISIBLE);
                    setQuestionViewVisibility(View.VISIBLE);
                } else if (response.code() == 503) {
                    Toast.makeText(getContext(), "503 Error", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            initQuestions();
                        }
                    }, 1000);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Question>> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void nextQuestion() {
        if (mIndex < (mQuestions.size() - 1)) {
            Question question = mQuestions.get(++mIndex);
            mQuestionTextView.setText(question.getQuestion());
        } else {
            Toast.makeText(getContext(), "O teste acabou", Toast.LENGTH_SHORT).show();
            showResult();
        }
    }

    private void showResult() {

        mQuestionWebService.send(mQuestions).enqueue(new Callback<Candidate>() {
            @Override
            public void onResponse(Call<Candidate> call, Response<Candidate> response) {
                if (response.code() == 200) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.navigationFrameLayout, TestResultFragment.newInstance(response.body())).commit();
                } else if (response.code() == 503) {
                    Toast.makeText(getContext(), "503 Error", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showResult();
                        }
                    }, 1000);
                }
            }

            @Override
            public void onFailure(Call<Candidate> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
