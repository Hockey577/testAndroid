package project.hqq.com.intern.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import project.hqq.com.intern.R;
import project.hqq.com.intern.UserId;
import project.hqq.com.intern.adapters.DailyListAdapter;
import project.hqq.com.intern.bean.DailyListBean;
import project.hqq.com.intern.bean.info.Constant;
import project.hqq.com.intern.http.HttpUrl;
import project.hqq.com.intern.http.XHttpUtils;
import project.hqq.com.intern.views.BaseActivity;

public class DailyActivity extends BaseActivity implements View.OnClickListener{
    private ImageView mBack;
    private TextView tv_write;
    private ListView daily;
    private int id = UserId.id;
    DailyListAdapter dailyListAdapter;
    List<DailyListBean.ResultDataBean> mDailyBeanList=new ArrayList<>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String jsonStr = (String) msg.obj;
            switch (msg.what) {
                case Constant.CONSTANT113:
                    DailyListBean dailyListBean=gson.fromJson(jsonStr,DailyListBean.class);
                    mDailyBeanList.clear();
                    for (DailyListBean.ResultDataBean bean:dailyListBean.getResultData()) {
                        mDailyBeanList.add(bean);
                    }
                    daily.setAdapter(dailyListAdapter);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
        init();
    }

    private void init() {

        mBack = (ImageView) findViewById(R.id.im_back);
        tv_write = (TextView) findViewById(R.id.tv_finish);
        daily=(ListView)findViewById(R.id.list_daily);
        tv_write .setOnClickListener(this);
        mBack.setOnClickListener(this);
        dailyListAdapter=new DailyListAdapter(this,mDailyBeanList,R.layout.item_daily);
        daily.setAdapter(dailyListAdapter);
        daily.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(DailyActivity.this,DailyDetailActivity.class);
                intent.putExtra(Constant.KEY_DAILY_INFO,mDailyBeanList.get(position).getId());
                startActivity(intent);
            }
        });
        returnDailyListRequest();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.im_back:
                finish();
                break;
            case R.id.tv_finish:
                startActivity(new Intent(DailyActivity.this,WriteDailyActivity.class));
                break;
        }
    }
    private void returnDailyListRequest(){
        Map<String, Object> map = new HashMap<>();
        map.put("studentId", id);
        XHttpUtils.post(HttpUrl.returnDailyListRequest, gson.toJson(map), handler, Constant.CONSTANT113);
    }

    @Override
    protected void onResume() {
        super.onResume();
        returnDailyListRequest();
    }
}
