package project.hqq.com.intern.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import project.hqq.com.intern.R;
import project.hqq.com.intern.adapters.JobSendAdapter;
import project.hqq.com.intern.bean.JobBean;
import project.hqq.com.intern.bean.JobSendBean;
import project.hqq.com.intern.views.BaseActivity;

public class SendActivity extends BaseActivity implements View.OnClickListener{

    private ImageView mBack;
    private ListView list_1;

    //列表
    private List<JobSendBean> jobList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        setJobData();
        init();
    }

    private void setJobData() {
        jobList = new ArrayList<>();
        JobSendBean bean_01 = new JobSendBean("产品经理","12k-20k","某某科技有限公司","杭州·下沙","本科·经验不限","已通过");
        jobList.add(bean_01);
        JobSendBean bean_02 = new JobSendBean("美工","11k-20k","某某科技有限公司","杭州·萧山","本科·经验不限","待审核");
        jobList.add(bean_02);

        JobSendBean bean_03 = new JobSendBean("总经理","20k-30k","某某科技有限公司","杭州·富阳","本科·3年以上工作经验","待审核");
        jobList.add(bean_03);

        JobSendBean bean_04 = new JobSendBean("Java工程师","13k-15k","某某科技有限公司","东阳·横店","本科·经验不限","已通过");
        jobList.add(bean_04);

        JobSendBean bean_05 = new JobSendBean("前台","3k-5k","某某科技有限公司","东阳·六石","大专·经验不限","已通过");
        jobList.add(bean_05);

        JobSendBean bean_06 = new JobSendBean("J2EE工程师","20k-25k","某某科技有限公司","东阳·南马","本科·3年以上工作经验","待审核");
        jobList.add(bean_06);

        JobSendBean bean_07 = new JobSendBean("IOS工程师","20k-25k","某某科技有限公司","东阳·千祥","本科·3年以上工作经验","待审核");
        jobList.add(bean_07);

    }

    private void init() {
        list_1=(ListView)findViewById(R.id.list_1);
        mBack = (ImageView) findViewById(R.id.im_back);
        mBack.setOnClickListener(this);
        JobSendAdapter adapter =new JobSendAdapter(this,jobList,R.layout.item_job_send);
        list_1.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.im_back:
                finish();
        }
    }
}
