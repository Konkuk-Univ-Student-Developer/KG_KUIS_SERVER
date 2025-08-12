package backend.kg_kuis_server.member.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Semester {
    FIRST(1), SUMMER(2), SECOND(3), WINTER(4);

    private final Integer semester;
}
