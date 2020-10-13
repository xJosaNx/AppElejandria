package com.elejandria.app.elejandria.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.elejandria.app.elejandria.R;
import com.elejandria.app.elejandria.adapters.RvCollectionAdapter;
import com.elejandria.app.elejandria.adapters.RvLibroAdapter;
import com.elejandria.app.elejandria.databinding.LibroFragmentBinding;
import com.elejandria.app.elejandria.helpers.Helpers;
import com.elejandria.app.elejandria.models.Book;
import com.elejandria.app.elejandria.models.BookCollection;
import com.elejandria.app.elejandria.models.ResultadoBusqueda;
import com.folioreader.Config;
import com.folioreader.FolioReader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

public class LibroFragment  extends Fragment {

    private LibroViewModel libroViewModel;
    private int bookId;
    private LibroFragmentBinding libroFragmentDataBinding;
    boolean abrirLibroReader = false;
    private DownloadManager downloadManager;
    public static int REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    Long reference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

       // View root = inflater.inflate(R.layout.libro_fragment, container, false);

        libroFragmentDataBinding = DataBindingUtil.inflate(              inflater, R.layout.libro_fragment, container, false);
        View view = libroFragmentDataBinding.getRoot();

        return view;

    }

    public static LibroFragment newInstance(int bookId) {
        LibroFragment myFragment = new LibroFragment();

        Bundle args = new Bundle();
        args.putInt("bookId", bookId);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public void onResume(){
        super.onResume();
        getActivity().setTitle("Libro");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        libroViewModel = ViewModelProviders.of(this).get(LibroViewModel.class);

        bookId = getArguments().getInt("bookId", 0);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        libroViewModel.getBook(bookId).observe(this, new Observer<Book>() {
            @Override
            public void onChanged(@Nullable Book book) {
                libroFragmentDataBinding.setBook(book);
                Picasso.get().load(book.getPortadaLG()).into((ImageView)getActivity().findViewById(R.id.ivFichaLibroPortada));
                Picasso.get().load(book.getAuthor().getPicSM()).into((ImageView)getActivity().findViewById(R.id.ivFichaLibroAutor));

                final RecyclerView rvOtrosLibrosRecomendados = (RecyclerView)getActivity().findViewById(R.id.rvOtrosLibrosRecomendados);
                rvOtrosLibrosRecomendados.setHasFixedSize(true);

                LinearLayoutManager llmLibrosRecomendados = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                rvOtrosLibrosRecomendados.setLayoutManager(llmLibrosRecomendados);

                RvLibroAdapter adapterLibrosRecomendados = new RvLibroAdapter(book.getRecomendaciones(), getActivity().getApplicationContext());
                rvOtrosLibrosRecomendados.setAdapter(adapterLibrosRecomendados);

            }
        });



        AppCompatButton btnLibroCompartir = getActivity().findViewById(R.id.btnLibroCompartir);
        btnLibroCompartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Acabo de descubrir este libro en Elejandria " + libroViewModel.getBook().getValue().getUrl() + "y me he acordado de ti :)");
                startActivity(Intent.createChooser(shareIntent, "Compartir libro.."));
            }
        });


        AppCompatButton btnLibroDescargar = getActivity().findViewById(R.id.btnLibroDescargar);
        btnLibroDescargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               descargar_libro();
            }
        });

        AppCompatButton btnLeerLibroOnline = getActivity().findViewById(R.id.btnLeerLibroOnline);
        btnLeerLibroOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leer_libro();
            }
        });

        AppCompatButton btnLibroColeccion = getActivity().findViewById(R.id.btnLibroColeccion);
        btnLibroColeccion.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String[] colors = {"Quiero leerlo", "Lo estoy leyendo", "Lo he leído" };

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("¿Cómo llevas el libro?");
                builder.setItems(colors, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // the user clicked on colors[which]
                    }
                });
                builder.show();
            }
        });

    }


    public void descargar_libro() {
        int permissionCheck = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL_STORAGE);
        }
        else
        {
            downloadManager = (DownloadManager) getActivity().getSystemService(getActivity().getApplicationContext().DOWNLOAD_SERVICE);
            Uri uri = Uri.parse( libroViewModel.getBook().getValue().getLinkPDF().getLink());
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS.toString(),  libroViewModel.getBook().getValue().getId()+".pdf");
            Long reference = downloadManager.enqueue(request);

        }



    }


    public void leer_libro()
    {
        abrirLibroReader = true;
        int permissionCheck = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL_STORAGE);
        }
        else {

            downloadManager = (DownloadManager) getActivity().getSystemService(getActivity().getApplicationContext().DOWNLOAD_SERVICE);
            Uri uri = Uri.parse(libroViewModel.getBook().getValue().getLinkEpub().getLink());


            File folder1 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + libroViewModel.getBook().getValue().getId() + ".epub");
            if (!folder1.exists()) {
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS.toString(), libroViewModel.getBook().getValue().getId() + ".epub");
                reference = downloadManager.enqueue(request);

                getContext().registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
            } else {
                Config config = new Config().setThemeColorInt(getResources().getColor(R.color.colorAccent));
                config.setAllowedDirection(Config.AllowedDirection.ONLY_HORIZONTAL);
                config.setDirection(Config.Direction.HORIZONTAL);

                FolioReader folioReader = FolioReader.get()
                        .setConfig(config, true)
                        .openBook(folder1.getAbsolutePath());

            }
        }
    }

    BroadcastReceiver onComplete=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Uri mostRecentDownload =  downloadManager.getUriForDownloadedFile(reference);

            if( abrirLibroReader ) {
                abrirLibroReader = false;

                Config config = new Config().setThemeColorInt(getResources().getColor(R.color.colorAccent));

                FolioReader folioReader = FolioReader.get()
                        .setConfig(config, true)
                        .openBook(Helpers.getPathFromUri(getActivity(), mostRecentDownload));
            }

        }

    };
}