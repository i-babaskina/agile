package com.example.babaskina.alias.activities;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.babaskina.alias.R;
import com.example.babaskina.alias.model_classes.Game;
import com.example.babaskina.alias.model_classes.Parameters;
import com.example.babaskina.alias.model_classes.Topic;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;



public class GameActivity extends AppCompatActivity {

    protected static int turnLengthSeconds;
    protected int numberWordsToWin;
    protected Topic topic;
    public TextView t;
    public TextView t1;
    ArrayList<String> teams = new ArrayList<String>();
    Map<String, String> topics = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        t = new TextView(this);
        t = (TextView) findViewById(R.id.timeForRoundValueText);
        t1 = new TextView(this);
        t1 = (TextView) findViewById(R.id.wordsToWinValueText);

        teams.add("Valera");teams.add("Dich");teams.add("Demo");
        topics.put("Singularity", "Singularity");topics.put("Memes", "Memes");topics.put("Jokes", "Jokes");

        Spinner spinner = (Spinner) findViewById(R.id.chooseDictionarySpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.teamNames, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String nameTopic = parentView.getItemAtPosition(position).toString();
                topics.get(nameTopic);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }});

        // SeekBars.
        final SeekBar timeForRoundSeekBar = (SeekBar) findViewById(R.id.timeForRoundBar);
        timeForRoundSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                turnLengthSeconds = progress;

                mHandler1.postDelayed(mUpdateUITimerTask1, 0);
            }});

        timeForRoundSeekBar.setProgress(Parameters.STANDARD_TURN_LENGTH_SECONDS);
        final SeekBar wordsToStopSeekBar = (SeekBar) findViewById(R.id.seekBar2);
        wordsToStopSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                numberWordsToWin = progress;
                mHandler.postDelayed(mUpdateUITimerTask, 0);
            }
        });
        wordsToStopSeekBar.setProgress(Parameters.STANDARD_NUMBER_WORDS_TO_WIN);
    }

    public final Runnable mUpdateUITimerTask = new Runnable() {
        public void run() {
            // do whatever you want to change here, like:
            t1.setText("" + numberWordsToWin);
        }
    };

    private final Handler mHandler = new Handler();

   public final Runnable mUpdateUITimerTask1 = new Runnable() {
        public void run() {
            // do whatever you want to change here, like:
            t.setText("" + turnLengthSeconds);
        }
    };

    private final Handler mHandler1 = new Handler();

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }*/


    public void onClickGameToMain(View view) {
        Intent intent = new Intent(GameActivity.this, MenuActivity.class);
        startActivity(intent);
    }
    public void onClickGameStart(View view) {
        startGame();
        Intent intent = new Intent(GameActivity.this, GameProc.class);
        startActivity(intent);
    }
    //public void

    public void startGame() {
        Parameters params = new Parameters(turnLengthSeconds, numberWordsToWin, topic);
        Exchange.game = new Game(Exchange.teams, params);
        Exchange.game.start();
        //Exchange.game = game;
    }
}
