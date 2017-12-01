package com.project.cs.patriotsadvisment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.DonutProgress;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SummaryActivity extends AppCompatActivity{

    private TextView adviserTextView;

    public static final String EXTRA_ADVISER = "com.project.cs.patriotsadvisment.ADVISER";
    String rawQuery;
    String stuName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        String email = getIntent().getExtras().getString("email");

        DBconn myconn = new DBconn(this);

        try {
            rawQuery = myconn.execute("query","select * from student where email = '"+ email + "';").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        ArrayList<String> studentarry = parseTheData(rawQuery);
        stuName = studentarry.get(studentarry.indexOf("fname")+1) + " " + studentarry.get(studentarry.indexOf("lname")+1);

        try {
            myconn = new DBconn(this);

            rawQuery = myconn.execute("query","select * from course;").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ArrayList<String> course = parseTheData(rawQuery);

        Toast.makeText(SummaryActivity.this, course.get(course.indexOf("course_code")+1),Toast.LENGTH_LONG).show();
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
        student.setText(stuName);
        adviserTextView.setText("Kay Pleasant");

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


    public ArrayList<String> parseTheData(String inputString){
        ArrayList<String> matches = new ArrayList();
        ArrayList<String> OMatches = new ArrayList();
        Pattern pattern = Pattern.compile("(([\\w\\d\\s&@.:\\/-]+)(,){0,1}([\\w\\d\\s&@.]+))");
        Pattern pattern2 = Pattern.compile("[{\"][\\ -z]+[\"}]");
        Matcher matcher = pattern2.matcher(inputString);
        while(matcher.find()) {
            OMatches.add(matcher.group());
        }
        for(String thisString : OMatches) {
            matcher = pattern.matcher(thisString);
            while (matcher.find()) {
                    matches.add(matcher.group());
            }
        }
        return matches;
    }
}
