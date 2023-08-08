package com.parser.controllers;

import com.parser.models.UserAgentModel;
import com.parser.services.interfaces.AnalyzerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/parser")
public class ParserController {
    private static final transient Logger log = LoggerFactory.getLogger(ParserController.class);
    private final AnalyzerService analyzer;
    private static final String FORMAT = "Header '%-20s' = %s";

    @Autowired
    public ParserController(AnalyzerService analyzer) {
        this.analyzer = analyzer;
    }

    @GetMapping("test")
    public void parserTest() {
        analyzer.parserTest("Mozilla/5.0 (iPhone; CPU iPhone OS 14_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.0 Mobile/15E148 Safari/604.1");
    }

    @PostMapping("")
    public ResponseEntity<UserAgentModel> parser( @RequestParam("useragent") String userAgent) {
        return new ResponseEntity<>(analyzer.parser(userAgent), HttpStatus.OK);
    }

    @GetMapping("agent")
    public ResponseEntity<UserAgentModel> parserHeader(@RequestHeader(value = "User-Agent") String userAgent) {
        log.info("\n++++++++++++++++++++++++++++++");
        log.info(userAgent);
        log.info("++++++++++++++++++++++++++++++\n");
        return new ResponseEntity<>(analyzer.parser(userAgent), HttpStatus.OK);
    }

    @GetMapping("header")
    public ResponseEntity<UserAgentModel> parserHeader(@RequestHeader Map<String, String> headers) {
        log.info("\n++++++++++++++++++++++++++++++");
        headers.forEach((key, value) -> {
            log.info(String.format(FORMAT, key, value));
        });
        log.info("++++++++++++++++++++++++++++++\n");
        return new ResponseEntity<>(analyzer.parser(headers.get("user-agent")), HttpStatus.OK);
    }
}
