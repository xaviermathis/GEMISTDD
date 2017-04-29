package com.example.xavier.gemisxsm.controller.service.rest;//package com.example.xavier.gemisxsm.controller.service.rest;
//
//
//import android.content.Context;
//
//import okhttp3.OkHttpClient;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
///**
// * API Asynchronous Rest Client.
// */
//public class RestClient {
////    public static final String BASE_URL = "https://blended.com.ar/newdev/";
////    public static final String BASE_UPLOADS_URL = "https://s3-us-west-2.amazonaws.com/blendeduploads/dev/";
////    public static final String BASIC_AUTH_USERNAME = "blended";
////    public static final String BASIC_AUTH_PASSWORD = "blended";
////    public static final String BASIC_AUTH_USERNAME = "blended";
////    public static final String BASIC_AUTH_PASSWORD = "[2xvNMRpj|nmV-Tx%C!?9gIf19>Xlz3Y=LiYIa1t";
////    public static String BASE_URL = "https://blended.com.ar/";
////    public static String BASE_UPLOADS_URL = "https://s3-us-west-2.amazonaws.com/blendeduploads/prod/";
//    public static String BASE_URL;
//    public static String BASE_UPLOADS_URL;
//    public static String BASIC_AUTH_USERNAME;
//    public static String BASIC_AUTH_PASSWORD;
//    private static String CHANGELOG_URL = "changelog/android/es/current.txt";
//
////    private static AsyncHttpClient client = new AsyncHttpClient();
//    Context context;
//    public RestClient(Context context){
//        this.context = context;
//    }
//
//    public static void getClient() {
////        https://api.mlab.com/api/1/databases/gemistd/collections/problemas?apiKey=Z9bU2vFN7gOsnsf4CfyJ8zXiZTadxnKB
//
//        String API_BASE_URL = "https://api.mlab.com/api/1/";
//
//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//
//        Retrofit.Builder builder =
//                new Retrofit.Builder()
//                        .baseUrl(API_BASE_URL)
//                        .addConverterFactory(
//                                GsonConverterFactory.create()
//                        );
//
//        Retrofit retrofit =
//                builder
//                        .client(
//                                httpClient.build()
//                        )
//                        .build();
//
//        TddApiInterface client =  retrofit.create(TddApiInterface.class);
//    }
//}

import com.example.xavier.gemisxsm.entities.Problema;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestClient {
}
