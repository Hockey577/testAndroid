package project.hqq.com.intern.http;

import org.xutils.http.RequestParams;

/**
 * xutils配置文件
 * Created by Administrator on 2017-3-18.
 */

public class NetRequestParams extends RequestParams {

    public NetRequestParams(String url, String json){
            super(url.replaceAll(" ",""));//删除空格以防万一url有空格
        setAsJsonContent(true);
        setBodyContent(json);
    }
}
