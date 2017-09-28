package com.example.yassine.tp3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

public class settingsActivity extends AppCompatActivity {

    private int size = 0;
    final static String TEXT_SIZE = "TEXT_SIZE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Intent intent = getIntent();
        size = intent.getIntExtra(MainActivity.TEXT_SIZE,1);

        switch(size) {
            case 1:
                ((RadioButton) findViewById(R.id.radioPetit)).setChecked(true);
                break;
            case 2:
                ((RadioButton) findViewById(R.id.radioMoyen)).setChecked(true);
                break;
            case 3:
                ((RadioButton) findViewById(R.id.radioGrand)).setChecked(true);
                break;
        }
    }


    @Override
    public void finish() {
        Intent data = new Intent();
        data.putExtra(TEXT_SIZE, size);
        setResult(RESULT_OK, data);
        super.finish();
    }


    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.radioPetit:
                if (checked)
                    size = 1;
                finish();
                break;
            case R.id.radioMoyen:
                if (checked)
                    size = 2;
                finish();
                break;
            case R.id.radioGrand:
                if (checked)
                    size = 3;
                finish();
                break;
        }
    }

}
