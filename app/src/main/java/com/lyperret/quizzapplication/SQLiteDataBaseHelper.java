package com.lyperret.quizzapplication;

import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDataBaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "clients.db";

    public final String SQL_CREATE = "CREATE TABLE Clients (id INTEGER PRIMARY KEY, nom TEXT);";

}
