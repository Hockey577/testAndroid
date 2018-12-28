package project.hqq.com.intern.activities;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import project.hqq.com.intern.R;
import project.hqq.com.intern.fragment.CompanyFragment;
import project.hqq.com.intern.fragment.InfoFragment;
import project.hqq.com.intern.fragment.JobFragment;
import project.hqq.com.intern.fragment.MineFragment;
import project.hqq.com.intern.views.BaseActivity;

public class InternActivity extends BaseActivity implements ViewPager.OnPageChangeListener{
    /**
     * 双击back键退出间隔时间
     */
    private long exitTime = 0;
    /**
     * 滑动控件
     */
    private ViewPager mViewPager;
    private ArrayList<Fragment> bFragments = new ArrayList<>();
    private ArrayList<TextView> bTabItems = new ArrayList<>();
    private int tabImage[] = {R.drawable.bg_im_job,R.drawable.bg_im_company,R.drawable.bg_im_info,R.drawable.bg_im_mine};
    private String tabText[] = {"职位","公司","消息","个人"};

    /**
     * 当期显示点击控件index
     */
    private int currentItem = 0;
    /**
     * 上一次点击的底部菜单index
     */
    private int lastItem;

    TextView tvJob;
    TextView tvCompany;
    TextView tvMessage;
    TextView tvMine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intern);
        initView();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出应用", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initView() {
        tvJob = (TextView) findViewById(R.id.tv_job);
        tvCompany = (TextView) findViewById(R.id.tv_company);
        tvMessage = (TextView) findViewById(R.id.tv_information);
        tvMine = (TextView) findViewById(R.id.tv_mine);
        bTabItems.add(tvJob);
        bTabItems.add(tvCompany);
        bTabItems.add(tvMessage);
        bTabItems.add(tvMine);
        this.initViewPager();
        for (int i = 0; i < bTabItems.size(); i++) {
            final TextView tvCurrentItem = bTabItems.get(i);
            //动态设置TextView的DrawableTop
            Drawable drawable = getResources().getDrawable(tabImage[i]);
            int imageLength = this.getResources().getDimensionPixelOffset(R.dimen.spacing_large);
            drawable.setBounds(0, 0, imageLength, imageLength);
            tvCurrentItem.setCompoundDrawables(null, drawable, null, null);
            tvCurrentItem.setText(tabText[i]);
            tvCurrentItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    switch (tvCurrentItem.getId()) {
                        case R.id.tv_job:
                            mViewPager.setCurrentItem(0);
                            break;
                        case R.id.tv_company:
                            mViewPager.setCurrentItem(1);
                            break;
                        case R.id.tv_information:
                            mViewPager.setCurrentItem(2);
                            break;
                        case R.id.tv_mine:
                            mViewPager.setCurrentItem(3);
                            break;
                    }
                }
            });
        }
        bTabItems.get(0).setSelected(true);
        mViewPager.setCurrentItem(0);
    }

    private void initViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        JobFragment jobFragment = new JobFragment();
        CompanyFragment companyFragment = new CompanyFragment();
        InfoFragment infoFragment = new InfoFragment();
        MineFragment mineFragment = new MineFragment();

        bFragments.add(jobFragment);
        bFragments.add(companyFragment);
        bFragments.add(infoFragment);
        bFragments.add(mineFragment);

        mViewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(),bFragments));
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(this);
        //设置缓存页面为5，即全部页面，非可暂时避免ViewPager预加载带来的问题
        mViewPager.setOffscreenPageLimit(4);
        this.switchFragment(0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        lastItem = currentItem;
        currentItem = position;
        switchFragment(currentItem);
        bTabItems.get(currentItem).setSelected(true);
        bTabItems.get(lastItem).setSelected(false);
    }

    private void switchFragment(int index) {
        switch (index){
            case 0:
                tvJob.setTextColor(this.getResources().getColor(R.color.home_red));
                tvCompany.setTextColor(this.getResources().getColor(R.color.gray));
                tvMessage.setTextColor(this.getResources().getColor(R.color.gray));
                tvMine.setTextColor(this.getResources().getColor(R.color.gray));
                break;
            case 1:
                tvJob.setTextColor(this.getResources().getColor(R.color.gray));
                tvCompany.setTextColor(this.getResources().getColor(R.color.home_red));
                tvMessage.setTextColor(this.getResources().getColor(R.color.gray));
                tvMine.setTextColor(this.getResources().getColor(R.color.gray));
                break;
            case 2:
                tvJob.setTextColor(this.getResources().getColor(R.color.gray));
                tvCompany.setTextColor(this.getResources().getColor(R.color.gray));
                tvMessage.setTextColor(this.getResources().getColor(R.color.home_red));
                tvMine.setTextColor(this.getResources().getColor(R.color.gray));
                break;
            case 3:
                tvJob.setTextColor(this.getResources().getColor(R.color.gray));
                tvCompany.setTextColor(this.getResources().getColor(R.color.gray));
                tvMessage.setTextColor(this.getResources().getColor(R.color.gray));
                tvMine.setTextColor(this.getResources().getColor(R.color.home_red));
                break;
            default:
                break;

        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter{
        ArrayList<Fragment> list;

        public MyFragmentPagerAdapter(FragmentManager fm , ArrayList<Fragment> list){
            super(fm);
            this.list = list;

        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }


}
