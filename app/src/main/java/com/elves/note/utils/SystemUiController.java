package com.elves.note.utils;

import android.os.Build;
import android.view.View;
import android.view.Window;
import java.util.Arrays;

/**
 * Utility class to manage various window flags to control system UI.
 */
public class SystemUiController {

    // Various UI states in increasing order of priority
    public static final int UI_STATE_BASE_WINDOW = 0;

    public static final int FLAG_LIGHT_NAV = 1 << 0;
    public static final int FLAG_DARK_NAV = 1 << 1;
    public static final int FLAG_LIGHT_STATUS = 1 << 2;
    public static final int FLAG_DARK_STATUS = 1 << 3;

    private final Window mWindow;
    private final int[] mStates = new int[5];

    public SystemUiController(Window window) {
        mWindow = window;
    }

    public void updateUiState(int uiState, boolean isLight) {
        updateUiState(uiState, isLight
                ? (FLAG_LIGHT_NAV | FLAG_LIGHT_STATUS) : (FLAG_DARK_NAV | FLAG_DARK_STATUS));
    }

    public void updateUiState(int uiState, int flags) {
        if (mStates[uiState] == flags) {
            return;
        }
        mStates[uiState] = flags;

        int oldFlags = mWindow.getDecorView().getSystemUiVisibility();
        // Apply the state flags in priority order
        int newFlags = oldFlags;
        for (int stateFlag : mStates) {
            if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
                if ((stateFlag & FLAG_LIGHT_NAV) != 0) {
                    newFlags |= View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
                } else if ((stateFlag & FLAG_DARK_NAV) != 0) {
                    newFlags &= ~(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
                }
            }

            if ((stateFlag & FLAG_LIGHT_STATUS) != 0) {
                newFlags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else if ((stateFlag & FLAG_DARK_STATUS) != 0) {
                newFlags &= ~(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
        if (newFlags != oldFlags) {
            mWindow.getDecorView().setSystemUiVisibility(newFlags);
        }
    }


    @Override
    public String toString() {
        return "mStates=" + Arrays.toString(mStates);
    }
}

