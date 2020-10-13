package com.elejandria.app.elejandria.models;

/**
 * Created by Incubus on 09/01/2019.
 */

public class Link {

    final public static int TIPO_LINK_PDF = 5;
    final public static int TIPO_LINK_EPUB = 6;
    final public static int TIPO_LINK_DOCX = 9;

    int id;
    int link_category_id;
    String link;

    public static int getTipoLinkPdf() {
        return TIPO_LINK_PDF;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLink_category_id() {
        return link_category_id;
    }

    public void setLink_category_id(int link_category_id) {
        this.link_category_id = link_category_id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
