package com.imporoney.ruby.activities;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.imporoney.ruby.BaseActivity;
import com.imporoney.ruby.R;
import com.imporoney.ruby.utils.NestedScrollManager;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CouponActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.scrollView)
    NestedScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_coupon);
        ButterKnife.bind(this);
    }

    private void setupToolBar(){
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        collapsingToolbar.setTitle("优惠");
        collapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent));
    }
}
