package com.parser.controllers;

import com.parser.models.RequestModel;
import com.parser.services.impl.ParserThreadImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/parser")
public class ParserControlerAsync {
    private static final transient Logger log = LoggerFactory.getLogger(ParserControlerAsync.class);
    private final ParserThreadImpl threadParser;
    private final ThreadPoolTaskExecutor taskExecutor;


    @Autowired
    public ParserControlerAsync(ThreadPoolTaskExecutor taskExecutor, ParserThreadImpl threadParser) {
        this.taskExecutor = taskExecutor;
        this.threadParser = threadParser;
    }

   /* @GetMapping("test")
    public ResponseEntity<String> parserTest() {
        threadParser.setUserAgent("Mozilla/5.0 (iPhone; CPU iPhone OS 14_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.0 Mobile/15E148 Safari/604.1");
        taskExecutor.execute(threadParser);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
*/
    @PostMapping("dates")
    public ResponseEntity<String> parserHeaders(@RequestBody(required = true) RequestModel headers) {
        //threadParser.setUserAgent("Mozilla/5.0 (iPhone; CPU iPhone OS 14_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.0 Mobile/15E148 Safari/604.1");
        threadParser.setHeaders(headers);
        taskExecutor.execute(threadParser);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
