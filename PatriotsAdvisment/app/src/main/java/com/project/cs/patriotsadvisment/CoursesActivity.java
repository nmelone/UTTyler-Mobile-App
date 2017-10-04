package com.project.cs.patriotsadvisment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CoursesActivity extends Activity {

    android.widget.ExpandableListAdapter listAdapter;
    ExpandableListView coursesEL;
    List<String> listCoursesHeader;
    HashMap<String, List<String>> listCoursesChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        //intent used to grab data from login
        Intent intent = getIntent();

        //get Expandable List View
        coursesEL = (ExpandableListView)findViewById(R.id.coursesListView);
        //prep the data
        prepareListData();
        //making a expandable list
        listAdapter = new ELAdapter(this, listCoursesHeader, listCoursesChild);
        //setting list adapter
        coursesEL.setAdapter(listAdapter);

    }

    private void prepareListData(){
        listCoursesHeader = new ArrayList<String>();
        listCoursesChild = new HashMap<String, List<String>>();

        listCoursesHeader.add("Freshman Courses");
        listCoursesHeader.add("Sophmore Courses");
        listCoursesHeader.add("Junior Courses");
        listCoursesHeader.add("Senior Courses");

        //final program will use a for loop to add all the proper data
        List<String> freshman = new ArrayList<String>();
        freshman.add("English 101");
        freshman.add("Math 101");
        freshman.add("History 101");

        List<String> sophmore = new ArrayList<String>();
        sophmore.add("English 201");
        sophmore.add("Math 201");
        sophmore.add("History 201");

        List<String> junior = new ArrayList<String>();
        junior.add("Physics 301");
        junior.add("Calculus 301");
        junior.add("Long Division 12");

        List<String> senior = new ArrayList<String>();
        senior.add("Slacking off 101");
        senior.add("Graduating LOL");
        senior.add("Fuckitall.exe");
        listCoursesChild.put(listCoursesHeader.get(0), freshman);
        listCoursesChild.put(listCoursesHeader.get(1), sophmore);
        listCoursesChild.put(listCoursesHeader.get(2), junior);
        listCoursesChild.put(listCoursesHeader.get(3), senior);
    }
}
