package com.elejandria.app.elejandria.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elejandria.app.elejandria.R;
import com.elejandria.app.elejandria.adapters.RvCollectionAdapter;
import com.elejandria.app.elejandria.models.Book;
import com.elejandria.app.elejandria.models.BookCollection;
import com.elejandria.app.elejandria.network.Repository;

import java.util.Arrays;
import java.util.List;

public class InicioFragment extends Fragment {

    private InicioViewModel homeViewModel;

    private RvCollectionAdapter adapter;
    private ProgressBar pgInicio;
    private RecyclerView rv;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.inicio_fragment, container, false);

        return root;
    }

    @Override
    public void onResume(){
        super.onResume();
        getActivity().setTitle("Inicio");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel = ViewModelProviders.of(this).get(InicioViewModel.class);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pgInicio = getActivity().findViewById(R.id.pgInicio);

        rv = getActivity().findViewById(R.id.rvInicioColecciones);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(llm);

        homeViewModel.getCollections().observe(this, new Observer<List<BookCollection>>() {
            @Override
            public void onChanged(@Nullable List<BookCollection> collections) {
                adapter = new RvCollectionAdapter(homeViewModel.getCollections().getValue(), getContext());
                rv.setAdapter(adapter);
                pgInicio.setVisibility(View.GONE);
                rv.setVisibility(View.VISIBLE);
            }
        });

        AppCompatButton btnGeneros = getActivity().findViewById(R.id.btnInicioGeneros);
        btnGeneros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigate(R.id.navigation_generos);
            }
        });

        AppCompatButton btnInicioAutores = getActivity().findViewById(R.id.btnInicioAutores);
        btnInicioAutores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigate(R.id.navigation_autores);
            }
        });


        AppCompatButton btnInicioLibros = getActivity().findViewById(R.id.btnInicioLibros);
        btnInicioLibros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigate(R.id.navigation_libros);
            }
        });



    }
}

/*

 NetworkService.getInstance()
                .getJSONApi()
                .getLibrosMasDescargados(6)
                .enqueue(new Callback<List<Book>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Book>> call, @NonNull Response<List<Book>> response) {
                        librosMasDescargados = response.body();
                        adapter = new RvLibroAdapter(librosMasDescargados, getApplicationContext());
                        rv.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Book>> call, @NonNull Throwable t) {

                        t.printStackTrace();
                    }
                });

 */