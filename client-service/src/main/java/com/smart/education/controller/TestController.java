package com.smart.education.controller;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/api/test")
public class TestController {

    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;

    public TestController(DiscoveryClient discoveryClient, RestClient.Builder restClientBuilder) {
        this.discoveryClient = discoveryClient;
        restClient = restClientBuilder.build();
    }
    @GetMapping("/all")
    public String allAccess() {
        ServiceInstance serviceInstance = discoveryClient.getInstances("servicea").get(0);
        String serviceAResponse = restClient.get()
                .uri(serviceInstance.getUri() + "/helloWorld")
                .retrieve()
                .body(String.class);
        return serviceAResponse;
    }
    @GetMapping("/user")
    public String userAccess() {
        return "User Content.";
    }
}