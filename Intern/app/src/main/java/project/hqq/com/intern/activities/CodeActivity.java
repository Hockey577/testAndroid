package project.hqq.com.intern.activities;

import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

import project.hqq.com.intern.R;
import project.hqq.com.intern.bean.RegisterResultGB;
import project.hqq.com.intern.bean.info.Constant;
import project.hqq.com.intern.http.HttpUrl;
import project.hqq.com.intern.http.XHttpUtils;
import project.hqq.com.intern.views.BaseActivity;

public class CodeActivity extends BaseActivity implements View.OnClickListener {
    private EditText etPhone,etPassword,etPasswordAgain,etCode;
    private String strPhone,strPassword,strPasswordAgain,strCode;
    private ImageView mBack;
    private Button getCode,btnRegister;
    private long longPhone;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String jsonStr = (String) msg.obj;
            switch (msg.what){
                case Constant.CONSTANT106:
                    RegisterResultGB rrGB = gson.fromJson(jsonStr,RegisterResultGB.class);
                    shortShow(rrGB.getResultMessage());
                    if (rrGB.getResultMessage().equals("修改成功"))
                        finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        init();
    }

    private void init() {
        mBack = (ImageView) findViewById(R.id.im_back);
        etPhone = (EditText) findViewById(R.id.et_phone);
        etPassword = (EditText) findViewById(R.id.et_password);
        etPasswordAgain = (EditText) findViewById(R.id.et_password_again);
        etCode = (EditText) findViewById(R.id.et_code);
        btnRegister = (Button) findViewById(R.id.btn_register);
        getCode = (Button) findViewById(R.id.btn_getCode);

        mBack.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        getCode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.im_back:
                finish();
                break;
            case R.id.btn_getCode:
                checkPhone();
                break;
            case R.id.btn_register:
                checkInfo();
                break;
        }
    }

    private void checkInfo() {
        strPhone = etPhone.getText().toString();
        strPassword = etPassword.getText().toString();
        strPasswordAgain = etPasswordAgain.getText().toString();
        strCode = etCode.getText().toString();
        if (strPhone.equals(""))
            shortShow("手机号不能为空");
        else if (strPassword.equals(""))
            shortShow("密码不能为空");
        else if (strPasswordAgain.equals(""))
            shortShow("请确认密码");
        else if (!strPasswordAgain.equals(strPassword))
            shortShow("两次密码不一致");
        else if (strCode.equals(""))
            shortShow("验证码不能为空");
        else
            forgetPasswordRequest();
    }

    private void forgetPasswordRequest() {
        longPhone = Long.valueOf(strPhone);
        Map<String,Object> map = new HashMap<>();
        map.put("phoneNum",longPhone);
        map.put("password",strPassword);
        //map.put("code",strCode);
        XHttpUtils.post(HttpUrl.forgetPasswordRequest,gson.toJson(map),handler,Constant.CONSTANT106);
    }

    private void checkPhone() {
        strPhone = etPhone.getText().toString();
        if (strPhone.equals("")){
            shortShow("手机号不能为空");
        }
        else {
            longPhone = Long.valueOf(strPhone);
            SmsRequest();
        }

    }

    private void SmsRequest(){
        Map<String,Object> map = new HashMap<>();
        map.put("phone",longPhone);
        XHttpUtils.post(HttpUrl.smsRequest,gson.toJson(map),handler, Constant.CONSTANT101);
    }
}
