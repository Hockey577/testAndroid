package project.hqq.com.intern.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import project.hqq.com.intern.R;
import project.hqq.com.intern.views.BaseActivity;

public class ResumeReleaseActivity extends BaseActivity implements View.OnClickListener{
    private ImageView mBack;
    TextView tv_submit;
    private GridView gridView;
    private SimpleAdapter simpleAdapter;
    private ArrayList<Map<String,Object>> data_list;
    private int[] icon = { R.drawable.pm,R.drawable.pm,R.drawable.pm,R.drawable.pm
            ,R.drawable.pm,R.drawable.pm};
    private String[] iconName={"产品经理","美工","Java工程师","J2EE工程师","Web工程师"
            ,"q前台"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_release);
        init();
    }

    private void init() {
        mBack = (ImageView) findViewById(R.id.im_back);
        tv_submit =(TextView)findViewById(R.id.tv_submit);
        //新建list
        data_list = new ArrayList<Map<String, Object>>();
        //获取数据
        getData();
        String[] from = {"image", "text"};
        int[] to = {R.id.image, R.id.text};
        gridView = (GridView)findViewById(R.id.gridview);
        simpleAdapter = new SimpleAdapter(this, data_list, R.layout.item_gridview, from, to);
        //配置适配器
        gridView.setAdapter(simpleAdapter);
        mBack.setOnClickListener(this);
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ResumeReleaseActivity.this,"提交成功",Toast.LENGTH_SHORT).show();
            }
        });

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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.im_back:
                finish();
                break;

        }
    }


}
