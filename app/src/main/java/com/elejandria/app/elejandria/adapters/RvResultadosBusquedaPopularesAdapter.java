package com.elejandria.app.elejandria.adapters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.elejandria.app.elejandria.MainActivity;
import com.elejandria.app.elejandria.R;
import com.elejandria.app.elejandria.models.Book;
import com.elejandria.app.elejandria.models.ResultadoBusqueda;
import com.elejandria.app.elejandria.ui.LibroFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

public class RvResultadosBusquedaPopularesAdapter
        extends RecyclerView.Adapter<RvResultadosBusquedaPopularesAdapter.LibroViewHolder>{


    public static class LibroViewHolder extends RecyclerView.ViewHolder {
        TextView tvTendenciasTituloLibro;
        TextView tvTendenciasNombreAutor;
        ImageView ivTendenciasPortadaLibro;
        ConstraintLayout constLayoutTendenciasWrapper;

        LibroViewHolder(View itemView) {
            super(itemView);

            tvTendenciasTituloLibro = (TextView)itemView.findViewById(R.id.tvTendenciasTituloLibro);
            tvTendenciasNombreAutor = (TextView)itemView.findViewById(R.id.tvTendenciasNombreAutor);
            ivTendenciasPortadaLibro = (ImageView) itemView.findViewById(R.id.ivTendenciasPortadaLibro);
            constLayoutTendenciasWrapper = itemView.findViewById(R.id.constLayoutTendenciasWrapper);

        }
    }

    List<ResultadoBusqueda> libros, librosFiltro;
    Context context;

    public RvResultadosBusquedaPopularesAdapter(List<ResultadoBusqueda> libros, Context context){

        this.libros = libros;
        this.librosFiltro = new ArrayList<ResultadoBusqueda>();
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return libros.size();
    }


    @Override
    public RvResultadosBusquedaPopularesAdapter.LibroViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_busqueda_tendencias, viewGroup, false);
        RvResultadosBusquedaPopularesAdapter.LibroViewHolder lvh = new RvResultadosBusquedaPopularesAdapter.LibroViewHolder(v);
        return lvh;
    }

    @Override
    public void onBindViewHolder(RvResultadosBusquedaPopularesAdapter.LibroViewHolder libroViewHolder, int i) {

        Picasso.get().load(libros.get(i).getPortadaLibroSM()).into(libroViewHolder.ivTendenciasPortadaLibro);
        libroViewHolder.ivTendenciasPortadaLibro.setScaleType(ImageView.ScaleType.CENTER_CROP);

        libroViewHolder.tvTendenciasNombreAutor.setText(libros.get(i).getNombreAutor());
        libroViewHolder.tvTendenciasTituloLibro.setText(libros.get(i).getTituloLibro());

        final int id = libros.get(i).getId();

        libroViewHolder.constLayoutTendenciasWrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Bundle bundle = new Bundle();
                bundle.putInt("bookId", id);

              Navigation.findNavController(view).navigate(R.id.navigation_libro,bundle);
            }
        });

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        librosFiltro.clear();
        if (charText.length() == 0) {
            librosFiltro.addAll(libros);
        } else {
            for (ResultadoBusqueda libro : libros) {
                if (libro.getTituloLibro().toLowerCase(Locale.getDefault()).contains(charText) || libro.getNombreAutor().toLowerCase(Locale.getDefault()).contains(charText)) {
                    librosFiltro.add(libro);
                }
            }
        }

        notifyDataSetChanged();
    }
}