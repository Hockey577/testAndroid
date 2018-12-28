package project.hqq.com.intern.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import project.hqq.com.intern.R;
import project.hqq.com.intern.adapters.IdAdapter;
import project.hqq.com.intern.bean.IdBean;
import project.hqq.com.intern.views.BaseActivity;

public class IdCheckActivity extends BaseActivity implements View.OnClickListener{
    private ImageView mBack;
    private List<IdBean> IdList = new ArrayList<>();
    private ListView mList;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_check);
        init();
        initList();
        IdAdapter idAdapter = new IdAdapter(IdCheckActivity.this,IdList,R.layout.item_id);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IdBean idBean = IdList.get(position);
                switch (position){
                    case 0:
                        startActivity(new Intent(IdCheckActivity.this,ModifySchoolActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(IdCheckActivity.this,ModifyCreditActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(IdCheckActivity.this,ModifyMajorActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(IdCheckActivity.this,ModifyNameActivity.class));
                        break;
                }
            }
        });
        mList.setAdapter(idAdapter);
    }

    private void initList() {
        IdBean school = new IdBean("学校",null);
        IdBean credit = new IdBean("身份证号",null);
        IdBean major = new IdBean("专业",null);
        IdBean name = new IdBean("姓名",null);

        IdList.add(school);
        IdList.add(credit);
        IdList.add(major);
        IdList.add(name);

    }

    private void init() {
        mBack = (ImageView) findViewById(R.id.im_back);
        mList = (ListView) findViewById(R.id.list_id);
        mBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.im_back:
                finish();
                break;
        }
    }
}
