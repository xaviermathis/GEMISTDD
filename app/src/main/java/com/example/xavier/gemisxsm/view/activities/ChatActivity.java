//package com.example.xavier.gemisxsm.view.activities;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.graphics.Bitmap;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.os.Bundle;
//import android.support.v7.app.ActionBar;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.blended.android.free.R;
//import com.blended.android.free.controller.service.push.BlendedApplication;
//import com.blended.android.free.controller.service.rest.OnSuccessCallback;
//import com.blended.android.free.controller.service.rest.RestClient;
//import com.blended.android.free.model.entities.Conversacion;
//import com.blended.android.free.model.entities.Mensaje;
//import com.blended.android.free.model.entities.User;
//import com.blended.android.free.utils.FrescoImageController;
//import com.blended.android.free.utils.InstitucionAdminManager;
//import com.blended.android.free.view.fragments.ChatFragment;
//import com.blended.android.free.view.fragments.InboxFragment;
//import com.facebook.drawee.drawable.RoundedColorDrawable;
//import com.facebook.drawee.generic.GenericDraweeHierarchy;
//import com.facebook.drawee.generic.RoundingParams;
//import com.facebook.drawee.view.SimpleDraweeView;
//import com.google.gson.Gson;
//import com.loopj.android.http.AsyncHttpResponseHandler;
//import com.loopj.android.http.JsonHttpResponseHandler;
//import com.loopj.android.http.RequestParams;
//import com.yongchun.library.view.ImageSelectorActivity;
//
//import org.apache.http.Header;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.Map;
//import java.util.Random;
//
//import static com.blended.android.free.constant.FragmentConst.FRAGMENT_CHAT;
//import static com.blended.android.free.view.activities.MakeAPostActivity.decodeSampledBitmapFromFile;
//
///**
// * Chat Activity, enables sending and receiving text and images.
// */
//public class ChatActivity extends BlendedActivity {
//    File f;
//    private static final int CAMERA_REQUEST = 1888;
//    private ArrayList<String> image_uris;
//    private User finalUser;
//    private TextView tvOtherName;
//    private SimpleDraweeView profilePicDraweeView;
//    private ImageView optionsIv;
//    private User receptor_user;
//
//    public File getFile(){
//        return f;
//    }
//
//    public ChatFragment chatFragment;
//    private static final int ELIMINAR_CONVERSACION = 0;
//    private static final int BLOQUEAR_CONVERSACION = 1;
//    private static final int ENVIAR_ARCHIVO = 2;
//
//
//    /**
//     * Unregisters the parse receiver
//     */
//    @Override
//    protected void onPause() {
//        super.onPause();
//        RestClient.cancelRequests(this);
//    }
//
//
//    /**
//     * Manages the response of
//     * @param requestCode
//     * @param resultCode
//     * @param data The data received
//     */
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
////        if (/*requestCode == CAMERA_REQUEST && */resultCode == RESULT_OK) {
////            Bitmap photo;
////            if(requestCode==1){
////                Uri selectedImage = data.getData();
////                String[] filePathColumn = { MediaStore.Images.Media.DATA };
////
////                Cursor cursor = getContentResolver().query(selectedImage,
////                        filePathColumn, null, null, null);
////                cursor.moveToFirst();
////
////                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
////                String picturePath = cursor.getString(columnIndex);
////                cursor.close();
////                photo =  BitmapFactory.decodeFile(picturePath);
////            }else{
////                photo = (Bitmap) data.getExtras().get("data");
////            }
////            f = new File(this.getCacheDir(), "blendedtemp.png");
////            try {
////                f.createNewFile();
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////            //Convert bitmap to byte array
////            Bitmap bitmap = photo;
////            ByteArrayOutputStream bos = new ByteArrayOutputStream();
////            bitmap.compress(Bitmap.CompressFormat.PNG, 100 /*ignored for PNG*/, bos);
////            byte[] bitmapdata = bos.toByteArray();
////            //write the bytes in file
////            FileOutputStream fos = null;
////            try {
////                fos = new FileOutputStream(f);
////                fos.write(bitmapdata);
////                fos.flush();
////                fos.close();
////                chatFragment.addAdjunto(f, photo);
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
////        }
//        if (resultCode == Activity.RESULT_OK && requestCode == ImageSelectorActivity.REQUEST_IMAGE) {
//            image_uris = (ArrayList<String>) data.getSerializableExtra(ImageSelectorActivity.REQUEST_OUTPUT);
//            if (image_uris != null) {
//                try {
//                    for (String uri : image_uris) {
//                    //Get our saved file into a bitmap object:
//                    Bitmap photo = decodeSampledBitmapFromFile(new File(uri).getAbsolutePath(), 1920, 1080);
//                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
//                    photo.compress(Bitmap.CompressFormat.JPEG, 80, bos);
//                    //create a file to write bitmap data
//                    Random r = new Random();
//                    int i1 = r.nextInt(1000 - 1) + 1;
//                    File f = new File(this.getCacheDir(), photo.getConfig().name() + Integer.toString(i1) + ".jpeg");
//                    Log.i("cachedir", this.getCacheDir().getAbsolutePath());
//                    try {
//                        f.createNewFile();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    byte[] bitmapdata = bos.toByteArray();
//                    //write the bytes in file
//                    FileOutputStream fos = new FileOutputStream(f);
//                    fos.write(bitmapdata);
//                    fos.flush();
//                    fos.close();
//                    chatFragment.addAdjunto(f);
//                }
//                    chatFragment.sendMsg();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    /**
//     *
//     * @param savedInstanceState
//     */
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        blendedApplication = (BlendedApplication)this.getApplicationContext();
////        blendedApplication.setCurrentActivity(this);
//
//        setContentView(R.layout.activity_main);
//        //Guardo objeto conversacion
//        Bundle extras = getIntent().getExtras();
//        String conversacionStr = "";
//
//
//
//        //disenio action bar
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setHomeActionContentDescription(getResources().getString(R.string.cancel));
//        actionBar.setHomeButtonEnabled(true);
//        //titulo de action bar
//        LayoutInflater inflator = LayoutInflater.from(this);
//        View v = inflator.inflate(R.layout.actionbar_chat, null);
//        tvOtherName = (TextView) v.findViewById(R.id.tv_title_action_bar);
//        optionsIv = (ImageView) v.findViewById(R.id.options);
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setDisplayShowTitleEnabled(false);
//        actionBar.setCustomView(v);
//        actionBar.setDisplayShowCustomEnabled(true);
//        //User name
//        profilePicDraweeView = (SimpleDraweeView) v.findViewById(R.id.chat_user_profile_icon);
//
//        profilePicDraweeView = (SimpleDraweeView) v.findViewById(R.id.chat_user_profile_icon);
//        GenericDraweeHierarchy hierarchy = profilePicDraweeView.getHierarchy();
//        adaptImageOverlay(Color.parseColor(BlendedApplication.get().institutionColor()), hierarchy);
//
//        //cambio bg color de la action bar
//        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(BlendedApplication.get().institutionColor())));
//
//
//        //cambio bg color de la action bar
//        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(BlendedApplication.get().institutionColor())));
//
//        if(extras != null) {
//            int conversation_id = 0;
//            Mensaje mensaje = null;
//            if (extras.containsKey("conversation_id")) {
//                conversation_id = extras.getInt("conversation_id");
//                if (extras.containsKey("message")) {
//                    try {
//                        mensaje = new Mensaje(new JSONObject(extras.getString("message")), false);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//                Mensaje finalMensaje = mensaje;
//                //TODO: spinner
//                boolean conversacionEncontrada = false;
//                for (Conversacion conv : BlendedApplication.get().getUserConversaciones()) {
//                    if (conv.getId() == conversation_id) {
//                        if(finalMensaje != null) {
//                            conv.getMensajes().add(finalMensaje);
//                        }
//                        handleConversation(conv);
//                        conversacionEncontrada = true;
//                    }
//                }
//                if (!conversacionEncontrada && mensaje != null) {
//                    crearNuevaConversacion(mensaje.getRemitente().getId(), new OnConversationCreatedCallback() {
//                        @Override
//                        public void execute(Conversacion conversation) {
//                            if(finalMensaje != null) {
//                                conversation.getMensajes().add(finalMensaje);
//                            }
//                            handleConversation(conversation);
//                        }
//                    });
//                }
//            } else if (extras.getInt("otheruser_id") != 0) {
//                final int otheruser_id = extras.getInt("otheruser_id");
//                crearNuevaConversacion(Integer.toString(otheruser_id),  new OnConversationCreatedCallback() {
//                    @Override
//                    public void execute(Conversacion conversation) {
//                        handleConversation(conversation);
//                    }
//                });
//            }
//        }
//    }
//
//
//    private void crearNuevaConversacion(String otheruser_id, OnConversationCreatedCallback callback) {
//        RequestParams params = new RequestParams();
//        params.add("user_id", getCurrentUser().getId());
//        params.add("otheruser_id", otheruser_id);
//        params.add("institucion_id", getCurrentInstitucion().getId());
//        ProgressDialog progressDialog = ProgressDialog.show(this, "Conversacion", "Cargando, por favor aguarde...", true,
//                true, new DialogInterface.OnCancelListener(){
//                    @Override
//                    public void onCancel(DialogInterface dialog) {
//                        dialog.dismiss();
//                        onBackPressed();
//                    }
//                }
//        );
//        RestClient.get("newconversation", params, new JsonHttpResponseHandler() {
//            public void onSuccess(int statusCode, Header[] headers, JSONObject responseBody) {
//                if(responseBody.has("error")) {
//                    Toast.makeText(BlendedApplication.getAppContext(), "No tiene permisos para iniciar conversacion con este usuario.", Toast.LENGTH_SHORT).show();
//                    progressDialog.cancel();
//                } else {
//                    try {
//                        JSONObject conversacionObj = responseBody.getJSONObject("conversacion");
//                        Log.i("conversacion", conversacionObj.toString());
//                        if(callback != null) {
//                            callback.execute(new Conversacion(conversacionObj, true));
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    progressDialog.dismiss();
//                }
//            }
//        });
//    }
//    /**
//     * Shows profile picture of the sender and his name on the Action Bar.
//     * @param conversacion The Conversation data
//     *
//     *
//     */
//    private void handleConversation(Conversacion conversacion) {
//        //titulo de chat
//        if (conversacion.getRemitente().getId().equals(getCurrentUser().getId())) {
//            finalUser = conversacion.getDestinatario();
//        } else {
//            finalUser = conversacion.getRemitente();
//        }
//        if(!InstitucionAdminManager.isAdmin(this, finalUser)) {
//            tvOtherName.setText(finalUser.getFullName());
//        } else {
//            tvOtherName.setText(getCurrentInstitucion().getNombre());
//        }
//        if(finalUser.getFullName() != null && !finalUser.getFullName().isEmpty()) {
//            tvOtherName.setVisibility(View.VISIBLE);
//        }
//        //Updates chat sender profile image
//        if(finalUser.getProfilePicture() != null) {
//            FrescoImageController.displayProfilePicture(finalUser.getProfilePicture().getFileName(), profilePicDraweeView);
//        }
//        profilePicDraweeView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent mainIntent = new Intent().setClass(
//                        ChatActivity.this, MainActivity.class);
//                mainIntent.putExtra("prof_id", chatFragment.getOtherId());
//                startActivity(mainIntent);
//            }
//        });
//        optionsIv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                displayOptionsMenu(conversacion);
//            }
//        });
//        //creo bundle con los datos de conversacion
//        Bundle data = new Bundle();
//        Gson gson = new Gson();
//        data.putString("conversacion", gson.toJson(conversacion));
//        //creo fragment chat y le agrego el bundle
//        chatFragment = new ChatFragment();
//        chatFragment.setArguments(data);
//        //ejecuto fragment
//        replaceFragment(R.id.fragment_main, chatFragment, FRAGMENT_CHAT);
//    }
//
//    private void displayOptionsMenu(Conversacion conversacion) {
//        if (conversacion.getDestinatario().getId().equals(getCurrentUser().getId())) {
//            receptor_user = conversacion.getRemitente();
//        } else {
//            receptor_user = conversacion.getDestinatario();
//        }
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//        Map<String, Integer> opciones = new LinkedHashMap<>();
//        if(chatFragment.isOtherUserBlocked()) {
//            opciones.put("Enviar Archivo", ENVIAR_ARCHIVO);
//        }
//        if (currentUserCanPostAny()) {
//            if (conversacion.isBloqueadoRemitente() || conversacion.isBloqueadoDestinatario()) {
//                opciones.put("Desbloquear conversación", BLOQUEAR_CONVERSACION);
//            } else {
//                opciones.put("Bloquear conversación", BLOQUEAR_CONVERSACION);
//            }
//        }
//        opciones.put("Eliminar conversación", ELIMINAR_CONVERSACION);
//
//        alertDialogBuilder.setItems(opciones.keySet().toArray(new CharSequence[opciones.size()]), new AlertDialog.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int index) {
//                switch ((Integer) opciones.values().toArray()[index]) {
//                    case ELIMINAR_CONVERSACION:
//                        eliminarConversacion(chatFragment.getConversacion(), new OnSuccessCallback() {
//                            @Override
//                            public void execute() {
//                                InboxFragment.setLazyRefreshInbox(true);
//                                onBackPressed();
//                            }
//                        });
//                        break;
//                    case BLOQUEAR_CONVERSACION:
//                        RequestParams params = new RequestParams();
//                        params.add("user_id", getCurrentUser().getId());
//                        params.add("conversacion_id", Integer.toString(conversacion.getId()));
//                        params.add("institucion_id",getCurrentInstitucion().getId());
//                        RestClient.post(getBaseContext(), "blockconversacion", params, new AsyncHttpResponseHandler() {
//                            @Override
//                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                                if(conversacion.isBloqueadoDestinatario()||conversacion.isBloqueadoRemitente()){
//                                    if(conversacion.isBloqueadoDestinatario()){
//                                        conversacion.setBloqueadoDestinatario(false);
//                                    }else {
//                                        conversacion.setBloqueadoRemitente(false);
//                                    }
//                                    chatFragment.desbloquearChat();
//                                    Toast.makeText(getBaseContext(), "La conversacion con " + receptor_user.getNombre() + " ha sido desbloqueada", Toast.LENGTH_LONG).show();
//                                }
//                                else {
//                                    if(getCurrentUser().getId().equals(conversacion.getDestinatario().getId())) {
//                                        conversacion.setBloqueadoRemitente(true);
//                                    }else if(getCurrentUser().getId().equals(conversacion.getRemitente().getId())){
//                                        conversacion.setBloqueadoDestinatario(true);
//                                    }
//                                    chatFragment.verifChatBlocked();
//                                    Toast.makeText(getBaseContext(), "La conversacion con " + receptor_user.getNombre() + " ha sido bloqueada", Toast.LENGTH_LONG).show();
//                                }
//                                InboxFragment.setLazyRefreshInbox(true);
//                            }
//
//                            @Override
//                            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                                Toast.makeText(getBaseContext(), "La conversacion no ha podido ser bloqueada", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                        break;
//                    case ENVIAR_ARCHIVO:
//                        showFileChooser();
//                        break;
//
//                }
//            }
//        }).show();
//    }
//
//    public void eliminarConversacion(Conversacion conversacion, OnSuccessCallback callback) {
//        RequestParams params = new RequestParams();
//        params.add("user_id", getCurrentUser().getId());
//        params.add("conversacion_id", Integer.toString(conversacion.getId()));
//        RestClient.post(this, "deleteconversation", params, new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                if(callback != null) {
//                    callback.execute();
//                }
//                Toast.makeText(getApplicationContext(), "La conversacion con " + conversacion.getDestinatario().getNombre() + " ha sido eliminada", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                Toast.makeText(getApplicationContext(), "La conversacion no ha podido ser eliminada", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                onBackPressed();
//                return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void showFileChooser() {
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("*/*");
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//
//        int FILE_SELECT_CODE = 0;
//        try {
//            startActivityForResult(
//                    Intent.createChooser(intent, "Select a File to Upload"),
//                    FILE_SELECT_CODE);
//        } catch (android.content.ActivityNotFoundException ex) {
//            // Potentially direct the user to the Market with a Dialog
//            Toast.makeText(this, "Please install a File Manager.",
//                    Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    /**
//     *
//     * @return The current user of the App.
//     */
//    public User getCurrentUser(){
//        //current user
//        User current_user = new User();
//        SharedPreferences sp = this.getSharedPreferences(getString(R.string.cache_objects), Context.MODE_PRIVATE);
//        try {
//            current_user = new User(new JSONObject(sp.getString("user", "")));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return current_user;
//    }
//
//    /**
//     * Finishes the activity to not be reachable with backpress.
//     */
//    @Override
//    public void onBackPressed()
//    {
//        finish();
////        super.onBackPressed();  // optional depending on your needs
//    }
////    private void clearReferences(){
////        Activity currActivity = blendedApplication.getCurrentActivity();
////        if (currActivity != null && currActivity.equals(this))
////            blendedApplication.setCurrentActivity(null);
////    }
//    private void adaptImageOverlay(int backgroundColor, GenericDraweeHierarchy hierarchy){
//        //GenericDraweeHierarchy hierarchy = viewHolder.ivUser.getHierarchy();  // get SettableDraweeHierarchy
//        RoundingParams roundingParams = hierarchy.getRoundingParams();
//        RoundedColorDrawable roundedColorDrawable = new RoundedColorDrawable(backgroundColor);  // construct an overlay drawable
//        roundedColorDrawable.setRadii(roundingParams.getCornersRadii());
//        //Changing overlay color to softgrey, and setting RoundAsCircle to False.
//        roundingParams.setRoundAsCircle(false).setRoundingMethod(RoundingParams.RoundingMethod.OVERLAY_COLOR).setOverlayColor(backgroundColor);
//        hierarchy.setRoundingParams(roundingParams);
//}
//
//    public interface OnConversationCreatedCallback {
//        public void execute(Conversacion conversation);
//    }
//
//}
