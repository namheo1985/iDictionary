package com.neotran.idictionary.helper;

import android.os.Handler;

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

}
