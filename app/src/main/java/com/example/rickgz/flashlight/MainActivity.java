package com.example.rickgz.flashlight;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.graphics.Color;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
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

        Log.d("CREATION","app CREATED!");

        //Button that turns on the flashlight
        final Button button = (Button) findViewById(R.id.flashlightButton);
        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            public void onClick(View v) {
                //Get the current window
                window = getWindow();
                View currentView = findViewById(R.id.layout);//window.getDecorView();
                Context context = getApplicationContext();

                flashlightOn = !flashlightOn;
                if(flashlightOn) {
                    Log.d("BUTTON","buttonclick");

                    currentView.setBackgroundColor(0xFFFFFFFF);
                    currentView.invalidate();

                    if(flashAvailable(context)) {
//                        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
//                        try {
//                            for (String id : cameraManager.getCameraIdList()) {
//
//                                // Turn on the flash if camera has one
//                                cameraManager.setTorchMode(id, flashlightOn);
//                            }
//                        } catch (CameraAccessException e) {
//                            e.printStackTrace();
//                        }
                        Log.d("FLASH","flash available");
                    }
                    else {
                        sendAlertDialog("Flash unavailable", "The phone's flashlight either does not exist or cannot be accessed.");
                        Log.d("FLASH","flash unavailable");
                    }
                }
                else {
                    currentView.setBackgroundColor(0x000000);
                    currentView.invalidate();

//                    CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
//                    try {
//                        for (String id : cameraManager.getCameraIdList()) {
//
//                            // Turn off the flash if camera has one
//                            cameraManager.setTorchMode(id, flashlightOn);
//                        }
//                    } catch (CameraAccessException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        });
    }

    private boolean flashAvailable(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    private void sendAlertDialog(String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
