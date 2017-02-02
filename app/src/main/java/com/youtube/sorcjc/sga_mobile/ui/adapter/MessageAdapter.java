package com.youtube.sorcjc.sga_mobile.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youtube.sorcjc.sga_mobile.R;
import com.youtube.sorcjc.sga_mobile.domain.Course;
import com.youtube.sorcjc.sga_mobile.domain.CourseNote;
import com.youtube.sorcjc.sga_mobile.domain.Message;
import com.youtube.sorcjc.sga_mobile.ui.CourseActivity;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    // private static final String TAG = "MessageAdapter";

    private ArrayList<Message> dataSet;

    static class ViewHolder extends RecyclerView.ViewHolder {
        // context

        Context context;
        // text views
        TextView tvName;
        TextView tvContent;

        // buttons

        // data
        String key;

        // int position;

        ViewHolder(View v) {
            super(v);
            context = v.getContext();

            tvName = (TextView) v.findViewById(R.id.tvName);
            tvContent = (TextView) v.findViewById(R.id.tvContent);
        }

    }

    public MessageAdapter() {
        this.dataSet = new ArrayList<>();
    }

    public void setDataSet(ArrayList<Message> dataSet) {
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }

    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_message, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message currentMessage = dataSet.get(position);

        holder.tvName.setText(currentMessage.getName());
        holder.tvContent.setText(currentMessage.getContent());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}