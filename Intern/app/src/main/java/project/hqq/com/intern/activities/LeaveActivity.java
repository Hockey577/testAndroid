package project.hqq.com.intern.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import project.hqq.com.intern.R;
import project.hqq.com.intern.UserId;
import project.hqq.com.intern.bean.RegisterResultGB;
import project.hqq.com.intern.bean.info.Constant;
import project.hqq.com.intern.http.HttpUrl;
import project.hqq.com.intern.http.XHttpUtils;
import project.hqq.com.intern.utils.DialogUtil;
import project.hqq.com.intern.views.BaseActivity;
import project.hqq.com.intern.views.BasePopupWindow;
import project.hqq.com.intern.views.PickerView;


public class LeaveActivity extends BaseActivity implements View.OnClickListener {
    private TextView startDateTime;
    private TextView endDateTime;
    private RelativeLayout startTime;
    private RelativeLayout endTime;
    private RelativeLayout rl_type;
    private BasePopupWindow mPopupWindow0;
    private PickerView wheelView;
    private List<String> mDatas;
    private String selected = "事假";
    private TextView tvCancel;
    private TextView tvFinish;
    private TextView tvType;
    private EditText etIntro;
    private Button btSubmit;
    private ImageView imBack;
    private int id= UserId.id;
    View pop0;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String jsonStr = (String) msg.obj;
            switch(msg.what){
                case Constant.CONSTANT116:
                    RegisterResultGB bean=gson.fromJson(jsonStr,RegisterResultGB.class);
                    if(bean.getResultMessage().equals("操作成功")){
                        shortShow("请假成功");
                        finish();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);
        initView();
    }

    private void initView() {
        // 两个输入框
        imBack=(ImageView)findViewById(R.id.im_back);

        startDateTime = (TextView) findViewById(R.id.tv_blank1);
        endDateTime = (TextView) findViewById(R.id.tv_blank2);
        startTime = (RelativeLayout) findViewById(R.id.rl_time_start);
        endTime = (RelativeLayout) findViewById(R.id.rl_time_end);
        rl_type = (RelativeLayout) findViewById(R.id.rl_type);
        tvType = (TextView) findViewById(R.id.tv_blank);
        etIntro = (EditText) findViewById(R.id.et_intro);
        btSubmit=(Button)findViewById(R.id.btn_submit) ;
        initPopupWindow();
        imBack.setOnClickListener(this);
        rl_type.setOnClickListener(this);
        startTime.setOnClickListener(this);
        endTime.setOnClickListener(this);
        btSubmit.setOnClickListener(this);
    }

    private void initPopupWindow() {
        pop0 = getLayoutInflater().inflate(R.layout.wheelview, null);
        wheelView = (PickerView) pop0.findViewById(R.id.pv_type);
        tvCancel = (TextView) pop0.findViewById(R.id.tv_cancel);
        tvFinish = (TextView) pop0.findViewById(R.id.tv_finish);
        tvFinish.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
        mDatas = new ArrayList<>();
        mDatas.add("事假");
        mDatas.add("上课");
        mDatas.add("病假");
        wheelView.setData(mDatas);
        wheelView.setSelected(0);
        wheelView.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                Log.d("qiqi", "text:" + text);
                selected = text;
            }
        });
        mPopupWindow0 = new BasePopupWindow(this);
        mPopupWindow0.setAnimationStyle(R.style.anim_menu_bottombar);
        mPopupWindow0.setContentView(pop0);
        mPopupWindow0.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        mPopupWindow0.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        mPopupWindow0.setTouchable(true);
        mPopupWindow0.setOutsideTouchable(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.im_back:
                finish();
                break;
            case R.id.rl_time_start:
                DialogUtil.showDateTimePickerDialog(LeaveActivity.this, startDateTime);
                break;
            case R.id.rl_time_end:
                DialogUtil.showDateTimePickerDialog(LeaveActivity.this, endDateTime);
                break;
            case R.id.rl_type:
                mPopupWindow0.showAsDropDown(rl_type);
                break;
            case R.id.tv_cancel:
                mPopupWindow0.dismiss();
                break;
            case R.id.tv_finish:
                tvType.setText(selected);
                mPopupWindow0.dismiss();
                break;
            case R.id.btn_submit:
                addLeaveRequest();
                break;
        }
    }
    private void addLeaveRequest(){
        Map<String,Object> map=new HashMap<>();
        map.put("studentId",id);
        map.put("leaveType",tvType.getText().toString());
        map.put("startTime",startDateTime.getText().toString());
        map.put("endTime",endDateTime.getText().toString());
        map.put("leaveReason",etIntro.getText().toString());
        XHttpUtils.post(HttpUrl.addLeaveRequest,gson.toJson(map),handler, Constant.CONSTANT116);
    }
}
