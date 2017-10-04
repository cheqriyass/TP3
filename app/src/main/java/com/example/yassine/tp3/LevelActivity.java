package com.example.yassine.tp3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LevelActivity extends AppCompatActivity {

    private TextView displayMessage;
    private TextView displayCompteur;
    private int size=0;
    private int level=0;
    private static int compteur = 0;
    private static final String TAG = "message";
    final static String NUMBER_OF_LAUNCHES_REQUEST = "NUMBER_OF_LAUNCHES_REQUEST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "Fonction onCreate() applée");
        setContentView(R.layout.activity_level);
        compteur++;
        Button btnBack = (Button) findViewById(R.id.btnBack);
        Intent intent = getIntent();
        level = intent.getIntExtra(MainActivity.EXTRA_LEVEL,1);
        size = intent.getIntExtra(MainActivity.TEXT_SIZE,2);

        displayMessage = (TextView) findViewById(R.id.displayMessage);
        displayCompteur = (TextView) findViewById(R.id.displayCompteur);
        changeTextSize();
        updateDisplay();

        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    public void finish() {
        Intent data = new Intent();
        data.putExtra(NUMBER_OF_LAUNCHES_REQUEST, compteur);
        setResult(RESULT_OK, data);
        super.finish();
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
        displayMessage.setTextSize(taille);
        displayCompteur.setTextSize(taille);
    }


    public void updateDisplay(){
        displayMessage.setText("Votre niveau : " + level);
        displayCompteur.setText("Nombre d'éxecution : " + compteur);
    }




    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "Fonction onStart() applée");
    }

    @Override
    protected void onStop() {
        Log.v(TAG, "Fonction onStop() applée");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.v(TAG, "Fonction onDestroy() applée");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.v(TAG, "Fonction onPause() applée");
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "Fonction onResume() applée");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v(TAG, "Fonction onRestart() applée");
    }
}
