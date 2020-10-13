package com.elejandria.app.elejandria.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Incubus on 01/08/2018.
 */

public class Author {
    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("born_year")
    @Expose
    int born_year;

    @SerializedName("die_year")
    @Expose
    int die_year;

    String bio;
    String wikipedia;
    String picSM;
    String picLG;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBorn_year() {
        return born_year;
    }

    public void setBorn_year(int born_year) {
        this.born_year = born_year;
    }

    public int getDie_year() {
        return die_year;
    }

    public void setDie_year(int die_year) {
        this.die_year = die_year;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }

    public String getPicSM() {
        return picSM;
    }

    public void setPicSM(String picSM) {
        this.picSM = picSM;
    }

    public String getPicLG() {
        return picLG;
    }

    public void setPicLG(String picLG) {
        this.picLG = picLG;
    }
}
