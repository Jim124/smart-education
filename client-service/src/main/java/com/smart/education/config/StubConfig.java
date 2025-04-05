package com.smart.education.config;

import generated.grpc.smartAiTutorService.SmartAiTutorServiceGrpc;
import generated.grpc.smartAutoGraderService.SmartAutoGraderServiceGrpc;
import generated.grpc.smartLearningManagerService.SmartLearningManagerServiceGrpc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.GrpcChannelFactory;

@Configuration
public class StubConfig {
    @Bean
    SmartAiTutorServiceGrpc.SmartAiTutorServiceBlockingStub smartAiTutorServiceBlockingStub(GrpcChannelFactory channelFactory){
        return SmartAiTutorServiceGrpc.newBlockingStub(channelFactory.createChannel("service1"));

    }
    @Bean
    SmartAutoGraderServiceGrpc.SmartAutoGraderServiceStub smartAutoGraderServiceStub(GrpcChannelFactory channelFactory){
        return SmartAutoGraderServiceGrpc.newStub(channelFactory.createChannel("service1"));
    }

    @Bean
    SmartLearningManagerServiceGrpc.SmartLearningManagerServiceStub smartLearningManagerServiceStub(GrpcChannelFactory channelFactory){
        return SmartLearningManagerServiceGrpc.newStub(channelFactory.createChannel("service1"));
    }

    @Bean
    SmartAiTutorServiceGrpc.SmartAiTutorServiceStub smartAiTutorServiceStub(GrpcChannelFactory channelFactory){
        return SmartAiTutorServiceGrpc.newStub(channelFactory.createChannel("service1"));
    }

}
