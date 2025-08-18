package backend.kg_kuis_server.course.service;// src/main/java/com/example/lecture/service/LecturePlanService.java

import backend.kg_kuis_server.course.domain.LecturePlan;
import backend.kg_kuis_server.course.repository.*;
import com.example.lecture.api.dto.LecturePlanResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class LecturePlanService {

    private final LecturePlanRepository lecturePlanRepository;
    private final EvaluationItemRepository evaluationItemRepository;
    private final BookRepository bookRepository;
    private final AssignmentRepository assignmentRepository;
    private final WeeklyPlanRepository weeklyPlanRepository;

    public LecturePlanService(LecturePlanRepository lecturePlanRepository,
                              EvaluationItemRepository evaluationItemRepository,
                              BookRepository bookRepository,
                              AssignmentRepository assignmentRepository,
                              WeeklyPlanRepository weeklyPlanRepository) {
        this.lecturePlanRepository = lecturePlanRepository;
        this.evaluationItemRepository = evaluationItemRepository;
        this.bookRepository = bookRepository;
        this.assignmentRepository = assignmentRepository;
        this.weeklyPlanRepository = weeklyPlanRepository;
    }

    public LecturePlanResponse getBySubjectCode(String code4) {
        LecturePlan lp = lecturePlanRepository
                .findTopBySbjtIdOrderByIdDesc(code4)
                .orElse(null);
        if (lp == null) return null;

        var resp = new LecturePlanResponse();

        // subjectInfo
        var si = new LecturePlanResponse.SubjectInfo();
        si.sbjt_id = lp.getSbjtId();
        si.subject_name = lp.getSubjectName();
        si.subject_name_eng = lp.getSubjectNameEng();
        si.notes = lp.getNotes();
        si.goal = lp.getGoal();
        resp.subjectInfo = si;

        // evaluation
        resp.evaluation = evaluationItemRepository.findByLecturePlanIdOrderById(lp.getId())
                .stream().map(e -> {
                    var dto = new LecturePlanResponse.Evaluation();
                    dto.item_name = e.getItemName();
                    dto.ratio = e.getRatio();
                    dto.full_score = e.getFullScore();
                    dto.is_public = e.getIsPublic();
                    return dto;
                }).collect(Collectors.toList());

        // books
        resp.books = bookRepository.findByLecturePlanIdOrderById(lp.getId())
                .stream().map(b -> {
                    var dto = new LecturePlanResponse.Book();
                    dto.type = b.getType();
                    dto.title = b.getTitle();
                    dto.author = b.getAuthor();
                    dto.publisher = b.getPublisher();
                    dto.year = b.getYear();
                    return dto;
                }).collect(Collectors.toList());

        // assignments
        resp.assignments = assignmentRepository.findByLecturePlanIdOrderById(lp.getId())
                .stream().map(a -> {
                    var dto = new LecturePlanResponse.Assignment();
                    dto.title = a.getTitle();
                    dto.due_date = a.getDueDate();
                    dto.method = a.getMethod();
                    return dto;
                }).collect(Collectors.toList());

        // weeklyPlans
        resp.weeklyPlans = weeklyPlanRepository.findByLecturePlanIdOrderByWeekAscIdAsc(lp.getId())
                .stream().map(w -> {
                    var dto = new LecturePlanResponse.WeeklyPlan();
                    dto.week = w.getWeek();
                    dto.period = w.getPeriod();
                    dto.topic = w.getTopic();
                    dto.content = w.getContent();
                    dto.type = w.getType();
                    dto.activity = w.getActivity();
                    dto.instructor = w.getInstructor();
                    return dto;
                }).collect(Collectors.toList());

        return resp;
    }
}
