package com.example.km.genericapp.activities;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.km.genericapp.R;
import com.example.km.genericapp.adapters.NavigationCustomAdapter;
import com.example.km.genericapp.fragments.MainFragment;
import com.example.km.genericapp.fragments.PostsFragment;
import com.example.km.genericapp.fragments.RecipesFragment;
import com.example.km.genericapp.fragments.SettingsFragment;
import com.example.km.genericapp.fragments.VersionsFragment;
import com.example.km.genericapp.models.navigation.NavigationDrawerItem;
import com.example.km.genericapp.utilities.SnackbarHelper;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    private static final int HOME_ITEM = 0;
    private static final int POSTS_ITEM = 1;
    private static final int RECIPES_ITEM = 2;
    private static final int VERSIONS_ITEM = 3;
    private static final int SETTINGS_ITEM = 4;
    private static final int DRAWER_ITEMS_COUNT = 5;

    private ActionBar actionBar;
    private String[] navigationDrawerItemTitles;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private LinearLayout drawerPanel;
    private TextView navigationDrawerHeaderTitle;
    private ActionBarDrawerToggle drawerToggle;
    FloatingActionButton fab;
    private CompositeDisposable observers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupNavigationDrawer();
        setupActionBar();
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SnackbarHelper.showSnackbar(MainActivity.this, view, getString(R.string.fab_click_message));
            }
        });
        LoadHomeFragment();
    }

    private void LoadHomeFragment() {
        LoadPostsFragment();
        setActionBarTitle(getString(R.string.app_name));
    }

    private void LoadMainFragment() {
        Fragment fragment = new MainFragment();
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        }
    }

    private void LoadPostsFragment() {
        Fragment fragment = new PostsFragment();
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        }
    }

    private void LoadRecipesFragment() {
        Fragment fragment = new RecipesFragment();
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        }
    }

    private void LoadVersionsFragment() {
        Fragment fragment = new VersionsFragment();
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        }
    }

    private void LoadSettingsFragment() {
        SettingsFragment fragment = new SettingsFragment();
        if (fragment != null) {
            setupViewObservers(fragment);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        }
    }

    private void setupViewObservers(SettingsFragment settingsFragment) {
        if (observers == null) {
            observers = new CompositeDisposable();
        }
        observers.add(settingsFragment.launchPosts()
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        LoadPostsFragment();
                        setActionBarTitle(getString(R.string.action_posts));
                    }
                }));
        observers.add(settingsFragment.launchRecipes()
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        LoadRecipesFragment();
                        setActionBarTitle(getString(R.string.action_recipes));
                    }
                }));
    }

    private void setActionBarTitle(final String title) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                actionBar.setTitle(title);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
                return true;
            case R.id.action_home:
                LoadHomeFragment();
                setActionBarTitle(getString(R.string.app_name));
                return true;
            case R.id.action_posts:
                LoadPostsFragment();
                setActionBarTitle(getString(R.string.action_posts));
                return true;
            case R.id.action_recipes:
                LoadRecipesFragment();
                setActionBarTitle(getString(R.string.action_recipes));
                return true;
            case R.id.action_versions:
                LoadVersionsFragment();
                setActionBarTitle(getString(R.string.action_versions));
                return true;
            case R.id.action_settings:
                LoadSettingsFragment();
                setActionBarTitle(getString(R.string.action_settings));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupNavigationDrawer() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.title_drawer_open,
                R.string.title_drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        // Set the drawer toggle as the DrawerListener
        drawerLayout.setDrawerListener(drawerToggle);
        navigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_items_array);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);
        drawerPanel = (LinearLayout) findViewById(R.id.drawer_panel);
        navigationDrawerHeaderTitle = (TextView) findViewById(R.id.navigationDrawerHeaderTitle);
        NavigationDrawerItem[] drawerItem = new NavigationDrawerItem[DRAWER_ITEMS_COUNT];
        drawerItem[0] = new NavigationDrawerItem(R.drawable.ic_home, navigationDrawerItemTitles[0]);
        drawerItem[1] = new NavigationDrawerItem(R.drawable.ic_posts, navigationDrawerItemTitles[1]);
        drawerItem[2] = new NavigationDrawerItem(R.drawable.ic_search, navigationDrawerItemTitles[2]);
        drawerItem[3] = new NavigationDrawerItem(R.drawable.ic_versions, navigationDrawerItemTitles[3]);
        drawerItem[4] = new NavigationDrawerItem(R.drawable.ic_settings, navigationDrawerItemTitles[4]);
        NavigationCustomAdapter adapter = new NavigationCustomAdapter(this, R.layout.navigation_item_row, drawerItem);
        drawerList.setAdapter(adapter);
        drawerList.setOnItemClickListener(new DrawerItemClickListener());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    protected void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(R.string.app_name);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer_menu);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        switch (position) {
            case HOME_ITEM:
                LoadHomeFragment();
                break;
            case POSTS_ITEM:
                LoadPostsFragment();
                break;
            case RECIPES_ITEM:
                LoadRecipesFragment();
                break;
            case VERSIONS_ITEM:
                LoadVersionsFragment();
                break;
            case SETTINGS_ITEM:
                LoadSettingsFragment();
                break;
            default:
                break;
        }
        drawerList.setItemChecked(position, true);
        drawerList.setSelection(position);
        if (actionBar != null) {
            actionBar.setTitle(navigationDrawerItemTitles[position]);
        }
        drawerLayout.closeDrawer(drawerPanel);
    }
}
