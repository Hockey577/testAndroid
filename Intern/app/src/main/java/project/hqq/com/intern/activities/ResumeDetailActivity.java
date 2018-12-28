package project.hqq.com.intern.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import project.hqq.com.intern.R;
import project.hqq.com.intern.bean.JobBean;
import project.hqq.com.intern.bean.ResumeBackBean;
import project.hqq.com.intern.views.BaseActivity;

public class ResumeDetailActivity extends BaseActivity {
    ImageView im_back;
    TextView im_text;
    ImageView im_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_detail);

        Intent intent = getIntent();
        String name =intent.getStringExtra("name");
        byte[] bis = intent.getByteArrayExtra("bitmap");
        Bitmap img = BitmapFactory.decodeByteArray(bis,0,bis.length);


        im_back=(ImageView)findViewById(R.id.im_back);
        im_text=(TextView)findViewById(R.id.im_text);
        im_img=(ImageView)findViewById(R.id.im_img);

        im_text.setText(name);
        im_img.setImageBitmap(img);

        im_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
