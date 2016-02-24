package com.imporoney.ruby.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imporoney.ruby.activities.MainDrawerActivity;
import com.imporoney.ruby.utils.NestedScrollManager;
import com.imporoney.ruby.R;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CouponFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CouponFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CouponFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;



    public CouponFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
    // * @param param1 Parameter 1.
    // * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CouponFragment newInstance() {
        CouponFragment fragment = new CouponFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setFragment();
        if (getArguments() != null) {
        }
    }

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.scrollView)
    NestedScrollView scrollView;

    NestedScrollManager manager=new NestedScrollManager();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_coupon, container, false);
        ButterKnife.bind(this,v);
        setupToolBar();
        manager.attach(scrollView);
        manager.addView(getActivity().findViewById(R.id.fab), NestedScrollManager.Direction.DOWN);
        setManager();
        return v;
    }

    private void setupToolBar(){
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setToolBar();
        collapsingToolbar.setTitle("优惠");
        collapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(getActivity(), android.R.color.transparent));
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Toolbar uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
            mListener.onFragmentInteraction(toolbar);
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
