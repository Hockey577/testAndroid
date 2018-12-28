package project.hqq.com.intern.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import project.hqq.com.intern.R;
import project.hqq.com.intern.adapters.NoScrollGridAdapter;
import project.hqq.com.intern.bean.ResumeBackBean;
import project.hqq.com.intern.views.BaseActivity;

public class ResumeActivity extends BaseActivity implements View.OnClickListener{
    private ImageView mBack;
    private GridView gridView;
    private NoScrollGridAdapter simpleAdapter;
    private ArrayList<ResumeBackBean> data_list;
    ResumeBackBean have = new ResumeBackBean();
    private int icon = R.drawable.add;
    private String iconName="添加简历";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);
        init();
    }

    private void init() {
        mBack = (ImageView) findViewById(R.id.im_back);
        mBack.setOnClickListener(this);
        gridView = (GridView) findViewById(R.id.gridview);
        //新建list
        data_list = new ArrayList<ResumeBackBean>();
        //获取数据
        have.setResourceId(icon);
        have.setName(iconName);
        data_list.add(have);

        simpleAdapter = new NoScrollGridAdapter(data_list,ResumeActivity.this);
        //配置适配器
        gridView.setAdapter(simpleAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int i, long l) {
                if(data_list.get(i).getName()!="添加简历"){
                    Log.d("cliak2","csdc");
                    Intent intent2 = new Intent(ResumeActivity.this,ResumeDetailActivity.class);

                    intent2.putExtra("name",data_list.get(i).getName());

                    ByteArrayOutputStream baos=new ByteArrayOutputStream();
                    data_list.get(i).getResource().compress(Bitmap.CompressFormat.PNG, 100, baos);
                    byte[] bitmapByte =baos.toByteArray();
                    intent2.putExtra("bitmap", bitmapByte);

                    startActivity(intent2);
                }else {
                    Intent intent = new Intent(ResumeActivity.this,ResumeAddActivity.class);
                    startActivityForResult(intent,0);
                }
            }
        });
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View
                    view, final int i, long l) {
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                builder.setMessage("是否删除？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data_list.remove(i);
                        simpleAdapter = new NoScrollGridAdapter(data_list,ResumeActivity.this);
                        //配置适配器
                        gridView.setAdapter(simpleAdapter);
                        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view,
                                                    int i, long l) {

                                if(data_list.get(i).getName()!="添加简历"){
                                    Log.d("cliak2","csdc");
                                    Intent intent2 = new Intent(ResumeActivity.this,ResumeDetailActivity.class);

                                    intent2.putExtra("name",data_list.get(i).getName());

                                    ByteArrayOutputStream baos=new ByteArrayOutputStream();
                                    data_list.get(i).getResource().compress(Bitmap.CompressFormat.PNG, 100, baos);
                                    byte[] bitmapByte =baos.toByteArray();
                                    intent2.putExtra("bitmap", bitmapByte);

                                    startActivity(intent2);
                                }else {
                                    Intent intent = new Intent(ResumeActivity.this,ResumeAddActivity.class);
                                    startActivityForResult(intent,0);
                                }

                            }
                        });
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
                return true;
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.im_back:
                finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        switch (requestCode) {
            case 0:
                if(resultCode ==0) {
                    String name =data.getStringExtra("name");
                    byte[] bis = data.getByteArrayExtra("bitmap");
                    Bitmap img = BitmapFactory.decodeByteArray(bis,0,bis.length);

                    Log.d("bit",img.getByteCount()+" ");
                    ResumeBackBean newMap = new ResumeBackBean();
                    newMap.setName(name);
                    newMap.setResource(img);


                    if(name!=null){
                        data_list.remove(have);
                        data_list.add(newMap);
                        data_list.add(have);
                        simpleAdapter = new NoScrollGridAdapter(data_list,ResumeActivity.this);
                        //配置适配器
                        gridView.setAdapter(simpleAdapter);
                        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view,
                                                    int i, long l) {

                                if(data_list.get(i).getName()!="添加简历"){
                                    Log.d("cliak2","csdc");
                                    Intent intent2 = new Intent(ResumeActivity.this,ResumeDetailActivity.class);

                                    intent2.putExtra("name",data_list.get(i).getName());

                                    ByteArrayOutputStream baos=new ByteArrayOutputStream();
                                    data_list.get(i).getResource().compress(Bitmap.CompressFormat.PNG, 100, baos);
                                    byte[] bitmapByte =baos.toByteArray();
                                    intent2.putExtra("bitmap", bitmapByte);

                                    startActivity(intent2);
                                }else {
                                    Intent intent = new Intent(ResumeActivity.this,ResumeAddActivity.class);
                                    startActivityForResult(intent,0);
                                }

                            }
                        });
                    }
                    break;
                }else break;
        }
    }


}
