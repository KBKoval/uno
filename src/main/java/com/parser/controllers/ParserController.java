package com.parser.controllers;


import com.parser.models.UserAgentModel;
import com.parser.services.impl.ParserThreadImpl;
import com.parser.services.interfaces.AnalyzerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@CrossOrigin("*")
@RequestMapping("/parser")
public class ParserController {
    private static final transient Logger log = LoggerFactory.getLogger(ParserController.class);
    private final AnalyzerService analyzer;
    private static final String FORMAT = "Header '%-20s' = %s";


    @Autowired
    public ParserController(AnalyzerService analyzer) {
        this.analyzer = analyzer;
    }

    @PostMapping("")
    public ResponseEntity<UserAgentModel> parser(@RequestParam("useragent") String userAgent) {
        return new ResponseEntity<>(analyzer.parser(userAgent), HttpStatus.OK);
    }

    @GetMapping("agent")
    public ResponseEntity<UserAgentModel> parserHeader(@RequestHeader(value = "User-Agent") String userAgent) {
        log.info("\n++++++++++++++++       ++++++++++++++");
        log.info(userAgent);
        log.info("++++++++++++++++++++++++++++++\n");
        return new ResponseEntity<>(analyzer.parser(userAgent), HttpStatus.OK);
    }

    @GetMapping("header")
    public ResponseEntity<UserAgentModel> parserHeader(@RequestHeader Map<String, String> headers) {
        HttpHeaders responseHeaders = new HttpHeaders();

        responseHeaders.add("Accept-CH", String.join(", ",analyzer.getSupportedClientHintHeaders() )  );

        LinkedCaseInsensitiveMap<String> requestHeaders = new LinkedCaseInsensitiveMap<>();
        requestHeaders.putAll(headers);

        UserAgentModel model = analyzer.parser(requestHeaders);


        return new ResponseEntity<>(model, responseHeaders, HttpStatus.OK);
    }


}
