package project.hqq.com.intern.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by asus-pc on 2017/6/1.
 */

public class JobBean implements Serializable{
    private String job;
    private String money;
    private String company;
    private String location;
    private String demand;

    public JobBean(String job, String money, String company, String location,
                   String demand) {
        this.job = job;
        this.money = money;
        this.company = company;
        this.location = location;
        this.demand = demand;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }
}
