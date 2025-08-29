package com.bajaj.BajajTask.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WebhookResponse {
    @JsonProperty("webhook") // Match the exact JSON key from the API if it's different
    private String webhook;
    private String accessToken;
}
