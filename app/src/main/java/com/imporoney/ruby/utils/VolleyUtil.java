package com.imporoney.ruby.utils;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imporoney.ruby.application.MyApplication;
import com.imporoney.ruby.modules.Test;
import com.imporoney.ruby.modules.Thing;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.android.volley.Request.*;

/**
 * Created by Zero on 2/1/2016.
 */
public class VolleyUtil {
    private static final String TAG = "volley";

    public static void getJsonObjectRequest(String url) {
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

    public static void getJsonArrayRequest(String url) {
        JsonArrayRequest jsonObjReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        List<Test> tests = new Gson().fromJson(response.toString(), new TypeToken<List<Test>>() {
                        }.getType());
                        try {
                            Log.d(TAG, response.getJSONObject(0).getJSONArray("Things").toString());
                            List<Thing> things = new Gson().fromJson(response.getJSONObject(0).getJSONArray("Things").toString(),
                                    new TypeToken<List<Thing>>() {
                                    }.getType());

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

    public static void getStringRequest(String url) {
        StringRequest stringRequest = new StringRequest(Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response);
                        List<Test> tests = new Gson().fromJson(response, new TypeToken<List<Test>>() {
                        }.getType());
                        Log.d(TAG, "1 " + tests.get(0).toString());

                        Log.d(TAG, "2 " + tests.toString());
                        for (Test test : tests) {
                            Log.d(TAG, test.toString());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

            }
        });
        MyApplication.getInstnce().addToRequestQueue(stringRequest);
    }

    public static void dealWithArray() {

    }

    public static void login(String phone, String password) {
        String url = "http://115.159.127.13:3000/sessions/create/jsonData=json?phone=" + phone +
                "&password=" + password;
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

    public static void signup(String phone) {
        String url = "http://115.159.127.13:3000/send_verification_code/jsonData=json?phone=" + phone;
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

    public static void signUp(String email, String phone, String password, String code) {
        String url = "http://115.159.127.13:3000/create/jsonData=json?phone=" + phone +
                "&password=" + password +
                "&email=" + email +
                "&code=" + code;
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

    public static void getUserCoupon(int user_id) {
        String url = "http://115.159.127.13:3000/users/my_coupon/jsonData=json?user_id=";
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

    public static void getUserCollectionDetail(int user_id) {
        String url = "http://115.159.127.13:3000/users/detail_list/jsonData=json?user_id=";

    }

    public static void getUserCollection(int user_id) {
        String url = "http://115.159.127.13:3000/users/my_card/jsonData=json?user_id=";
    }

    public static void getCoupon(int coupon_id, int user_id) {
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

    public static void getCouponMap(int block_id, int kind_id) {
        String url = "http://115.159.127.13:3000/things/coupon_map/jsonData=json?block_id=&kind_id=";
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

    public static void getCouponDetail(int coupon_id) {
        String url = "http://115.159.127.13:3000/users/coupon_detail/jsonData=json?coupon_id=";
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

    public static void getBarCode() {
        String url = "http://115.159.127.13:3000/code/generate/jsonData=json\n";
    }

    public static void shake(String longitude,String latitude){
        String url="http://115.159.127.13:3000/things/shake/jsonData=json?longitude&latitude=";
    }

    public static void getRelateShop(String longitude,String latitude){
        String url="http://115.159.127.13:3000/things/near_shop/jsonData=json?longitude=&latitude=";
    }

    public static void getSimilarShop(String type){
        String url="http://115.159.127.13:3000/things/same_shop/jsonData=json?type_detail=";
    }
}
