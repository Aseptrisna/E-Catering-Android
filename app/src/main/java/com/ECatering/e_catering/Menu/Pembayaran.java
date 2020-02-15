package com.ECatering.e_catering.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ECatering.e_catering.R;
import com.ECatering.e_catering.Server.InitRetrofit;
import com.ECatering.e_catering.Storage.SharedPrefManager;
import com.google.android.material.snackbar.Snackbar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pembayaran extends AppCompatActivity {
    SharedPrefManager sharedPrefManager;
    TextView Namauser,Namamenu,Hagrasatuan;
    EditText Alamat,Tanggal,jumlahbeli;
    LinearLayout coordinatorLayout;

//    Hasil
      TextView HNamauser,HNamamenu,HHagrasatuan,Htanggal,Htotal,HHargasatuan,Hjumlahbeli,HAlamat;

    ProgressDialog loading;
      Button Submit,bayar,hapus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);
        sharedPrefManager = new SharedPrefManager(this);
        String Username=(sharedPrefManager.getSPEmail());
        String NamaUser=(sharedPrefManager.getSPNama());
        coordinatorLayout=(LinearLayout)findViewById(R.id.pembayaran) ;

        Namauser=(TextView)findViewById(R.id.namapelanggan);
        Namamenu=(TextView)findViewById(R.id.namabarang);
        Hagrasatuan=(TextView)findViewById(R.id.harga);
        Alamat=(EditText) findViewById(R.id.alamat);
        Tanggal=(EditText) findViewById(R.id.tanggal);
        jumlahbeli=(EditText) findViewById(R.id.jumlahbeli);

        HNamauser=(TextView)findViewById(R.id.hasilnamapelanggan);
        HNamamenu=(TextView)findViewById(R.id.hasilnamabarang);
        HHagrasatuan=(TextView)findViewById(R.id.hasilharga);
        Htanggal=(TextView)findViewById(R.id.hasiltanggal);
        Htotal=(TextView)findViewById(R.id.totalbelanja);
        HHargasatuan=(TextView)findViewById(R.id.hasilharga);
        HAlamat=(TextView) findViewById(R.id.hasilalamat);
        Hjumlahbeli=(TextView) findViewById(R.id.hasiljumlahbeli);

        Submit=(Button) findViewById(R.id.tombol1);
        bayar=(Button) findViewById(R.id.tombol2);
        hapus=(Button) findViewById(R.id.tombol3);

        String Nama = getIntent().getStringExtra("NAMA");
        String Hargatotal= getIntent().getStringExtra("HARGA");
        String Hargasatuan= getIntent().getStringExtra("HARGASATUAN");
        String jmlbeli= getIntent().getStringExtra("JML");
        Namamenu.setText(Nama);
        jumlahbeli.setText(jmlbeli);
        Hagrasatuan.setText("Rp."+Hargasatuan);
        Namauser.setText(NamaUser);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loading = ProgressDialog.show(Pembayaran.this,"Loading.....",null,true,true);
                if (Alamat.getText().toString().equals("") || jumlahbeli.getText().toString().equals("") || Tanggal.getText().toString().equals("")) {
                    Alamat.setFocusable(false);
                    jumlahbeli.setFocusable(false);
                    Tanggal.setFocusable(false);
                    showSnackbar();
                }else {
                    hitung();
                }
            }
        });

        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HNamamenu.setText("");
                Hjumlahbeli.setText("");
                HHagrasatuan.setText("");
                HNamauser.setText("");
                Htotal.setText("");
                Htanggal.setText("");
                HAlamat.setText("");
                Alamat.setText("");
                jumlahbeli.setText("");
                Tanggal.setText("");

            }
        });

        bayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(Pembayaran.this,"Loading.....",null,true,true);
                Bayar();
            }
        });
    }

    private void Bayar() {
        String d_username=(sharedPrefManager.getSPEmail());
        String d_tlp=(sharedPrefManager.getSPTelpon());
        final String d_namauser=(sharedPrefManager.getSPNama());
        String d_id = getIntent().getStringExtra("ID");
        String Hargasatuan= getIntent().getStringExtra("HARGASATUAN");
        String d_jml = String.valueOf(jumlahbeli.getText());
        String d_tanggal = String.valueOf(Tanggal.getText());
        String d_alamat = String.valueOf(Alamat.getText());
        String idr = String.valueOf(Hargasatuan);
        int idrsatuan = Integer.parseInt(d_jml);
        final int jmlbarang =Integer.parseInt(idr);
        final int d_harga=idrsatuan*jmlbarang;
        final String d_nama = String.valueOf(Namamenu.getText());
//        String ID=response.body();
        retrofit2.Call<ResponseBody> call = InitRetrofit
                .getInstance()
                .getApi()
                .add_pesanan(d_username,d_id,d_nama,d_alamat,d_tlp,d_tanggal,d_harga,d_jml);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody df = response.body();
                Log.d("response api", response.toString());
                delete();
//                Toast.makeText(SignUp.this,df.getMsg(),Toast.LENGTH_LONG).show();
                loading.dismiss();
                Toast.makeText(Pembayaran.this, "Daftar Pesanan Ditambah", Toast.LENGTH_LONG).show();

//                String pesan= d_namauser.toString();
//                int   pesan3 =d_harga;
////                ResponseBody pesan2 = df;
//                String Mandiri="18360071676154";
//                String Bri    ="7464564652242";
//                Intent kirimWA = new Intent(Intent.ACTION_SEND);
//                Intent intent = kirimWA.setType("text/plain");
//                String semuapesan="Nama: " + pesan
//                        + "\n" +
//                        "ID : " + response.body()+
//                        "\n" +
//                        "Total :Rp. " +
//                        pesan3+ "\n" +
//                        "Rek.Mandiri:"+ Mandiri
//                        +  "\n" + "Rek.Bri: " + Bri
//                        + "\n" + "Atas Nama:Rahmawati Mba";
//
//                kirimWA.putExtra(Intent.EXTRA_TEXT, semuapesan);
//                kirimWA.putExtra("jid", "62895616712143"+"@s.whatsapp.net");
//                kirimWA.setPackage("com.whatsapp");
//                startActivity(kirimWA);
//                finish();
                // Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(Pembayaran.this,t.toString(),Toast.LENGTH_LONG).show();
                // Toast.makeText(SignUp.this, "Registrasi Gagal Ulangi Kembali", Toast.LENGTH_LONG).show();
                // Toast.makeText(SignUp.this, "Register Berhasil" +tempcabang+temporgan+tempJenis+d_username+d_addresshome+d_addresswork, Toast.LENGTH_LONG).show();
            }
        });


        Intent sign = new Intent(this, Dashboard.class);
        startActivity(sign);
//        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

    private void delete() {
        String id= getIntent().getStringExtra("ID");
        retrofit2.Call<ResponseBody> call = InitRetrofit.getInstance().getApi().Deletekeranjang(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    loading.dismiss();
                    Log.d("response api", response.body().toString());
                    Toast.makeText(Pembayaran.this, "", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Pembayaran.this, Dashboard.class);
                    Pembayaran.this.startActivity(intent);
                } else {
                    loading.dismiss();
                    Toast.makeText(Pembayaran.this, "Data Tidak Berhasil Di hapus", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v("debug", "onFailure: ERROR > " + t.toString());

            }
        });
    }

    public void hitung() {
        String Hargasatuan= getIntent().getStringExtra("HARGASATUAN");
        String total = String.valueOf(jumlahbeli.getText());
        String jmlmenu = String.valueOf(jumlahbeli.getText());
        String tanggal = String.valueOf(Tanggal.getText());
        String alamat = String.valueOf(Alamat.getText());
        String idr = String.valueOf(Hargasatuan);

        int idrsatuan = Integer.parseInt(total);
        int jmlbarang =Integer.parseInt(idr);
        int Idrbelanja=idrsatuan*jmlbarang;
        String Username=(sharedPrefManager.getSPEmail());
        String NamaUser=(sharedPrefManager.getSPNama());
        String Nama = getIntent().getStringExtra("NAMA");
        String Hargatotal= getIntent().getStringExtra("HARGA");
        String jmlbeli= getIntent().getStringExtra("JML");
        HNamamenu.setText(Nama);
        Hjumlahbeli.setText(jmlmenu);
        HHagrasatuan.setText("Rp."+Hargasatuan);
        HNamauser.setText(NamaUser);
        Htotal.setText("Rp."+Idrbelanja);
        Htanggal.setText(tanggal);
        HAlamat.setText(alamat);

    }


    public void showSnackbar() {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, "Harus disi", Snackbar.LENGTH_INDEFINITE)
                .setAction("Ulangi", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar snackbar1 = Snackbar.make(coordinatorLayout, "Silahkan ulangi", Snackbar.LENGTH_SHORT);
                        snackbar1.show();
                        Alamat.setFocusableInTouchMode(true);
                        Tanggal.setFocusableInTouchMode(true);
                        jumlahbeli.setFocusableInTouchMode(true);
//                        Password.setFocusableInTouchMode(true);
                    }
                });
        snackbar.show();
    }

}
