package com.elves.note;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.elves.note.base.BaseActivity;
import com.elves.note.utils.Utils;
import com.elves.note.widget.RichEditText;

public class NewNoteActivity extends BaseActivity {
    Toolbar toolbar;
    RichEditText richEditText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_new_note );
        richEditText = findViewById( R.id.content);
        initView(this);
        initAction( this );
    }

    @Override
    protected void initAction(BaseActivity baseActivity) {
        toolbar.setNavigationOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        } );
    }

    @Override
    public void initView(BaseActivity baseActivity) {
        toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayShowTitleEnabled( false );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.new_note_menu,menu );
        return super.onCreateOptionsMenu( menu );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.i("test","12233:requestCode = "+requestCode+" data = "+data);
        if (requestCode == 0&&data!=null) {
            Uri originalUri = data.getData(); // 获得图片的uri
            String path = Utils.getRealPathFromUri(this,originalUri);
            richEditText.insertImage(path);
        }
        super.onActivityResult( requestCode, resultCode, data );
    }

    public void onTakePicClick(View view) {
        Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
        getAlbum.setType("image/*");
        startActivityForResult(getAlbum, 0);
    }
}
