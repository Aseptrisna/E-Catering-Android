package com.ECatering.e_catering.Menu_Item;

import com.google.gson.annotations.SerializedName;

public class Pesanan_Item {

    @SerializedName("id_pesanan")
    private String id_pesanan;

    @SerializedName("nama_menu")
    private String nama_menu;

    @SerializedName("Total_Harga")
    private String Total_Harga;

    @SerializedName("Status")
    private String Status;



    public void setId_pesanan(String id_pesanan){

        this.id_pesanan = id_pesanan;
    }

    public String getId_pesanan(){
        return id_pesanan;

    }

    public void setNama_menu(String nama_menu){

        this.nama_menu = nama_menu;
    }

    public String getNama_menu(){
        return nama_menu;

    }


    public void setTotal_Harga(String total_Harga){

        this.Total_Harga = Total_Harga;
    }

    public String getTotal_Harga(){
        return Total_Harga;

    }

    public void setStatus(String status){

        this.Status = status;
    }

    public String getStatus(){
        return Status;

    }
    @Override
    public String toString(){
        return
                "Pesanan_Item{" +
                        "id_pesanan= '" + id_pesanan+ '\'' +
                        "nama_menu= '" + nama_menu+ '\'' +
                        ",Total_Harga= '" + Total_Harga+ '\'' +
                        ",Status= '" + Status+ '\'' +
                        "}";
    }
}

