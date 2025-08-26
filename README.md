## Architecture V2 (마이그레이션)
<img width="999" alt="image" src="https://github.com/user-attachments/assets/e9df4d0f-abb9-402d-9850-9eeda1643e20" />

## 챗봇 로직
<img width="999" alt="image" src="https://github.com/user-attachments/assets/21d0f95a-165a-4dfb-a0f6-c40a94cb6a7b" />

## 챗봇 가능 범위
<img width="999" alt="image" src="https://github.com/user-attachments/assets/874835f8-9143-4786-8240-d0abc1e81a9c" /> 

## 개선 과정
프로젝트에 있어, 자세한 개선 과정은 [(포트폴리오 링크 -> 학사정보시스템)](https://adaptable-leaf-eea.notion.site/ver-2-251f4c0a88dc80aa940df6de26b4df5d?source=copy_link)에서 확인 가능합니다.

### Amazon SQS를 이용한 Lambda 동시성 처리
<img width="720" height="360" alt="image" src="https://github.com/user-attachments/assets/5b6e9027-9b38-4038-8d81-d2d7dbd52422" />

### 상황
건국대학교 강의계획서 데이터를 대량으로 크롤링해야 했습니다.<br>
단일 Lambda 또는 서버에서 순차적으로 크롤링할 경우,
**25분 이상 소요**되었고, 대량의 요청이 몰리면서 **서버 자원 고갈 및 장애 위험**이 발생했습니다.

### 실행

**Amazon SQS + AWS Lambda 기반 병렬 크롤링 파이프라인 구성**
- **작업 단위 분할**
    강의계획서 데이터를 학과/과목별로 분리하여 **SQS 큐에 메시지 형태로 발행**했습니다.
- **Lambda 동시성 처리**
    SQS 이벤트를 트리거로 Lambda 함수가 병렬 실행되어, 각 메시지(과목 단위)를 독립적으로 크롤링하도록 구성했습니다.
- **S3 임시 저장소 활용**
    크롤링된 결과는 즉시 서버로 보내지 않고 **S3 버킷에 저장** 후, 서버가 이를 배치 처리 방식으로 읽어 안정적으로 DB에 반영했습니다.
- **서버 부담 최소화**
    서버는 크롤링 과정에 직접 개입하지 않고, 단순히 **S3 → DB 적재** 역할만 맡아 안정성을 높였습니다.

### 결과

<img width="600" height="220" alt="image" src="https://github.com/user-attachments/assets/ea22fe45-33d3-427b-bfd8-e201099582af" />

**대규모 데이터 크롤링 속도 대폭 개선**


- 순차 실행 시 **25분 이상** 걸리던 작업이,
    **SQS + Lambda 200개씩 단건 병렬 처리** 도입 후 **10초 이내**로 단축되었습니다.
- Lambda 동시 실행을 통해 **자동 확장성**을 확보했고, 장애 위험 없이 안정적으로 데이터를 수집할 수 있었습니다.
