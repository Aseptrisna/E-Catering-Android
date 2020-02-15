package com.ECatering.e_catering.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.ECatering.e_catering.Menu.Dashboard;
import com.ECatering.e_catering.Menu.Login;
import com.ECatering.e_catering.Menu.Pembayaran;
import com.ECatering.e_catering.Menu_Item.Keranjang_Item;
import com.ECatering.e_catering.R;
import com.ECatering.e_catering.Response.Response_keranjang;
import com.ECatering.e_catering.Server.InitRetrofit;
import com.squareup.picasso.Picasso;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_Keranjang extends RecyclerView.Adapter<Adapter_Keranjang.MyViewHolder> {
    Context context;
    List<Keranjang_Item> keranjang;
    String ID, NM, HG, JL;
    ProgressDialog loading;


    public Adapter_Keranjang(Context context, List<Keranjang_Item> data_keranjang) {
        this.context = context;
        this.keranjang = data_keranjang;
    }

    @Override
    public Adapter_Keranjang.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_keranjang, parent, false);
        Adapter_Keranjang.MyViewHolder holder = new Adapter_Keranjang.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Adapter_Keranjang.MyViewHolder holder, final int position) {
        // Set widget
//        String id=(keranjang.get(position).getId());
        holder.nama_makanan.setText(keranjang.get(position).getNama_menu());
        holder.harga.setText("Rp." + keranjang.get(position).getPerkiraan_harga());
        holder.Jumlah_pesanan.setText(keranjang.get(position).getJumlah_menu());
        final String urlGambarmakanan = InitRetrofit.BASE_URL + "../Images/" + keranjang.get(position).getFoto_makanan();
        Picasso.with(context).load(urlGambarmakanan).into(holder.gambar);
        ID = keranjang.get(position).getId();
        NM = keranjang.get(position).getNama_menu();
        HG = keranjang.get(position).getPerkiraan_harga();
        JL = keranjang.get(position).getJumlah_menu();



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent varIntent = new Intent(context, Pembayaran.class);
//
//                  Toast.makeText(context, "" + ID, Toast.LENGTH_SHORT).show();
//                Toast.makeText(context, ""+ID, Toast.LENGTH_SHORT).show();
                varIntent.putExtra("ID", keranjang.get(position).getId());
                varIntent.putExtra("NAMA", keranjang.get(position).getNama_menu());
                varIntent.putExtra("HARGA", keranjang.get(position).getPerkiraan_harga());
                varIntent.putExtra("JML", keranjang.get(position).getJumlah_menu());
                varIntent.putExtra("HARGASATUAN", keranjang.get(position).getHarga_satuan());
//                varIntent.putExtra("DESKRIPSI", menu.get(position).getDeskripsi());
//                varIntent.putExtra("GAMBAR_MENU", urlGambar);
//                varIntent.putExtra("GAMBAR", keranjang.get(position).get());
                context.startActivity(varIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return keranjang.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView gambar;
        TextView nama_makanan, harga, Jumlah_pesanan;
        CheckBox pilihbelanjaan;
        ImageButton delete;
        Button bayar;

        public MyViewHolder(View itemView) {
            super(itemView);
            Jumlah_pesanan = (TextView) itemView.findViewById(R.id.Jml_Order);
            gambar = (ImageView) itemView.findViewById(R.id.CheckoutItemImg);
            nama_makanan = (TextView) itemView.findViewById(R.id.checkoutShopName);
            harga = (TextView) itemView.findViewById(R.id.checkoutTxtTitle);
            delete = (ImageButton) itemView.findViewById(R.id.delChkoutItemBut);
//            pilihbelanjaan = (CheckBox) itemView.findViewById(R.id.checkOutChkBox);
//            bayar = (Button) itemView.findViewById(R.id.Prosesbayar);

//            pilihbelanjaan.isChecked();
//            Toast.makeText(context, "Anda Memilih"+NM, Toast.LENGTH_SHORT).show();



//            if (pilihbelanjaan.isChecked()){
////                ID= keranjang.get(position).getId();
////                checout();
//                Toast.makeText(context, "" + ID, Toast.LENGTH_SHORT).show();
//                Toast.makeText(context, "Anda Memilih"+ID, Toast.LENGTH_SHORT).show();
//            }
//            pilihbelanjaan.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    checout();
//                }
//            });


            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                String ID= keranjang.get(position).getId();
                    loading = ProgressDialog.show(context, "Loading.....", null, true, true);
                    Toast.makeText(context, "" + ID, Toast.LENGTH_SHORT).show();
                    Delete();
                }
            });

//            bayar.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {

//                    if (pilihbelanjaan.isChecked()){
////                ID= keranjang.get(position).getId();
////                checout();
//                        Toast.makeText(context, "" + ID, Toast.LENGTH_SHORT).show();
//                        Toast.makeText(context, "Anda Memilih"+ID, Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//            });
        }


    }

    private void checout(Adapter_Keranjang.MyViewHolder holder, final int position) {
//       CheckBox pilihbelanjaan = (CheckBox) itemView.findViewById(R.id.checkOutChkBox);
//        String ID= keranjang.get(position).getId();
//        Toast.makeText(context, "" + ID, Toast.LENGTH_SHORT).show();
//        if (pilihbelanjaan.isChecked()){
////                ID= keranjang.get(position).getId();
////                checout();
//            Toast.makeText(context, "" + ID, Toast.LENGTH_SHORT).show();
//            Toast.makeText(context, "Anda Memilih"+NM, Toast.LENGTH_SHORT).show();
//        }
    }

    private void Delete() {
        String id = ID;
        retrofit2.Call<ResponseBody> call = InitRetrofit.getInstance().getApi().Deletekeranjang(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    loading.dismiss();
                    Log.d("response api", response.body().toString());
                    Toast.makeText(context, "Daftar Belanja Berhasil Di hapus", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, Dashboard.class);
                    context.startActivity(intent);
                } else {
                    loading.dismiss();
                    Toast.makeText(context, "Data Tidak Berhasil Di hapus", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v("debug", "onFailure: ERROR > " + t.toString());

            }
        });
    }




}