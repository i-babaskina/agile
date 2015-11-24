package com.example.babaskina.alias.model_classes;



/**
 * Created by Yohan on 10.05.2015.
 */
public class Team  {
    private int teamId;

    private String temName;

    private int points;

    public Team() {

    }
    public Team(int teamId,String temName,int points) {
        this.teamId = teamId;
        this.points = points;
        this.temName = temName;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setTeamName(String temName) {
        this.temName = temName;
    }

    public int getTeamId() {
        return teamId;
    }

    public int getPoints() {
        return points;
    }

    public String getName() {
        return temName;
    }
}
