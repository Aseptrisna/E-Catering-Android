package com.ECatering.e_catering.Menu.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ECatering.e_catering.Adapter.Adapter_Menu;
import com.ECatering.e_catering.Menu_Item.Menu_Item;
import com.ECatering.e_catering.R;
import com.ECatering.e_catering.Response.Response_Menu;
import com.ECatering.e_catering.Server.ApiServices;
import com.ECatering.e_catering.Server.InitRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    ViewFlipper v_flipper;
    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.list_menu);
        LinearLayoutManager layoutManager= new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        tampilmenu();

//        int images[] = {R.drawable.itemsatu, R.drawable.menudua, R.drawable.menutiga,R.drawable.menuempat,R.drawable.maxresdefault};
//        v_flipper = (ViewFlipper) view.findViewById(R.id.imageslider);
//
//        for (int i =0; i<images.length; i++){
//            fliverImages(images[i]);
//        }
//        for (int image: images)
//            fliverImages(image);



        return view;
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

    public  void tampilmenu(){
        ApiServices api = InitRetrofit.getInstance().getApi();
        Call<Response_Menu> menuCall = api.tampil_menu();
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
}
