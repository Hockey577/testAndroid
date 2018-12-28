package project.hqq.com.intern.views;

import android.annotation.SuppressLint;
import android.app.Application;

import org.xutils.x;

import io.rong.imkit.RongIM;

/**
 * Created by Administrator on 2017-3-18.
 */

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        x.Ext.init(this);
        RongIM.init(this);
    }

    @SuppressLint("SdCardPath")
    public static final String SD_LOCAL = "/sdcard/CRMExhibition";
}
