package com.ECatering.e_catering.Response;

import com.ECatering.e_catering.Menu_Item.Keranjang_Item;
import com.ECatering.e_catering.Menu_Item.Menu_Item;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response_keranjang {
    @SerializedName("keranjang")
    private List<Keranjang_Item> keranjang;

    @SerializedName("status")
    private boolean status;

    public void setKeranjang(List<Keranjang_Item> keranjang) {
        this.keranjang = keranjang;
    }

    public List<Keranjang_Item> getKeranjang() {
        return keranjang;
    }

    public void setStatus(boolean status) {

        this.status = status;
    }

    public boolean isStatus() {

        return status;
    }

    @Override
    public String toString() {
        return
                "Response_keranjang{" +
                        "keranjang = '" + keranjang + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}
