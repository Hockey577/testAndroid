package project.hqq.com.intern.bean;

/**
 * Created by Administrator on 2017-5-6.
 */

public class IdBean {
    public String getName() {
        return name;
    }

    public String getBlank() {
        return blank;
    }

    private String name;
    private String blank;

    public IdBean(String name,String blank){
        this.name = name;
        this.blank = blank;
    }


}
