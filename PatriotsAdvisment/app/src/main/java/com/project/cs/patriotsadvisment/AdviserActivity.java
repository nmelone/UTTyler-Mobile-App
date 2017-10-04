package com.project.cs.patriotsadvisment;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AdviserActivity extends AppCompatActivity {

    TextView adviserphoneTextView;
    TextView adviseremailTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advisor);
        //getting the intent data
        Intent intent = getIntent();
        //creating a textview and setting it to the adviser name
        TextView advisorName = (TextView)findViewById(R.id.advisorinfoname);
        advisorName.setText(intent.getStringExtra(SummaryActivity.EXTRA_ADVISER));

        //filling in the adviser's info
        adviseremailTextView = (TextView)findViewById(R.id.advisoremailTextView);
        adviseremailTextView.setText("kpleasant@uttyler.edu");
        adviserphoneTextView = (TextView)findViewById(R.id.advisorphoneTextView);
        adviserphoneTextView.setText("9035555555");
    }
    //used to start an email to an Adviser
    public void sendEmail(View myView){
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{adviseremailTextView.getText().toString()});
        startActivity(Intent.createChooser(emailIntent, "EMAIL"));
    }
    //used to start a call to an Adviser
    public void callPhone(View myView){
        Intent phoneIntent = new Intent(android.content.Intent.ACTION_DIAL);
        phoneIntent.setData(Uri.parse("tel:"+ adviserphoneTextView.getText().toString()));
        startActivity(phoneIntent);
    }
}
