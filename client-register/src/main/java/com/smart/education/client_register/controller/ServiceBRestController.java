package com.smart.education.client_register.controller;

import com.google.protobuf.Descriptors;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;
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

    // unary service
    @GetMapping("/tutor/{question}")
    public String helloWorld(@PathVariable String question) {
        ServiceInstance serviceInstance = discoveryClient.getInstances("servicea").get(0);
        String serviceAResponse = restClient.get()
                .uri(serviceInstance.getUri() + "/api/tutor/" + question)
                .retrieve()
                .body(String.class);
        return serviceAResponse;
    }

    // Bi-Directional service
    @GetMapping("/chat/{content}")
    public List<Map<Descriptors.FieldDescriptor, Object>> chatWithTutor(@PathVariable String content) throws InterruptedException {
        ServiceInstance serviceInstance = discoveryClient.getInstances("servicea").get(0);
        List<Map<Descriptors.FieldDescriptor, Object>> serviceAResponse = restClient.get()
                .uri(serviceInstance.getUri() + "/api/chat/" + content)
                .retrieve()
                .body(List.class);
        return serviceAResponse;
    }
    // client-streaming service
    @GetMapping("/manage")
    public Map<String, Map<Descriptors.FieldDescriptor, Object>> getSummary() throws InterruptedException {
        ServiceInstance serviceInstance = discoveryClient.getInstances("servicea").get(0);
        Map<String, Map<Descriptors.FieldDescriptor, Object>> serviceAResponse = restClient.get()
                .uri(serviceInstance.getUri() + "/api/learn/manage")
                .retrieve()
                .body(Map.class);
        return serviceAResponse;
    }

    // server streaming service
    @GetMapping("/grade/{studentId}")
    public List<Map<Descriptors.FieldDescriptor, Object>> getGradeByStudentId(@PathVariable String studentId) throws InterruptedException {
        ServiceInstance serviceInstance = discoveryClient.getInstances("servicea").get(0);
        List<Map<Descriptors.FieldDescriptor, Object>> serviceAResponse = restClient.get()
                .uri(serviceInstance.getUri() + "/api/grade/" + studentId)
                .retrieve()
                .body(List.class);
        return serviceAResponse;
    }
}
