package backend.kg_kuis_server.schedule.service;

import backend.kg_kuis_server.schedule.repository.ScheduleRepository;
import backend.kg_kuis_server.schedule.service.response.ScheduleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository repository;

    private static final ZoneId ZONE = ZoneId.of("Asia/Seoul");

    public List<ScheduleResponse> nextThree() {
        LocalDate today = LocalDate.now(ZONE);
        return repository.findTop3ByEndDateGreaterThanEqualOrderByStartDateAscEndDateAsc(today)
                .stream()
                .map(e -> ScheduleResponse.from(e, today))
                .toList();
    }
}