package com.imporoney.ruby.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.gordonwong.materialsheetfab.MaterialSheetFab;
import com.gordonwong.materialsheetfab.MaterialSheetFabEventListener;
import com.imporoney.ruby.widget.Fab;
import com.imporoney.ruby.R;
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

public class MainActivity extends AppCompatActivity implements
        BaseFragment.OnFragmentInteractionListener{
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

    public static MainActivity activity;

    @Override
    public void onBackPressed() {
        if (! (currentFragment instanceof AppreciativeFragment)){
            getSupportFragmentManager().beginTransaction().replace(R.id.content, AppreciativeFragment.newInstance()).commitAllowingStateLoss();
        }else {
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
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupFragment();
        setupFab();
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
                getSupportFragmentManager().beginTransaction().replace(R.id.content,AboutFragment.newInstance()).commitAllowingStateLoss();
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

//    private void setupMenuItemClick(){
//        menuAty.setOnClickListener(this);
//        menuCard.setOnClickListener(this);
//        menuMap.setOnClickListener(this);
//        menuShake.setOnClickListener(this);
//        memuAbout.setOnClickListener(this);
//        menuShop.setOnClickListener(this);
//    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_bar; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Toolbar uri) {

    }


}
