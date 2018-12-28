package project.hqq.com.intern.activities;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import project.hqq.com.intern.R;
import project.hqq.com.intern.views.BaseActivity;
import project.hqq.com.intern.views.MyApplication;

public class ResumeAddActivity extends BaseActivity implements View.OnClickListener {
    ImageView im_back ;
    EditText edit;
    Button btn_camera;
    Button btn_bulm;
    ImageView img;

    private static final int TAKE_PHOTO=1;
    //调用系统相册-选择图片
    private static final int IMAGE = 2;
    private String fileName="";
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_add);
        initView();
    }

    private void initView() {
        im_back = (ImageView)findViewById(R.id.im_back);
        edit = (EditText)findViewById(R.id.edit);
        btn_camera = (Button)findViewById(R.id.btn_camera);
        btn_bulm = (Button)findViewById(R.id.btn_bulm);
        img = (ImageView)findViewById(R.id.img);

        btn_bulm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("click","acsdc");
                //调用相册
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE);
            }
        });
        btn_camera.setOnClickListener(this);
        im_back.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.im_back:
                if (edit.getText().toString().length()==0){
                    Toast.makeText(ResumeAddActivity.this,"请输入简历名称!",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent1 =getIntent();
                    Bundle bundle = new Bundle();
                    intent1.putExtra("name",edit.getText().toString());

                    ByteArrayOutputStream baos=new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                    byte[] bitmapByte =baos.toByteArray();
                    intent1.putExtra("bitmap", bitmapByte);

                    ResumeAddActivity.this.setResult(0,intent1);
                    ResumeAddActivity.this.finish();
                }
                break;

            case R.id.btn_camera:
                startActivityForResult(new Intent("android.media.action.IMAGE_CAPTURE"), TAKE_PHOTO);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent intent) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String sdStatus = Environment.getExternalStorageState();
                    if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
                        Log.i("TestFile",
                                "SD card is not avaiable/writeable right now.");
                    }
                    new DateFormat();
                    //以时间作为文件名
                    String name = DateFormat.format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
                    Bundle bundle = intent.getExtras();
                    bitmap = (Bitmap) bundle.get("data"); //获取相机返回的数据，转换为Bitmap格式

                    FileOutputStream b = null;
                    File file = new File(MyApplication.SD_LOCAL + "/Image");
                    boolean mkdirs = file.mkdirs();

                    fileName = MyApplication.SD_LOCAL + "/Image/" + name;

                    try {
                        b = new FileOutputStream(fileName);
                        if (bitmap != null) {
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);
                        } else {

                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            assert b != null;
                            b.flush();
                            b.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    img.setImageBitmap(bitmap);
                    break;
                }
                break;
            case 2:
                if (resultCode == RESULT_OK && intent != null) {
                    Uri uri = intent.getData();
                    Cursor cursor = getContentResolver().query(uri, null,
                            null, null, null);
                    assert cursor != null;
                    cursor.moveToFirst();
                    ContentResolver contentResolver = getContentResolver();
                    try {
                        bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(uri));
                        img.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    String imgPath = cursor.getString(1); // 图片文件路径
                    cursor.close();
                    fileName = imgPath;
                }
                break;

        }
    }
}
