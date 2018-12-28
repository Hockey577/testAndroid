package project.hqq.com.intern.adapters;

import android.content.Context;

import java.util.List;

import project.hqq.com.intern.R;
import project.hqq.com.intern.bean.LeaveListBean;
import project.hqq.com.intern.views.CommonAdapter;
import project.hqq.com.intern.views.ViewHolder;

/**
 * Created by ZWY on 2017/6/1.
 */

public class LeaveListAdapter extends CommonAdapter<LeaveListBean.ResultDataBean> {
    public LeaveListAdapter(Context context, List<LeaveListBean.ResultDataBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(int position, ViewHolder helper, LeaveListBean.ResultDataBean item) {
        helper.setText(R.id.tv_type,item.getLeaveType());
        helper.setText(R.id.tv_fromtime,item.getStartTime());
        helper.setText(R.id.tv_totime,item.getEndTime());
        if(item.getStatus()==0){
            helper.setText(R.id.tv_status,"正在审核");
        }else if(item.getStatus()==1){
            helper.setText(R.id.tv_status,"未通过");
        }else if(item.getStatus()==2){
            helper.setText(R.id.tv_status,"已通过");
        }
    }
}
