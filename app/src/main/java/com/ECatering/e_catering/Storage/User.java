package com.ECatering.e_catering.Storage;

public class User {

    private String username, password, email, nama_lengkap, no_telp, alamat_rumah;

    public User(String email, String nama_lengkap, String username, String password) {

        this.email = this.email;
        this.nama_lengkap = this.nama_lengkap;
        this.no_telp=no_telp;
        this.alamat_rumah=alamat_rumah;
        this.password =password;
        this.username = username;

    }

    public String getEmail() {
        return email;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public String getAlamat_rumah() {
        return alamat_rumah;
    }



    public String getUsername() {
        return username;
    }



    public String getPassword() {
        return password;
    }


}
