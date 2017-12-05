package com.gb.comp3074_assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NurseMainActivity extends Activity implements View.OnClickListener {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_main);
        Button btn1 = findViewById(R.id.enterTestButton);
        Button btn2 = findViewById(R.id.displayTestButton);
        Button btn3 = findViewById(R.id.enterPatientButton);
        Button btn4 = findViewById(R.id.displayPatientButton);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.enterTestButton:
                intent = new Intent(getApplicationContext(), EnterTestActivity.class);
                startActivity(intent);
                break;
            case R.id.displayTestButton:
                intent = new Intent(getApplicationContext(), DisplayTestActivity.class);
                startActivity(intent);
                break;
            case R.id.enterPatientButton:
                intent = new Intent(getApplicationContext(), EnterPatientActivity.class);
                startActivity(intent);
                break;
            case R.id.displayPatientButton:
                intent = new Intent(getApplicationContext(), DisplayPatientActivity.class);
                startActivity(intent);
                break;
        }
    }

}
