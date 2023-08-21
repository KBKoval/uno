package com.parser.services.impl;


import com.parser.models.RequestModel;
import com.parser.models.UserAgentModel;
import com.parser.services.interfaces.AnalyzerService;
import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedCaseInsensitiveMap;

@Component
@Scope("prototype")
public class ParserThreadImpl implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(ParserThreadImpl.class);
    private static final String FORMAT = "Header '%-20s' = %s";
    private String userAgent;
    private RequestModel headers;
    private final SenderTask senderTask;
    private final AnalyzerService analyzer;

    @Autowired
    public ParserThreadImpl(AnalyzerService analyzer,SenderTask senderTask) {
        this.analyzer = analyzer;
        this.senderTask = senderTask;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public void setHeaders(RequestModel headers) {
        this.headers = headers;
    }

    public String getName(){
        return Thread.currentThread().getName();
}
    public void run() {
        headers.getHeaders().forEach((key,value)->log.info(String.format(FORMAT,key,value)));
        LinkedCaseInsensitiveMap<String> requestHeaders = new LinkedCaseInsensitiveMap<>();
        requestHeaders.putAll(headers.getHeaders());

        UserAgentModel userDates = analyzer.parser(requestHeaders);
        userDates.setMacAdress(headers.getMacAdress());

        System.out.println("MyRunnable running" + Thread.currentThread().getName());
        System.out.println(">> message = " + senderTask.getMessage(userDates).block());
    }



}
