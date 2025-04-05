package com.smart.education.service;

import com.google.protobuf.Descriptors;
import generated.grpc.smartAutoGraderService.GradeRequest;
import generated.grpc.smartAutoGraderService.GradeResponse;
import generated.grpc.smartAutoGraderService.SmartAutoGraderServiceGrpc;
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
public class SmartAutoGradeService {

    @Resource
    SmartAutoGraderServiceGrpc.SmartAutoGraderServiceStub asynchronousClient;

    public List<Map<Descriptors.FieldDescriptor, Object>> getGradeByStudentId(String studentId) throws InterruptedException {
        final GradeRequest gradeRequest = GradeRequest.newBuilder().setStudentId(studentId).build();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final List<Map<Descriptors.FieldDescriptor, Object>> response = new ArrayList<>();
        asynchronousClient.gradeSubmission(gradeRequest, new StreamObserver<GradeResponse>() {
            @Override
            public void onNext(GradeResponse gradeResponse) {
                response.add(gradeResponse.getAllFields());
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
        boolean await = countDownLatch.await(1, TimeUnit.MINUTES);
        return await ? response : Collections.emptyList();
    }

}
