package com.elves.note.base;

import android.content.Intent;
import android.view.KeyEvent;

import androidx.appcompat.app.AppCompatActivity;

import com.elves.note.R;
import com.elves.note.utils.ToastUtil;

public class BaseActivity extends AppCompatActivity {
    public boolean doDoubleClickAction = false;
    private long exitTime;

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
}
