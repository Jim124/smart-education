package com.smart.education.service;

import com.google.protobuf.Descriptors;
import com.smart.education.TempDb;
import generated.grpc.smartLearningManagerService.ManagerRequest;
import generated.grpc.smartLearningManagerService.ManagerResponse;
import generated.grpc.smartLearningManagerService.SmartLearningManagerServiceGrpc;
import io.grpc.stub.StreamObserver;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
public class SmartLearningManagerService {
    @Resource
    SmartLearningManagerServiceGrpc.SmartLearningManagerServiceStub smartLearningManagerServiceStub;

    public Map<String, Map<Descriptors.FieldDescriptor, Object>> getSummary() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final Map<String, Map<Descriptors.FieldDescriptor, Object>> response = new HashMap<>();
        StreamObserver<ManagerRequest> responseObserver= smartLearningManagerServiceStub.updateLearningPath(new StreamObserver<ManagerResponse>() {
            @Override
            public void onNext(ManagerResponse managerResponse) {
                response.put("summary", managerResponse.getAllFields());
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
        TempDb.getAllManageRequest().forEach(responseObserver::onNext);
        responseObserver.onCompleted();
        boolean await = countDownLatch.await(1, TimeUnit.MINUTES);
        return await ? response : Collections.emptyMap();
    }
}
