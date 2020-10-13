package com.elejandria.app.elejandria.network;

import com.elejandria.app.elejandria.interfaces.ApiElejandria;
import com.elejandria.app.elejandria.network.BasicAuthInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Incubus on 03/01/2019.
 */

public class NetworkService {
    private static NetworkService mInstance;
    private static final String BASE_URL = "https://www.elejandria.com";
    private Retrofit mRetrofit;

    private NetworkService() {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new BasicAuthInterceptor("apielejandria", "Elejadminapi88"))
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    public ApiElejandria getJSONApi() {
        return mRetrofit.create(ApiElejandria.class);
    }
}