package com.lyperret.quizzapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityScore extends AppCompatActivity {

    TextView pseudo;
    String pseudoExtra;
    int scoreExtra;
    TextView textScore;
    Button home;
    Button quitter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_score);
        pseudoExtra = getIntent().getStringExtra("pseudo");
        pseudo = findViewById(R.id.pseudoScore);
        pseudo.setText(pseudoExtra);

        scoreExtra = getIntent().getIntExtra("score",0);
        textScore = findViewById(R.id.textScore);
        progressBar = findViewById(R.id.progressBar);

        //Barre de progression
        if(scoreExtra==3){
            textScore.setText(getString(R.string._3of3));
            progressBar.setProgress(100);
        }
        else if(scoreExtra==2){
            textScore.setText(getString(R.string._2of3));
            progressBar.setProgress(66);
        }
        else if(scoreExtra==1){
            textScore.setText(getString(R.string._1of3));
            progressBar.setProgress(33);
        }
        else{
            textScore.setText(getString(R.string._0of3));
            progressBar.setProgress(0);
        }

        //retour à ActivityChoice
        home = findViewById(R.id.BTN_Home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityScore.this, ActivityChoice.class);
                startActivity(intent);
                finish();
            }
        });

        //Quitte l'appli
        quitter = findViewById(R.id.BTN_Quitter);
        quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.quizzmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menuHome:
                startActivity(new Intent(ActivityScore.this,ActivityChoice.class));
                finish();
                return true;
            case R.id.menuRules:
                startActivity(new Intent(ActivityScore.this,ActivityRules.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {}


}
