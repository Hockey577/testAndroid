package project.hqq.com.intern.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import project.hqq.com.intern.R;
import project.hqq.com.intern.UserId;
import project.hqq.com.intern.activities.DailyActivity;
import project.hqq.com.intern.activities.FeedbackListActivity;
import project.hqq.com.intern.activities.GetFeedBackActivity;
import project.hqq.com.intern.activities.IdCheckActivity;
import project.hqq.com.intern.activities.LeaveListActivity;
import project.hqq.com.intern.activities.PersonalAcitivity;
import project.hqq.com.intern.activities.PunchActivity;
import project.hqq.com.intern.activities.ResumeActivity;
import project.hqq.com.intern.activities.SendActivity;
import project.hqq.com.intern.bean.PersonInfoGB;
import project.hqq.com.intern.bean.info.Constant;
import project.hqq.com.intern.http.HttpUrl;
import project.hqq.com.intern.http.XHttpUtils;
import project.hqq.com.intern.views.BaseActivity;

public class MineFragment extends Fragment implements AdapterView.OnItemClickListener{
    View contentView;
    private int id= UserId.id;
    private Button exit;
    private GridView gridView;
    private TextView tv_name;
    private TextView tv_from;
    private ImageView im_icon;
    private ArrayList<Map<String,Object>> data_list;
    private SimpleAdapter simpleAdapter;
    private int[] icon = {R.drawable.identification,R.drawable.personal_data,R.drawable.resume,R.drawable.send,
    R.drawable.leave,R.drawable.pass,R.drawable.clock_in,R.drawable.feedback1,R.drawable.history};
        private String[] iconName={"身份认证","个人资料","简历管理","已投递","请假报告","日报","考勤打卡",
    "反馈","收到反馈"};
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String jsonStr = (String) msg.obj;
            switch (msg.what) {
                //显示个人信息
                case Constant.CONSTANT103:
                    PersonInfoGB personBean = ((BaseActivity)getActivity()).gson.fromJson(jsonStr,
                            PersonInfoGB
                            .class);
                    Glide.with(getActivity()).load(personBean.getResultData().getHeadImg
                            ()).into(im_icon);
                    tv_from.setText(personBean.getResultData().getSchoolName());
                    tv_name.setText(personBean.getResultData().getStudentName());
                    break;
            }
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_mine,container,false);
        exit= ((Button) contentView.findViewById(R.id.btn_exit));
        tv_from=(TextView)contentView.findViewById(R.id.tv_from);
        tv_name=(TextView)contentView.findViewById(R.id.tv_name);
        im_icon=(ImageView)contentView.findViewById(R.id.im_icon);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        initView();
        return contentView;
    }

    private void initView() {
        gridView = (GridView) contentView.findViewById(R.id.gridview);
        //新建list
        data_list = new ArrayList<Map<String, Object>>();
        //获取数据
        getData();
        //新建适配器
        String[] from = {"image", "text"};
        int[] to = {R.id.image, R.id.text};
        simpleAdapter = new SimpleAdapter(getContext(), data_list, R.layout.item_gridview, from, to);
        //配置适配器
        gridView.setAdapter(simpleAdapter);
        gridView.setOnItemClickListener(this);
        showPersonInfoRequest(id);
    }

    public List<Map<String, Object>> getData() {
        //icon和iconName的长度是相同的，这里任选其一都可以
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }

        return data_list;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                startActivity(new Intent(getContext(), IdCheckActivity.class));
                break;
            case 1:
                startActivity(new Intent(getContext(), PersonalAcitivity.class));
                break;
            case 2:
                startActivity(new Intent(getContext(), ResumeActivity.class));
                break;
            case 3:
                startActivity(new Intent(getContext(), SendActivity.class));
                break;
            case 4:
                startActivity(new Intent(getContext(), LeaveListActivity.class));
                break;
            case 5:
                startActivity(new Intent(getContext(), DailyActivity.class));
                break;
            case 6:
                startActivity(new Intent(getContext(), PunchActivity.class));
                break;
            case 7:
                startActivity(new Intent(getContext(), FeedbackListActivity.class));
                break;
            case 8:
                startActivity(new Intent(getContext(),GetFeedBackActivity.class));
                break;
        }
    }
    private void showPersonInfoRequest(int id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        XHttpUtils.post(HttpUrl.personInfoRequest,((BaseActivity) getActivity()).gson.toJson(map), handler,
                Constant
                .CONSTANT103);
    }

    @Override
    public void onResume() {
        super.onResume();
        showPersonInfoRequest(id);
    }
}
