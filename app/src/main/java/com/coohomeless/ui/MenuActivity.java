package com.coohomeless.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coohomeless.R;
import com.coohomeless.ui.fragments.LocalizationFragment;
import com.coohomeless.ui.fragments.MapFragment;
import com.coohomeless.ui.fragments.SettingFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;

public class MenuActivity extends AppCompatActivity {

    private ActionBarDrawerToggle mDrawerToggle;
    private FirebaseAuth mAuth;

    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @BindView(R.id.navigation) NavigationView mNavigationView;
    @BindView(R.id.toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mAuth = FirebaseAuth.getInstance();

        // As we're using a Toolbar, we should retrieve it and set it to be our ActionBar
        setSupportActionBar(mToolbar);

        // Display icon in the toolbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        mDrawerLayout.setFitsSystemWindows(true);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        if (mNavigationView != null) {
            setupNavigationView(mNavigationView);
        }

        View headerView = mNavigationView.getHeaderView(0);
        TextView userName = headerView.findViewById(R.id.txtName);
        FirebaseUser user = mAuth.getCurrentUser();
        userName.setText(user.getDisplayName());

        selectItem(0);
    }

    private void setupNavigationView(NavigationView mNavigationView) {
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.drawer_menu, menu);
        return true;
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
        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}
