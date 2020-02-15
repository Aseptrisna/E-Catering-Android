package com.ECatering.e_catering.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ECatering.e_catering.Menu.Detail_Menu;
import com.ECatering.e_catering.Menu_Item.Menu_Item;
import com.ECatering.e_catering.R;
import com.ECatering.e_catering.Server.InitRetrofit;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_Custome extends RecyclerView.Adapter<Adapter_Custome.MyViewHolder> {
    Context context;
    List<Menu_Item> menu;
    String HG;




    public Adapter_Custome(Context context, List<Menu_Item> data_menu) {
        this.context = context;
        this.menu= data_menu;
    }

    @Override
    public Adapter_Custome.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listcustome, parent, false);
        Adapter_Custome.MyViewHolder holder = new Adapter_Custome.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Adapter_Custome.MyViewHolder holder, final int position) {
        // Set widget
//        holder.nama.setText(menu.get(position).getNama());
        holder.harga.setText("Rp"+menu.get(position).getHarga());
//        int idrsatuan = Integer.parseInt(menu.get(position).getNama());
        String ID = menu.get(position).getId();
        String  NM = menu.get(position).getNama();
         HG = menu.get(position).getHarga();
//        JL = menu.get(position).getJumlah_menu();
//        int jml=Jumlah;
//        holder.harga.setText(idrsatuan*jumlah);
        final String urlGambar = InitRetrofit.BASE_URL+"../Images/" + menu.get(position).getFoto();
        Picasso.with(context).load(urlGambar).into(holder.gambarmenu);
        holder.addbelanja.setOnClickListener(new View.OnClickListener() {
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
        Button addbelanja;
        EditText Jumlah;
        public MyViewHolder(View itemView) {
            super(itemView);
            gambarmenu = (ImageView) itemView.findViewById(R.id.gambar);
//            nama = (TextView) itemView.findViewById(R.id.nama_menu);
            harga = (TextView) itemView.findViewById(R.id.idr);
            addbelanja = (Button) itemView.findViewById(R.id.Keranjang);
            Jumlah=(EditText) itemView.findViewById(R.id.mJumlahorang);

//            int idrsatuan = Integer.parseInt(HG);
            String jmlbarang =String.valueOf(Jumlah);
            String d_harga="6666";
            harga.setText(jmlbarang);

        }
    }


}