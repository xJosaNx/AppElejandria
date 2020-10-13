package com.elejandria.app.elejandria.ui;

import com.elejandria.app.elejandria.models.Book;
import com.elejandria.app.elejandria.models.BookCollection;
import com.elejandria.app.elejandria.network.Repository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LibroViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<Book> book;
    private Repository repository;


    public LiveData<Book> getBook()
    {
        return book;
    }

    public LiveData<Book> getBook(int id) {

        if (book == null) {
            book = new MutableLiveData<Book>();
            repository = Repository.getInstance();
            book = repository.getLibro(id);
        }

        return book;
    }
}
