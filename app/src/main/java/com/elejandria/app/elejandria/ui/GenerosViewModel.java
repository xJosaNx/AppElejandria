package com.elejandria.app.elejandria.ui;

import com.elejandria.app.elejandria.models.Author;
import com.elejandria.app.elejandria.models.Book;
import com.elejandria.app.elejandria.network.Repository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GenerosViewModel extends ViewModel {

    private MutableLiveData<List<Book>> booksFiccion, booksNoFiccion;
    private Repository repository;

    public LiveData<List<Book>> getLibrosFiccion(int cantidad) {

        if (booksFiccion == null) {
            booksFiccion = new MutableLiveData<List<Book>>();
            repository = Repository.getInstance();
            booksFiccion = repository.getLibrosFiccion(cantidad);

        }

        return booksFiccion;
    }

    public LiveData<List<Book>> getLibrosSubcategoria(int idSubcategoria) {

        if (booksFiccion == null) {
            booksFiccion = new MutableLiveData<List<Book>>();
            repository = Repository.getInstance();
            booksFiccion = repository.getLibrosSubcategoria(idSubcategoria);

        }

        return booksFiccion;
    }


}
