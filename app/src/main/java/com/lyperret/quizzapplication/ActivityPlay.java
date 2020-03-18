package com.lyperret.quizzapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class ActivityPlay extends AppCompatActivity {

    SQLiteDataBaseHelper bdd = new SQLiteDataBaseHelper(this);
    TextView question;
    Button suivant;
    Button btnVrai;
    Button btnFaux;
    TextView explication;
    Cursor curs;
    private int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_play);
        this.insertData();
        this.i=0;

        suivant = findViewById(R.id.suivant);
        suivant.setVisibility(View.INVISIBLE);
        btnFaux = findViewById(R.id.btnFaux);
        btnVrai = findViewById(R.id.btnVrai);
        explication = findViewById(R.id.explication);
        explication.setVisibility(View.INVISIBLE);
        question = findViewById(R.id.question);


        //récupération de la difficulté et du thème choisi
        String difficulte = getIntent().getStringExtra("difficulte");
        String theme = getIntent().getStringExtra("theme");

        this.selectData(difficulte, theme);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuquitter,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menuCroix:
                startActivity(new Intent(ActivityPlay.this,ActivityChoice.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() { }

    public void selectData(final String difficulte, final String theme){
        SQLiteDatabase db = bdd.getWritableDatabase();
        String[] col = {"*"};
        String[] select={theme, difficulte};
        curs = db.query("questions", col, "theme=? and difficulte=?", select,null, null, "id ASC");


        curs.moveToFirst();
        traitementReponse(difficulte,theme);
    }

    public void traitementReponse(final String difficulte, final String theme){

        final String questionEnonce = curs.getString(curs.getColumnIndexOrThrow("enonce"));
        question.setText(questionEnonce);
        btnFaux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textFaux = btnFaux.getText().toString();
                explication.setVisibility(View.VISIBLE);
                String reponse = curs.getString(curs.getColumnIndexOrThrow("reponse"));
                if(reponse.equals("FAUX")){
                    explication.setText(getString(R.string.BravoFAUX)+"\n" + curs.getString(curs.getColumnIndexOrThrow("explication")));

                }
                else{
                    explication.setText(getString(R.string.DommageVRAI)+"\n" + curs.getString(curs.getColumnIndexOrThrow("explication")));
                }
                suivant.setVisibility(View.VISIBLE);
                btnFaux.setVisibility(View.INVISIBLE);
                btnVrai.setVisibility(View.INVISIBLE);
                curs.moveToNext();

                suivant.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(i == 2) {
                            Intent intent = new Intent(ActivityPlay.this, ActivityScore.class);
                            startActivity(intent);
                        }
                        else{
                            String questionEnonce = curs.getString(curs.getColumnIndexOrThrow("enonce"));
                            question.setText(questionEnonce);
                            explication.setText("");
                            suivant.setVisibility(View.INVISIBLE);
                            btnFaux.setVisibility(View.VISIBLE);
                            btnVrai.setVisibility(View.VISIBLE);
                            i++;
                        }
                    }
                });

            }
        });

        btnVrai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textVrai = btnVrai.getText().toString();
                explication.setVisibility(View.VISIBLE);
                String reponse = curs.getString(curs.getColumnIndexOrThrow("reponse"));
                if(reponse.equals("VRAI")){
                    explication.setText(getString(R.string.BravoVRAI)+"\n" + curs.getString(curs.getColumnIndexOrThrow("explication")));
                }
                else{
                    explication.setText(getString(R.string.DommageFAUX)+"\n" + curs.getString(curs.getColumnIndexOrThrow("explication")));
                }
                suivant.setVisibility(View.VISIBLE);
                btnFaux.setVisibility(View.INVISIBLE);
                btnVrai.setVisibility(View.INVISIBLE);
                curs.moveToNext();

                suivant.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(i == 2) {
                            Intent intent = new Intent(ActivityPlay.this, ActivityScore.class);
                            startActivity(intent);
                        }
                        else {
                            String questionEnonce = curs.getString(curs.getColumnIndexOrThrow("enonce"));
                            question.setText(questionEnonce);
                            explication.setText("");
                            suivant.setVisibility(View.INVISIBLE);
                            btnFaux.setVisibility(View.VISIBLE);
                            btnVrai.setVisibility(View.VISIBLE);
                            i++;
                        }
                    }
                });

            }
        });

    }

    @SuppressLint("ResourceType")
    public void insertData(){
        SQLiteDatabase db = bdd.getWritableDatabase();
        ContentValues values = new ContentValues();

        //////////// ANIMAUX ////////////

        values.put("id", 1);
        values.put("enonce", getString(R.string.AF1E));
        values.put("theme", getString(R.string.animaux));
        values.put("difficulte", getString(R.string.facile));
        values.put("reponse", getString(R.string.vrai));
        values.put("explication", getString(R.string.AF1EX));
        db.insert("questions", null, values);
        values.clear();

        values.put("id", 2);
        values.put("enonce", getString(R.string.AF2E));
        values.put("theme", getString(R.string.animaux));
        values.put("difficulte", getString(R.string.facile));
        values.put("reponse", getString(R.string.vrai));
        values.put("explication", getString(R.string.AF2Ex));
        db.insert("questions", null, values);
        values.clear();

        values.put("id", 3);
        values.put("enonce", getString(R.string.AF3E));
        values.put("theme", getString(R.string.animaux));
        values.put("difficulte", getString(R.string.facile));
        values.put("reponse", getString(R.string.vrai));
        values.put("explication", getString(R.string.AF3Ex));
        db.insert("questions", null, values);
        values.clear();

        values.put("id", 4);
        values.put("enonce", getString(R.string.AM1E));
        values.put("theme", getString(R.string.animaux));
        values.put("difficulte", getString(R.string.moyen));
        values.put("reponse", getString(R.string.faux));
        values.put("explication", getString(R.string.AM1Ex));
        db.insert("questions", null, values);
        values.clear();

        values.put("id", 5);
        values.put("enonce", getString(R.string.AM2E));
        values.put("theme", getString(R.string.animaux));
        values.put("difficulte", getString(R.string.moyen));
        values.put("reponse", getString(R.string.vrai));
        values.put("explication", getString(R.string.AM2Ex));
        db.insert("questions", null, values);
        values.clear();

        values.put("id", 6);
        values.put("enonce", getString(R.string.AM3E));
        values.put("theme", getString(R.string.animaux));
        values.put("difficulte", getString(R.string.moyen));
        values.put("reponse", getString(R.string.faux));
        values.put("explication", getString(R.string.AM3Ex));
        db.insert("questions", null, values);
        values.clear();

        values.put("id", 7);
        values.put("enonce", getString(R.string.AD1E));
        values.put("theme", getString(R.string.animaux));
        values.put("difficulte", getString(R.string.difficile));
        values.put("reponse", getString(R.string.vrai));
        values.put("explication", getString(R.string.AD1Ex));
        db.insert("questions", null, values);
        values.clear();

        values.put("id", 8);
        values.put("enonce", getString(R.string.AD2E));
        values.put("theme", getString(R.string.animaux));
        values.put("difficulte", getString(R.string.difficile));
        values.put("reponse", getString(R.string.faux));
        values.put("explication", getString(R.string.AD2Ex));
        db.insert("questions", null, values);
        values.clear();

        values.put("id", 9);
        values.put("enonce", getString(R.string.AD3E));
        values.put("theme", getString(R.string.animaux));
        values.put("difficulte", getString(R.string.difficile));
        values.put("reponse", getString(R.string.vrai));
        values.put("explication", getString(R.string.AD3Ex));
        db.insert("questions", null, values);
        values.clear();

        //////////// SCIENCES ET TECH ////////////

        values.put("id", 10);
        values.put("enonce", getString(R.string.SF1E));
        values.put("theme", getString(R.string.sciences));
        values.put("difficulte", getString(R.string.facile));
        values.put("reponse", getString(R.string.vrai));
        values.put("explication", getString(R.string.SF1Ex));
        db.insert("questions", null, values);
        values.clear();

        values.put("id", 11);
        values.put("enonce", getString(R.string.SF2E));
        values.put("theme", getString(R.string.sciences));
        values.put("difficulte", getString(R.string.facile));
        values.put("reponse", getString(R.string.vrai));
        values.put("explication", getString(R.string.SF2Ex));
        db.insert("questions", null, values);
        values.clear();

        values.put("id", 12);
        values.put("enonce", getString(R.string.SF3E));
        values.put("theme", getString(R.string.sciences));
        values.put("difficulte", getString(R.string.facile));
        values.put("reponse", getString(R.string.faux));
        values.put("explication", getString(R.string.SF3Ex));
        db.insert("questions", null, values);
        values.clear();

        values.put("id", 13);
        values.put("enonce", getString(R.string.SM1E));
        values.put("theme", getString(R.string.sciences));
        values.put("difficulte", getString(R.string.moyen));
        values.put("reponse", getString(R.string.faux));
        values.put("explication", getString(R.string.SM1Ex));
        db.insert("questions", null, values);
        values.clear();

        values.put("id", 14);
        values.put("enonce", getString(R.string.SM2E));
        values.put("theme", getString(R.string.sciences));
        values.put("difficulte", getString(R.string.moyen));
        values.put("reponse", getString(R.string.faux));
        values.put("explication", getString(R.string.SM2Ex));
        db.insert("questions", null, values);
        values.clear();

        values.put("id", 15);
        values.put("enonce", getString(R.string.SM3E));
        values.put("theme", getString(R.string.sciences));
        values.put("difficulte", getString(R.string.moyen));
        values.put("reponse", getString(R.string.vrai));
        values.put("explication", getString(R.string.SM3Ex));
        db.insert("questions", null, values);
        values.clear();

        values.put("id", 16);
        values.put("enonce", getString(R.string.SD1E));
        values.put("theme", getString(R.string.sciences));
        values.put("difficulte", getString(R.string.difficile));
        values.put("reponse", getString(R.string.faux));
        values.put("explication", getString(R.string.SD1Ex));
        db.insert("questions", null, values);
        values.clear();

        values.put("id", 17);
        values.put("enonce", getString(R.string.SD2E));
        values.put("theme", getString(R.string.sciences));
        values.put("difficulte", getString(R.string.difficile));
        values.put("reponse", getString(R.string.faux));
        values.put("explication", getString(R.string.SD2Ex));
        db.insert("questions", null, values);
        values.clear();

        values.put("id", 18);
        values.put("enonce", getString(R.string.SD3E));
        values.put("theme", getString(R.string.sciences));
        values.put("difficulte", getString(R.string.difficile));
        values.put("reponse", getString(R.string.faux));
        values.put("explication", getString(R.string.SD3Ex));
        db.insert("questions", null, values);
        values.clear();

        //////////// HISTOIRE ////////////

        values.put("id", 19);
        values.put("enonce", getString(R.string.HF1E));
        values.put("theme", getString(R.string.histoire));
        values.put("difficulte", getString(R.string.facile));
        values.put("reponse", getString(R.string.vrai));
        values.put("explication", getString(R.string.HF1Ex));
        db.insert("questions", null, values);
        values.clear();

        values.put("id", 20);
        values.put("enonce", getString(R.string.HF2E));
        values.put("theme", getString(R.string.histoire));
        values.put("difficulte", getString(R.string.facile));
        values.put("reponse", getString(R.string.vrai));
        values.put("explication", getString(R.string.HF2Ex));
        db.insert("questions", null, values);
        values.clear();

        values.put("id", 21);
        values.put("enonce", getString(R.string.HF3E));
        values.put("theme", getString(R.string.histoire));
        values.put("difficulte", getString(R.string.facile));
        values.put("reponse", getString(R.string.faux));
        values.put("explication", getString(R.string.HF3Ex));
        db.insert("questions", null, values);
        values.clear();

        values.put("id", 22);
        values.put("enonce", getString(R.string.HM1E));
        values.put("theme", getString(R.string.histoire));
        values.put("difficulte", getString(R.string.moyen));
        values.put("reponse", getString(R.string.faux));
        values.put("explication", getString(R.string.HM1Ex));
        db.insert("questions", null, values);
        values.clear();

        values.put("id", 23);
        values.put("enonce", getString(R.string.HM2E));
        values.put("theme", getString(R.string.histoire));
        values.put("difficulte", getString(R.string.moyen));
        values.put("reponse", getString(R.string.faux));
        values.put("explication", getString(R.string.HM2Ex));
        db.insert("questions", null, values);
        values.clear();

        values.put("id", 24);
        values.put("enonce", getString(R.string.HM3E));
        values.put("theme", getString(R.string.histoire));
        values.put("difficulte", getString(R.string.moyen));
        values.put("reponse", getString(R.string.faux));
        values.put("explication", getString(R.string.HM3Ex));
        db.insert("questions", null, values);
        values.clear();

        values.put("id", 25);
        values.put("enonce", getString(R.string.HD1E));
        values.put("theme", getString(R.string.histoire));
        values.put("difficulte", getString(R.string.difficile));
        values.put("reponse", getString(R.string.vrai));
        values.put("explication", getString(R.string.HD1Ex));
        db.insert("questions", null, values);
        values.clear();

        values.put("id", 26);
        values.put("enonce", getString(R.string.HD2E));
        values.put("theme", getString(R.string.histoire));
        values.put("difficulte", getString(R.string.difficile));
        values.put("reponse", getString(R.string.faux));
        values.put("explication", getString(R.string.HD2Ex));
        db.insert("questions", null, values);
        values.clear();

        values.put("id", 27);
        values.put("enonce", getString(R.string.HD3E));
        values.put("theme", getString(R.string.histoire));
        values.put("difficulte", getString(R.string.difficile));
        values.put("reponse", getString(R.string.faux));
        values.put("explication", getString(R.string.HD3Ex));
        db.insert("questions", null, values);
        values.clear();

        //////////// MUSIQUE ////////////

        values.put("id", 28);
        values.put("enonce", getString(R.string.MF1E));
        values.put("theme", getString(R.string.musique));
        values.put("difficulte", getString(R.string.facile));
        values.put("reponse", getString(R.string.faux));
        values.put("explication", getString(R.string.MF1Ex));
        db.insert("questions", null, values);
        values.clear();

        values.put("id", 29);
        values.put("enonce", getString(R.string.MF2E));
        values.put("theme", getString(R.string.musique));
        values.put("difficulte", getString(R.string.facile));
        values.put("reponse", getString(R.string.faux));
        values.put("explication", getString(R.string.MF2Ex));
        db.insert("questions", null, values);
        values.clear();

        values.put("id", 30);
        values.put("enonce", getString(R.string.MF3E));
        values.put("theme", getString(R.string.musique));
        values.put("difficulte", getString(R.string.facile));
        values.put("reponse", getString(R.string.vrai));
        values.put("explication", getString(R.string.MF3Ex));
        db.insert("questions", null, values);
        values.clear();

        values.put("id", 31);
        values.put("enonce", getString(R.string.MM1E));
        values.put("theme", getString(R.string.musique));
        values.put("difficulte", getString(R.string.moyen));
        values.put("reponse", getString(R.string.vrai));
        values.put("explication", getString(R.string.MM1Ex));
        db.insert("questions", null, values);
        values.clear();

        values.put("id", 32);
        values.put("enonce", getString(R.string.MM2E));
        values.put("theme", getString(R.string.musique));
        values.put("difficulte", getString(R.string.moyen));
        values.put("reponse", getString(R.string.vrai));
        values.put("explication", getString(R.string.MM2Ex));
        db.insert("questions", null, values);
        values.clear();

        values.put("id", 33);
        values.put("enonce", getString(R.string.MM3E));
        values.put("theme", getString(R.string.musique));
        values.put("difficulte", getString(R.string.moyen));
        values.put("reponse", getString(R.string.faux));
        values.put("explication", getString(R.string.MM3Ex));
        db.insert("questions", null, values);
        values.clear();

        values.put("id", 34);
        values.put("enonce", getString(R.string.MD1E));
        values.put("theme", getString(R.string.musique));
        values.put("difficulte", getString(R.string.difficile));
        values.put("reponse", getString(R.string.faux));
        values.put("explication", getString(R.string.MD1Ex));
        db.insert("questions", null, values);
        values.clear();

        values.put("id", 35);
        values.put("enonce", getString(R.string.MD2E));
        values.put("theme", getString(R.string.musique));
        values.put("difficulte", getString(R.string.difficile));
        values.put("reponse", getString(R.string.vrai));
        values.put("explication", getString(R.string.MD2Ex));
        db.insert("questions", null, values);
        values.clear();

        values.put("id", 36);
        values.put("enonce", getString(R.string.MD3E));
        values.put("theme", getString(R.string.musique));
        values.put("difficulte", getString(R.string.difficile));
        values.put("reponse", getString(R.string.faux));
        values.put("explication", getString(R.string.MD3Ex));
        db.insert("questions", null, values);
        values.clear();

    }

}
