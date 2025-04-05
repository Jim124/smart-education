package com.smart.education.controller;

import com.google.protobuf.Descriptors;
import com.smart.education.service.SmartAiTutorClientService;
import generated.grpc.smartAiTutorService.SmartAiTutorServiceGrpc;
import generated.grpc.smartAiTutorService.TutorRequest;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TutorController {

    @Resource
    SmartAiTutorServiceGrpc.SmartAiTutorServiceBlockingStub clientService;

    @Resource
    SmartAiTutorClientService smartAiTutorClientService;

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
    @GetMapping("/chat/{content}")
    public List<Map<Descriptors.FieldDescriptor, Object>> chatWithTutor(@PathVariable String content) throws InterruptedException {
        return smartAiTutorClientService.chatWithTutor(content);
    }

}
