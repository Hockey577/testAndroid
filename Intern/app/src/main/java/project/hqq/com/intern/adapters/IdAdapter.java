package project.hqq.com.intern.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import project.hqq.com.intern.R;
import project.hqq.com.intern.bean.IdBean;
import project.hqq.com.intern.views.CommonAdapter;
import project.hqq.com.intern.views.ViewHolder;

/**
 * Created by Administrator on 2017-5-6.
 */

public class IdAdapter extends CommonAdapter<IdBean>{
    public IdAdapter(Context context, List<IdBean> mDatas,int itemLayoutId){
        super(context,mDatas,itemLayoutId);
    }

    @Override
    public void convert(int position, ViewHolder helper, IdBean item) {
        helper.setText(R.id.tv_name,item.getName());
        helper.setText(R.id.tv_blank,item.getBlank());
    }


}
