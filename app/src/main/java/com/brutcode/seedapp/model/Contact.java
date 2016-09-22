package com.brutcode.seedapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Danilo on 10/09/2016.
 */
public class Contact {


    public Contact(String noun, String mail, String phone, String s) {
        this.noun = noun;
        this.mail = mail;
        this.phone = phone;
        this.comment = s;
    }

    @SerializedName("name")
    public String noun;
    public String mail;
    public String phone;
    private String comment;
}
