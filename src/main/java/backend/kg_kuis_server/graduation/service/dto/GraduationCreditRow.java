package backend.kg_kuis_server.graduation.service.dto;

public record GraduationCreditRow(
        String programType,   // 항상 "원전공"
        String category,      // 기교/심교/전필+전선/...
        Integer required,     // 기준학점
        Integer acquired,     // 취득학점
        Integer remaining    // 잔여학점
) {}
