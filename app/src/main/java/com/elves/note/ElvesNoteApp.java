package com.elves.note;

import android.app.Application;

public class ElvesNoteApp extends Application {
    public static ElvesNoteApp ysApp;
    @Override
    public void onCreate() {
        super.onCreate();
        ysApp=this;
    }
}
