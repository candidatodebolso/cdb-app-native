package br.com.candidatodebolso.app.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import br.com.candidatodebolso.app.R;
import br.com.candidatodebolso.app.model.Candidate;

public class CandidateDetailsActivity extends AppCompatActivity {

    private Candidate mCandidate;
    private ImageView mPhotoCandidate;
    private TextView mNickname;
    private ImageView mImagePoliticalParty;
    private TextView mFullName;
    private TextView mNamePoliticalParty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_details);

        mCandidate = (Candidate) getIntent().getSerializableExtra("candidate");

        mPhotoCandidate = findViewById(R.id.candidatePhotoImageViewItem);
        mNickname = findViewById(R.id.candidateNameTextViewItem);
        mImagePoliticalParty = findViewById(R.id.politicalPartyImageViewItem);
        mFullName = findViewById(R.id.fullNameTextView);
        mNamePoliticalParty = findViewById(R.id.politicalPartyNameTextView);

        initCandidate();

        setTitle(mCandidate.getNickname());
    }

    private void initCandidate() {
        Glide.with(this).asBitmap().load(mCandidate.getPhotoUrl()).into(mPhotoCandidate);
        Glide.with(this).asBitmap().load(mCandidate.getPoliticalParty().getImageUrl()).into(mImagePoliticalParty);
        mNickname.setText(mCandidate.getNickname());
        mFullName.setText(mCandidate.getFullName());
        mNamePoliticalParty.setText(mCandidate.getPoliticalParty().getInitials() + " - " + mCandidate.getPoliticalParty().getFullName());
    }
}
