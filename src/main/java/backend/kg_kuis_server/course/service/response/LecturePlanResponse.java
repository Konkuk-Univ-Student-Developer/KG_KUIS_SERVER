// src/main/java/com/example/lecture/api/dto/LecturePlanResponse.java
package com.example.lecture.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "강의계획서 전체 응답")
public class LecturePlanResponse {

    @Schema(description = "과목 기본 정보")
    public SubjectInfo subjectInfo;

    @Schema(description = "평가 항목 리스트")
    public List<Evaluation> evaluation;

    @Schema(description = "교재 및 참고문헌 리스트")
    public List<Book> books;

    @Schema(description = "과제 리스트")
    public List<Assignment> assignments;

    @Schema(description = "주차별 강의계획")
    public List<WeeklyPlan> weeklyPlans;


    // -------------------- 내부 DTO --------------------
    @Schema(description = "과목 기본 정보")
    public static class SubjectInfo {
        @Schema(description = "과목 ID (예: 0001)", example = "0001")
        public String sbjt_id;

        @Schema(description = "과목명", example = "자료구조")
        public String subject_name;

        @Schema(description = "과목명(영문)", example = "Data Structures")
        public String subject_name_eng;

        @Schema(description = "비고/특이사항", example = "영강 / 블렌디드 러닝")
        public String notes;

        @Schema(description = "강의 목표", example = "자료구조의 기본 개념과 활용을 이해한다.")
        public String goal;
    }

    @Schema(description = "평가 항목")
    public static class Evaluation {
        @Schema(description = "평가 항목명", example = "중간고사")
        public String item_name;

        @Schema(description = "비율(%)", example = "30")
        public Integer ratio;

        @Schema(description = "만점 점수", example = "100")
        public Integer full_score;

        @Schema(description = "성적 공개 여부", example = "Y")
        public String is_public;
    }

    @Schema(description = "교재 및 참고문헌")
    public static class Book {
        @Schema(description = "구분 (예: 주교재, 참고문헌)", example = "주교재")
        public String type;

        @Schema(description = "교재 제목", example = "Introduction to Algorithms")
        public String title;

        @Schema(description = "저자", example = "Thomas H. Cormen")
        public String author;

        @Schema(description = "출판사", example = "MIT Press")
        public String publisher;

        @Schema(description = "출판년도", example = "2022")
        public String year;
    }

    @Schema(description = "과제 정보")
    public static class Assignment {
        @Schema(description = "과제 제목", example = "프로젝트 1")
        public String title;

        @Schema(description = "제출 기한", example = "2025-10-15")
        public String due_date;

        @Schema(description = "제출 방법", example = "LMS 업로드")
        public String method;
    }

    @Schema(description = "주차별 강의계획")
    public static class WeeklyPlan {
        @Schema(description = "주차", example = "1")
        public Integer week;

        @Schema(description = "기간", example = "2025-03-02 ~ 2025-03-08")
        public String period;

        @Schema(description = "주제", example = "자료구조 개요")
        public String topic;

        @Schema(description = "강의 내용", example = "자료구조의 정의와 중요성 소개")
        public String content;

        @Schema(description = "수업 형태", example = "이론")
        public String type;

        @Schema(description = "학습 활동", example = "강의 및 질의응답")
        public String activity;

        @Schema(description = "담당 강사", example = "김교수")
        public String instructor;
    }
}
