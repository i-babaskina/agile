package com.example.babaskina.alias;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by artem on 09.11.15.
 */
public class AliasDatabaseHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "alias.sqlite";
    private static final int VERSION = 1;

    public AliasDatabaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table dictionaries (idDictionary integer primary key autoincrement, title text);");

        db.execSQL("create table themes (idThemes integer primary key autoincrement, title text, idDictionary integer);");

        db.execSQL("create table words (idWords integer primary key autoincrement, title text, idTheme integer);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
