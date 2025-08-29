package com.bajaj.BajajTask.controller;

import com.bajaj.BajajTask.service.WebHookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("getWebHook")
public class PostRequest {

    private final WebHookService webHookService;

    @PostMapping("/post")
    public String postRequest() {
        return webHookService.processWebhook();
    }
}
