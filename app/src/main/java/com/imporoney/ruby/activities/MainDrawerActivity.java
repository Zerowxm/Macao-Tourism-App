package com.imporoney.ruby.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.gordonwong.materialsheetfab.MaterialSheetFab;
import com.gordonwong.materialsheetfab.MaterialSheetFabEventListener;
import com.imporoney.ruby.*;
import com.imporoney.ruby.LoginActivity;
import com.imporoney.ruby.fragment.AboutFragmentNew;
import com.imporoney.ruby.fragment.CollectionFragmentSize;
import com.imporoney.ruby.fragment.CouponFragment;
import com.imporoney.ruby.fragment.CouponMapFragmentNew;
import com.imporoney.ruby.fragment.ShopFragmentNew;
import com.imporoney.ruby.utils.VolleyUtil;
import com.imporoney.ruby.widget.Fab;
import com.imporoney.ruby.fragment.AboutFragment;
import com.imporoney.ruby.fragment.AppreciativeFragment;
import com.imporoney.ruby.fragment.BaseFragment;
import com.imporoney.ruby.fragment.CollectionFragment;
import com.imporoney.ruby.fragment.CouponMapFragment;
import com.imporoney.ruby.fragment.ShakeFragment;
import com.imporoney.ruby.fragment.ShopFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainDrawerActivity extends AppCompatActivity implements
        BaseFragment.OnFragmentInteractionListener{

    public static Toolbar toolbar;
    @Bind(R.id.menu_bar)
    ImageView menuBar;
    @Bind(R.id.memu_panel)
    FrameLayout menuPanel;
    @Bind(R.id.fab)
    Fab fab;
    @Bind(R.id.fab_sheet)
    View sheetView;
    @Bind(R.id.overlay)
    View overlay;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    public static MainDrawerActivity activity;

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
            Log.d("BackPress","closeDrawer");
        }
        else if (currentFragment instanceof CouponFragment){
            getSupportFragmentManager().beginTransaction().replace(R.id.content, CollectionFragment.newInstance()).commitAllowingStateLoss();

        }
        else if (! (currentFragment instanceof AppreciativeFragment)){
            getSupportFragmentManager().beginTransaction().replace(R.id.content, AppreciativeFragment.newInstance()).commitAllowingStateLoss();
            Log.d("BackPress","beginTransaction");

        }else {
            Log.d("BackPress","onBackPressed");

            super.onBackPressed();
        }

    }

  

    public static Fragment currentFragment;

    public static void setCurrentFragment(Fragment fragment){
        currentFragment=fragment;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);
        ButterKnife.bind(this);
        setupFragment();
        setupFab();
        setupDrawer();
        activity=this;
    }



    private void setupFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.content, AppreciativeFragment.newInstance()).commitAllowingStateLoss();
    }
    private MaterialSheetFab materialSheetFab;

    private void setupFab(){
        int sheetColor = ContextCompat.getColor(this,R.color.background_dim_overlay);
        int fabColor = ContextCompat.getColor(this,R.color.accent);

        // Initialize material sheet FAB
        materialSheetFab = new MaterialSheetFab<>(fab, sheetView, overlay,
                sheetColor, fabColor);

        materialSheetFab.setEventListener(new MaterialSheetFabEventListener() {
            @Override
            public void onShowSheet() {
                Log.d("Fab","onShowSheet");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        menuPanel.setVisibility(View.VISIBLE);
                        menuPanel.animate().alpha(1).setDuration(300).start();
                    }
                },450);
            }

            @Override
            public void onSheetShown() {
                Log.d("Fab","onSheetShown");

            }

            @Override
            public void onHideSheet() {
                menuPanel.animate().alpha(0).setDuration(200).start();
                menuPanel.setVisibility(View.INVISIBLE);
                Log.d("Fab","onHideSheet");
            }

            @Override
            public void onSheetHidden() {
                Log.d("Fab","onSheetHidden");
            }
        });

        //setupMenuItemClick();
    }

    public View getContent(){
        return content;
    }

    @Bind(R.id.action_about)
    ImageView memuAbout;
    @Bind(R.id.action_aty)
    ImageView menuAty;
    @Bind(R.id.action_card)
    ImageView menuCard;
    @Bind(R.id.action_map)
    ImageView menuMap;
    @Bind(R.id.action_shake)
    ImageView menuShake;
    @Bind(R.id.action_shop)
    ImageView menuShop;
    @Bind(R.id.content)
    View content;

    @OnClick({R.id.action_shake,R.id.action_map,R.id.action_card
    ,R.id.action_shop,R.id.action_about,R.id.action_aty})
    public void itemClick(ImageView view){
        materialSheetFab.hideSheet();
        switch (view.getId()){
            case R.id.action_about:
                getSupportFragmentManager().beginTransaction().replace(R.id.content, AboutFragment.newInstance()).commitAllowingStateLoss();
                break;
            case R.id.action_shake:
                getSupportFragmentManager().beginTransaction().replace(R.id.content,ShakeFragment.newInstance()).commitAllowingStateLoss();

                break;
            case R.id.action_aty:
                getSupportFragmentManager().beginTransaction().replace(R.id.content, AppreciativeFragment.newInstance()).commitAllowingStateLoss();
                break;
            case R.id.action_card:
                getSupportFragmentManager().beginTransaction().replace(R.id.content, CollectionFragment.newInstance()).commitAllowingStateLoss();
                break;
            case R.id.action_map:
                getSupportFragmentManager().beginTransaction().replace(R.id.content, CouponMapFragment.newInstance()).commitAllowingStateLoss();
                break;
            case R.id.action_shop:
                getSupportFragmentManager().beginTransaction().replace(R.id.content, ShopFragment.newInstance()).commitAllowingStateLoss();
                break;

            default:
                Log.d("menu","default"+view.getId());

        }
    }




    private void setupDrawer(){
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout,toolbar , R.string.font_simple, R.string.font_big);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        setupNavigationView();
    }
    private void setupDrawerContent(final NavigationView navigationView, final Context context) {

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        for (int index = 0; index < navigationView.getMenu().size(); index++) {
                            navigationView.getMenu().getItem(index).setChecked(false);
                        }

                        drawerLayout.closeDrawers();
                        switch (menuItem.getItemId()) {
                            case R.id.nav_appreciate:
                                getSupportFragmentManager().beginTransaction().replace(R.id.content,AppreciativeFragment.newInstance()).commitAllowingStateLoss();
                                menuItem.setChecked(true);
                                return true;
                            case R.id.nav_shop:
                                getSupportFragmentManager().beginTransaction().replace(R.id.content, ShopFragmentNew.newInstance()).commitAllowingStateLoss();
                                menuItem.setChecked(true);
                                return true;
                            case R.id.nav_map:
                                getSupportFragmentManager().beginTransaction().replace(R.id.content, CouponMapFragmentNew.newInstance()).commitAllowingStateLoss();
                                menuItem.setChecked(true);
                                return true;
                            case R.id.nav_shake:
                                getSupportFragmentManager().beginTransaction().replace(R.id.content, ShakeFragment.newInstance()).commitAllowingStateLoss();
                                menuItem.setChecked(true);
                                return true;
                            case R.id.nav_card:
                                getSupportFragmentManager().beginTransaction().replace(R.id.content, CollectionFragment.newInstance()).commitAllowingStateLoss();
                                menuItem.setChecked(true);
                                return true;
                            case R.id.nav_about:
                                getSupportFragmentManager().beginTransaction().replace(R.id.content, FragmentParent.newInstance()).commitAllowingStateLoss();

                                return true;
                            case R.id.nav_user_setting:
                                getSupportFragmentManager().beginTransaction().replace(R.id.content, CollectionFragmentSize.newInstance()).commitAllowingStateLoss();

//                                startActivity(new Intent(MainDrawerActivity.this, com.imporoney.ruby.SettingsActivity.class));
                                return true;
                            default:
                                return true;
                        }
                    }
                });
    }
    private void setupNavigationView() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView, this);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onFragmentInteraction(Toolbar uri) {
        toolbar=uri;
    }

   /* @OnClick(R.id.user_name)
    public void onLogin(View view){
        startActivityForResult(new Intent(this, LoginActivity.class),1);
    }*/
}
