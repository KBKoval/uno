package com.parser.services.interfaces;

import com.parser.models.UserAgentModel;


public interface AnalyzerService {
    public void parserTest(String userAgent);
    public UserAgentModel parser(String userAgent);
}
