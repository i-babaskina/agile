package com.example.babaskina.alias;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by artem on 29.10.15.
 */
public class ThemeActivity extends AppCompatActivity {

    private static final String TABLE_THEMES = "themes";
    private static final String COLUMN_THEME_TITLE = "title";
    private static final String COLUMN_THEME_IDDICT = "idDictionary";

    private int idDictionary;

    private String themeTitle;

    private AliasDatabaseHelper aliasDBHelper;

    private ArrayList<Theme> mTheme;

    public class ThemeAdapter extends ArrayAdapter<Theme> {
        Context mContext;

        public ThemeAdapter(ArrayList<Theme> themes) {
            super(getApplicationContext(), 0, themes);
            this.mContext = getApplicationContext();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_item_words, null);
            }

            Theme theme = getItem(position);

            TextView titleTextView = (TextView) convertView.findViewById(R.id.word_list_item_titleTextView);
            titleTextView.setText(theme.getTitleTheme());

            return convertView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        idDictionary = getIntent().getIntExtra(COLUMN_THEME_IDDICT, 1);

        ThemeLab.get(this).clear();

        queryThemeDBHelper();

        final ListView lvMain = (ListView) findViewById(R.id.listViewThemeActivity);
        mTheme = ThemeLab.get(this).getTheme();
        ThemeAdapter adapter = new ThemeAdapter(mTheme);
        lvMain.setAdapter(adapter);

        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ThemeActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

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
        themeTitle = data.getStringExtra("title");
        Theme theme = new Theme(themeTitle);
        addThemeToDatabase(theme);
    }

    private void queryThemeDBHelper() {
        aliasDBHelper = new AliasDatabaseHelper(getApplicationContext());
        SQLiteDatabase db = aliasDBHelper.getWritableDatabase();

        String sqlQuery = "select * from themes where idDictionary = \"" + idDictionary + "\"";

        Cursor c = db.rawQuery(sqlQuery, null);
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    int titleColIndex = c.getColumnIndex(COLUMN_THEME_TITLE);
                    Theme resTheme = new Theme();
                    resTheme.setTitleTheme(c.getString(titleColIndex));
                    ThemeLab.get(getApplicationContext()).addTheme(resTheme);
                } while (c.moveToNext());
            }
        }

        c.close();

    }

    private void addThemeToDatabase(Theme theme) {
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = aliasDBHelper.getWritableDatabase();
        cv.put(COLUMN_THEME_TITLE, theme.getTitleTheme());
        cv.put(COLUMN_THEME_IDDICT, idDictionary);
        db.insert(TABLE_THEMES, null, cv);
    }
}