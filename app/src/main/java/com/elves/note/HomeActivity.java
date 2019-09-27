package com.elves.note;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elves.note.adapter.HomeRecyclerViewAdapter;
import com.elves.note.base.BaseActivity;
import com.elves.note.config.Global;
import com.elves.note.utils.CoordinatorUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends BaseActivity implements View.OnClickListener, AppBarLayout.OnOffsetChangedListener {
    FloatingActionButton fabNewNoteBt;
    Toolbar toolbar;
    RecyclerView content;
    AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home );
        initView(this);
        initAction(this);
        doDoubleClickAction=true;
    }

    @Override
    protected void initAction(BaseActivity baseActivity) {
        fabNewNoteBt.setOnClickListener( this );
    }

    @Override
    public void initView(BaseActivity baseActivity) {
        fabNewNoteBt = findViewById( R.id.fab_new_note );
        toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayShowTitleEnabled( false );

        content = findViewById( R.id.recyclerView );
        LinearLayoutManager layoutManager = new LinearLayoutManager( this );
        content.setLayoutManager( layoutManager );
        content.setAdapter( getAdapter() );

        // appBarLayout = findViewById( R.id.appbar_layout );
        //appBarLayout.addOnOffsetChangedListener( this);
    }

    private RecyclerView.Adapter getAdapter() {
        HomeRecyclerViewAdapter adapter = new HomeRecyclerViewAdapter();
        for(int i = 0 ;i<200;i++){
            adapter.addData( "Item "+i );
        }
        return  adapter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu( menu );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_new_note:
                createNewNote();
                break;
                default:
                    break;
        }
    }

    private void createNewNote() {
        Intent intent = new Intent();
        intent.setClass( HomeActivity.this,NewNoteActivity.class );
        startActivityForResult( intent, Global.REQUEST_CODE_NEW_NOTE);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            Log.i("test","verticalOffset = "+verticalOffset);
            //toolbar.setBackgroundColor( CoordinatorUtils.changeAlpha(Color.YELLOW, Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange()));

    }
}
