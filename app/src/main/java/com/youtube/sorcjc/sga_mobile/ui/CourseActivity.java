package com.youtube.sorcjc.sga_mobile.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.youtube.sorcjc.sga_mobile.R;
import com.youtube.sorcjc.sga_mobile.domain.Course;
import com.youtube.sorcjc.sga_mobile.domain.CourseNote;

public class CourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        CourseNote course = (CourseNote) getIntent().getExtras().getSerializable("NOTECOURSE");

        final TextView tvName = (TextView) findViewById(R.id.tvName);
        final TextView tvNota1 = (TextView) findViewById(R.id.Nota1);
        final TextView tvNota2 = (TextView) findViewById(R.id.Nota2);
        final TextView tvNota3 = (TextView) findViewById(R.id.Nota3);
        final TextView tvPromedio = (TextView) findViewById(R.id.PromedioFinal);
        tvName.setText(course.getName());
        tvNota1.setText(course.getNote1());
        tvNota2.setText(course.getNote2());
        tvNota3.setText(course.getNote3());
        tvPromedio.setText(course.getAverage());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
