package com.example.babaskina.alias.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.babaskina.alias.R;
import com.example.babaskina.alias.activities.tutorial.RulesActivity;
import com.example.babaskina.alias.activities.tutorial.page1;

public class TutorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        TextView rules = (TextView) findViewById(R.id.rules);
        rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TutorialActivity.this, RulesActivity.class);
                startActivity(intent);

            }
        });
        TextView toplay = (TextView) findViewById(R.id.toplay);
        toplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TutorialActivity.this, page1.class);
                startActivity(intent);

            }
        });
    }
}
