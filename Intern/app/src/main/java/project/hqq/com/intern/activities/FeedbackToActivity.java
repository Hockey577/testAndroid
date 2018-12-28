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

/**
 * Created by ZWY on 2017/6/1.
 */

public class FeedbackToActivity extends BaseActivity implements View.OnClickListener {
    private ImageView mBack;
    private TextView tv_finish;
    private EditText et_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedbackto);
        init();
    }

    private void init() {
        mBack = (ImageView) findViewById(R.id.im_back);
        tv_finish = (TextView) findViewById(R.id.tv_finish);
        et_name = (EditText) findViewById(R.id.et_feedbackto);
        mBack.setOnClickListener(this);
        tv_finish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.im_back:
                finish();
                break;
            case R.id.tv_finish:
                Intent intent = new Intent();
                intent.putExtra(Constant.KEY_FEEDBACK_TO, et_name.getText().toString());
                setResult(Constant.RESULT_CODE_FEEDBACK_TO, intent);
                finish();
                break;
        }

    }

}

