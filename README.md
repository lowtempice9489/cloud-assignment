# 클라우드 기반 백엔드 과제

![화면 캡처 2026-08-04 164856.png](%ED%99%94%EB%A9%B4%20%EC%BA%A1%EC%B2%98%202026-08-04%20164856.png)

## EC2
- Public IPv4 Address : 52.78.160.195

![화면 캡처 2026-08-07 182739.png](%ED%99%94%EB%A9%B4%20%EC%BA%A1%EC%B2%98%202026-08-07%20182739.png)

![화면 캡처 2026-08-07 183313.png](%ED%99%94%EB%A9%B4%20%EC%BA%A1%EC%B2%98%202026-08-07%20183313.png)

![화면 캡처 2026-08-07 184735.png](%ED%99%94%EB%A9%B4%20%EC%BA%A1%EC%B2%98%202026-08-07%20184735.png)

![화면 캡처 2026-08-07 185730.png](%ED%99%94%EB%A9%B4%20%EC%BA%A1%EC%B2%98%202026-08-07%20185730.png)

![화면 캡처 2026-08-07 185756.png](%ED%99%94%EB%A9%B4%20%EC%BA%A1%EC%B2%98%202026-08-07%20185756.png)

![화면 캡처 2026-08-07 190016.png](%ED%99%94%EB%A9%B4%20%EC%BA%A1%EC%B2%98%202026-08-07%20190016.png)

# 추가 구현 사항

## Global Exception Handler
- 애플리케이션에서 발생하는 예외를 한 곳에서 처리하여 일관된 오류 응답(JSON)을 반환하도록 구현
## Custom Exception
### MemberNotFoundException
- 존재하지 않는 회원 조회 시 사용하는 사용자 정의 예외
### ProfileImageNotFoundException
- 등록되지 않은 프로필 이미지를 요청했을 때 사용하는 사용자 정의 예외
### ErrorResponse
- 오류 발생 시 상태 코드, 오류 종류, 메시지, 시간을 포함한 공통 응답 형식을 제공
### API Logging Interceptor
- 모든 API 요청의 HTTP Method와 URI를 로그로 기록하여 요청 흐름을 확인할 수 있도록 구현했습니다.
### MBTI Enum
- MBTI 값을 Enum으로 관리하여 잘못된 문자열 입력을 방지하고 타입 안정성을 높였습니다.
### Validation
- Bean Validation을 사용하여 요청 데이터의 형식을 검증하고 잘못된 입력을 사전에 차단했습니다.
### Presigned URL
- S3 객체에 직접 접근 권한을 주지 않고 일정 시간 동안만 접근 가능한 URL을 생성하도록 구현했습니다.
### AWS IAM Role
- EC2에서 Access Key 없이 S3에 접근할 수 있도록 IAM Role을 사용했습니다.
### Spring Boot Actuator
- /actuator/health, /actuator/info를 통해 애플리케이션 상태와 기본 정보를 확인할 수 있도록 구성했습니다.