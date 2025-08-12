package backend.kg_kuis_server.scholarship.repository;

import backend.kg_kuis_server.member.domain.Semester;
import backend.kg_kuis_server.scholarship.repository.entity.MemberScholarShip;
import backend.kg_kuis_server.scholarship.repository.entity.ScholarshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface MemberScholarshipRepository extends JpaRepository<MemberScholarShip, Long> {

    // 기간 필터: 특정 학생의 신청 내역
    @Query("""
                select a from MemberScholarShip a
                where a.member.id = :memberId
                  and a.applicationDate between :from and :to
                order by a.applicationDate desc, a.id desc
            """)
    List<MemberScholarShip> findAllByStudentAndPeriod(
            Long memberId, LocalDate from, LocalDate to);

    // 내가 해당 공고에 이미 신청했는지 (상태를 같이 보고 싶으면 projection 사용)
    @Query("""
                select a from MemberScholarShip a
                where a.member.id = :memberId
                  and a.scholarship.year = :year
                  and a.scholarship.semester = :semesterText
                  and a.scholarship.scholarshipName = :scholarshipName
                order by a.id desc
            """)
    List<ScholarshipEntity> findLatestForPosting(
            Long memberId, Integer year, Semester semester, String scholarshipName);
}
