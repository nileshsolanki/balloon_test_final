package com.androidprojects.solankinilesh.balloontest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.io.IOException;
import java.net.URL;

import static com.androidprojects.solankinilesh.balloontest.Utility.createUrl;
import static com.androidprojects.solankinilesh.balloontest.Utility.makeHttpRequest;

public class MainActivity extends AppCompatActivity {

    EditText siteNameEt;
    Button submitBtn;
    ProgressBar progressBar;
    String response = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Utility.displayToast(this);
        siteNameEt = findViewById(R.id.et_website_name);
        submitBtn = findViewById(R.id.btn_submit);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setIndeterminate(true);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String mUrl = siteNameEt.getText().toString();
                new NetworkAsync().execute(mUrl);

            }
        });
    }




    private class NetworkAsync extends AsyncTask<String, Integer, String>{

        @Override
        protected String doInBackground(String... strings) {
            URL url = createUrl(strings[0]);
            String response = null;

            try {
                 Thread.sleep(1500);
                 response = makeHttpRequest(url);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.INVISIBLE);
            response = s;
            Log.d("MainActivity", "response: "+response);
            Intent intent = new Intent(getApplicationContext(), HeadersActivity.class);
            intent.putExtra("response_head",response);
            startActivity(intent);
        }
    }


}