package com.example.babaskina.alias.model_classes;


public class Word1 {
    private int wordId;

    private Description descr;

    private Level level;

    private Topic topic;

    private String wordText;

    public boolean isDef;

    public Word1() {

    }

    public Word1(int wordId, Description descr, Level level, Topic topic, String wordText, boolean isDefault) {
        setDef(isDefault);
        setDescr(descr);
        setLevel(level);
        setTopic(topic);
        setWordId(wordId);
        setWordText(wordText);
    }
    public Word1(int wordId, String wordText, Topic topic){
        new Word1(wordId, new Description(0, "description"), null, topic, wordText,false);
        setDef(true);
        setDescr(new Description(0, "description"));
        setLevel(null);
        setTopic(topic);
        setWordId(wordId);
        setWordText(wordText);
    }

    //region GetSet
    public void setWordId ( int wordId){
        this.wordId = wordId;
    }

    public void setDef(boolean isDef) {
        this.isDef = isDef;
    }

    public void setDescr(Description descr) {
        this.descr = descr;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public void setWordText(String wordText) {
        this.wordText = wordText;
    }

    public int getWordId() {
        return wordId;
    }

    public Description getDescr() {
        return descr;
    }

    public Level getLevel() {
        return level;
    }

    public String getWordText() {
        return wordText;
    }

    public Topic getTopic() {
        return topic;
    }
    //endregion
}
