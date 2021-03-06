package project.hqq.com.intern.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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

public class ModifyTimeActivity extends BaseActivity implements View.OnClickListener {
    private ImageView mBack;
    private TextView tv_finish;
    private TextView et_time;
    private int id= UserId.id;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String jsonStr = (String) msg.obj;
            switch (msg.what) {
                case Constant.CONSTANT105:
                    RegisterResultGB bean=gson.fromJson(jsonStr,RegisterResultGB.class);
                    if(bean.getResultMessage().equals("修改成功")){
                        Intent intent=new Intent();
                        intent.putExtra(Constant.KEY_WORKTIME,et_time.getText().toString());
                        setResult(Constant.RESULT_CODE_WORKTIME,intent);
                        finish();
                    }
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        init();

    }

    private void init() {
        mBack = (ImageView) findViewById(R.id.im_back);
        tv_finish = (TextView) findViewById(R.id.tv_finish);
        et_time=(EditText)findViewById(R.id.et_time);
        mBack.setOnClickListener(this);
        tv_finish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.im_back:
                finish();
                break;
            case R.id.tv_finish:
                editTimeRequest(id);
                break;
        }

    }
    private void editTimeRequest(int id){
        Map<String,Object> map=new HashMap<>();
        map.put("id",id);
        map.put("workDate",et_time.getText().toString());
        XHttpUtils.post(HttpUrl.editPersonInfoRequest,gson.toJson(map),handler, Constant.CONSTANT105);
    }
}
