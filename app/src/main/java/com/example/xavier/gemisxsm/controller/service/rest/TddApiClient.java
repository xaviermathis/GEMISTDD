package com.example.xavier.gemisxsm.controller.service.rest;

import com.example.xavier.gemisxsm.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Xavier on 24/04/2017.
 */

public class TddApiClient {
    private static TddApiInterface tddApiInterface;
    //    private static String baseUrl = BuildConfig.BASE_URL ;
//    public static final String BASIC_AUTH_USERNAME = "blended";
//    public static final String BASIC_AUTH_PASSWORD = "[2xvNMRpj|nmV-Tx%C!?9gIf19>Xlz3Y=LiYIa1t";
//    public static String BASE_URL = "https://blended.com.ar/";
//    public static String BASE_UPLOADS_URL = "https://s3-us-west-2.amazonaws.com/blendeduploads/prod/";

    public static TddApiInterface getClient() {

        if (tddApiInterface == null) {

//            BASIC_AUTH_PASSWORD = BuildConfig.BASIC_AUTH_PASSWORD;
            OkHttpClient okClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            return null;
                        }
                    }).readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true).build();

            Retrofit client = new Retrofit.Builder()
                    .baseUrl( "https://api.mlab.com/api/1/")
                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            tddApiInterface = client.create(TddApiInterface.class);
        }
        return tddApiInterface;
    }
}
