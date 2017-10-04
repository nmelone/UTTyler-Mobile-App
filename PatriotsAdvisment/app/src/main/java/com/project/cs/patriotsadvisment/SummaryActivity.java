package com.project.cs.patriotsadvisment;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.DonutProgress;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        //creating progress bars
        DonutProgress circle = (DonutProgress) findViewById(R.id.donut_progress);
        ProgressBar coreProgress = (ProgressBar)findViewById(R.id.coreProgressBar);
        ProgressBar degreeProgress = (ProgressBar)findViewById(R.id.degreeProgressBar);
        ProgressBar overallProgress = (ProgressBar)findViewById(R.id.overallProgressBar);
        //setting progress
        //TODO use data from Database
        circle.setProgress((int)(Math.random()*100));
        coreProgress.setProgress((int)(Math.random()*100));
        degreeProgress.setProgress((int)(Math.random()*100));
        overallProgress.setProgress((int)(Math.random()*100));
        //creating percent labels
        TextView corePercent = (TextView)findViewById(R.id.corePercent);
        TextView degreePercent = (TextView)findViewById(R.id.degreePercent);
        TextView overallPercent = (TextView)findViewById(R.id.overallPercent);
        //setting percent label
        corePercent.setText(coreProgress.getProgress() + "%");
        degreePercent.setText(degreeProgress.getProgress() + "%");
        overallPercent.setText(overallProgress.getProgress() + "%");
        //creating student label
        TextView student = (TextView)findViewById(R.id.student);
        TextView gpa = (TextView)findViewById(R.id.gpaText);
        double gpaNumber = Math.random()*4;
        gpaNumber = Math.round(gpaNumber*100.00);
        gpaNumber = gpaNumber/100;
        Double gpaText = gpaNumber;
        gpa.setText(gpaText.toString());
        //setting student label
        //TODO use data from Database
        student.setText("John Smith");

    }
    public void startCourses(View myView){
        Intent intent = new Intent(this,CoursesActivity.class);
        startActivity(intent);
    }
}
