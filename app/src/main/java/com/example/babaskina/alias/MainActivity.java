package com.example.babaskina.alias;

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

        ThemeLab.get(this).clear();

        queryThemeDBHelper();

        final ListView lvMain = (ListView) findViewById(R.id.listViewWordsMainActivity);
        mWords = WordLab.get(this).getWords();
        WordAdapter adapter = new WordAdapter(mWords);
        lvMain.setAdapter(adapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        ThemeLab.get(this).clear();
        queryThemeDBHelper();
        ListView listView = (ListView) this.findViewById(R.id.listViewThemeActivity);
        ThemeAdapter adapter = new ThemeAdapter(mTheme);
        listView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ThemeLab.get(this).clear();
    }

    @Override
    public void onPause() {
        super.onPause();
        ThemeLab.get(this).clear();
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
        Theme theme = new Theme(wordTitle);
        addThemeToDatabase(theme);
    }

    
}
