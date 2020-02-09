package com.ECatering.e_catering.Menu.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.ECatering.e_catering.Adapter.Adapter_Menu;
import com.ECatering.e_catering.Custommize.Tumpeng;
import com.ECatering.e_catering.MainActivity;
import com.ECatering.e_catering.Menu.Menu_Utama;
import com.ECatering.e_catering.Menu_Item.Menu_Item;
import com.ECatering.e_catering.R;
import com.ECatering.e_catering.Response.Response_Menu;
import com.ECatering.e_catering.Server.ApiServices;
import com.ECatering.e_catering.Server.InitRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.ECatering.e_catering.Adapter.Adapter_Menu;

public class DashboardFragment extends Fragment {
    ViewFlipper v_flipper;
    GridLayoutManager llm;
    private RecyclerView recyclerView;
    LinearLayout tampilanmenu,tampilanbuttonmenu;
    ImageView Nasikotak,Tumpeng,CustomTumpeng,CustomNastak,allmenu;
    TextView kemablikedashboard;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.list_menuutama);
        recyclerView.setHasFixedSize(true);
//        LinearLayoutManager layoutManager= new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
//        llm.setOrientation(LinearLayoutManager.VERTICAL);
//
//        //GRID 2 kolom
         GridLayoutManager llm=new GridLayoutManager(getActivity(),2);
//
//        //STAGGER 4 KOLOM
//         StaggeredGridLayoutManager llm=new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        tampilanbuttonmenu=(LinearLayout)view.findViewById(R.id.two);
        tampilanmenu=(LinearLayout)view.findViewById(R.id.lineartampildata);
        tampilanmenu.setVisibility(View.GONE);
        recyclerView.setLayoutManager(llm);
        Nasikotak=(ImageView)view.findViewById(R.id.nasikotak);
        Nasikotak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Menu_Utama.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        Tumpeng=(ImageView)view.findViewById(R.id.tumpeng);
        Tumpeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        CustomTumpeng=(ImageView)view.findViewById(R.id.c_tumpeng);
        CustomTumpeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), com.ECatering.e_catering.Custommize.Tumpeng.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        CustomNastak=(ImageView)view.findViewById(R.id.c_nasikotak);
        CustomNastak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), com.ECatering.e_catering.Custommize.Nasi_Kotak.class);
                startActivity(intent);
                getActivity().finish();

            }
        });
        allmenu=(ImageView)view.findViewById(R.id.semuamenu);
        allmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tampilanbuttonmenu.setVisibility(View.GONE);
                tampilanmenu.setVisibility(View.VISIBLE);
                tampilmenu();

            }
        });

        kemablikedashboard=(TextView)view.findViewById(R.id.kembalikedashboard);
        kemablikedashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tampilanbuttonmenu.setVisibility(View.VISIBLE);
                tampilanmenu.setVisibility(View.GONE);

            }
        });



        int images[] = {R.drawable.menuharian, R.drawable.paketramadhan,R.drawable.logonya};
        v_flipper = (ViewFlipper) view.findViewById(R.id.imageslider);

        for (int i =0; i<images.length; i++){
            fliverImages(images[i]);
        }
        for (int image: images)
            fliverImages(image);
        return view;
    }

    private void tampilmenu() {

        ApiServices api = InitRetrofit.getInstance().getApi();
        Call<Response_Menu> menuCall = api.tampil_semuamenu();
        menuCall.enqueue(new Callback<Response_Menu>() {
            @Override
            public void onResponse(Call<Response_Menu> call, Response<Response_Menu> response) {
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    List<Menu_Item> data_menu= response.body().getMenu();
                    boolean status = response.body().isStatus();
                    if (status){
                        Adapter_Menu adapter = new Adapter_Menu(getActivity(), data_menu);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(getActivity(), "Tidak Ada data Menu saat ini", Toast.LENGTH_SHORT).show();
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


    public  void  fliverImages(int images){
        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(images);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(getActivity(),android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(getActivity(),android.R.anim.slide_out_right);

    }

}
