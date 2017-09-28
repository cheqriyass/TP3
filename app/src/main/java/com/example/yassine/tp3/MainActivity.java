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
    private int level = 0;
    private TextView displayScore;
    private TextView displayLevel;
    private TextView lnumberV;
    final static String EXTRA_LEVEL = "EXTRA_LEVEL";
    final static String EXTRA_LAUNCHES = "NUMBER_OF_LAUNCHES_REQUEST";
    final static String TEXT_SIZE = "TEXT_SIZE";
    private Intent intent;
    private int NUMBER_OF_LAUNCHES_REQUEST=0;
    private int NUMBER_OF_LAUNCHES_REQUEST2=2;
    private int size = 2;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAdd = (Button) findViewById(R.id.btnAjouter);
        Button btnReset = (Button) findViewById(R.id.btnReset);

        displayScore = (TextView) findViewById(R.id.displayScore);
        displayLevel = (TextView) findViewById(R.id.displayLevel);
        lnumberV = (TextView) findViewById(R.id.lnumberV);
        changeTextSize();
        intent = new Intent(this, LevelActivity.class);

        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (++score==5){
                    score=0;
                    level++;
                    intent.putExtra(EXTRA_LEVEL, level);
                    startActivityForResult(intent, NUMBER_OF_LAUNCHES_REQUEST);
                    updateDisplay();
                }
                updateDisplay();

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
                intent = new Intent(this, settingsActivity.class);
                intent.putExtra(TEXT_SIZE, size);
                startActivityForResult(intent, NUMBER_OF_LAUNCHES_REQUEST2);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == NUMBER_OF_LAUNCHES_REQUEST && resultCode == RESULT_OK) {
            int launches = data.getIntExtra(EXTRA_LAUNCHES, 0);
            lnumberV.setText("Nombre de fois lancé : " + launches);

        }
        if (requestCode == NUMBER_OF_LAUNCHES_REQUEST2 && resultCode == RESULT_OK) {
            size = data.getIntExtra(TEXT_SIZE, 2);
            changeTextSize();

        }
    }

    public void changeTextSize(){
        int taille = 20;
        switch(size){
            case 1:
                taille = 12;
                break;
            case 2:
                taille = 20;
                break;
            case 3:
                taille = 30;
                break;
            default:
                taille = 20;
                break;
        }
        displayScore.setTextSize(taille);
        displayLevel.setTextSize(taille);
        lnumberV.setTextSize(taille);
    }

    public void rest(){
        score = 0;
        level = 0;
    }

    public void updateDisplay(){
        displayScore.setText("votre score : " + score);
        displayLevel.setText("votre niveau : " + level);
    }
}
