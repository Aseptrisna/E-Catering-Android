package com.ECatering.e_catering.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ECatering.e_catering.MainActivity;
import com.ECatering.e_catering.R;
import com.wang.avi.AVLoadingIndicatorView;

public class Splash_Screen extends AppCompatActivity {

    //pembuatan variabel untuk linearlayout
    private LinearLayout lv_loading;
    private AVLoadingIndicatorView avi;
    Animation uptodown, downtoup;
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);
        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        TextView nama=(TextView)findViewById(R.id.namaapps);
        lv_loading = (LinearLayout) findViewById(R.id.lv_loading);
        logo=(ImageView)findViewById(R.id.logonya);


//        avi= (AVLoadingIndicatorView) findViewById(R.id.avi);
//        avi.setIndicator("BallClipRotateMultipleIndicator");
        nama.setAnimation(downtoup);
        logo.setAnimation(uptodown);

//        avi.setAnimation(downtoup);

        //membuat sebuah proses yang ter delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //mendefenisikan Intent activity
                Intent i = new Intent(Splash_Screen.this,Login.class);
                startActivity(i);
                //finish berguna untuk mengakhiri activity
                //disini saya menggunakan finish,supaya ketika menekan tombol back
                //tidak kembali pada activity SplashScreen nya
                finish();
            }
            //disini maksud 3000 nya itu adalah lama screen ini terdelay 3 detik,dalam satuan mili second
        }, 3000);
    }

}