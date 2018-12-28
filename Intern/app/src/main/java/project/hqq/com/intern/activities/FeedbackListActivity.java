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
import project.hqq.com.intern.adapters.FeedbackListAdater;
import project.hqq.com.intern.bean.FeedbackListBean;
import project.hqq.com.intern.bean.info.Constant;
import project.hqq.com.intern.http.HttpUrl;
import project.hqq.com.intern.http.XHttpUtils;
import project.hqq.com.intern.views.BaseActivity;

public class FeedbackListActivity extends BaseActivity implements View.OnClickListener {
    private int id = UserId.id;
    private TextView tv_finish;
    private ImageView mBack;
    private ListView mListView;
    FeedbackListAdater mFeedbackListAdater;
    List<FeedbackListBean.ResultDataBean> mFeedbackBeanList = new ArrayList<>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String jsonStr = (String) msg.obj;
            switch (msg.what) {
                //学生反馈列表
                case Constant.CONSTANT110:
                    FeedbackListBean feedbackListBean = gson.fromJson(jsonStr, FeedbackListBean.class);
                    mFeedbackBeanList.clear();
                    for (FeedbackListBean.ResultDataBean bean:feedbackListBean.getResultData()) {
                        mFeedbackBeanList.add(bean);
                    }
                    mListView.setAdapter(mFeedbackListAdater);
                    break;

                case Constant.CONSTANT114:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_list);

        init();
        returnStFeedbackRequest();
    }

    private void init() {
        mBack = (ImageView) findViewById(R.id.im_back);
        tv_finish = (TextView) findViewById(R.id.tv_finish);
        mListView = (ListView) findViewById(R.id.list_feedback);
        tv_finish.setOnClickListener(this);
        mBack.setOnClickListener(this);
        mFeedbackListAdater = new FeedbackListAdater(FeedbackListActivity.this, mFeedbackBeanList,
                R.layout.item_feedback);
        mListView.setAdapter(mFeedbackListAdater);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(FeedbackListActivity.this,FeedbackDetailActivity.class);
                intent.putExtra(Constant.KEY_FEEDBACK_INFO,mFeedbackBeanList.get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.im_back:
                finish();
                break;
            case R.id.tv_finish:
                startActivity(new Intent(FeedbackListActivity.this, FeedbackActivity.class));
                break;
        }
    }

    private void returnStFeedbackRequest() {
        Map<String, Object> map = new HashMap<>();
        map.put("sponsorId", id);
        XHttpUtils.post(HttpUrl.returnFeedbackListRequest, gson.toJson(map), handler, Constant
                .CONSTANT110);
    }

    @Override
    protected void onResume() {
        super.onResume();
        returnStFeedbackRequest();
    }
}
