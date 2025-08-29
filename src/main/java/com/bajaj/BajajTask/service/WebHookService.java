package com.bajaj.BajajTask.service;

import com.bajaj.BajajTask.dto.UserDetailsRequest;
import com.bajaj.BajajTask.dto.SolutionRequest;
import com.bajaj.BajajTask.dto.WebhookResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class WebHookService {

    private final RestTemplate restTemplate = new RestTemplate();

    public String processWebhook() {
        try {
            // 1. Generate Webhook
            String url = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

            UserDetailsRequest body = new UserDetailsRequest(
                    "Divyanshu Kumar",
                    "22BCE2303",
                    "divyanshu.kumar2022@vitstudent.ac.in"
            );

            ResponseEntity<WebhookResponse> response = restTemplate.postForEntity(url, body, WebhookResponse.class);

            if (response.getBody() == null) {
                return "❌ Failed: No response from server";
            }

            String webhookUrl = response.getBody().getWebhook();
            String accessToken = response.getBody().getAccessToken();

            System.out.println(webhookUrl);
            System.out.println(accessToken);

            // 2. SQL Query
            String finalQuery =
                    "SELECT p.AMOUNT AS SALARY, CONCAT(e.FIRST_NAME, ' ', e.LAST_NAME) AS NAME, "
                            + "TIMESTAMPDIFF(YEAR, e.DOB, CURDATE()) AS AGE, d.DEPARTMENT_NAME "
                            + "FROM PAYMENTS p "
                            + "JOIN EMPLOYEE e ON p.EMP_ID = e.EMP_ID "
                            + "JOIN DEPARTMENT d ON e.DEPARTMENT = d.DEPARTMENT_ID "
                            + "WHERE DAY(p.PAYMENT_TIME) <> 1 "
                            + "ORDER BY p.AMOUNT DESC LIMIT 1;";


// Prepare request body DTO
            SolutionRequest answerBody = new SolutionRequest(finalQuery);


            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", accessToken);


            HttpEntity<SolutionRequest> entity = new HttpEntity<>(answerBody, headers);


            restTemplate.postForEntity(webhookUrl, entity, String.class);


            return "✅ Final SQL query submitted successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "❌ Error occurred: " + e.getMessage();
        }
    }

}
