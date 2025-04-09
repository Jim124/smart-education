package com.smart.education.service;

import generated.grpc.smartLearningManagerService.ManagerRequest;
import generated.grpc.smartLearningManagerService.ManagerResponse;
import generated.grpc.smartLearningManagerService.SmartLearningManagerServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class SmartLearningService extends SmartLearningManagerServiceGrpc.SmartLearningManagerServiceImplBase {

    @Override
    public StreamObserver<ManagerRequest> updateLearningPath(StreamObserver<ManagerResponse> responseObserver) {

       return new StreamObserver<ManagerRequest>() {
            String learningPath = "";
            String summary = "";
            String studentId = "";
            String courseId = "";
           @Override
           public void onNext(ManagerRequest managerRequest) {
                if (!managerRequest.getPerformanceData().equals("")){
                    learningPath += managerRequest.getPerformanceData() +"||";
                    summary += managerRequest.getPerformanceData() + "||";
                    studentId = managerRequest.getStudentId();
                    courseId = managerRequest.getCourseId();
                }
           }

           @Override
           public void onError(Throwable throwable) {

           }

           @Override
           public void onCompleted() {
               ManagerResponse managerResponse = ManagerResponse.newBuilder().setCourseId(courseId).setStudentId(studentId).setLearningPath(learningPath).setSummary(summary).build();
               responseObserver.onNext(managerResponse);
               responseObserver.onCompleted();

           }
       };
    }
}
