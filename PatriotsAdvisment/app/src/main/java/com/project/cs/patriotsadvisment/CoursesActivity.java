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
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CoursesActivity extends Activity {

    android.widget.ExpandableListAdapter listAdapter;
    ExpandableListView coursesEL;
    List<String> listCoursesHeader;
    HashMap<String, List<String>> listCoursesChild;
    String rawQuery;

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
        //the lists for the headers and children
        listCoursesHeader = new ArrayList<String>();
        listCoursesChild = new HashMap<String, List<String>>();

        //adding all the headers to the header list
        listCoursesHeader.add("Freshman Courses");
        listCoursesHeader.add("Sophomore Courses");
        listCoursesHeader.add("Junior Courses");
        listCoursesHeader.add("Senior Courses");


        //gets all classes for a degree plan that aren't electives
        rawQuery = makeQuery("select name from course natural join rq_ln_course natural join requirement where degree_name = 'computer information systems' and (rq_code != 'rq 1161' and rq_code != 'rq 1158') and course_code like '____ 1___' order by course_code;");
        ArrayList<String> rawFreshmanArray = parseTheData(rawQuery);

        //final program will use a for loop to add all the proper data
        //TODO change all the adding to a loop that gets data from database
        List<String> freshman = new ArrayList<String>();
        for(int i = 1; i < rawFreshmanArray.size(); i+=2){
            freshman.add(rawFreshmanArray.get(i));
        }

        //gets all classes for a degree plan that aren't electives
        rawQuery = makeQuery("select name from course natural join rq_ln_course natural join requirement where degree_name = 'computer information systems' and (rq_code != 'rq 1161' and rq_code != 'rq 1158') and course_code like '____ 2___' order by course_code;");
        ArrayList<String> rawSophomoreArray = parseTheData(rawQuery);
        List<String> sophomore = new ArrayList<String>();

        for(int i = 1; i < rawSophomoreArray.size(); i+=2){
            sophomore.add(rawSophomoreArray.get(i));
        }

        //gets all classes for a degree plan that aren't electives
        rawQuery = makeQuery("select name from course natural join rq_ln_course natural join requirement where degree_name = 'computer information systems' and (rq_code != 'rq 1161' and rq_code != 'rq 1158') and course_code like '____ 3___' order by course_code;");
        ArrayList<String> rawJuniorArray = parseTheData(rawQuery);
        List<String> junior = new ArrayList<String>();
        for(int i = 1; i < rawJuniorArray.size(); i+=2){
            junior.add(rawJuniorArray.get(i));
        }

        rawQuery = makeQuery("select name from course natural join rq_ln_course natural join requirement where degree_name = 'computer information systems' and (rq_code != 'rq 1161' and rq_code != 'rq 1158') and course_code like '____ 4___' order by course_code;");
        ArrayList<String> rawSeniorArray = parseTheData(rawQuery);
        List<String> senior = new ArrayList<String>();
        for(int i = 1; i < rawSeniorArray.size(); i+=2){
            senior.add(rawSeniorArray.get(i));
        }

        //putting all the children into the right list
        listCoursesChild.put(listCoursesHeader.get(0), freshman);
        listCoursesChild.put(listCoursesHeader.get(1), sophomore);
        listCoursesChild.put(listCoursesHeader.get(2), junior);
        listCoursesChild.put(listCoursesHeader.get(3), senior);
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
