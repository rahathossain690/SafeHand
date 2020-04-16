package com.example.safehand;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
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

    public PictureResult excess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CameraView camera = findViewById(R.id.camera);
        camera.setLifecycleOwner(this);

        camera.setFacing(Facing.BACK);
        camera.setMode(Mode.PICTURE);
        camera.setFlash(Flash.AUTO);
        camera.setPlaySounds(true);

        camera.addCameraListener(new CameraListener() {
            @Override
            public void onPictureTaken(PictureResult result) {
                excess = result;
                Toast.makeText(MainActivity.this, "Picture taken", Toast.LENGTH_SHORT).show();
                // A Picture was taken!
            }
        });

        ((ImageView)findViewById(R.id.capturebutton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excess = null;
                camera.takePicture();
                //Toast.makeText(MainActivity.this, "CLICKED", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
