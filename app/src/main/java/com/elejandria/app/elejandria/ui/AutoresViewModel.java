package com.elejandria.app.elejandria.ui;

import com.elejandria.app.elejandria.models.Author;
import com.elejandria.app.elejandria.models.BookCollection;
import com.elejandria.app.elejandria.network.Repository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AutoresViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private MutableLiveData<List<Author>> authors;
    private Repository repository;

    public LiveData<List<Author>> getAutores(char letra, boolean recargar) {

        if (authors == null || recargar) {
            authors = new MutableLiveData<List<Author>>();
            repository = Repository.getInstance();
            authors = repository.getAuthors(letra);

        }

        return authors;
    }
}
