package com.lyperret.quizzapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDataBaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "quizz.db";

    public final String SQL_CREATE = "CREATE TABLE questions (id INTEGER PRIMARY KEY, enonce TEXT, theme TEXT, difficulte TEXT, reponse TEXT, explication TEXT);";

    public final String SQL_DELETE = "DROP TABLE IF EXISTS question";

    public SQLiteDataBaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int NewVersion) {

    }

}