package com.imporoney.ruby;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.imporoney.ruby.adapter.TabPagerAdapter;
import com.imporoney.ruby.fragment.CollectionFragment;
import com.imporoney.ruby.fragment.CollectionFragmentNew;
import com.imporoney.ruby.fragment.CouponFragment;
import com.imporoney.ruby.fragment.CouponFragmentNew;
import com.imporoney.ruby.fragment.CouponMapFragment;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Wu on 2015/4/16.
 */
public class FragmentParent extends Fragment implements AppBarLayout.OnOffsetChangedListener{

    String userId;
    @Bind(R.id.appbar)
    AppBarLayout appBarLayout;
    private TabPagerAdapter adapter;
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    @Bind(R.id.tabs)
    TabLayout tabLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    public static Fragment newInstance() {
        Fragment fragment = new FragmentParent();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tablayout, container, false);
        ButterKnife.bind(this, rootView);
        appBarLayout.addOnOffsetChangedListener(this);
        if (viewPager != null) {
            setupViewPager();
        }

        setupToolBar();
        return rootView;
    }
    private void setupToolBar(){
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        final ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("我的卡包");
    }

    private void setupViewPager() {
        Log.d("user", "setupViewPager" + userId);
        adapter = new TabPagerAdapter(getChildFragmentManager());
        adapter.addFragment(CollectionFragmentNew.newInstance(),"收藏");
        adapter.addFragment(CouponFragmentNew.newInstance(),"优惠卷");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStop() {
        super.onStop();
        appBarLayout.removeOnOffsetChangedListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    //swipe to refresh fix
    int index=0;
    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        index=i;
    }

    private void resetAppBar(){
        CoordinatorLayout.LayoutParams params=(CoordinatorLayout.LayoutParams)appBarLayout.getLayoutParams();
        AppBarLayout.Behavior behavior=(AppBarLayout.Behavior)params.getBehavior();
        behavior.onNestedFling(coordinatorLayout,appBarLayout,null,0,-1000,true);
    }
}