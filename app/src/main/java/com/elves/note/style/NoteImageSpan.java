package com.elves.note.style;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.style.ImageSpan;

import androidx.annotation.NonNull;

public class NoteImageSpan extends ImageSpan {
    public NoteImageSpan(@NonNull Context context, @NonNull Bitmap bitmap) {
        super( context, bitmap );
    }
}
