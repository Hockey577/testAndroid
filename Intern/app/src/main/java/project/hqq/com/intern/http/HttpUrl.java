package project.hqq.com.intern.http;

/**
 * 接口类
 * Created by Administrator on 2017-3-18.
 */

public class HttpUrl {
    //
    public static final String BASEURL0 = "http://119.23.245.196:8885/employseason/";
    public static final String BASEURL = "http://119.23.245.196:8081/";
    //4.1短信验证码请求
    public static final String smsRequest = BASEURL0 + "util/sms";
    //1.1注册
    public static final String registerRequest = BASEURL +
            "St_Register.aspx";
    //1.2登陆
    public static final String loginRequest = BASEURL +
            "St_CheckLogin.aspx";
    //个人信息
    public static final String personInfoRequest = BASEURL +
            "St_ReturnStInfo.aspx";
    //编辑头像
    public static final String editHeadImgRequest = BASEURL +
            "St_EditHeadImg.aspx";
    //编辑个人信息
    public static final String editPersonInfoRequest = BASEURL +
            "St_EditStInfo.aspx";
    //忘记密码
    public static final String forgetPasswordRequest = BASEURL +
            "St_ResetPassword.aspx";
    //用户认证
    public static final String authenticationRequest = BASEURL +
            "St_Authentication.aspx";
    //添加简历
    public static final String addResumeRequest = BASEURL +
            "StRe_AddStRe.aspx";
    //删除简历
    public static final String deleteResumeRequest = BASEURL +
            "StRe_DeleteStReList.aspx";
    //返回某个简历
    public static final String returnResumeRequest = BASEURL +
            "StRe_ReturnStRe.aspx";
    //返回简历列表
    public static final String returnResumeListRequest = BASEURL +
            "StRe_ReturnStReList.aspx";
    //添加请假
    public static final String addLeaveRequest = BASEURL +
            "LeEv_AddLeEv.aspx";
    //返回某个请假
    public static final String returnLeaveRequest = BASEURL +
            "LeEv_ReturnLeEv.aspx";
    //返回个人请假列表
    public static final String returnLeaveListRequest = BASEURL +
            "LeEv_ReturnLeEvList.aspx";
    //添加反馈
    public static final String addFeedbackRequest = BASEURL +
            "Evaluate_AddEvaluate.aspx";
    //返回学生收到反馈的列表
    public static final String returnFeedbackToStListRequest = BASEURL +
            "Evaluate_RecieveEvaluateList.aspx";
    //返回学生反馈列表
    public static final String returnFeedbackListRequest = BASEURL +
            "Evaluate_ReturnEvaluateList.aspx";
    //返回某个反馈
    public static final String returnFeedbackRequest = BASEURL +
            "Evaluate_ReturnEvaluate.aspx";
    //添加投递
    public static final String addSendRequest = BASEURL +
            "DeEv_AddDeEv.aspx";
    //返回投递列表
    public static final String returnSendListRequest = BASEURL +
            "DeEv_ReturnDeEvList.aspx";
    //添加日报
    public static final String addDailyRequest = BASEURL +
            "DaSu_AddDaSu.aspx";
    //返回日报
    public static final String returnDailyRequest = BASEURL +
            "DaSu_ReturnDaSu.aspx";
    //返回日报列表
    public static final String returnDailyListRequest = BASEURL +
            "DaSu_ReturnDaSuList.aspx";
    //返回职位
    public static final String returnPositionRequest = BASEURL +
            "CoPo_ReturnPosition.aspx";

    //返回职位列表（公司）
    public static final String returnCoPositionListRequest = BASEURL +
            "CoPo_ReturnPositionList_Co.aspx";
    //返回职位列表（学生）
    public static final String returnStPositionListRequest = BASEURL +
            "CoPo_ReturnPositionList_Co.aspx";
    //返回公司信息
    public static final String returnCoInfoRequest = BASEURL +
            "Company_ReturnCompany.aspx";
    //返回公司列表
    public static final String returnCoListRequest = BASEURL +
            "Company_ReturnCompanyList_St.aspx";
    //考勤
    public static final String addAtRequest = BASEURL +
            "AtEv_AddAtEv.aspx";
    //返回今日考勤信息
    public static final String returnAtListRequest = BASEURL +
            "AtEv_ReturnAtEv_Today.aspx";

    public static final String myInter = "http://localhost:8090/qiqi/CheckServlet";
}
