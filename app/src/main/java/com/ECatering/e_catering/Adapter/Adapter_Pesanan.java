package com.ECatering.e_catering.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.ECatering.e_catering.MainActivity;
import com.ECatering.e_catering.Menu_Item.Menu_Item;
import com.ECatering.e_catering.Menu_Item.Pesanan_Item;
import com.ECatering.e_catering.R;
import com.ECatering.e_catering.Response.Img_Pojo;
import com.ECatering.e_catering.Server.ApiServices;
import com.ECatering.e_catering.Server.InitRetrofit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.pm.PackageManager.*;
import static androidx.core.app.ActivityCompat.startActivityForResult;
import static androidx.media.MediaBrowserServiceCompat.RESULT_OK;

public class Adapter_Pesanan extends RecyclerView.Adapter<Adapter_Pesanan.MyViewHolder> {
    Context context;
    List<Pesanan_Item> menu;
    String HG;


    Button GetImageFromGalleryButton, UploadImageOnServerButton;
    ImageView ShowSelectedImage;
    EditText imageName;
    ImageView img1;
    Button btn1, btn2;

    final int kodeGallery = 100, kodeKamera = 99;
    Uri imageUri;

    String ID, HARGA;

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
        holder.nama.setText(menu.get(position).getNama_menu());
        ID = menu.get(position).getId_pesanan();
        HARGA = menu.get(position).getTotal_Harga();
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

    public class MyViewHolder extends RecyclerView.ViewHolder  {


        TextView nama, harga, id, status;

        public MyViewHolder(View itemView) {
            super(itemView);
            nama = (TextView) itemView.findViewById(R.id.namabarang);
            harga = (TextView) itemView.findViewById(R.id.TotalBayar);
            id = (TextView) itemView.findViewById(R.id.IdTransakai);
            status = (TextView) itemView.findViewById(R.id.Status);
//            GetImageFromGalleryButton = (Button) itemView.findViewById(R.id.pilihfoto);
            UploadImageOnServerButton = (Button) itemView.findViewById(R.id.tombol2);
//            ShowSelectedImage = (ImageView) itemView.findViewById(R.id.foto);


            UploadImageOnServerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String Mandiri="18360071676154";
                    String Bri    ="02940676650";
                    Intent kirimWA = new Intent(Intent.ACTION_SEND);
                    Intent intent = kirimWA.setType("text/plain");
                    String semuapesan=
                            "\n" +
                                    "ID : " + ID+
                                    "\n" +
                                    "Total :Rp. " +
                                    HARGA+ "\n" +
                                    "Rek.Mandiri:"+ Mandiri
                                    +  "\n" + "Rek.BCA: " + Bri
                                    + "\n" + "Atas Nama:An.Aisyah";
                    String url = "https://api.whatsapp.com/send?phone=+6282178766618"+"&"+"text="+semuapesan;
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    context.startActivity(i);
//                    kirim();
                }
            });


//
        }

    }

//    private void kirim() {
//
////        String pesan= d_namauser.toString();
////                int   pesan3 =d_harga;
////                ResponseBody pesan2 = df;
//                String Mandiri="18360071676154";
//                String Bri    ="7464564652242";
//                Intent kirimWA = new Intent(Intent.ACTION_SEND);
//                Intent intent = kirimWA.setType("text/plain");
//                String semuapesan=
//                         "\n" +
//                        "ID : " + ID+
//                        "\n" +
//                        "Total :Rp. " +
//                        HARGA+ "\n" +
//                        "Rek.Mandiri:"+ Mandiri
//                        +  "\n" + "Rek.BCA: " + Bri
//                        + "\n" + "Atas Nama:An.Aisyah";
//
//                kirimWA.putExtra(Intent.EXTRA_TEXT, semuapesan);
////                String kirim="https://api.whatsapp.com/send?phone=628981234567&"+semuapesan;
//                kirimWA.putExtra("jid", "@s.whatsapp.net"+"628981234567");
//                kirimWA.setPackage("com.whatsapp");
//                context.startActivity(kirimWA);
////               finish();
    }

//}

