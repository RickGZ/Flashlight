package com.example.rickgz.flashlight;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
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

        if(savedInstanceState != null) {
            Log.d("STATE", savedInstanceState.toString());
        }

        Log.d("CREATION","app created");

        //Button that turns on the flashlight
        final Button button = (Button) findViewById(R.id.flashlightButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Get the current window
                window = getWindow();
                View currentView = findViewById(R.id.layout);//window.getDecorView();

                flashlightOn = !flashlightOn;
                if(flashlightOn) {
                    Log.d("BUTTON","buttonclick");

                    currentView.setBackgroundColor(0xFFFFFFFF);
                    currentView.invalidate();

                    if(flashAvailable) {
                        //TODO: turn on flash
                    }
                }
                else {
                    currentView.setBackgroundColor(0x000000);
                    currentView.invalidate();
                }
            }
        });
    }

    void screenBrightness(int level, Context context) {
        Settings.System.putInt( context.getContentResolver(),
                                Settings.System.SCREEN_BRIGHTNESS,
                                level);
    }

    private boolean flashAvailable(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }
}
