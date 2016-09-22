
package com.brutcode.seedapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class RealtyAddress {

    @SerializedName("Logradouro")
    @Expose
    public String logradouro;

    @SerializedName("Numero")
    @Expose
    public String numero;

    @SerializedName("Complemento")
    @Expose
    public String complemento;

    @SerializedName("CEP")
    @Expose
    public String cEP;

    @SerializedName("Bairro")
    @Expose
    public String bairro;

    @SerializedName("Cidade")
    @Expose
    public String cidade;

    @SerializedName("Estado")
    @Expose
    public String estado;

    @SerializedName("Zona")
    @Expose
    public String zona;

    /**
     * 
     * @return
     *     The logradouro
     */
    public String getLogradouro() {
        return logradouro;
    }

    /**
     * 
     * @param logradouro
     *     The Logradouro
     */
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    /**
     * 
     * @return
     *     The numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * 
     * @param numero
     *     The Numero
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * 
     * @return
     *     The complemento
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * 
     * @param complemento
     *     The Complemento
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    /**
     * 
     * @return
     *     The cEP
     */
    public String getCEP() {
        return cEP;
    }

    /**
     * 
     * @param cEP
     *     The CEP
     */
    public void setCEP(String cEP) {
        this.cEP = cEP;
    }

    /**
     * 
     * @return
     *     The bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * 
     * @param bairro
     *     The Bairro
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * 
     * @return
     *     The cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * 
     * @param cidade
     *     The Cidade
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * 
     * @return
     *     The estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * 
     * @param estado
     *     The Estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * 
     * @return
     *     The zona
     */
    public String getZona() {
        return zona;
    }

    /**
     * 
     * @param zona
     *     The Zona
     */
    public void setZona(String zona) {
        this.zona = zona;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RealtyAddress that = (RealtyAddress) o;

        if (logradouro != null ? !logradouro.equals(that.logradouro) : that.logradouro != null)
            return false;
        if (numero != null ? !numero.equals(that.numero) : that.numero != null) return false;
        if (complemento != null ? !complemento.equals(that.complemento) : that.complemento != null)
            return false;
        if (cEP != null ? !cEP.equals(that.cEP) : that.cEP != null) return false;
        if (bairro != null ? !bairro.equals(that.bairro) : that.bairro != null) return false;
        if (cidade != null ? !cidade.equals(that.cidade) : that.cidade != null) return false;
        if (estado != null ? !estado.equals(that.estado) : that.estado != null) return false;
        return zona != null ? zona.equals(that.zona) : that.zona == null;

    }

    @Override
    public int hashCode() {
        int result = logradouro != null ? logradouro.hashCode() : 0;
        result = 31 * result + (numero != null ? numero.hashCode() : 0);
        result = 31 * result + (complemento != null ? complemento.hashCode() : 0);
        result = 31 * result + (cEP != null ? cEP.hashCode() : 0);
        result = 31 * result + (bairro != null ? bairro.hashCode() : 0);
        result = 31 * result + (cidade != null ? cidade.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        result = 31 * result + (zona != null ? zona.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RealtyAddress{" +
                "logradouro='" + logradouro + '\'' +
                ", numero='" + numero + '\'' +
                ", complemento='" + complemento + '\'' +
                ", cEP='" + cEP + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", zona='" + zona + '\'' +
                '}';
    }
}
