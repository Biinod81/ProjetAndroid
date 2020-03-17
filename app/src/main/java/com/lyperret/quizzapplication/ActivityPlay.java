package com.lyperret.quizzapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityPlay extends AppCompatActivity {

    SQLiteDataBaseHelper bdd = new SQLiteDataBaseHelper(this);
    TextView question;
    Button suivant;
    Button btnVrai;
    Button btnFaux;
    TextView explication;
    Cursor curs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_play);
        this.insertData();

        suivant = findViewById(R.id.suivant);
        suivant.setVisibility(View.INVISIBLE);
        btnFaux = findViewById(R.id.btnFaux);
        btnVrai = findViewById(R.id.btnVrai);
        explication = findViewById(R.id.explication);
        explication.setVisibility(View.INVISIBLE);



        //récupération de la difficulté et du thème choisi
        String difficulte = getIntent().getStringExtra("difficulte");
        String theme = getIntent().getStringExtra("theme");


        this.selectData(difficulte, theme);
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.quizzmenu,menu);
        return true;
    }

    public void insertData(){
        SQLiteDatabase db = bdd.getWritableDatabase();

        //insertion des question pour le thème Animaux avec la difficulte facile
        ContentValues values = new ContentValues();
        values.put("id", 1);
        values.put("enonce", "Les pieds du manchot servent de nid.");
        values.put("theme", "Animaux");
        values.put("difficulte", "Facile");
        values.put("reponse", "VRAI");
        values.put("explication", "Le manchot mâle place délicatement l’œuf sur ses pattes, puis le cache sous un bourrelet de peau qui transmet efficacement sa chaleur corporelle à l’œuf pour le protéger du froid. La couvaison dure environ deux mois.");
        db.insert("questions", null, values);

        ContentValues values2 = new ContentValues();
        values2.put("id", 2);
        values2.put("enonce", "L’albatros peut voler des heures sans battre des ailes.");
        values2.put("theme", "Animaux");
        values2.put("difficulte", "Facile");
        values2.put("reponse", "VRAI");
        values2.put("explication", "L’albatros hurleur plane au-dessus de la mer pendant des heures sans esquisser le moindre battement d’ailes. Il peut parcourir près de 500 km (310 mi) par jour et atteindre des vitesses frôlant les 70 km/h (43 mi/h) en se laissant simplement porter par les vents.");
        db.insert("questions", null, values2);

        ContentValues values3 = new ContentValues();
        values3.put("id", 3);
        values3.put("enonce", "La couleuvre à collier feint parfois d’être morte pour éloigner les prédateurs non charognards.");
        values3.put("theme", "Animaux");
        values3.put("difficulte", "Facile");
        values3.put("reponse", "VRAI");
        values3.put("explication", "La couleuvre à collier possède de nombreuses stratégies de défense. Il lui arrive de simuler la mort en se retournant sur le dos, restant immobile, la bouche ouverte et la langue pendante. C’est sa façon d’éloigner les prédateurs non charognards.");
        db.insert("questions", null, values3);

        //insertion des question pour le thème Animaux avec la difficulte moyen
        ContentValues values4 = new ContentValues();
        values4.put("id", 4);
        values4.put("enonce","Les chauves-souris vampires sont un mythe.");
        values4.put("theme","Animaux");
        values4.put("difficulte","Moyen");
        values4.put("reponse","FAUX");
        values4.put("explication","Il existe 3 espèces de chauve-souris qui se nourrissent de sang. Ces chauves-souris vivent dans les zones tropicales du continent américain. Elles mesurent à peine 20 cm (8 po) et ne menacent pas la vie de leurs victimes, car elles ne prélèvent qu’une petite quantité de sang.");
        db.insert("questions", null, values4);

        ContentValues values5 = new ContentValues();
        values5.put("id", 5);
        values5.put("enonce","Le crocodile brise la nuque de ses grosses proies pour les tuer.");
        values5.put("theme","Animaux");
        values5.put("difficulte","Moyen");
        values5.put("reponse","FAUX");
        values5.put("explication","Le crocodile saisit ses grosses proies et les entraîne dans le fond des cours d’eau pour les noyer. Il les laisse ensuite pourrir quelques jours au fond de la rivière afin qu’elles soient plus faciles à démembrer.");
        db.insert("questions", null, values5);

        ContentValues values6 = new ContentValues();
        values6.put("id", 6);
        values6.put("enonce","Le petit du phoque (blanchon) triple son poids en 12 jours.");
        values6.put("theme","Animaux");
        values6.put("difficulte","Moyen");
        values6.put("reponse","VRAI");
        values6.put("explication","Le blanchon triple son poids en 12 jours grâce au lait de sa mère qui contient 45 % de gras (celui de la vache n’en contient que 4 %). À 12 jours, il pèse environ 35 kg (77 lb) dont la moitié en graisse.");
        db.insert("questions", null, values6);

        //insertion des question pour le thème Animaux avec la difficulte difficile
        ContentValues values7 = new ContentValues();
        values7.put("id", 7);
        values7.put("enonce","Le morse possède une paire de sacs, reliés au pharynx, qui l’aident à flotter.");
        values7.put("theme","Animaux");
        values7.put("difficulte","Difficile");
        values7.put("reponse","VRAI");
        values7.put("explication","Le morse possède une paire de sacs reliés au pharynx. Une fois gonflés d’air, ces sacs lui servent de bouées et l’aident à maintenir sa tête hors de l’eau.");
        db.insert("questions", null, values7);

        ContentValues values8 = new ContentValues();
        values8.put("id", 8);
        values8.put("enonce","Les manchots marchent jusqu’à 100 km (60 mi) pour atteindre leur lieu de reproduction.");
        values8.put("theme","Animaux");
        values8.put("difficulte","Difficile");
        values8.put("reponse","VRAI");
        values8.put("explication","En mars et avril, les manchots avancent en longues files vers leurs lieux de nidification qui se trouve parfois à plus de 100 km (60 mi) de leurs lieux de pêche.");
        db.insert("questions", null, values8);

        ContentValues values9 = new ContentValues();
        values9.put("id", 9);
        values9.put("enonce","Le dernier gavial s’est éteint au début du 20e siècle.");
        values9.put("theme","Animaux");
        values9.put("difficulte","Difficile");
        values9.put("reponse","FAUX");
        values9.put("explication","Le gavial vit toujours dans le nord de l’Inde et du Népal. Toutefois, l’espèce est en danger critique d’extinction. C’est un crocodilien piscivore, c’est-à-dire qu’il se nourrit de poisson. Il ne représente donc aucun danger pour l’humain.");
        db.insert("questions", null, values9);

        //insertion des question pour le thème Histoire avec la difficulte facile
        ContentValues values10 = new ContentValues();
        values10.put("id", 10);
        values10.put("enonce","Le dernier gavial s’est éteint au début du 20e siècle.");
        values10.put("theme","Histoire");
        values10.put("difficulte","Facile");
        values10.put("reponse","FAUX");
        values10.put("explication","Le gavial vit toujours dans le nord de l’Inde et du Népal. Toutefois, l’espèce est en danger critique d’extinction. C’est un crocodilien piscivore, c’est-à-dire qu’il se nourrit de poisson. Il ne représente donc aucun danger pour l’humain.");
        db.insert("questions", null, values10);

        ContentValues values11 = new ContentValues();
        values11.put("id", 11);
        values11.put("enonce","Le dernier gavial s’est éteint au début du 20e siècle.");
        values11.put("theme","Histoire");
        values11.put("difficulte","Facile");
        values11.put("reponse","FAUX");
        values11.put("explication","Le gavial vit toujours dans le nord de l’Inde et du Népal. Toutefois, l’espèce est en danger critique d’extinction. C’est un crocodilien piscivore, c’est-à-dire qu’il se nourrit de poisson. Il ne représente donc aucun danger pour l’humain.");
        db.insert("questions", null, values11);

        ContentValues values12 = new ContentValues();
        values12.put("id", 12);
        values12.put("enonce","Le dernier gavial s’est éteint au début du 20e siècle.");
        values12.put("theme","Histoire");
        values12.put("difficulte","Difficile");
        values12.put("reponse","FAUX");
        values12.put("explication","Le gavial vit toujours dans le nord de l’Inde et du Népal. Toutefois, l’espèce est en danger critique d’extinction. C’est un crocodilien piscivore, c’est-à-dire qu’il se nourrit de poisson. Il ne représente donc aucun danger pour l’humain.");
        db.insert("questions", null, values12);

        //insertion des question pour le thème Histoire avec la difficulte moyen

        //insertion des question pour le thème Histoire avec la difficulte difficile
    }





    public void selectData(String difficulte, String theme){

        SQLiteDatabase db = bdd.getWritableDatabase();
        String[] col = {"*"};
        String[] select={theme, difficulte};
        curs = db.query("questions", col, "theme=? and difficulte=?", select,null, null, "id ASC");

        this.question = findViewById(R.id.question);
        curs.moveToFirst();
        String questionEnonce = curs.getString(curs.getColumnIndexOrThrow("enonce"));
        question.setText(questionEnonce);

        btnFaux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textFaux = btnFaux.getText().toString();
                explication.setVisibility(View.VISIBLE);
                String reponse = curs.getString(curs.getColumnIndexOrThrow("reponse"));
                if(reponse.equals("FAUX")){
                    explication.setText("Bravo, la bonne réponse était FAUX.\n" + curs.getString(curs.getColumnIndexOrThrow("explication")));
                }
                else{
                    explication.setText("Dommage, la bonne réponse était VRAI.\n" + curs.getString(curs.getColumnIndexOrThrow("explication")));
                }
                suivant.setVisibility(View.VISIBLE);

            }
        });

        btnVrai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textVrai = btnFaux.getText().toString();
                explication.setVisibility(View.VISIBLE);
                String reponse = curs.getString(curs.getColumnIndexOrThrow("reponse"));
                if(reponse.equals("VRAI")){
                    explication.setText("Bravo, la bonne réponse était VRAI.\n" + curs.getString(curs.getColumnIndexOrThrow("explication")));
                }
                else{
                    explication.setText("Dommage, la bonne réponse était FAUX.\n" + curs.getString(curs.getColumnIndexOrThrow("explication")));
                }
            }
        });


    }


}
