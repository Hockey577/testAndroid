package project.hqq.com.intern.bean;

/**
 * Created by ZWY on 2017/6/1.
 */

public class LeaveBean {
    /**
     * leaveType :
     * endTime :
     * startTime :
     * leaveReason :
     * studentId : 150701209
     */

    private String leaveType;
    private String endTime;
    private String startTime;
    private String leaveReason;
    private int studentId;

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
