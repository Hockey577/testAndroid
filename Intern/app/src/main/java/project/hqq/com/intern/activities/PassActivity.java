package project.hqq.com.intern.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import project.hqq.com.intern.R;
import project.hqq.com.intern.views.BaseActivity;

public class PassActivity extends BaseActivity implements View.OnClickListener{
    private ImageView mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass);
        init();
    }

    private void init() {
        mBack = (ImageView) findViewById(R.id.im_back);
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
