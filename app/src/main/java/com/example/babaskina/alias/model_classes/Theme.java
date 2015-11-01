package com.example.babaskina.alias;

import java.util.UUID;

/**
 * Created by artem on 29.10.15.
 */
public class Theme {
    private String titleTheme;
    private UUID id;

    public Theme(){
        id = UUID.randomUUID();
        titleTheme = "Theme";
    }

    public Theme(String title){
        id = UUID.randomUUID();
        titleTheme = title;
    }

    public String getId() {
        return id.toString();
    }

    public String getTitleTheme() {
        return titleTheme;
    }

    public void setTitleTheme(String titleTheme) {
        this.titleTheme = titleTheme;
    }
}
