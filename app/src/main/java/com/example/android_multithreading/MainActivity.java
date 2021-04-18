package com.example.android_multithreading;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    Runnable bgThread = new Runnable() {

        public void run() {
            // wrap in try catch for interrupt exceptions
            try {
                // tell the thread to sleep for a second
                Thread.sleep( 1000 );
                // generate a random four digit number
                Random rand = new Random();
                int randInt = rand.nextInt( ( 9999 - 1000 ) ) + 1000;
                // write number to log
                Log.i(
                "android_multithreading",
                Thread.currentThread() + " generated " + randInt
                );
                // send message to main thread
                

            } catch ( Exception e ) {
                e.getMessage();
            }
        }
    };
}