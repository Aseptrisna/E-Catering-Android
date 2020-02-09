package com.ECatering.e_catering.Custommize;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ECatering.e_catering.Adapter.Adapter_Custome;
import com.ECatering.e_catering.Adapter.Adapter_Keranjang;
import com.ECatering.e_catering.Adapter.Adapter_Menu;
import com.ECatering.e_catering.Menu.Dashboard;
import com.ECatering.e_catering.Menu.Menu_Utama;
import com.ECatering.e_catering.Menu.Pembayaran;
import com.ECatering.e_catering.Menu_Item.Keranjang_Item;
import com.ECatering.e_catering.Menu_Item.Menu_Item;
import com.ECatering.e_catering.R;
import com.ECatering.e_catering.Response.Response_Menu;
import com.ECatering.e_catering.Response.Response_keranjang;
import com.ECatering.e_catering.Server.ApiServices;
import com.ECatering.e_catering.Server.InitRetrofit;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Nasi_Kotak extends AppCompatActivity {
    RelativeLayout main;
    Spinner isiin;
    EditText jumlah;
    Button Submit,Addkeranjang;
    ImageView Hasilgambar;
    ProgressDialog Loading;
    CardView hasil;
    TextView Idr;
    RecyclerView recyclerView;
    String IsianSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nasi__kotak);
        main=(RelativeLayout)findViewById(R.id.layoutMain);
        isiin=(Spinner) findViewById(R.id.isiian);
        jumlah=(EditText) findViewById(R.id.mJumlahorang);
        Submit=(Button) findViewById(R.id.Proses);
//        Addkeranjang=(Button) findViewById(R.id.Keranjang);
//        Hasilgambar=(ImageView) findViewById(R.id.gambar);
//        hasil=(CardView)findViewById(R.id.mhasil);
//        Idr=(TextView) findViewById(R.id.idr);
//        hasil.setVisibility(View.GONE);

        recyclerView = (RecyclerView)findViewById(R.id.listCustom);
        recyclerView.setHasFixedSize(true);
        //GRID 2 kolom
        GridLayoutManager llm=new GridLayoutManager(this,1);
//
//        //STAGGER 4 KOLOM
//         StaggeredGridLayoutManager llm=new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);


        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isiin.toString().equals("") ||jumlah.getText().toString().equals("") ){
                    isiin.setFocusable(false);
                    jumlah.setFocusable(false);
//                    Tanggal.setFocusable(false);
                    showSnackbar();
                }else {
//                hasil.setVisibility(View.VISIBLE);
                    tampilhasil();
                }
            }
        });

        initSpinnerIsian();
        isiin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedIsian = parent.getItemAtPosition(position).toString();
//                requestDetailDosen(selectedName);
                IsianSpinner=selectedIsian;
                Toast.makeText(Nasi_Kotak.this, "Kamu memilih : " + selectedIsian, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initSpinnerIsian() {
        Loading = ProgressDialog.show(Nasi_Kotak.this, null, "harap tunggu...", true, false);
        final ApiServices api = InitRetrofit.getInstance().getApi();
        Call<Response_Menu> menuCall = api.TampilNasiKotak();
        menuCall.enqueue(new Callback<Response_Menu>() {
            @Override
            public void onResponse(Call<Response_Menu> call, Response<Response_Menu> response) {
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    List<Menu_Item> data_menu= response.body().getMenu();
                    boolean status = response.body().isStatus();
                    if (status){
                        Loading.dismiss();
                        List<Menu_Item> semuadosenItems = response.body().getMenu();
                        List<String> listSpinner = new ArrayList<String>();
                        for (int i = 0; i < semuadosenItems.size(); i++){
                            listSpinner.add(semuadosenItems.get(i).getDeskripsi());
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Nasi_Kotak.this,
                                android.R.layout.simple_spinner_item, listSpinner);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        isiin.setAdapter(adapter);

//                        Adapter_Custome adapterlagi = new Adapter_Custome(Nasi_Kotak.this, data_menu);
//                        List<Menu_Item> dataMenu_items= response.body().getMenu();
                        Adapter_Custome adapter_custome=new Adapter_Custome(Nasi_Kotak.this,data_menu);
//                        adapter_custome.onBindViewHolder(holder.harga.setText("Rp"+menu.get(position).getHarga()));

//                        Adapter_Menu adapter = new Adapter_Menu(Nasi_Kotak.this, data_menu);
//                        isiin.setAdapter((SpinnerAdapter) adapter);
                    } else {
                        Loading.dismiss();
                        Toast.makeText(Nasi_Kotak.this, "Tidak Ada data Menu saat ini", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Response_Menu> call, Throwable t) {
                // print ke log jika Error
                t.printStackTrace();
            }
        });

}

    private void tampilhasil() {
        String d_kategori = String.valueOf(IsianSpinner);
//        Toast.makeText(this, ""+IsianSpinner, Toast.LENGTH_SHORT).show();
        retrofit2.Call<Response_Menu> call = InitRetrofit.getInstance().getApi().CustomizeKotak(d_kategori);
        call.enqueue(new Callback<Response_Menu>() {
            @Override
            public void onResponse(Call<Response_Menu> call, Response<Response_Menu> response) {
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    List<Menu_Item> data_menu= response.body().getMenu();
                    boolean status = response.body().isStatus();
                    if (status){
                        Adapter_Custome adapter = new Adapter_Custome(Nasi_Kotak.this, data_menu);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(Nasi_Kotak.this, "Tidak Ada data Menu saat ini", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Response_Menu> call, Throwable t) {
                // print ke log jika Error
                t.printStackTrace();
            }
        });
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
        Snackbar snackbar = Snackbar.make(main, "Harus disi", Snackbar.LENGTH_INDEFINITE)
                .setAction("Ulangi", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar snackbar1 = Snackbar.make(main, "Silahkan ulangi", Snackbar.LENGTH_SHORT);
                        snackbar1.show();
                        isiin.setFocusableInTouchMode(true);
                        jumlah.setFocusableInTouchMode(true);
//                        jumlahbeli.setFocusableInTouchMode(true);
//                        Password.setFocusableInTouchMode(true);
                    }
                });
        snackbar.show();
    }
}
