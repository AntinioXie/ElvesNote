package com.elves.note;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.elves.note.base.BaseActivity;
import com.elves.note.widget.RichEditText;

public class NewNoteActivity extends BaseActivity {
    boolean isBold = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_new_note );

        View view = findViewById( R.id.note_menu );
        final RichEditText editText = findViewById( R.id.content );
        view.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isBold=!isBold;
                editText.setStrikethrough( !isBold );
            }
        } );
    }
}
