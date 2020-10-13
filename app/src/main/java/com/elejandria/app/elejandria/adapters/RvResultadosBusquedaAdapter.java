package com.elejandria.app.elejandria.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import com.elejandria.app.elejandria.R;
import com.elejandria.app.elejandria.helpers.BookFilter;
import com.elejandria.app.elejandria.helpers.ResultadoBusquedaFilter;
import com.elejandria.app.elejandria.models.Book;
import com.elejandria.app.elejandria.models.ResultadoBusqueda;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public class RvResultadosBusquedaAdapter  extends RecyclerView.Adapter<RvLibroAdapter.LibroViewHolder>  implements Filterable {


    public List<ResultadoBusqueda> libros, librosFiltro;
    Context context;

    ResultadoBusquedaFilter filter;


    public RvResultadosBusquedaAdapter(List<ResultadoBusqueda> libros, Context context) {

        this.libros = libros;
        this.librosFiltro = libros;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return libros.size();
    }


    @Override
    public RvLibroAdapter.LibroViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_collection_libros_libro, viewGroup, false);
        RvLibroAdapter.LibroViewHolder lvh = new RvLibroAdapter.LibroViewHolder(v);
        return lvh;
    }

    @Override
    public void onBindViewHolder(RvLibroAdapter.LibroViewHolder libroViewHolder, int i) {

        Picasso.get().load(libros.get(i).getPortadaLibroSM()).into(libroViewHolder.ivPortadaLibro);

        libroViewHolder.ivPortadaLibro.setScaleType(ImageView.ScaleType.CENTER_CROP);

        final ResultadoBusqueda libro = libros.get(i);

        libroViewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putInt("bookId", libro.getId());
                bundle.putString("titulo", libro.getTituloLibro());
                bundle.putString("portada", libro.getPortadaLibroSM());

                Navigation.findNavController(view).navigate(R.id.navigation_libro, bundle);

            }
        });

    }
    //RETURN FILTER OBJ
    @Override
    public Filter getFilter() {
        if(filter==null)
        {
            filter=new ResultadoBusquedaFilter(librosFiltro,this);
        }

        return filter;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}