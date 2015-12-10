package com.example.babaskina.alias.activities;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.babaskina.alias.R;
import com.example.babaskina.alias.database.AliasDatabaseHelper;

import java.util.ArrayList;

/**
 * Created by artem on 29.10.15.
 */
public class DictionariesActivity extends AppCompatActivity {

    private static final String TABLE_DICTIONARY = "dictionaries";
    private static final String COLUMN_DICTIONARY_TITLE = "title";
    private static final String idDictionary = "idDictionary";

    private String dictTitle;

    private ArrayList<Dictionary> mDictionaries;

    private AliasDatabaseHelper aliasDBHelper;

    public class DictionariesAdapter extends ArrayAdapter<Dictionary> {
        Context mContext;

        public DictionariesAdapter(ArrayList<Dictionary> dictionaries) {
            super(getApplicationContext(), 0, dictionaries);
            this.mContext = getApplicationContext();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_item_words, null);
            }

            Dictionary dictionary = getItem(position);

            TextView titleTextView = (TextView) convertView.findViewById(R.id.word_list_item_titleTextView);
            titleTextView.setText(dictionary.getTitleDictionary());

            return convertView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionaries);
        DictionaryLab.get(this).clear();

        queryDictDBHelper();

        final ListView lvMain = (ListView) findViewById(R.id.listViewDictionariesActivity);
        mDictionaries = DictionaryLab.get(this).getDictionary();
        DictionariesAdapter adapter = new DictionariesAdapter(mDictionaries);
        lvMain.setAdapter(adapter);

        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(DictionariesActivity.this, ThemeActivity.class);
                i.putExtra(idDictionary, position);
                startActivity(i);
            }
        });
        lvMain.setOnCreateContextMenuListener(this);

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo aMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;

// Получаем позицию элемента в списке
        int position = aMenuInfo.position;

// Получаем данные элемента списка, тип данных здесь вы должны указать свой!

        Dictionary dictionary = mDictionaries.remove(position);
        final String title = dictionary.getTitleDictionary();

        menu.setHeaderTitle("Are you sure you want to delete this element?");
        menu.add("Yes").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
// дествия по клику меню
                deleteDictDB(title);
                Toast.makeText(getApplicationContext(),
                        title + " удалён.",
                        Toast.LENGTH_SHORT).show();
                update();
                return true;
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        DictionaryLab.get(this).clear();
        queryDictDBHelper();
        ListView listView = (ListView) this.findViewById(R.id.listViewDictionariesActivity);
        DictionariesAdapter adapter = new DictionariesAdapter(mDictionaries);
        listView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        DictionaryLab.get(this).clear();
    }

    @Override
    public void onPause() {
        super.onPause();
        DictionaryLab.get(this).clear();
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
        dictTitle = data.getStringExtra("title");
        Dictionary dictionary = new Dictionary(dictTitle);
        addDictToDatabase(dictionary);
    }

    private void queryDictDBHelper() {
        aliasDBHelper = new AliasDatabaseHelper(getApplicationContext());
        SQLiteDatabase db = aliasDBHelper.getWritableDatabase();

        Cursor c = db.query(TABLE_DICTIONARY, null, null, null, null, null, null);

        if (c.moveToFirst()) {

            int titleColIndex = c.getColumnIndex(COLUMN_DICTIONARY_TITLE);


            do {
                Dictionary resDict = new Dictionary();
                resDict.setTitleDictionary(c.getString(titleColIndex));
                DictionaryLab.get(this).addDictionary(resDict);
            } while (c.moveToNext());
        }
        c.close();
    }

    private void update(){
        DictionaryLab.get(this).clear();
        queryDictDBHelper();
        ListView listView = (ListView) this.findViewById(R.id.listViewDictionariesActivity);
        DictionariesAdapter adapter = new DictionariesAdapter(mDictionaries);
        listView.setAdapter(adapter);
    }

    private void addDictToDatabase(Dictionary dictionary) {
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = aliasDBHelper.getWritableDatabase();
        cv.put(COLUMN_DICTIONARY_TITLE, dictionary.getTitleDictionary());
        db.insert(TABLE_DICTIONARY, null, cv);
    }
    private void deleteDictDB(String dictTitle) {
        aliasDBHelper = new AliasDatabaseHelper(getApplicationContext());
        SQLiteDatabase db = aliasDBHelper.getWritableDatabase();
        db.delete("dictionaries", "title = \"" + dictTitle + "\"", null);
        aliasDBHelper.close();
    }
}
