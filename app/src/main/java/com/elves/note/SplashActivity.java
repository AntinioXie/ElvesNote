package com.elves.note;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.elves.note.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash );
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler( new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                goHomeActivity();
                return false;
            }
        } ).sendEmptyMessageDelayed( 0, 1200 );
    }

    private void goHomeActivity() {
        Intent intent = new Intent( this, HomeActivity.class );
        startActivity( intent );
        finish();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged( hasFocus );
        int currentApiVersion = Build.VERSION.SDK_INT;
        if (currentApiVersion >= 19) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY );
        } else if (currentApiVersion > 14) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_HIDE_NAVIGATION );
        }
    }
}
