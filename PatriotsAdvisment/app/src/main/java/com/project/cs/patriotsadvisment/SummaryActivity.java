package com.project.cs.patriotsadvisment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.DonutProgress;

public class SummaryActivity extends AppCompatActivity implements AsyncResponse{

    private TextView adviserTextView;
    public static final String EXTRA_ADVISER = "com.project.cs.patriotsadvisment.ADVISER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        //creating progress bars
        DonutProgress graduationProgress = (DonutProgress) findViewById(R.id.donut_progress);
        ProgressBar coreProgress = (ProgressBar)findViewById(R.id.coreProgressBar);
        ProgressBar degreeProgress = (ProgressBar)findViewById(R.id.degreeProgressBar);
        ProgressBar overallProgress = (ProgressBar)findViewById(R.id.overallProgressBar);

        //creating percent labels
        TextView corePercent = (TextView)findViewById(R.id.corePercent);
        TextView degreePercent = (TextView)findViewById(R.id.degreePercent);
        TextView overallPercent = (TextView)findViewById(R.id.overallPercent);

        //creating student, advisor, and gpa textviews
        TextView student = (TextView)findViewById(R.id.student);
        TextView gpa = (TextView)findViewById(R.id.gpaText);
        adviserTextView = (TextView)findViewById(R.id.advisernameTextView);

        //setting progress
        //TODO use data from Database
        graduationProgress.setProgress((int)(Math.random()*100));
        coreProgress.setProgress((int)(Math.random()*100));
        degreeProgress.setProgress((int)(Math.random()*100));
        overallProgress.setProgress((int)(Math.random()*100));

        //setting percent label
        corePercent.setText(coreProgress.getProgress() + "%");
        degreePercent.setText(degreeProgress.getProgress() + "%");
        overallPercent.setText(overallProgress.getProgress() + "%");

        //dirty way to get two decimal places likely to be removed
        double gpaNumber = Math.random()*4;
        gpaNumber = Math.round(gpaNumber*100.00);
        gpaNumber = gpaNumber/100;
        Double gpaText = gpaNumber;
        gpa.setText(gpaText.toString());

        //TODO use data from Database
        //student and advisor info
        student.setText("John Smith");
        adviserTextView.setText("Kay Pleasant");




        DBconn myconn = new DBconn(this);
        myconn.delegate = SummaryActivity.this;
        myconn.execute("query","desc student;");


    }
    //used to call the Courses Screen
    public void startCourses(View myView){
        //TODO add any data to the intent as needed
        Intent intent = new Intent(this,CoursesActivity.class);
        startActivity(intent);
    }

    //used to call the Adviser Screen
    public void startAdvisor(View myView){
        //TODO add any data to the intent as needed
        Intent intent = new Intent(this,AdviserActivity.class);
        String advisor = adviserTextView.getText().toString();
        intent.putExtra(EXTRA_ADVISER, advisor);
        startActivity(intent);
    }

    @Override
    public void processFinish(String output) {
        //TODO Do something with the string from the database
        Toast.makeText(this, output,Toast.LENGTH_LONG).show();
    }
}
