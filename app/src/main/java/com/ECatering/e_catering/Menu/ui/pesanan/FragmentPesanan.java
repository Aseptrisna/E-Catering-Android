package com.ECatering.e_catering.Menu.ui.pesanan;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ECatering.e_catering.Adapter.Adapter_Keranjang;
import com.ECatering.e_catering.Adapter.Adapter_Pesanan;
import com.ECatering.e_catering.Menu.ui.notifications.NotificationsViewModel;
import com.ECatering.e_catering.Menu_Item.Pesanan_Item;
import com.ECatering.e_catering.R;
import com.ECatering.e_catering.Response.Response_Pesanan;
import com.ECatering.e_catering.Server.InitRetrofit;
import com.ECatering.e_catering.Storage.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPesanan  extends Fragment {
    private RecyclerView recyclerView;
    SharedPrefManager sharedPrefManager;
    ImageButton Deleteitem;
    Adapter_Keranjang adapter_keranjang;
    Button Bayar;
    Context context;

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pesanan, container, false);
        context = getActivity();
        sharedPrefManager = new SharedPrefManager(getActivity());
        String Username=(sharedPrefManager.getSPEmail());
//        Deleteitem=(ImageButton)root.findViewById(R.id.delChkoutItemBut);
        recyclerView = (RecyclerView)root.findViewById(R.id.list_pesanan);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        Bayar=(Button)root.findViewById(R.id.Prosesbayar);
//        recyclerView.setHasFixedSize(true);
//        GRID 2 kolom
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
        tampilpesanan();
//
        return root;
    }

    private void tampilpesanan() {

        String Username=(sharedPrefManager.getSPEmail());
        retrofit2.Call<Response_Pesanan> call = InitRetrofit.getInstance().getApi().tampilpesanan(Username);
        call.enqueue(new Callback<Response_Pesanan>() {
            @Override
            public void onResponse(Call<Response_Pesanan> call, Response<Response_Pesanan> response) {
                if (response.isSuccessful()) {
                    Log.d("response api", response.body().toString());
                    List<Pesanan_Item> data_menu = response.body().getPesanan();
                    boolean status = response.body().isStatus();
                    if (status){
                        Adapter_Pesanan adapter = new Adapter_Pesanan(getActivity(), data_menu);
                        recyclerView.setAdapter(adapter);
                    } else {
//                        Bayar.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), "Tidak Ada data Daftar Belanja Saat ini", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<Response_Pesanan> call, Throwable t) {
                Log.v("debug", "onFailure: ERROR > " + t.toString());
//                loading.dismiss();

            }
        });
    }

    }
