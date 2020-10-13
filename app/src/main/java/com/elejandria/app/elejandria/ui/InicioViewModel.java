package com.elejandria.app.elejandria.ui;

import android.app.Application;

import com.elejandria.app.elejandria.models.BookCollection;
import com.elejandria.app.elejandria.network.Repository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InicioViewModel extends ViewModel {

    private MutableLiveData<List<BookCollection>> bookCollections;
    private Repository repository;


    public LiveData<List<BookCollection>> getCollections() {

        if (bookCollections == null) {
            bookCollections = new MutableLiveData<List<BookCollection>>();
            repository = Repository.getInstance();
            bookCollections = repository.getCollections();

        }

        return bookCollections;
    }

}