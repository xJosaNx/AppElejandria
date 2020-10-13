package com.elejandria.app.elejandria.ui;

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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.elejandria.app.elejandria.R;
import com.elejandria.app.elejandria.adapters.RvCollectionAdapter;
import com.elejandria.app.elejandria.adapters.RvResultadosBusquedaAdapter;
import com.elejandria.app.elejandria.adapters.RvResultadosBusquedaPopularesAdapter;
import com.elejandria.app.elejandria.models.Book;
import com.elejandria.app.elejandria.models.BookCollection;
import com.elejandria.app.elejandria.models.ResultadoBusqueda;

import java.util.List;

public class BusquedaFragment extends Fragment implements SearchView.OnQueryTextListener {

    private BusquedaViewModel busquedaViewModel;

    private RecyclerView rvTendenciasBusqueda, rvTodosLibros;
    private ProgressBar pgCargando;

    private LinearLayout llBusquedasPopulares;
    private RvResultadosBusquedaPopularesAdapter rvTendenciasBusquedaAdapter;
    private RvResultadosBusquedaAdapter rvTodosLibrosAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.busqueda_fragment, container, false);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        busquedaViewModel = ViewModelProviders.of(this).get(BusquedaViewModel.class);

    }


    @Override
    public void onResume(){
        super.onResume();
        getActivity().setTitle("Búsqueda");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pgCargando = getActivity().findViewById(R.id.pgCargando);

        rvTendenciasBusqueda  = getActivity().findViewById(R.id.rvTendenciasBusqueda);
        rvTendenciasBusqueda.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvTendenciasBusqueda.setLayoutManager(llm);

        busquedaViewModel.tendenciasBusqueda().observe(this, new Observer<List<ResultadoBusqueda>>() {
            @Override
            public void onChanged(@Nullable List<ResultadoBusqueda> books) {
                rvTendenciasBusquedaAdapter = new RvResultadosBusquedaPopularesAdapter(books, getContext());
                rvTendenciasBusqueda.setAdapter(rvTendenciasBusquedaAdapter);

                rvTendenciasBusqueda.setVisibility(View.VISIBLE);
                pgCargando.setVisibility(View.GONE);
            }
        });

        llBusquedasPopulares = getActivity().findViewById(R.id.llBusquedasPopulares);

        rvTodosLibros  = getActivity().findViewById(R.id.rvTodosLibros);

        GridLayoutManager llmTodosLibros = new GridLayoutManager(getContext(), 3);
        rvTodosLibros.setLayoutManager(llmTodosLibros);

        busquedaViewModel.todosLibros().observe(this, new Observer<List<ResultadoBusqueda>>() {
            @Override
            public void onChanged(@Nullable List<ResultadoBusqueda> books) {
                rvTodosLibrosAdapter = new RvResultadosBusquedaAdapter(books, getContext());
                rvTodosLibros.setAdapter(rvTodosLibrosAdapter);

            }
        });


        SearchView svBusquedaLibros = getActivity().findViewById(R.id.svBusquedaLibros);
        svBusquedaLibros.setOnQueryTextListener(this);
    }



    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(newText.equals(""))
        {
            llBusquedasPopulares.setVisibility(View.VISIBLE);
            rvTodosLibros.setVisibility(View.GONE);
        }
        else
        {
            String text = newText;
            rvTodosLibrosAdapter.getFilter().filter(newText);
            rvTodosLibros.setVisibility(View.VISIBLE);
            llBusquedasPopulares.setVisibility(View.GONE);

        }

        return false;
    }
}
