package project.hqq.com.intern.bean.info;

/**
 * 常量
 * Created by Administrator on 2017-3-18.
 */

public interface Constant {

    int CONSTANT101 = 0X101;//注册
    int CONSTANT102 = 0X102;//登录
    int CONSTANT103 = 0X103;//个人信息
    int CONSTANT104 = 0X104;//编辑头像
    int CONSTANT105 = 0X105;//编辑个人信息
    int CONSTANT106 = 0X106;//忘记密码
    int CONSTANT107 = 0X107;//返回公司列表
    int CONSTANT108 = 0X108;//公司信息
    int CONSTANT109 = 0X109;//添加反馈
    int CONSTANT110 = 0X110;//返回用户反馈列表
    int CONSTANT111 = 0X111;//返回某条反馈
    int CONSTANT112 = 0X112;//添加日报
    int CONSTANT113 = 0X113;//返回日报列表
    int CONSTANT114 = 0X114;//返回某条日报
    int CONSTANT115 = 0X115;//返回学生收到反馈列表
    int CONSTANT116 = 0X116;//添加请假;
    int CONSTANT117=0X117;//请假列表
    int CONSTANT118=0X118;//今日打卡情况
    int CONSTANT119=0X119;//添加打卡
    int CONSTANT120=0X120;//测试
    int REQUESTERROR = 0X500;


    int CONSTANT_GALLERY_REQUEST = 0X150;


    String KEY_WECHAT = "key_wechat";
    int REQUEST_CODE_WECHAT = 0X300;
    int RESULT_CODE_WECHAT = 0X400;

    String KEY_QQ = "key_qq";
    int REQUEST_CODE_QQ = 0X301;
    int RESULT_CODE_QQ = 0X401;

    String KEY_WORKTIME = "key_worktime";
    int REQUEST_CODE_WORKTIME = 0X302;
    int RESULT_CODE_WORKTIME = 0X402;

    String KEY_NAME = "key_name";
    int REQUEST_CODE_NAME = 0X303;
    int RESULT_CODE_NAME = 0X403;

    int REQUEST_CODE_INTRO = 0X304;
    int RESULT_CODE_INTRO = 0X404;
    String KEY_INTRO = "key_intro";

    String KEY_FEEDBACK_TO = "key_feedback_to";
    int REQUEST_CODE_FEEDBACK_TO = 0X305;
    int RESULT_CODE_FEEDBACK_TO = 0X405;


    int REQUEST_CODE_FINISH = 0X306;
    int REQUEST_CODE_UNFINISH = 0X307;
    int REQUEST_CODE_COORDINATE = 0X308;
    int REQUEST_CODE_REMARK = 0X309;
    int RESULT_CODE_FINISH = 0X406;
    int RESULT_CODE_UNFINISH = 0X407;
    int RESULT_CODE_COORDINATE = 0X408;
    int RESULT_CODE_REMARK = 0X409;
    String KEY_DAILY_FINISH = "key_daily_finish";
    String KEY_DAILY_UNFINISH = "key_daily_unfinish";
    String KEY_DAILY_COORDINATE = "key_daily_coordinate";
    String KEY_DAILY_REMARK = "key_daily_remark";

    String KEY_COMPANY = "key_company_id";


    String KEY_DAILY_INFO= "key_daily_info";
    String KEY_FEEDBACK_INFO="key_feedback_info";

}
