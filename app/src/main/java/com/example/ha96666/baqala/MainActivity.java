package com.example.ha96666.baqala;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Adduser(View view) {
        Intent intent = new Intent(this, ManageUser.class);
        startActivity(intent);
    }


    public void ShowDatabase(View view) {
        Intent intent = new Intent(this, ViewAllUsers.class);
        startActivity(intent);
    }

    public void showLocation(View view) {
        Intent intent = new Intent(this, CurrentLocation.class);
        startActivity(intent);
    }






}
