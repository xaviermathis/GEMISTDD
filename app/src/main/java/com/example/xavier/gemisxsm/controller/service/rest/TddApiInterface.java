package com.example.xavier.gemisxsm.controller.service.rest;

import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * API Interface, exposes all methods available for using, with methods and parameters.
 */
//@Headers()
public interface TddApiInterface {

    @GET("databases/gemistd/collections/problemas?apiKey=Z9bU2vFN7gOsnsf4CfyJ8zXiZTadxnKB")
    ///api/v3/newsfeed?limit=5&offset=0&user_id=15406&institucion_id=144
    Call<JsonObject> getProblemas();

//    @GET("conversaciones")
//    Call<ResponseBody> getConversaciones(@Query("user_id") String user_id);
//
//
//    @POST("conversationread")
//    Call<ResponseBody> postConversationRead( @Query("user_id") String user_id, @Query("conversation_id") int conversation_id);
//
//    @FormUrlEncoded
//    @POST("message")
//    Call<ResponseBody> postMessage(@Field("user_id") String user_id, @Field("conversation_id") Integer conversation_id,
//                                   @Field("message") String message);
//
//    @FormUrlEncoded
//    @POST("message")
//    Call<ResponseBody> postMessage(@Field("user_id") String user_id, @Field("conversation_id") Integer conversation_id,
//                                   @Field("message") String message, @Field("has_attach") Integer has_attach,
//                                   @FieldMap Map<String, File> options);
}