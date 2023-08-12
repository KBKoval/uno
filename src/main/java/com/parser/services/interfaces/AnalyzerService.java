package com.parser.services.interfaces;

import com.parser.models.UserAgentFull;
import com.parser.models.UserAgentModel;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.util.List;
import java.util.Map;


public interface AnalyzerService {
    public List<String> getSupportedClientHintHeaders();
    public void parserTest(String userAgent);
    public UserAgentModel parser(String userAgent);
    public UserAgentModel  parser(LinkedCaseInsensitiveMap<String> requestHeaders);
    public UserAgentFull parserFull(LinkedCaseInsensitiveMap<String> requestHeaders);
}
