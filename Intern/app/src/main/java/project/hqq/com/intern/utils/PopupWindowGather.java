package project.hqq.com.intern.utils;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.List;

import project.hqq.com.intern.R;
import project.hqq.com.intern.adapters.CustomPWDetailsAdapter;
import project.hqq.com.intern.bean.CustomPWDetailsBean;
import project.hqq.com.intern.myinterface.SelectTypeInterface;


/**
 * Created by hao on 2016/11/27.
 */

public class PopupWindowGather {
    private int mPosition;

    /**
     * 类别选择弹出框
     *
     * @param activity
     * @param view        点击控件弹出
     * @param list        显示内容数据集合
     * @param type        第几个弹出框（用于一个页面多次调用此弹出框做于判断）
     * @param myinterface 回调
     */
    public void customPW(Activity activity, View view, List<CustomPWDetailsBean> list,
                         final int type, final SelectTypeInterface myinterface,int height) {
        //这个就是渲染一个布局 里面是一个listview
        View popView = View.inflate(activity, R.layout.popop_size, null);
        ListView lv_context = (ListView) popView.findViewById(R.id.list_size);
        TextView tv_certain=(TextView)popView.findViewById(R.id.tv_certain);
        TextView tv_cancel=(TextView)popView.findViewById(R.id.tv_cancel);
        //listview的item，是在adapter里面看
        CustomPWDetailsAdapter adapter = new CustomPWDetailsAdapter(activity, list,
                R.layout.item_size);
        lv_context.setAdapter(adapter);
        int width = activity.getResources().getDisplayMetrics().widthPixels;
        int height2 = height;
        final PopupWindow mPopWindow = new PopupWindow(popView, width, height);
        ColorDrawable dw = new ColorDrawable(0xFFFFFFFF);
        mPopWindow.setBackgroundDrawable(dw);
        mPopWindow.setFocusable(true);
        mPopWindow.setTouchable(true);
        mPopWindow.setOutsideTouchable(true);// 允许在外侧点击取消
        mPopWindow.showAsDropDown(view, 0, 5);//显示的位置
        lv_context.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPosition=position;
                myinterface.selector(type, position);
                mPopWindow.dismiss();
            }
        });
        tv_certain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myinterface.selector(type,mPosition);
                mPopWindow.dismiss();
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopWindow.dismiss();
            }
        });
    }
}
