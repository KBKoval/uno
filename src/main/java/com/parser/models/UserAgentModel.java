package com.parser.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserAgentModel {
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Class")
    private String deviceClass;
    @JsonProperty("Brand")
    private String brand;

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
