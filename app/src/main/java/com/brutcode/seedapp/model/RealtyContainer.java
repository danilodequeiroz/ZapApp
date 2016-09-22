
package com.brutcode.seedapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RealtyContainer {

    @SerializedName("Imoveis")
    @Expose
    private List<Realty> realtyItemList = new ArrayList<Realty>();

    @SerializedName("Imovel")
    @Expose
    private Realty mRealty;


    public Realty getmRealty() {
        return mRealty;
    }

    public void setmRealty(Realty mRealty) {
        this.mRealty = mRealty;
    }

    /**
     * 
     * @return
     *     The realtyItemList
     */
    public List<Realty> getRealtyItemList() {
        return realtyItemList;
    }

    /**
     * 
     * @param realtyItemList
     *     The Imoveis
     */
    public void setRealtyItemList(List<Realty> realtyItemList) {
        this.realtyItemList = realtyItemList;
    }

}
