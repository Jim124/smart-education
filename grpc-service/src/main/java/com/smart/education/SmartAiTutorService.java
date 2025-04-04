package com.smart.education;

import generated.grpc.smartAiTutorService.SmartAiTutorServiceGrpc;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class SmartAiTutorService extends SmartAiTutorServiceGrpc.SmartAiTutorServiceImplBase {
}
