package project.hqq.com.intern.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import project.hqq.com.intern.R;
import project.hqq.com.intern.UserId;
import project.hqq.com.intern.activities.CompanyPageActivity;
import project.hqq.com.intern.activities.InternActivity;
import project.hqq.com.intern.adapters.CompanyListAdapter;
import project.hqq.com.intern.bean.CompanyListBean;
import project.hqq.com.intern.bean.CustomPWDetailsBean;
import project.hqq.com.intern.bean.info.Constant;
import project.hqq.com.intern.http.HttpUrl;
import project.hqq.com.intern.http.XHttpUtils;
import project.hqq.com.intern.myinterface.SelectTypeInterface;
import project.hqq.com.intern.utils.PopupWindowGather;
import project.hqq.com.intern.views.BaseActivity;

/**
 * Created by Administrator on 2017-5-5.
 */

public class CompanyFragment extends Fragment {
    View contentView;
    private PopupWindowGather mPopupWindowGather;
    private LinearLayout ll_size, ll_industry;
    private TextView tv_size, tv_industry;
    private ListView mListView;
    private int id = UserId.id;
    //规模、行业
    private String StrSize, StrIndustry;

    //规模弹出框内容：
    private List<CustomPWDetailsBean> sizeList;
    private String[] sizeArr = new String[]{"0-20人", "20-99人", "100-199人", "50-999人", "1000人以上"};
    private String[] sizeValue = new String[]{"0-20人", "20-99人", "100-199人", "50-999人", "1000人以上"};

    //行业弹出框内容：
    private List<CustomPWDetailsBean> industryList;
    private String[] industryArr = new String[]{"互联网/IT", "餐饮/酒店", "教育/高校",
            "房地产/工程", "物流仓储", "电子商务", "移动通讯/信息"};
    private String[] industryValue = new String[]{"互联网/IT", "餐饮/酒店", "教育/高校",
            "房地产/工程", "物流仓储", "电子商务", "移动通讯/信息"};
    private List<CompanyListBean.ResultDataBean> mCompanyBeanList = new ArrayList<>();
CompanyListAdapter mCompanyListAdapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String jsonStr = (String) msg.obj;
            switch (msg.what) {
                case Constant.CONSTANT107:
                    CompanyListBean companyListBean =((BaseActivity)getActivity()).gson.fromJson(jsonStr,
                            CompanyListBean.class);
                    mCompanyBeanList.clear();
                    for (CompanyListBean.ResultDataBean bean: companyListBean.getResultData()) {
                        mCompanyBeanList.add(bean);
                    }
                    mListView.setAdapter(mCompanyListAdapter);
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_company, container, false);
//        init();
        setListener();
        install();
        return contentView;
    }

    private void install() {
        setSizePWData();
        setIndustryPWData();
    }

    private void setListener() {
        ll_size = (LinearLayout) contentView.findViewById(R.id.ll_size);
        tv_size = (TextView) contentView.findViewById(R.id.tv_size);
        ll_industry = (LinearLayout) contentView.findViewById(R.id.ll_industry);
        tv_industry = (TextView) contentView.findViewById(R.id.tv_industry);
        ll_size.setOnClickListener(click);
        ll_industry.setOnClickListener(click);
        mPopupWindowGather = new PopupWindowGather();
    }

    private void init() {


        mListView = (ListView) contentView.findViewById(R.id.list_1);

        mCompanyListAdapter=new CompanyListAdapter(getActivity(), mCompanyBeanList, R.layout
                .item_company);
        mListView.setAdapter(mCompanyListAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), CompanyPageActivity.class);
                intent.putExtra(Constant.KEY_COMPANY,mCompanyBeanList.get(position).getPositionCount());
                startActivity(intent);
            }
        });
        mPopupWindowGather = new PopupWindowGather();
        returnCompanyListRequest(tv_size.getText().toString(), tv_industry.getText().toString());
    }

    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ll_size:
                    int height = getActivity().getResources().getDisplayMetrics().heightPixels / 3;
                    mPopupWindowGather.customPW(getActivity(), ll_size, sizeList,
                            Constant.CONSTANT101, selectTypeInterface, height);
                    break;
                case R.id.ll_industry:
                    int height1 = getActivity().getResources().getDisplayMetrics().heightPixels / 3;
                    mPopupWindowGather.customPW(getActivity(), ll_industry, industryList,
                            Constant.CONSTANT102, selectTypeInterface, height1);
                    break;
            }
        }
    };

    private void setIndustryPWData() {
        industryList = new ArrayList<>();
        CustomPWDetailsBean bean;
        for (int i = 0; i < industryArr.length; i++) {
            bean = new CustomPWDetailsBean();
            bean.setName(industryArr[i]);
            bean.setValue(industryArr[i]);
            industryList.add(bean);
        }
    }

    private void setSizePWData() {
        sizeList = new ArrayList<>();
        CustomPWDetailsBean bean;
        for (int i = 0; i < sizeArr.length; i++) {
            bean = new CustomPWDetailsBean();
            bean.setName(sizeArr[i]);
            bean.setValue(sizeArr[i]);
            sizeList.add(bean);
        }
    }

    private SelectTypeInterface selectTypeInterface = new SelectTypeInterface() {
        @Override
        public void selector(int groupPosition, int childPosition) {
            switch (groupPosition) {
                case Constant.CONSTANT101:
                    tv_size.setText(sizeList.get(childPosition).getName());
                    StrSize = sizeList.get(childPosition).getValue();
                    break;
                case Constant.CONSTANT102:
                    tv_industry.setText(industryList.get(childPosition).getName());
                    StrIndustry = industryList.get(childPosition).getValue();
                    break;
            }
        }
    };

    private void returnCompanyListRequest(String size, String type) {
        Map<String, Object> map = new HashMap<>();
        map.put("studentId", id);
        map.put("companySize", "0-20人");
        map.put("companyType", "互联网/IT");
        XHttpUtils.post(HttpUrl.returnCoListRequest, ((InternActivity) getActivity()).gson.toJson
                (map), handler, Constant.CONSTANT107);

    }
}

