package com.parser.models;

public class UserAgentModel {
    private String deviceName;
    private String agentNameVersion;
    private String operatingSystemClass;
    private String operatingSystemNameVersion;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getAgentNameVersion() {
        return agentNameVersion;
    }

    public void setAgentNameVersion(String agentNameVersion) {
        this.agentNameVersion = agentNameVersion;
    }

    public String getOperatingSystemClass() {
        return operatingSystemClass;
    }

    public void setOperatingSystemClass(String operatingSystemClass) {
        this.operatingSystemClass = operatingSystemClass;
    }

    public String getOperatingSystemNameVersion() {
        return operatingSystemNameVersion;
    }

    public void setOperatingSystemNameVersion(String operatingSystemNameVersion) {
        this.operatingSystemNameVersion = operatingSystemNameVersion;
    }
}
