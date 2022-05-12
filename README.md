# Chart-Backend-V1 [![Build Status](https://app.travis-ci.com/dsm-chart/Chart-Backend-V1.svg?branch=main)](https://app.travis-ci.com/dsm-chart/Chart-Backend-V1)


# Chart란?

---

> 방과후 프로젝트반 **DaF** 팀에서 진행하고 있어요.

 ***학생들에게 필요한 기능(ex. 시간표, 급식표)를 포함하여, 개발자에게도 유용한 기능(github 커밋 그래프)을 제공하기 위해 노력해요!***
> 



# 개발 언어 및 활용 기술

---

### 개발 환경

- **SpringBoot** 로 어플리케이션 서버를 구축합니다.
- 빌드 도구로는 *Gradle*을 사용합니다.
- 어플리케이션의 **안전성과, 간결함, 자바와의 높은 상호 운영성**을 위해 **Kotlin**으로 개발합니다.
- **Spring Data JPA(Hibernate)** 로 *객체 지향 데이터 로직*을 작성합니다.

### Infrastructure

- **AWS EC2**를 사용합니다.
- **AWS RDS**를 데이터베이스로 사용합니다.
- **Firebase Cloud Message** 를 사용하여 푸시알림을 구현합니다.

### CI/CD

- **TrivisCI** 로 *빌드와 테스트를 검사합니다.*
- **CodeDeploy** 로 *빌드 자동화를 구축합니다.*
- **Sonar Cloud**를 통해 github에 Push된 code를 정적분석하여, 보안 취약점 및 버그 등을 사전에 탐색합니다.

### Security

- **Spring Security** 를 사용합니다.
- **JWT**를 이용하여, *서버에 별도 저장소*를 유지하지 않음에도 권한을 인증할 수 있는 로직을 구성합니다.




## Backend Architecture
![architecture](https://github.com/dsm-chart/Chart-Backend-V1/blob/main/chart-architecture.png)


