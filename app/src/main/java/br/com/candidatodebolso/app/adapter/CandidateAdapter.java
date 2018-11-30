package br.com.candidatodebolso.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import br.com.candidatodebolso.app.R;
import br.com.candidatodebolso.app.activity.CandidateDetailsActivity;
import br.com.candidatodebolso.app.model.Candidate;

public class CandidateAdapter extends RecyclerView.Adapter<CandidateAdapter.CandidateHolder> {

    private List<Candidate> mCandidateList;
    private Context mContext;

    public CandidateAdapter(Context mContext, List<Candidate> mCandidateList) {
        this.mCandidateList = mCandidateList;
        this.mContext = mContext;
    }

    @Override
    public CandidateHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.candidate_item, parent, false);
        return new CandidateHolder(view);
    }

    @Override
    public void onBindViewHolder(CandidateHolder holder, int position) {
        final Candidate candidate = mCandidateList.get(position);
        holder.candidateName.setText(candidate.getNickname());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CandidateDetailsActivity.class);
                intent.putExtra("candidate", candidate);
                mContext.startActivity(intent);
            }
        });
        Glide.with(mContext).asBitmap().load(candidate.getPhotoUrl()).into(holder.candidatePhoto);
        Glide.with(mContext).asBitmap().load(candidate.getPoliticalParty().getImageUrl()).into(holder.politicalPartyImage);
    }

    @Override
    public int getItemCount() {
        return mCandidateList.size();
    }

    public static class CandidateHolder extends RecyclerView.ViewHolder {

        ImageView candidatePhoto;
        ImageView politicalPartyImage;
        TextView candidateName;
        ConstraintLayout constraintLayout;

        public CandidateHolder(View itemView) {
            super(itemView);
            candidatePhoto = itemView.findViewById(R.id.candidatePhotoImageViewItem);
            politicalPartyImage = itemView.findViewById(R.id.politicalPartyImageViewItem);
            candidateName = itemView.findViewById(R.id.candidateNameTextViewItem);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
        }
    }
}
