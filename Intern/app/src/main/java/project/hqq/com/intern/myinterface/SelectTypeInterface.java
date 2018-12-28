package project.hqq.com.intern.myinterface;

/**
 * 弹出框回调接口
 * Created by hao on 2016/11/27.
 */
public interface SelectTypeInterface {
    /**
     * 弹出菜单操作
     *
     * @param groupPosition 第几个弹出框（用于一个页面多次调用此弹出框做于判断）
     * @param childPosition 点击的项数
     */
    void selector(int groupPosition, int childPosition);
}
