package com.androidprojects.solankinilesh.balloontest;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

public class Utility {

    public static void displayToast(Context context){
        Toast.makeText(context, "function called from common class", Toast.LENGTH_SHORT).show();
    }


    private static String readFromInputStream(InputStream inputStream) {
        StringBuilder output = new StringBuilder();

        if (inputStream != null) {
            InputStreamReader inputStream1 = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStream1);
            try {
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        return output.toString();
    }




    public static URL createUrl(String s) {
        URL url = null;
        try {
            url = new URL(s);
        } catch (MalformedURLException e) {
            Log.e("Utility", "Problem building the Url", e);
        }
        return url;
    }



    public static String makeHttpRequest(URL url) throws IOException{
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        Map<String, List<String>> headers = null;
        String response = "";
        if (url == null) {
            return null;
        }

        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode() == 200) {
                inputStream = httpURLConnection.getInputStream();

                response = readFromInputStream(inputStream);
            } else {
                Log.e("Utility", "The response code return by Server is other than 200");
            }
        } catch (IOException e) {
            Log.e("Utility", "Problem retrieving Earthquake JsonResponse", e);
        } finally {
            headers = httpURLConnection.getHeaderFields();
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return headers.values().toString();
    }

}
