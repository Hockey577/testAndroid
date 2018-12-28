package project.hqq.com.intern.adapters;

import android.content.Context;

import java.util.List;

import project.hqq.com.intern.R;
import project.hqq.com.intern.bean.JobBean;
import project.hqq.com.intern.bean.JobSendBean;
import project.hqq.com.intern.views.CommonAdapter;
import project.hqq.com.intern.views.ViewHolder;

/**
 * Created by asus-pc on 2017/6/1.
 */

public class JobSendAdapter extends CommonAdapter<JobSendBean> {

    public JobSendAdapter(Context context, List<JobSendBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(int position, ViewHolder helper, JobSendBean item) {
        helper.setText(R.id.tv_name1,item.getJob());
        helper.setText(R.id.tv_name2,item.getCompany());
        helper.setText(R.id.tv_from1,item.getLocation());
        helper.setText(R.id.tv_from,item.getDemand());
        helper.setText(R.id.tv_modify,item.getMoney());
        helper.setText(R.id.tv_state,item.getState());
    }
}
