package com.smart.education.controller;

import com.google.protobuf.Descriptors;
import com.smart.education.service.SmartLearningManagerService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class LearningManageController {
    @Resource
    SmartLearningManagerService smartLearningManagerService;

    @GetMapping("/manage")
    public Map<String, Map<Descriptors.FieldDescriptor, Object>> getSummary() throws InterruptedException {
        return smartLearningManagerService.getSummary();
    }
}
