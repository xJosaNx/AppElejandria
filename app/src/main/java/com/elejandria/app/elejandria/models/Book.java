package com.elejandria.app.elejandria.models;

import android.os.Build;
import android.text.Html;
import android.text.Spanned;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Incubus on 03/01/2019.
 */

public class Book {

    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("picSM")
    @Expose
    public String portadaSM;

    @SerializedName("picLG")
    @Expose
    public String portadaLG;


    @SerializedName("author")
    @Expose
    public Author author;

    @SerializedName("abstract")
    @Expose
    public String bookAbstract;


    @Expose
    public String nombreCategoria;

    @Expose
    public String nombreSubcategoria;


    @SerializedName("recomendaciones")
    @Expose
    public List<Book> recomendaciones;

    @SerializedName("otrosLibrosAutor")
    @Expose
    public List<Book> otrosLibrosAutor;

    @Expose
    public Link linkEpub;

    @Expose
    public Link linkPDF;

    public String url;

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

    public String getPortadaSM() {
        return portadaSM;
    }

    public void setPortadaSM(String portada) {
        this.portadaSM = portada;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getBookAbstract() {

        return bookAbstract != null ? bookAbstract : "";
    }

    public void setBookAbstract(String bookAbstract) {
        this.bookAbstract = bookAbstract;
    }

    public List<Book> getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(List<Book> recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public List<Book> getOtrosLibrosAutor() {
        return otrosLibrosAutor;
    }

    public void setOtrosLibrosAutor(List<Book> otrosLibrosAutor) {
        this.otrosLibrosAutor = otrosLibrosAutor;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getNombreSubcategoria() {
        return nombreSubcategoria;
    }

    public void setNombreSubcategoria(String nombreSubcategoria) {
        this.nombreSubcategoria = nombreSubcategoria;
    }

    public String getUrl() {
        return url;
    }

    public String getPortadaLG() {
        return portadaLG;
    }

    public void setPortadaLG(String portadaLG) {
        this.portadaLG = portadaLG;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Link getLinkEpub() {
        return linkEpub;
    }

    public Link getLinkPDF() {
        return linkPDF;
    }

    public void setLinkPDF(Link linkPDF) {
        this.linkPDF = linkPDF;
    }

    public void setLinkEpub(Link linkEpub) {
        this.linkEpub = linkEpub;
    }

    public boolean permiteLeerOnline()
    {
        return this.linkEpub != null;
    }

    public boolean permiteDescargar()
    {
        return this.linkPDF != null;
    }

    public Spanned formatAbstract()
    {
        if(this.bookAbstract != null){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                return Html.fromHtml(bookAbstract, Html.FROM_HTML_MODE_LEGACY);
            } else {
                return Html.fromHtml(bookAbstract);
            }
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                return Html.fromHtml("<p>Sin resumen disponible</p>", Html.FROM_HTML_MODE_LEGACY);
            }else
            {
                return Html.fromHtml("<p>Sin resumen disponible</p>");
            }
        }
    }
}
