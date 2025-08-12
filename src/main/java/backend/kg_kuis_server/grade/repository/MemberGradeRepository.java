package backend.kg_kuis_server.grade.repository;

import backend.kg_kuis_server.course.domain.CourseCategory;
import backend.kg_kuis_server.grade.repository.entity.MemberGrade;
import backend.kg_kuis_server.member.domain.Semester;
import backend.kg_kuis_server.member.repository.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberGradeRepository extends JpaRepository<MemberGrade, Long> {

    // 카테고리별 취득 학점 합계 (F/NP 제외)
    @Query("""
              select mc.course.category, coalesce(sum(mc.course.credit),0)
              from MemberGrade mc
              where mc.member.id = :memberId
                and (mc.letterGrade is null or mc.letterGrade not in ('F','NP'))
              group by mc.course.category
            """)
    List<Object[]> sumEarnedCreditsGroupByCategory(@Param("memberId") Long memberId);

    // 카테고리별 수강 신청 학점 합계
    @Query("""
              select mc.course.category, coalesce(sum(mc.course.credit),0)
              from MemberGrade mc
              where mc.member.id = :memberId
                and mc.letterGrade is null
              group by mc.course.category
            """)
    List<Object[]> sumRegisteredCreditsGroupByCategory(@Param("memberId") Long memberId);

    // 특정 카테고리의 과목 목록 (표 행)
    @Query("""
              select mc
              from MemberGrade mc
              where mc.member.id = :memberId
                and mc.course.category = :category
              order by mc.course.courseYear asc, mc.course.semester asc, mc.course.courseNumber asc
            """)
    List<MemberGrade> findAllByMemberAndCategory(
            @Param("memberId") Long memberId,
            @Param("category") CourseCategory category);

    List<MemberGrade> findAllByMember(MemberEntity member);

    @Query("""
                select coalesce(sum(c.credit), 0)
                from MemberGrade mc
                join mc.course c
                where mc.member.id = :memberId
                  and (mc.letterGrade is null or mc.letterGrade not in ('F','NP'))
            """)
    int sumEarnedCredits(@Param("memberId") Long memberId);

    List<MemberGrade> findMemberGradeByMemberAndCourse_CourseYearAndCourse_Semester(MemberEntity member, Integer courseYear, Semester semester);

    // 모든 성적 + 과목정보를 한 번에 가져오기 (N+1 방지)
    @Query("""
        select mg from MemberGrade mg
        join fetch mg.course c
        where mg.member.id = :memberId
        order by c.courseYear asc, c.semester asc, c.courseNumber asc
    """)
    List<MemberGrade> findAllWithCourseByMemberId(Long memberId);
}
