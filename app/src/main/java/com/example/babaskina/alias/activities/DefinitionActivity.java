package com.example.babaskina.alias.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.babaskina.alias.R;
import com.example.babaskina.alias.model_classes.Turn;

public class DefinitionActivity extends AppCompatActivity {
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition);
        final TextView def = (TextView)findViewById(R.id.def);
        final TextView word1 = (TextView)findViewById(R.id.textView13);
        Button button = (Button)findViewById(R.id.button123);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ukn1[] = GameProc.ukn;
                word1.setText(""+ukn1[i]);
                if(ukn1[i].equals("инкапсуляция")) def.setText(definitions.definitions[0]);
                if(ukn1[i].equals("абстракция")) def.setText(definitions.definitions[1]);
                if(ukn1[i].equals("наследование")) def.setText(definitions.definitions[2]);
                if(ukn1[i].equals("полиморфизм")) def.setText(definitions.definitions[3]);
                if(ukn1[i].equals("паттерн")) def.setText(definitions.definitions[4]);
                if(ukn1[i].equals("геймификация")) def.setText(definitions.definitions[5]);
                if(ukn1[i].equals(null)) def.setText("");
                i++;
            }
        });

    }
}
