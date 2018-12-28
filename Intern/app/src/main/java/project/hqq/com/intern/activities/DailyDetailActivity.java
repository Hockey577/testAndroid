package project.hqq.com.intern.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import project.hqq.com.intern.R;
import project.hqq.com.intern.bean.DailyBean;
import project.hqq.com.intern.bean.info.Constant;
import project.hqq.com.intern.http.HttpUrl;
import project.hqq.com.intern.http.XHttpUtils;
import project.hqq.com.intern.views.BaseActivity;

/**
 * Created by ZWY on 2017/6/1.
 */

public class DailyDetailActivity extends BaseActivity {
    private ImageView imback;
    private TextView tvblank4;
    private TextView tvblank;
    private TextView tvblank1;
    private TextView tvblank2;
    private TextView tvintro;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String jsonStr = (String) msg.obj;
            switch (msg.what) {
                case Constant.CONSTANT114:
                    DailyBean bean=gson.fromJson(jsonStr,DailyBean.class);
                    if(bean.getResultMessage().equals("操作正常")){
                        tvintro.setText(bean.getResultData().getRemarks());
                        tvblank.setText(bean.getResultData().getFinishedWork());
                        tvblank1.setText(bean.getResultData().getUnfinishedWork());
                        tvblank2.setText(bean.getResultData().getOtherWork());
                        tvblank4.setText(bean.getResultData().getAddTime());
                    }else{
                        shortShow(bean.getResultMessage());
                    }

                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_details);
        init();
        returnDailyRequest(getIntent().getIntExtra(Constant.KEY_DAILY_INFO,0));
    }

    private void init() {
        this.tvintro = (TextView) findViewById(R.id.tv_intro);
        this.tvblank2 = (TextView) findViewById(R.id.tv_blank2);
        this.tvblank1 = (TextView) findViewById(R.id.tv_blank1);
        this.tvblank = (TextView) findViewById(R.id.tv_blank);
        this.tvblank4 = (TextView) findViewById(R.id.tv_blank4);
        this.imback = (ImageView) findViewById(R.id.im_back);
        imback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void returnDailyRequest(int id){
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        XHttpUtils.post(HttpUrl.returnDailyRequest, gson.toJson(map), handler, Constant
                .CONSTANT114);
    }
}
