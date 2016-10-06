package com.youtube.sorcjc.sga_mobile.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.youtube.sorcjc.sga_mobile.R;
import com.youtube.sorcjc.sga_mobile.domain.Course;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MessageViewHolder> {

    private ArrayList<Course> courses;
    private Context context;

    public CourseAdapter(Context context) {
        this.context = context;
        this.courses = new ArrayList<>();
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_course, parent, false);
        return new MessageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        Course course = courses.get(position);

        holder.setName(course.getName());
        holder.setTeacher(course.getTeacher());
        holder.setDetails(course.getCredits(), course.getType());
        holder.setCourseClickEvent(course.getId());
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public void setAll(ArrayList<Course> courses) {
        if (courses == null)
            throw new NullPointerException("The results cannot be null.");

        this.courses = courses;
        notifyDataSetChanged();
    }

    class MessageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvName;
        private TextView tvTeacher;
        private TextView tvDetails;
        private View itemCourse;

        MessageViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvTeacher = (TextView) itemView.findViewById(R.id.tvTeacher);
            tvDetails = (TextView) itemView.findViewById(R.id.tvDetails);
            itemCourse = itemView.findViewById(R.id.itemCourse);
        }

        private void setName(String name) {
            tvName.setText(name);
        }

        private void setTeacher(String teacher) {
            tvTeacher.setText(teacher);
        }

        private void setDetails(int credits, String type) {
            tvDetails.setText(credits + " créditos - " + type);
        }

        private void setCourseClickEvent(int courseId) {
            itemCourse.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context, "Abrir CourseActivity !", Toast.LENGTH_SHORT).show();
        }

//        public void setImage(String urlImage) {
//            Picasso.with(context)
//                    .load(urlImage)
//                    .placeholder(R.drawable.avatar_default)
//                    .transform(new CircleTransform())
//                    .into(ivPhoto);
//        }

    }

}