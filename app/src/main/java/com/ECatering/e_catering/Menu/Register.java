package com.ECatering.e_catering.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ECatering.e_catering.R;
import com.ECatering.e_catering.Response.Response_Register;
import com.ECatering.e_catering.Server.InitRetrofit;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    private LinearLayout coordinatorLayout;
    Animation uptodown, downtoup,kiri_kanan,kanan_kiri;
    FrameLayout FormRegister;
    TextView Login;
    TextView Username,Nama,Password;
    Button Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        kiri_kanan = AnimationUtils.loadAnimation(this,R.anim.to_left);
        kanan_kiri = AnimationUtils.loadAnimation(this,R.anim.to_right);
        FormRegister=(FrameLayout)findViewById(R.id.formregister);
        coordinatorLayout = findViewById(R.id.Myregister);
        FormRegister.setAnimation(kanan_kiri);
        Username=(TextView)findViewById(R.id.Email_Register);
        Password=(TextView)findViewById(R.id.Register_password);
        Nama=(TextView)findViewById(R.id.Nama_Register);
        Register=(Button)findViewById(R.id.RegisternBtn);


        Login=(TextView) findViewById(R.id.sudahpunyaakun);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Register.this,Login.class);
                startActivity(intent);
                finish();
            }
        });


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Nama.getText().toString().equals("") ||Username.getText().toString().equals("") || Password.getText().toString().equals("")) {
                    Nama.setFocusable(false);
                    Username.setFocusable(false);
                    Password.setFocusable(false);
                    showSnackbar();
                } else {
                    RegisterUser();

                }
            }
        });
    }

    private void RegisterUser() {
        String d_name = String.valueOf(Nama.getText());
        String d_mail = String.valueOf(Username.getText());
        String d_password = String.valueOf(Password.getText());

        if (d_name.equals("")) {
            showSnackbar();
        } else if (d_mail.equals("")) {
            showSnackbar();
        } else if (d_password.equals("")) {
            showSnackbar();
        } else {

            // Toast.makeText(SignUp.this,"Tombol sign up di tekan",Toast.LENGTH_LONG).show();
            retrofit2.Call<Response_Register> call = InitRetrofit
                    .getInstance()
                    .getApi()
                    .User(d_name,d_mail,d_password);

            call.enqueue(new Callback<Response_Register>() {
                @Override
                public void onResponse(Call<Response_Register> call, Response<Response_Register> response) {
                    Response_Register df = response.body();
                    //Toast.makeText(SignUp.this,df.getMsg(),Toast.LENGTH_LONG).show();
                    Toast.makeText(Register.this, "Register Berhasil", Toast.LENGTH_LONG).show();
                    // Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

                }



                @Override
                public void onFailure(Call<Response_Register> call, Throwable t) {
                    Toast.makeText(Register.this,t.toString(),Toast.LENGTH_LONG).show();
                    // Toast.makeText(SignUp.this, "Registrasi Gagal Ulangi Kembali", Toast.LENGTH_LONG).show();
                    // Toast.makeText(SignUp.this, "Register Berhasil" +tempcabang+temporgan+tempJenis+d_username+d_addresshome+d_addresswork, Toast.LENGTH_LONG).show();
                }
            });


            Intent sign = new Intent(this, Login.class);
            startActivity(sign);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
        }
    }

    public void backSignin() {
        Intent sign = new Intent(this, Login.class);
        startActivity(sign);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }



    public void showSnackbar() {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, "Lengkapi data terlebih dahulu", Snackbar.LENGTH_INDEFINITE)
                .setAction("Ulangi", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar snackbar1 = Snackbar.make(coordinatorLayout, "Silahkan ulangi", Snackbar.LENGTH_SHORT);
                        snackbar1.show();
                        Nama.setFocusableInTouchMode(true);
                        Username.setFocusableInTouchMode(true);
                        Password.setFocusableInTouchMode(true);
                    }
                });
        snackbar.show();
    }

}
