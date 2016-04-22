package com.will.zengzewill.mobilecodeinfo.model;

import org.json.JSONObject;

/**
 * Created by ze on 16/4/11.
 */
public class InfoModel {
    public String phone;
    public String province;
    public String city;
    public String prefix;
    public String supplier;
    public String suit;
    public String jsonString;


    public InfoModel(){

    }

    public InfoModel(JSONObject object){

        try {
            JSONObject data = object.getJSONObject("retData");
            this.jsonString = data.toString();
            this.phone = data.getString("phone");
            this.prefix = data.getString("prefix");
            this.supplier = data.getString("supplier");
            this.province = data.getString("province");
            this.city = data.getString("city");
            this.suit = data.getString("suit");
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }


}
