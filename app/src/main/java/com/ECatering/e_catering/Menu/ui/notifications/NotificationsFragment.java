package com.ECatering.e_catering.Menu.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ECatering.e_catering.Adapter.Adapter_Keranjang;
import com.ECatering.e_catering.Adapter.Adapter_Menu;
import com.ECatering.e_catering.Menu.Dashboard;
import com.ECatering.e_catering.Menu.Login;
import com.ECatering.e_catering.Menu.Menu_Utama;
import com.ECatering.e_catering.Menu_Item.Keranjang_Item;
import com.ECatering.e_catering.Menu_Item.Menu_Item;
import com.ECatering.e_catering.R;
import com.ECatering.e_catering.Response.Response_keranjang;
import com.ECatering.e_catering.Server.ApiServices;
import com.ECatering.e_catering.Server.InitRetrofit;
import com.ECatering.e_catering.Storage.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsFragment extends Fragment {
    private RecyclerView recyclerView;
    SharedPrefManager sharedPrefManager;
    ImageButton Deleteitem;
    Adapter_Keranjang adapter_keranjang;
    Button Bayar;
    Context context;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        context = getActivity();
        sharedPrefManager = new SharedPrefManager(getActivity());
        String Username=(sharedPrefManager.getSPEmail());
        Deleteitem=(ImageButton)root.findViewById(R.id.delChkoutItemBut);
        recyclerView = (RecyclerView)root.findViewById(R.id.listkeranjang);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        Bayar=(Button)root.findViewById(R.id.Prosesbayar);
//        recyclerView.setHasFixedSize(true);
        //GRID 2 kolom
//        GridLayoutManager llm=new GridLayoutManager(getActivity(),2);
//
//        //STAGGER 4 KOLOM
//         StaggeredGridLayoutManager llm=new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(llm);
//        Deleteitem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Delete();
//            }
//        });
        tampilkeranjang();
//
        return root;
    }

    private void tampilkeranjang() {
        String Username=(sharedPrefManager.getSPEmail());
        retrofit2.Call<Response_keranjang> call = InitRetrofit.getInstance().getApi().tampilkeranjang(Username);
        call.enqueue(new Callback<Response_keranjang>() {
            @Override
            public void onResponse(Call<Response_keranjang> call, Response<Response_keranjang> response) {
                if (response.isSuccessful()) {
                    Log.d("response api", response.body().toString());
                    List<Keranjang_Item> data_menu = response.body().getKeranjang();
                    boolean status = response.body().isStatus();
                    if (status){
                        Adapter_Keranjang adapter = new Adapter_Keranjang(getActivity(), data_menu);
                        recyclerView.setAdapter(adapter);
                    } else {
//
                        Toast.makeText(getActivity(), "Tidak Ada data Daftar Belanja Saat ini", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<Response_keranjang> call, Throwable t) {
                Log.v("debug", "onFailure: ERROR > " + t.toString());
//                loading.dismiss();

            }
        });
    }


}