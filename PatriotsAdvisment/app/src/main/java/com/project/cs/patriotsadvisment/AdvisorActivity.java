package com.project.cs.patriotsadvisment;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AdvisorActivity extends AppCompatActivity {

    TextView advisorphoneTextView;
    TextView advisoremailTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advisor);
        Intent intent = getIntent();
        TextView advisorName = (TextView)findViewById(R.id.advisorinfoname);
        advisorName.setText(intent.getStringExtra(SummaryActivity.EXTRA_ADVISOR));
        advisoremailTextView = (TextView)findViewById(R.id.advisoremailTextView);
        advisoremailTextView.setText("kpleasant@uttyler.edu");
        advisorphoneTextView = (TextView)findViewById(R.id.advisorphoneTextView);
        advisorphoneTextView.setText("9035555555");
    }

    public void sendEmail(View myView){
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{advisoremailTextView.getText().toString()});
        startActivity(Intent.createChooser(emailIntent, "EMAIL"));
    }

    public void callPhone(View myView){
        Intent phoneIntent = new Intent(android.content.Intent.ACTION_DIAL);
        phoneIntent.setData(Uri.parse("tel:"+advisorphoneTextView.getText().toString()));
        startActivity(phoneIntent);
    }
}
