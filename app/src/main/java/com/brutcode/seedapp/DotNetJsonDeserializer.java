package com.brutcode.seedapp;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by Danilo on 08/09/2016.
 *  Anulando os parametros que estão
 *  formatados erroneamente ie: "/Date(-62135596800000-0000)/"
 */
public class DotNetJsonDeserializer  implements JsonDeserializer<Date> {

    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Date d = null;
        if(!json.getAsJsonPrimitive().getAsString().contains("(-")){
            String s = json.getAsJsonPrimitive().getAsString();
            long l = Long.parseLong(s.substring(s.indexOf("(")+1, s.indexOf(")")));
            d = new Date(l);
        }
        return d;
    }

}