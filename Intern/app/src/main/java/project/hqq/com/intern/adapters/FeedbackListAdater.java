package project.hqq.com.intern.adapters;

import android.content.Context;

import java.util.List;

import project.hqq.com.intern.R;
import project.hqq.com.intern.bean.FeedbackListBean;
import project.hqq.com.intern.views.CommonAdapter;
import project.hqq.com.intern.views.ViewHolder;

/**
 * Created by ZWY on 2017/6/1.
 */

public class FeedbackListAdater extends CommonAdapter<FeedbackListBean.ResultDataBean> {
    public FeedbackListAdater(Context context, List<FeedbackListBean.ResultDataBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(int position, ViewHolder helper, FeedbackListBean.ResultDataBean item) {
        helper.setText(R.id.tv_name1,item.getRecipientCon());
        helper.setText(R.id.tv_time,item.getAddTime());
    }
}
