package project.hqq.com.intern.adapters;

import android.content.Context;

import java.util.List;

import project.hqq.com.intern.R;
import project.hqq.com.intern.bean.DailyListBean;
import project.hqq.com.intern.views.CommonAdapter;
import project.hqq.com.intern.views.ViewHolder;

/**
 * Created by ZWY on 2017/6/1.
 */

public class DailyListAdapter extends CommonAdapter<DailyListBean.ResultDataBean> {
    public DailyListAdapter(Context context, List<DailyListBean.ResultDataBean> mDatas, int
            itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(int position, ViewHolder helper, DailyListBean.ResultDataBean item) {
        helper.setText(R.id.tv_name1, item.getStudentId()+"");
        helper.setText(R.id.tv_hot1,item.getAddTime());
    }
}
