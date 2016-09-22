package com.brutcode.seedapp.model;

/**
 * Created by Danilo on 08/09/2016.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;
@Parcel
public class Realty {

    @SerializedName("CodImovel")
    @Expose
    public int codRealty;

    @SerializedName("TipoImovel")
    @Expose
    public String realtyType;

    @SerializedName("Endereco")
    @Expose
    public RealtyAddress realtyAddress;

    @SerializedName("PrecoVenda")
    @Expose
    public int sellPrice;

    @SerializedName("Dormitorios")
    @Expose
    public int dorms;

    @SerializedName("Suites")
    @Expose
    public int suites;

    @SerializedName("Vagas")
    @Expose
    public int parkingSpaces;

    @SerializedName("AreaUtil")
    @Expose
    public int utilArea;

    @SerializedName("AreaTotal")
    @Expose
    public int totalArea;

//    @SerializedName("DataAtualizacao")
//    @Expose
//    private Date updatedDate;

    @SerializedName("Cliente")
    @Expose
    public Costumer client;

    @SerializedName("Fotos")
    @Expose
    public List<String> images = new ArrayList<String>();

    @SerializedName("SubTipoOferta")
    @Expose
    public String offerSubType;

    @SerializedName("Observacao")
    @Expose
    public String observation;

    @SerializedName("Caracteristicas")
    @Expose
    public List<String> characteristics = new ArrayList<String>();

    @SerializedName("PrecoCondominio")
    @Expose
    public int condominiumFee;

    @SerializedName("SubtipoImovel")
    @Expose
    public String realtySubType;

    @SerializedName("CaracteristicasComum")
    @Expose
    public List<String> commonCharacteristics = new ArrayList<String>();

    @SerializedName("InformacoesComplementares")
    @Expose
    public String additionalInformation;

    ///////////////////// adicional dos detalhes

    @SerializedName("UrlImagem")
    @Expose
    public String imgUrl;

    @SerializedName("StatusQualidadeTotal")
    @Expose
    public boolean statusQualidadeTotal;

    @SerializedName("EstagioObra")
    @Expose
    public String constructProgress;

    @SerializedName("DistanciaKilometros")
    @Expose
    public double distanceKilometers;

    public Realty(){}

    public int getCodRealty() {
        return codRealty;
    }

    public String getRealtyType() {
        return realtyType;
    }

    public RealtyAddress getRealtyAddress() {
        return realtyAddress;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public int getDorms() {
        return dorms;
    }

    public int getSuites() {
        return suites;
    }

    public int getParkingSpaces() {
        return parkingSpaces;
    }

    public int getUtilArea() {
        return utilArea;
    }

    public int getTotalArea() {
        return totalArea;
    }

    public Costumer getClient() {
        return client;
    }

    public List<String> getImages() {
        return images;
    }

    public String getOfferSubType() {
        return offerSubType;
    }

    public String getObservation() {
        return observation;
    }

    public List<String> getCharacteristics() {
        return characteristics;
    }

    public int getCondominiumFee() {
        return condominiumFee;
    }

    public String getRealtySubType() {
        return realtySubType;
    }

    public List<String> getCommonCharacteristics() {
        return commonCharacteristics;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public boolean isStatusQualidadeTotal() {
        return statusQualidadeTotal;
    }

    public String getConstructProgress() {
        return constructProgress;
    }

    public double getDistanceKilometers() {
        return distanceKilometers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Realty realty = (Realty) o;

        if (codRealty != realty.codRealty) return false;
        if (sellPrice != realty.sellPrice) return false;
        if (dorms != realty.dorms) return false;
        if (suites != realty.suites) return false;
        if (parkingSpaces != realty.parkingSpaces) return false;
        if (utilArea != realty.utilArea) return false;
        if (totalArea != realty.totalArea) return false;
        if (condominiumFee != realty.condominiumFee) return false;
        if (statusQualidadeTotal != realty.statusQualidadeTotal) return false;
        if (Double.compare(realty.distanceKilometers, distanceKilometers) != 0) return false;
        if (realtyType != null ? !realtyType.equals(realty.realtyType) : realty.realtyType != null)
            return false;
        if (realtyAddress != null ? !realtyAddress.equals(realty.realtyAddress) : realty.realtyAddress != null)
            return false;
        if (client != null ? !client.equals(realty.client) : realty.client != null) return false;
        if (images != null ? !images.equals(realty.images) : realty.images != null) return false;
        if (offerSubType != null ? !offerSubType.equals(realty.offerSubType) : realty.offerSubType != null)
            return false;
        if (observation != null ? !observation.equals(realty.observation) : realty.observation != null)
            return false;
        if (characteristics != null ? !characteristics.equals(realty.characteristics) : realty.characteristics != null)
            return false;
        if (realtySubType != null ? !realtySubType.equals(realty.realtySubType) : realty.realtySubType != null)
            return false;
        if (commonCharacteristics != null ? !commonCharacteristics.equals(realty.commonCharacteristics) : realty.commonCharacteristics != null)
            return false;
        if (additionalInformation != null ? !additionalInformation.equals(realty.additionalInformation) : realty.additionalInformation != null)
            return false;
        if (imgUrl != null ? !imgUrl.equals(realty.imgUrl) : realty.imgUrl != null) return false;
        return constructProgress != null ? constructProgress.equals(realty.constructProgress) : realty.constructProgress == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = codRealty;
        result = 31 * result + (realtyType != null ? realtyType.hashCode() : 0);
        result = 31 * result + (realtyAddress != null ? realtyAddress.hashCode() : 0);
        result = 31 * result + sellPrice;
        result = 31 * result + dorms;
        result = 31 * result + suites;
        result = 31 * result + parkingSpaces;
        result = 31 * result + utilArea;
        result = 31 * result + totalArea;
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (images != null ? images.hashCode() : 0);
        result = 31 * result + (offerSubType != null ? offerSubType.hashCode() : 0);
        result = 31 * result + (observation != null ? observation.hashCode() : 0);
        result = 31 * result + (characteristics != null ? characteristics.hashCode() : 0);
        result = 31 * result + condominiumFee;
        result = 31 * result + (realtySubType != null ? realtySubType.hashCode() : 0);
        result = 31 * result + (commonCharacteristics != null ? commonCharacteristics.hashCode() : 0);
        result = 31 * result + (additionalInformation != null ? additionalInformation.hashCode() : 0);
        result = 31 * result + (imgUrl != null ? imgUrl.hashCode() : 0);
        result = 31 * result + (statusQualidadeTotal ? 1 : 0);
        result = 31 * result + (constructProgress != null ? constructProgress.hashCode() : 0);
        temp = Double.doubleToLongBits(distanceKilometers);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Realty{" +
                "codRealty=" + codRealty +
                ", realtyType='" + realtyType + '\'' +
                ", realtyAddress=" + realtyAddress +
                ", sellPrice=" + sellPrice +
                ", dorms=" + dorms +
                ", suites=" + suites +
                ", parkingSpaces=" + parkingSpaces +
                ", utilArea=" + utilArea +
                ", totalArea=" + totalArea +
                ", client=" + client +
                ", images=" + images +
                ", offerSubType='" + offerSubType + '\'' +
                ", observation='" + observation + '\'' +
                ", characteristics=" + characteristics +
                ", condominiumFee=" + condominiumFee +
                ", realtySubType='" + realtySubType + '\'' +
                ", commonCharacteristics=" + commonCharacteristics +
                ", additionalInformation='" + additionalInformation + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", statusQualidadeTotal=" + statusQualidadeTotal +
                ", constructProgress='" + constructProgress + '\'' +
                ", distanceKilometers=" + distanceKilometers +
                '}';
    }
}