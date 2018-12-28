package project.hqq.com.intern.activities;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

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

public class GetFeedBackActivity extends BaseActivity {
    private int id = UserId.id;
    private ImageView imback;
    private ListView mListView;
    private List<FeedbackListBean.ResultDataBean> mFeedbackBeanList = new ArrayList<>();
    FeedbackListAdater mFeedbackListAdater;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String jsonStr = (String) msg.obj;
            switch (msg.what) {
                //反馈给学生列表
                case Constant.CONSTANT115:
                    FeedbackListBean feedbackListBean = gson.fromJson(jsonStr, FeedbackListBean.class);
                    mFeedbackBeanList.clear();
                    for (FeedbackListBean.ResultDataBean bean:feedbackListBean.getResultData()) {
                        mFeedbackBeanList.add(bean);
                    }
                    mListView.setAdapter(mFeedbackListAdater);
                    break;
                case Constant.CONSTANT111:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_feedback);
        this.imback = (ImageView) findViewById(R.id.im_back);
        mListView = (ListView) findViewById(R.id.list_receive_feedback);
        mFeedbackListAdater=new FeedbackListAdater(this, mFeedbackBeanList, R.layout
                .item_feedback);
        mListView.setAdapter(mFeedbackListAdater);
        imback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(GetFeedBackActivity.this,FeedbackDetailActivity.class);
                intent.putExtra(Constant.KEY_FEEDBACK_INFO,mFeedbackBeanList.get(position).getId());
                startActivity(intent);

            }
        });
        returnFeedbackToStListRequest();
    }

    private void returnFeedbackToStListRequest() {
        Map<String, Object> map = new HashMap<>();
        map.put("recipientId", id);
        XHttpUtils.post(HttpUrl.returnFeedbackToStListRequest, gson.toJson(map), handler, Constant
                .CONSTANT115);
    }
    private void returnFeedbackRequest(int id){
        Map<String ,Object> map=new HashMap<>();
        map.put("id",id);
        XHttpUtils.post(HttpUrl.returnFeedbackRequest,gson.toJson(map),handler, Constant.CONSTANT111);
    }
}
