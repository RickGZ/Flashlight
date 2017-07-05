package com.example.rickgz.flashlight;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    boolean flashlightOn = false;
    private Camera camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        camera = openCamera(camera);

        //Button that turns on the flashlight
        final Button button = (Button) findViewById(R.id.flashlightButton);
        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            public void onClick(View v) {
                Log.d("BUTTON","buttonclick");
                Context context = getApplicationContext();

                flashlightOn = !flashlightOn;
                if(flashlightOn) {
                    if(flashAvailable(context)) {
                        Log.d("FLASH","flash available");
                        turnOnFlashlight(camera);
                    }
                    else {
                        sendAlertDialog("Flash unavailable", "The phone's flashlight either does not exist or cannot be accessed by the app.");
                        Log.d("FLASH","flash unavailable");
                    }
                }
                else {
                    if(flashAvailable(context)) {
                        turnOffFlashlight(camera);
                    }
                    else {
                        sendAlertDialog("Flash unavailable", "The phone's flashlight either does not exist or cannot be accessed by the app.");
                        Log.d("FLASH","flash unavailable");
                    }
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

    private Camera openCamera(Camera camera) {
        if (camera != null) {
            camera.release();
            camera = null;
        }
        camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
        return camera;
    }

    private void turnOnFlashlight(Camera camera) {
        Camera.Parameters parameters = camera.getParameters();
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        camera.setParameters(parameters);
        camera.startPreview();
    }

    private void turnOffFlashlight(Camera camera) {
        Camera.Parameters parameters = camera.getParameters();
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        camera.setParameters(parameters);
        camera.stopPreview();
    }
}
