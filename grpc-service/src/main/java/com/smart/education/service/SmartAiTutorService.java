package com.smart.education.service;

import com.smart.education.TempDb;
import generated.grpc.smartAiTutorService.SmartAiTutorServiceGrpc;
import generated.grpc.smartAiTutorService.TutorRequest;
import generated.grpc.smartAiTutorService.TutorResponse;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public StreamObserver<TutorRequest> chatWithTutor(StreamObserver<TutorResponse> responseObserver) {

        return new StreamObserver<TutorRequest>() {
            List<TutorResponse> tutorList = new ArrayList<>();

            @Override
            public void onNext(TutorRequest tutorRequest) {
                TempDb.getChatQuestion().stream()
                        .filter(questionFromDb -> questionFromDb.getQuestionId().equals(tutorRequest.getQuestionId()))
                        .forEach(tutorList::add);

            }

            @Override
            public void onError(Throwable throwable) {
                responseObserver.onError(throwable);
            }

            @Override
            public void onCompleted() {
                tutorList.forEach(responseObserver::onNext);
                responseObserver.onCompleted();
            }
        };
    }
}
