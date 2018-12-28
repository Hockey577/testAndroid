package project.hqq.com.intern.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import project.hqq.com.intern.R;
import project.hqq.com.intern.UserId;
import project.hqq.com.intern.adapters.LeaveListAdapter;
import project.hqq.com.intern.bean.LeaveListBean;
import project.hqq.com.intern.bean.info.Constant;
import project.hqq.com.intern.http.HttpUrl;
import project.hqq.com.intern.http.XHttpUtils;
import project.hqq.com.intern.views.BaseActivity;

public class LeaveListActivity extends BaseActivity implements View.OnClickListener{
private int id= UserId.id;
    private ImageView mBack;
    private TextView tv_finish;
    private ListView mListView;
    LeaveListAdapter mLeaveListAdapter;
    List<LeaveListBean.ResultDataBean> mLeaveBeanList=new ArrayList<>();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String jsonStr = (String) msg.obj;
            switch(msg.what){
                case Constant.CONSTANT117:
                    LeaveListBean leaveListBean=gson.fromJson(jsonStr,LeaveListBean.class);
                    if(leaveListBean.getResultMessage().equals("操作正常")){
                        mLeaveBeanList.clear();
                        for (LeaveListBean.ResultDataBean bean:leaveListBean.getResultData()) {
                            mLeaveBeanList.add(bean);
                        }
                        mListView.setAdapter(mLeaveListAdapter);
                    }else {
                        shortShow(leaveListBean.getResultMessage());
                    }
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_list);
        init();
        leaveListRequest();
    }

    private void init() {
        mBack = (ImageView) findViewById(R.id.im_back);
        mListView=(ListView)findViewById(R.id.list_leave);
        mLeaveListAdapter=new LeaveListAdapter(this,mLeaveBeanList,R.layout.item_leave);
        mListView.setAdapter(mLeaveListAdapter);
        mBack.setOnClickListener(this);
        tv_finish = (TextView) findViewById(R.id.tv_finish);
        tv_finish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.im_back:
                finish();
                break;
            case R.id.tv_finish:
                startActivity(new Intent(LeaveListActivity.this,LeaveActivity.class));
                break;
        }
    }
    private void leaveListRequest(){
        Map<String,Object> map=new HashMap<>();
        map.put("studentId",id);
        XHttpUtils.post(HttpUrl.returnLeaveListRequest,gson.toJson(map),handler, Constant.CONSTANT117);
    }
}
