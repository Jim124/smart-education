package com.smart.education.client_register.controller;

import com.google.protobuf.Descriptors;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ServiceBRestController {
    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;

    public ServiceBRestController(DiscoveryClient discoveryClient, RestClient.Builder restClientBuilder) {
        this.discoveryClient = discoveryClient;
        restClient = restClientBuilder.build();
    }

    @GetMapping("/discovery")
    public String helloWorld() {
        ServiceInstance serviceInstance = discoveryClient.getInstances("servicea").get(0);
        String serviceAResponse = restClient.get()
                .uri(serviceInstance.getUri() + "/api/test/all")
                .retrieve()
                .body(String.class);
        return serviceAResponse;
    }

    @GetMapping("/manage")
    public Map<String, Map<Descriptors.FieldDescriptor, Object>> getSummary() throws InterruptedException {
        ServiceInstance serviceInstance = discoveryClient.getInstances("servicea").get(0);
        Map<String, Map<Descriptors.FieldDescriptor, Object>> serviceAResponse = restClient.get()
                .uri(serviceInstance.getUri() + "/api/learn/manage")
                .retrieve()
                .body(Map.class);
        return serviceAResponse;
        //return smartLearningManagerService.getSummary();
    }
}
