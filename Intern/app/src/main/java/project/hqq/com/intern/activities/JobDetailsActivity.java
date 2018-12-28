package project.hqq.com.intern.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import project.hqq.com.intern.R;
import project.hqq.com.intern.bean.JobBean;

public class JobDetailsActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tvJobDetail,tvWorkDetail,tv_name1,tv_company,tv_from1,tv_from,tv_modify;
    private ImageView mBack;
    private Button btnRelease,btnCompany;
    JobBean jobBean;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_details);

        intent = getIntent();
        Bundle bundle = intent.getExtras();
        jobBean =(JobBean) bundle.getSerializable("data");
        Log.d("data01",jobBean.getJob());
        initView();
    }

    private void initView() {
        btnCompany = (Button) findViewById(R.id.btn_company);
        mBack = (ImageView) findViewById(R.id.im_back);
        btnRelease = (Button) findViewById(R.id.btn_release);

        btnCompany.setOnClickListener(this);
        btnRelease.setOnClickListener(this);
        mBack.setOnClickListener(this);


        tv_name1 = (TextView)findViewById(R.id.tv_name1);
        tv_name1.setText(jobBean.getJob());
        tv_company =(TextView)findViewById(R.id.tv_company);
        tv_company.setText(jobBean.getCompany());
        tv_from1 = (TextView)findViewById(R.id.tv_from1);
        tv_from1.setText(jobBean.getLocation());
        tv_from = (TextView)findViewById(R.id.tv_from);
        tv_from.setText(jobBean.getDemand());
        tv_modify = (TextView)findViewById(R.id.tv_modify);
        tv_modify.setText(jobBean.getMoney());

        tvJobDetail = (TextView) findViewById(R.id.tv_job_details);
        tvWorkDetail = (TextView) findViewById(R.id.tv_work_details);
        tvJobDetail.setText("1.工作点：杭州·下沙某某科技有限公司\n" +
                "2.完成市场规划需求，根据客户需求制作原型、设计原型" +"");
        tvWorkDetail.setText("1.工作点：杭州·下沙某某科技有限公司\n" +
                "2.完成市场规划需求，根据客户需求制作原型、设计原型" +"");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.im_back:
                finish();
                break;
            case R.id.btn_company:
                startActivity(new Intent(JobDetailsActivity.this,CompanyPageActivity.class));
                break;
            case R.id.btn_release:
                startActivity(new Intent(JobDetailsActivity.this,ResumeReleaseActivity.class));
                break;
        }

    }
}
