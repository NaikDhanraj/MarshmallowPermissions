package com.dssp.marshmallowpermissions;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SinglePermission extends AppCompatActivity {

    //permission code that will be checked in the method onRequestPermissionsResult
    private static final int PERMISSION_CODE_STORAGE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void readExternalStorage(View v){
      //  Toast.makeText(this, "jai ho", Toast.LENGTH_SHORT).show();
        //first check if the app is already having the permission
        if(isReadStorageAllowed()){

            //if permission is already having then show the toast
            Toast.makeText(this, "You already have the permission", Toast.LENGTH_SHORT).show();
            //EXIT the method with the return
            return;
        }

        //if app has not provided the permissions,then asking for the permission
        requestStoragePermission();
    }

    private void requestStoragePermission() {

        //if the user has denied the permissions previously then this block of code will run
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){
            /*
            here you can explain why this permission/s are needed
             */
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Permissions");
            builder.setMessage("Permissions are manditory to proceed in the app");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //and finally ask for the permission
                    ActivityCompat.requestPermissions(SinglePermission.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSION_CODE_STORAGE);
                    dialog.dismiss();

                }
            });
            builder.setNegativeButton("Cancel",null);
            builder.show();

        }

       /* //and finally ask for the permission
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSION_CODE_STORAGE);*/

    }

    private boolean isReadStorageAllowed() {
        //getting the permission status
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        //if the permission is granted then return true
        if(result == PackageManager.PERMISSION_GRANTED){//0 means granted
            return true;
        }
        //if the permission is not granted tehn return false
        return false;
    }

    //this method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
      //  super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //check the request code of our request
        if(requestCode==PERMISSION_CODE_STORAGE){
            //if the permission is granted
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //display a toast
                Toast.makeText(this, "Permissions granted,now you can access the external storage", Toast.LENGTH_SHORT).show();
            }else{
                //display another toast saying permission is not granted
                Toast.makeText(this, "Opps you just denied the permissions", Toast.LENGTH_SHORT).show();
            }
        }


    }
}
