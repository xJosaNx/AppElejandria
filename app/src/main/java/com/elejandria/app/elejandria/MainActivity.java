package com.elejandria.app.elejandria;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    /*private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_busqueda:
                    Navigation.findNavController(findViewById(android.R.id.content)).navigate(R.id.navigation_busqueda);

                    return true;
                case R.id.navigation_coleccion:
                    Navigation.findNavController(findViewById(android.R.id.content)).navigate(R.id.nav_coleccion);
                    return true;
            }
            return false;
        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpNavigation();



    }


    private void setUpNavigation()
    {
        bottomNavigationView = findViewById(R.id.nav_view);

        NavHostFragment navHostFragment =       (NavHostFragment)getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView,
                navHostFragment.getNavController());

        // navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
        // R.id.navigation_inicio, R.id.navigation_busqueda, R.id.navigation_lectura, R.id.navigation_coleccion, R.id.navigation_perfil)
        // .build();
       // NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
       // NavigationUI.setupWithNavController(navView, navController);
    }

}
