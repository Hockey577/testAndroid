package project.hqq.com.intern.activities;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import project.hqq.com.intern.R;
import project.hqq.com.intern.UserId;
import project.hqq.com.intern.bean.PersonInfoGB;
import project.hqq.com.intern.bean.RegisterResultGB;
import project.hqq.com.intern.bean.info.Constant;
import project.hqq.com.intern.http.HttpUrl;
import project.hqq.com.intern.http.XHttpUtils;
import project.hqq.com.intern.views.BaseActivity;
import project.hqq.com.intern.views.BasePopupWindow;

public class PersonalAcitivity extends BaseActivity implements View.OnClickListener {

    private ImageView mBack;
    private TextView mIntro;
    private RelativeLayout rl_name;
    private RelativeLayout rl_time;
    private RelativeLayout rl_wechat;
    private RelativeLayout rl_qq;
    private RelativeLayout rl_title1;//个人简介
    private RelativeLayout rl_modify_icon;//头像
    private BasePopupWindow mPopupWindow0;
    private TextView tv_wechat;
    private TextView tv_qq;
    private TextView tv_name;
    private TextView tv_time;
    private TextView tv_from;
    private TextView tv_name1;
    private Button btn_camera;
    private Button btn_album;
    private CheckBox cb_boy;
    private CheckBox cb_girl;
    View pop0;
    private int id = UserId.id;

    //修改头像相关：
    public static final int TAKE_PHOTO = 1;
    public static final int CHOOSE_PHOTO = 2;
    private Uri imageUri;
    private ImageView im_icon;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String jsonStr = (String) msg.obj;
            switch (msg.what) {
                //显示个人信息
                case Constant.CONSTANT103:
                    PersonInfoGB personBean = gson.fromJson(jsonStr, PersonInfoGB.class);
                    if(personBean.getResultMessage().equals("操作正常")){
                        try{
                            Glide.with(PersonalAcitivity.this).load(personBean.getResultData().getHeadImg
                                    ()).into(im_icon);
                            tv_from.setText(personBean.getResultData().getSchoolName());
                            tv_name.setText(personBean.getResultData().getStudentName());
                            tv_name1.setText(personBean.getResultData().getStudentName());
                            tv_time.setText(personBean.getResultData().getWorkDate());
                            tv_wechat.setText(personBean.getResultData().getWeChat());
                            tv_qq.setText(personBean.getResultData().getQQ());
                            mIntro.setText(personBean.getResultData().getProfile());
                            if (personBean.getResultData().getSex().equals("男")) {
                                cb_boy.setChecked(true);
                                cb_girl.setChecked(false);
                            } else if (personBean.getResultData().getSex().equals("女")) {
                                cb_boy.setChecked(false);
                                cb_girl.setChecked(true);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    break;
                //编辑头像
                case Constant.CONSTANT104:
                    RegisterResultGB bean = gson.fromJson(jsonStr, RegisterResultGB.class);
                    Glide.with(PersonalAcitivity.this).load(imageUri).into(im_icon);
                    shortShow(bean.getResultMessage());
                    break;
                case Constant.CONSTANT105:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        init();
    }

    private void init() {

        mBack = (ImageView) findViewById(R.id.im_back);
        mIntro = (TextView) findViewById(R.id.tv_intro);
        rl_name = (RelativeLayout) findViewById(R.id.rl_modify_name);
        rl_modify_icon = (RelativeLayout) findViewById(R.id.rl_modify_icon);
        rl_time = (RelativeLayout) findViewById(R.id.rl_time);
        rl_wechat = (RelativeLayout) findViewById(R.id.rl_wechat);
        rl_qq = (RelativeLayout) findViewById(R.id.rl_qq);
        rl_title1 = (RelativeLayout) findViewById(R.id.rl_title1);
        im_icon = (ImageView) findViewById(R.id.im_icon);
        tv_wechat = (TextView) findViewById(R.id.tv_blank2);
        tv_qq = (TextView) findViewById(R.id.tv_blank3);
        tv_name1 = (TextView) findViewById(R.id.tv_blank);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_time = (TextView) findViewById(R.id.tv_blank1);
        tv_from = (TextView) findViewById(R.id.tv_from);
        cb_boy = (CheckBox) findViewById(R.id.cb_boy);
        cb_girl = (CheckBox) findViewById(R.id.cb_girl);
        initPopupWindow();

        rl_name.setOnClickListener(this);
        rl_modify_icon.setOnClickListener(this);
        rl_time.setOnClickListener(this);
        rl_wechat.setOnClickListener(this);
        rl_qq.setOnClickListener(this);
        rl_title1.setOnClickListener(this);
        mBack.setOnClickListener(this);
        mIntro.setText("你看看大伙儿合照\n" +
                "就你一个人没有笑\n" +
                "是我们装傻 还是你真的\n" +
                "有很多普通人没有的困扰\n" +
                "我才懒得给你解药\n" +
                "反正你爱来这一套\n" +
                "为爱情折腰 难道不是你\n" +
                "一直以来戒不掉的癖好\n" +
                "你在想谁想到睡不着\n" +
                "你应该觉得骄傲\n" +
                "很多人想失恋也没有目标\n" +
                "只是想睡个好觉 别炫耀\n" +
                "别说你还好 没什么不好\n" +
                "你就怨日子枯燥 喔噢\n" +
                "没什么烦恼 恐怕就想到\n" +
                "什么生存意义想到没完没了\n" +
                "你给我听好 想哭就要笑");
        showPersonInfoRequest(id);
        cb_boy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cb_boy.isChecked() || cb_girl.isChecked()) {
                    editSexRequest();
                }
            }
        });
        cb_girl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cb_boy.isChecked() || cb_girl.isChecked()) {
                    editSexRequest();
                }
            }
        });
        cb_boy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cb_girl.setChecked(false);
            }
        });
        cb_girl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb_boy.setChecked(false);
            }
        });
    }

    private void initPopupWindow() {
        pop0 = getLayoutInflater().inflate(R.layout.popupwindow_0, null);
        mPopupWindow0 = new BasePopupWindow(this);
        mPopupWindow0.setAnimationStyle(R.style.anim_menu_bottombar);
        mPopupWindow0.setContentView(pop0);
        mPopupWindow0.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        mPopupWindow0.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        mPopupWindow0.setTouchable(true);
        mPopupWindow0.setOutsideTouchable(true);

        btn_camera = (Button) pop0.findViewById(R.id.btn_camera);
        btn_camera.setOnClickListener(this);
        btn_album = (Button) pop0.findViewById(R.id.btn_album);
        btn_album.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_modify_icon:
                mPopupWindow0.showAsDropDown(rl_modify_icon);
                break;
            case R.id.im_back:
                finish();
                break;
            case R.id.rl_modify_name:
                startActivityForResult(new Intent(PersonalAcitivity.this, ModifyNameActivity
                        .class), Constant.REQUEST_CODE_NAME);
                break;
            case R.id.rl_time:
                startActivityForResult(new Intent(PersonalAcitivity.this, ModifyTimeActivity
                        .class), Constant.REQUEST_CODE_WORKTIME);
                break;
            case R.id.rl_wechat:
                startActivityForResult(new Intent(PersonalAcitivity.this, ModifyWechatActivity
                        .class), Constant.REQUEST_CODE_WECHAT);
                break;
            case R.id.rl_qq:
                startActivityForResult(new Intent(PersonalAcitivity.this, ModifyQQActivity
                        .class), Constant.REQUEST_CODE_QQ);
                break;
            case R.id.rl_title1:
                startActivityForResult(new Intent(PersonalAcitivity.this, ModifyIntroActivity
                        .class), Constant.REQUEST_CODE_INTRO);
                break;
            case R.id.btn_camera:
                File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    imageUri = FileProvider.getUriForFile(PersonalAcitivity.this,
                            "project.hqq.com.camerademo.fileprovider", outputImage);
                } else {
                    imageUri = Uri.fromFile(outputImage);
                }
                //启动相机程序
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);
                break;
            case R.id.btn_album:
                if (ContextCompat.checkSelfPermission(PersonalAcitivity.this,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager
                        .PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(PersonalAcitivity.this, new String[]{
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    openAlbum();
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[]
            grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager
                        .PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);//打开相册
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        //将拍摄的照片显示出来
                        Bitmap bitmap = BitmapFactory.
                                decodeStream(getContentResolver().openInputStream(imageUri));
                        im_icon.setImageBitmap(bitmap);

                        editHeadImgRequest(id, imageUri + "");

                        mPopupWindow0.dismiss();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    //判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 19) {
                        //4.4及以上系统使用这个方法处理图片
                        handleImageOnKitKat(data);
                    } else {
                        //4.4以下系统使用这个方法处理图片
                        handleImageBeforeKitKat(data);
                    }

                }
                break;
            case Constant.REQUEST_CODE_WECHAT:
                if (resultCode == Constant.RESULT_CODE_WECHAT) {
                    tv_wechat.setText(data.getStringExtra(Constant.KEY_WECHAT));
                    shortShow("修改成功");
                }
                break;
            case Constant.REQUEST_CODE_QQ:
                if (resultCode == Constant.RESULT_CODE_QQ) {
                    tv_qq.setText(data.getStringExtra(Constant.KEY_QQ));
                    shortShow("修改成功");
                }
                break;
            case Constant.REQUEST_CODE_NAME:
                if (resultCode == Constant.RESULT_CODE_NAME) {
                    tv_name.setText(data.getStringExtra(Constant.KEY_NAME));
                    tv_name1.setText(data.getStringExtra(Constant.KEY_NAME));
                    shortShow("修改成功");
                }
                break;
            case Constant.REQUEST_CODE_WORKTIME:
                if (resultCode == Constant.RESULT_CODE_WORKTIME) {
                    tv_time.setText(data.getStringExtra(Constant.KEY_WORKTIME));
                    shortShow("修改成功");
                }
                break;
            case Constant.REQUEST_CODE_INTRO:
                if (resultCode == Constant.RESULT_CODE_INTRO) {
                    mIntro.setText(data.getStringExtra(Constant.KEY_INTRO));
                    shortShow("修改成功");
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);

    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];//解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse
                                ("content://downloads/public_downloads"),
                        Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            //如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            //如果是file类型的Uri,直接获取图片路径即可
            imagePath = uri.getPath();
        }
        displayImage(imagePath);//根据图片路径显示图片

    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            im_icon.setImageBitmap(bitmap);
            mPopupWindow0.dismiss();
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
        editHeadImgRequest(id, imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        //通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void editSexRequest() {
        Map<String, Object> editHeadImg = new HashMap<>();
        editHeadImg.put("id", id);
        editHeadImg.put("sex", cb_boy.isChecked() ? "男" : "女");
        XHttpUtils.post(HttpUrl.editPersonInfoRequest, gson.toJson(editHeadImg), handler, Constant
                .CONSTANT105);
    }

    private void editHeadImgRequest(int id, String path) {
        Map<String, Object> editHeadImg = new HashMap<>();
        editHeadImg.put("id", id);
        editHeadImg.put("headImg", path);
        XHttpUtils.post(HttpUrl.editHeadImgRequest, gson.toJson(editHeadImg), handler, Constant
                .CONSTANT104);
    }

    public void showPersonInfoRequest(int id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        XHttpUtils.post(HttpUrl.personInfoRequest, gson.toJson(map), handler, Constant.CONSTANT103);
    }
}
