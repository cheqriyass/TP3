package com.example.yassine.tp3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private int score = 0;
    private int level = 1;
    private TextView displayScore;
    private TextView displayLevel;
    private TextView lnumberV;
    final static String EXTRA_LEVEL = "EXTRA_LEVEL";
    final static String EXTRA_LAUNCHES = "NUMBER_OF_LAUNCHES_REQUEST";
    final static String TEXT_SIZE = "TEXT_SIZE";
    private Intent messageIntent;
    private Intent settingsIntent;
    private Intent recyclerIntent;
    private int NUMBER_OF_LAUNCHES_REQUEST_CODE=0;
    private int SETTINGS_REQUEST_CODE =2;
    public static int size = 2;
    private int launches = 0;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnAdd = (Button) findViewById(R.id.btnAjouter);
        Button btnReset = (Button) findViewById(R.id.btnReset);
        Button btnRecycler = (Button) findViewById(R.id.btnRecycler);

        displayScore = (TextView) findViewById(R.id.displayScore);
        displayLevel = (TextView) findViewById(R.id.displayLevel);
        lnumberV = (TextView) findViewById(R.id.lnumberV);
        changeTextSize();
        messageIntent = new Intent(this, LevelActivity.class);
        recyclerIntent = new Intent(this, recyclerTest.class);
        updateDisplay();
        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (++score==5){
                    score=0;
                    level++;
                    messageIntent.putExtra(EXTRA_LEVEL, level);
                    messageIntent.putExtra(TEXT_SIZE, size);
                    startActivityForResult(messageIntent, NUMBER_OF_LAUNCHES_REQUEST_CODE);
                    updateDisplay();
                }
                updateDisplay();

            }
        });


        btnRecycler.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(recyclerIntent);
            }
        });


        btnReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                rest();
                updateDisplay();
            }
        });
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settingsAction:
                settingsIntent = new Intent(this, settingsActivity.class);
                settingsIntent.putExtra(TEXT_SIZE, size);
                startActivityForResult(settingsIntent, SETTINGS_REQUEST_CODE);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == NUMBER_OF_LAUNCHES_REQUEST_CODE && resultCode == RESULT_OK) {
            launches = data.getIntExtra(EXTRA_LAUNCHES, 0);
            updateDisplay();
        }
        if (requestCode == SETTINGS_REQUEST_CODE && resultCode == RESULT_OK) {
            size = data.getIntExtra(TEXT_SIZE, 2);
            updateDisplay();
        }
    }
    static public int convertSize(int size){
        switch(size){
            case 1:
                return 12;
            case 2:
                return 20;
            case 3:
                return 30;
            default:
                return 20;
        }
    }

    public void changeTextSize(){
        int taille = convertSize(size);
        displayScore.setTextSize(taille);
        displayLevel.setTextSize(taille);
        lnumberV.setTextSize(taille);
    }

    public void rest(){
        score = 0;
        level = 1;
        LevelActivity.compteur = 0;
        launches = 0;
        updateDisplay();
    }

    public void updateDisplay(){
        changeTextSize();
        displayScore.setText("Votre score : " + score);
        displayLevel.setText("Votre niveau : " + level);
        lnumberV.setText("Nombre de fois lancé : " + launches);
    }
}
