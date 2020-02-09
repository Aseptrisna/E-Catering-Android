package com.ECatering.e_catering.Response;

import com.ECatering.e_catering.Menu_Item.Keranjang_Item;
import com.ECatering.e_catering.Menu_Item.Pesanan_Item;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response_Pesanan {
    @SerializedName("pesanan")
    private List<Pesanan_Item> pesanan;

    @SerializedName("status")
    private boolean status;

    public void setKeranjang(List<Pesanan_Item> pesanan)
    {
        this.pesanan = pesanan;
    }

    public List<Pesanan_Item> getPesanan()
    {
        return pesanan;
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
                "Response_Pesanan{" +
                        "pesanan = '" + pesanan + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}


