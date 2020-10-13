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

import com.elejandria.app.elejandria.R;

public class LecturaFragment extends Fragment {

    private LecturaViewModel lecturaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        lecturaViewModel =
                ViewModelProviders.of(this).get(LecturaViewModel.class);
        View root = inflater.inflate(R.layout.lectura_fragment, container, false);
        /*final TextView textView = root.findViewById(R.id.text_dashboard);
        lecturaViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}