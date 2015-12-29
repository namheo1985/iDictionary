package com.neotran.idictionary.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by neotran on 12/28/15.
 */
public class Word extends RealmObject {
    @PrimaryKey
    private int id;
    private String value;

    public int getId() {
        return id;
    }

    public void setId(int index) {
        id = index;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String v) {
        value = v;
    }

}
