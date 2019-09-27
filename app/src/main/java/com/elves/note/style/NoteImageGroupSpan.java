package com.elves.note.style;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class NoteImageGroupSpan extends NoteImageSpan {
    private List<NoteImageSpan> mSpans = new ArrayList<NoteImageSpan>();
    public NoteImageGroupSpan(@NonNull Context context, @NonNull Bitmap bitmap, Uri uri) {
        super( context, bitmap, uri );
    }
    public void addSpan(NoteImageSpan span){
        if(mSpans!=null) {
            mSpans.add( span );
        }
    }

    public void removeSpan(NoteImageSpan span){
        if(mSpans!=null&&!mSpans.contains( span )) {
            mSpans.remove( span );
        }
    }
}
