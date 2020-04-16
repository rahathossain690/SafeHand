package com.example.safehand;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.PictureResult;
import com.otaliastudios.cameraview.VideoResult;
import com.otaliastudios.cameraview.controls.Facing;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CameraView camera = findViewById(R.id.camera);
        camera.setLifecycleOwner(this);

        camera.setFacing(Facing.FRONT);

        camera.addCameraListener(new CameraListener() {
            @Override
            public void onPictureTaken(PictureResult result) {
                Toast.makeText(MainActivity.this, "Picture taken", Toast.LENGTH_SHORT).show();
                // A Picture was taken!
            }

            @Override
            public void onVideoTaken(VideoResult result) {
                Toast.makeText(MainActivity.this, "Video taken", Toast.LENGTH_SHORT).show();
                // A Video was taken!
            }

            // And much more
        });
    }
}
