package project.hqq.com.intern.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import project.hqq.com.intern.R;
import project.hqq.com.intern.bean.info.Constant;
import project.hqq.com.intern.views.BaseActivity;

public class ModifyCoordinateActivity extends BaseActivity implements View.OnClickListener{
    private ImageView mBack;
    private TextView finish;
    private EditText content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_coordinate);
        init();
    }

    private void init() {
        mBack = (ImageView) findViewById(R.id.im_back);
        finish=(TextView)findViewById(R.id.tv_finish);
        content=(EditText)findViewById(R.id.et_content);
        content.setText(getIntent().getStringExtra(Constant.KEY_DAILY_COORDINATE));
        mBack.setOnClickListener(this);
        finish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.im_back:
                finish();
                break;
            case R.id.tv_finish:
                Intent intent=new Intent();
                intent.putExtra(Constant.KEY_DAILY_COORDINATE,content.getText().toString());
                setResult(Constant.RESULT_CODE_COORDINATE,intent);
                finish();
                break;
        }
    }
}
