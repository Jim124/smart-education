package com.smart.education;

import java.util.ArrayList;
import java.util.List;

import generated.grpc.smartAiTutorService.TutorResponse;
import generated.grpc.smartAutoGraderService.GradeResponse;
import generated.grpc.smartLearningManagerService.ManagerRequest;

public class TempDb {
    public static List<TutorResponse> getQuestion(){
       ArrayList<TutorResponse> result = new ArrayList<>();
       result.add(TutorResponse.newBuilder().setQuestionId("1").setQuestionContent("question1").setExplanation("this is the answer 1").build());
       result.add(TutorResponse.newBuilder().setQuestionId("2").setQuestionContent("question2").setExplanation("this is the answer 2").build());
       result.add(TutorResponse.newBuilder().setQuestionId("3").setQuestionContent("question3").setExplanation("this is the answer 3").build());
       result.add(TutorResponse.newBuilder().setQuestionId("4").setQuestionContent("question4").setExplanation("this is the answer 4").build());
       return result;
    }

    public static List<GradeResponse> getGradeByStudentId(){
        ArrayList<GradeResponse> result = new ArrayList<>();
        result.add(GradeResponse.newBuilder().setStudentId("1").setScore(65).setFeedback("this course is perfect").setContent("this is the content").setIsComplete(true).setCourse("math").build());
        result.add(GradeResponse.newBuilder().setStudentId("1").setScore(76).setFeedback("this course is nice").setContent("this is the content").setIsComplete(true).setCourse("physical").build());
        result.add(GradeResponse.newBuilder().setStudentId("1").setScore(88).setFeedback("awesome").setContent("this is the content").setIsComplete(true).setCourse("english").build());
        result.add(GradeResponse.newBuilder().setStudentId("1").setScore(89).setFeedback("perfect").setContent("this is the content").setIsComplete(true).setCourse("chinese").build());
        result.add(GradeResponse.newBuilder().setStudentId("1").setScore(98).setFeedback("superb").setContent("this is the content").setIsComplete(true).setCourse("japanese").build());
        result.add(GradeResponse.newBuilder().setStudentId("1").setScore(67).setFeedback("good").setContent("this is the content").setIsComplete(true).setCourse("german").build());
        result.add(GradeResponse.newBuilder().setStudentId("1").setScore(86).setFeedback("awesome").setContent("this is the content").setIsComplete(true).setCourse("Chemical").build());
        result.add(GradeResponse.newBuilder().setStudentId("1").setScore(88).setFeedback("well done").setContent("this is the content").setIsComplete(true).setCourse("biology").build());
        return result;
    }

    public static List<ManagerRequest> getAllManageRequest(){
        ArrayList<ManagerRequest> result = new ArrayList<>();
        result.add(ManagerRequest.newBuilder().setStudentId("1").setCourseId("1").setPerformanceData("path://abc/abc/abc").build());
        result.add(ManagerRequest.newBuilder().setStudentId("1").setCourseId("1").setPerformanceData("path://bcd/bcd/bcd").build());
        result.add(ManagerRequest.newBuilder().setStudentId("1").setCourseId("1").setPerformanceData("path://fgb/fgb/fgb").build());
        result.add(ManagerRequest.newBuilder().setStudentId("1").setCourseId("1").setPerformanceData("path://yui/yui/yui").build());
        result.add(ManagerRequest.newBuilder().setStudentId("1").setCourseId("1").setPerformanceData("path://bhu/bhu/bhu").build());
        return result;
    }

    public static List<TutorResponse> getChatQuestion(){
        ArrayList<TutorResponse> result = new ArrayList<>();
        result.add(TutorResponse.newBuilder().setQuestionId("1").setQuestionContent("question1").setExplanation("this is the answer 1").build());
        result.add(TutorResponse.newBuilder().setQuestionId("2").setQuestionContent("question1").setExplanation("this is the answer 2").build());
        result.add(TutorResponse.newBuilder().setQuestionId("3").setQuestionContent("question1").setExplanation("this is the answer 3").build());
        result.add(TutorResponse.newBuilder().setQuestionId("4").setQuestionContent("question1").setExplanation("this is the answer 4").build());
        return result;
    }
}
