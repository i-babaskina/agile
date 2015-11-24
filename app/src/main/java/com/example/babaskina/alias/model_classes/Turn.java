package com.example.babaskina.alias.model_classes;

import java.util.ArrayList;


public class Turn{
    ArrayList<GameWord> _gameWords;
    Team _team;
    Parameters _parameters;
    int _currWordCount;
    int _statistics;
    int _guessedCount;
    int _unguessedCount;

    private static int i = 0;

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

    // TODO
    public GameWord suggestNewWord(){

//        List<Word1> allWords = getAllSugarWords();
//        Word1 w = new Word1(0, new Description(0, "description"), null, getSugarTopic(Exchange.CurrentTopicId),"�����",false);
//        for(int i = 0;i < allWords.size();i++) {
//            if (allWords.get(i).getTopic().getTopicId() == Exchange.CurrentTopicId) {
//                w = allWords.get(i);
//            }
//        }
        //GameWord result = new GameWord(w,GameWord.NEUTRAL_STATUS);
        String words[] = new String[] {"инкапсуляция", "наследование", "полиморфизм", "абстракция", "интерфейс"};

        GameWord result = new GameWord(new Word1(0, null, null, null, words[i], false));
        i = (i + 1) % words.length;
        _gameWords.add(result);
        return result;
    }

    //void showWord(GameWord inputWord);

    public void stop(){
        _statistics = countStatistics();
    }
}
