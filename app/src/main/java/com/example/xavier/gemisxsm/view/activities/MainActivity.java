//package com.example.xavier.gemisxsm.view.activities;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.graphics.Bitmap;
//import android.graphics.drawable.ColorDrawable;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.view.MenuItemCompat;
//import android.support.v7.app.ActionBar;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.Spinner;
//import android.widget.TextView;
//
//import com.blended.android.free.R;
//import com.blended.android.free.controller.listeners.TabListener;
//import com.blended.android.free.controller.service.push.BlendedApplication;
//import com.blended.android.free.controller.service.rest.TddApiRequestHandler;
//import com.blended.android.free.controller.service.rest.RestClient;
//import com.blended.android.free.model.entities.Evento;
//import com.blended.android.free.model.entities.Institucion;
//import com.blended.android.free.model.entities.User;
//import com.blended.android.free.utils.BadgeManager;
//import com.blended.android.free.view.fragments.BlendedTabFragment;
//import com.blended.android.free.view.fragments.EventoCalendarioFragment;
//import com.blended.android.free.view.fragments.ExpandedInstitutionFragment;
//import com.blended.android.free.view.fragments.InboxFragment;
//import com.blended.android.free.view.fragments.MenuFragment;
//import com.blended.android.free.view.fragments.NewsFeedFragment;
//import com.blended.android.free.view.fragments.NotificationsFragment;
//import com.blended.android.free.view.fragments.PostCommentsFragment;
//import com.blended.android.free.view.fragments.ProfileFragment;
//import com.blended.android.free.view.fragments.SearchFragment;
//import com.google.gson.Gson;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.atomic.AtomicBoolean;
//
//import me.grantland.widget.AutofitHelper;
//import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
//import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
//
//import static com.blended.android.free.constant.FragmentConst.FRAGMENT_EVENTO_CALENDARIO;
//import static com.blended.android.free.constant.FragmentConst.FRAGMENT_EXPANDED;
//import static com.blended.android.free.constant.FragmentConst.FRAGMENT_INBOX;
//import static com.blended.android.free.constant.FragmentConst.FRAGMENT_MENU;
//import static com.blended.android.free.constant.FragmentConst.FRAGMENT_NEWS_FEED;
//import static com.blended.android.free.constant.FragmentConst.FRAGMENT_NOTIFICATIONS;
//import static com.blended.android.free.constant.FragmentConst.FRAGMENT_POST_COMMENTS;
//import static com.blended.android.free.constant.FragmentConst.FRAGMENT_PROFILE;
//import static com.blended.android.free.constant.FragmentConst.FRAGMENT_SEARCH;
//import static com.blended.android.free.controller.service.push.BlendedApplication.getAppContext;
//import static com.blended.android.free.model.entities.Notificacion.BC_NOTIFICATION_ID_ALSOCOMMENT;
//import static com.blended.android.free.model.entities.Notificacion.BC_NOTIFICATION_ID_CALIFICACIONES;
//import static com.blended.android.free.model.entities.Notificacion.BC_NOTIFICATION_ID_LIKECOMMENT;
//import static com.blended.android.free.model.entities.Notificacion.BC_NOTIFICATION_ID_LIKEPOST;
//import static com.blended.android.free.model.entities.Notificacion.BC_NOTIFICATION_ID_NEWCOMMENT;
//import static com.blended.android.free.model.entities.Notificacion.BC_NOTIFICATION_ID_NEWEVENT;
//import static com.blended.android.free.model.entities.Notificacion.BC_NOTIFICATION_ID_NEWPOST;
//
///**
// * Main Activity of the App.
// * Initializes most of the necessary visual components.
// * Manages push notifications target Fragments.
// * Configures ActionBar, title, tabs.
// */
//public class MainActivity extends BlendedActivity {
//    private static final int CAMERA_REQUEST = 1888;
//    ActionBar.Tab tab_home, tab_messages, tab_notifications, tab_menu;
//    BlendedTabFragment fragment_tab_home = new NewsFeedFragment();
//    BlendedTabFragment fragment_tab_messages = new InboxFragment();
//    BlendedTabFragment fragment_tab_notifications = new NotificationsFragment();
////    BlendedTabFragment fragment_tab_profile = new ProfileFragmentOld();
//    BlendedTabFragment fragment_tab_menu = new MenuFragment();
//    Fragment expandedInstitution = new ExpandedInstitutionFragment();
//
//    public static List<ActionBar.Tab> tabList = new ArrayList<>();
//    private int back_pos;
//    private User curr_us;
//    private static SharedPreferences sharedPref;
//    private SharedPreferences.OnSharedPreferenceChangeListener spChanged;
//    public static AtomicBoolean firstTimeAccess = new AtomicBoolean(true);
//    private TddApiRequestHandler blendedApiRequestHandler;
//    private static BadgeManager badgeManager;
//
////    Fragment fragment_tab_menu = ProfileFragmentOld.newInstance(ar.com.blended.blended.view.widgets.MenuItem.TYPE_USER, getCurrentUser().getId());
//
//    /**
//     * @deprecated replaced with MakeAPostActivity
//     */
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
//            Bitmap photo = (Bitmap) data.getExtras().get("data");
//            ImageView iv = new ImageView(this);
//            iv.setImageBitmap(photo);
//            LinearLayout ll_photos = (LinearLayout) findViewById(R.id.make_a_post_ll_photos);
//            ll_photos.addView(iv);
//        }
//    }
//
//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        //calligraphy
//        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
//                .setDefaultFontPath("fonts/lato-regular.ttf")
//                .setFontAttrId(R.attr.fontPath)
//                .build()
//        );
//
//
//        sharedPref = getSharedPreferences(getString(R.string.cache_objects), Context.MODE_PRIVATE);
//        curr_us = getCurrentUser();
//        //TODO: Esto es un codeSmell de que la forma en que obtenemos todo no es optima
//        //TODO: Hay que hacer que sean syncronicos los request de instituciones y canPost
//        //TODO: A partir del Login
////        spChanged = new SharedPreferences.OnSharedPreferenceChangeListener() {
////            @Override
////            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
////                                                  String key) {
////                if(key.equals("current_institucion")) {
////                    invalidateOptionsMenu();
////                }
////                if(key.equals("user_canpost")) {
////                    invalidateOptionsMenu();
////                }
////            }
////        };
////        sharedPref.registerOnSharedPreferenceChangeListener(spChanged);
//
//
//        //Obtengo la data del usuario necesaria para usar las funcionalidades mas directas
//
//        //Suscripcion a parse
//        ((BlendedApplication)getApplicationContext()).oneSignalSuscribe(curr_us);
//        //Conexion a la rest API
//        RestClient.getClient();
//        //Request de BasicAccessData
//        blendedApiRequestHandler = new TddApiRequestHandler(this);
//        blendedApiRequestHandler.getFirstTimeApiData();
//
//        setContentView(R.layout.activity_main);
//        actionBar = getSupportActionBar();
//        actionBar.setHomeButtonEnabled(true);
//        actionBar.setHomeAsUpIndicator(getResources().getDrawable(R.mipmap.ic_search));
//        actionBar.setStackedBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blanco)));
//        LayoutInflater inflator = LayoutInflater.from(this);
//        View v = inflator.inflate(R.layout.actionbar_title, null);
//        AutofitHelper.create(((TextView) v.findViewById(R.id.tv_title_action_bar)));
//        v.findViewById(R.id.tv_title_action_bar).setVisibility(View.GONE);
////        v.findViewById(R.id.action_bar_iv_logo).setVisibility(View.GONE);
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setDisplayShowTitleEnabled(false);
//        actionBar.setCustomView(v);
//        actionBar.setDisplayShowCustomEnabled(true);
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//        //cambio bg color de la action bar
//        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.light_gray_background)));
//        //asigno iconos de tabs
//
//        tab_home = actionBar.newTab().setCustomView(renderTabView(getApplicationContext(), R.drawable.ic_newsfeed_empty)).setTag(FRAGMENT_NEWS_FEED);
//        tab_messages = actionBar.newTab().setCustomView(renderTabView(getApplicationContext(), R.drawable.ic_messages_empty)).setTag(FRAGMENT_INBOX);
//        tab_notifications = actionBar.newTab().setCustomView(renderTabView(getApplicationContext(), R.drawable.ic_notifications_empty)).setTag(FRAGMENT_NOTIFICATIONS);
//        tab_menu = actionBar.newTab().setCustomView(renderTabView(getApplicationContext(), R.drawable.ic_menu_empty)).setTag(FRAGMENT_MENU);
//
//        forceActivityAttach(fragment_tab_home);
//        forceActivityAttach(fragment_tab_messages);
//        forceActivityAttach(fragment_tab_notifications);
//        forceActivityAttach(fragment_tab_menu);
//        tab_home.setTabListener(new TabListener(fragment_tab_home, R.drawable.ic_newsfeed_filled, R.drawable.ic_newsfeed_empty));
//        tab_messages.setTabListener(new TabListener(fragment_tab_messages, R.drawable.ic_messages_filled, R.drawable.ic_messages_empty));
//        tab_notifications.setTabListener(new TabListener(fragment_tab_notifications, R.drawable.ic_notifications_filled, R.drawable.ic_notifications_empty));
//        tab_menu.setTabListener(new TabListener(fragment_tab_menu, R.drawable.ic_menu_filled, R.drawable.ic_menu_empty));
//        //profile tab
////        ((ProfileFragmentOld)fragment_tab_menu).setType(com.blended.android.free.view.widgets.MenuItem.TYPE_USER);
////        ((ProfileFragmentOld)fragment_tab_menu).setItem_id(getCurrentUser().getId());
//
//        actionBar.addTab(tab_home, true); //Fist added tab launches onTabSelected inmediately (adds de fragment to main activity(
//        actionBar.addTab(tab_messages, false);
//        actionBar.addTab(tab_notifications, false);
//        actionBar.addTab(tab_menu, false);
//
//        tabList.add(tab_home);
//        tabList.add(tab_messages);
//        tabList.add(tab_notifications);
//        tabList.add(tab_menu);
//
//        actionBar.show();
//
//        //Logica de Menu
////        if (!sharedPref.getString("user_canpost", "").equals("")) {
////            invalidateOptionsMenu();
////        }
//
//        //inicializo newsfeed fragment
//        Bundle bundle = this.getIntent().getExtras();
//        if(bundle != null) {
//            handleNotification(bundle);
//        }
//        String otherId = "";
//        if(bundle != null && bundle.getString("prof_id") != null){
//            otherId = bundle.getString("prof_id", "");
//        }
//        if(otherId!=null && !otherId.equals("")){
//            replaceFragment(R.id.fragment_main,
//                    ProfileFragment.newInstance(com.blended.android.free.view.widgets.MenuItem.TYPE_USER, otherId),
//                    FRAGMENT_PROFILE);
//        }
//
//        styleBlendedApp();
//        badgeManager = new BadgeManager(getAppContext(), actionBar, tab_home, tab_messages, tab_notifications, tab_menu);
//        badgeManager.updateBadges();
//        badgeManager.refreshBadgeLauncher();
//    }
//
//
//    /**
//     * Push Notification onClick logic
//     */
//    private void handleNotification(Bundle bundle) {
//        int notification_type = bundle.getInt("notification_type");
//        boolean flag_notification = bundle.getBoolean("notification");
//        if (flag_notification) {
//            switch (notification_type) {
//                case BC_NOTIFICATION_ID_NEWPOST:
//                case BC_NOTIFICATION_ID_LIKEPOST:
//                case BC_NOTIFICATION_ID_LIKECOMMENT:
//                case BC_NOTIFICATION_ID_NEWCOMMENT:
//                case BC_NOTIFICATION_ID_ALSOCOMMENT:
//                        Bundle post = new Bundle();
//                        post.putString("post", bundle.getString("post"));
////                    try {
//                        PostCommentsFragment postCommentsFragment = new PostCommentsFragment();
//                        postCommentsFragment.setArguments(post);
////                        replaceFragment(R.id.fragment_main, new PostCommentsFragment(new Post(new JSONObject(bundle.getString("post")))), FRAGMENT_POST_COMMENTS);
//                        replaceFragment(R.id.fragment_main, postCommentsFragment, FRAGMENT_POST_COMMENTS);
////                    } catch (JSONException e) {
////                        e.printStackTrace();
////                    }
//                    break;
//                case BC_NOTIFICATION_ID_NEWEVENT:
//                    try {
//                        Evento evento = new Evento(new JSONObject(bundle.getString("evento")));
//                        List<Evento> listaEventos = new ArrayList<>();
//                        listaEventos.add(evento);
//                        Gson gson = new Gson();
//                        EventoCalendarioFragment eventoCalendarioFragment = new EventoCalendarioFragment();
//                        Bundle args = new Bundle();
//                        String dateEvents = gson.toJson(listaEventos).toString();
//                        args.putString("dateEvents", dateEvents);
//                        eventoCalendarioFragment.setArguments(args);
//                        replaceFragment(R.id.fragment_main, eventoCalendarioFragment, FRAGMENT_EVENTO_CALENDARIO);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    break;
//                case BC_NOTIFICATION_ID_CALIFICACIONES:
//                    replaceFragment(R.id.fragment_main, new NotificationsFragment(), FRAGMENT_NOTIFICATIONS);
//                    break;
//            }
//        }
//        else if(bundle.getInt("conversation_id") != 0) {
//            //Mensaje nuevo
//            Intent intent = new Intent(this, ChatActivity.class);
//            intent.putExtra("conversation_id", bundle.getInt("conversation_id"));
//            intent.putExtra("message", bundle.getString("message"));
//            startActivity(intent);
//        }
//    }
//
//    /**
//     * Initializes a tab
//     * @param context App context
//     * @param icon Icon to be put on the tab
//     * @return The view
//     */
//    public static View renderTabView(Context context, int icon) {
//        FrameLayout view = (FrameLayout) LayoutInflater.from(context).inflate(R.layout.tab_badge, null, false);
//        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        view.findViewById(R.id.tab_image).setBackgroundResource(icon);
//        return view;
//    }
//
//
//    private View.OnTouchListener SpinnerOnTouch = new View.OnTouchListener() {
//        public boolean onTouch(View v, MotionEvent event) {
//            if (event.getAction() == MotionEvent.ACTION_UP) {
//                if(!expandedInstitution.isAdded()) {
//                    hideSpinner();
//                    replaceFragment(R.id.fragment_main, expandedInstitution, FRAGMENT_EXPANDED);
//                }
//            }
//
//            return true;
//        }
//    };
//
//    private void forceActivityAttach(Fragment fragment) {
//        fragment.onAttach((Context) this);
//    }
//
//    /**
//     * Inflates menu with Action Bar and Spinner.
//     * @param menu The menu
//     */
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//
//        this.menu = menu;
//
//        MenuItem item = menu.findItem(R.id.action_bar_spinner);
//
//        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);
//        Institucion current_institucion = getCurrentInstitucion();
//
//        if (current_institucion != null) {
//            refreshSpinner(spinner, current_institucion);
//            spinner.setOnTouchListener(SpinnerOnTouch);
//        }
//        return true;
//    }
//
//    /**
//     * Refreshes the specified spinner with a new institution name
//     * @param spinner The spinner
//     * @param current_institucion The institution from which the name is taken
//     */
//    private void refreshSpinner(Spinner spinner, Institucion current_institucion) {
//        List<String> spinnerDataList;
//        spinnerDataList = new ArrayList<>();
//        if (current_institucion.getNombre() != null) {
//            spinnerDataList.add(current_institucion.getNombre());
//            ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
//                    R.layout.spinner_item, spinnerDataList);
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            spinner.setAdapter(adapter);
//        }
//    }
//
//    /**
//     * CanPostItemCheck
//     */
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
////        sharedPref = getSharedPreferences(getApplicationContext().
////                getString(R.string.cache_objects), Context.MODE_PRIVATE);
////        if (getCurrentFragment() != null && !getCurrentFragment().equals(FRAGMENT_CALENDARIO)) {
////            //TODO: voy a poder sacar esto cuando saque el listener de can_post
////            userCanPostItemCheck(menu);
////        }
//        badgeManager.updateBadges();
//        return super.onPrepareOptionsMenu(menu);
//    }
////
////    /**
////     * Enables the NewPost button if the current user should be able to post.
////     * @param menu The menu
////     */
////    private void userCanPostItemCheck(Menu menu) {
////        if (!sharedPref.getString("user_canpost", "").equals("")) {
////            try {
////                JSONArray canPost = new JSONArray(sharedPref.getString("user_canpost", ""));
////                if(canPost.toString().equals("[]")) {
////                    menu.findItem(R.id.action_settings).setVisible(false);
////                } else {
////                    menu.findItem(R.id.action_settings).setVisible(true);
////                }
////            } catch (JSONException e) {
////                e.printStackTrace();
////            }
////        }
////    }
//
//    /**
//     * OnActionBar item click logic
//     * @param item The selected item
//     */
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
////        if (id == R.id.action_settings) {
////            if (getCurrentFragment().equals(FRAGMENT_INBOX)
////                    || getCurrentFragment().equals(FRAGMENT_MESSAGE_SEARCH)) {
////                Bundle extras = new Bundle();
////                extras.putString("origin", "message");
////                SearchFragment searchFragment = new SearchFragment();
////                searchFragment.setArguments(extras);
////                replaceFragment(R.id.fragment_main, searchFragment, "Message" + FRAGMENT_SEARCH);
////            }
////            else {
////                Intent intent = new Intent(this, MakeAPostActivity.class);
////                startActivity(intent);
////            }
////        }
//
//        if(id == android.R.id.home){
//            Bundle extras = new Bundle();
//            extras.putString("origin", "default");
//            SearchFragment searchFragment = new SearchFragment();
//            searchFragment.setArguments(extras);
//            replaceFragment(R.id.fragment_main, searchFragment, FRAGMENT_SEARCH);
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        LayoutInflater inflator = LayoutInflater.from(this);
//        View v = inflator.inflate(R.layout.actionbar_title, null);
////        v.findViewById(R.id.action_bar_iv_logo).setVisibility(View.GONE);
//
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setDisplayShowTitleEnabled(false);
//        actionBar.setCustomView(v);
//        badgeManager.updateBadges();
//    }
//
//
//    public InboxFragment getInboxTabFragment() {
//        return (InboxFragment) fragment_tab_messages;
//    }
//    public NotificationsFragment getFragmentTabNotifications() {
//        return (NotificationsFragment) fragment_tab_notifications;
//    }
//}