package com.example.babaskina.alias.model_classes;


public class GameWord{
    public static final int GUESSED_STATUS = 1;
    public static final int NEUTRAL_STATUS = 0;
    public static final int UNGUESSED_STATUS = -1;

    Word1 _word1;
    int _guessedStatus;

    public GameWord(Word1 inputWord1, int guessedStatus){
        _word1 = inputWord1;
        _guessedStatus = guessedStatus;
    }
    public GameWord(Word1 word1){
        this(word1, NEUTRAL_STATUS);
    }

    public String getInUppercase(){
        //return ToUpperCase(_word1.getWordText());
        String result = new String(_word1.getWordText());

        result.toUpperCase();
        return result;
    }

    public String getInLowercase(){
        //return ToLowerCase(_word1.getWordText());
        String result = new String(_word1.getWordText());

        result.toLowerCase();
        return result;
    }

    public int getLCharactersNumber(){
        return _word1.getWordText().length();
    }

    public int getGuessedStatus(){
        return _guessedStatus;
    }

    public boolean getIsGuessed(){
        return _guessedStatus == GUESSED_STATUS;
    }

    public boolean getIsUnguessed(){
        return _guessedStatus == UNGUESSED_STATUS;
    }

    public boolean getIsNeutral(){
        return _guessedStatus == NEUTRAL_STATUS;
    }

    public void markGuessed(){
        _guessedStatus = GUESSED_STATUS;
    }

    public void markNeutral(){
        _guessedStatus = NEUTRAL_STATUS;
    }

    public void markUnguessed() {
        _guessedStatus = UNGUESSED_STATUS;
    }
}