package com.example.rickgz.flashlight;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    boolean flashlightOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.flashlightButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                flashlightOn = !flashlightOn;
                if(flashlightOn) {
                    //screenBrightness(255, getApplicationContext());
                }
                else {
                    //screenBrightness(255, getApplicationContext());
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
