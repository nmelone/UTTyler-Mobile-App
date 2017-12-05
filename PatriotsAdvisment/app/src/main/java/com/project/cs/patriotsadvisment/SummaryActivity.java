package com.project.cs.patriotsadvisment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.DonutProgress;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SummaryActivity extends AppCompatActivity{

    String rawQuery;
    String stuName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        String email = getIntent().getExtras().getString("email");
        rawQuery = makeQuery("select * from student where email = '"+ email + "';");

        ArrayList<String> studentarry = parseTheData(rawQuery);
        stuName = studentarry.get(studentarry.indexOf("fname")+1) + " " + studentarry.get(studentarry.indexOf("lname")+1);

        //gets credits for classes being taken currently
        rawQuery = makeQuery("select unit from student natural join stu_enroll natural join course natural join rq_ln_course natural join requirement where email = '"+ email + "' and (grade = 'null' or grade = '');");
        Double taking = 0.0;
        if(rawQuery != null) {
            ArrayList<String> classesTaking = parseTheData(rawQuery);

            for (int i = 1; i < classesTaking.size(); i += 2) {
                taking += Double.parseDouble(classesTaking.get(i));
            }
        }

        //gets credits for classes enrolled
        rawQuery = makeQuery("select unit from student natural join stu_enroll natural join course natural join rq_ln_course natural join requirement where email = '"+ email + "';");
        Double taken = 0.0;
        if(rawQuery != null) {
            ArrayList<String> classesTaken = parseTheData(rawQuery);

            for (int i = 1; i < classesTaken.size(); i += 2) {
                taken += Double.parseDouble(classesTaken.get(i));
            }
        }

        //gets credits for classes transferred
        rawQuery = makeQuery("select unit from student natural join trans_stu natural join trans_course natural join rq_ln_transc natural join requirement where email = '"+ email + "';");
        Double trans = 0.0;
        if(rawQuery != null) {
            ArrayList<String> classesTrans = parseTheData(rawQuery);

            for (int i = 1; i < classesTrans.size(); i += 2) {
                trans += Double.parseDouble(classesTrans.get(i));
            }
        }

        // gets credits for classes taken that are labelled core
        rawQuery = makeQuery("select unit from student natural join stu_enroll natural join course natural join rq_ln_course natural join requirement where email = '"+ email + "' and rq_code = 'rq 1159';");
        Double coreTaken = 0.0;
        if(rawQuery != null) {
            ArrayList<String> coreTakenArray = parseTheData(rawQuery);

            for (int i = 1; i < coreTakenArray.size(); i += 2) {
                coreTaken += Double.parseDouble(coreTakenArray.get(i));
            }
        }
        // gets credits for trans classes taken that are labelled core
        rawQuery = makeQuery("select unit from trans_stu natural join trans_course natural join rq_ln_transc natural join requirement where email = '"+ email + "' and rq_code = 'rq 1159';");
        Double coreTransTaken = 0.0;
        if(rawQuery != null) {
            ArrayList<String> coreTransTakenArray = parseTheData(rawQuery);

            for (int i = 1; i < coreTransTakenArray.size(); i += 2) {
                coreTaken += Double.parseDouble(coreTransTakenArray.get(i));
            }
        }
        //gets credits for classes labelled core
        rawQuery = makeQuery("select unit from course natural join rq_ln_course natural join requirement where rq_code = 'rq 1159';");
        Double coreTotal = 0.0;
        if(rawQuery != null){
            ArrayList<String> coreTotalArray = parseTheData(rawQuery);
            for (int i = 1; i < coreTotalArray.size(); i += 2) {
                coreTotal += Double.parseDouble(coreTotalArray.get(i));
            }
        }

        // gets credits for classes taken that are labelled lower
        rawQuery = makeQuery("select unit from student natural join stu_enroll natural join course natural join rq_ln_course natural join requirement where email = '"+ email + "' and rq_code = 'rq 1156';");
        Double lowerTaken = 0.0;
        if(rawQuery != null) {
            ArrayList<String> lowerTakenArray = parseTheData(rawQuery);

            for (int i = 1; i < lowerTakenArray.size(); i += 2) {
                lowerTaken += Double.parseDouble(lowerTakenArray.get(i));
            }
        }
        // gets credits for trans classes taken that are labelled lower
        rawQuery = makeQuery("select unit from trans_stu natural join trans_course natural join rq_ln_transc natural join requirement where email = '"+ email + "' and rq_code = 'rq 1156';");
        Double lowerTransTaken = 0.0;
        if(rawQuery != null) {
            ArrayList<String> lowerTransTakenArray = parseTheData(rawQuery);

            for (int i = 1; i < lowerTransTakenArray.size(); i += 2) {
                coreTaken += Double.parseDouble(lowerTransTakenArray.get(i));
            }
        }
        //gets credits for classes labelled lower
        rawQuery = makeQuery("select unit from course natural join rq_ln_course natural join requirement where rq_code = 'rq 1156';");
        Double lowerTotal = 0.0;
        if(rawQuery != null){
            ArrayList<String> lowerTotalArray = parseTheData(rawQuery);
            for (int i = 1; i < lowerTotalArray.size(); i += 2) {
                coreTotal += Double.parseDouble(lowerTotalArray.get(i));
            }
        }

        // gets credits for classes taken that are labelled upper
        rawQuery = makeQuery("select unit from student natural join stu_enroll natural join course natural join rq_ln_course natural join requirement where email = '"+ email + "' and rq_code = 'rq 1157';");
        Double upperTaken = 0.0;
        if(rawQuery != null) {
            ArrayList<String> upperTakenArray = parseTheData(rawQuery);

            for (int i = 1; i < upperTakenArray.size(); i += 2) {
                upperTaken += Double.parseDouble(upperTakenArray.get(i));
            }
        }
        // gets credits for trans classes taken that are labelled lupper
        rawQuery = makeQuery("select unit from trans_stu natural join trans_course natural join rq_ln_transc natural join requirement where email = '"+ email + "' and rq_code = 'rq 1157';");
        Double upperTransTaken = 0.0;
        if(rawQuery != null) {
            ArrayList<String> upperTransTakenArray = parseTheData(rawQuery);

            for (int i = 1; i < upperTransTakenArray.size(); i += 2) {
                upperTaken += Double.parseDouble(upperTransTakenArray.get(i));
            }
        }
        //gets credits for classes labelled upper
        rawQuery = makeQuery("select unit from course natural join rq_ln_course natural join requirement where rq_code = 'rq 1157';");
        Double upperTotal = 0.0;
        if(rawQuery != null){
            ArrayList<String> upperTotalArray = parseTheData(rawQuery);
            for (int i = 1; i < upperTotalArray.size(); i += 2) {
                upperTotal += Double.parseDouble(upperTotalArray.get(i));
            }
        }


        //creating progress bars
        DonutProgress graduationProgress = (DonutProgress) findViewById(R.id.donut_progress);
        ProgressBar coreProgress = (ProgressBar)findViewById(R.id.coreProgressBar);
        ProgressBar upperProgress = (ProgressBar)findViewById(R.id.upperProgressBar);
        ProgressBar lowerProgress = (ProgressBar)findViewById(R.id.lowerProgressBar);

        //creating percent labels
        TextView corePercent = (TextView)findViewById(R.id.corePercent);
        TextView degreePercent = (TextView)findViewById(R.id.upperPercent);
        TextView overallPercent = (TextView)findViewById(R.id.lowerPercent);

        //creating student, advisor, and gpa textviews
        TextView student = (TextView)findViewById(R.id.student);


        //setting progress
        //TODO use data from Database
        graduationProgress.setProgress((int)(((taken - taking) / 120)*100));
        coreProgress.setProgress((int)(((coreTaken + coreTransTaken)/coreTotal)*100));
        upperProgress.setProgress((int)(((upperTaken + upperTransTaken)/upperTotal)*100));
        lowerProgress.setProgress((int)(((lowerTaken + lowerTransTaken)/lowerTotal)*100));

        //setting percent label
        corePercent.setText(coreProgress.getProgress() + "%");
        degreePercent.setText(upperProgress.getProgress() + "%");
        overallPercent.setText(lowerProgress.getProgress() + "%");


        //TODO use data from Database
        //student and advisor info
        student.setText(stuName);


    }
    //used to call the Courses Screen
    public void startCourses(View myView){
        //TODO add any data to the intent as needed
        Intent intent = new Intent(this,CoursesActivity.class);
        startActivity(intent);
    }



    //used to get data from a raw query return
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
    //used to make a query and get a return
    private String makeQuery(String s){
        String output = "";
        try {
            DBconn myconn = new DBconn(this);

            output = myconn.execute("query",s).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return output;
    }
}
