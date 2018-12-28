package project.hqq.com.intern.bean;

/**
 * Created by ZWY on 2017/6/1.
 */

public class DailyBean {

    /**
     * resultCode : 1
     * resultMessage : 操作正常
     * resultData : {"id":4,"studentId":150701209,"companyId":201701,"finishedWork":"已完成的工作内容",
     * "unfinishedWork":"未完成的工作内容","otherWork":"需要协调的工作","remarks":"我需要有人协助","addTime":null,
     * "status":null}
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
         * id : 4
         * studentId : 150701209
         * companyId : 201701
         * finishedWork : 已完成的工作内容
         * unfinishedWork : 未完成的工作内容
         * otherWork : 需要协调的工作
         * remarks : 我需要有人协助
         * addTime : null
         * status : null
         */

        private int id;
        private int studentId;
        private int companyId;
        private String finishedWork;
        private String unfinishedWork;
        private String otherWork;
        private String remarks;
        private String addTime;
        private int status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStudentId() {
            return studentId;
        }

        public void setStudentId(int studentId) {
            this.studentId = studentId;
        }

        public int getCompanyId() {
            return companyId;
        }

        public void setCompanyId(int companyId) {
            this.companyId = companyId;
        }

        public String getFinishedWork() {
            return finishedWork;
        }

        public void setFinishedWork(String finishedWork) {
            this.finishedWork = finishedWork;
        }

        public String getUnfinishedWork() {
            return unfinishedWork;
        }

        public void setUnfinishedWork(String unfinishedWork) {
            this.unfinishedWork = unfinishedWork;
        }

        public String getOtherWork() {
            return otherWork;
        }

        public void setOtherWork(String otherWork) {
            this.otherWork = otherWork;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
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
