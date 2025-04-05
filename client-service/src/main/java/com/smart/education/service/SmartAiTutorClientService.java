package com.smart.education.service;

import com.google.protobuf.Descriptors;
import com.smart.education.TempDb;
import generated.grpc.smartAiTutorService.SmartAiTutorServiceGrpc;
import generated.grpc.smartAiTutorService.TutorRequest;
import generated.grpc.smartAiTutorService.TutorResponse;
import io.grpc.stub.StreamObserver;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
public class SmartAiTutorClientService {

    @Resource
    SmartAiTutorServiceGrpc.SmartAiTutorServiceStub smartAiTutorServiceStub;

    public List<Map<Descriptors.FieldDescriptor, Object>> chatWithTutor(String content) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final List<Map<Descriptors.FieldDescriptor, Object>> response = new ArrayList<>();
        StreamObserver<TutorRequest> responseObserver =  smartAiTutorServiceStub.chatWithTutor(new StreamObserver<TutorResponse>() {
            @Override
            public void onNext(TutorResponse tutorResponse) {
                response.add(tutorResponse.getAllFields());
            }

            @Override
            public void onError(Throwable throwable) {
                countDownLatch.countDown();
            }

            @Override
            public void onCompleted() {
                countDownLatch.countDown();
            }
        });
        TempDb.getChatQuestion().stream()
                        .filter(tutorResponse -> tutorResponse.getQuestionContent().equals(content))
                                .forEach(tutorResponse -> responseObserver.onNext(TutorRequest.newBuilder().setQuestionId(tutorResponse.getQuestionId()).build()));
        responseObserver.onCompleted();
        boolean await = countDownLatch.await(1, TimeUnit.MINUTES);
        return await ? response : Collections.emptyList();
    }
}

