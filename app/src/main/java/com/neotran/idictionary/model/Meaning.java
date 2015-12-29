package com.neotran.idictionary.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by neotran on 12/28/15.
 */
public class Meaning extends RealmObject {
    @PrimaryKey
    private int id;
    private String value;
    private String meaning;

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

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String v) {
        meaning = v;
    }
}
