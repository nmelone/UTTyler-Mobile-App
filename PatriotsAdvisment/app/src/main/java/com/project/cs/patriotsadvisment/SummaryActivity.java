package com.project.cs.patriotsadvisment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        //creating progress bars
        ProgressBar coreProgress = (ProgressBar)findViewById(R.id.coreProgressBar);
        ProgressBar degreeProgress = (ProgressBar)findViewById(R.id.degreeProgressBar);
        ProgressBar overallProgress = (ProgressBar)findViewById(R.id.overallProgressBar);
        //setting progress
        //TODO use data from Database
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
        //setting student label
        //TODO use data from Database
        student.setText("John Smith");

    }
}
