package com.brutcode.seedapp;

import android.text.Html;
import android.text.Spanned;

import com.brutcode.seedapp.model.RealtyAddress;

/**
 * Created by Danilo on 10/09/2016.
 */
public class Util {
    public static Spanned fromHtml(String source) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
            // noinspection deprecation
            return Html.fromHtml(source);
        }
        return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
    }

    public static String addressFormatted(RealtyAddress realtyAddress){
        if(realtyAddress == null)
            return "";
        String address = new StringBuilder()
                .append(realtyAddress.getLogradouro() != null ? realtyAddress.getLogradouro() + " - " : "")
                .append(realtyAddress.getBairro())
                .append(realtyAddress.getLogradouro() == null ? " - " + realtyAddress.getZona() : "")
                .toString();
        return address;
    }

    public static String fullAddressFormatted(RealtyAddress realtyAddress){
        if(realtyAddress == null)
            return "";
        String address = new StringBuilder()
                .append(realtyAddress.getLogradouro() != null ? realtyAddress.getLogradouro() + ", " : "")
                .append(realtyAddress.getBairro())
                .append(realtyAddress.getLogradouro() == null ? " - " + realtyAddress.getZona() : "")
                .append(", ")
                .append(realtyAddress.getCidade())
                .append(", ")
                .append(realtyAddress.getEstado())
                .append((realtyAddress.getCEP() == null || realtyAddress.getCEP().equals("")) ?
                        "" : ", "+realtyAddress.getEstado())
                .toString();
        return address;
    }
}
