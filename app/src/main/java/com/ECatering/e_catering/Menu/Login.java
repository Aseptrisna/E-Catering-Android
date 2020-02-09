package com.ECatering.e_catering.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ECatering.e_catering.MainActivity;
import com.ECatering.e_catering.R;
import com.ECatering.e_catering.Server.ApiServices;
import com.ECatering.e_catering.Server.InitRetrofit;
import com.ECatering.e_catering.Storage.SharedPrefManager;
import com.google.android.material.snackbar.Snackbar;
import com.ECatering.e_catering.Response.Response_Login;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    private LinearLayout coordinatorLayout;
    Animation uptodown, downtoup;
    FrameLayout Formlogin;
    TextView Register,Username,Password;
    Button Login;
    ProgressDialog loading;
    ApiServices mApiService;
    SharedPrefManager sharedPrefManager;

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        if (SharedPrefManager.getInstance(this).isLoggedIn()){
//            Intent intent = new Intent(this,Menu_Utama.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//        }else{
//
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPrefManager = new SharedPrefManager(this);
        coordinatorLayout = findViewById(R.id.Mylogin);
        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        Formlogin=(FrameLayout)findViewById(R.id.formlogin);
        Username=(TextView)findViewById(R.id.Username);
        Password=(TextView)findViewById(R.id.Password);
        Formlogin.setAnimation(downtoup);

        Register=(TextView) findViewById(R.id.Belumpunyaakun);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Login.this,Register.class);
                startActivity(intent);
                finish();
            }
        });

        Login=(Button)findViewById(R.id.LogInBtn);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent =new Intent(Login.this,Menu_Utama.class);
//                startActivity(intent);
//                finish();
                if (Username.getText().toString().equals("") || Password.getText().toString().equals("")) {
                    Username.setFocusable(false);
                    Password.setFocusable(false);
                    showSnackbar();
                } else {
                    loading = ProgressDialog.show(Login.this,"Loading.....",null,true,true);
                    RequestLogin();
                }
            }
        });

        if (sharedPrefManager.getSudahLogin()){
            startActivity(new Intent(Login.this, Dashboard.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }
    }

    private void RequestLogin(){
        String user = String.valueOf(Username.getText());
        String pas = String.valueOf(Password.getText());
        if (user.equals("")) {
            showSnackbar();
        } else if (pas.equals("")) {
            showSnackbar();
        } else {
//            mApiService.userLogin(Username.getText().toString(), Password.getText().toString())
            retrofit2.Call<ResponseBody> call = InitRetrofit.getInstance().getApi().userLogin(user,pas);
            call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()){
                                loading.dismiss();
                                try {
                                    JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                    if (jsonRESULTS.getString("error").equals("false")){
                                        Log.d("response api", jsonRESULTS.toString());
                                        Toast.makeText(Login.this, "BERHASIL LOGIN", Toast.LENGTH_SHORT).show();
//                                        String id = jsonRESULTS.getJSONObject("user").getString("ID");
                                        String nama = jsonRESULTS.getJSONObject("user").getString("nama");
                                        String alamat = jsonRESULTS.getJSONObject("user").getString("Alamat");
                                        String email = jsonRESULTS.getJSONObject("user").getString("email");
                                        String telpon = jsonRESULTS.getJSONObject("user").getString("telpon");
//                                        sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA, id);
                                        sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA, nama);
                                        sharedPrefManager.saveSPString(SharedPrefManager.SP_EMAIL, email);
                                        sharedPrefManager.saveSPString(SharedPrefManager.SP_ALAMAT, alamat);
                                        sharedPrefManager.saveSPString(SharedPrefManager.SP_TELPON, telpon);
                                        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                                        Log.d("alamat", alamat+telpon.toString());
                                        startActivity(new Intent(Login.this, Dashboard.class)
                                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                                        finish();
                                    } else {
                                        String error_message = jsonRESULTS.getString("error_msg");
                                        Toast.makeText(Login.this, error_message, Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                loading.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.v("debug", "onFailure: ERROR > " + t.toString());
                            loading.dismiss();

                        }
                    });
        }

    }


    public void showSnackbar() {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, "Lengkapi data terlebih dahulu", Snackbar.LENGTH_INDEFINITE)
                .setAction("Ulangi", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar snackbar1 = Snackbar.make(coordinatorLayout, "Silahkan ulangi", Snackbar.LENGTH_SHORT);
                        snackbar1.show();
                        Username.setFocusableInTouchMode(true);
                        Password.setFocusableInTouchMode(true);
                    }
                });
        snackbar.show();
    }


}
