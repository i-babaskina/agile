package com.example.babaskina.alias.model_classes;

public class Level {
    private int levelId;

    private String levelName;

    private int levelNumber;

    public Level() {

    }
    public Level(int levelId, String levelName, int levelNumber) {
        this.levelId = levelId;
        this.levelName = levelName;
        this.levelNumber = levelNumber;
    }

    public int getLevelId() {
        return levelId;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }
}
