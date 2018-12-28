package project.hqq.com.intern.bean;

/**
 * Created by Administrator on 2017-5-6.
 */

public class ResumeBean {
    private String text;
    private int imageId;

    public ResumeBean(String name,int imageId){
        this.text = name;
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public String getText() {
        return text;
    }
}
