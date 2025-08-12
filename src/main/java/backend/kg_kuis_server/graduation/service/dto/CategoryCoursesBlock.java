package backend.kg_kuis_server.graduation.service.dto;

import java.util.List;

public record CategoryCoursesBlock(String categoryLabel,             // "기교", "심교", ...
                                   Integer totalEarnedCredits,       // (F/NP 제외)
                                   Integer totalRegisteredCredits,   //
                                   List<CategoryCourseItem> items) {
}
