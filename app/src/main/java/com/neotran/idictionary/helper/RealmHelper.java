package com.neotran.idictionary.helper;

import android.content.Context;
import android.content.res.AssetManager;

import com.neotran.idictionary.model.Meaning;
import com.neotran.idictionary.model.Word;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import io.realm.Realm;

/**
 * Created by neotran on 12/28/15.
 */
public class RealmHelper {
    private static InputStream readTextFile(Context context, String fileName) {
        try {
            AssetManager am = context.getAssets();
            InputStream is = am.open(fileName);
            return is;
        } catch (Exception e) {
            return null;
        }
    }

    public static void createMeaningsDatabase(Context context) {
        InputStream inputStream = readTextFile(context, "a.html");
        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        int i = 0;
        Realm realm = Realm.getInstance(context);
        realm.beginTransaction();
        try {
            line = r.readLine();
            while (line != null) {
                Meaning mean = new Meaning();
                mean.setId(i);
                String[] value = line.split("</b>");
                if(value.length >=2) {
                    value[0] = value[0].replace("<p><b>", "");
                    mean.setValue(value[0].toLowerCase());
                    mean.setMeaning(line);
                }
                realm.copyToRealm(mean);
                i++;
                line = r.readLine();
            }
        } catch (Exception e) {

        }
        realm.commitTransaction();
    }

    public static void createWordsDatabase(Context context) {
        InputStream inputStream = readTextFile(context, "words.txt");
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
        } catch (Exception e) {

        }
        realm.commitTransaction();
    }
}
