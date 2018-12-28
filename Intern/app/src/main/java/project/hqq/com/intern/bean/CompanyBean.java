package project.hqq.com.intern.bean;

/**
 * Created by ZWY on 2017/6/1.
 */

public class CompanyBean {

    /**
     * resultCode : 1
     * resultMessage : 操作正常
     * resultData : {"id":201723,"phoneNum":"15869191710","password":"123456",
     * "companyName":"33公司","companyType":"互联网/IT","size":"0-20人","companyIntroduce":"我们是一个好公司",
     * "companyAddress":"YY城市","background":"zhuYeDiZhi","headImg":null,
     * "checkImg":"/storage/emulated/0/tencent/MicroMsg/WeiXin/mmexport1496238452102.jpg",
     * "addTime":null,"status":0}
     */

    private int resultCode;
    private String resultMessage;
    private ResultDataBean resultData;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public ResultDataBean getResultData() {
        return resultData;
    }

    public void setResultData(ResultDataBean resultData) {
        this.resultData = resultData;
    }

    public static class ResultDataBean {
        /**
         * id : 201723
         * phoneNum : 15869191710
         * password : 123456
         * companyName : 33公司
         * companyType : 互联网/IT
         * size : 0-20人
         * companyIntroduce : 我们是一个好公司
         * companyAddress : YY城市
         * background : zhuYeDiZhi
         * headImg : null
         * checkImg : /storage/emulated/0/tencent/MicroMsg/WeiXin/mmexport1496238452102.jpg
         * addTime : null
         * status : 0
         */

        private int id;
        private String phoneNum;
        private String password;
        private String companyName;
        private String companyType;
        private String size;
        private String companyIntroduce;
        private String companyAddress;
        private String background;
        private Object headImg;
        private String checkImg;
        private Object addTime;
        private int status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPhoneNum() {
            return phoneNum;
        }

        public void setPhoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getCompanyType() {
            return companyType;
        }

        public void setCompanyType(String companyType) {
            this.companyType = companyType;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getCompanyIntroduce() {
            return companyIntroduce;
        }

        public void setCompanyIntroduce(String companyIntroduce) {
            this.companyIntroduce = companyIntroduce;
        }

        public String getCompanyAddress() {
            return companyAddress;
        }

        public void setCompanyAddress(String companyAddress) {
            this.companyAddress = companyAddress;
        }

        public String getBackground() {
            return background;
        }

        public void setBackground(String background) {
            this.background = background;
        }

        public Object getHeadImg() {
            return headImg;
        }

        public void setHeadImg(Object headImg) {
            this.headImg = headImg;
        }

        public String getCheckImg() {
            return checkImg;
        }

        public void setCheckImg(String checkImg) {
            this.checkImg = checkImg;
        }

        public Object getAddTime() {
            return addTime;
        }

        public void setAddTime(Object addTime) {
            this.addTime = addTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
