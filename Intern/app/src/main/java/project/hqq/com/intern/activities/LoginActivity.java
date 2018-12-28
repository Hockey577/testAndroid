package project.hqq.com.intern.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import project.hqq.com.intern.R;
import project.hqq.com.intern.UserId;
import project.hqq.com.intern.bean.PersonInfoGB;
import project.hqq.com.intern.bean.info.Constant;
import project.hqq.com.intern.http.HttpUrl;
import project.hqq.com.intern.http.XHttpUtils;
import project.hqq.com.intern.views.BaseActivity;

import static io.rong.imkit.utils.SystemUtils.getCurProcessName;

public class LoginActivity extends BaseActivity implements View.OnClickListener{
    private TextView mRegister;
    private TextView mCode;
    private Button bLogin;
    private String strPhone,strPassword;
    private long longPhone;
    private EditText etPhone,etPassword;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String jsonStr = (String) msg.obj;
            switch (msg.what){
                case Constant.CONSTANT102:
                    PersonInfoGB bean=gson.fromJson(jsonStr,PersonInfoGB.class);
                    if(bean.getResultMessage().equals("操作正常")){
                        UserId.id=bean.getResultData().getId();
                        Intent intent=new Intent(LoginActivity.this,InternActivity.class);
                        startActivity(intent);
                    }
                    shortShow(bean.getResultMessage());
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        mRegister = (TextView) findViewById(R.id.tv_register);
        mCode = (TextView) findViewById(R.id.tv_code);
        bLogin = (Button) findViewById(R.id.btn_login);
        etPhone = (EditText) findViewById(R.id.et_phone);
        etPassword = (EditText) findViewById(R.id.et_password);
        mRegister.setOnClickListener(this);
        mCode.setOnClickListener(this);
        bLogin.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_register:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
            case R.id.tv_code:
                startActivity(new Intent(LoginActivity.this,CodeActivity.class));
                break;
            case R.id.btn_login:
                checkInfo();
                break;
        }
    }

    private void checkInfo() {
        strPhone = etPhone.getText().toString();
        strPassword = etPassword.getText().toString();
        if (strPhone.equals(""))
            shortShow("手机号不能为空");
        else if (strPassword.equals(""))
            shortShow("密码不能为空");
        else
            LoginRequest();
    }



    private void LoginRequest() {
        longPhone = Long.valueOf(strPhone);
        connect("ZaYi7UlJgTEnPmsrT0/+IxrWFyOwrH8WkITMXZ2op/W1IQM1Wqjn68aUqcTdsU+wpedODSHqpyRgNSCLR7m24w==");
        Map<String,Object> map = new HashMap<>();
        map.put("phoneNum",strPhone);
        map.put("password",strPassword);
        XHttpUtils.post(HttpUrl.loginRequest,gson.toJson(map),handler,Constant.CONSTANT102);
    }


    private void connect(final String token) {

        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {

            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {
                    Log.d("qiqi","1");

                }
                /**
                 * 连接融云成功
                 * @param userid 当前 token 对应的用户 id
                 */
                @Override
                public void onSuccess(String userid) {
                    Log.d("qiqi","2");
                    Log.e("tag0","融云登录成功");
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Log.d("qiqi","3");
                    Log.e("失败","" + errorCode);
                }
            });
        }
    }
}
