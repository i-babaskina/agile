package com.example.babaskina.alias;

import android.content.Context;
import android.content.Intent;
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

        final ListView lvMain = (ListView) findViewById(R.id.listViewThemeActivity);
        mTheme = ThemeLab.get(this).getTheme();
        ThemeAdapter adapter = new ThemeAdapter(mTheme);
        lvMain.setAdapter(adapter);

        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ThemeActivity.this, MainActivity.class);
                Theme theme = (Theme) lvMain.getItemAtPosition(position);
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
