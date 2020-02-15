package com.ECatering.e_catering.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ECatering.e_catering.R;
import com.ECatering.e_catering.Server.InitRetrofit;
import com.ECatering.e_catering.Storage.SharedPrefManager;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Edit_profile extends AppCompatActivity {
Button update;
LinearLayout coordinatorLayout;
TextView NM,EM,TL,AL;
    ProgressDialog loading;
    SharedPrefManager sharedPrefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        sharedPrefManager = new SharedPrefManager(this);
        coordinatorLayout=(LinearLayout)findViewById(R.id.myapdet);
        NM=(TextView)findViewById(R.id.Nama_Edit);
        EM=(TextView)findViewById(R.id.Email_Edit);
        TL=(TextView)findViewById(R.id.Telpon_Edit);
        AL=(TextView)findViewById(R.id.Alamat_Edit);
        EM.setText(sharedPrefManager.getSPEmail());

        update=(Button)findViewById(R.id.EditBtn);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NM.getText().toString().equals("") || EM.getText().toString().equals("")|| TL.getText().toString().equals("")
                        || AL.getText().toString().equals("") ) {
                    NM.setFocusable(false);
                    EM.setFocusable(false);
                    TL.setFocusable(false);
                    AL.setFocusable(false);
                    showSnackbar();
                } else {
                   loading = ProgressDialog.show(Edit_profile.this,"Loading.....",null,true,true);
                   UpadateProfile();
                }
            }
        });
    }

    private void UpadateProfile() {
        String nama = String.valueOf(NM.getText());
        String alamat = String.valueOf(AL.getText());
        String telpon = String.valueOf(TL.getText());
//        String email= String.valueOf(EM.getText());
        String email = (sharedPrefManager.getSPEmail());
//        if (user.equals("")) {
//            showSnackbar();
//        } else if (pas.equals("")) {
//            showSnackbar();
//        } else {
//            mApiService.userLogin(Username.getText().toString(), Password.getText().toString())
            retrofit2.Call<ResponseBody> call = InitRetrofit.getInstance().getApi().userapdet(nama,alamat,telpon,email);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()){
                        loading.dismiss();
//                        Toast.makeText(Edit_profile.this, "Berhasil"+response, Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(Edit_profile.this,Dashboard.class);
                        startActivity(intent);
                        finish();

                    } else {
                        loading.dismiss();
                        Toast.makeText(Edit_profile.this, "Gagal"+response, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.v("debug", "onFailure: ERROR > " + t.toString());
                    loading.dismiss();

                }
            });
        }


    public void showSnackbar() {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, "Lengkapi data terlebih dahulu", Snackbar.LENGTH_INDEFINITE)
                .setAction("Ulangi", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar snackbar1 = Snackbar.make(coordinatorLayout, "Silahkan ulangi", Snackbar.LENGTH_SHORT);
                        snackbar1.show();
                        NM.setFocusableInTouchMode(true);
                        AL.setFocusableInTouchMode(true);
                        EM.setFocusableInTouchMode(true);
                        TL.setFocusableInTouchMode(true);
                    }
                });
        snackbar.show();
    }

    public void onBackPressed() {
        super.onBackPressed();
        goBackMenu();
    }

    public void goBackMenu(){
        startActivity(new Intent(this, Dashboard.class));
        finish();
    }


}
