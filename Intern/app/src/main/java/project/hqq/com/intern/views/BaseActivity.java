package project.hqq.com.intern.views;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.gson.Gson;

//import com.google.gson.Gson;

/**
 * Created by Administrator on 2017-2-26.
 */

public class BaseActivity extends FragmentActivity {
    public Gson gson = new Gson();

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    protected void shortShow( String info) {
        Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
    }

    public void longShow( int info) {
        Toast.makeText(this, info, Toast.LENGTH_LONG).show();
    }

    public void setBackgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }
}
