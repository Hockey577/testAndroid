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
import project.hqq.com.intern.bean.FeedbackBean;
import project.hqq.com.intern.bean.info.Constant;
import project.hqq.com.intern.http.HttpUrl;
import project.hqq.com.intern.http.XHttpUtils;
import project.hqq.com.intern.views.BaseActivity;

/**
 * Created by ZWY on 2017/6/1.
 */

public class FeedbackDetailActivity extends BaseActivity {
    private ImageView imback;
    private TextView tvtime;
    private TextView tvblank;
    private TextView tvgood;
    private TextView tvintro;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String jsonStr = (String) msg.obj;
            switch (msg.what) {
                case Constant.CONSTANT111:
                    FeedbackBean bean = gson.fromJson(jsonStr, FeedbackBean.class);
                    if (bean.getResultMessage().equals("操作正常")) {
                        if (bean.getResultData().getEvaluateType() == 1) {
                            tvgood.setText("好评");
                        } else if (bean.getResultData().getEvaluateType() == 0) {
                            tvgood.setText("建议改进");
                        }
                        tvblank.setText(bean.getResultData().getRecipientCon());
                        tvintro.setText(bean.getResultData().getEvaluateCon());
                        tvtime.setText(bean.getResultData().getAddTime());
                    } else {
                        shortShow(bean.getResultMessage());
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_detail);
        init();
        returnFeedbackRequest(getIntent().getIntExtra(Constant.KEY_FEEDBACK_INFO, 0));
    }

    private void init() {
        this.tvintro = (TextView) findViewById(R.id.tv_intro);
        this.tvblank = (TextView) findViewById(R.id.tv_blank);
        this.tvgood = (TextView) findViewById(R.id.tv_good);
        this.tvtime = (TextView) findViewById(R.id.tv_time);
        this.imback = (ImageView) findViewById(R.id.im_back);
        imback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void returnFeedbackRequest(int id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        XHttpUtils.post(HttpUrl.returnFeedbackRequest, gson.toJson(map), handler, Constant
                .CONSTANT111);
    }
}
