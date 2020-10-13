package com.elejandria.app.elejandria.ui;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.elejandria.app.elejandria.R;
import com.elejandria.app.elejandria.adapters.RvAuthorAdapter;
import com.elejandria.app.elejandria.adapters.RvLibroAdapter;
import com.elejandria.app.elejandria.models.Author;
import com.elejandria.app.elejandria.models.Book;

import java.util.List;

public class GenerosFragment extends Fragment {

    private GenerosViewModel generosViewModel;
    private ProgressBar pgLibros;
    private RecyclerView rvLibrosGenero;
    private RvLibroAdapter adapterLibros;

    private Spinner spinnerCategoria,spinnerSubcategoriasNoFiccion,spinnerSubcategoriasFiccion;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        generosViewModel =
                ViewModelProviders.of(this).get(GenerosViewModel.class);
        View root = inflater.inflate(R.layout.generos_fragment, container, false);

        return root;
    }


    public static GenerosFragment newInstance() {
        return new GenerosFragment();
    }


    @Override
    public void onResume(){
        super.onResume();
        getActivity().setTitle("Inicio");
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        pgLibros = getActivity().findViewById(R.id.pgLibros);

        rvLibrosGenero = getActivity().findViewById(R.id.rvLibrosGenero);
        rvLibrosGenero.setHasFixedSize(true);

        GridLayoutManager llm = new GridLayoutManager(getContext(), 3);
        rvLibrosGenero.setLayoutManager(llm);

        cargarLibros(120);

        spinnerCategoria = getActivity().findViewById(R.id.spinnerCategoria);
        String[] listado = getResources().getStringArray(R.array.categorias);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),R.layout.spinner_item, listado);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinnerCategoria.setAdapter(dataAdapter);


        spinnerSubcategoriasFiccion = getActivity().findViewById(R.id.spinnerSubcategoriasFiccion);
        spinnerSubcategoriasNoFiccion = getActivity().findViewById(R.id.spinnerSubcategoriasNoFiccion);


    }


    public void cargarLibros(int idSubcategoria)
    {
        rvLibrosGenero.setVisibility(View.GONE);
        pgLibros.setVisibility(View.VISIBLE);

        generosViewModel.getLibrosSubcategoria(idSubcategoria).observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(@Nullable List<Book> books) {
                adapterLibros = new RvLibroAdapter(books, getContext());
                rvLibrosGenero.setAdapter(adapterLibros);
                pgLibros.setVisibility(View.GONE);
                rvLibrosGenero.setVisibility(View.VISIBLE);
            }
        });
    }
}