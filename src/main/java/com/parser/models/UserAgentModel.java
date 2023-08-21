package com.parser.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserAgentModel {
    @JsonProperty("MacAdress")
    private String macAdress;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Class")
    private String deviceClass;
    @JsonProperty("Brand")
    private String brand;

    public String getMacAdress() {
        return macAdress;
    }

    public void setMacAdress(String macAdress) {
        this.macAdress = macAdress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceClass() {
        return deviceClass;
    }

    public void setDeviceClass(String deviceClass) {
        this.deviceClass = deviceClass;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
