package project.hqq.com.intern.adapters;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import project.hqq.com.intern.R;
import project.hqq.com.intern.bean.CompanyListBean;
import project.hqq.com.intern.views.CommonAdapter;
import project.hqq.com.intern.views.ViewHolder;

/**
 * Created by ZWY on 2017/6/1.
 */

public class CompanyListAdapter extends CommonAdapter<CompanyListBean.ResultDataBean> {
    public CompanyListAdapter(Context context, List<CompanyListBean.ResultDataBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(int position, ViewHolder helper, CompanyListBean.ResultDataBean item) {
        helper.setText(R.id.tv_name1,item.getCompanyName());
        helper.setText(R.id.tv_address,item.getCompanyAddress());
        helper.setText(R.id.tv_size,item.getSize());
        ImageView icon= helper.getView(R.id.im_icon);
        Glide.with(mContext).load(item.getHeadImg()).into(icon);
    }
}
