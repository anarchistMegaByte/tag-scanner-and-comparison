package com.anarchistmegabyte.helloworld;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.anarchistmegabyte.helloworld.Fragments.OCRFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount()==1)
        {
            super.onBackPressed();
        }
        else
        {
            getFragmentManager().popBackStackImmediate();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        Fragment fragment = null;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        boolean fragmentPopped = false;
        String backStateName;
        String fragmentTag;

        if (id == R.id.nav_ocr) {
            // Handle the camera action
            fragment = OCRFragment.newInstance();

        } else if (id == R.id.nav_flipkart_api) {

        } else if (id == R.id.nav_amazon_api) {

        } else if (id == R.id.nav_result) {

        } else if (id == R.id.nav_search_history) {

        }

        backStateName = fragment.getClass().getName();
        fragmentTag = fragment.getClass().getName();
        fragmentPopped = getFragmentManager().popBackStackImmediate(backStateName, 0);
        if (!fragmentPopped && getFragmentManager().findFragmentByTag(fragmentTag) == null) {
            FragmentManager fm = getFragmentManager();
            //fm.beginTransaction().add(R.id.flContent, fragment).addToBackStack(null).commit();
            //As a rule of thumb to follow:
            //Always replace fragments, never add them. And add the transaction to backstack to support the back button.
            fm.beginTransaction().replace(R.id.flContent, fragment, fragmentTag).addToBackStack(backStateName).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
