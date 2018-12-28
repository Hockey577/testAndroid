package project.hqq.com.intern.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import project.hqq.com.intern.R;
import project.hqq.com.intern.UserId;
import project.hqq.com.intern.bean.PersonInfoGB;
import project.hqq.com.intern.bean.info.Constant;
import project.hqq.com.intern.http.HttpUrl;
import project.hqq.com.intern.http.XHttpUtils;
import project.hqq.com.intern.views.BaseActivity;

public class PunchActivity extends BaseActivity implements View.OnClickListener {
    private CheckBox cbtime1,cbtime2;
    private Button getLocation, getLocation1;
    private static final double EARTH_RADIUS = 6378137.0;
    private ImageView mBack, mClose;
    private int id = UserId.id;
    private TextView tvLocation, tvLocation1;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    String adress="浙江省杭州市";
    private AlertDialog dialog;
    public double distance;//距离
    public double lat;//纬度
    public double log;//经度
    private String time;//考勤时间
    private View dialogView;
    private ImageView im_icon;
    private Button punchBt1,punchBt2;
    private TextView tv_name, tv_depart;
    private TextView tv_ok1,tv_ok2;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String jsonStr = (String) msg.obj;
            switch (msg.what) {
                //显示个人信息
                case Constant.CONSTANT103:
                    PersonInfoGB personBean = gson.fromJson(jsonStr,
                            PersonInfoGB.class);
                    Glide.with(PunchActivity.this).load(personBean.getResultData().getHeadImg
                            ()).into(im_icon);
                    tv_depart.setText(personBean.getResultData().getSchoolName());
                    tv_name.setText(personBean.getResultData().getStudentName());
                    break;
                case Constant.CONSTANT118:

                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punch);
        //获取定位结果：
        mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        //可在其中解析amapLocation获取相应内容。
                        aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，
                        // 则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                        aMapLocation.getLatitude();//获取纬度
                        aMapLocation.getLongitude();//获取经度
                        adress = aMapLocation.getAddress();
                        lat = aMapLocation.getLatitude();
                        log = aMapLocation.getLongitude();
                        Log.d("qiqi", "" + lat + "," + log);
//                        LatLng latLng = new LatLng(location.getLatitude(), location
// .getLongitude());
//                        latLng = CoordinateUtil.transformFromWGSToGCJ(latLng);
//                        LatLng end = new LatLng(aMapLocation.getLatitude(),aMapLocation
// .getLongitude());
//                        end = CoordinateUtil.transformFromWGSToGCJ(end);
//                        Log.d("qunimade",""+end);
//                        distance = calculateDistance(latLng,end);
                    } else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，
                        // errInfo是错误信息，详见错误码表。
                        Log.e("AmapError", "location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo());
                    }
                }
            }
        };

        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
        initView();
        showPersonInfoRequest(id);
        returnPunchRequest();
    }

    private void initView() {
//        AMapLocation location = null;
//        location.setLatitude(120.35042);
//        location.setLongitude(30.318957);//杭电某区域
        getLocation = (Button) findViewById(R.id.get_location);
        getLocation1 = (Button) findViewById(R.id.get_location1);
        mBack = (ImageView) findViewById(R.id.im_back);
        tvLocation = (TextView) findViewById(R.id.tv_location);
        tvLocation1 = (TextView) findViewById(R.id.tv_location1);
        im_icon = (ImageView) findViewById(R.id.im_icon);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_depart = (TextView) findViewById(R.id.tv_from);
        punchBt1=(Button)findViewById(R.id.punch_bt1);
        punchBt2=(Button)findViewById(R.id.punch_bt2);
        cbtime1=(CheckBox)findViewById(R.id.cb_time);
        cbtime2=(CheckBox)findViewById(R.id.cb_time1);
        tv_ok1=(TextView)findViewById(R.id.tv_ok1);
        tv_ok2=(TextView)findViewById(R.id.tv_ok2);
        punchBt1.setOnClickListener(this);
        punchBt2.setOnClickListener(this);
        getLocation1.setOnClickListener(this);
        mBack.setOnClickListener(this);
        getLocation.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.im_back:
                finish();
                break;
            case R.id.get_location:
                distance = getDistance(120.35042, 30.318957, log, lat);
                tvLocation.setText(adress);
                shortShow("距离为" + distance + "米");
                break;
            case R.id.get_location1:
                distance = getDistance(120.35042, 30.318957, log, lat);
                tvLocation1.setText(adress);
                shortShow("距离为" + distance + "米");
                break;
            case R.id.punch_bt1:
                dialogShow();
                cbtime1.setChecked(true);
                tv_ok1.setVisibility(View.VISIBLE);
                addPunchRequest1();
                punchBt1.setVisibility(View.INVISIBLE);
                break;
            case R.id.punch_bt2:
                dialogShow();
                cbtime2.setChecked(true);
                tv_ok2.setVisibility(View.VISIBLE);
                addPunchRequest();
                punchBt2.setVisibility(View.INVISIBLE);
                break;
        }
    }



    //    public float calculateDistance(LatLng start, LatLng end){
//        return AMapUtils.calculateLineDistance(start,end);
//    }
    private TextView tv_time;

    public void dialogShow() {
        //提示框开始
        dialogView = getLayoutInflater().inflate(R.layout.punch_successful, null);
        mClose = (ImageView) dialogView.findViewById(R.id.im_close);
        tv_time = (TextView) dialogView.findViewById(R.id.tv_time);
        Date date = new Date();
        time = date.getHours() + ":" + date.getMinutes();
        tv_time.setText(time);
        dialog = new AlertDialog.Builder(this).create();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.width = (int) (getWindowManager().getDefaultDisplay().getWidth() * 0.8);
        params.height = (int) (getWindowManager().getDefaultDisplay().getHeight() * 0.25);
        dialog.getWindow().setAttributes(params);
        dialog.setView(dialogView);
        //dialog.getWindow().setContentView(dialogView);
        mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        //提示框结束
        tv_ok1.setText("打卡成功（打卡时间"+time+"）");
        tv_ok2.setText("打卡成功（打卡时间"+time+"）");
    }

    public static double getDistance(double longitude1, double latitude1,
                                     double longitude2, double latitude2) {
        double Lat1 = rad(latitude1);
        double Lat2 = rad(latitude2);
        double a = Lat1 - Lat2;
        double b = rad(longitude1) - rad(longitude2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(Lat1) * Math.cos(Lat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    private void showPersonInfoRequest(int id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        XHttpUtils.post(HttpUrl.personInfoRequest, gson.toJson(map), handler,
                Constant.CONSTANT103);
    }
    private void returnPunchRequest(){
        Map<String, Object> map = new HashMap<>();
        map.put("studentId", id);
        XHttpUtils.post(HttpUrl.returnAtListRequest, gson.toJson(map), handler,
                Constant.CONSTANT118);
    }
    private void addPunchRequest(){
        Map<String, Object> map = new HashMap<>();
        map.put("studentId", id);
        map.put("companyId",201701);
        map.put("latitude",lat +"");
        map.put("longitude",log +"");
        map.put("addTimePM",time);
        XHttpUtils.post(HttpUrl.addAtRequest, gson.toJson(map), handler,
                Constant.CONSTANT119);
    }

    private void addPunchRequest1() {
        Map<String, Object> map = new HashMap<>();
        map.put("studentId", id);
        map.put("companyId",201701);
        map.put("latitude",lat +"");
        map.put("longitude",log +"");
        map.put("addTimeAM",time);
        XHttpUtils.post(HttpUrl.addAtRequest, gson.toJson(map), handler,
                Constant.CONSTANT119);
    }
}

