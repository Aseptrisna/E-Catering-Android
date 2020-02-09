package com.ECatering.e_catering.Menu_Item;

import com.google.gson.annotations.SerializedName;

public class Keranjang_Item {

    @SerializedName("id")
    private String id;

    @SerializedName("Nama_menu")
    private String nama_menu;

    @SerializedName("perkiraan_harga")
    private String perkiraan_harga;

    @SerializedName("foto")
    private String foto_makanan;

    @SerializedName("Jumlah_menu")
    private String Jumlah_menu;


    @SerializedName("harga_satuan")
    private String harga_satuan;


    public void setId(String id){

        this.id = id;
    }

    public String getId(){
        return id;

    }

    public void setNama_menu(String nama_menu){

        this.nama_menu = nama_menu;
    }

    public String getNama_menu(){
        return nama_menu;

    }

    public void setPerkiraan_harga(String perkiraan_harga){

        this.perkiraan_harga = perkiraan_harga;
    }

    public String getPerkiraan_harga(){
        return perkiraan_harga;
    }

    public void setFoto_makanan(String foto_makanan){

        this.foto_makanan = foto_makanan;
    }

    public String getFoto_makanan(){

        return foto_makanan;
    }

    public void setJumlah_menu(String Jumlah_menu){

        this. Jumlah_menu= Jumlah_menu;
    }

    public String getJumlah_menu(){

        return Jumlah_menu;
    }

    public void setHarga_satuan(String harga_satuan){

        this. harga_satuan= harga_satuan;
    }

    public String getHarga_satuan(){

        return harga_satuan;
    }
    @Override
    public String toString(){
        return
                "Keranjang_Item{" +
                        "id= '" + id+ '\'' +
                        "nama_menu= '" + nama_menu+ '\'' +
                        ",perkiraan_harga= '" + perkiraan_harga+ '\'' +
                        ",foto_makanan= '" + foto_makanan+ '\'' +
                        ",Jumlah_menu= '" + Jumlah_menu+ '\'' +
                        ",harga_satuan= '" + harga_satuan+ '\'' +
                        "}";
    }
}


