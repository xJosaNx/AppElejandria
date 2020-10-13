package com.elejandria.app.elejandria.helpers;

import android.widget.Filter;

import com.elejandria.app.elejandria.adapters.RvLibroAdapter;
import com.elejandria.app.elejandria.models.Book;

import java.util.ArrayList;
import java.util.List;

public class BookFilter extends Filter {

    RvLibroAdapter adapter;
    List<Book> filterList;

    public BookFilter(List<Book> filterList, RvLibroAdapter adapter)
    {
        this.adapter=adapter;
        this.filterList=filterList;

    }

    //FILTERING OCURS
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();

        //CHECK CONSTRAINT VALIDITY
        if(constraint != null && constraint.length() > 0)
        {
            constraint=constraint.toString().toUpperCase();

            List<Book> filteredPlayers=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getName().toUpperCase().contains(constraint))
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
    protected void publishResults(CharSequence constraint, FilterResults results) {

        adapter.libros = (List<Book>) results.values;

        //REFRESH
        adapter.notifyDataSetChanged();
    }
}