package com.parser.controllers;

import com.parser.models.UserAgentModel;
import com.parser.services.interfaces.AnalyzerService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        analyzer.parserTest("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.8.1.11) Gecko/20071127 Firefox/2.0.0.11");
    }

    @PostMapping("")
    public ResponseEntity<UserAgentModel> parser(@NotNull  @RequestParam("useragent") String useragent){
        return new ResponseEntity<>( analyzer.parser(useragent), HttpStatus.OK);
    }
}
