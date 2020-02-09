package com.ECatering.e_catering.Menu_Item;

import com.google.gson.annotations.SerializedName;

public class Menu_Item {

    @SerializedName("id")
    private String id;

    @SerializedName("jenis")
    private String jenis;

    @SerializedName("nama")
    private String nama;

    @SerializedName("harga")
    private String harga;

    @SerializedName("foto")
    private String foto;


    @SerializedName("deskripsi")
    private String deskripsi;



    public void setId(String id){

        this.id = id;
    }

    public String getId(){
        return id;
    }



    public void setNama(String nama){

        this.nama = nama;
    }

    public String getNama(){
        return nama;
    }


    public void setHarga(String harga){

        this.harga = harga;
    }

    public String getHarga(){
        return harga;
    }
    public void setFoto(String foto){

        this.foto = foto;
    }

    public String getFoto(){

        return foto;
    }

    public void setDeskripsi(String deskripsi){

        this.deskripsi = deskripsi;
    }

    public String getDeskripsi(){

        return deskripsi;
    }


    public void setJenis(String jenis){

        this.jenis = jenis;
    }

    public String getJenis(){

        return jenis;
    }



    @Override
    public String toString(){
        return
                "Menu_Item{" +
                        "id= '" + id+ '\'' +
                        "nama= '" + nama+ '\'' +
                        ",harga= '" + harga+ '\'' +
                        ",jenis= '" + jenis+ '\'' +
                        ",deskripsi= '" + deskripsi+ '\'' +
                        ",foto= '" + foto+ '\'' +
                        "}";
    }
}
