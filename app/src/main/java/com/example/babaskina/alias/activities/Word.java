package com.example.babaskina.alias.activities;

import java.util.UUID;

/**
 * Created by artem on 26.10.15.
 */
public class Word {

    private String titleWord;
    private UUID id;

    public Word(){
        id = UUID.randomUUID();
        titleWord = "word";
    }

    public Word(String title){
        id = UUID.randomUUID();
        titleWord = title;
    }

    public String getId() {
        return id.toString();
    }

    public String getTitleWord() {
        return titleWord;
    }

    public void setTitleWord(String titleWord) {
        this.titleWord = titleWord;
    }
}
