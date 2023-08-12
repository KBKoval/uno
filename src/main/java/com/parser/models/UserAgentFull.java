package com.parser.models;

import java.util.Map;

public class UserAgentFull extends UserAgentModel{
    private Map<String,String> fullInfo;

    public Map<String, String> getFullInfo() {
        return fullInfo;
    }

    public void setFullInfo(Map<String, String> fullInfo) {
        this.fullInfo = fullInfo;
    }
}
