package com.elves.note.utils;

import android.content.Context;
import android.widget.Toast;
import com.elves.note.ElvesNoteApp;

public class ToastUtil {

    private static final Context sContext = ElvesNoteApp.ysApp;

    public static void shortToast(String msg) {
        Toast.makeText(sContext, msg, Toast.LENGTH_SHORT).show();
    }

    public static void shortToast(int msgId) {
        Toast.makeText(sContext, msgId, Toast.LENGTH_SHORT).show();
    }

    public static void longToast(String msg) {
        Toast.makeText(sContext, msg, Toast.LENGTH_LONG).show();
    }

    public static void longToast(int msgId) {
        Toast.makeText(sContext, msgId, Toast.LENGTH_LONG).show();
    }

}
