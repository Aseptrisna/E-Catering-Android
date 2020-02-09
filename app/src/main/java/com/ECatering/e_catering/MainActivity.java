package com.ECatering.e_catering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ECatering.e_catering.Adapter.Adapter_Menu;
import com.ECatering.e_catering.Menu.Dashboard;
import com.ECatering.e_catering.Menu.Menu_Utama;
import com.ECatering.e_catering.Menu_Item.Menu_Item;
import com.ECatering.e_catering.Response.Response_Menu;
import com.ECatering.e_catering.Server.ApiServices;
import com.ECatering.e_catering.Server.InitRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    ImageView keluar,Kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__utama);

        recyclerView = (RecyclerView)findViewById(R.id.list_menu);
        recyclerView.setHasFixedSize(true);
        //GRID 2 kolom
        GridLayoutManager llm=new GridLayoutManager(this,2);
//
//        //STAGGER 4 KOLOM
//         StaggeredGridLayoutManager llm=new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        tampilmenu();

        Kembali=(ImageView)findViewById(R.id.back);
        Kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public  void  fliverImages(int images){

    }
    private void tampilmenu() {
        ApiServices api = InitRetrofit.getInstance().getApi();
        Call<Response_Menu> menuCall = api.tampiltumpeng();
        menuCall.enqueue(new Callback<Response_Menu>() {
            @Override
            public void onResponse(Call<Response_Menu> call, Response<Response_Menu> response) {
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    List<Menu_Item> data_menu= response.body().getMenu();
                    boolean status = response.body().isStatus();
                    if (status){
                        Adapter_Menu adapter = new Adapter_Menu(MainActivity.this, data_menu);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(MainActivity.this, "Tidak Ada data Menu saat ini", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goBackMenu();
    }

    public void goBackMenu(){
        startActivity(new Intent(this, Dashboard.class));
        finish();
    }

}
