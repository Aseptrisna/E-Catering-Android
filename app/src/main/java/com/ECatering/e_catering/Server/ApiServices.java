package com.ECatering.e_catering.Server;

import com.ECatering.e_catering.Response.Img_Pojo;
import com.ECatering.e_catering.Response.Response_Menu;
import com.ECatering.e_catering.Response.Response_Pesanan;
import com.ECatering.e_catering.Response.Response_Register;
import com.ECatering.e_catering.Response.Response_keranjang;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiServices {

    @GET("Menu_Catering.php")
    Call<Response_Menu> tampil_semuamenu();

    @GET("Tampil_menu.php?katergori=Nasi Kotak")
    Call<Response_Menu> tampil_menu();

    @GET("Tampil_menu.php?katergori=tumpeng")
    Call<Response_Menu> tampiltumpeng();

    @FormUrlEncoded
    @POST("Tampil_keranjang.php")
    Call<Response_keranjang> tampilkeranjang(
            @Field("username") String Username
    );

    @FormUrlEncoded
    @POST("Delete_Keranjang.php")
    Call<ResponseBody> Deletekeranjang(
            @Field("id") String id
    );


    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseBody> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register.php")
    Call<Response_Register> User(
            @Field("nama")String d_name,
            @Field("email") String d_mail,
            @Field("password") String d_pass
    );

    @FormUrlEncoded
    @POST("Tambah__keranjang.php")
    Call<ResponseBody> Keranjang(
            @Field("username")String d_Username,
            @Field("id_menu") String d_id,
            @Field("nama") String d_nama,
            @Field("harga") String d_harga,
            @Field("foto") String d_foto,
            @Field("Jumlah_menu") String d_Jml

    );

    @FormUrlEncoded
    @POST("Bayar.php")
    Call<ResponseBody> add_pesanan(
            @Field("username")String d_Username,
            @Field("id_menu") String d_id,
            @Field("nama_menu") String d_nama,
            @Field("alamat") String d_alamat,
            @Field("tlp") String d_tlp,
            @Field("tanggal") String d_tanggal,
            @Field("harga") int d_harga,
            @Field("jml") String d_jml

    );



    @GET("NasiKotak.php?katergori=Nasi Kotak")
    Call<Response_Menu> TampilNasiKotak();

    @FormUrlEncoded
    @POST("C.NasiKotak.php")
    Call<Response_Menu> CustomizeKotak(
            @Field("kategori") String d_kategori,
            @Field("jumlah") String d_jml
    );


    @GET("NasiTumpeng.php?kategori=tumpeng")
    Call<Response_Menu> TampiljenisTumpeng();


    @GET("JenisTumpeng.php?kategori=tumpeng")
    Call<Response_Menu>TampilkategoriTumpeng();

    @FormUrlEncoded
    @POST("C.NasiTumpeng.php")
    Call<Response_Menu> CustomizeTumpeng(
            @Field("kategori") String d_kategori,
            @Field("jenis") String d_jenis

    );


    @FormUrlEncoded
    @POST("Tampil_pesanan.php")
    Call<Response_Pesanan> tampilpesanan(
            @Field("username") String Username
    );



    @FormUrlEncoded
    @POST("Update_User.php")
    Call<ResponseBody> userapdet(
            @Field("nama") String nama,
            @Field("alamat") String alamat,
            @Field("telpom") String telpon,
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("BuktiByar.php")
    Call<Img_Pojo> uploadImage(@Field("id_pesanan") String title, @Field("foto") String image);

}
