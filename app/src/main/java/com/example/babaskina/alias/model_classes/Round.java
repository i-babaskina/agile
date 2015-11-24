package com.example.babaskina.alias.model_classes;

import java.util.ArrayList;


public class Round{
    ArrayList<Team> _teams;
    Parameters _parameters;
    ArrayList<Turn> _turns;
    int _turnCount;
    int[] _statistics;

    public Round(ArrayList<Team> teams, Parameters parameters){
        _teams = teams;
        _parameters = parameters;
        _turnCount = -1;
        _turns = new ArrayList<Turn>();
        _statistics = new int[_teams.size()];
    }

    public int[] countStatistics(){
        if(_teams == null){
            return null;
        }
        int i = 0;
        int[] result = new int[_teams.size()];

        while(i < _turnCount){
            result[i] = _turns.get(i).countStatistics();
        }
        _statistics = result;
        return result;
    }

    public int[] getStatistics(){
        return _statistics;
    }

    public Turn getCurrentTurn(){
        if(_turns == null){
            return null;
        }
        if((_turnCount >= _turns.size()) || (_turnCount < 0)){
            return null;
        }
        return _turns.get(_turnCount);
    }

    public int getTurnCount(){
        if(_turns == null){
            return -1;
        }
        return _turns.size();
    }

    private boolean isTimeToStop(){
        return _turnCount == _teams.size() - 1;
    }

    public void start(){
        newTurn();
    }

    public boolean newTurn(){
        stopCurrentTurn();
        return startNewTurn();
    }

    private boolean startNewTurn(){
        _turnCount++;
        if((_turnCount >= _teams.size()) || (_turnCount < 0)){
            return false;
        }
        Turn currTurn = new Turn(_teams.get(_turnCount), _parameters);
        _turns.add(currTurn);
        currTurn.start();
        return true;
    }

    private boolean stopCurrentTurn(){
        Turn turn = getCurrentTurn();

        if(turn == null){
            return false;
        }
        turn.stop();
        _statistics[_turnCount] = turn.countStatistics();
        return true;
    }

    public void stop(){
        stopCurrentTurn();
        countStatistics();
    }
}