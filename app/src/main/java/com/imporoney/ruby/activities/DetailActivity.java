package com.imporoney.ruby.activities;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.imporoney.ruby.R;
import com.imporoney.ruby.application.MyApplication;
import com.imporoney.ruby.modules.ItemDetail;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.scrollView)
    NestedScrollView scrollView;
    @Bind(R.id.detail_logo)
    ImageView detail_logo;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.phone)
    TextView phone;
    @Bind(R.id.address)
    TextView address;
    @Bind(R.id.relate_info)
    TextView relate_info;
    @Bind(R.id.detail_image)
    ImageView detail_image;
    @Bind(R.id.detail_text)
    TextView detail_text;


    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        ButterKnife.bind(this);
        id=getIntent().getExtras().getInt("id");
        init(id);
    }

    @OnClick(R.id.btn_relate_shop)
    public void shopRelate(){
        Intent i = new Intent();
        //the type of latitude and longitude is double

        Bundle conData = new Bundle();

        i.putExtras(conData);
        setResult(RESULT_OK, i);
    }

    @OnClick(R.id.btn_similar_shop)
    public void shopSimilar(){
        Intent i = new Intent();
        //the type of latitude and longitude is double

        Bundle conData = new Bundle();

        i.putExtras(conData);
        setResult(RESULT_OK, i);

    }
    private static String TAG = "volley";
    private ItemDetail itemDetail;

    private void init(int id) {
        String url = "http://115.159.127.13:3000/things/detail_list/jsonData=json?item_id=" + id;
        JsonArrayRequest jsonObjReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        try {
                            itemDetail = new Gson().fromJson(response.getJSONObject(0).toString(), ItemDetail.class);
                            Log.d(TAG, itemDetail.toString());
                            Picasso.with(getApplicationContext()).load(MyApplication.baseUrl+itemDetail.getHeadlogo().getUrl())
                                    .into(detail_logo);
                            phone.setText("联系方式：" + itemDetail.getPhone());
                            address.setText("位置：" + itemDetail.getAddress());
                            title.setText(itemDetail.getName());
                            relate_info.setText(itemDetail.getImage_explain());
                            detail_text.setText(itemDetail.getThing_details().get(0).getDescription());
                            Picasso.with(getApplicationContext()).load(MyApplication.baseUrl+itemDetail.getThing_details().get(0).getImage().getUrl())
                                    .into(detail_image);
                            setupToolBar();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });
        MyApplication.getInstnce().addToRequestQueue(jsonObjReq);

    }

    private void setupToolBar() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setupToolBar(toolbar,itemDetail.getName());
        collapsingToolbar.setTitle(itemDetail.getName());
        collapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent));
     /*   toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.activity.onBackPressed();
            }
        });*/
    }

    @OnClick(R.id.like)
    public void onLike(){

    }

    @OnClick(R.id.get_coupon)
    public void getCoupon(){

    }

    public static void getLike(int thing_id, int user_id) {
        String url = "http://115.159.127.13:3000/users/collect/jsonData=json?thing_id=" + thing_id +
                "&user_id=" + user_id;
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });
        MyApplication.getInstnce().addToRequestQueue(jsonObjReq);
    }

    public void getCoupon(int coupon_id, int user_id) {
        String url = "http://115.159.127.13:3000/users/get_coupon/jsonData=json?coupon_id=" + coupon_id +
                "&user_id=" + user_id;
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });
        MyApplication.getInstnce().addToRequestQueue(jsonObjReq);
    }
}
