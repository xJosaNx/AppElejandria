package com.elejandria.app.elejandria.ui;

import com.elejandria.app.elejandria.models.Book;
import com.elejandria.app.elejandria.models.BookCollection;
import com.elejandria.app.elejandria.models.ResultadoBusqueda;
import com.elejandria.app.elejandria.network.Repository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BusquedaViewModel extends ViewModel {

    private MutableLiveData<List<ResultadoBusqueda>> books;
    private MutableLiveData<List<ResultadoBusqueda>> booksTendenciasBusqueda;
    private Repository repository;



    public MutableLiveData<List<ResultadoBusqueda>> tendenciasBusqueda() {

        if (booksTendenciasBusqueda == null) {
            booksTendenciasBusqueda = new MutableLiveData<>();
            repository = Repository.getInstance();
            booksTendenciasBusqueda = repository.tendenciasBusqueda();
        }

        return booksTendenciasBusqueda;
    }

    public MutableLiveData<List<ResultadoBusqueda>> todosLibros() {

        if (books == null) {
            books = new MutableLiveData<>();
            repository = Repository.getInstance();
            books = repository.todosLibros();
        }

        return books;
    }



}
