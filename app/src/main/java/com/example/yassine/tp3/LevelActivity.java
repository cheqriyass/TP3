package com.example.yassine.tp3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LevelActivity extends AppCompatActivity {

    private TextView displayMessage;
    private TextView displayCompteur;
    private int size=0;
    private int level=0;
    private Intent settingsIntent;
    public static int compteur = 0;
    private static final String TAG = "myMessage";
    final static int SIZE_REQUEST_CODE = 3;

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

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settingsAction:
                settingsIntent = new Intent(this, settingsActivity.class);
                settingsIntent.putExtra(MainActivity.TEXT_SIZE, size);
                startActivityForResult(settingsIntent, SIZE_REQUEST_CODE);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SIZE_REQUEST_CODE && resultCode == RESULT_OK) {
            size = data.getIntExtra(MainActivity.TEXT_SIZE, 2);
            changeTextSize();
            MainActivity.size = size;
        }
    }


    @Override
    public void finish() {
        Intent data = new Intent();
        data.putExtra(MainActivity.EXTRA_LAUNCHES, compteur);
        setResult(RESULT_OK, data);
        super.finish();
    }



    public void changeTextSize(){
        int taille = MainActivity.convertSize(size);
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
