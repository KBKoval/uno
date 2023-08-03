package com.parser.controllers;

import com.parser.models.UserAgentModel;
import com.parser.services.interfaces.AnalyzerService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/parser")
public class ParserController {
    private final AnalyzerService analyzer;

    @Autowired
    public ParserController(AnalyzerService analyzer){
        this.analyzer = analyzer;
    }

    @GetMapping("test")
    public void parserTest(){
        analyzer.parserTest("Mozilla/5.0 (iPhone; CPU iPhone OS 14_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.0 Mobile/15E148 Safari/604.1");
    }

    @PostMapping("")
    public ResponseEntity<UserAgentModel> parser(@NotNull  @RequestParam("useragent") String userAgent){
        return new ResponseEntity<>( analyzer.parser(userAgent), HttpStatus.OK);
    }
    @GetMapping("agent")
    public ResponseEntity<UserAgentModel> parserHeader(@RequestHeader(value = "User-Agent") String userAgent){
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println(userAgent);
        System.out.println("++++++++++++++++++++++++++++++");
        return new ResponseEntity<>( analyzer.parser(userAgent), HttpStatus.OK);
    }
    @GetMapping("agent2")
    public void parserHeader(@RequestHeader Map<String, String> headers) {
        System.out.println("++++++++++++++++++++++++++++++");
        headers.forEach((key, value) -> {
            System.out.printf("Header '%s' = %s%n", key, value);
        });
        System.out.println("++++++++++++++++++++++++++++++");
    }
}
