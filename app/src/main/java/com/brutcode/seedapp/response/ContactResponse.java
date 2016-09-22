package com.brutcode.seedapp.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Danilo on 10/09/2016.
 */
public class ContactResponse{
    ContactEnum msg;

    public enum ContactEnum {
        @SerializedName("Hello World.")
        OK
    }

    public ContactEnum getMsg() {
        return msg;
    }
}
