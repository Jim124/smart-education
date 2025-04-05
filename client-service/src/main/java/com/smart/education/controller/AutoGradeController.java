package com.smart.education.controller;

import com.google.protobuf.Descriptors;
import com.smart.education.service.SmartAutoGradeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AutoGradeController {

    @Resource
    SmartAutoGradeService smartAutoGradeService;

    @GetMapping("/grade/{studentId}")
    public List<Map<Descriptors.FieldDescriptor, Object>> getGradeByStudentId(@PathVariable String studentId) throws InterruptedException {
        return smartAutoGradeService.getGradeByStudentId(studentId);
    }


}
