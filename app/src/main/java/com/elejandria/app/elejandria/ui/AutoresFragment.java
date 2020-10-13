package com.elejandria.app.elejandria.ui;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.elejandria.app.elejandria.R;
import com.elejandria.app.elejandria.adapters.RvAuthorAdapter;
import com.elejandria.app.elejandria.adapters.RvCollectionAdapter;
import com.elejandria.app.elejandria.models.Author;
import com.elejandria.app.elejandria.models.BookCollection;

import java.util.List;

public class AutoresFragment extends Fragment {

    private AutoresViewModel mViewModel;
    private RecyclerView rv;

    private RvAuthorAdapter adapter;
    private ProgressBar pgAutores;

    Spinner spinnerAutoresLetra;
    TextView tvAutoresTitulo;

    public static AutoresFragment newInstance() {
        return new AutoresFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.autores_fragment, container, false);
    }



    @Override
    public void onResume(){
        super.onResume();
        getActivity().setTitle("Inicio");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AutoresViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        pgAutores = getActivity().findViewById(R.id.pgAutores);
        rv = getActivity().findViewById(R.id.rvAutoresLista);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(llm);

        recargarAutores('A');

        tvAutoresTitulo = getActivity().findViewById(R.id.tvAutoresTitulo);

        spinnerAutoresLetra = getActivity().findViewById(R.id.spinnerAutoresLetra);
        spinnerAutoresLetra.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String text = spinnerAutoresLetra.getSelectedItem().toString();

                recargarAutores(text.charAt(text.length() - 1));
                tvAutoresTitulo.setText("Autores que comienzan por "+ text.charAt(text.length() - 1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        String[] listado = getResources().getStringArray(R.array.listado_alfabetico);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),R.layout.spinner_item, listado);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinnerAutoresLetra.setAdapter(dataAdapter);

        SearchView svAutores = getActivity().findViewById(R.id.svAutores);
        svAutores.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

    public void recargarAutores(char caracter)
    {
        pgAutores.setVisibility(View.VISIBLE);
        rv.setVisibility(View.GONE);

        mViewModel.getAutores(caracter, true).observe(this, new Observer<List<Author>>() {
            @Override
            public void onChanged(@Nullable List<Author> authors) {
                adapter = new RvAuthorAdapter(authors, getContext());
                rv.setAdapter(adapter);
                pgAutores.setVisibility(View.GONE);
                rv.setVisibility(View.VISIBLE);
            }
        });
    }

}
