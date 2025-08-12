package backend.kg_kuis_server.home.service;

import backend.kg_kuis_server.home.domain.UserList;
import backend.kg_kuis_server.home.service.dto.HomeNoticeResponse;
import backend.kg_kuis_server.home.service.dto.HomeResponse;
import backend.kg_kuis_server.notice.repository.NoticeRepository;
import backend.kg_kuis_server.notice.repository.entity.NoticeEntity;
import backend.kg_kuis_server.notice.service.dto.NoticeResponse;
import backend.kg_kuis_server.notice.service.dto.NoticeSimpleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final NoticeRepository noticeRepository;

    public HomeResponse getHomeData() {
        List<HomeNoticeResponse> notices = noticeRepository.findAllByOrderByPubDateDesc(PageRequest.of(0, 4))
                .stream()
                .map(entity -> new HomeNoticeResponse(
                        entity.getId(),
                        entity.getTitle(),
                        entity.getPubDate()
                ))
                .toList();

        return HomeResponse.builder()
                .nickname(UserList.김건국.getName())
                .build();
    }
}
