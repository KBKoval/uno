package com.parser.services.impl;


import com.parser.models.UserAgentModel;
import com.parser.services.interfaces.AnalyzerService;
import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AnalyzerServiceImpl implements AnalyzerService {
    private static final transient Logger log = LoggerFactory.getLogger(AnalyzerService.class);
    public void parserTest(String userAgent){
        UserAgentAnalyzer uaa = UserAgentAnalyzer
                .newBuilder()
                .hideMatcherLoadStats()
                .withCache(10000)
                .build();

        UserAgent agent = uaa.parse(userAgent);

        for (String fieldName: agent.getAvailableFieldNamesSorted()) {
            log.info(fieldName + " = " + agent.getValue(fieldName));
        }
    }

    public UserAgentModel  parser(String userAgent){
        UserAgentModel model = new UserAgentModel();
        UserAgentAnalyzer uaa = UserAgentAnalyzer
                .newBuilder()
                .hideMatcherLoadStats()
                .withCache(10000)
                .build();

        UserAgent agent = uaa.parse(userAgent);
        for (String fieldName: agent.getAvailableFieldNamesSorted()) {
            log.info(fieldName + " = " + agent.getValue(fieldName));
        }
        agent.getAvailableFieldNamesSorted().forEach(fieldName -> {
            if(fieldName.equalsIgnoreCase("DeviceBrand")) model.setBrand(agent.getValue(fieldName));
            if(fieldName.equalsIgnoreCase("DeviceClass")) model.setDeviceClass(agent.getValue(fieldName));
            if(fieldName.equalsIgnoreCase("DeviceName")) model.setName(agent.getValue(fieldName));
        });
        return model;
    }
}
