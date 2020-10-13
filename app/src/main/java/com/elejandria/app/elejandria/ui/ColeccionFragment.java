package com.elejandria.app.elejandria.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.elejandria.app.elejandria.R;
import com.elejandria.app.elejandria.adapters.RvResultadosBusquedaPopularesAdapter;
import com.elejandria.app.elejandria.adapters.TabsCollectionPagerAdapter;
import com.elejandria.app.elejandria.models.ResultadoBusqueda;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class ColeccionFragment extends Fragment {

    private ColeccionViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        notificationsViewModel = ViewModelProviders.of(this).get(ColeccionViewModel.class);
        View root = inflater.inflate(R.layout.coleccion_fragment, container, false);


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        TabsCollectionPagerAdapter tabsPagerAdapter = new TabsCollectionPagerAdapter(getContext(), getFragmentManager());

        ViewPager viewPager = getActivity().findViewById(R.id.vpgTabsCollection);
        viewPager.setAdapter(tabsPagerAdapter);

        TabLayout tabs = getActivity().findViewById(R.id.tabsCollection);
        tabs.setupWithViewPager(viewPager);


    }
}