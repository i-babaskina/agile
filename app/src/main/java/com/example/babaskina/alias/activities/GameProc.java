package com.example.babaskina.alias.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
    public static int guessed = 0;
    public static int unguessed = 0;
    public static int unknown = 0;
    public static int count = 0;
    static String currentword = "";
    public static String ukn[] = new String[200];
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
        gameWord = (TextView) findViewById(R.id.gameWordCurrent);
        teamName.setText(teams.get(1));/*Exchange.game.getCurrentTeamName()*/
        currentword = Exchange.game.getCurrentTurn().suggestNewWord().getInLowercase();
        gameWord.setText(currentword);
        final ImageButton guessedbtn = (ImageButton) findViewById(R.id.imageButton6);
        guessedbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler1.postDelayed(mUpdateUITimerTask, 0);
                guessed++;
                count++;
            }
        });
        final ImageButton unguessedbtn = (ImageButton) findViewById(R.id.imageButton7);
        unguessedbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler1.postDelayed(mUpdateUITimerTask1, 0);
                unguessed++;
                count++;
            }
        });
        final ImageButton unknownbtn = (ImageButton) findViewById(R.id.imageButton);
        unknownbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler1.postDelayed(mUpdateUITimerTask2, 0);
                ukn[unknown] = currentword;
                unknown++;
                count++;
        }});
    };


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
                Progress.setText("" + (100 - myProgress) + "%");
            }
        };
    };

    private final Runnable mUpdateUITimerTask = new Runnable() {
        public void run() {
            // do whatever you want to change here, like:
            gameWord.setText(Exchange.game.getCurrentTurn().suggestNewWord().getInLowercase());
        }
    };
    private final Runnable mUpdateUITimerTask1 = new Runnable() {
        public void run() {
            // do whatever you want to change here, like:
            gameWord.setText(Exchange.game.getCurrentTurn().suggestNewWord().getInLowercase());
        }
    };
    private final Runnable mUpdateUITimerTask2 = new Runnable() {
        public void run() {
            // do whatever you want to change here, like:
            currentword = Exchange.game.getCurrentTurn().suggestNewWord().getInLowercase();
            gameWord.setText(currentword);

        }
    };

    private final Handler mHandler11 = new Handler();

    private final Handler mHandler1 = new Handler();


   /* public static void onClickApprove(View view) {
        guessed++;
    }

    public static void onClickDecline(View view) {
        unguessed++;
    }
    public static void onClickUnknown(){
        unknown++;
    }*/
   public static String[] getUkn(){
       return ukn;
   }
    public static String getGuessed(){
        return ""+guessed;
    }
    public static String getUnguessed(){
        return ""+unguessed;
    }
    public static String getUnknown(){
        return ""+unknown;
    }
    public static String getTotal(){
        int total = guessed-unguessed;
        total = (total < 0) ? 0 : total;
        return ""+ total;
    }

}

