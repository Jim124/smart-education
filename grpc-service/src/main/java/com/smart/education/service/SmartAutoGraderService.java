package com.smart.education.service;

import com.smart.education.TempDb;
import generated.grpc.smartAutoGraderService.GradeRequest;
import generated.grpc.smartAutoGraderService.GradeResponse;
import generated.grpc.smartAutoGraderService.SmartAutoGraderServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

@Service
public class SmartAutoGraderService extends SmartAutoGraderServiceGrpc.SmartAutoGraderServiceImplBase {
    @Override
    public void gradeSubmission(GradeRequest request, StreamObserver<GradeResponse> responseObserver) {
        TempDb.getGradeByStudentId()
                .stream()
                .filter(gradeResponse -> request.getStudentId().equals(gradeResponse.getStudentId()))
                .forEach(responseObserver::onNext);
        responseObserver.onCompleted();
    }
}
