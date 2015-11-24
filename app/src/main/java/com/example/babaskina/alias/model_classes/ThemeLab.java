package com.example.babaskina.alias.model_classes;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by artem on 29.10.15.
 */
public class ThemeLab {
    private ArrayList<Theme> mTheme;

    private static ThemeLab sThemeLab;
    private Context mAppContext;

    private ThemeLab(Context appContext) {
        mAppContext = appContext;
        mTheme = new ArrayList<Theme>();
        for (int i = 0; i < 10; i++) {
            Theme theme = new Theme();
            theme.setTitleTheme("Theme #" + i);
            mTheme.add(theme);
        }
    }

    public static ThemeLab get(Context c) {
        if (sThemeLab == null) {
            sThemeLab = new ThemeLab(c.getApplicationContext());
        }
        return sThemeLab;
    }

    public ArrayList<Theme> getTheme() {
        return mTheme;
    }

    public Theme getTheme(UUID id) {
        for (Theme theme : mTheme) {
            if (theme.getId().equals(id)) {
                return theme;
            }
        }
        return null;
    }

    public void addTheme(Theme theme) {
        mTheme.add(theme);
    }

    public void clear() {
        mTheme.clear();
    }

    public void deleteTheme(Theme theme) {
        mTheme.remove(theme);
    }
}
