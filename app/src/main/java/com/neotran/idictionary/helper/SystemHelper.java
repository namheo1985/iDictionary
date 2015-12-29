package com.neotran.idictionary.helper;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by neotran on 12/28/15.
 */
public class SystemHelper {
    private static Handler handler;

    public static void recycleDelayed(Runnable runner) {
        if(handler != null) handler.removeCallbacks(runner);
        handler = null;
    }

    public static void postDelayed(Runnable runner, int delayedTime) {
        handler = new Handler();
        handler.postDelayed(runner, delayedTime);
    }
    public static void toggleSoftKeyboardFromView(final Activity context, boolean openIt) {
        if(context != null) {
            try {
                if(openIt) {
                    ((InputMethodManager) context
                            .getSystemService(Context.INPUT_METHOD_SERVICE))
                            .toggleSoftInput(InputMethodManager.SHOW_FORCED,
                                    InputMethodManager.HIDE_IMPLICIT_ONLY);
                } else {
                    View view = context.getWindow().getCurrentFocus();
                    if(view != null)
                        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE))
                                .hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
                    else {
                        context.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                    }
                }

            } catch (Exception e) {
            }
        }
    }
}
