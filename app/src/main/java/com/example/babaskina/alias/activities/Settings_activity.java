package com.example.babaskina.alias.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.babaskina.alias.R;

public class Settings_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_activity);
    }



    public void onClickSettingsToMain(View view) {
        Intent intent = new Intent(Settings_activity.this, MenuActivity.class);
        startActivity(intent);
    }
}
