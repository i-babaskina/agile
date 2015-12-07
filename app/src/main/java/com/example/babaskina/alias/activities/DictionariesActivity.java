package com.example.babaskina.alias.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.babaskina.alias.R;

import java.util.ArrayList;

/**
 * Created by artem on 29.10.15.
 */
public class DictionariesActivity extends AppCompatActivity {

    private ArrayList<Dictionary> mDictionaries;

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

        final ListView lvMain = (ListView) findViewById(R.id.listViewDictionariesActivity);
        mDictionaries = DictionaryLab.get(this).getDictionary();
        DictionariesAdapter adapter = new DictionariesAdapter(mDictionaries);
        lvMain.setAdapter(adapter);

        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(DictionariesActivity.this, ThemeActivity.class);
                Dictionary dictionary = (Dictionary) lvMain.getItemAtPosition(position);
                startActivity(i);
            }
        });

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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
