package com.imporoney.ruby.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.baidu.mapapi.SDKInitializer;
import com.imporoney.ruby.modules.User;
import com.imporoney.ruby.utils.LruBitmapCache;

/**
 * Created by Zero on 12/18/2015.
 */
public class MyApplication extends Application {

    public static final String TAG=MyApplication.class.getSimpleName();
    public static final String baseUrl="http://115.159.127.13:3000";

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private User user;

    private static MyApplication instnce;

    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());
        instnce=this;
        login();
    }

    public static synchronized MyApplication getInstnce(){
        return instnce;
    }

    public RequestQueue getmRequestQueue(){
        if (mRequestQueue ==null){
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public ImageLoader getmImageLoader() {
        getmRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getmRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getmRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public void login(){
        SharedPreferences sharedpreferences = getSharedPreferences(TAG, Context.MODE_PRIVATE);
        String phone=sharedpreferences.getString("phone","");
        String password=sharedpreferences.getString("password","");
        if (phone!=""&&password!=""){
            user.setPassword(password);
            user.setPhone(phone);
        }else {

        }
    }

    public void signup(){

    }
}
