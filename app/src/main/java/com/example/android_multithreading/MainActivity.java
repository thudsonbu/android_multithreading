package com.example.android_multithreading;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Handler mHandler;
    private ProgressBar mProgressBar;
    private TextView display;
    private Thread thread1;
    private Thread thread2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandler = new Handler();

        mProgressBar = findViewById( R.id.progressBar );
        display = findViewById( R.id.numberDisplay );

        startThreads();
    }

    public void startThreads() {
        // create threads
        thread1 = new Thread( bgThread );
        thread2 = new Thread( bgThread );

        // name threads
        thread1.setName("thread1");
        thread2.setName("thread2");

        // start threads
        thread1.start();
        thread2.start();
    }

    public void checkMagicNumber( int num ) {
        boolean magic = false;

        // check if magic number
        if ( num % 7 == 0 ) {
            magic = true;
        } else if ( ( num % 4 == 0 ) && ( num - 2 % 10 == 0 ) ) {
            magic = true;
        }

        if ( magic ) {
            System.out.println( "FOUND MAGIC NUMBER" );

            display.setText( "Found magic number " + num );

            // do something that ends the threads
        }
    }

    Runnable bgThread = new Runnable() {

        public void run() {

            while( true ) {
                // wrap in try catch for interrupt exceptions
                try {
                    // tell the thread to sleep for a second
                    Thread.sleep(1000);

                    // generate a random four digit number
                    Random rand = new Random();
                    int randInt = rand.nextInt((9999 - 1000)) + 1000;

                    // write number to log
                    Log.i(
                    "android_multithreading",
                    Thread.currentThread() + " generated " + randInt
                    );

                    // send message to main thread
                    mHandler.post( new Runnable() {
                        @Override
                        public void run() {
                            checkMagicNumber( randInt );
                        }
                    });

                } catch (Exception e) {
                    e.getMessage();
                }
            }
        }
    };
}