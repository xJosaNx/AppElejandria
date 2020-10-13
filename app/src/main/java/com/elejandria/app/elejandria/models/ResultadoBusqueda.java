package com.elejandria.app.elejandria.models;

public class ResultadoBusqueda {
    int id;
    String tituloLibro;
    String portadaLibroSM;
    String nombreAutor;

    public String getTituloLibro() {
        return tituloLibro;
    }

    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
    }

    public String getPortadaLibroSM() {
        return portadaLibroSM;
    }

    public void setPortadaLibroSM(String portadaLibroSM) {
        this.portadaLibroSM = portadaLibroSM;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
