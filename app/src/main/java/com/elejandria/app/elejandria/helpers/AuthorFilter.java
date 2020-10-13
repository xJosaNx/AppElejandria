package com.elejandria.app.elejandria.helpers;

import android.widget.Filter;

import com.elejandria.app.elejandria.adapters.RvAuthorAdapter;
import com.elejandria.app.elejandria.adapters.RvLibroAdapter;
import com.elejandria.app.elejandria.models.Author;
import com.elejandria.app.elejandria.models.Book;

import java.util.ArrayList;
import java.util.List;

public class AuthorFilter extends Filter {

    RvAuthorAdapter adapter;
    List<Author> filterList;

    public AuthorFilter(List<Author> filterList, RvAuthorAdapter adapter)
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

            List<Author> filteredPlayers=new ArrayList<>();

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

        adapter.authorsFiltro = (List<Author>) results.values;

        //REFRESH
        adapter.notifyDataSetChanged();
    }
}