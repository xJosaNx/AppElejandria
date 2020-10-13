package com.elejandria.app.elejandria.ui;

import com.elejandria.app.elejandria.models.Author;
import com.elejandria.app.elejandria.models.Book;
import com.elejandria.app.elejandria.network.Repository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LibrosViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private MutableLiveData<List<Book>> libros;
    private Repository repository;

    public LiveData<List<Book>> getLibros(char letra, boolean recargar) {

        if (libros == null || recargar) {
            libros = new MutableLiveData<List<Book>>();
            repository = Repository.getInstance();
            libros = repository.getLibros(letra);
        }

        return libros;
    }
}
