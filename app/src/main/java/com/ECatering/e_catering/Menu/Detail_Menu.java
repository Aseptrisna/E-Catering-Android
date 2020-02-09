package com.ECatering.e_catering.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ECatering.e_catering.R;
import com.ECatering.e_catering.Response.Response_Register;
import com.ECatering.e_catering.Server.InitRetrofit;
import com.ECatering.e_catering.Storage.SharedPrefManager;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detail_Menu extends AppCompatActivity {

    ImageView gambarmenu;
    TextView nama,harga;
    EditText Jml_menu;
    WebView Deskripsi;
    SharedPrefManager sharedPrefManager;
    Button tambah_keranjang;
    LinearLayout coordinatorLayout;
    ProgressDialog loading;
//    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__menu);
        sharedPrefManager = new SharedPrefManager(this);
        String Username=(sharedPrefManager.getSPEmail());
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        gambarmenu=(ImageView)findViewById(R.id.Gambarmenudetail);
        nama=(TextView) findViewById(R.id.Nama_Menu);
        harga=(TextView) findViewById(R.id.Harga_Menu);
        Deskripsi=(WebView)findViewById(R.id.Deskripsi_Menu);
        tambah_keranjang=(Button)findViewById(R.id.tambahkeranjang);
        Jml_menu=(EditText)findViewById(R.id.Jumlah_belanja);
        coordinatorLayout=(LinearLayout)findViewById(R.id.layout2);

        tambah_keranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Jml_menu.getText().toString().equals("")) {
                    showSnackbar();
                }else {
                    loading = ProgressDialog.show(Detail_Menu.this,"Loading.....",null,true,true);
                    Tambah_keranjang();
                }
            }
        });
        Tampil_detail();


    }

    public void Tampil_detail() {
        String Nama = getIntent().getStringExtra("NAMA");
        String Harga= getIntent().getStringExtra("HARGA");
        String Deskripsi_Menu= getIntent().getStringExtra("DESKRIPSI");
        String gambar_menu= getIntent().getStringExtra("GAMBAR_MENU");
        String gambar= getIntent().getStringExtra("GAMBAR");
        String id= getIntent().getStringExtra("ID");
        Picasso.with(this).load(gambar_menu).into(gambarmenu);
        nama.setText(Nama);
        harga.setText("Rp"+Harga);
        Deskripsi.getSettings().setJavaScriptEnabled(true);
        Deskripsi.loadData("<strong>Deskripsi Menu :</strong> "+Deskripsi_Menu, "text/html; charset=utf-8", "UTF-8");
    }

    public void Tambah_keranjang(){
        String d_Username=(sharedPrefManager.getSPEmail());
        String d_id = getIntent().getStringExtra("ID");
        String d_nama = getIntent().getStringExtra("NAMA");
        String d_harga=getIntent().getStringExtra("HARGA");
        String d_gambar= getIntent().getStringExtra("GAMBAR");
        String d_Jml= String.valueOf(Jml_menu.getText());
        retrofit2.Call<ResponseBody> call = InitRetrofit
                .getInstance()
                .getApi()
                .Keranjang(d_Username,d_id,d_nama,d_harga,d_gambar,d_Jml);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody df = response.body();
                //Toast.makeText(SignUp.this,df.getMsg(),Toast.LENGTH_LONG).show();
                loading.dismiss();
                Toast.makeText(Detail_Menu.this, "Daftar Belanja Ditambah", Toast.LENGTH_LONG).show();
                // Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(Detail_Menu.this,t.toString(),Toast.LENGTH_LONG).show();
                // Toast.makeText(SignUp.this, "Registrasi Gagal Ulangi Kembali", Toast.LENGTH_LONG).show();
                // Toast.makeText(SignUp.this, "Register Berhasil" +tempcabang+temporgan+tempJenis+d_username+d_addresshome+d_addresswork, Toast.LENGTH_LONG).show();
            }
        });


        Intent sign = new Intent(this, Dashboard.class);
        startActivity(sign);
//        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }


    public void onBackPressed() {
        super.onBackPressed();
        goBackMenu();
    }

    public void goBackMenu(){
        startActivity(new Intent(this, Dashboard.class));
        finish();
    }

    public void showSnackbar() {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, "Tidak Boleh kosong", Snackbar.LENGTH_INDEFINITE)
                .setAction("Ulangi", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar snackbar1 = Snackbar.make(coordinatorLayout, "Silahkan ulangi", Snackbar.LENGTH_SHORT);
                        snackbar1.show();
                        Jml_menu.setFocusableInTouchMode(true);
//                        Password.setFocusableInTouchMode(true);
                    }
                });
        snackbar.show();
    }

}
