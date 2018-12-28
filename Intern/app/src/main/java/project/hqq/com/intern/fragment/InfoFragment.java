package project.hqq.com.intern.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.design.widget.TabLayout;

import io.rong.imkit.RongIM;
import project.hqq.com.intern.R;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-5-5.
 */

public class InfoFragment extends Fragment implements View.OnClickListener{
    View contentView;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private RelativeLayout rlChat,rlChat2,rlChat3;
    private LayoutInflater mInflater;
    private List<String> mTitleList = new ArrayList<>();//页卡标题集合
    private View view1,view2;
    private List<View> mViewList = new ArrayList<>();//页卡视图集合

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_information,container,false);
        initView();
        MyPagerAdapter mAdapter = new MyPagerAdapter(mViewList);
        mViewPager.setAdapter(mAdapter);//给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
        mTabLayout.setTabsFromPagerAdapter(mAdapter);//给Tabs设置适配器
        return contentView;
    }

    private void initView() {
        mTabLayout = (TabLayout) contentView.findViewById(R.id.tabs);
        mViewPager = (ViewPager) contentView.findViewById(R.id.viewPager);

        mInflater = LayoutInflater.from(getContext());
        view1 = mInflater.inflate(R.layout.item_company2,null);
        view2 = mInflater.inflate(R.layout.item_company,null);

        rlChat = (RelativeLayout) view2.findViewById(R.id.rl_chat2);
        rlChat2 = (RelativeLayout) view1.findViewById(R.id.rl_school);
        rlChat3 = (RelativeLayout) view1.findViewById(R.id.rl_company);
        rlChat.setOnClickListener(this);
        rlChat2.setOnClickListener(this);
        rlChat3.setOnClickListener(this);
        //添加页卡视图
        mViewList.add(view1);
        mViewList.add(view2);
        //添加页卡标题
        mTitleList.add("聊天");
        mTitleList.add("标记");
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(0)));//添加tab选项卡
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(1)));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_school:
                RongIM.getInstance().startPrivateChat(getContext(),"3","hello");
                break;
            case R.id.rl_company:
                RongIM.getInstance().startPrivateChat(getContext(),"2","hello");
                break;
            case R.id.rl_chat2:
                RongIM.getInstance().startPrivateChat(getContext(),"2","hello");
                break;
        }

    }

    //ViewPager适配器
    class MyPagerAdapter extends PagerAdapter {
        private List<View> mViewList;

        public MyPagerAdapter(List<View> mViewList) {
            this.mViewList = mViewList;
        }

        @Override
        public int getCount() {
            return mViewList.size();//页卡数
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViewList.get(position));//添加页卡
            return mViewList.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);//页卡标题
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViewList.get(position));//删除页卡
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;//官方推荐写法
        }
    }
}
