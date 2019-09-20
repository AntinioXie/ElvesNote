package com.elves.note;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.elves.note.base.BaseActivity;
import com.elves.note.config.Global;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home );
        doDoubleClickAction=true;
    }

    public void test(View view) {
        createNewNote();
    }

    private void createNewNote() {
        Intent intent = new Intent();
        intent.setClass( HomeActivity.this,NewNoteActivity.class );
        startActivityForResult( intent, Global.REQUEST_CODE_NEW_NOTE);
    }
}
