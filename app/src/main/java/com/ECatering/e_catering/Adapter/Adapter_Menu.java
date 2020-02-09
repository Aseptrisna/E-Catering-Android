package com.ECatering.e_catering.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ECatering.e_catering.Menu.Detail_Menu;
import com.ECatering.e_catering.Menu_Item.Menu_Item;
import com.ECatering.e_catering.R;
import com.ECatering.e_catering.Server.InitRetrofit;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_Menu extends RecyclerView.Adapter<Adapter_Menu.MyViewHolder> {
    Context context;
    List<Menu_Item> menu;



    public Adapter_Menu(Context context, List<Menu_Item> data_menu) {
        this.context = context;
        this.menu= data_menu;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.test_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // Set widget
        holder.nama.setText(menu.get(position).getNama());
        holder.harga.setText("Rp"+menu.get(position).getHarga());
        final String urlGambar = InitRetrofit.BASE_URL+"/Images/" + menu.get(position).getFoto();
        Picasso.with(context).load(urlGambar).into(holder.gambarmenu);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                Intent varIntent = new Intent(context, Detail_Menu.class);
                varIntent.putExtra("ID", menu.get(position).getId());
                varIntent.putExtra("NAMA", menu.get(position).getNama());
                varIntent.putExtra("HARGA", menu.get(position).getHarga());
                varIntent.putExtra("DESKRIPSI", menu.get(position).getDeskripsi());
                varIntent.putExtra("GAMBAR_MENU", urlGambar);
                varIntent.putExtra("GAMBAR", menu.get(position).getFoto());
                context.startActivity(varIntent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return menu.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView gambarmenu;
        TextView nama,harga;
        public MyViewHolder(View itemView) {
            super(itemView);
            gambarmenu = (ImageView) itemView.findViewById(R.id.gambarmenu);
            nama = (TextView) itemView.findViewById(R.id.nama_menu);
            harga = (TextView) itemView.findViewById(R.id.harga_menu);

        }
    }

   
}
