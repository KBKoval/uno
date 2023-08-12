package com.parser.services.impl;


import com.parser.models.UserAgentFull;
import com.parser.models.UserAgentModel;
import com.parser.services.interfaces.AnalyzerService;
import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedCaseInsensitiveMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class AnalyzerServiceImpl implements AnalyzerService {
    private static final Logger log = LoggerFactory.getLogger(AnalyzerServiceImpl.class);
    private static final UserAgentAnalyzer userAgentAnalyzer = UserAgentAnalyzer.newBuilder()
            .showMatcherLoadStats()
            .addOptionalResources("file:UserAgents*/*.yaml")
            .addOptionalResources("classpath*:UserAgents-*/*.yaml")
            .immediateInitialization()
            .keepTests()
            .build();

    public void parserTest(String userAgent) {
        UserAgent agent = userAgentAnalyzer.parse(userAgent);
        for (String fieldName : agent.getAvailableFieldNamesSorted()) {
            log.info(fieldName + " = " + agent.getValue(fieldName));
        }
    }

    public UserAgentModel parser(String userAgent) {
        UserAgentModel model = new UserAgentModel();
        UserAgent agent = userAgentAnalyzer.parse(userAgent);
        return createModel(agent);
    }

    public UserAgentFull parserFull(LinkedCaseInsensitiveMap<String> requestHeaders) {
        UserAgent agent = userAgentAnalyzer.parse(requestHeaders);
        UserAgentFull model = new UserAgentFull();


        agent.getAvailableFieldNamesSorted().forEach(fieldName -> {
            if (fieldName.equalsIgnoreCase("DeviceBrand")) model.setBrand(agent.getValue(fieldName));
            if (fieldName.equalsIgnoreCase("DeviceClass")) model.setDeviceClass(agent.getValue(fieldName));
            if (fieldName.equalsIgnoreCase("DeviceName")) model.setName(agent.getValue(fieldName));
        });
        Map<String,String> fullInfo=new TreeMap<>();
        for (String fieldName : agent.getAvailableFieldNamesSorted()) {
            fullInfo.put(fieldName ,agent.getValue(fieldName));
        }
        model.setFullInfo(fullInfo);
        return model;
    }

    public UserAgentModel parser(LinkedCaseInsensitiveMap<String> requestHeaders) {
        UserAgent agent = userAgentAnalyzer.parse(requestHeaders);
        return createModel(agent);
    }
    private UserAgentModel createModel(UserAgent agent) {
        UserAgentModel model = new UserAgentModel();
        log.info("===============================================================================");
        for (String fieldName : agent.getAvailableFieldNamesSorted()) {
            log.info(fieldName + " = " + agent.getValue(fieldName));
        }
        log.info("===============================================================================");

        agent.getAvailableFieldNamesSorted().forEach(fieldName -> {
            if (fieldName.equalsIgnoreCase("DeviceBrand")) model.setBrand(agent.getValue(fieldName));
            if (fieldName.equalsIgnoreCase("DeviceClass")) model.setDeviceClass(agent.getValue(fieldName));
            if (fieldName.equalsIgnoreCase("DeviceName")) model.setName(agent.getValue(fieldName));
        });
        return model;
    }


    public List<String> getSupportedClientHintHeaders() {
        return userAgentAnalyzer.supportedClientHintHeaders();
    }

}
