package com.gig.georgy.tinkoffnews.common.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gig.georgy.tinkoffnews.R;
import com.gig.georgy.tinkoffnews.model.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by georgy on 26.11.2017.
 * GIG
 */

public class RecyclerNewsAdapter extends RecyclerView.Adapter<RecyclerNewsAdapter.CategoryItemViewHolder> {

    private List<News> newsList;

    public RecyclerNewsAdapter() {
        this.newsList = new ArrayList<>();
    }

    @Override
    public CategoryItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.news_recycler_item, parent, false);
        return new CategoryItemViewHolder(view);
    }

    public void setNews(List<News> newsList) {
        this.newsList = newsList;
        notifyDataSetChanged();
    }

    public void update(List<News> newsList) {
        this.newsList.clear();
        this.newsList.addAll(newsList);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(CategoryItemViewHolder holder, int position) {

        final News news = newsList.get(position);

        // отображаем данные
        holder.tvTitle.setText(news.getText());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    // view holder class ======================
    class CategoryItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;

        CategoryItemViewHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }
}
