package com.androidprojects.solankinilesh.balloontest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class HeadersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headers);

        Intent intent = getIntent();
        String response = intent.getStringExtra("response_head");

        TextView responseTextView = findViewById(R.id.tv_response);
        responseTextView.setText(response);

    }
}
