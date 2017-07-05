package com.example.rickgz.flashlight;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Color;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    boolean flashlightOn = false;

    //Variable to store brightness value
    private int brightness;
    //Content resolver used as a handle to the system's settings
    private ContentResolver Conresolver;
    //Window object, that will store a reference to the current window
    private Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.flashlightButton);

        if(savedInstanceState != null) {
            Log.d("STATE", savedInstanceState.toString());
        }

        Log.d("CREATION","app created");

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Get the current window
                window = getWindow();
                Context context = getApplicationContext();

                View currentView = (View) findViewById(android.R.id.content);

                flashlightOn = !flashlightOn;
                if(flashlightOn) {
                    //screenBrightness(255, getApplicationContext());
                    WindowManager.LayoutParams lp = window.getAttributes();
                    lp.screenBrightness = 1;
                    window.setAttributes(lp);
                    Log.d("BUTTON","buttonclick");

                }
                else {
                    //screenBrightness(255, getApplicationContext());
                    WindowManager.LayoutParams lp = window.getAttributes();
                    lp.screenBrightness = -1;
                    window.setAttributes(lp);
                }
            }
        });
    }

    void screenBrightness(int level, Context context) {
        Settings.System.putInt( context.getContentResolver(),
                                Settings.System.SCREEN_BRIGHTNESS,
                                level);
    }
}
