package com.youtube.sorcjc.sga_mobile.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.youtube.sorcjc.sga_mobile.R;
import com.youtube.sorcjc.sga_mobile.domain.Course;
import com.youtube.sorcjc.sga_mobile.domain.CourseNote;
import com.youtube.sorcjc.sga_mobile.ui.CourseActivity;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MessageViewHolder> {

    private ArrayList<Course> courses;
    private ArrayList<CourseNote> coursesNotes;
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
        CourseNote courseNote = coursesNotes.get(position);

        holder.setName(course.getName());
        holder.setTeacher(course.getTeacher());
        holder.setDetails(course.getCredits(), course.getType());
        holder.setCourseClickEvent(courseNote);
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public void setAll(ArrayList<Course> courses, ArrayList<CourseNote> coursesNotes) {
        if (courses == null)
            throw new NullPointerException("The results cannot be null.");

        this.courses = courses;
        this.coursesNotes = coursesNotes;
        notifyDataSetChanged();
    }

    class MessageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Context context;
        private TextView tvName;
        private TextView tvTeacher;
        private TextView tvDetails;
        private View itemCourse;

        private CourseNote courseItem;

        MessageViewHolder(View itemView) {
            super(itemView);

            setContext(itemView.getContext());
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvTeacher = (TextView) itemView.findViewById(R.id.tvTeacher);
            tvDetails = (TextView) itemView.findViewById(R.id.tvDetails);
            itemCourse = itemView.findViewById(R.id.itemCourse);
        }

        private void setContext(Context context) {
            this.context = context;
        }

        private void setName(String name) {
            tvName.setText(name);
        }

        private void setTeacher(String teacher) {
            tvTeacher.setText(teacher);
        }

        private void setDetails(int credits, String type) {
            tvDetails.setText(credits+ " créditos - " + type);
        }

        private void setCourseClickEvent(CourseNote course) {
            this.courseItem = course;
            itemCourse.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, CourseActivity.class);
            intent.putExtra("NOTECOURSE", courseItem);
            context.startActivity(intent);
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