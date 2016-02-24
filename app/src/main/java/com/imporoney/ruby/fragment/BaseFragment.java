package com.imporoney.ruby.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.imporoney.ruby.activities.MainActivity;
import com.imporoney.ruby.R;
import com.imporoney.ruby.activities.MainDrawerActivity;
import com.imporoney.ruby.utils.ScrollManager;

/**
 * Created by Zero on 11/28/2015.
 */
public class BaseFragment extends Fragment {
    ScrollManager manager=new ScrollManager();
    protected void setManager(){
        manager.showFab();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    void setFragment(){
        MainDrawerActivity.setCurrentFragment(this);
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Toolbar uri);
    }

    protected void setToolBar(String title){
        final ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(title);
    }

    protected void setToolBar(){
        final ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

    }


}
