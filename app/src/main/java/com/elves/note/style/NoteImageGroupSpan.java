package com.elves.note.style;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.annotation.NonNull;

public class NoteImageGroupSpan extends NoteImageSpan {
    public NoteImageGroupSpan(@NonNull Context context, @NonNull Bitmap bitmap) {
        super( context, bitmap );
    }
}
