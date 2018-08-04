package com.androidprojects.solankinilesh.balloontest;

import android.content.Context;
import android.widget.Toast;

public class Utility {

    public static void displayToast(Context context){
        Toast.makeText(context, "function called from common class", Toast.LENGTH_LONG).show();
    }

}
