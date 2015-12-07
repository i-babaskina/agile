package com.example.babaskina.alias.model_classes;

import java.util.UUID;

/**
 * Created by artem on 29.10.15.
 */
public class Dictionary {
    private String titleDictionary;
    private UUID id;

    public Dictionary(){
        id = UUID.randomUUID();
        titleDictionary = "Dictionary";
    }

    public Dictionary(String title){
        id = UUID.randomUUID();
        titleDictionary = title;
    }

    public String getId() {
        return id.toString();
    }

    public String getTitleDictionary() {
        return titleDictionary;
    }

    public void setTitleDictionary(String titleDictionary) {
        this.titleDictionary = titleDictionary;
    }
}
