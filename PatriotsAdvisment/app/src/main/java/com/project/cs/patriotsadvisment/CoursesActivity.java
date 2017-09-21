package com.project.cs.patriotsadvisment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CoursesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        //hashmap for the headers
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();
        //lists for child items under a header
        List<String> freshman = new ArrayList<String>();
        //final program will use a for loop to add all the proper data
        freshman.add("English 101");
        freshman.add("Math 101");
        freshman.add("History 101");
        List<String> sophmore = new ArrayList<String>();
        sophmore.add("English 201");
        sophmore.add("Math 201");
        sophmore.add("History 201");
        expandableListDetail.put("Freshman", freshman);
        expandableListDetail.put("Sophmore", sophmore);
    }
}
