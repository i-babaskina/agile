package com.example.babaskina.alias.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.babaskina.alias.R;
import com.example.babaskina.alias.database.AliasDatabaseHelper;
import com.example.babaskina.alias.model_classes.*;
import com.example.babaskina.alias.model_classes.WordLab;

import java.util.ArrayList;
import java.util.Collections;


public class GameProc extends AppCompatActivity {
    private int idDictionary;
    private static final String COLUMN_WORD_TITLE = "title";
    public ArrayList<String> teams = new ArrayList<String>();
    ProgressBar myProgressBar;
    TextView Progress;
    int progress = 100;//GameActivity.turnLengthSeconds;
    int myProgress = 0;
    TextView gameWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_proc);
        Resources res = getResources();
        Collections.addAll(teams, res.getStringArray(R.array.teamset));

        myProgressBar = (ProgressBar) findViewById(R.id.progressBar3);
        Progress = (TextView) findViewById(R.id.textView2);
        gameWord = (TextView) findViewById(R.id.gameWordCurrent);
        new Thread(myThread).start();
        //public Turn turn = new Turn(playingTeam, parametres);

        TextView teamName = (TextView) findViewById(R.id.gameProcTeamName);
        //gameWord = (TextView) findViewById(R.id.gameWordCurrent);

        teamName.setText(teams.get(1));/*Exchange.game.getCurrentTeamName()*/
        gameWord.setText(Exchange.game.getCurrentTurn().suggestNewWord().getInLowercase());
    }

    public void progress(){
        while(true){
            progress = 0;
            progress++;
            Progress.setText(""+progress);
        }
    }

    private void queryAllWordsDBHelper() {
        AliasDatabaseHelper aliasDBHelper = new AliasDatabaseHelper(this);
        SQLiteDatabase db = aliasDBHelper.getWritableDatabase();

        String sqlQuery = "select * from words where idDictionary = \"" + idDictionary + "\"";

        Cursor c = db.rawQuery(sqlQuery, null);
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    int titleColIndex = c.getColumnIndex(COLUMN_WORD_TITLE);
                    com.example.babaskina.alias.model_classes.Word resWord = new com.example.babaskina.alias.model_classes.Word();
                    resWord.setTitleWord(c.getString(titleColIndex));
                    WordLab.get(this).addWord(resWord);
                } while (c.moveToNext());
            }
        }

        c.close();

    }

    private Runnable myThread = new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            while (myProgress < 100) {
                try {
                    myHandle.sendMessage(myHandle.obtainMessage());
                    Thread.sleep(Exchange.game.getTurnLengthSeconds() * 10);
                } catch (Throwable t) {
                }
            }
            Intent intent = new Intent(GameProc.this, WordStatistic.class);
            startActivity(intent);
        }

        Handler myHandle = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                myProgress++;
                myProgressBar.setProgress(myProgress);
            }
        };
    };

    private final Runnable mUpdateUITimerTask = new Runnable() {
        public void run() {
            // do whatever you want to change here, like:
            Exchange.game.getCurrentTurn().getCurrentWord().markGuessed();
            gameWord.setText(Exchange.game.getCurrentTurn().suggestNewWord().getInLowercase());
        }
    };
    private final Runnable mUpdateUITimerTask1 = new Runnable() {
        public void run() {
            // do whatever you want to change here, like:
            Exchange.game.getCurrentTurn().getCurrentWord().markUnguessed();
            gameWord.setText(Exchange.game.getCurrentTurn().suggestNewWord().getInLowercase());
        }
    };

    private final Handler mHandler11 = new Handler();

    private final Handler mHandler1 = new Handler();


    public void onClickApprove(View view) {
        mHandler1.postDelayed(mUpdateUITimerTask, 0);
    }

    public void onClickDecline(View view) {
        mHandler11.postDelayed(mUpdateUITimerTask1, 0);
    }


}

