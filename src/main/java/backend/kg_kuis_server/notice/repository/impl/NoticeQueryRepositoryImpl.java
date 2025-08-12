package backend.kg_kuis_server.notice.repository.impl;

import backend.kg_kuis_server.notice.repository.entity.NoticeEntity;
import backend.kg_kuis_server.notice.repository.entity.QNoticeEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NoticeQueryRepositoryImpl {
    private final JPAQueryFactory queryFactory;

    public Page<NoticeEntity> searchByTitleAndCategory(String keyword, Integer categoryId, Pageable pageable) {
        QNoticeEntity notice = QNoticeEntity.noticeEntity;

        BooleanBuilder builder = new BooleanBuilder();
        if (keyword != null && !keyword.isBlank()) {
            builder.and(notice.title.containsIgnoreCase(keyword));
        }
        if (categoryId != null) {
            builder.and(notice.categoryId.eq(categoryId));
        }

        List<NoticeEntity> content = queryFactory
                .selectFrom(notice)
                .where(builder)
                .orderBy(notice.pubDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(notice.count())
                .from(notice)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }

}
