package com.neotran.idictionary;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.neotran.idictionary.fragment.BaseFragment;
import com.neotran.idictionary.fragment.SearchFragment;
import com.neotran.idictionary.helper.BackgroundTask;
import com.neotran.idictionary.helper.RealmHelper;
import com.neotran.idictionary.helper.SystemHelper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BaseFragment.FragmentCallBacker {
    public static final int SEARCH_SCREEN = 1;
    private View mFragmentContainer;
    private View mWelcomeView;
    private BaseFragment mCurrentFragment = null;
    private DrawerLayout mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemHelper.onScreen(this);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setUpToolBar(toolbar);

        mWelcomeView = findViewById(R.id.welcome_view);
        mFragmentContainer = findViewById(R.id.fragment_container_view);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setUpDatabase();
    }
    private void setUpDatabase() {
        BackgroundTask task = new BackgroundTask();
        task.setOnTaskWorkListner(new BackgroundTask.OnTaskWorkListner() {
            @Override
            public Object onWork(Object... params) {
                RealmHelper.createWordsDatabase(MainActivity.this);
                // RealmHelper.createMeaningsDatabase(MainActivity.this);
                return null;
            }

            @Override
            public Object onComplete(Object param) {
                Log.v("setUpDatabase", "Completed");
                return null;
            }

            @Override
            public Object onProgress(Object... params) {
                return null;
            }
        });
        task.execute();
    }
    private void setUpToolBar(Toolbar toolBar) {
        setSupportActionBar(toolBar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();
    }
    public void loadScreen(int screenId) {
        switch (screenId) {
            case SEARCH_SCREEN:
                loadFragment(new SearchFragment());
                break;
        }
    }

    private void loadFragment(BaseFragment newFragment) {
        newFragment.setFragmentCallBacker(this);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container_view, newFragment).addToBackStack("");
        ft.commit();
        mCurrentFragment = newFragment;
    }

    @Override
    public void onBackPressed() {
        if(mDrawer != null && mDrawer.isDrawerOpen(GravityCompat.START)) {
            toggleDrawer(false);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(mWelcomeView != null) mWelcomeView.setVisibility(View.GONE);
        if(id == R.id.nav_camera) {
            loadScreen(SEARCH_SCREEN);
        } else if(id == R.id.nav_gallery) {

        } else if(id == R.id.nav_slideshow) {

        } else if(id == R.id.nav_manage) {

        } else if(id == R.id.nav_share) {

        } else if(id == R.id.nav_send) {

        }
        toggleDrawer(false);
        return true;
    }

    public void toggleDrawer(boolean openIt) {
        if(mDrawer != null) {
            if(openIt) {
                mDrawer.openDrawer(GravityCompat.END);
            } else {
                mDrawer.closeDrawer(GravityCompat.START);
            }
        }
    }

    @Override
    public void onFirstLoaded(BaseFragment fragment) {
        setUpToolBar(fragment.getToolBar());
    }
}
