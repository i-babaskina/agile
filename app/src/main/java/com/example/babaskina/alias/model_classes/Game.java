package com.example.babaskina.alias.model_classes;

import java.util.ArrayList;

public class Game{
    private static final String NO_TEAM_NAME = "NO_TEAM_NAME";

    ArrayList<Team> _teams;
    Parameters _parameters;
    ArrayList<Round> _rounds;
    int _roundCount;
    int[] _statistics;

    public Game(ArrayList<Team> teams, Parameters parameters){
        _teams = teams;
        _parameters = parameters;
        _rounds = new ArrayList<Round>();
        _roundCount = -1;
        _statistics = new int[_teams.size()];
    }

    public int[] countStatistics(){
        if(_teams == null){
            return null;
        }
        int[] result = new int[_teams.size()];

        for(int team = 0; team < _teams.size(); team++){
            int sum = 0;
            for(int round = 0; round < _rounds.size(); round++){
                try {
                    sum += _rounds.get(round).countStatistics()[team];
                }
                catch (Exception e){
                    // Ignore.
                }
            }
            result[team] = sum;
        }
        _statistics = result;
        return result;
    }

    public int[] getStatitstics(){
        return _statistics;
    }

    public int getTurnLengthSeconds(){
        return _parameters.getTurnLengthSeconds();
    }

    public int getNumberWordsToWin(){
        return _parameters.getNumberWordsToWin();
    }

    public Round getCurrentRound(){
        if(_rounds == null){
            return null;
        }
        if((_roundCount >= _rounds.size()) || (_roundCount < 0)){
            return null;
        }
        return _rounds.get(_roundCount);
    }

    public Turn getCurrentTurn(){
        Round round = getCurrentRound();
        if(round == null){
            return null;
        }
        return round.getCurrentTurn();
    }

    public String getCurrentTeamName(){
        int teamIndex = getTurnCount() - 1;
        if(teamIndex < 0){
            if((_teams == null) || (_teams.size() == 0)){
                return NO_TEAM_NAME;
            }
            return _teams.get(0).getName();
        }
        return getTeamName(teamIndex);
    }

    public String getTeamName(int teamIndex){
        if(_teams == null){
            return NO_TEAM_NAME;
        }
        if((teamIndex >= _teams.size()) || (teamIndex < 0)){
            return NO_TEAM_NAME;
        }
        return _teams.get(teamIndex).getName();
    }

    public ArrayList<String> getAllTeamNames(){
        ArrayList<String> result = new ArrayList<String>();

        if((_teams == null) || (_teams.size() == 0)){
            return result;
        }
        for(Team team : _teams){
            result.add(team.getName());
        }
        return result;
    }

    public int getRoundCount(){
        if(_rounds == null){
            return -1;
        }
        return _rounds.size();
    }

    public int getTurnCount(){
        if(_rounds == null){
            return -1;
        }
        return getCurrentRound().getTurnCount();
    }

    private boolean addToStatistics(int[] roundStatistics){
        if (roundStatistics.length != _statistics.length){
            return false;
        }

        for(int team = 0; team < _teams.size(); team++){
            _statistics[team] += roundStatistics[team];
        }
        return true;
    }

    public void start(){
        startNewRound();
    }

    private boolean startNewRound(){
        if(isTimeToStop()){
            return false;
        }

        Round round = new Round(_teams, _parameters);
        _rounds.add(round);
        round.start();
        _roundCount++;
        return true;
    }

    private boolean stopCurrentRound(){
        Round round = getCurrentRound();

        if(round == null){
            return false;
        }
        round.stop();
        //
        return true;
    }

    boolean isTimeToStop(){
        return determineWinnerIndex() != -1;
    }

    public void stop(){
        stopCurrentRound();
        countStatistics();
    }

    public int determineWinnerIndex(){
        for(int team = 0; team < _teams.size(); team++){
            if(_statistics[team] >= _parameters.getNumberWordsToWin()){
                return team;
            }
        }
        return -1;
    }

    public String determineWinnerName(){
        int index = determineWinnerIndex();

        if(index == -1){
            return "";
        }
        return _teams.get(index).getName();
    }
}