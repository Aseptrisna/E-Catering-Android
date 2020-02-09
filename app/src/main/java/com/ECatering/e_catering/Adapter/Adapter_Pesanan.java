package com.ECatering.e_catering.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ECatering.e_catering.Menu_Item.Menu_Item;
import com.ECatering.e_catering.Menu_Item.Pesanan_Item;
import com.ECatering.e_catering.R;

import java.util.List;

public class Adapter_Pesanan extends RecyclerView.Adapter<Adapter_Pesanan.MyViewHolder> {
    Context context;
    List<Pesanan_Item> menu;
    String HG;


    public Adapter_Pesanan(Context context, List<Pesanan_Item> data_menu) {
        this.context = context;
        this.menu = data_menu;
    }

    @Override
    public Adapter_Pesanan.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_pesanan, parent, false);
        Adapter_Pesanan.MyViewHolder holder = new Adapter_Pesanan.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Adapter_Pesanan.MyViewHolder holder, final int position) {
        // Set widget
        holder.harga.setText("Rp." + menu.get(position).getTotal_Harga());
        holder.id.setText(menu.get(position).getId_pesanan());
        holder.status.setText(menu.get(position).getStatus());
        holder.nama.setText( menu.get(position).getNama_menu());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
////
//        Intent varIntent = new Intent(context, Detail_Menu.class);
//        varIntent.putExtra("ID", menu.get(position).getId());
//        varIntent.putExtra("NAMA", menu.get(position).getNama());
//        varIntent.putExtra("HARGA", menu.get(position).getHarga());
//        varIntent.putExtra("DESKRIPSI", menu.get(position).getDeskripsi());
//        varIntent.putExtra("GAMBAR_MENU", urlGambar);
//        varIntent.putExtra("GAMBAR", menu.get(position).getFoto());
//        context.startActivity(varIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return menu.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView nama, harga,id,status;

        public MyViewHolder(View itemView) {
            super(itemView);
            nama = (TextView) itemView.findViewById(R.id.namabarang);
              harga = (TextView) itemView.findViewById(R.id.TotalBayar);
            id = (TextView) itemView.findViewById(R.id.IdTransakai);
            status = (TextView) itemView.findViewById(R.id.Status);
        }
    }

}
