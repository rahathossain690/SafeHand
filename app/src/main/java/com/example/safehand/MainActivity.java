package com.example.safehand;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.PictureResult;
import com.otaliastudios.cameraview.VideoResult;
import com.otaliastudios.cameraview.controls.Facing;
import com.otaliastudios.cameraview.controls.Flash;
import com.otaliastudios.cameraview.controls.Mode;

public class MainActivity extends AppCompatActivity {

    public static PictureResult excess;
    public int currentToShowPic = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CameraView camera = findViewById(R.id.camera);
        camera.setLifecycleOwner(this);

        Toast.makeText(MainActivity.this, "Click the white to change overlay", Toast.LENGTH_SHORT).show();

        camera.setFacing(Facing.BACK);
        camera.setMode(Mode.PICTURE);
        camera.setFlash(Flash.AUTO);
        camera.setPlaySounds(true);

        camera.addCameraListener(new CameraListener() {
            @Override
            public void onPictureTaken(PictureResult result) {
                excess = result;
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

        if(currentToShowPic == 0){
            ((ImageView)findViewById(R.id.overlay)).setImageResource(R.drawable.overlay_staying_home);
        } else if(currentToShowPic == 1){
            ((ImageView)findViewById(R.id.overlay)).setImageResource(R.drawable.overlay_washing_hands);
        } else{
            ((ImageView)findViewById(R.id.overlay)).setImageResource(R.drawable.overlay_social_distance);
        }

        findViewById(R.id.sada).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentToShowPic == 0){
                    currentToShowPic = 1;
                    ((ImageView)findViewById(R.id.overlay)).setImageResource(R.drawable.overlay_washing_hands);
                }
                else if(currentToShowPic == 1){
                    currentToShowPic = 2;
                    ((ImageView)findViewById(R.id.overlay)).setImageResource(R.drawable.overlay_social_distance);
                } else{
                    currentToShowPic = 0;
                    ((ImageView)findViewById(R.id.overlay)).setImageResource(R.drawable.overlay_staying_home);
                }
            }
        });

        ((ImageView)findViewById(R.id.capturebutton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excess = null;
                currentToShowPic = (currentToShowPic + 2) % 3;
                camera.takePictureSnapshot();
                //Toast.makeText(MainActivity.this, "CLICKED", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
