package com.gb.comp3074_assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gb.comp3074_assignment2.R;

public class DoctorMainActivity extends Activity implements View.OnClickListener {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main);
        Button btn1 = findViewById(R.id.enterTestButton);
        Button btn2 = findViewById(R.id.displayTestButton);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.enterTestButton:
                intent = new Intent(getApplicationContext(),PatientTestSelection.class);
                intent.putExtra("controller", "doctor");
                startActivity(intent);
                break;
            case R.id.displayTestButton:
                intent = new Intent(getApplicationContext(), DisplayTestActivity.class);
                startActivity(intent);
                break;
        }
    }
}
