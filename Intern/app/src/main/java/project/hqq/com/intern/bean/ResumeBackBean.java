package project.hqq.com.intern.bean;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by asus-pc on 2017/6/2.
 */

public class ResumeBackBean {
    private String name;
    private int resourceId=0;
    private Bitmap resource;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public Bitmap getResource() {
        return resource;
    }

    public void setResource(Bitmap resource) {
        this.resource = resource;
    }
}
