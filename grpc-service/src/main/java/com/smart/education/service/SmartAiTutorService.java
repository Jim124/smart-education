package com.smart.education.service;

import com.smart.education.TempDb;
import generated.grpc.smartAiTutorService.SmartAiTutorServiceGrpc;
import generated.grpc.smartAiTutorService.TutorError;
import generated.grpc.smartAiTutorService.TutorRequest;
import generated.grpc.smartAiTutorService.TutorResponse;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.protobuf.ProtoUtils;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@GrpcService
public class SmartAiTutorService extends SmartAiTutorServiceGrpc.SmartAiTutorServiceImplBase {
    @Override
    public void askSingleQuestion(TutorRequest request, StreamObserver<TutorResponse> responseObserver) {
        Stream<TutorResponse> answers = TempDb.getQuestion().stream().filter(tourResponse -> tourResponse.getQuestionContent().equals(request.getQuestionContent()));
        if(answers.count() == 0){
            Status status = Status.NOT_FOUND.withDescription("NOT FOUND ANSWER");
            Metadata metadata = new Metadata();
            Metadata.Key<TutorError> errorKey =ProtoUtils.keyForProto(TutorError.getDefaultInstance());
            metadata.put(errorKey,TutorError.newBuilder().setErrType("1").setMessage("There is no answer").build());
            responseObserver.onError(status.asRuntimeException(metadata));
        } else {
            TempDb.getQuestion()
                    .stream()
                    .filter(tourResponse -> tourResponse.getQuestionContent().equals(request.getQuestionContent()))
                    .findFirst()
                    .ifPresent(responseObserver::onNext);
            responseObserver.onCompleted();
        }

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
