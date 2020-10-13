package com.elejandria.app.elejandria.helpers;

import android.widget.Filter;

import com.elejandria.app.elejandria.adapters.RvLibroAdapter;
import com.elejandria.app.elejandria.adapters.RvResultadosBusquedaAdapter;
import com.elejandria.app.elejandria.models.Book;
import com.elejandria.app.elejandria.models.ResultadoBusqueda;

import java.util.ArrayList;
import java.util.List;

public class ResultadoBusquedaFilter extends Filter {

    RvResultadosBusquedaAdapter adapter;
    List<ResultadoBusqueda> filterList;

    public ResultadoBusquedaFilter(List<ResultadoBusqueda> filterList, RvResultadosBusquedaAdapter adapter)
    {
        this.adapter=adapter;
        this.filterList=filterList;

    }

    //FILTERING OCURS
    @Override
    protected Filter.FilterResults performFiltering(CharSequence constraint) {
        Filter.FilterResults results=new Filter.FilterResults();

        //CHECK CONSTRAINT VALIDITY
        if(constraint != null && constraint.length() > 0)
        {
            constraint=constraint.toString().toUpperCase();

            List<ResultadoBusqueda> filteredPlayers=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getTituloLibro().toUpperCase().contains(constraint) || filterList.get(i).getNombreAutor().toUpperCase().contains(constraint) )
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredPlayers.add(filterList.get(i));
                }
            }

            results.count=filteredPlayers.size();
            results.values=filteredPlayers;
        }else
        {
            results.count=filterList.size();
            results.values=filterList;

        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, Filter.FilterResults results) {

        adapter.libros = (List<ResultadoBusqueda>) results.values;

        //REFRESH
        adapter.notifyDataSetChanged();
    }
}