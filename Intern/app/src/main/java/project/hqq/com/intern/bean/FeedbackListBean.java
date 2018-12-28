package project.hqq.com.intern.bean;

import java.util.List;

/**
 * Created by ZWY on 2017/6/1.
 */

public class FeedbackListBean {
    /**
     * resultCode : 1
     * resultMessage : 操作正常
     * resultData : [{"recipientCon":"66公司","id":7,"sponsorId":150701209,"recipientId":201701,
     * "evaluateType":13,"evaluateCon":"5555","evaluateMsg":0,"addTime":"2017-06-01T15:22:14",
     * "status":2},{"recipientCon":"66公司","id":8,"sponsorId":150701209,"recipientId":201701,
     * "evaluateType":13,"evaluateCon":"66","evaluateMsg":1,"addTime":"2017-06-01T15:22:50",
     * "status":2}]
     */

    private int resultCode;
    private String resultMessage;
    private List<ResultDataBean> resultData;

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

    public List<ResultDataBean> getResultData() {
        return resultData;
    }

    public void setResultData(List<ResultDataBean> resultData) {
        this.resultData = resultData;
    }

    public static class ResultDataBean {
        /**
         * recipientCon : 66公司
         * id : 7
         * sponsorId : 150701209
         * recipientId : 201701
         * evaluateType : 13
         * evaluateCon : 5555
         * evaluateMsg : 0
         * addTime : 2017-06-01T15:22:14
         * status : 2
         */

        private String recipientCon;
        private int id;
        private int sponsorId;
        private int recipientId;
        private int evaluateType;
        private String evaluateCon;
        private int evaluateMsg;
        private String addTime;
        private int status;

        public String getRecipientCon() {
            return recipientCon;
        }

        public void setRecipientCon(String recipientCon) {
            this.recipientCon = recipientCon;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSponsorId() {
            return sponsorId;
        }

        public void setSponsorId(int sponsorId) {
            this.sponsorId = sponsorId;
        }

        public int getRecipientId() {
            return recipientId;
        }

        public void setRecipientId(int recipientId) {
            this.recipientId = recipientId;
        }

        public int getEvaluateType() {
            return evaluateType;
        }

        public void setEvaluateType(int evaluateType) {
            this.evaluateType = evaluateType;
        }

        public String getEvaluateCon() {
            return evaluateCon;
        }

        public void setEvaluateCon(String evaluateCon) {
            this.evaluateCon = evaluateCon;
        }

        public int getEvaluateMsg() {
            return evaluateMsg;
        }

        public void setEvaluateMsg(int evaluateMsg) {
            this.evaluateMsg = evaluateMsg;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
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
