package com.neotran.idictionary.helper;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by neotran on 12/28/15.
 */
public class Noticer {
    private static Toast mToast;

    public static void clearToast() {
        if(mToast != null)
            mToast.cancel();

    }

    public static Toast makeToast(String text, int gravity, boolean isKeyboardVisible) {
        Activity onScreenActivity = SystemHelper.getOnScreenActivity();
        try {
            if(mToast != null) {
                clearToast();
            }
            mToast = Toast.makeText(onScreenActivity, text, Toast.LENGTH_SHORT);
            int yPos = isKeyboardVisible ? -150 : 0;
            mToast.setGravity(gravity, 0, yPos);
            mToast.show();
            return mToast;
        } catch (Exception e) {
        }
        return null;
    }

    public static Toast makeToast(String text, int gravity) {
        return makeToast(text, gravity, Toast.LENGTH_LONG);
    }

    public static Toast makeToast(String text, int gravity, int duration) {
        Activity onScreenActivity = SystemHelper.getOnScreenActivity();
        try {
            if(mToast != null) {
                clearToast();
            }
            mToast = Toast.makeText(onScreenActivity, text, Toast.LENGTH_SHORT);
            mToast.setDuration(duration);
            mToast.setGravity(gravity, 0, 0);
            mToast.show();
            return mToast;
        } catch (Exception e) {
        }
        return null;
    }

    public static Toast makeToast(String text) {
        return makeToast(text, Gravity.CENTER);
    }
}
