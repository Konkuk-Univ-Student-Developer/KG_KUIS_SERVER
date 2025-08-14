INSERT INTO member_entity
(student_no, name, name_en, birth_raw, gender, college, department,
 entrance_year, entrance_semester, admission_type, selection_type,
 curriculum_year, student_type, grade, status)
VALUES ('20250001', '김민준', 'MINJUN KIM', '2001-03-15', 'MALE',
        '공과대학', '컴퓨터공학과', 2020, 'FIRST', '수시', '학생부종합', 2020, '학부', 4, 'ENROLLED'),
       ('20250002', '이서연', 'SEOYEON LEE', '2002-07-21', 'FEMALE',
        '자연과학대학', '화학과', 2021, 'SECOND', '정시', '일반전형', 2021, '학부', 3, 'ENROLLED'),
       ('20250003', '박도윤', 'DOYOON PARK', '2000-11-05', 'MALE',
        '사회과학대학', '경제학과', 2019, 'FIRST', '정시', '일반전형', 2019, '학부', 4, 'LEAVE'),
       ('20250004', '최하은', 'HAEUN CHOI', '2003-02-28', 'FEMALE',
        '인문대학', '국어국문학과', 2022, 'SUMMER', '수시', '학생부교과', 2022, '학부', 2, 'ENROLLED'),
       ('20250005', '정우진', 'WOOJIN JUNG', '1999-09-12', 'MALE',
        '경영대학', '경영학과', 2018, 'WINTER', '편입', '일반편입', 2018, '학부', 4, 'GRADUATED'),
       ('20250006', '한서윤', 'SEOYUN HAN', '2004-05-07', 'FEMALE',
        '예술디자인대학', '시각디자인학과', 2023, 'FIRST', '수시', '실기전형', 2023, '학부', 1, 'ENROLLED');


INSERT INTO schedule_entity (title, start_date, end_date)
VALUES ('2학기 등록금 고지서 출력(16:00 예정)', DATE '2025-08-07', DATE '2025-08-07'),
       ('2학기 등록금 납부 (재학생, 복학생)', DATE '2025-08-18', DATE '2025-08-22'),
       ('후기 학위수여식(15:00)', DATE '2025-08-20', DATE '2025-08-20'),
       ('수강신청', DATE '2025-08-22', DATE '2025-08-29'),
       ('수강신청', DATE '2025-08-23', DATE '2025-08-29'),
       ('수강신청', DATE '2025-08-24', DATE '2025-08-29'),
       ('수강신청', DATE '2025-08-25', DATE '2025-08-29'),
       ('수강신청', DATE '2025-08-26', DATE '2025-08-29'),
       ('수강신청', DATE '2025-08-27', DATE '2025-08-29'),
       ('수강신청', DATE '2025-08-28', DATE '2025-08-29'),
       ('수강신청', DATE '2025-08-29', DATE '2025-08-29');


/*INSERT INTO notices
(category_id, category_name, title, link, pub_date, author, description)
VALUES (234, '학사', '2025-2학기 수강신청 안내', 'https://univ.example/notices/234-20250813-1', '2025-08-13 10:00:00.000', '학사과',
        '수강신청 일정 및 유의사항을 안내합니다.'),
       (234, '학사', '휴학/복학 신청 기간 공지1', 'https://univ.example/notices/234-20250812-1', '2025-08-12 09:30:00.000', '학사과',
        '2025-2학기 휴학 및 복학 신청 기간 안내.'),
       (234, '학사', '휴학/복학 신청 기간 공지2', 'https://univ.example/notices/234-20250812-1', '2025-08-12 09:30:00.000', '학사과',
        '2025-2학기 휴학 및 복학 신청 기간 안내.'),
       (234, '학사', '휴학/복학 신청 기간 공지3', 'https://univ.example/notices/234-20250812-1', '2025-08-12 09:30:00.000', '학사과',
        '2025-2학기 휴학 및 복학 신청 기간 안내.'),
       (234, '학사', '휴학/복학 신청 기간 공지4', 'https://univ.example/notices/234-20250812-1', '2025-08-12 09:30:00.000', '학사과',
        '2025-2학기 휴학 및 복학 신청 기간 안내.'),
       (234, '학사', '휴학/복학 신청 기간 공지5', 'https://univ.example/notices/234-20250812-1', '2025-08-12 09:30:00.000', '학사과',
        '2025-2학기 휴학 및 복학 신청 기간 안내.'),
       (234, '학사', '휴학/복학 신청 기간 공지6', 'https://univ.example/notices/234-20250812-1', '2025-08-12 09:30:00.000', '학사과',
        '2025-2학기 휴학 및 복학 신청 기간 안내.'),
       (234, '학사', '휴학/복학 신청 기간 공지7', 'https://univ.example/notices/234-20250812-1', '2025-08-12 09:30:00.000', '학사과',
        '2025-2학기 휴학 및 복학 신청 기간 안내.'),
       (234, '학사', '휴학/복학 신청 기간 공지8', 'https://univ.example/notices/234-20250812-1', '2025-08-12 09:30:00.000', '학사과',
        '2025-2학기 휴학 및 복학 신청 기간 안내.'),
       (235, '장학', '국가장학금 2차 신청 안내', 'https://univ.example/notices/235-20250811-1', '2025-08-11 14:15:00.000', '장학지원팀',
        '한국장학재단 국가장학금 2차 신청 일정 및 방법.'),
       (235, '장학', '교내 성적우수 장학금 신청', 'https://univ.example/notices/235-20250810-1', '2025-08-10 11:00:00.000', '장학지원팀',
        '성적우수 장학금 신청 자격 및 제출서류 안내.'),
       (237, '국제', '2026-1 교환학생 모집 안내', 'https://univ.example/notices/237-20250809-1', '2025-08-09 16:40:00.000',
        '국제교류팀', '파견대학, 지원자격, 일정 등 상세 안내.'),
       (237, '국제', '어학연수 장학 프로그램', 'https://univ.example/notices/237-20250808-1', '2025-08-08 10:20:00.000', '국제교류팀',
        '방학 중 어학연수 참가자 장학 혜택 공지.'),
       (238, '학생', '동아리 박람회 개최', 'https://univ.example/notices/238-20250807-1', '2025-08-07 13:00:00.000', '학생지원팀',
        '신입 동아리원 모집을 위한 박람회 일정 공지.'),
       (238, '학생', '학생 상담주간 운영', 'https://univ.example/notices/238-20250806-1', '2025-08-06 09:10:00.000', '학생상담센터',
        '학사/진로/심리 상담주간 운영 안내.'),
       (4083, '취창업', '하반기 채용설명회 안내', 'https://univ.example/notices/4083-20250805-1', '2025-08-05 15:30:00.000',
        '취업지원센터', '대기업·공기업 채용설명회 일정 및 장소.'),
       (4083, '취창업', '현직자 멘토링 프로그램 모집', 'https://univ.example/notices/4083-20250804-1', '2025-08-04 17:45:00.000',
        '취업지원센터', '산업별 멘토링 매칭 안내.'),
       (240, '일반', '캠퍼스 정전 안내(예정)', 'https://univ.example/notices/240-20250803-1', '2025-08-03 08:00:00.000', '총무팀',
        '설비 점검으로 인한 일시 정전 안내.'),
       (240, '일반', '캠퍼스 대청소 자원봉사 모집', 'https://univ.example/notices/240-20250802-1', '2025-08-02 12:20:00.000', '학생지원팀',
        '방학 중 환경정화 봉사자 모집.'),
       (4214, '산학', '캡스톤디자인 과제 모집', 'https://univ.example/notices/4214-20250801-1', '2025-08-01 10:05:00.000', '산학협력단',
        '산학 공동 캡스톤 과제 참여팀 모집.'),
       (4214, '산학', '기업 연계 인턴십 안내', 'https://univ.example/notices/4214-20250731-1', '2025-07-31 09:55:00.000', '산학협력단',
        '연계 기업, 지원자격, 선발일정 안내.');*/

INSERT INTO course_entity (course_year, semester, grade, course_category, course_code, course_name, credit,
                           department_name,
                           schedule, professor, course_number, lecture_type, method)
VALUES (2025, 'SECOND', 1, '반교', 'BKSA05021', '컴퓨터공학개론', 3, '상허교양대학', '월04-06(새403), 수04-06(새403)', '윤경로', '0087', '이론',
        '일반'),
       (2025, 'SECOND', 1, '반교', 'BKSA05021', '컴퓨터공학개론', 3, '상허교양대학', '월07-09(새403), 수07-09(새403)', '윤경로', '0088', '이론',
        '일반'),
       (2025, 'SECOND', 2, '전필전선', 'BBAB11792', '객체지향프로그래밍', 3, '컴퓨터공학부', '화13-16(공A1510), 목13-16(공A1510)', '지정희', '3153',
        '이론+실습', '일반'),
       (2025, 'SECOND', 2, '전필전선', 'BBAB11792', '객체지향프로그래밍', 3, '컴퓨터공학부', '월09-12(새502), 수09-12(새502)', '차영운', '3154',
        '이론+실습', '일반'),
       (2025, 'SECOND', 2, '전필전선', 'BBAB12016', '소프트웨어공학', 3, '컴퓨터공학부', '월01-03(공C487), 금01-03(공C487)', '유준범', '3155',
        '이론', '일반'),
       (2025, 'SECOND', 2, '전필전선', 'BBAB12016', '소프트웨어공학', 3, '컴퓨터공학부', '월04-06(공C487), 금04-06(공C487)', '유준범', '3156',
        '이론', '일반'),
       (2025, 'SECOND', 3, '전필전선', 'BBAB12017', '소프트웨어아키텍처', 3, '컴퓨터공학부', '화09-12(신공1214), 목09-12(신공1214)', '민덕기', '3157',
        '이론+실습', '일반'),
       (2025, 'SECOND', 2, '전필전선', 'BBAB12022', '시스템프로그래밍', 3, '컴퓨터공학부', '화04-06(새502), 목04-06(새502)', '김두현', '3158',
        '이론', '일반'),
       (2025, 'SECOND', 2, '전필전선', 'BBAB12022', '시스템프로그래밍', 3, '컴퓨터공학부', '월01-03(공A602), 수01-03(공A602)', '김욱희', '3159',
        '이론', '일반'),
       (2025, 'SECOND', 2, '전필전선', 'BBAB12022', '시스템프로그래밍', 3, '컴퓨터공학부', '월04-06(공B361), 수04-06(공B361)', '김욱희', '3160',
        '이론', '일반'),
       (2025, 'SECOND', 2, '전필전선', 'BBAB12022', '시스템프로그래밍', 3, '컴퓨터공학부', '화07-09(공A602), 목07-09(공A602)', '임민규', '3161',
        '이론', '일반'),
       (2025, 'SECOND', 2, '전필전선', 'BBAB12022', '시스템프로그래밍', 3, '컴퓨터공학부', '화10-12(공A602), 목10-12(공A602)', '임민규', '3162',
        '이론', '일반'),
       (2025, 'SECOND', 2, '전필전선', 'BBAB12023', '알고리즘', 3, '컴퓨터공학부', '월10-12(공A602), 수10-12(공A602)', '이희연', '3163', '이론',
        '일반'),
       (2025, 'SECOND', 2, '전필전선', 'BBAB12023', '알고리즘', 3, '컴퓨터공학부', '월13-15(공A602), 수13-15(공A602)', '이희연, 장현국', '3164',
        '이론', '일반'),
       (2025, 'SECOND', 2, '전필전선', 'BBAB12042', '자료구조', 3, '컴퓨터공학부', '월13-15(공B361), 수13-15(공B361)', '하영국', '3165', '이론',
        'B-러닝(녹화+대면)'),
       (2025, 'SECOND', 3, '전필전선', 'BBAB12050', '컴파일러', 3, '컴퓨터공학부', '화13-18(새401)', '심철준', '3166', '이론', 'B-러닝(녹화+대면)'),
       (2025, 'SECOND', 3, '전필전선', 'BBAB12070', '디지털영상처리', 3, '컴퓨터공학부', '화04-06(공B170), 목04-06(공B170)', '김은이', '3167',
        '이론', '일반'),
       (2025, 'SECOND', 3, '전필전선', 'BBAB12070', '디지털영상처리', 3, '컴퓨터공학부', '화07-09(공B170), 목07-09(공B170)', '김은이', '3168',
        '이론', '일반'),
       (2025, 'SECOND', 4, '전필전선', 'BBAB12112', 'HCI', 3, '컴퓨터공학부', '월10-12(공B352), 수10-12(공B352)', '장현국', '3169', '이론',
        '일반'),
       (2025, 'SECOND', 2, '전필전선', 'BBAB12158', '컴퓨터회로', 3, '컴퓨터공학부', '월03-06(신공1214), 수03-06(신공1214)', '박능수', '3170',
        '이론+실습', 'B-러닝(녹화+대면)'),
       (2025, 'SECOND', 2, '전필전선', 'BBAB12158', '컴퓨터회로', 3, '컴퓨터공학부', '월09-12(공A1510), 수09-12(공A1510)', '홍상우', '3171',
        '이론+실습', '일반'),
       (2025, 'SECOND', 2, '전필전선', 'BBAB12158', '컴퓨터회로', 3, '컴퓨터공학부', '월13-16(공A1510), 수13-16(공A1510)', '홍상우', '3172',
        '이론+실습', '일반'),
       (2025, 'SECOND', 3, '전필전선', 'BBAB49715', '병렬프로그래밍', 3, '컴퓨터공학부', '월10-12(신공1214), 수10-12(신공1214)', '박능수', '3173',
        '이론', 'B-러닝(녹화+대면)'),
       (2025, 'SECOND', 2, '전필전선', 'BBAB51983', '랜덤프로세스개론', 3, '컴퓨터공학부', '화04-06(공C487), 목04-06(공C487)', '임창훈', '3174',
        '이론', '일반'),
       (2025, 'SECOND', 2, '전필전선', 'BBAB51983', '랜덤프로세스개론', 3, '컴퓨터공학부', '화07-09(공C487), 목07-09(공C487)', '임창훈', '3175',
        '이론', '일반'),
       (2025, 'SECOND', 2, '전필전선', 'BBAB53292', '컴퓨테이션이론', 3, '컴퓨터공학부', '화04-06(신공104), 목04-06(신공104)', '남원홍', '3176',
        '이론', '일반'),
       (2025, 'SECOND', 2, '전필전선', 'BBAB53292', '컴퓨테이션이론', 3, '컴퓨터공학부', '화07-09(신공104), 목07-09(신공104)', '남원홍', '3177',
        '이론', '일반'),
       (2025, 'SECOND', 4, '전필전선', 'BBAB54782', '웹기술및응용', 3, '컴퓨터공학부', '월09-12(공B361), 수09-12(공B361)', '하영국', '3178',
        '이론+실습', 'B-러닝(녹화+대면)'),
       (2025, 'SECOND', 4, '전필전선', 'BBAB54855', '차세대분산시스템', 3, '컴퓨터공학부', '화01-04(신공1214), 목01-04(신공1214)', '민덕기', '3179',
        '이론+실습', '일반'),
       (2025, 'SECOND', 4, '전필전선', 'BBAB55840', '졸업프로젝트1(종합설계)', 1, '컴퓨터공학부', '수17-18(미배정)', '김두현', '3180', '실험+실습+실기',
        '일반'),
       (2025, 'SECOND', 4, '전필전선', 'BBAB55840', '졸업프로젝트1(종합설계)', 1, '컴퓨터공학부', '수17-18(미배정)', '김학수', '3181', '실험+실습+실기',
        '일반'),
       (2025, 'SECOND', 4, '전필전선', 'BBAB55840', '졸업프로젝트1(종합설계)', 1, '컴퓨터공학부', '수17-18(미배정)', '박능수', '3182', '실험+실습+실기',
        '일반'),
       (2025, 'SECOND', 4, '전필전선', 'BBAB55840', '졸업프로젝트1(종합설계)', 1, '컴퓨터공학부', '수17-18(미배정)', '신효섭', '3183', '실험+실습+실기',
        '일반'),
       (2025, 'SECOND', 4, '전필전선', 'BBAB55840', '졸업프로젝트1(종합설계)', 1, '컴퓨터공학부', '수17-18(미배정)', '윤경로', '3184', '실험+실습+실기',
        '일반'),
       (2025, 'SECOND', 4, '전필전선', 'BBAB55840', '졸업프로젝트1(종합설계)', 1, '컴퓨터공학부', '수17-18(미배정)', '이향원', '3185', '실험+실습+실기',
        '일반'),
       (2025, 'SECOND', 4, '전필전선', 'BBAB55840', '졸업프로젝트1(종합설계)', 1, '컴퓨터공학부', '수17-18(미배정)', '정갑주', '3186', '실험+실습+실기',
        '일반'),
       (2025, 'SECOND', 4, '전필전선', 'BBAB55840', '졸업프로젝트1(종합설계)', 1, '컴퓨터공학부', '수17-18(미배정)', '하영국', '3187', '실험+실습+실기',
        '일반'),
       (2025, 'SECOND', 4, '전필전선', 'BBAB55841', '졸업프로젝트2(종합설계)', 1, '컴퓨터공학부', '목17-18(미배정)', '김욱희', '3188', '실험+실습+실기',
        '일반'),
       (2025, 'SECOND', 4, '전필전선', 'BBAB55841', '졸업프로젝트2(종합설계)', 1, '컴퓨터공학부', '목17-18(미배정)', '김은이', '3189', '실험+실습+실기',
        '일반'),
       (2025, 'SECOND', 4, '전필전선', 'BBAB55841', '졸업프로젝트2(종합설계)', 1, '컴퓨터공학부', '목17-18(미배정)', '김형석', '3190', '실험+실습+실기',
        '일반'),
       (2025, 'SECOND', 4, '전필전선', 'BBAB55841', '졸업프로젝트2(종합설계)', 1, '컴퓨터공학부', '목17-18(미배정)', '남원홍', '3191', '실험+실습+실기',
        '일반'),
       (2025, 'SECOND', 4, '전필전선', 'BBAB55841', '졸업프로젝트2(종합설계)', 1, '컴퓨터공학부', '목17-18(미배정)', '문창주', '3192', '실험+실습+실기',
        '일반'),
       (2025, 'SECOND', 4, '전필전선', 'BBAB55841', '졸업프로젝트2(종합설계)', 1, '컴퓨터공학부', '목17-18(미배정)', '오병국', '3193', '실험+실습+실기',
        '일반'),
       (2025, 'SECOND', 4, '전필전선', 'BBAB55841', '졸업프로젝트2(종합설계)', 1, '컴퓨터공학부', '목17-18(미배정)', '유준범', '3194', '실험+실습+실기',
        '일반'),
       (2025, 'SECOND', 4, '전필전선', 'BBAB55841', '졸업프로젝트2(종합설계)', 1, '컴퓨터공학부', '목17-18(미배정)', '임민규', '3195', '실험+실습+실기',
        '일반'),
       (2025, 'SECOND', 4, '전필전선', 'BBAB55841', '졸업프로젝트2(종합설계)', 1, '컴퓨터공학부', '목17-18(미배정)', '임창훈', '3196', '실험+실습+실기',
        '일반'),
       (2025, 'SECOND', 4, '전필전선', 'BBAB55841', '졸업프로젝트2(종합설계)', 1, '컴퓨터공학부', '목17-18(미배정)', '지서원', '3197', '실험+실습+실기',
        '일반'),
       (2025, 'SECOND', 4, '전필전선', 'BBAB55841', '졸업프로젝트2(종합설계)', 1, '컴퓨터공학부', '목17-18(미배정)', '차영운', '3198', '실험+실습+실기',
        '일반'),
       (2025, 'SECOND', 4, '전필전선', 'BBAB55841', '졸업프로젝트2(종합설계)', 1, '컴퓨터공학부', '목17-18(미배정)', '홍상우', '3199', '실험+실습+실기',
        '일반'),
       (2025, 'SECOND', 3, '전필전선', 'BBAB59068', '클라우드웹서비스', 3, '컴퓨터공학부', '화15-18(공B361), 목15-18(공B361)', '정갑주', '3200',
        '이론+실습', 'B-러닝(녹화+대면)'),
       (2025, 'SECOND', 3, '전필전선', 'BBAB61087', '데이터사이언스', 3, '컴퓨터공학부', '화04-06(공B361), 목04-06(공B361)', '신효섭', '3201',
        '이론', '일반'),
       (2025, 'SECOND', 3, '전필전선', 'BBAB61087', '데이터사이언스', 3, '컴퓨터공학부', '화10-12(공B361), 목10-12(공B361)', '신효섭', '3202',
        '이론', '일반'),
       (2025, 'SECOND', 3, '전필전선', 'BBAB61087', '데이터사이언스', 3, '컴퓨터공학부', '월13-15(공B352), 수13-15(공B352)', '오병국', '3203',
        '이론', '일반'),
       (2025, 'SECOND', 3, '전필전선', 'BBAB61087', '데이터사이언스', 3, '컴퓨터공학부', '월16-18(공B361), 수16-18(공B361)', '오병국', '3204',
        '이론', '일반'),
       (2025, 'SECOND', 4, '전필전선', 'BBAB62242', '컴퓨터공학세미나', 1, '컴퓨터공학부', '화13-16(공C_B182)', '남원홍', '3205', '이론', '일반'),
       (2025, 'SECOND', 4, '전필전선', 'BBAB62245', '컴퓨터정보보안', 3, '컴퓨터공학부', '월13-15(새502), 수13-15(새502)', '박소영', '3206', '이론',
        'B-러닝(녹화+대면)'),
       (2025, 'SECOND', 3, '전필전선', 'BBAB62250', '고급컴퓨터그래픽스', 3, '컴퓨터공학부', '월05-08(온라인(녹화)), 금01-04(새403)', '김형석', '3207',
        '이론+실습', 'B-러닝(녹화+대면)'),
       (2025, 'SECOND', 3, '전필전선', 'BBAB62735', '인공지능', 3, '컴퓨터공학부', '월01-03(신공104), 수01-03(신공104)', '김은이', '3208', '이론',
        'B-러닝(녹화+대면)'),
       (2025, 'SECOND', 3, '전필전선', 'BBAB62866', '기계학습', 3, '컴퓨터공학부', '화01-03(공A1510), 목01-03(공A1510)', '김학수', '3209',
        '이론', '일반'),
       (2025, 'SECOND', 3, '전필전선', 'BBAB62866', '기계학습', 3, '컴퓨터공학부', '화04-06(공A1510), 목04-06(공A1510)', '김학수', '3210',
        '이론', '일반'),
       (2025, 'SECOND', 3, '전필전선', 'BBAB62866', '기계학습', 3, '컴퓨터공학부', '화15-17(신공1214), 목15-17(신공1214)', '민덕기', '3211',
        '이론', '일반'),
       (2025, 'SECOND', 2, '전필전선', 'BBAB63052', '멀티미디어공학', 3, '컴퓨터공학부', '월07-09(공B475), 수07-09(공B475)', '지서원', '3212',
        '이론', '일반'),
       (2025, 'SECOND', 2, '전필전선', 'BBAB63052', '멀티미디어공학', 3, '컴퓨터공학부', '월10-12(공B475), 수10-12(공B475)', '지서원', '3213',
        '이론', '일반'),
       (2025, 'SECOND', 2, '전필전선', 'BBAB67021', '컴퓨터네트워크1', 3, '컴퓨터공학부', '월01-03(공B475), 수01-03(공B475)', '김기천', '3214',
        '이론', '일반'),
       (2025, 'SECOND', 2, '전필전선', 'BBAB67021', '컴퓨터네트워크1', 3, '컴퓨터공학부', '월04-06(공B475), 수04-06(공B475)', '김기천', '3215',
        '이론', '일반'),
       (2025, 'SECOND', 2, '전필전선', 'BBAB67057', '전공기초프로젝트(종합설계)', 3, '컴퓨터공학부', '화05-08(새403), 금05-08(새403)', '차리서',
        '3216', '실험+실습+실기', '일반'),
       (2025, 'SECOND', 2, '전필전선', 'BBAB67057', '전공기초프로젝트(종합설계)', 3, '컴퓨터공학부', '화09-12(새403), 금09-12(새403)', '차리서',
        '3217', '실험+실습+실기', '일반'),
       (2025, 'SECOND', 3, '전필전선', 'BBAB67059', '전공심화프로젝트(종합설계)', 3, '컴퓨터공학부', '화09-12(새502), 목09-12(새502)', '김두현',
        '3218', '실험+실습+실기', '일반'),
       (2025, 'SECOND', 3, '전필전선', 'BBAB67059', '전공심화프로젝트(종합설계)', 3, '컴퓨터공학부', '화13-16(새502), 목13-16(새502)', '김두현',
        '3219', '실험+실습+실기', '일반');

INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('0238', 'BLAA55562', 2020, 'FIRST', 0, '일선', '전공기초화학', 3, '미지정', 'N/A', '정윤성', '이론', '상대평가');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('1410', 'BKSA11014', 2020, 'FIRST', 0, '일선', '독일작가와문화기행', 3, '미지정', 'N/A', '사지원', '이론', '상대평가');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('0240', 'BLAA55564', 2020, 'FIRST', 0, '일선', '줄기세포재생과학입문', 3, '미지정', 'N/A', '조쌍구', '이론', '절대평가(ABF제)');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('1484', 'BKSA46877', 2020, 'FIRST', 0, '심교', '독립영화와함께하는세상바로보기', 3, '미지정', 'N/A', '김동현', '이론', '상대평가');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('0259', 'BLAA58386', 2020, 'FIRST', 0, '심교', 'CMS진로설계', 3, '미지정', 'N/A', '송혁, 배호재, 홍권호, 박찬규, 조쌍구', '이론',
        'P/N평가');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('0242', 'BLAA55566', 2020, 'FIRST', 0, '일선', '전공기초실험1', 3, '미지정', 'N/A', '조쌍구, 배호재, 홍권호, 송혁, 박찬규', '이론',
        '상대평가(A등급 40%)');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('1622', 'BKSA49597', 2020, 'SECOND', 0, '심교', '초급프랑스어', 3, '미지정', 'N/A', '박하성', '이론', '상대평가');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('0211', 'BLAA55569', 2020, 'SECOND', 0, '일선', '전공기초실험2', 3, '미지정', 'N/A', '홍권호, 조쌍구, 배호재, 박찬규, 송혁', '이론',
        '상대평가(A등급 40%)');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('0212', 'BLAA57503', 2020, 'SECOND', 0, '기타', '세포생화학', 3, '미지정', 'N/A', '배호재', '이론', '상대평가');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('1751', 'BKSA60428', 2020, 'SECOND', 0, '심교', '생활속의뇌과학', 3, '미지정', 'N/A', '정지혜', '이론', '절대평가(ABF제)');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('4230', 'BLFA07594', 2020, 'SECOND', 0, '일선', '동물유전학', 3, '미지정', 'N/A', '박찬규', '이론', '상대평가');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('1331', 'BQAA16970', 2020, 'SECOND', 0, '기교', '대학영어1', 3, '미지정', 'N/A', '나디', '이론', '상대평가');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('1272', 'BKSA56558', 2020, 'SECOND', 0, '심교', '컴퓨팅적사고', 3, '미지정', 'N/A', '오미자', '이론', '절대평가(ABF제)');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('1201', 'BKSA11088', 2020, 'SECOND', 0, '심교', '사회봉사2', 3, '미지정', 'N/A', '박창규', '이론', 'P/N평가');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('0043', 'BBAA57369', 2021, 'FIRST', 0, '전필전선', 'C프로그래밍', 3, '미지정', 'N/A', '박소영', '이론', '상대평가');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('1284', 'BKSA54991', 2021, 'FIRST', 0, '전필전선', '진로설계와직업탐색', 3, '미지정', 'N/A', '미배정', '이론', 'P/N평가');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('1634', 'BKSA49626', 2021, 'FIRST', 0, '일선', '미술의이해', 3, '미지정', 'N/A', '안현정', '이론', '상대평가');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('0360', 'COAA80704', 2021, 'FIRST', 0, '반교', '수학및연습1', 3, '미지정', 'N/A', '이승원', '이론', '상대평가');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('0434', 'COAA82119', 2021, 'FIRST', 0, '반교', '선형대수', 3, '미지정', 'N/A', '이향원', '이론', '상대평가');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('0423', 'COAA81704', 2021, 'FIRST', 0, '일선', '일반화학및실험1', 3, '미지정', 'N/A', '왕한철', '이론', '상대평가');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('3428', 'BBWA12022', 2021, 'SECOND', 0, '전필전선', '시스템프로그래밍', 3, '미지정', 'N/A', '임민규', '이론', '상대평가');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('3440', 'BBWA51983', 2021, 'SECOND', 0, '전필전선', '랜덤프로세스개론', 3, '미지정', 'N/A', '임창훈', '이론', '상대평가');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('3432', 'BBWA12052', 2021, 'SECOND', 0, '전필전선', '컴퓨터구조', 3, '미지정', 'N/A', '박소영', '이론', '상대평가');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('0040', 'BBAA57358', 2021, 'SECOND', 0, '전필전선', 'JAVA프로그래밍', 3, '미지정', 'N/A', '지정희', '이론', '상대평가');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('2041', 'BBWA57404', 2021, 'SECOND', 0, '전필전선', '소프트웨어학과세미나', 3, '미지정', 'N/A', '장현국', '이론', 'P/N평가');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('1260', 'BKSA39798', 2021, 'SECOND', 0, '심교', '비판적사고와토론', 3, '미지정', 'N/A', '김은하', '이론', '상대평가');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('3446', 'BBWA55616', 2021, 'SECOND', 0, '전필전선', 'C++프로그래밍', 3, '미지정', 'N/A', '지정희', '이론', '상대평가');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('3198', 'BBAB12190', 2022, 'FIRST', 0, '전필전선', '운영체제', 3, '미지정', 'N/A', '김두현', '이론', '절대평가');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('3228', 'BBAB62244', 2022, 'FIRST', 0, '전필전선', '전공기초프로젝트1(종합설계)', 3, '미지정', 'N/A', '차리서', '이론', '절대평가(ABF제)');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('3204', 'BBAB51979', 2022, 'FIRST', 0, '전필전선', '모바일프로그래밍', 3, '미지정', 'N/A', '지정희', '이론', '절대평가');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('0446', 'COAA83619', 2022, 'FIRST', 0, '반교', '확률과통계', 3, '미지정', 'N/A', '이향원', '이론', '절대평가');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('3189', 'BBAB12042', 2022, 'FIRST', 0, '전필전선', '자료구조', 3, '미지정', 'N/A', '김성열', '이론', '절대평가');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('3221', 'BBAB59453', 2022, 'FIRST', 0, '전필전선', '컴퓨터구조', 3, '미지정', 'N/A', '박능수', '이론', '절대평가');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('3173', 'BBAB12017', 2022, 'SECOND', 0, '전필전선', '소프트웨어아키텍처', 3, '미지정', 'N/A', '민덕기', '이론', '절대평가(ABF제)');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('3209', 'BBAB62243', 2022, 'SECOND', 0, '전필전선', 'UX디자인', 3, '미지정', 'N/A', '김지인', '이론', '절대평가(ABF제)');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('3206', 'BBAB61223', 2022, 'SECOND', 0, '전필전선', 'K-Lab프로젝트2(종합설계)', 3, '미지정', 'N/A', '이희연, 윤경로', '이론',
        '절대평가(ABF제)');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('3196', 'BBAB55842', 2022, 'SECOND', 0, '전필전선', '산학협력프로젝트2(종합설계)', 3, '미지정', 'N/A', '장현국', '이론', '절대평가(ABF제)');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('3179', 'BBAB12022', 2022, 'SECOND', 0, '전필전선', '알고리즘', 3, '미지정', 'N/A', '김성열', '이론', '상대평가');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('3201', 'BBAB59363', 2022, 'SECOND', 0, '전필전선', '네트워크프로그래밍', 3, '미지정', 'N/A', '이향원', '이론', '상대평가');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('8073', 'BKUA65019', 2022, 'WINTER', 0, '전필전선', '국제단기프로그램6', 3, '미지정', 'N/A', '미배정', '이론', 'P/N평가');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('1552', 'BKSA11433', 2023, 'SECOND', 0, '전필전선', 'TECHNICAL COMMUNICATION', 3, '미지정', 'N/A', '이종연', '이론', '절대평가');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('1590', 'BKSA53751', 2023, 'SECOND', 0, '심교', '클래식음악여행', 3, '미지정', 'N/A', '권동현', '이론', '상대평가');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('3180', 'BBAB53292', 2023, 'SECOND', 0, '전필전선', '컴퓨테이션이론', 3, '미지정', 'N/A', '남원홍', '이론', '상대평가');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('3183', 'BBAB55840', 2023, 'SECOND', 0, '전필전선', '졸업프로젝트1(종합설계)', 3, '미지정', 'N/A', '정갑주', '이론', '절대평가(ABF제)');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('3205', 'BBAB62247', 2023, 'SECOND', 0, '전필전선', '전공기초프로젝트2(종합설계)', 3, '미지정', 'N/A', '차리서', '이론', '절대평가(ABF제)');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('3203', 'BBAB62245', 2023, 'SECOND', 0, '전필전선', '컴퓨터정보보안', 3, '미지정', 'N/A', '박소영', '이론', '절대평가(ABF제)');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('1295', 'BKSA54994', 2024, 'FIRST', 0, '전필전선', '실전취업특강', 3, '미지정', 'N/A', '미배정', '이론', 'P/N평가');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('3206', 'BBAB55841', 2024, 'FIRST', 0, '전필전선', '졸업프로젝트2(종합설계)', 3, '미지정', 'N/A', '정갑주', '이론', '절대평가(ABF제)');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('1531', 'BKSA11201', 2024, 'FIRST', 0, '심교', '야외스포츠(골프)', 3, '미지정', 'N/A', '신석환', '이론', '상대평가');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('1561', 'BKSA45302', 2024, 'FIRST', 0, '심교', '서울의역사와문화', 3, '미지정', 'N/A', '김형근', '이론', '상대평가(A등급 40%)');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('3202', 'BBAB55836', 2024, 'FIRST', 0, '전필전선', '산학협력프로젝트1(종합설계)', 3, '미지정', 'N/A', '김재군', '이론', '절대평가(ABF제)');
INSERT INTO course_entity (course_number, course_code, course_year, semester, grade, course_category, course_name,
                           credit, department_name, schedule, professor, lecture_type, method)
VALUES ('1598', 'BKSA55584', 2024, 'FIRST', 0, '심교', '요가와명상', 3, '미지정', 'N/A', '정경화', '이론', '상대평가');

INSERT INTO member_academy (member_id,
                            total_earned_credits,
                            required_credits,
                            gpa,
                            gpa_scale,
                            pass_status,
                            early_graduation,
                            honor)
VALUES (1, -- MemberEntity.id
        96, -- 총 취득학점
        130, -- 졸업 필요학점
        4.25, -- 전체 평점(BigDecimal)
        4.50, -- 스케일(BigDecimal)
        '합격', -- 합격/진행 상태 요약(예: IN_PROGRESS, PASSED 등)
        FALSE, -- 조기졸업 여부
        TRUE -- 우등 여부
       );

INSERT INTO member_major (member_id,
                          program_type,
                          major_name,
                          college_name,
                          applied_year,
                          applied_semester,
                          thesis_type,
                          thesis_title,
                          passed)
VALUES (1, '원전공', '컴퓨터공학부', '공과대학', 2022, 'FIRST', '없음', NULL, 'N'),
       (1, '복수전공', '컴퓨터공학부', '공과대학', 2022, 'FIRST', '없음', NULL, 'N');


INSERT INTO graduation_requirement(scope,
                                   major_name,
                                   title,
                                   criterion_value,
                                   description)
VALUES ('학사', '컴퓨터공학부', '교양학점(영역별 이수학점 및 총 이수학점 조건)', 12, '-'),
       ('전공', '컴퓨터공학부', '교양학점(영역별 이수학점 및 총 이수학점 조건)', 12, '-');


INSERT INTO member_graduation(member_id,
                              graduation_id,
                              subject)
VALUES (1, 1, 12),
       (1, 2, 12);


INSERT INTO member_grade (member_id, course_id, letter_grade, attendance, midterm, final_exam, assignment, project,
                          quiz, presentation, discussion, etc5)
VALUES (1, 70, 'B', 50, 72, 80, 49, 0, 0, 0, 0, 0),
       (1, 71, 'B', 10, 15, 16, 0, 0, 30, 0, 0, 0),
       (1, 72, 'A+', 0, 0, 0, 0, 0, 0, 0, 0, 95),
       (1, 73, 'C+', 14, 20, 33, 10, 0, 0, 0, 0, 0),
       (1, 74, 'P', 0, 0, 0, 0, 0, 0, 0, 75, 0),
       (1, 75, 'B+', 20, 0, 0, 42, 22, 0, 0, 0, 0),
       (1, 76, 'B+', 14, 29, 29, 15, 0, 0, 0, 0, 0),
       (1, 77, 'B+', 100, 78, 79, 96, 0, 0, 0, 0, 0),
       (1, 78, 'B+', 0, 40, 45, 100, 0, 0, 0, 0, 0),
       (1, 79, 'A', 95, 100, 74, 18, 0, 0, 0, 28, 10),
       (1, 80, 'C+', 14, 17, 24, 14, 0, 0, 0, 0, 0),
       (1, 81, 'A', 8, 99, 88, 65, 47, 24, 43, 46, 0),
       (1, 82, 'A', 29, 23, 23, 19, 0, 0, 0, 0, 0),
       (1, 83, 'P', 0, 0, 0, 0, 0, 0, 0, 0, 100),
       (1, 84, 'C+', 80, 55, 25, 76, 0, 100, 0, 0, 0),
       (1, 85, 'P', 100, 100, 100, 100, 0, 0, 0, 0, 0),
       (1, 86, 'A', 15, 29, 28, 15, 0, 0, 0, 0, 0),
       (1, 87, 'C+', 20, 58, 46, 10, 0, 0, 0, 0, 0),
       (1, 88, 'B+', 10, 31, 20, 15, 0, 0, 0, 0, 0),
       (1, 89, 'B+', 10, 30, 15, 0, 17, 0, 0, 0, 0),
       (1, 90, 'B+', 10, 13, 14, 20, 0, 0, 0, 0, 0),
       (1, 91, 'C', 20, 36, 32, 46, 0, 0, 0, 0, 6),
       (1, 92, 'C+', 100, 72, 48, 95, 0, 0, 0, 0, 0),
       (1, 93, 'C+', 10, 38, 31, 10, 0, 0, 13, 0, 0),
       (1, 94, 'P', 97, 91, 86, 0, 0, 0, 0, 0, 0),
       (1, 95, 'B+', 4, 23, 20, 10, 14, 0, 0, 0, 0),
       (1, 96, 'C', 9, 30, 5, 7, 0, 0, 17, 0, 0),
       (1, 97, 'A', 10, 0, 37, 28, 0, 0, 0, 0, 0),
       (1, 98, 'A+', 10, 10, 10, 10, 0, 0, 58, 0, 0),
       (1, 99, 'B', 5, 0, 0, 35, 25, 0, 0, 0, 18),
       (1, 100, 'B+', 15, 16, 20, 25, 0, 0, 0, 0, 0),
       (1, 101, 'B+', 100, 0, 47, 100, 0, 0, 0, 0, 0),
       (1, 102, 'C+', 10, 0, 0, 32, 0, 0, 0, 0, 0),
       (1, 103, 'A+', 10, 32, 35, 15, 0, 0, 0, 0, 0),
       (1, 104, 'A+', 10, 24, 30, 30, 0, 0, 0, 0, 0),
       (1, 105, 'A+', 20, 0, 0, 15, 65, 0, 0, 0, 0),
       (1, 106, 'A+', 98, 95, 96, 95, 0, 0, 0, 0, 0),
       (1, 107, 'C', 100, 14, 2, 40, 0, 0, 0, 0, 0),
       (1, 108, 'B+', 10, 12, 30, 17, 0, 0, 0, 0, 0),
       (1, 109, 'P', 100, 100, 100, 100, 0, 0, 0, 0, 0),
       (1, 110, 'A', 14, 0, 35, 20, 96, 2, 2, 1, 0),
       (1, 111, 'B+', 18, 15, 46, 0, 0, 0, 0, 0, 0),
       (1, 112, 'B+', 32, 90, 55, 100, 0, 0, 0, 0, 0),
       (1, 113, 'A', 10, 0, 0, 0, 0, 0, 83, 0, 0),
       (1, 114, 'A+', 10, 10, 10, 10, 0, 0, 58, 0, 0),
       (1, 115, 'B+', 80, 82, 78, 77, 0, 0, 0, 0, 0),
       (1, 116, 'P', 100, 100, 100, 100, 0, 0, 0, 0, 0),
       (1, 117, 'A+', 9, 0, 0, 0, 0, 0, 87, 0, 0),
       (1, 118, 'B+', 100, 93, 96, 96, 0, 0, 0, 0, 0),
       (1, 119, 'A+', 10, 32, 39, 15, 0, 0, 0, 0, 0),
       (1, 120, 'A', 19, 0, 0, 20, 0, 0, 53, 0, 0),
       (1, 121, 'A+', 18, 30, 30, 10, 0, 10, 0, 0, 0);

INSERT INTO graduation_credit_requirement (major_type, category, required_credits)
VALUES ('원전공', '기교', 15),
       ('원전공', '심교', 12),
       ('원전공', '반교', 0),
       ('원전공', '지교', 0),
       ('원전공', '지필', 0),
       ('원전공', '전필', 0),
       ('원전공', '전필전선', 72),
       ('원전공', '전기', 0),
       ('원전공', '일선', 0),
       ('원전공', '기타', 0);

INSERT INTO subject_entity (name, classification)
VALUES ('요가와명상', '학문소양및인성함양'),
       ('서울의역사와문화', '학문소양및인성함양'),
       ('야외스포츠(골프)', '학문소양및인성함양'),
       ('실전취업특강', '취창업'),
       ('클래식음악여행', '사고력증진'),
       ('비판적사고와토론', '글쓰기'),
       ('미술의이해', '사고력증진'),
       ('진로설계와직업탐색', '취창업'),
       ('사회봉사2', '인성'),
       ('컴퓨팅적사고', 'SW'),
       ('대학영어1', '외국어'),
       ('초급프랑스어', '외국어'),
       ('CMS진로설계', '취창업'),
       ('독일작가와문화기행', '글로벌인재양성');

INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2022, 'FIRST', '004689', '가송재단장학(생활비성)', 711, '20240712', '0900', '20240722', '0900', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1131153/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2022, 'FIRST', '005572', '동산장학회', 721, '20240805', '1500', '20240816', '0900', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1132719/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2022, 'FIRST', '003676', '롯데장학(별도회계)', NULL, '20240919', '1300', '20240925', '1300', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1135544/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2022, 'FIRST', '004958', '류호산장학회(별도회계,생활비)', 711, '20250106', '1200', '20250113', '1000', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1141499/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2022, 'FIRST', '005550', '백운백합재단', 711, '20250114', '1000', '20250204', '1000', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1141897/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2022, 'FIRST', '000937', '삼공장학재단', 711, '20250212', '1700', '20250217', '1000', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1143387/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2022, 'FIRST', '000961', '성보장학(생활비)', 710, '20250212', '1000', '20250219', '1000', NULL);
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2022, 'FIRST', '005556', '세계한민족여성재단(별도회계)', 721, '20240716', '1000', '20240723', '0900', NULL);
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2022, 'FIRST', '005459', '수림재단', 300, '20250327', '1700', '20250416', '1000', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1147339/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2022, 'FIRST', '004360', '안중근의사숭모회(별도회계)', 721, '20240906', '1000', '20240919', '0900', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1134868/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2022, 'FIRST', '005308', '재단법인 선현', 711, '20250114', '1400', '20250117', '1000', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1141961/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2022, 'FIRST', '005230', '청소년교육(국고)(4월)', NULL, '20250310', '0900', '20250323', '2359', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1145857/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2022, 'FIRST', '005724', '하영호장학회', 711, '20250227', '1500', '20250304', '1000', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1145036/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2024, 'FIRST', '004689', '가송재단장학(생활비성)', 711, '20240712', '0900', '20240722', '0900', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1131153/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2024, 'FIRST', '005572', '동산장학회', 721, '20240805', '1500', '20240816', '0900', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1132719/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2024, 'FIRST', '003676', '롯데장학(별도회계)', NULL, '20240919', '1300', '20240925', '1300', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1135544/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2024, 'FIRST', '005550', '백운백합재단', 711, '20250114', '1000', '20250204', '1000', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1141897/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2024, 'FIRST', '000937', '삼공장학재단', 711, '20250212', '1700', '20250217', '1000', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1143387/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2024, 'FIRST', '000961', '성보장학(생활비)', 710, '20250212', '1000', '20250219', '1000', NULL);
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2024, 'FIRST', '005556', '세계한민족여성재단(별도회계)', 721, '20240716', '1000', '20240723', '0900', NULL);
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2024, 'FIRST', '004360', '안중근의사숭모회(별도회계)', 721, '20240906', '1000', '20240919', '0900', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1134868/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2024, 'FIRST', '005724', '하영호장학회', 711, '20250227', '1500', '20250304', '1000', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1145036/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2024, 'SECOND', '004822', '(기금)일목 정태영 장학', 906, '20241016', '0900', '20241029', '2359', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1137010/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2024, 'SECOND', '004689', '가송재단장학(생활비성)', 711, '20240712', '0900', '20240722', '0900', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1131153/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2024, 'SECOND', '000428', '건국가족장학', NULL, '20241001', '0900', '20241018', '2359', NULL);
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2024, 'SECOND', '001570', '관정이종환교육재단(별도회계)', NULL, '20241114', '1300', '20241202', '0900', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1139111/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2024, 'SECOND', '005572', '동산장학회', 721, '20240805', '1500', '20240816', '0900', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1132719/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2024, 'SECOND', '003676', '롯데장학(별도회계)', NULL, '20240919', '1300', '20240925', '1300', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1135544/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2024, 'SECOND', '005654', '롯데장학관(별도회계)', NULL, '20241028', '1300', '20241121', '1300', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1137955/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2024, 'SECOND', '000927', '복지장학', NULL, '20241028', '0900', '20241104', '1700', NULL);
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2024, 'SECOND', '005556', '세계한민족여성재단(별도회계)', 721, '20240716', '1000', '20240723', '0900', NULL);
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2024, 'SECOND', '004360', '안중근의사숭모회(별도회계)', 721, '20240906', '1000', '20240919', '0900', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1134868/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2024, 'SECOND', '000449', '우덕재단', NULL, '20240827', '1100', '20240903', '1500', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1134112/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2024, 'SECOND', '004886', '운해장학재단(별도,생활,사설)', 711, '20241127', '0900', '20241211', '0900', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1139695/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2024, 'SECOND', '002793', '푸른등대(삼성/생활비성)', 779, '20241108', '1300', '20241114', '0900', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1138745/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2024, 'SECOND', '000925', '해성문화', 711, '20240711', '1500', '20240730', '0900', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1131330/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '002799', '(기금)기독장학_앤더슨장학회', 750, '20250306', '0900', '20250311', '2359', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1145400/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '003155', '(기금)유자은이사장특별장학금', 900, '20250429', '1000', '20250620', '1700', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1149598/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '004822', '(기금)일목 정태영 장학', 906, '20250610', '0900', '20250616', '2359', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1151479/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '004774', 'KEC과학교육재단', 711, '20250407', '1500', '20250414', '1000', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1148081/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '004689', '가송재단장학(생활비성)', 711, '20240712', '0900', '20240722', '0900', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1131153/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '001516', '가헌신도재단(구,가헌신도리코)', 775, '20250310', '1700', '20250312', '1300', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1145853/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '000428', '건국가족장학', NULL, '20250401', '0900', '20250425', '1729', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1146904/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '005759', '광진복지재단(별도회계,생활비)', 721, '20250414', '1600', '20250421', '0900', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1148572/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '005572', '동산장학회', 721, '20240805', '1500', '20240816', '0900', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1132719/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '003676', '롯데장학(별도회계)', NULL, '20240919', '1300', '20240925', '1300', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1135544/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '003796', '롯데장학(생활비)', 711, '20250602', '1300', '20250630', '0900', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1151292/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '004958', '류호산장학회(별도회계,생활비)', 711, '20250106', '1200', '20250113', '1000', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1141499/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '005550', '백운백합재단', 711, '20250114', '1000', '20250204', '1000', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1141897/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '000927', '복지장학', NULL, '20250428', '0900', '20250507', '1659', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1149366/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '000937', '삼공장학재단', 711, '20250212', '1700', '20250217', '1000', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1143387/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '000961', '성보장학(생활비)', 710, '20250212', '1000', '20250219', '1000', NULL);
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '005556', '세계한민족여성재단(별도회계)', 721, '20240716', '1000', '20240723', '0900', NULL);
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '005459', '수림재단', 300, '20250327', '1700', '20250416', '1000', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1147339/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '004360', '안중근의사숭모회(별도회계)', 721, '20240906', '1000', '20240919', '0900', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1134868/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '004501', '예술체육비전(전공탐색)', 803, '20250328', '0900', '20250413', '2359', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1147434/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '002648', '예술체육비전(전공확립)', 803, '20250328', '0900', '20250413', '2359', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1147434/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '001276', '우강장학재단(별도회계)', NULL, '20250423', '1700', '20250502', '0900', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1149159/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '003218', '우덕', 711, '20250304', '1500', '20250307', '1300', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1145297/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '001573', '유당장학재단(별도,등록,사설,서울)', 770, '20250117', '1600', '20250123', '1000', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1142169/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '002745', '인문100년(전공탐색)장학금', 803, '20250328', '0900', '20250413', '2359', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1147434/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '003751', '인문100년(전공확립)장학금', 803, '20250328', '0900', '20250413', '2359', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1147434/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '000616', '인촌', 772, '20250124', '1500', '20250131', '1000', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1142484/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '004584', '인탑스평산장학(별도,생활)', 781, '20250327', '1000', '20250402', '1300', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1147333/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '005291', '재단법인 동양이엔피장학재단', 720, '20250512', '1400', '20250516', '0900', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1150083/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '005308', '재단법인 선현', 711, '20250114', '1400', '20250117', '1000', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1141961/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '005230', '청소년교육(국고)(4월)', NULL, '20250310', '0900', '20250323', '2359', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1145857/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '002793', '푸른등대(삼성/생활비성)', 779, '20250414', '1300', '20250421', '0900', 'https://www.konkuk.ac.kr/konkuk/2239/subview.do?enc=Zm5jdDF8QEB8JTJGYmJzJTJGa29ua3VrJTJGMjM1JTJGMTE0ODUzMCUyRmFydGNsVmlldy5kbyUzRnBhZ2UlM0QxJTI2c3JjaENvbHVtbiUzRCUyNnNyY2hXcmQlM0QlMjZiYnNDbFNlcSUzRCUyNmJic09wZW5XcmRTZXElM0QlMjZyZ3NCZ25kZVN0ciUzRCUyNnJnc0VuZGRlU3RyJTNEJTI2aXNWaWV3TWluZSUzRGZhbHNlJTI2cGFzc3dvcmQlM0QlMjY%3D');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '000720', '하림장학재단', 778, '20250217', '1500', '20250219', '1300', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1143693/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '005724', '하영호장학회', 711, '20250227', '1500', '20250304', '1000', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1145036/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '000605', '한국지도자육성장학재단', 711, '20250106', '1200', '20250115', '1000', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1141509/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '000925', '해성문화', 774, '20250110', '1000', '20250124', '1000', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1141685/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'FIRST', '001850', '흥한재단장학', 777, '20250205', '1700', '20250211', '1300', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1142903/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '002799', '(기금)기독장학_앤더슨장학회', 750, '20250306', '0900', '20250311', '2359', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1145400/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '003155', '(기금)유자은이사장특별장학금', 900, '20250429', '1000', '20250620', '1700', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1149598/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '004822', '(기금)일목 정태영 장학', 906, '20250610', '0900', '20250616', '2359', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1151479/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '004774', 'KEC과학교육재단', 711, '20250407', '1500', '20250414', '1000', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1148081/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '004689', '가송재단장학(생활비성)', 711, '20250715', '1000', '20250725', '1000', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1153863/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '001516', '가헌신도재단(구,가헌신도리코)', 775, '20250310', '1700', '20250312', '1300', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1145853/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '000428', '건국가족장학', NULL, '20250401', '0900', '20250425', '1729', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1146904/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '005759', '광진복지재단(별도회계,생활비)', 721, '20250414', '1600', '20250421', '0900', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1148572/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '005572', '동산장학회', 721, '20240805', '1500', '20240816', '0900', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1132719/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '003676', '롯데장학(별도회계)', NULL, '20240919', '1300', '20240925', '1300', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1135544/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '003796', '롯데장학(생활비)', 711, '20250602', '1300', '20250630', '0900', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1151292/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '005654', '롯데장학관(별도회계)', NULL, '20250707', '1500', '20250715', '0900', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1153476/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '004958', '류호산장학회(별도회계,생활비)', 711, '20250106', '1200', '20250113', '1000', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1141499/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '005550', '백운백합재단', 711, '20250114', '1000', '20250204', '1000', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1141897/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '000927', '복지장학', NULL, '20250428', '0900', '20250507', '1659', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1149366/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '004600', '부남장학(별도,생활,사설)', NULL, '20250715', '1000', '20250721', '1000', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1153861/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '000937', '삼공장학재단', 711, '20250212', '1700', '20250217', '1000', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1143387/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '000961', '성보장학(생활비)', 710, '20250212', '1000', '20250219', '1000', NULL);
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '005556', '세계한민족여성재단(별도회계)', 721, '20250709', '1600', '20250716', '1000', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1153666/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '005459', '수림재단', 300, '20250327', '1700', '20250416', '1000', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1147339/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '004360', '안중근의사숭모회(별도회계)', 721, '20240906', '1000', '20240919', '0900', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1134868/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '002771', '연재장학재단', 773, '20250715', '1000', '20250723', '1000', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1153857/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '004501', '예술체육비전(전공탐색)', 803, '20250328', '0900', '20250413', '2359', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1147434/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '002648', '예술체육비전(전공확립)', 803, '20250328', '0900', '20250413', '2359', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1147434/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '001276', '우강장학재단(별도회계)', NULL, '20250423', '1700', '20250502', '0900', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1149159/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '003218', '우덕', 711, '20250304', '1500', '20250307', '1300', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1145297/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '001573', '유당장학재단(별도,등록,사설,서울)', 770, '20250117', '1600', '20250123', '1000', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1142169/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '002745', '인문100년(전공탐색)장학금', 803, '20250328', '0900', '20250413', '2359', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1147434/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '003751', '인문100년(전공확립)장학금', 803, '20250328', '0900', '20250413', '2359', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1147434/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '000616', '인촌', 772, '20250124', '1500', '20250131', '1000', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1142484/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '004584', '인탑스평산장학(별도,생활)', 781, '20250327', '1000', '20250402', '1300', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1147333/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '005291', '재단법인 동양이엔피장학재단', 720, '20250512', '1400', '20250516', '0900', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1150083/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '005308', '재단법인 선현', 711, '20250709', '1400', '20250715', '1000', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1153662/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '005230', '청소년교육(국고)(4월)', NULL, '20250310', '0900', '20250323', '2359', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1145857/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '002793', '푸른등대(삼성/생활비성)', 779, '20250414', '1300', '20250421', '0900', 'https://www.konkuk.ac.kr/konkuk/2239/subview.do?enc=Zm5jdDF8QEB8JTJGYmJzJTJGa29ua3VrJTJGMjM1JTJGMTE0ODUzMCUyRmFydGNsVmlldy5kbyUzRnBhZ2UlM0QxJTI2c3JjaENvbHVtbiUzRCUyNnNyY2hXcmQlM0QlMjZiYnNDbFNlcSUzRCUyNmJic09wZW5XcmRTZXElM0QlMjZyZ3NCZ25kZVN0ciUzRCUyNnJnc0VuZGRlU3RyJTNEJTI2aXNWaWV3TWluZSUzRGZhbHNlJTI2cGFzc3dvcmQlM0QlMjY%3D');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '000720', '하림장학재단', 778, '20250217', '1500', '20250219', '1300', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1143693/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '005724', '하영호장학회', 711, '20250227', '1500', '20250304', '1000', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1145036/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '000605', '한국지도자육성장학재단', 711, '20250106', '1200', '20250115', '1000', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1141509/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '000925', '해성문화', 774, '20250715', '1000', '20250723', '1000', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1153858/artclView.do?layout=unknown');
INSERT INTO scholarships (academic_year, semester, scholarship_code, scholarship_name, round_no, apply_start_date, apply_start_time, apply_end_date, apply_end_time, notice) VALUES (2025, 'SECOND', '001850', '흥한재단장학', 777, '20250205', '1700', '20250211', '1300', 'https://www.konkuk.ac.kr/bbs/konkuk/235/1142903/artclView.do?layout=unknown');


INSERT INTO scholarship_applications (
    member_id, scholarship_id, application_date, application_status, rejection_reason
) VALUES
      (1, 1, '2025-08-13', '신청', NULL),
      (1, 2, '2025-08-12', '신청', NULL),
      (1, 3, '2025-08-10', '탈락', '서류 미비');

INSERT INTO scholarship_disbursements (
    member_id, academic_year, semester, scholarship_name,
    admission_amount, tuition_amount, flat_amount, payment_date
) VALUES
      (1, 2023, 'FIRST',  '가송재단장학(생활비성)',  500000,  NULL,      NULL,      '2023-03-10'),
      (1, 2023, 'SECOND', '동산장학회',             NULL,     1500000,  NULL,      '2023-09-01'),
      (1, 2024, 'FIRST',  '롯데장학(별도회계)',      NULL,     NULL,     800000,   '2024-03-05'),
      (1, 2024, 'SECOND', '백운백합재단',           NULL,     1200000,  NULL,      '2024-09-03'),
      (1, 2024, 'FIRST',  '삼공장학재단',           300000,   700000,   NULL,      '2024-04-15'),
      (1, 2025, 'FIRST',  '성보장학(생활비)',       NULL,     NULL,     500000,   '2025-03-02'),
      (1, 2025, 'SECOND', '하영호장학회',           250000,   NULL,     NULL,      '2025-09-07'),
      (1, 2025, 'FIRST',  'KEC과학교육재단',        NULL,     2000000,  NULL,      '2025-03-25'),
      (1, 2025, 'SECOND', '유당장학재단',           NULL,     1800000,  NULL,      '2025-10-05'),
      (1, 2025, 'FIRST',  '해성문화',               400000,   NULL,     NULL,      '2025-04-18');
