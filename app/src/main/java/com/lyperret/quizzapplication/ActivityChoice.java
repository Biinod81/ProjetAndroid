package com.lyperret.quizzapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class ActivityChoice extends AppCompatActivity {

    String difficulte="Facile"; //Thème facile sélectionné de base
    String themeChoisi;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_choice);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //création de la liste déroulante pour les thèmes
        final Spinner spinner = findViewById(R.id.spinnerTheme);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.theme,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //récupération du thème sélectionné
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                themeChoisi = selectedItemText;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        //Passe à l'activité ActivityPlay
        Button BTN_Start = findViewById(R.id.BTN_Play);
        BTN_Start.setOnClickListener(new View.OnClickListener() {
            //Passe sur l'activité ActivityChoice lorsqu'on clique sur le Boutton "START"
            @Override
            public void onClick(View view) {
                EditText pseudo = findViewById(R.id.pseudo);
                if (pseudo.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(),getString(R.string.PseudoManquant),Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(ActivityChoice.this, ActivityPlay.class);
                    intent.putExtra("difficulte",difficulte);
                    intent.putExtra("theme",themeChoisi);
                    intent.putExtra("pseudo", pseudo.getText().toString());
                    startActivity(intent);
                    finish();
                }
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
            case R.id.menuRules:
                startActivity(new Intent(ActivityChoice.this,ActivityRules.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Récupération de la difficulté sélectionné
    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()){
            case R.id.radioButton_facile:
                if(checked){
                    this.difficulte = "Facile";
                }
                break;
            case R.id.radioButton_moyen:
                if(checked){
                    this.difficulte = "Moyen";
                }
                break;
            case R.id.radioButton_difficile:
                if(checked){
                    this.difficulte = "Difficile";
                }
                break;
            default:
                this.difficulte = "facile";
                break;
        }
    }

    //annule le retour arrière
    @Override
    public void onBackPressed(){}

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}


