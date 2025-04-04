package com.smart.education.config;

import generated.grpc.smartAiTutorService.SmartAiTutorServiceGrpc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.GrpcChannelFactory;

@Configuration
public class StubConfig {
    @Bean
    SmartAiTutorServiceGrpc.SmartAiTutorServiceBlockingStub smartAiTutorServiceBlockingStub(GrpcChannelFactory channelFactory){
        return SmartAiTutorServiceGrpc.newBlockingStub(channelFactory.createChannel("service1"));

    }

}
