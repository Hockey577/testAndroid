package project.hqq.com.intern.adapters;

import android.content.Context;

import java.util.List;


import project.hqq.com.intern.R;
import project.hqq.com.intern.bean.CustomPWDetailsBean;
import project.hqq.com.intern.views.CommonAdapter;
import project.hqq.com.intern.views.ViewHolder;

/**
 * Created by Administrator on 2016/11/27.
 */

public class CustomPWDetailsAdapter extends CommonAdapter<CustomPWDetailsBean> {

    public CustomPWDetailsAdapter(Context context, List<CustomPWDetailsBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(int position, ViewHolder helper, CustomPWDetailsBean item) {
        helper.setText(R.id.tv_size, item.getName());
    }
}
