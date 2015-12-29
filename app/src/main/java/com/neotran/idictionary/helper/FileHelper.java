package com.neotran.idictionary.helper;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.neotran.idictionary.model.Word;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import io.realm.Realm;

/**
 * Created by neotran on 12/28/15.
 */
public class FileHelper {
    private static InputStream readTextFile(Context context, String fileName) {
        try {
            AssetManager am = context.getAssets();
            InputStream is = am.open(fileName);
            return is;
        } catch (Exception e) {
            return null;
        }
    }

    public static void createWordsRealmFromTextFile(final Context context, String fileName) {
        InputStream inputStream = readTextFile(context, fileName);
        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        int i = 0;
        Realm realm = Realm.getInstance(context);
        realm.beginTransaction();

        try {
            line = r.readLine();
            while (line != null) {
                Word word = new Word();
                word.setId(i);
                word.setValue(line.trim());
                realm.copyToRealm(word);
                line = r.readLine();
                i++;
            }
            realm.commitTransaction();
        } catch (Exception e) {

        }
    }
}
