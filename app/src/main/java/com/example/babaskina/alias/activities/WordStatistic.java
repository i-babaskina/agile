package com.example.babaskina.alias.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.babaskina.alias.R;
import com.example.babaskina.alias.model_classes.Game;

public class WordStatistic extends AppCompatActivity {
    TextView guessed1;
    TextView unguessed2;
    TextView unknown3;
    public static int guessed = 0;
    public static int unguessed = 0;
    public static int unknown = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_statistic);
        guessed1 = (TextView)findViewById(R.id.textView7);
        unguessed2 = (TextView)findViewById(R.id.textView10);
        unknown3 = (TextView)findViewById(R.id.textView11);
        TextView total = (TextView)findViewById(R.id.textView12);
        guessed1.setText("Guessed: "+GameProc.getGuessed());
        unguessed2.setText("Unguessed: "+GameProc.getUnguessed());
        unknown3.setText("Unknown: " +GameProc.getUnknown());
        total.setText("Total Score: " + GameProc.getTotal());
        unknown3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WordStatistic.this, DefinitionActivity.class);
                startActivity(intent);
            }
        });
    }

}
