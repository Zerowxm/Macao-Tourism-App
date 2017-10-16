package com.imporoney.ruby.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imporoney.ruby.activities.DetailActivity;
import com.imporoney.ruby.activities.MainActivity;
import com.imporoney.ruby.R;
import com.imporoney.ruby.activities.MainDrawerActivity;
import com.imporoney.ruby.adapter.CardRecyclerviewAdapter;
import com.imporoney.ruby.application.MyApplication;
import com.imporoney.ruby.modules.Test;
import com.imporoney.ruby.modules.Thing;
import com.imporoney.ruby.utils.RecyclerItemClickListener;
import com.imporoney.ruby.utils.ScrollManager;
import com.imporoney.ruby.adapter.RecyclerviewAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AppreciativeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AppreciativeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppreciativeFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    public AppreciativeFragment() {
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
    public static AppreciativeFragment newInstance() {
        AppreciativeFragment fragment = new AppreciativeFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        MainDrawerActivity.setCurrentFragment(this);
        if (getArguments() != null) {
        }
    }


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.recyclerview_list)
    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_appreciate, container, false);
        ButterKnife.bind(this, v);
        setupToolBar();
        init();
        return v;
    }

    private void setupToolBar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setToolBar("激赏活动");
        collapsingToolbar.setTitle("激赏活动");
        collapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(getActivity(), android.R.color.transparent));
        collapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(getActivity(), android.R.color.transparent));
    }


    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new CardRecyclerviewAdapter(getActivity()));
//        recyclerView.setAdapter(new RecyclerviewAdapter(getActivity(), things));
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                /*((AppCompatActivity)getActivity()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content,DetailFragment.newInstance(things.get(position-1).getId())).commitAllowingStateLoss();
                */
                getActivity().startActivityForResult(new Intent(getActivity(), DetailActivity.class).putExtra("id", things.get(position - 1).getId()),1000);

            }
        }));
        manager.attach(recyclerView);
        manager.addView(getActivity().findViewById(R.id.fab), ScrollManager.Direction.DOWN);
        setManager();
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

    private static String TAG = "volley";
    private List<Thing> things;
    private String json = "[{\"id\":1,\"address\":\"大三巴街B号24座09\",\"name\":\"澳门大街\",\"headlogo\":{\"url\":\"/uploads/thing/headlogo/1/1.png\"},\"phone\":\"15959377999.0\"},{\"id\":3,\"address\":\"大生里中山路22号\",\"name\":\"阳光百货\",\"headlogo\":{\"url\":\"/uploads/thing/headlogo/3/3.png\"},\"phone\":\"15959377999.0\"},{\"id\":4,\"address\":\"我家在东北\",\"name\":\"东北嘎达\",\"headlogo\":{\"url\":\"/uploads/thing/headlogo/4/4.png\"},\"phone\":\"15959377999.0\"},{\"id\":5,\"address\":\"北京天安门555\",\"name\":\"北京天安门\",\"headlogo\":{\"url\":\"/uploads/thing/headlogo/5/5.png\"},\"phone\":\"15959377999.0\"},{\"id\":6,\"address\":\"大三巴街B号24座09\",\"name\":\"澳门大街\",\"headlogo\":{\"url\":\"/uploads/thing/headlogo/6/1.png\"},\"phone\":\"15959377999.0\"},{\"id\":9,\"address\":\"我家在东北\",\"name\":\"东北嘎达\",\"headlogo\":{\"url\":\"/uploads/thing/headlogo/9/4.png\"},\"phone\":\"15959377999.0\"},{\"id\":10,\"address\":\"北京天安门555\",\"name\":\"北京天安门\",\"headlogo\":{\"url\":\"/uploads/thing/headlogo/10/5.png\"},\"phone\":\"15959377999.0\"},{\"id\":11,\"address\":\"北京天安门555\",\"name\":\"北京天安门\",\"headlogo\":{\"url\":\"/uploads/thing/headlogo/11/5.png\"},\"phone\":\"15959377999.0\"}]";

    private void init() {
        things = new Gson().fromJson(json,
                new TypeToken<List<Thing>>() {
                }.getType());
        String url = "http://115.159.127.13:3000/things/list/jsonData=json?kind=1";
        JsonArrayRequest jsonObjReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "response:" + response.toString());
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
                            Log.d(TAG, "error" + e.toString());
                        }
                        Log.d(TAG, "1" + tests.get(0).toString());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null) {
                    // HTTP Status Code: 401 Unauthorized
                    Log.d(TAG, "Error: " + error.getMessage() + "network" + error.networkResponse.toString() +
                            "statusCode" + error.networkResponse.statusCode);
                }
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Log.d(TAG, "Error: " + error.getMessage());
            }
        });
        MyApplication.getInstnce().addToRequestQueue(jsonObjReq,TAG);

    }

}
