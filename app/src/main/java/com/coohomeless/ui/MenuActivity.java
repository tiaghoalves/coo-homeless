package com.coohomeless.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.coohomeless.R;
import com.coohomeless.ui.fragments.LocalizationFragment;
import com.coohomeless.ui.fragments.MapFragment;
import com.coohomeless.ui.fragments.SettingFragment;

public class MenuActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        NavigationView mNavigationView = (NavigationView) findViewById(R.id.navigation);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_map:
                        selectItem(0);
                        return true;
                    case R.id.action_location:
                        selectItem(1);
                        return true;
                    case R.id.action_settings:
                        selectItem(2);
                        return true;
                    default:
                        return true;
                }
            }
        });

        View headerView = mNavigationView.getChildAt(0);
        TextView userName = (TextView) findViewById(R.id.txtName);
        userName.setText("Chico Homeless");

        selectItem(0);
    }

    private void selectItem(int position) {
        Fragment fragment = null;
        String title = null;

        switch (position) {
            case 0:
                fragment = new MapFragment();
                title = "Mapa";
                break;
            case 1:
                fragment = new LocalizationFragment();
                title = "Localizações";
                break;
            case 2:
                fragment = new SettingFragment();
                title = "Configurações";
                break;
        }

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, fragment).commit();

        setTitle(title);
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
