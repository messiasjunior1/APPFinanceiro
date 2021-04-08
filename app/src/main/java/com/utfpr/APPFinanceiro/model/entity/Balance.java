package com.utfpr.APPFinanceiro.model.entity;

import java.io.Serializable;

public class Balance implements Serializable{

    private Long id;
    private String value;
    private String desc;
    private String date;
    private String typeBalance;


    public Balance(String value, String desc, String date, String typeBalance) {

        this.value = value;
        this.desc = desc;
        this.date = date;
        this.typeBalance = typeBalance;
    }


    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }


    public String getDate() {
        return date;
    }
    public void setDate(String date) { this.desc = date; }

    public String getTypeBalance() {
        return typeBalance;
    }
    public void setTypeBalance(String typeBalance) { this.desc = typeBalance; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }
}
