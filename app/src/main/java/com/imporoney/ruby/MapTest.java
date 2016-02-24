package com.imporoney.ruby;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.*;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.*;


/**
 * Created by Administrator on 2015/7/3.
 */
public class MapTest extends AppCompatActivity {
    public static String Latitude = "Latitude";
    public static String Longtitude = "Longtitude";
    public static String Address = "Address";
    private String maddress;
    /**
     * Called when the activity is first created.
     */
    // 定位相关
    LocationClient mLocClient;
    MyLocationListenner myListener = new MyLocationListenner();



    MapView mMapView;
    BaiduMap mBaiduMap;
    boolean isFirstLoc = true;// 是否首次定位
    GeoCoder mSearch = null; // 反地址搜索模块

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        mMapView.showScaleControl(false);// 不显示默认比例尺控件
        mMapView.showZoomControls(false);
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(15));

        //GPS定位初始化
        InitGPS();

        // 初始化搜索模块，注册事件监听
        mSearch = GeoCoder.newInstance();
        mSearch.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {

                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    Toast.makeText(MapTest.this, "抱歉，未能找到对应地址", Toast.LENGTH_LONG)
                            .show();
                    return;
                }
                maddress = result.getAddress();

                Toast.makeText(MapTest.this,maddress, Toast.LENGTH_LONG)
                        .show();
            }
        });


    }

    private void InitGPS() {
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);// 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);    //设置定位间隔
        option.setIsNeedAddress(true);
        option.setIgnoreKillProcess(false);
        mLocClient.setLocOption(option);
        mLocClient.start();
    }

    private void setLocationText(LatLng loc) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    private void initOverlay(LatLng original) {
        // add marker overlay



        mBaiduMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
            public void onMarkerDrag(Marker marker) {

                LatLng loc = new LatLng(marker.getPosition().latitude, marker.getPosition().longitude);
                setLocationText(loc);
            }

            public void onMarkerDragEnd(Marker marker) {
                LatLng loc = new LatLng(marker.getPosition().latitude, marker.getPosition().longitude);

                setLocationText(loc);
                mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(loc));
                setLocationResult(loc);

                Toast.makeText(
                        MapTest.this,
                        "You Choose Location: [" + loc.latitude + ", "
                                + loc.longitude + "]",
                        Toast.LENGTH_LONG).show();
            }

            public void onMarkerDragStart(Marker marker) {
            }
        });
    }


    private void setLocationResult(LatLng local) {
        Intent i = new Intent();
        Log.d("MapTest","setLocal");
        //the type of latitude and longitude is double

        Bundle conData = new Bundle();
        conData.putDouble(MapTest.Latitude, local.latitude);
        conData.putDouble(MapTest.Longtitude, local.longitude);
        conData.putString(MapTest.Address, maddress);
        i.putExtras(conData);
        setResult(RESULT_OK, i);
    }

    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {

            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null)
                return;
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);

                isFirstLoc = false;
                LatLng loc = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(loc);
                mBaiduMap.animateMapStatus(u);
                maddress = location.getAddrStr();
                setLocationResult(loc);
                initOverlay(loc);
                setLocationText(loc);
                mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(loc));

            final MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.zoomTo(17);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mBaiduMap.animateMapStatus(mapStatusUpdate, 5000);
                    mBaiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
                        @Override
                        public void onMapStatusChangeStart(MapStatus mapStatus) {
                            Log.d("MapListener", "onMapStatusChangeStart");

                        }

                        @Override
                        public void onMapStatusChange(MapStatus mapStatus) {
                            Log.d("MapListener", "onMapStatusChange");

                        }

                        @Override
                        public void onMapStatusChangeFinish(MapStatus mapStatus) {
                            Log.d("MapListener", "onMapStatusChangeFinish");
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                }
                            },1000);
                        }
                    });
                }
            }, 2000);
        }
    }
    private void finishWithResult(){
        Bundle conData=new Bundle();
//        conData.putString("Latitute",);
    }
}
