package br.com.candidatodebolso.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

import br.com.candidatodebolso.app.R;
import br.com.candidatodebolso.app.model.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    private List<News> mNewsList;
    private Context mContext;

    public NewsAdapter(Context mContext, List<News> mNewsList) {
        Collections.reverse(mNewsList);
        this.mNewsList = mNewsList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsHolder holder, int position) {
        final News news = mNewsList.get(position);
        holder.portalTextView.setText(news.getPortal().getName().toUpperCase());
        holder.titleTextView.setText(news.getTitle());
        holder.descriptionTextView.setText(news.getDescription());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(news.getLink()));
                mContext.startActivity(intent);
            }
        });
        Glide.with(mContext).asBitmap().load(news.getImageUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    static class NewsHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView portalTextView;
        TextView titleTextView;
        TextView descriptionTextView;
        ConstraintLayout constraintLayout;

        NewsHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.newsImageViewItem);
            portalTextView = itemView.findViewById(R.id.newsPortalTextViewItem);
            titleTextView = itemView.findViewById(R.id.newsTitleTextViewItem);
            descriptionTextView = itemView.findViewById(R.id.newsDescriptionTextViewItem);
            constraintLayout = itemView.findViewById(R.id.newsConstraintLayoutItem);
        }
    }
}
