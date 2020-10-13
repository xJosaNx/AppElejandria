package com.elejandria.app.elejandria.network;

import android.util.Log;

import com.elejandria.app.elejandria.R;
import com.elejandria.app.elejandria.interfaces.ApiElejandria;
import com.elejandria.app.elejandria.models.Author;
import com.elejandria.app.elejandria.models.Book;
import com.elejandria.app.elejandria.models.BookCollection;
import com.elejandria.app.elejandria.models.ResultadoBusqueda;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private static Repository repository;

    public static Repository getInstance(){
        if (repository == null){
            repository = new Repository();
        }
        return repository;
    }

    private ApiElejandria apiElejandria;

    public Repository(){
        apiElejandria = NetworkService.getInstance().getJSONApi();
    }



    public MutableLiveData<List<BookCollection>> getCollections(){

        final MutableLiveData<List<BookCollection>> collections = new MutableLiveData<>();

        apiElejandria.getColecciones().enqueue(new Callback<List<BookCollection>>() {
            @Override
            public void onResponse(Call<List<BookCollection>> call,
                                   Response<List<BookCollection>> response) {
                if (response.isSuccessful()){
                    collections.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<BookCollection>> call, Throwable t) {
                collections.setValue(null);
            }
        });
        return collections;
    }


    public MutableLiveData<List<ResultadoBusqueda>> getResultadosBusqueda(String busqueda){

        final MutableLiveData<List<ResultadoBusqueda>> books = new MutableLiveData<>();

        apiElejandria.getResultadosBusqueda(busqueda).enqueue(new Callback<List<ResultadoBusqueda>>() {
            @Override
            public void onResponse(Call<List<ResultadoBusqueda>> call,
                                   Response<List<ResultadoBusqueda>> response) {
                if (response.isSuccessful()){
                    books.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ResultadoBusqueda>> call, Throwable t) {
                books.setValue(null);
            }
        });
        return books;
    }

    public MutableLiveData<List<ResultadoBusqueda>> todosLibros(){

        final MutableLiveData<List<ResultadoBusqueda>> books = new MutableLiveData<>();

        apiElejandria.todosLibros().enqueue(new Callback<List<ResultadoBusqueda>>() {
            @Override
            public void onResponse(Call<List<ResultadoBusqueda>> call,
                                   Response<List<ResultadoBusqueda>> response) {
                if (response.isSuccessful()){
                    books.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ResultadoBusqueda>> call, Throwable t) {
                books.setValue(null);
            }
        });
        return books;
    }


    public MutableLiveData<List<ResultadoBusqueda>> tendenciasBusqueda(){

        final MutableLiveData<List<ResultadoBusqueda>> books = new MutableLiveData<>();

        apiElejandria.tendenciasBusqueda().enqueue(new Callback<List<ResultadoBusqueda>>() {
            @Override
            public void onResponse(Call<List<ResultadoBusqueda>> call,
                                   Response<List<ResultadoBusqueda>> response) {
                if (response.isSuccessful()){
                    books.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ResultadoBusqueda>> call, Throwable t) {
                Log.e("Error",t.toString());
            }
        });

        return books;
    }


    public MutableLiveData<Book> getLibro(int id){

        final MutableLiveData<Book> book = new MutableLiveData<>();

        apiElejandria.getLibro(id).enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call,
                                   Response<Book> response) {
                if (response.isSuccessful()){
                    book.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Log.e("Error",t.toString());
            }
        });

        return book;
    }

    public MutableLiveData<List<Author>> getAuthors(char letter){

        final MutableLiveData<List<Author>> authors = new MutableLiveData<>();

        apiElejandria.getAutores(letter).enqueue(new Callback<List<Author>>() {
            @Override
            public void onResponse(Call<List<Author>> call,
                                   Response<List<Author>> response) {
                if (response.isSuccessful()){
                    authors.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Author>> call, Throwable t) {
                Log.e("Error",t.toString());
            }
        });

        return authors;
    }



    public MutableLiveData<List<Book>> getLibros(char letter){

        final MutableLiveData<List<Book>> libros = new MutableLiveData<>();

        apiElejandria.getLibros(letter).enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call,
                                   Response<List<Book>> response) {
                if (response.isSuccessful()){
                    libros.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Log.e("Error",t.toString());
            }
        });

        return libros;
    }


    public MutableLiveData<List<Book>> getLibrosFiccion(int cantidad){

        final MutableLiveData<List<Book>> libros = new MutableLiveData<>();

        apiElejandria.getLibrosFiccion(cantidad).enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call,
                                   Response<List<Book>> response) {
                if (response.isSuccessful()){
                    libros.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Log.e("Error",t.toString());
            }
        });

        return libros;
    }

    public MutableLiveData<List<Book>> getLibrosSubcategoria(int idSubcategoria){

        final MutableLiveData<List<Book>> libros = new MutableLiveData<>();

        apiElejandria.getLibrosSubcategoria(idSubcategoria).enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call,
                                   Response<List<Book>> response) {
                if (response.isSuccessful()){
                    libros.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Log.e("Error",t.toString());
            }
        });

        return libros;
    }


}
