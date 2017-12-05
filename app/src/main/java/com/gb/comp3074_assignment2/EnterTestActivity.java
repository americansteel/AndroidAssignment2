package com.gb.comp3074_assignment2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.gb.comp3074_assignment2.classes.Test;

public class EnterTestActivity extends Activity {

    Test test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_test);
    }

    public void onClickBtn(View v) {
        EditText testId = findViewById(R.id.testIdEdit);
        EditText patientId = findViewById(R.id.patientIdEdit);
        EditText bpl = findViewById(R.id.bplEdit);
        EditText bph = findViewById(R.id.bphEdit);
        EditText temperature = findViewById(R.id.temperatureEdit);
    }
}
