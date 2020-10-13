package com.elejandria.app.elejandria.interfaces;

import com.elejandria.app.elejandria.models.Author;
import com.elejandria.app.elejandria.models.Book;
import com.elejandria.app.elejandria.models.BookCollection;
import com.elejandria.app.elejandria.models.Category;
import com.elejandria.app.elejandria.models.ResultadoBusqueda;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Incubus on 01/08/2018.
 */

public interface ApiElejandria {

    @GET("/api/autores/{letter}")
    public Call< List<Author> > getAutores(@Path("letter") char letter);

    @GET("/api/libros/{letter}")
    public Call< List<Book> > getLibros(@Path("letter") char letter);


    @GET("/api/libros_mas_descargados/{cantidad}")
    public Call<List<Book>> getLibrosMasDescargados(@Path("cantidad") int cantidad);

    @GET("/api/libros_nuevos/{cantidad}")
    public Call<List<Book>> getLibrosNuevos(@Path("cantidad") int cantidad);

    @GET("/api/libros_ficcion/{cantidad}")
    public Call<List<Book>> getLibrosFiccion(@Path("cantidad") int cantidad);

    @GET("/api/libros_subcategoria/{id}")
    public Call<List<Book>> getLibrosSubcategoria(@Path("id") int idSubcategoria);

    @GET("/api/categorias")
    public Call<List<Category>> getCategorias();

    @GET("/api/libro/{id}")
    public Call<Book> getLibro(@Path("id") int id);


    @GET("/api/colecciones")
    public Call<List<BookCollection>> getColecciones();

    @GET("/api/busqueda/{cadena}")
    public Call<List<ResultadoBusqueda>> getResultadosBusqueda(@Path("cadena") String cadena);

    @GET("/api/tendenciasBusqueda")
    public Call<List<ResultadoBusqueda>> tendenciasBusqueda();

    @GET("/api/todosLibros")
    public Call<List<ResultadoBusqueda>> todosLibros();

    @GET("/api/subcategory_list/{id}")
    public Call<List<Category>> getListaSubcategorias(@Path("id") String idCategoria);



}
