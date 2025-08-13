package backend.kg_kuis_server.schedule.service.response;

import backend.kg_kuis_server.schedule.repository.entity.ScheduleEntity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public record ScheduleResponse(
        String title,
        String dday
) {
    public static ScheduleResponse from(ScheduleEntity e, LocalDate today) {
        String label;
        if (today.isBefore(e.getStartDate())) {
            long days = ChronoUnit.DAYS.between(today, e.getStartDate());
            label = "D-" + days;
        } else if (!today.isAfter(e.getEndDate())) {
            label = "D-DAY";
        } else {
            long days = ChronoUnit.DAYS.between(e.getEndDate(), today);
            label = "D+" + days;
        }
        return new ScheduleResponse(e.getTitle(), label);
    }
}
