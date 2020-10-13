package com.elejandria.app.elejandria.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookCollection {

    @Expose
    public String name;


    @SerializedName("books")
    @Expose
    public List<Book> books;
}
