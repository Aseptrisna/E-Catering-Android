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
import android.widget.TextView;
import android.widget.Toast;

import com.ECatering.e_catering.Adapter.Adapter_Custome;
import com.ECatering.e_catering.Menu.Dashboard;
import com.ECatering.e_catering.Menu.Menu_Utama;
import com.ECatering.e_catering.Menu_Item.Menu_Item;
import com.ECatering.e_catering.R;
import com.ECatering.e_catering.Response.Response_Menu;
import com.ECatering.e_catering.Server.ApiServices;
import com.ECatering.e_catering.Server.InitRetrofit;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tumpeng extends AppCompatActivity {

    RelativeLayout main;
    Spinner isian,Jenis;
    EditText jumlah;
    Button Submit,Addkeranjang;
    ImageView Hasilgambar;
    ProgressDialog Loading;
    CardView hasil;
    TextView Idr;
    RecyclerView recyclerView;
    String IsianSpinner,Mjeniss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tumpeng);
        Jenis = (Spinner) findViewById(R.id.Spinnerjenis);
        isian = (Spinner) findViewById(R.id.Misian);
        Submit = (Button) findViewById(R.id.Proses);
        recyclerView = (RecyclerView) findViewById(R.id.listCustom);
        recyclerView.setHasFixedSize(true);
        //GRID 2 kolom
        GridLayoutManager llm = new GridLayoutManager(this, 1);
//
//        //STAGGER 4 KOLOM
//         StaggeredGridLayoutManager llm=new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);


        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isian.toString().equals("") || Jenis.toString().equals("")) {
                    Jenis.setFocusable(false);
                    isian.setFocusable(false);
//                    Tanggal.setFocusable(false);
                    showSnackbar();
                } else {
//                hasil.setVisibility(View.VISIBLE);
                    tampilhasil();
                }
            }
        });


        initSpinnerIsian();
        isian.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedIsian = parent.getItemAtPosition(position).toString();
//                requestDetailDosen(selectedName);
                IsianSpinner=selectedIsian;
                Toast.makeText(Tumpeng.this, "Kamu memilih : " + selectedIsian, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        initSpinnerJenis();
        Jenis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedIsian = parent.getItemAtPosition(position).toString();
//                requestDetailDosen(selectedName);
                Mjeniss=selectedIsian;
                Toast.makeText(Tumpeng.this, "Kamu memilih : " + selectedIsian, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initSpinnerJenis() {

//        Loading = ProgressDialog.show(Tumpeng.this, null, "harap tunggu...", true, false);
        final ApiServices api = InitRetrofit.getInstance().getApi();
        Call<Response_Menu> menuCall = api.TampilkategoriTumpeng();
        menuCall.enqueue(new Callback<Response_Menu>() {
            @Override
            public void onResponse(Call<Response_Menu> call, Response<Response_Menu> response) {
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    List<Menu_Item> data_menu= response.body().getMenu();
                    boolean status = response.body().isStatus();
                    if (status){
//                        Loading.dismiss();
                        List<Menu_Item> semuadosenItems = response.body().getMenu();
                        List<String> listSpinner = new ArrayList<String>();
                        for (int i = 0; i < semuadosenItems.size(); i++){
                            listSpinner.add(semuadosenItems.get(i).getJenis());
                        }

                        ArrayAdapter<String> adapt = new ArrayAdapter<String>(Tumpeng.this,
                                android.R.layout.simple_spinner_item, listSpinner);
                        adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        Jenis.setAdapter(adapt);

//                        Adapter_Custome adapterlagi = new Adapter_Custome(Nasi_Kotak.this, data_menu);
//                        List<Menu_Item> dataMenu_items= response.body().getMenu();
                        Adapter_Custome adapter_custome=new Adapter_Custome(Tumpeng.this,data_menu);
//                        adapter_custome.onBindViewHolder(holder.harga.setText("Rp"+menu.get(position).getHarga()));

//                        Adapter_Menu adapter = new Adapter_Menu(Nasi_Kotak.this, data_menu);
//                        isiin.setAdapter((SpinnerAdapter) adapter);
                    } else {
//                        Loading.dismiss();
                        Toast.makeText(Tumpeng.this, "Tidak Ada data Menu saat ini", Toast.LENGTH_SHORT).show();
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

    private void initSpinnerIsian() {
//        Loading = ProgressDialog.show(Tumpeng.this, null, "harap tunggu...", true, false);
        final ApiServices api = InitRetrofit.getInstance().getApi();
        Call<Response_Menu> menuCall = api.TampiljenisTumpeng();
        menuCall.enqueue(new Callback<Response_Menu>() {
            @Override
            public void onResponse(Call<Response_Menu> call, Response<Response_Menu> response) {
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    List<Menu_Item> data_menu= response.body().getMenu();
                    boolean status = response.body().isStatus();
                    if (status){
//                        Loading.dismiss();
                        List<Menu_Item> semuadosenItems = response.body().getMenu();
                        List<String> listSpinner = new ArrayList<String>();
                        for (int i = 0; i < semuadosenItems.size(); i++){
                            listSpinner.add(semuadosenItems.get(i).getDeskripsi());
                        }

                        ArrayAdapter<String> adapt = new ArrayAdapter<String>(Tumpeng.this,
                                android.R.layout.simple_spinner_item, listSpinner);
                        adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        isian.setAdapter(adapt);

//                        Adapter_Custome adapterlagi = new Adapter_Custome(Nasi_Kotak.this, data_menu);
//                        List<Menu_Item> dataMenu_items= response.body().getMenu();
                        Adapter_Custome adapter_custome=new Adapter_Custome(Tumpeng.this,data_menu);
//                        adapter_custome.onBindViewHolder(holder.harga.setText("Rp"+menu.get(position).getHarga()));

//                        Adapter_Menu adapter = new Adapter_Menu(Nasi_Kotak.this, data_menu);
//                        isiin.setAdapter((SpinnerAdapter) adapter);
                    } else {
//                        Loading.dismiss();
                        Toast.makeText(Tumpeng.this, "Tidak Ada data Menu saat ini", Toast.LENGTH_SHORT).show();
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
        String d_jenis = String.valueOf(Mjeniss);
//        Toast.makeText(this, ""+IsianSpinner, Toast.LENGTH_SHORT).show();
        retrofit2.Call<Response_Menu> call = InitRetrofit.getInstance().getApi().CustomizeTumpeng(d_kategori,d_jenis);
        call.enqueue(new Callback<Response_Menu>() {
            @Override
            public void onResponse(Call<Response_Menu> call, Response<Response_Menu> response) {
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    List<Menu_Item> data_menu= response.body().getMenu();
                    boolean status = response.body().isStatus();
                    if (status){
                        Adapter_Custome adapter = new Adapter_Custome(Tumpeng.this, data_menu);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(Tumpeng.this, "Tidak Ada data Menu saat ini", Toast.LENGTH_SHORT).show();
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
                        isian.setFocusableInTouchMode(true);
                        jumlah.setFocusableInTouchMode(true);
//                        jumlahbeli.setFocusableInTouchMode(true);
//                        Password.setFocusableInTouchMode(true);
                    }
                });
        snackbar.show();
    }
}
