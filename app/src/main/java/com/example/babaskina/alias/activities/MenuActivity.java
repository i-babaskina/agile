package com.example.babaskina.alias.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.babaskina.alias.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        TextView btn = (TextView) findViewById(R.id.textView5);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, DictionariesActivity.class);
                startActivity(intent);
            }
        });

        TextView btn1 = (TextView) findViewById(R.id.textView3);
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ChooseGame.class);
                startActivity(intent);
            }
        });

        TextView btn2 = (TextView) findViewById(R.id.textView4);
        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Settings_activity.class);
                startActivity(intent);
            }
        });

        TextView btn3 = (TextView) findViewById(R.id.textView6);
        btn3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, TutorialActivity.class);
                startActivity(intent);
            }
        });

        TextView btn4 = (TextView) findViewById(R.id.textView7);
        btn4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, StatsActivity.class);
                startActivity(intent);
            }
        });
    }


}
