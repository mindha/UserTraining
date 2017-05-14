package com.example.mindha.usertraining.menubar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mindha.usertraining.R;

public class GoingTo extends Activity {
    private TextView mdate,mlocation,mtitle,mdescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_going_to);
        mdate = (TextView) findViewById(R.id.dateContent);
        mlocation = (TextView) findViewById(R.id.location);
        mtitle = (TextView) findViewById(R.id.titleContent);
        mdescription = (TextView) findViewById(R.id.descriptionContent);

        mdate.setText(getIntent().getStringExtra("tanggal"));
        mlocation.setText(getIntent().getStringExtra("lokasi"));
        mtitle.setText(getIntent().getStringExtra("judul"));
    }
}
