package project.hqq.com.intern.adapters;

import android.content.Context;

import java.util.List;

import project.hqq.com.intern.R;
import project.hqq.com.intern.bean.ResumeBean;
import project.hqq.com.intern.views.CommonAdapter;
import project.hqq.com.intern.views.ViewHolder;

/**
 * Created by Administrator on 2017-5-6.
 */

public class ResumeAdapter extends CommonAdapter<ResumeBean> {
    public ResumeAdapter(Context context, List<ResumeBean> mDatas,int itemLayoutId){
        super(context,mDatas,itemLayoutId);

    }
    @Override
    public void convert(int position, ViewHolder helper, ResumeBean item) {
        helper.setText(R.id.text,item.getText());
        helper.setImageResource(R.id.image,item.getImageId());
    }
}
