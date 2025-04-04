package com.smart.education.controller;

import generated.grpc.smartAiTutorService.SmartAiTutorServiceGrpc;
import generated.grpc.smartAiTutorService.TutorRequest;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class TutorController {

    @Resource
    SmartAiTutorServiceGrpc.SmartAiTutorServiceBlockingStub clientService;

    @GetMapping("/tutor/{content}")
    public String getAnswer(@PathVariable String content){
        try{
            TutorRequest request = TutorRequest.newBuilder().setQuestionContent(content).build();
            return clientService.askSingleQuestion(request).getExplanation();
        }catch (Exception e){
            e.printStackTrace();
            return "Sorry, something went wrong!";
        }


    }

}
