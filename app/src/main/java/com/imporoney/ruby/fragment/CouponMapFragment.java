package com.imporoney.ruby.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.imporoney.ruby.utils.NestedScrollManager;
import com.imporoney.ruby.R;
import com.imporoney.ruby.picker.Section;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CouponMapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CouponMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CouponMapFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    OptionsPickerView<Section> pvOptions;

    private ArrayList<Section> options1Items = new ArrayList<Section>();


    public CouponMapFragment() {
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
    public static CouponMapFragment newInstance() {
        CouponMapFragment fragment = new CouponMapFragment();
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
    @Bind(R.id.choose_loc)
    TextView chooseLoc;
    @Bind(R.id.vMasker)
    View vMasker;
    NestedScrollManager manager=new NestedScrollManager();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_coupon_map, container, false);
        ButterKnife.bind(this,v);
        setupToolBar();
        manager.attach(scrollView);
        manager.addView(getActivity().findViewById(R.id.fab), NestedScrollManager.Direction.DOWN);
        pvOptions = new OptionsPickerView<Section>(getActivity());

        //选项1
        options1Items.add(new Section(0,"广东","广东省，以岭南东道、广南东路得名","其他数据"));
        options1Items.add(new Section(1,"湖南","湖南省地处中国中部、长江中游，因大部分区域处于洞庭湖以南而得名湖南","芒果TV"));
        options1Items.add(new Section(3,"广西","嗯～～",""));

        pvOptions.setPicker(options1Items);
        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                vMasker.setVisibility(View.GONE);
                Log.d("Choose","id"+options1+" "+option2+ " "+options3);
                chooseLoc.setText(options1Items.get(options1).getName());
            }
        });
        chooseLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvOptions.show();
            }
        });

        manager.addView(getActivity().findViewById(R.id.fab), NestedScrollManager.Direction.DOWN);
        setManager();
        return v;
    }

    private void setupToolBar(){
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setToolBar();
        collapsingToolbar.setTitle("优惠地图");
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
