package com.example.babaskina.alias.model_classes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.babaskina.alias.database.AliasDatabaseHelper;

import java.util.ArrayList;
import java.util.List;


public class Turn{
    private static final String TABLE_WORD = "words";
    private static final String COLUMN_WORD_TITLE = "title";
    private static final String COLUMN_WORD_IDTHEME = "idTheme";
    private static final String COLUMN_WORD_IDDICT = "idDictionary";
    private int idDictionary;
    private int idTheme;

    private String wordTitle;
    ArrayList<GameWord> _gameWords;
    Team _team;
    Parameters _parameters;
    int _currWordCount;
    int _statistics;
    int _guessedCount;
    int _unguessedCount;
    private Context context;

    private static int i = 0;
    public  Turn(Context context){this.context = context;}
    public Turn(Team playingTeam, Parameters parameters){
        _team = _team;
        _parameters = parameters;
        _currWordCount = -1;
        _gameWords = new ArrayList<GameWord>();
    }

    public ArrayList<GameWord> getListOfWords(){
        return _gameWords;
    }

    public int countStatistics(){
        int result = 0;

        for(int i = 0; i < _gameWords.size(); i++){
            result += _gameWords.get(i).getGuessedStatus();
        }
        _statistics = result;
        return result;
    }

    public GameWord getCurrentWord(){
        if(_gameWords == null){
            return null;
        }
        return _gameWords.get(_gameWords.size() - 1);
    }

    public int countNumberOfGuessed(){
        int result = 0;

        for(GameWord gameWord : _gameWords){
            if(gameWord.getIsGuessed()){
                result += 1;
            }
        }
        _guessedCount = result;
        return result;
    }

    public int countNumberOfUnguessed(){
        int result = 0;

        for(GameWord gameWord : _gameWords){
            if(gameWord.getIsUnguessed()){
                result += 1;
            }
        }
        _unguessedCount = result;
        return result;
    }

    public int getStatistics(){
        return _statistics;
    }

    public int getNumberOfGuessed(){
        return _guessedCount;
    }

    public int getNumberOfUnguessed(){
        return _unguessedCount;
    }

    public void start(){
        _gameWords.clear();
        suggestNewWord();
    }

    public void GuessCurrentWord(){
        _gameWords.get(_currWordCount).markGuessed();
        _guessedCount++;
        _statistics += GameWord.GUESSED_STATUS;
        suggestNewWord();
    }

    public void UnguessCurrentWord(){
        _gameWords.get(_currWordCount).markUnguessed();
        _unguessedCount++;
        _statistics += GameWord.UNGUESSED_STATUS;
        suggestNewWord();
    }
    private void queryWordDBHelper() {
        AliasDatabaseHelper aliasDBHelper = new AliasDatabaseHelper();
        SQLiteDatabase db = aliasDBHelper.getWritableDatabase();

        String sqlQuery = "select * from words where idDictionary = \"" + idDictionary + "\"";

        Cursor c = db.rawQuery(sqlQuery, null);
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    int themeColIndex = c.getColumnIndex(COLUMN_WORD_IDTHEME);
                    int themeID = Integer.parseInt(c.getString(themeColIndex));
                    if (themeID == idTheme) {
                        int titleColIndex = c.getColumnIndex(COLUMN_WORD_TITLE);
                        com.example.babaskina.alias.activities.Word resWord = new com.example.babaskina.alias.activities.Word();
                        resWord.setTitleWord(c.getString(titleColIndex));
                    }
                } while (c.moveToNext());
            }
        }

        c.close();

    }
    /*private void queryAllWordsDBHelper() {
        AliasDatabaseHelper aliasDBHelper = new AliasDatabaseHelper(this);
        SQLiteDatabase db = aliasDBHelper.getWritableDatabase();

        String sqlQuery = "select * from words where idDictionary = \"" + idDictionary + "\"";

        Cursor c = db.rawQuery(sqlQuery, null);
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    int titleColIndex = c.getColumnIndex(COLUMN_WORD_TITLE);
                    Word resWord = new Word();
                    resWord.setTitleWord(c.getString(titleColIndex));
                    WordLab.get(this).addWord(resWord);
                } while (c.moveToNext());
            }
        }

        c.close();

    }*/
    public GameWord suggestNewWord(){

//        List<Word1> allWords = getAllSugarWords();
//        Word1 w = new Word1(0, new Description(0, "description"), null, getSugarTopic(Exchange.CurrentTopicId),"�����",false);
//        for(int i = 0;i < allWords.size();i++) {
//            if (allWords.get(i).getTopic().getTopicId() == Exchange.CurrentTopicId) {
//                w = allWords.get(i);
//            }
//        }
        //GameWord result = new GameWord(w,GameWord.NEUTRAL_STATUS);
        //String words[] = new String[] {"инкапсуляция", "наследование", "полиморфизм", "абстракция", "интерфейс"};
        //AliasDatabaseHelper dbHelper = new AliasDatabaseHelper(this);
        List<Word> words = WordLab.getWords();
        GameWord result = new GameWord(new Word1(0, null, null, null, words.get(i).toString(), false));
        i = (i + 1) % words.size();
        _gameWords.add(result);
        return result;

    }

    //void showWord(GameWord inputWord);

    public void stop(){
        _statistics = countStatistics();
    }
}
