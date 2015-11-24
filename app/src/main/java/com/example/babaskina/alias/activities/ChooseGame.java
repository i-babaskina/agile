package com.example.babaskina.alias.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.babaskina.alias.R;

public class ChooseGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);
        TextView btn = (TextView) findViewById(R.id.textView8);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseGame.this, Edit_Teams.class);
                startActivity(intent);
            }
        });
        TextView btn1 = (TextView) findViewById(R.id.textView9);
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseGame.this, GameProc.class);
                startActivity(intent);
            }
        });

    }
}
