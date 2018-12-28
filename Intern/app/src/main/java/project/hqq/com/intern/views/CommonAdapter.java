package project.hqq.com.intern.views;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;



import java.util.List;

/**
 * Created by Administrator on 2016/8/24.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    protected final int mItemLayoutId;
//    protected Gson gson = new Gson();
    protected Intent intent = new Intent();

    public CommonAdapter(Context context, List<T> mDatas, int itemLayoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = mDatas;
        this.mItemLayoutId = itemLayoutId;
    }


    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder = getViewHolder(position, convertView,
                parent);
        convert(position, viewHolder, getItem(position));
        return viewHolder.getConvertView();

    }

    public abstract void convert(int position, ViewHolder helper, T item);

    private ViewHolder getViewHolder(int position, View convertView,
                                     ViewGroup parent) {
        return ViewHolder.get(mContext, convertView, parent, mItemLayoutId,
                position);
    }

    // 刷新
    public void refresh(List<T> list) {
        this.mDatas = list;
        notifyDataSetChanged();
    }

    public void add(T t) {
        if (mDatas != null) {
            mDatas.add(t);
        }
        notifyDataSetChanged();
    }

    // 添加数据然后刷新
    public void addAll(List<T> list) {
        if (list == null) {
            this.mDatas = list;
        } else {
            this.mDatas.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void delete(int position) {
        if (mDatas != null && mDatas.size() > position) {
            mDatas.remove(position);
            notifyDataSetChanged();
        }
    }

    public void deleteAll() {
        if (mDatas != null) {
            mDatas.clear();
            notifyDataSetChanged();
        }
    }

}
