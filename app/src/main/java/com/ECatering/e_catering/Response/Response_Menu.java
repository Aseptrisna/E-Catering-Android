package com.ECatering.e_catering.Response;

import com.ECatering.e_catering.Menu_Item.Menu_Item;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response_Menu {
    @SerializedName("menu")
    private List<Menu_Item> menu;

    @SerializedName("status")
    private boolean status;

    public void setMenu(List<Menu_Item> menu) {
        this.menu = menu;
    }

    public List<Menu_Item> getMenu()
    {
        return menu;
    }

    public void setStatus(boolean status){

        this.status = status;
    }

    public boolean isStatus(){

        return status;
    }

    @Override
    public String toString(){
        return
                "Response_Menu{" +
                        "menu = '" + menu+ '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }

}
