package com.imporoney.ruby.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ZoomControls;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.imporoney.ruby.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MapActivity extends AppCompatActivity {

    @Bind(R.id.bmapView)
    MapView mMapView;
    BaiduMap mBaiduMap;
    LocationClient mLocClient;

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
            LatLng ll = new LatLng(location.getLatitude(),
                    location.getLongitude());
            MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
            mBaiduMap.animateMapStatus(u);
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
//        setContentView(R.layout.activity_map);
        //ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent.hasExtra("x") && intent.hasExtra("y")) {
            // 当用intent参数时，设置中心点为指定点
            Bundle b = intent.getExtras();
            LatLng p = new LatLng(b.getDouble("y"), b.getDouble("x"));
            mMapView = new MapView(this,
                    new BaiduMapOptions().mapStatus(new MapStatus.Builder()
                            .target(p)
                            .zoom(16).build()));
        } else {
            mMapView = new MapView(this, new BaiduMapOptions());
        }
        setContentView(mMapView);
        mMapView.showScaleControl(false);// 不显示默认比例尺控件
        mMapView.showZoomControls(false);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        mMapView.setClickable(false);
        mMapView.setEnabled(false);
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(15));
        final MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.zoomTo(17);

        MyLocationListenner myListener=new MyLocationListenner();

        mBaiduMap.setMyLocationEnabled(true);
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);// 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();
        mBaiduMap.setOnMapLoadedCallback(new BaiduMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                Log.d("MapListener", "onMapLoaded");

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

                            }
                        });
                    }
                }, 3000);
            }
        });
    }

    @Override
    protected void onDestroy() {
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        // 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView = null;

        super.onDestroy();

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
}
