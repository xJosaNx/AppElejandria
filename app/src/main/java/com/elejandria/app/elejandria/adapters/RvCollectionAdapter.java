package com.elejandria.app.elejandria.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.elejandria.app.elejandria.R;
import com.elejandria.app.elejandria.models.BookCollection;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RvCollectionAdapter extends RecyclerView.Adapter<RvCollectionAdapter.CollectionViewHolder> {


    public static class CollectionViewHolder extends RecyclerView.ViewHolder {

        TextView tvCollecionTitulo;
        RecyclerView rvCollectionLibros;

        CollectionViewHolder(View itemView) {
            super(itemView);
            tvCollecionTitulo = itemView.findViewById(R.id.tvCollectionTitulo);
            rvCollectionLibros = itemView.findViewById(R.id.rvCollectionLibros);
        }
    }

    List<BookCollection> collections;
    Context context;

    public RvCollectionAdapter(List<BookCollection> collections, Context context) {

        this.collections = collections;
        this.context = context;
    }


    @Override
    public int getItemCount() {
        return collections.size();
    }


    @Override
    public CollectionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_coleccion_libros, viewGroup, false);
        CollectionViewHolder lvh = new CollectionViewHolder(v);
        return lvh;
    }

    @Override
    public void onBindViewHolder(CollectionViewHolder collectionVH, int i) {

        // Picasso.get().load(libros.get(i).getPortadaSM()).into(personViewHolder.ivPortadaLibro);
        //  personViewHolder.ivPortadaLibro.setScaleType(ImageView.ScaleType.CENTER_CROP);

        //  final int id = libros.get(i).getId();

      /* personViewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BookActivity.class);
                intent.putExtra("id", String.valueOf(id) );
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);

                context.startActivity(intent);
            }
        });*/

        if(collections.get(i).books != null) {
            LinearLayoutManager llm = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            collectionVH.rvCollectionLibros.setLayoutManager(llm);
            RvLibroAdapter adapter = new RvLibroAdapter(collections.get(i).books, context);
            collectionVH.rvCollectionLibros.setAdapter(adapter);
        }
        collectionVH.tvCollecionTitulo.setText(collections.get(i).name);

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
