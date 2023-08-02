package com.parser.services.interfaces;

import com.parser.models.UserAgentModel;
import jakarta.validation.constraints.NotNull;


public interface AnalyzerService {
    public void parserTest(@NotNull String userAgent);
    public UserAgentModel parser(@NotNull   String userAgent);
}
