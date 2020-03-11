package com.lyperret.quizzapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.nio.charset.Charset;

public class ActivityChoice extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_choice);

        //création de la liste déroulante
        Spinner spinner = (Spinner) findViewById(R.id.spinnerTheme);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.theme,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        //Passe à l'activité ActivityPlay
        Button BTN_Start = findViewById(R.id.BTN_Play);
        BTN_Start.setOnClickListener(new View.OnClickListener() {
            //Passe sur l'activité ActivityChoice lorsqu'on clique sur le Boutton "START"
            @Override
            public void onClick(View view) {
                EditText pseudo = findViewById(R.id.pseudo);
                if(pseudo.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Veuillez entrer un pseudo.",Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(new Intent(ActivityChoice.this, ActivityPlay.class));
                }

            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}
