package backend.kg_kuis_server.home.service.dto;

import backend.kg_kuis_server.notice.service.dto.NoticeResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record HomeResponse(
        String nickname,
        List<NoticeResponse> noticeResponses
 ) {}
