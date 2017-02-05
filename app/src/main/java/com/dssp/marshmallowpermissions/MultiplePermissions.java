package com.dssp.marshmallowpermissions;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by dhanrajnaik522 on 2/5/2017.
 */

public class MultiplePermissions extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_MULTIPLE = 201;
    private static final String TAG = MultiplePermissions.class.getSimpleName();
    private TextView successTV;
    //permission grant result
    boolean cameraPermission = false;
    boolean phoneStatePermission = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        successTV = (TextView) findViewById(R.id.tv_success);
    }

    public void readExternalStorage(View v) {

        if (areAllPermissionsAllowed()) {
            Toast.makeText(this, "You Already have all the permissions granted", Toast.LENGTH_SHORT).show();
            successTV.setVisibility(View.VISIBLE);
            openCamera();
            return;
        }

        //if permissions are  not granted
        requestAllPermissions();
    }

    private void requestAllPermissions() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            /*
            here you can explain why this permission/s are needed
             */
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Permissions");

            String msg = "All the Permissions are mandatory to proceed in the app";
            if (!cameraPermission && !phoneStatePermission) {
                msg = "All the Permissions are mandatory to proceed in the app";
            } else if (!cameraPermission && phoneStatePermission) {
                msg = "Please accept camera permission too";
            } else if (cameraPermission && !phoneStatePermission) {
                msg = "Please accept read phone state permission too";
            }

            builder.setMessage(msg);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //and finally ask for the permission
                    ActivityCompat.requestPermissions(MultiplePermissions.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE}
                            , PERMISSION_REQUEST_MULTIPLE);
                    dialog.dismiss();

                }
            });
            builder.setNegativeButton("Cancel", null);
            builder.show();

        }

   /*     ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE}
                , PERMISSION_REQUEST_MULTIPLE);*/
    }

    private boolean areAllPermissionsAllowed() {

        int cameraPermissionResult = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int phoneStatePermissionResult = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

        if (cameraPermissionResult == PackageManager.PERMISSION_GRANTED
                && phoneStatePermissionResult == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        //if the permission is not granted tehn return false
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSION_REQUEST_MULTIPLE:
                if (grantResults.length > 0) {

                    cameraPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    phoneStatePermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (cameraPermission && phoneStatePermission) {

                        successTV.setVisibility(View.VISIBLE);

                        Toast.makeText(this, "All Permissions granted", Toast.LENGTH_SHORT).show();
                        /**
                         * now here you add your actual function to do further application flow
                         */
                        openCamera();

                    } else {

                        if (!cameraPermission && !phoneStatePermission) {
                            Toast.makeText(this, "Both Permissions denied", Toast.LENGTH_SHORT).show();
                        } else if (!cameraPermission) {
                            Toast.makeText(this, "Camera Permission denied", Toast.LENGTH_SHORT).show();
                        } else if (!phoneStatePermission) {
                            Toast.makeText(this, "Reading Phone state Permission denied", Toast.LENGTH_SHORT).show();
                        }


                    }

                } else {
                    Toast.makeText(this, "lol", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void openCamera() {
        Log.d(TAG, "openCamera: camera is opening");
    }
}
