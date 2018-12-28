package project.hqq.com.intern.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import project.hqq.com.intern.R;
import project.hqq.com.intern.activities.JobDetailsActivity;
import project.hqq.com.intern.adapters.JobAdapter;
import project.hqq.com.intern.bean.CustomPWDetailsBean;
import project.hqq.com.intern.bean.JobBean;
import project.hqq.com.intern.bean.info.Constant;
import project.hqq.com.intern.myinterface.SelectTypeInterface;
import project.hqq.com.intern.utils.PopupWindowGather;

/**
 * Created by Administrator on 2017-5-5.
 */

public class JobFragment extends Fragment {
    View contentView;
    private PopupWindowGather mPopupWindowGather;
    private LinearLayout ll_recommend,ll_area,ll_salary;
    private TextView tv_recommend,tv_area,tv_salary;
    private ListView list_1;

    //推荐、地区、薪资
    private String StrRecommend,StrArea,StrSalary;

    //推荐弹出框内容：
    private List<CustomPWDetailsBean> RecommendList;
    private String[] RecommendArr = new String[]{"系统推荐","学校推荐"};
    private String[] RecommendValue = new String[]{"系统推荐","学校推荐"};

    //地区弹出框内容:
    private List<CustomPWDetailsBean> AreaList;
    private String[] AreaArr = new String[]{"上海","北京","浙江","深圳","广东"};
    private String[] AreaValue = new String[]{"上海","北京","浙江","深圳","广东"};

    //薪资弹出框内容
    private List<CustomPWDetailsBean> SalaryList;
    private String[] SalartArr = new String[]{"1k-3k","3k-5k","5k-8k","8k-1w","1w-2w","2w以上"};
    private String[] SalartValue = new String[]{"1k-3k","3k-5k","5k-8k","8k-1w","1w-2w","2w以上"};

    //列表
    private List<JobBean> jobList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_job,container,false);


        install();
        init();
        setListener();
        return contentView;
    }

    private void install() {
        setRecommendPWData();
        setAreaPWData();
        setSalaryPWData();
        setJobData();
    }



    private void setListener() {
        ll_recommend.setOnClickListener(click);
        ll_area.setOnClickListener(click);
        ll_salary.setOnClickListener(click);
    }

    private void init() {
        ll_recommend = (LinearLayout) contentView.findViewById(R.id.ll_recommend);
        ll_area = (LinearLayout) contentView.findViewById(R.id.ll_area);
        ll_salary = (LinearLayout) contentView.findViewById(R.id.ll_salary);
        tv_recommend = (TextView) contentView.findViewById(R.id.tv_recommend);
        tv_area = (TextView) contentView.findViewById(R.id.tv_area);
        tv_salary = (TextView) contentView.findViewById(R.id.tv_salary);
        list_1 = (ListView)contentView.findViewById(R.id.list_1);
        JobAdapter adapter = new JobAdapter(getContext(),jobList,R.layout.item_job);
        list_1.setAdapter(adapter);
        list_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int i, long l) {
                Intent intent = new Intent(getContext(), JobDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data",jobList.get(i));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        mPopupWindowGather = new PopupWindowGather();
    }

    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ll_recommend:
                    int height =  getActivity().getResources().getDisplayMetrics().heightPixels / 3;
                    mPopupWindowGather.customPW(getActivity(),ll_recommend,RecommendList,
                            Constant.CONSTANT103,selectTypeInterface,height);
                    break;
                case R.id.ll_area:
                    int height1 =  getActivity().getResources().getDisplayMetrics().heightPixels / 3;
                    mPopupWindowGather.customPW(getActivity(),ll_area,AreaList,
                            Constant.CONSTANT104,selectTypeInterface,height1);
                    break;
                case R.id.ll_salary:
                    int height2 =  getActivity().getResources().getDisplayMetrics().heightPixels / 3;
                    mPopupWindowGather.customPW(getActivity(),ll_salary,SalaryList,
                            Constant.CONSTANT105,selectTypeInterface,height2);
                    break;
            }
        }
    };

    private SelectTypeInterface selectTypeInterface = new SelectTypeInterface() {
        @Override
        public void selector(int groupPosition, int childPosition) {
            switch (groupPosition) {
                case Constant.CONSTANT103:
                    tv_recommend.setText(RecommendList.get(childPosition).getName());
                    StrRecommend = RecommendList.get(childPosition).getValue();
                    break;
                case Constant.CONSTANT104:
                    tv_area.setText(AreaList.get(childPosition).getName());
                    StrArea = AreaList.get(childPosition).getValue();
                    break;
                case Constant.CONSTANT105:
                    tv_salary.setText(SalaryList.get(childPosition).getName());
                    StrSalary = SalaryList.get(childPosition).getValue();
                    break;
            }
        }
    };

    private void setRecommendPWData() {
        RecommendList = new ArrayList<>();
        CustomPWDetailsBean bean;
        for (int i = 0; i < RecommendArr.length; i++) {
            bean = new CustomPWDetailsBean();
            bean.setName(RecommendArr[i]);
            bean.setValue(RecommendArr[i]);
            RecommendList.add(bean);
        }
    }
    private void setJobData() {
        jobList = new ArrayList<>();
        JobBean bean_01 = new JobBean("产品经理","12k-20k","某某科技有限公司","杭州·下沙","本科·经验不限");
        jobList.add(bean_01);
        JobBean bean_02 = new JobBean("美工","11k-20k","某某科技有限公司","杭州·萧山","本科·经验不限");
        jobList.add(bean_02);

        JobBean bean_03 = new JobBean("总经理","20k-30k","某某科技有限公司","杭州·富阳","本科·3年以上工作经验");
        jobList.add(bean_03);

        JobBean bean_04 = new JobBean("Java工程师","13k-15k","某某科技有限公司","东阳·横店","本科·经验不限");
        jobList.add(bean_04);

        JobBean bean_05 = new JobBean("前台","3k-5k","某某科技有限公司","东阳·六石","大专·经验不限");
        jobList.add(bean_05);

        JobBean bean_06 = new JobBean("J2EE工程师","20k-25k","某某科技有限公司","东阳·南马","本科·3年以上工作经验");
        jobList.add(bean_06);

        JobBean bean_07 = new JobBean("IOS工程师","20k-25k","某某科技有限公司","东阳·千祥","本科·3年以上工作经验");
        jobList.add(bean_07);


    }

    private void setAreaPWData(){
        AreaList = new ArrayList<>();
        CustomPWDetailsBean bean;
        for (int i = 0;i<AreaArr.length;i++){
            bean = new CustomPWDetailsBean();
            bean.setName(AreaArr[i]);
            bean.setValue(AreaValue[i]);
            AreaList.add(bean);
        }
    }

    private void setSalaryPWData(){
        SalaryList = new ArrayList<>();
        CustomPWDetailsBean bean;
        for (int i = 0;i<SalartArr.length;i++){
            bean = new CustomPWDetailsBean();
            bean.setName(SalartArr[i]);
            bean.setValue(SalartArr[i]);
            SalaryList.add(bean);
        }
    }
}
