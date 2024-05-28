package com.example.vkbot.controller;

import com.example.vkbot.model.VkMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/callback")
public class VkCallbackController {

    @Value("${vk.confirmation}")
    private String confirmationCode;

    @Value("${vk.token}")
    private String token;

    @Value("${vk.api.version}")
    private String apiVersion;

    @Value("${vk.api.endpoint}")
    private String apiEndpoint;

    @PostMapping
    public String handleCallback(@RequestBody VkMessage vkMessage) {
        switch (vkMessage.getType()) {
            case "confirmation":
                return confirmationCode;
            case "message_new":
                var message = vkMessage.getObject().getMessage();
                sendMessage(message.getPeer_id(), "Вы сказали: "+ message.getText());
                return "ok";
            default:
                return "Unsupported event";
        }
    }

    private void sendMessage(int peerId, String message) {
        String method = "messages.send";
        String url = apiEndpoint + method;
        String params = String.format(
                "peer_id=%d&random_id=0&message=%s&access_token=%s&v=%s",
                peerId, message, token, apiVersion
        );

        RestTemplate restTemplate = new RestTemplate();
        try {
            String response = restTemplate.postForObject(url + "?" + params, null, String.class);
            if (response.contains("\"response\"")) {
                System.out.println("Message sent successfully");
            } else {
                System.out.println("Failed to send message, response: " + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
