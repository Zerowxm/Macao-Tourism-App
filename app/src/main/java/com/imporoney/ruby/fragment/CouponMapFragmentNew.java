package com.imporoney.ruby.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imporoney.ruby.R;
import com.imporoney.ruby.activities.DetailActivity;
import com.imporoney.ruby.adapter.RecyclerviewAdapter;
import com.imporoney.ruby.application.MyApplication;
import com.imporoney.ruby.modules.Test;
import com.imporoney.ruby.modules.Thing;
import com.imporoney.ruby.picker.Section;
import com.imporoney.ruby.utils.NestedScrollManager;
import com.imporoney.ruby.utils.RecyclerItemClickListener;
import com.imporoney.ruby.utils.ScrollManager;
import com.imporoney.ruby.widget.WrappingLinearLayoutManager;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CouponMapFragmentNew#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CouponMapFragmentNew extends BaseFragment {
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

    private String type = "店铺";
    private String location = "1";

    public CouponMapFragmentNew() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * <p/>
     * // * @param param1 Parameter 1.
     * // * @param param2 Parameter 2.
     *
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CouponMapFragmentNew newInstance() {
        CouponMapFragmentNew fragment = new CouponMapFragmentNew();
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

    @Bind(R.id.recyclerview_list)
    RecyclerView recyclerView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
//    @Bind(R.id.scrollView)
//    NestedScrollView scrollView;
    @Bind(R.id.choose_loc)
    TextView chooseLoc;
    @Bind(R.id.vMasker)
    View vMasker;
    NestedScrollManager manager = new NestedScrollManager();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_coupon_map_new, container, false);
        ButterKnife.bind(this, v);
        setupToolBar();
//        manager.attach(scrollView);
//        manager.addView(getActivity().findViewById(R.id.fab), NestedScrollManager.Direction.DOWN);
        pvOptions = new OptionsPickerView<Section>(getActivity());

        setupOptions(pvOptions);

        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                vMasker.setVisibility(View.GONE);
                Log.d("Choose", "id" + options1 + " " + option2 + " " + options3);
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

    private void setupToolBar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setToolBar("优惠地图");
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

    private void setupOptions(OptionsPickerView pvOptions) {
        options1Items.add(new Section("圣安多尼堂区", 1));
        options1Items.add(new Section("家模堂区", 2));
        options1Items.add(new Section("圣方济各堂区", 3));
        options1Items.add(new Section("花地玛堂区", 4));
        options1Items.add(new Section("大堂区", 5));
        options1Items.add(new Section("风顺堂区", 6));
        options1Items.add(new Section("望德堂区", 7));
        options1Items.add(new Section("横琴区", 8));
        options1Items.add(new Section("金光大道", 9));
        options1Items.add(new Section("澳门大学", 10));

        pvOptions.setPicker(options1Items);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @OnClick({R.id.food_btn, R.id.tour_btn, R.id.fun_btn, R.id.shop_btn})
    public void typeClick(ImageView view) {
        switch (view.getId()) {
            case R.id.shop_btn:
                type = "店铺";
                break;
            case R.id.tour_btn:
                type = "旅游";
                break;
            case R.id.fun_btn:
                type = "娱乐";
                break;
            case R.id.food_btn:
                type = "美食";
                break;
        }
        Toast.makeText(getContext(),type,Toast.LENGTH_SHORT);
        if (location!=""){
            init();
        }
    }

    private static String TAG = "volley";
    private List<Thing> things;

    private void init() {
        String url = "http://115.159.127.13:3000/things/list/jsonData=json?kind=2";
        JsonArrayRequest jsonObjReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        List<Test> tests = new Gson().fromJson(response.toString(), new TypeToken<List<Test>>() {
                        }.getType());
                        try {
                            Log.d(TAG, response.getJSONObject(0).getJSONArray("Things").toString());
                            things = new Gson().fromJson(response.getJSONObject(0).getJSONArray("Things").toString(),
                                    new TypeToken<List<Thing>>() {
                                    }.getType());
                            setupRecyclerView(recyclerView);
                            for (Thing thing : things) {
                                Log.d(TAG, " " + thing.toString());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d(TAG, "1" + tests.get(0).toString());

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });
        MyApplication.getInstnce().addToRequestQueue(jsonObjReq);

    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new RecyclerviewAdapter(getActivity(),things));
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //((AppCompatActivity)getActivity()).getSupportFragmentManager().beginTransaction()
                //      .replace(R.id.content,DetailFragment.newInstance(things.get(position-1).getId())).commitAllowingStateLoss();
                getActivity().startActivity(new Intent(getActivity(), DetailActivity.class).putExtra("id", things.get(position - 1).getId()));

            }
        }));
    }

}
