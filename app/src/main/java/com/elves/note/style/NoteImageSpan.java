package com.elves.note.style;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.text.style.ImageSpan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NoteImageSpan extends ImageSpan {
    Uri mUri;
    public NoteImageSpan(@NonNull Context context, @NonNull Bitmap bitmap, Uri uri) {
        super( context, bitmap );
        mUri = uri;
    }

    @Nullable
    @Override
    public String getSource() {
        return mUri!=null?mUri.toString():"";
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
        super.draw( canvas, text, start, end, x, top, y, bottom, paint );
    }
}
