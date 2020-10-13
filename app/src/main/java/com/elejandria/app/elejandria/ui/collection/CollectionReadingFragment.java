package com.elejandria.app.elejandria.ui.collection;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elejandria.app.elejandria.R;
import com.elejandria.app.elejandria.ui.ColeccionViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectionReadingFragment extends Fragment {


    private ColeccionViewModel notificationsViewModel;

    /**
     * @return A new instance of fragment SpeedDialFragment.
     */
    public static CollectionReadingFragment newInstance() {
        return new CollectionReadingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notificationsViewModel = ViewModelProviders.of(this).get(ColeccionViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_collection_reading, container, false);
    }

}
