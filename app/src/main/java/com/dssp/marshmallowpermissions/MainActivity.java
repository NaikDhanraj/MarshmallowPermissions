package com.dssp.marshmallowpermissions;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by dhanrajnaik522 on 2/5/2017.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);
    }

    public void requestSingle(View v) {

        Intent intent = new Intent(this, SinglePermission.class);
        startActivity(intent);

    }

    public void requestMultiple(View v) {
        Intent intent = new Intent(this, MultiplePermissions.class);
        startActivity(intent);
    }
}
