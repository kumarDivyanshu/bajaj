package com.bajaj.BajajTask.runner;

import com.bajaj.BajajTask.service.WebHookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApiTaskRunner implements CommandLineRunner {
    private final WebHookService webHookService;

    public ApiTaskRunner(WebHookService webHookService) {
        this.webHookService = webHookService;
    }

    @Override
    public void run(String... args) {
        String result = webHookService.processWebhook();
        System.out.println(result);
    }
}
