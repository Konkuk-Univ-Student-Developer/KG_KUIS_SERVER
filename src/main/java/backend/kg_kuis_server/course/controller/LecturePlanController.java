package backend.kg_kuis_server.course.controller;

import backend.kg_kuis_server.course.service.LecturePlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/courses")
@Tag(name = "종합강의시간표 API", description = "종합강의시간표 조회 API")
public class LecturePlanController {

    private final LecturePlanService service;

    public LecturePlanController(LecturePlanService service) {
        this.service = service;
    }

    @Operation(
            summary = "강의계획서 조회",
            description = "과목코드(4자리)를 통해 강의계획서 전체 내용을 조회합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "조회 성공",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = com.example.lecture.api.dto.LecturePlanResponse.class),
                    examples = @ExampleObject(value = """
                        {
                          "subjectInfo": {
                            "sbjt_id": "0001",
                            "subject_name": "자료구조",
                            "subject_name_eng": "Data Structures",
                            "notes": "영강 / 블렌디드 러닝",
                            "goal": "자료구조의 기본 개념과 활용을 이해한다."
                          },
                          "evaluation": [
                            { "item_name": "중간고사", "ratio": 30, "full_score": 100, "is_public": "Y" },
                            { "item_name": "기말고사", "ratio": 40, "full_score": 100, "is_public": "Y" }
                          ],
                          "books": [
                            { "type": "주교재", "title": "Introduction to Algorithms", "author": "Thomas H. Cormen", "publisher": "MIT Press", "year": "2022" }
                          ],
                          "assignments": [
                            { "title": "프로젝트 1", "due_date": "2025-10-15", "method": "LMS 업로드" }
                          ],
                          "weeklyPlans": [
                            { "week": 1, "period": "2025-03-02 ~ 2025-03-08", "topic": "자료구조 개요", "content": "자료구조의 정의와 중요성 소개", "type": "이론", "activity": "강의 및 질의응답", "instructor": "김교수" }
                          ]
                        }
                        """)
            )
    )
    @GetMapping("/{code}")
    public ResponseEntity<com.example.lecture.api.dto.LecturePlanResponse> getBySubjectCode(
            @PathVariable String code
    ) {
        return ResponseEntity.ok(service.getBySubjectCode(code));
    }


}
