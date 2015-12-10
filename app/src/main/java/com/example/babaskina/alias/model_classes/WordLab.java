package com.example.babaskina.alias.model_classes;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by artem on 26.10.15.
 */
public class WordLab {
    private static ArrayList<Word> mWords;
//for commit
    private static WordLab sWordLab;
    private Context mAppContext;

    private WordLab(Context appContext) {
        mAppContext = appContext;
        mWords = new ArrayList<Word>();

    }

    public static WordLab get(Context c) {
        if (sWordLab == null) {
            sWordLab = new WordLab(c.getApplicationContext());
        }
        return sWordLab;
    }

    public static ArrayList<Word> getWords() {
        return mWords;
    }

    public static Word getWord(UUID id) {
        for (Word w : mWords) {
            if (w.getId().equals(id)) {
                return w;
            }
        }
        return null;
    }

    public void addWord(Word word) {
        mWords.add(word);
    }

    public void clear() {
        mWords.clear();
    }

    public void deleteWord(Word word) {
        mWords.remove(word);
    }
}
