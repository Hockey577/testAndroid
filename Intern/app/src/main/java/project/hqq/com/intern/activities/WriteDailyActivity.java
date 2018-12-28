package project.hqq.com.intern.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
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

public class WriteDailyActivity extends BaseActivity implements View.OnClickListener {
    private ImageView mBack;
    private RelativeLayout rl_today, rl_until, rl_coordinate, rl_remark;
    private int id = UserId.id;
    private TextView finish, unfinish, coordinate, remark;
    private Button submit;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String jsonStr = (String) msg.obj;
            switch (msg.what) {
                case Constant.CONSTANT112:
                    RegisterResultGB bean=gson.fromJson(jsonStr,RegisterResultGB.class);
                    if(bean.getResultMessage().equals("添加成功")){
                        shortShow(bean.getResultMessage());
                        finish();
                    }else {
                        shortShow(bean.getResultMessage());
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_daily);
        init();
    }

    private void init() {
        mBack = (ImageView) findViewById(R.id.im_back);
        rl_today = (RelativeLayout) findViewById(R.id.rl_today);
        rl_until = (RelativeLayout) findViewById(R.id.rl_until);
        rl_coordinate = (RelativeLayout) findViewById(R.id.rl_coordinate);
        rl_remark = (RelativeLayout) findViewById(R.id.rl_remark);
        finish = (TextView) findViewById(R.id.tv_finish);
        unfinish = (TextView) findViewById(R.id.tv_unfinish);
        coordinate = (TextView) findViewById(R.id.tv_coordinate);
        remark = (TextView) findViewById(R.id.tv_remark);
        submit = (Button) findViewById(R.id.btn_submit);
        rl_today.setOnClickListener(this);
        rl_until.setOnClickListener(this);
        rl_coordinate.setOnClickListener(this);
        rl_remark.setOnClickListener(this);
        mBack.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.im_back:
                finish();
                break;
            case R.id.rl_today:
                Intent intent1 = new Intent(WriteDailyActivity.this, ModifyFinishActivity.class);
                intent1.putExtra(Constant.KEY_DAILY_FINISH, finish.getText().toString());
                startActivityForResult(intent1, Constant.REQUEST_CODE_FINISH);
                break;
            case R.id.rl_until:
                Intent intent2 = new Intent(WriteDailyActivity.this, ModifyUntilActivity.class);
                intent2.putExtra(Constant.KEY_DAILY_UNFINISH, unfinish.getText().toString());
                startActivityForResult(intent2, Constant.REQUEST_CODE_UNFINISH);
                break;
            case R.id.rl_coordinate:
                Intent intent3 = new Intent(WriteDailyActivity.this, ModifyCoordinateActivity
                        .class);
                intent3.putExtra(Constant.KEY_DAILY_COORDINATE, coordinate.getText().toString());
                startActivityForResult(intent3, Constant.REQUEST_CODE_COORDINATE);
                break;
            case R.id.rl_remark:
                Intent intent4 = new Intent(WriteDailyActivity.this, ModifyRemarkAcitivity.class);
                intent4.putExtra(Constant.KEY_DAILY_REMARK, remark.getText().toString());
                startActivityForResult(intent4, Constant.REQUEST_CODE_REMARK);
                break;
            case R.id.btn_submit:
                addDailyRequest();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case Constant.REQUEST_CODE_FINISH:
                if(resultCode==Constant.RESULT_CODE_FINISH){
                    finish.setText(data.getStringExtra(Constant.KEY_DAILY_FINISH));
                }
                break;
            case Constant.REQUEST_CODE_UNFINISH:
                if(resultCode==Constant.RESULT_CODE_UNFINISH){
                    unfinish.setText(data.getStringExtra(Constant.KEY_DAILY_UNFINISH));
                }
                break;
            case Constant.REQUEST_CODE_COORDINATE:
                if(resultCode==Constant.RESULT_CODE_COORDINATE){
                    coordinate.setText(data.getStringExtra(Constant.KEY_DAILY_COORDINATE));
                }
                break;
            case Constant.REQUEST_CODE_REMARK:
                if(resultCode==Constant.RESULT_CODE_REMARK){
                    remark.setText(data.getStringExtra(Constant.KEY_DAILY_REMARK));
                }
                break;
        }
    }

    private void addDailyRequest() {
        Map<String, Object> map = new HashMap<>();
        map.put("studentId", id);
        map.put("finishedWork", finish.getText().toString());
        map.put("unfinishedWork", unfinish.getText().toString());
        map.put("otherWork", coordinate.getText().toString());
        map.put("remarks", remark.getText().toString());
        XHttpUtils.post(HttpUrl.addDailyRequest, gson.toJson(map), handler, Constant.CONSTANT112);
    }
}
