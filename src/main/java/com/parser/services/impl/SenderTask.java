package com.parser.services.impl;

import com.parser.models.ResponseModel;
import com.parser.models.UserAgentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
//https://login.wifly.net/api/hdrsRes
@Component
public class SenderTask {
    private final WebClient client;

    @Autowired
    public SenderTask(WebClient.Builder builder) {
        this.client = builder.baseUrl("url").build();
    }

    public Mono<String> getMessage(UserAgentModel userDates) {
        return this.client.post().uri("endpoint").accept(MediaType.APPLICATION_JSON)
                .bodyValue(userDates)
                .retrieve()
                .bodyToMono(ResponseModel.class)
                .map(ResponseModel::getMessage);
    }
}
