package com.example.safehand;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.otaliastudios.cameraview.CameraUtils;
import com.otaliastudios.cameraview.FileCallback;
import com.otaliastudios.cameraview.PictureResult;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final PictureResult image = MainActivity.excess;

        Bitmap bitmap = BitmapFactory.decodeByteArray(image.getData(), 0, image.getData().length);
        ((ImageView)findViewById(R.id.preview)).setImageBitmap(bitmap);

        ((ImageView)findViewById(R.id.discard)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { discard();
            }
        });
        ((ImageView)findViewById(R.id.save)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date();
                final String filename = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath() + "/SafeHand_" + date.getTime() + "." + image.getFormat().toString().toLowerCase();
                checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, 101);
                image.toFile(new File(filename), new FileCallback() {
                    @Override
                    public void onFileReady(@Nullable File file) {
                        Toast.makeText(Main2Activity.this, "Saved at " + filename, Toast.LENGTH_SHORT).show();
                        discard();
                    }
                });
            }
        });
    }

    void discard(){
        MainActivity.excess = null;
        Intent intent = new Intent(Main2Activity.this, MainActivity.class);
        startActivity(intent);
    }

    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(Main2Activity.this, permission)
                == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(Main2Activity.this,
                    new String[] { permission },
                    requestCode);
        }
        else {
        }
    }

}
