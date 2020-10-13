package com.elejandria.app.elejandria.ui;

import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import androidx.appcompat.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.elejandria.app.elejandria.R;
import com.elejandria.app.elejandria.adapters.RvAuthorAdapter;
import com.elejandria.app.elejandria.adapters.RvLibroAdapter;
import com.elejandria.app.elejandria.models.Author;
import com.elejandria.app.elejandria.models.Book;

import java.util.List;

public class LibrosFragment extends Fragment {

    private LibrosViewModel mViewModel;
    private RecyclerView rv;
    private TextView tvLibrosTitulo;

    private RvLibroAdapter adapter;
    ProgressBar pgLibros;
    Spinner spinnerLibrosLetra;

    public static LibrosFragment newInstance() {
        return new LibrosFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.libros_fragment, container, false);
    }


    @Override
    public void onResume(){
        super.onResume();
        getActivity().setTitle("Inicio");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LibrosViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        pgLibros = getActivity().findViewById(R.id.pgLibros);
        tvLibrosTitulo = getActivity().findViewById(R.id.tvLibrosTitulo);

        rv = getActivity().findViewById(R.id.rvLibrosLista);
        rv.setHasFixedSize(true);

        GridLayoutManager llm = new GridLayoutManager(getContext(), 3);
        rv.setLayoutManager(llm);

        recargarLibros('A');

        spinnerLibrosLetra = getActivity().findViewById(R.id.spinnerLibrosLetra);
        spinnerLibrosLetra.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String text = spinnerLibrosLetra.getSelectedItem().toString();



                recargarLibros(text.charAt(text.length() - 1));
                tvLibrosTitulo.setText("Libros que comienzan por "+ text.charAt(text.length() - 1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        String[] listado = getResources().getStringArray(R.array.listado_alfabetico);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),R.layout.spinner_item, listado);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinnerLibrosLetra.setAdapter(dataAdapter);

        SearchView svLibros = getActivity().findViewById(R.id.svLibros);
        svLibros.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                //FILTER AS YOU TYPE
                adapter.getFilter().filter(query);
                return false;
            }
        });


    }

    public void recargarLibros(char caracter)
    {
        pgLibros.setVisibility(View.VISIBLE);
        rv.setVisibility(View.GONE);

        mViewModel.getLibros(caracter, true).observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(@Nullable List<Book> books) {
                adapter = new RvLibroAdapter(books, getContext());
                rv.setAdapter(adapter);
                pgLibros.setVisibility(View.GONE);
                rv.setVisibility(View.VISIBLE);
            }
        });
    }
}
