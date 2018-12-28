package project.hqq.com.intern.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

public class RegisterActivity extends BaseActivity implements View.OnClickListener{
    private ImageView mBack;
    private long longPhone;
    private Button getCode,btnRegister;
    private EditText etPhone,etPassword,etPasswordAgain,etCode;
    private String strPhone,strPassword,strPasswordAgain,strCode;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String jsonStr = (String) msg.obj;
            switch (msg.what){
                case Constant.CONSTANT101:
                    RegisterResultGB rrGB = gson.fromJson(jsonStr,RegisterResultGB.class);
                    shortShow(rrGB.getResultMessage());
                    if (rrGB.getResultMessage().equals("注册成功"))
                        finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init() {
        mBack = (ImageView) findViewById(R.id.im_back);
        getCode = (Button) findViewById(R.id.btn_getCode);
        etPhone = (EditText) findViewById(R.id.ed_phone);
        etPassword = (EditText) findViewById(R.id.et_password);
        etPasswordAgain = (EditText) findViewById(R.id.et_password_again);
        etCode = (EditText) findViewById(R.id.et_code);
        btnRegister = (Button) findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(this);
        mBack.setOnClickListener(this);
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
            registerRequest();
    }

    private void registerRequest() {
        longPhone = Long.valueOf(strPhone);
        Map<String,Object> map = new HashMap<>();
        map.put("phoneNum",strPhone);
        map.put("password",strPassword);
       // map.put("code",strCode);
        XHttpUtils.post(HttpUrl.registerRequest,gson.toJson(map),handler,Constant.CONSTANT101);
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
