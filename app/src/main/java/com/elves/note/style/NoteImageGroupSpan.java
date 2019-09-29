package com.elves.note.style;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class NoteImageGroupSpan extends NoteImageSpan {
    private List<NoteImageSpan> mSpans = new ArrayList<NoteImageSpan>();
    public NoteImageGroupSpan(@NonNull Context context, @NonNull Bitmap bitmap, Uri uri) {
        super( context, bitmap, uri );
    }
    public void addSubSpan(NoteImageSpan span){
        if(mSpans!=null) {
            mSpans.add( span );
        }
    }

    public void removeSubSpan(NoteImageSpan span){
        if(mSpans!=null&&!mSpans.contains( span )) {
            mSpans.remove( span );
        }
    }

    public int getSubSpanSzie(){
        return mSpans.size();
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
        super.draw( canvas, text, start, end, x, top, y, bottom, paint );
    }
}
