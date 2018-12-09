package com.example.ha96666.baqala;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Shop extends Activity {

    private EditText etOrder;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        etOrder = (EditText) findViewById(R.id.etOrder);
        tvResult = (TextView) findViewById(R.id.tvResult);
    }

    public void showLocation(View view) {
        Intent intent = new Intent(this, CurrentLocation.class);
        startActivity(intent);
    }

    public void doOrder(View view){

        String strItem = etOrder.getText().toString();

        if(strItem.isEmpty()) {
            Toast.makeText(this, "Please fill the blanks", Toast.LENGTH_SHORT).show();
            return;
        }

        tvResult.setText("You have ordered " + strItem);

        //Intent intent = new Intent(this, payment.class);
        //startActivity(intent);

    }

    public void doCLEAR(View view){


        etOrder.setText("");
        tvResult.setText("");

    }






}
