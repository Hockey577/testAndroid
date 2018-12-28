package project.hqq.com.intern.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import project.hqq.com.intern.R;

public class ModifyCreditActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView mBack;
    private TextView tv_finish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);
        init();
    }

    private void init() {
        mBack = (ImageView) findViewById(R.id.im_back);
        tv_finish = (TextView) findViewById(R.id.tv_finish);
        mBack.setOnClickListener(this);
        tv_finish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.im_back:
                finish();
                break;
            case R.id.tv_finish:
                finish();
                break;
        }
    }
}
