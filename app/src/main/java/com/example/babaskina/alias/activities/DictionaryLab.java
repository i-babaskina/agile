package com.example.babaskina.alias.activities;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by artem on 29.10.15.
 */
public class DictionaryLab {
    private ArrayList<Dictionary> mDictionaries;

    private static DictionaryLab sDictionariesLab;
    private Context mAppContext;

    private DictionaryLab(Context appContext) {
        mAppContext = appContext;
        mDictionaries = new ArrayList<Dictionary>();
    }

    public static DictionaryLab get(Context c) {
        if (sDictionariesLab == null) {
            sDictionariesLab = new DictionaryLab(c.getApplicationContext());
        }
        return sDictionariesLab;
    }

    public ArrayList<Dictionary> getDictionary() {
        return mDictionaries;
    }

    public Dictionary getDictionary(UUID id) {
        for (Dictionary dictionary : mDictionaries) {
            if (dictionary.getId().equals(id)) {
                return dictionary;
            }
        }
        return null;
    }

    public void addDictionary(Dictionary dictionary) {
        mDictionaries.add(dictionary);
    }

    public void clear() {
        mDictionaries.clear();
    }

    public void deleteDictionary(Dictionary dictionary) {
        mDictionaries.remove(dictionary);
    }
}
