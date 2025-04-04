package com.smart.education;

import java.util.ArrayList;
import java.util.List;

import generated.grpc.smartAiTutorService.TutorResponse;
public class TempDb {
    public static List<TutorResponse> getQuestion(){
       ArrayList<TutorResponse> result = new ArrayList<>();
       result.add(TutorResponse.newBuilder().setQuestionId("1").setQuestionContent("question1").setExplanation("this is the answer 1").build());
       result.add(TutorResponse.newBuilder().setQuestionId("2").setQuestionContent("question2").setExplanation("this is the answer 2").build());
       result.add(TutorResponse.newBuilder().setQuestionId("3").setQuestionContent("question3").setExplanation("this is the answer 3").build());
       result.add(TutorResponse.newBuilder().setQuestionId("4").setQuestionContent("question4").setExplanation("this is the answer 4").build());
       return result;
    }
}
