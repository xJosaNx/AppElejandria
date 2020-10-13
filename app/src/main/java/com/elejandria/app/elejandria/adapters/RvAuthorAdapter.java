package com.elejandria.app.elejandria.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.elejandria.app.elejandria.R;
import com.elejandria.app.elejandria.helpers.AuthorFilter;
import com.elejandria.app.elejandria.helpers.BookFilter;
import com.elejandria.app.elejandria.models.Author;
import com.elejandria.app.elejandria.models.ResultadoBusqueda;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public class RvAuthorAdapter extends RecyclerView.Adapter<RvAuthorAdapter.AuthorViewHolder> implements Filterable {


    public static class AuthorViewHolder extends RecyclerView.ViewHolder {
        TextView tvAutorNumLibros;
        TextView tvAutorNombre;
        ImageView ivAutorImagen;
        ConstraintLayout constLayoutAutorWrapper;

        AuthorViewHolder(View itemView) {
            super(itemView);

            tvAutorNumLibros = (TextView) itemView.findViewById(R.id.tvAutorNumLibros);
            tvAutorNombre = (TextView) itemView.findViewById(R.id.tvAutorNombre);
            ivAutorImagen = (ImageView) itemView.findViewById(R.id.ivAutorImagen);
            constLayoutAutorWrapper = itemView.findViewById(R.id.constLayoutAutorWrapper);

        }
    }

    public List<Author> authors, authorsFiltro;
    Context context;
    AuthorFilter filter;

    public RvAuthorAdapter(List<Author> authors, Context context) {

        this.authors = authors;
        this.authorsFiltro = authors;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return authors.size();
    }


    @Override
    public RvAuthorAdapter.AuthorViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_author_listado, viewGroup, false);
        RvAuthorAdapter.AuthorViewHolder lvh = new RvAuthorAdapter.AuthorViewHolder(v);
        return lvh;
    }

    @Override
    public void onBindViewHolder(RvAuthorAdapter.AuthorViewHolder libroViewHolder, int i) {

        Picasso.get().load(authors.get(i).getPicSM()).into(libroViewHolder.ivAutorImagen);
        libroViewHolder.ivAutorImagen.setScaleType(ImageView.ScaleType.CENTER_CROP);

        libroViewHolder.tvAutorNombre.setText(authors.get(i).getName());
        libroViewHolder.tvAutorNumLibros.setText("0");

        final int id = authors.get(i).getId();

        libroViewHolder.constLayoutAutorWrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Bundle bundle = new Bundle();
                bundle.putInt("authorId", id);

                Navigation.findNavController(view).navigate(R.id.navigation_libro, bundle);
            }
        });

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    //RETURN FILTER OBJ
    @Override
    public Filter getFilter() {
        if(filter==null)
        {
            filter=new AuthorFilter(authorsFiltro,this);
        }

        return filter;
    }
}