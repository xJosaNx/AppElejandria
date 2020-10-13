package com.elejandria.app.elejandria.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import com.elejandria.app.elejandria.R;
import com.elejandria.app.elejandria.helpers.BookFilter;
import com.elejandria.app.elejandria.models.Book;
import com.elejandria.app.elejandria.models.ResultadoBusqueda;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public class RvLibroAdapter extends RecyclerView.Adapter<RvLibroAdapter.LibroViewHolder>  implements Filterable {


    public static class LibroViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        //TextView tvTituloLibro;
        // TextView tvNombreAutor;
        ImageView ivPortadaLibro;

        LibroViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cvLibro);
            // tvTituloLibro = (TextView)itemView.findViewById(R.id.tvTituloLibro);
            ivPortadaLibro = (ImageView) itemView.findViewById(R.id.ivPortadaLibro);
            // tvNombreAutor = (TextView)itemView.findViewById(R.id.tvNombreAutor);
        }
    }

    public List<Book> libros, librosFiltro;
    Context context;

    BookFilter filter;


    public RvLibroAdapter(List<Book> libros, Context context){

        this.libros = libros;
        this.librosFiltro = libros;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return libros.size();
    }


    @Override
    public LibroViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_collection_libros_libro, viewGroup, false);
        LibroViewHolder lvh = new LibroViewHolder(v);
        return lvh;
    }

    @Override
    public void onBindViewHolder(LibroViewHolder libroViewHolder, int i) {

        Picasso.get().load(libros.get(i).getPortadaSM()).into(libroViewHolder.ivPortadaLibro);

        libroViewHolder.ivPortadaLibro.setScaleType(ImageView.ScaleType.CENTER_CROP);

        final Book libro = libros.get(i);

        libroViewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putInt("bookId", libro.getId());
                bundle.putString("titulo", libro.getName());
                bundle.putString("portada", libro.getPortadaLG());

                Navigation.findNavController(view).navigate(R.id.navigation_libro,bundle);

            }
        });

    }

    //RETURN FILTER OBJ
    @Override
    public Filter getFilter() {
        if(filter==null)
        {
            filter=new BookFilter(librosFiltro,this);
        }

        return filter;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
