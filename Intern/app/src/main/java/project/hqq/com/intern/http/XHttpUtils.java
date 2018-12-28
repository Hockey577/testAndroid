package project.hqq.com.intern.http;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import project.hqq.com.intern.bean.info.Constant;


/**
 * xUtils工具类
 * Created by Administrator on 2017-3-18.
 */

public class XHttpUtils {
    /**
     * post请求
     * @param url  地址
     * @param json  json字符串（请求参数转成json）
     * @param handler  handler用来返回请求返回结果
     * @param action  请求码
     */
        public static  void post(final String url, String json, final Handler handler, final int action){
            Log.i("jsonlog","访问地址："+url+"  \n请求参数："+json);
            NetRequestParams params = new NetRequestParams(url,json);
            final Message message = new Message();
            Log.e("rrr","params: "+params);
            x.http().post(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    Log.d("jsonlog","成功！訪問地址："+url+"  \n返回結果："+result);
                    message.what = action;
                    message.obj = result;
                    handler.sendMessage(message);
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    Log.e("jsonlog","錯誤！訪問地址："+url+"  \n失敗結果："+ex);
                    message.what = Constant.REQUESTERROR;
                    message.arg1 = action;
                    handler.sendMessage(message);
                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });
        }
    /**
     * post请求
     * @param url  地址
     * @param handler  handler用来返回请求返回结果
     * @param action  请求码
     */
    public static  void post(final String url, RequestParams params, final Handler handler, final int action){
        final Message message = new Message();
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("jsonlog","成功！訪問地址："+url+"  \n返回結果："+result);
                message.what = action;
                message.obj = result;
                handler.sendMessage(message);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("jsonlog","錯誤！訪問地址："+url+"  \n失敗結果："+ex);
                message.what = Constant.REQUESTERROR;
                message.arg1 = action;
                handler.sendMessage(message);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
