package com.example.babaskina.alias.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.babaskina.alias.model_classes.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by artem on 09.11.15.
 */
public class AliasDatabaseHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "alias.sqlite";
    private static final int VERSION = 1;

    public AliasDatabaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }
    public AliasDatabaseHelper() {
        super(null, DB_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table dictionaries (idDictionary integer primary key autoincrement, title text);");

        db.execSQL("create table themes (idThemes integer primary key autoincrement, title text, idDictionary integer);");

        db.execSQL("create table words (idWords integer primary key autoincrement, title text, idTheme integer, idDictionary integer);");
    }

    public List<Word> getWords(String idDictionary){
        List<Word> words = new ArrayList<Word>();
        SQLiteDatabase db = this.getWritableDatabase();

        String sqlQuery = "select * from words where idDictionary = \"" + idDictionary + "\"";

        Cursor c = db.rawQuery(sqlQuery, null);
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    int titleColIndex = c.getColumnIndex("title");
                    Word resWord = new Word();
                    resWord.setTitleWord(c.getString(titleColIndex));
                    //WordLab.get(getApplicationContext()).addTheme(resTheme);
                    words.add(resWord);
                } while (c.moveToNext());
            }
        }
        return words;
    }
        
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
