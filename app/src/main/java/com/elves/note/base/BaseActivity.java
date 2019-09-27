package com.elves.note.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.elves.note.R;
import com.elves.note.utils.SystemUiController;
import com.elves.note.utils.ToastUtil;

import java.lang.reflect.Method;

public abstract class BaseActivity extends AppCompatActivity {
    public boolean doDoubleClickAction = false;
    private long exitTime;
    private SystemUiController mSystemUiController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        getSystemUiController().updateUiState( SystemUiController.UI_STATE_BASE_WINDOW ,true);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if(doDoubleClickAction) {
                    if (System.currentTimeMillis() - exitTime > 3000) {
                        ToastUtil.shortToast( R.string.click_again_to_exit );
                        exitTime = System.currentTimeMillis();
                        return true;
                    }
                    gotoHome();
                    return true;
                }
                break;
            case KeyEvent.KEYCODE_MENU:
                return true;
            default: {

            }
        }

        return super.onKeyDown(keyCode, event);
    }


    private void gotoHome() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public SystemUiController getSystemUiController() {
        if (mSystemUiController == null) {
            mSystemUiController = new SystemUiController(getWindow());
        }
        return mSystemUiController;
    }

    protected abstract void initAction(BaseActivity baseActivity);

    public abstract void initView(BaseActivity baseActivity);
}
