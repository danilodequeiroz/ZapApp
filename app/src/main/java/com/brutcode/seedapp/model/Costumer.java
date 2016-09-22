
package com.brutcode.seedapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Costumer {

    @SerializedName("CodCliente")
    @Expose
    public int codCostumer;
    @SerializedName("NomeFantasia")
    @Expose
    public String tradeName;

    /**
     * 
     * @return
     *     The codCostumer
     */
    public int getCodCostumer() {
        return codCostumer;
    }

    /**
     * 
     * @param codCostumer
     *     The CodCliente
     */
    public void setCodCostumer(int codCostumer) {
        this.codCostumer = codCostumer;
    }

    /**
     * 
     * @return
     *     The tradeName
     */
    public String getTradeName() {
        return tradeName;
    }

    /**
     * 
     * @param tradeName
     *     The NomeFantasia
     */
    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Costumer costumer = (Costumer) o;

        if (codCostumer != costumer.codCostumer) return false;
        return tradeName != null ? tradeName.equals(costumer.tradeName) : costumer.tradeName == null;

    }

    @Override
    public int hashCode() {
        int result = codCostumer;
        result = 31 * result + (tradeName != null ? tradeName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Costumer{" +
                "codCostumer=" + codCostumer +
                ", tradeName='" + tradeName + '\'' +
                '}';
    }
}
