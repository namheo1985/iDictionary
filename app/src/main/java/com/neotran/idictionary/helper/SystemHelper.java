package com.neotran.idictionary.helper;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.neotran.idictionary.R;

/**
 * Created by neotran on 12/28/15.
 */
public class SystemHelper {
    private static Handler mHANDLER;
    private static Activity mOnScreenActivity;

    public static Activity getOnScreenActivity() {
        return mOnScreenActivity;
    }
    public static void onScreen(Activity activity) {
        mOnScreenActivity = activity;
    }
    public static void recycleDelayed(Runnable runner) {
        if(mHANDLER != null) mHANDLER.removeCallbacks(runner);
        mHANDLER = null;
    }

    public static void postDelayed(Runnable runner, int delayedTime) {
        mHANDLER = new Handler();
        mHANDLER.postDelayed(runner, delayedTime);
    }

    public static String getText(int idSource) {
        if(mOnScreenActivity != null)
            if(mOnScreenActivity.getResources() != null)
                return mOnScreenActivity.getResources().getString(idSource);
        return "";
    }

    public static void toggleSoftKeyboardFromView(boolean openIt) {
        if(mOnScreenActivity != null) {
            try {
                if(openIt) {
                    ((InputMethodManager) mOnScreenActivity
                            .getSystemService(Context.INPUT_METHOD_SERVICE))
                            .toggleSoftInput(InputMethodManager.SHOW_FORCED,
                                    InputMethodManager.HIDE_IMPLICIT_ONLY);
                } else {
                    View view = mOnScreenActivity.getWindow().getCurrentFocus();
                    if(view != null)
                        ((InputMethodManager) mOnScreenActivity.getSystemService(Context.INPUT_METHOD_SERVICE))
                                .hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
                    else {
                        mOnScreenActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                    }
                }

            } catch (Exception e) {
            }
        }
    }
}
