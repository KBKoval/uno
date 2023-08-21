package com.parser.controllers;

import com.parser.models.UserAgentFull;

import com.parser.services.interfaces.AnalyzerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;


@Controller
public class HelloController {
    private static final transient Logger log = LoggerFactory.getLogger(HelloController.class);

    private final AnalyzerService analyzer;

    @Autowired
    public HelloController(AnalyzerService analyzer) {
        this.analyzer = analyzer;
    }

    @GetMapping("/")
    public String index(ModelMap model, @RequestHeader Map<String, String> headers) {

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Accept-CH", String.join(", ", analyzer.getSupportedClientHintHeaders()));

        LinkedCaseInsensitiveMap<String> requestHeaders = new LinkedCaseInsensitiveMap<>();
        requestHeaders.putAll(headers);

        UserAgentFull userAgentModel = analyzer.parserFull(requestHeaders);
        model.addAttribute("info", userAgentModel);
        return "index";
    }

}
