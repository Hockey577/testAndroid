package project.hqq.com.intern.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import project.hqq.com.intern.R;
import project.hqq.com.intern.bean.ResumeBackBean;

/**
 * Created by asus-pc on 2017/3/11.
 */

public class NoScrollGridAdapter extends BaseAdapter {
    private ArrayList<ResumeBackBean> files;

    private LayoutInflater mLayoutInflater;

    public NoScrollGridAdapter(ArrayList<ResumeBackBean> files, Context context) {
        this.files = files;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return files == null ? 0 : files.size();
    }

    @Override
    public String getItem(int position) {
        return files.get(position).getName();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyGridViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new MyGridViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.item_gridview,
                    parent, false);
            viewHolder.imageView = (ImageView) convertView
                    .findViewById(R.id.image);
            viewHolder.textView = (TextView) convertView
                    .findViewById(R.id.text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MyGridViewHolder) convertView.getTag();
        }
        String url = getItem(position);

        if(files.get(position).getResourceId()!=0){
            viewHolder.imageView.setImageResource(files.get(position).getResourceId());
        }else {
            viewHolder.imageView.setImageBitmap(files.get(position).getResource());

        }
        viewHolder.textView.setText(files.get(position).getName());

        return convertView;
    }

    private static class MyGridViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
