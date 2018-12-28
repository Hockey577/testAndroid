package project.hqq.com.intern.bean;

import java.util.List;

/**
 * Created by ZWY on 2017/6/1.
 */

public class LeaveListBean {
    /**
     * resultCode : 1
     * resultMessage : 操作正常
     * resultData : [{"id":3,"studentId":150701209,"companyId":201701,"leaveType":"2",
     * "startTime":"2017-05-10T02:17:37","endTime":"2021-05-03T04:10:40","leaveReason":"Reason2",
     * "addTime":null,"status":3},{"id":4,"studentId":150701209,"companyId":201701,
     * "leaveType":"1","startTime":"2017-05-10T02:17:37","endTime":"2017-06-22T14:38:11",
     * "leaveReason":"Reason","addTime":null,"status":1},{"id":5,"studentId":150701209,
     * "companyId":201701,"leaveType":"事假","startTime":"2017-06-01T14:46:00",
     * "endTime":"2017-06-15T14:46:00","leaveReason":"5","addTime":"2017-06-01T14:56:07",
     * "status":0},{"id":6,"studentId":150701209,"companyId":201701,"leaveType":"",
     * "startTime":null,"endTime":null,"leaveReason":"","addTime":"2017-06-01T15:01:06",
     * "status":0},{"id":7,"studentId":150701209,"companyId":201701,"leaveType":"病假",
     * "startTime":null,"endTime":null,"leaveReason":"67777","addTime":"2017-06-01T15:05:00",
     * "status":0}]
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
         * id : 3
         * studentId : 150701209
         * companyId : 201701
         * leaveType : 2
         * startTime : 2017-05-10T02:17:37
         * endTime : 2021-05-03T04:10:40
         * leaveReason : Reason2
         * addTime : null
         * status : 3
         */

        private int id;
        private int studentId;
        private int companyId;
        private String leaveType;
        private String startTime;
        private String endTime;
        private String leaveReason;
        private Object addTime;
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

        public String getLeaveType() {
            return leaveType;
        }

        public void setLeaveType(String leaveType) {
            this.leaveType = leaveType;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getLeaveReason() {
            return leaveReason;
        }

        public void setLeaveReason(String leaveReason) {
            this.leaveReason = leaveReason;
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
