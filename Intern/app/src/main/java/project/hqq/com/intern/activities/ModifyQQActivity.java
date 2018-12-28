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

public class ModifyQQActivity extends BaseActivity implements View.OnClickListener {
    private ImageView mBack;
    private TextView tv_finish;
    private EditText et_qq;
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
                        intent.putExtra(Constant.KEY_QQ,et_qq.getText().toString());
                        setResult(Constant.RESULT_CODE_QQ,intent);
                        finish();
                    }
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qq);
        init();
    }

    private void init() {
        mBack = (ImageView) findViewById(R.id.im_back);
        tv_finish = (TextView) findViewById(R.id.tv_finish);
        et_qq=(EditText) findViewById(R.id.et_qq);
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
                editQQRequest(id);
                break;
        }

    }
    private void editQQRequest(int id){
        Map<String,Object> map=new HashMap<>();
        map.put("id",id);
        map.put("QQ",et_qq.getText().toString());
        XHttpUtils.post(HttpUrl.editPersonInfoRequest,gson.toJson(map),handler, Constant.CONSTANT105);
    }
}
