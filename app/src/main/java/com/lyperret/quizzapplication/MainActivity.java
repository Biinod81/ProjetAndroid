package com.lyperret.quizzapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        Button BTN_Start = findViewById(R.id.Btn_Start);
        BTN_Start.setOnClickListener(new View.OnClickListener() {
            //Passe sur l'activité ActivityChoice lorsqu'on clique sur le Boutton "START"
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ActivityChoice.class));
                finish();
            }
        });
    }
}
