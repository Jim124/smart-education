package com.smart.education.service;

import com.smart.education.TempDb;
import generated.grpc.smartAiTutorService.SmartAiTutorServiceGrpc;
import generated.grpc.smartAiTutorService.TutorRequest;
import generated.grpc.smartAiTutorService.TutorResponse;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

@Service
public class SmartAiTutorService extends SmartAiTutorServiceGrpc.SmartAiTutorServiceImplBase {
    @Override
    public void askSingleQuestion(TutorRequest request, StreamObserver<TutorResponse> responseObserver) {
        TempDb.getQuestion()
                .stream()
                .filter(tourResponse -> tourResponse.getQuestionContent().equals(request.getQuestionContent()))
                .findFirst()
                .ifPresent(responseObserver::onNext);
        responseObserver.onCompleted();
    }
}
