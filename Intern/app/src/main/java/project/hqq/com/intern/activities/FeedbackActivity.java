package project.hqq.com.intern.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import project.hqq.com.intern.R;
import project.hqq.com.intern.UserId;
import project.hqq.com.intern.bean.RegisterResultGB;
import project.hqq.com.intern.bean.info.Constant;
import project.hqq.com.intern.http.HttpUrl;
import project.hqq.com.intern.http.XHttpUtils;
import project.hqq.com.intern.views.BaseActivity;

public class FeedbackActivity extends BaseActivity {
    private int id= UserId.id;
    private ImageView im_back;
    private Button submit;
    private CheckBox cb_good;
    private CheckBox cb_up;
    private EditText content;
    private RelativeLayout rl1;
    private TextView feedbackTo;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String jsonStr = (String) msg.obj;
            switch (msg.what) {
                case Constant.CONSTANT109:
                    RegisterResultGB bean=gson.fromJson(jsonStr,RegisterResultGB.class);
                    if(bean.getResultMessage().equals("操作成功")){
                        shortShow("提交成功");
                        finish();
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
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_feedback);

        initView();
        initListener();
    }

    private void initView() {
        im_back = (ImageView) findViewById(R.id.im_back);
        submit = (Button) findViewById(R.id.btn_submit);
        cb_good = (CheckBox) findViewById(R.id.cb_good);
        cb_up = (CheckBox) findViewById(R.id.cb_up);
        content = (EditText) findViewById(R.id.et_content);
        rl1 = (RelativeLayout) findViewById(R.id.rl_1);
        feedbackTo = (TextView) findViewById(R.id.tv_blank);

    }

    private void initListener() {
        im_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_up.isChecked() || cb_good.isChecked()) {
                    addFeedbackRequest();
                } else {
                    shortShow("需选择反馈类型");
                }
            }
        });
        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(FeedbackActivity.this, FeedbackToActivity.class),
                        Constant.REQUEST_CODE_FEEDBACK_TO);
            }
        });
        cb_good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb_up.setChecked(false);
            }
        });
        cb_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb_good.setChecked(false);
            }
        });
    }

    private void addFeedbackRequest() {
        Map<String, Object> map = new HashMap<>();
        map.put("sponsorId", id);
        map.put("evaluateMsg", cb_good.isChecked() ? 1 : 0);
        map.put("evaluateCon", content.getText().toString());
        map.put("recipientCon", feedbackTo.getText().toString());
        XHttpUtils.post(HttpUrl.addFeedbackRequest, gson.toJson(map), handler, Constant.CONSTANT109);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQUEST_CODE_FEEDBACK_TO) {
            if (resultCode == Constant.RESULT_CODE_FEEDBACK_TO) {
                feedbackTo.setText(data.getStringExtra(Constant.KEY_FEEDBACK_TO));
            }
        }
    }
}
