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


//        int MY_PERMISSIONS_REQUEST_CAMERA=0;
//        // Here, this is the current activity
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
//        {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA))
//            {
//
//            }
//            else
//            {
//                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA );
//                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
//                // app-defined int constant. The callback method gets the
//                // result of the request.
//            }
//        }



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
