package com.lyperret.quizzapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityChoice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_choice);

        Button BTN_Start = findViewById(R.id.BTN_Play);
        BTN_Start.setOnClickListener(new View.OnClickListener() {
            //Passe sur l'activité ActivityChoice lorsqu'on clique sur le Boutton "START"
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityChoice.this, ActivityPlay.class));
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}
