package project.hqq.com.intern.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import project.hqq.com.intern.R;
import project.hqq.com.intern.bean.CompanyBean;
import project.hqq.com.intern.bean.IdBean;
import project.hqq.com.intern.bean.info.Constant;
import project.hqq.com.intern.http.HttpUrl;
import project.hqq.com.intern.http.XHttpUtils;
import project.hqq.com.intern.views.BaseActivity;

public class CompanyPageActivity extends BaseActivity implements View.OnClickListener {
    private ImageView mBack;
    private List<IdBean> IdList = new ArrayList<>();
    private ListView mList;
    TextView mText;
    private TextView tvIntro;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TextView tvName, tvType, tvFrom, tvSize;
    private LayoutInflater mInflater;
    private List<String> mTitleList = new ArrayList<>();//页卡标题集合
    private View view1, view2;
    private List<View> mViewList = new ArrayList<>();//页卡视图集合
    private int comId;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String jsonStr = (String) msg.obj;
            switch (msg.what) {
                case Constant.CONSTANT108:
//                    CompanyBean bean = gson.fromJson(jsonStr, CompanyBean.class);
//                    if (bean.getResultMessage().equals("操作正常")) {
//                        tvName.setText(bean.getResultData().getCompanyName());
//                        tvFrom.setText(bean.getResultData().getCompanyAddress());
//                        tvSize.setText(bean.getResultData().getSize());
//                        tvType.setText(bean.getResultData().getCompanyType());
//                        tvIntro.setText(bean.getResultData().getCompanyIntroduce());
//                    }

                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_page);
        comId = getIntent().getIntExtra(Constant.KEY_COMPANY, 0);
        init();

        MyPagerAdapter mAdapter = new MyPagerAdapter(mViewList);
        mViewPager.setAdapter(mAdapter);//给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
        mTabLayout.setTabsFromPagerAdapter(mAdapter);//给Tabs设置适配器
        returnCoInfoRequest();
    }


    private void init() {
        tvName = (TextView) findViewById(R.id.tv_name1);
        tvFrom = (TextView) findViewById(R.id.tv_from);
        tvType = (TextView) findViewById(R.id.tv_type);
        tvSize = (TextView) findViewById(R.id.tv_size);
        mBack = (ImageView) findViewById(R.id.im_back);
        mBack.setOnClickListener(this);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        mInflater = LayoutInflater.from(this);
        view1 = mInflater.inflate(R.layout.company_page, null);
        view2 = mInflater.inflate(R.layout.item_job, null);
        tvIntro = (TextView) view1.findViewById(R.id.tv_intro);
        //添加页卡视图
        mViewList.add(view1);
        mViewList.add(view2);
        //添加页卡标题
        mTitleList.add("公司概况");
        mTitleList.add("职位需求");
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(0)));//添加tab选项卡
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(1)));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.im_back:
                finish();
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

    private void returnCoInfoRequest() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", comId);
        XHttpUtils.post(HttpUrl.returnCoInfoRequest, gson.toJson(map), handler, Constant.CONSTANT108);
    }
}
