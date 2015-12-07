package com.example.babaskina.alias.activities;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.babaskina.alias.R;
import com.example.babaskina.alias.database.AliasDatabaseHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TABLE_WORD = "words";
    private static final String COLUMN_WORD_TITLE = "title";
    private static final String COLUMN_WORD_IDTHEME = "idTheme";
    private static final String COLUMN_WORD_IDDICT = "idDictionary";

    private int idDictionary;
    private int idTheme;

    private String wordTitle;

    private AliasDatabaseHelper aliasDBHelper;

    private ArrayList<Word> mWords;

    public class WordAdapter extends ArrayAdapter<Word> {
        Context mContext;

        public WordAdapter(ArrayList<Word> words) {
            super(getApplicationContext(), 0, words);
            this.mContext = getApplicationContext();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_item_words, null);
            }

            Word word = getItem(position);

            TextView titleTextView = (TextView) convertView.findViewById(R.id.word_list_item_titleTextView);
            titleTextView.setText(word.getTitleWord());

            return convertView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idDictionary = getIntent().getIntExtra(COLUMN_WORD_IDDICT, 1);
        idTheme = getIntent().getIntExtra(COLUMN_WORD_IDTHEME, 1);

        WordLab.get(this).clear();

        queryWordDBHelper();

        final ListView lvMain = (ListView) findViewById(R.id.listViewWordsMainActivity);
        mWords = WordLab.get(this).getWords();
        WordAdapter adapter = new WordAdapter(mWords);
        lvMain.setAdapter(adapter);
    }



    @Override
    public void onResume() {
        super.onResume();
        WordLab.get(this).clear();
        queryWordDBHelper();
        ListView listView = (ListView) this.findViewById(R.id.listViewWordsMainActivity);
        WordAdapter adapter = new WordAdapter(mWords);
        listView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        WordLab.get(this).clear();
    }

    @Override
    public void onPause() {
        super.onPause();
        WordLab.get(this).clear();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_add_task:
                Intent i = new Intent(getApplicationContext(), AddElementsActivity.class);
                startActivityForResult(i, 0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        wordTitle = data.getStringExtra("title");
        Word word = new Word(wordTitle);
        addThemeToDatabase(word);
    }

    private void queryWordDBHelper() {
        aliasDBHelper = new AliasDatabaseHelper(getApplicationContext());
        SQLiteDatabase db = aliasDBHelper.getWritableDatabase();

        String sqlQuery = "select * from words where idDictionary = \"" + idDictionary + "\"";

        Cursor c = db.rawQuery(sqlQuery, null);
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    int themeColIndex = c.getColumnIndex(COLUMN_WORD_IDTHEME);
                    int themeID = Integer.parseInt(c.getString(themeColIndex));
                    if (themeID == idTheme) {
                        int titleColIndex = c.getColumnIndex(COLUMN_WORD_TITLE);
                        Word resWord = new Word();
                        resWord.setTitleWord(c.getString(titleColIndex));
                        WordLab.get(getApplicationContext()).addWord(resWord);
                        WordLab.get(getApplicationContext()).deleteWord(resWord);
                    }
                } while (c.moveToNext());
            }
        }

        c.close();

    }

    private void addThemeToDatabase(Word word) {
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = aliasDBHelper.getWritableDatabase();
        cv.put(COLUMN_WORD_TITLE, word.getTitleWord());
        cv.put(COLUMN_WORD_IDDICT, idDictionary);
        cv.put(COLUMN_WORD_IDTHEME, idTheme);
        db.insert(TABLE_WORD, null, cv);
    }

    private void deleteThemefromDatabase(Word word) {
        SQLiteDatabase db = aliasDBHelper.getWritableDatabase();
        db.delete(TABLE_WORD, null, null);
    }
}
