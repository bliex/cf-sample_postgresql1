# cf-sample_postgresql1
sample for spring boot application using mysql on local environment and cloudfoundry appservice 

### 프로젝트 구성
- Java / JDK1.8 / Maven
- SpringBoot 1.3.8  
- Spring WebMVC / Freemarker Template

### 기본동작
- 설정된 datasource 의 postgresql 에 대해서 아래의 쿼리결과문을 화면에 출력
- `SHOW ALL`

### 로컬실행
- `java -jar .\target\cf-sample_postgresql1-1.0.0-SNAPSHOT.jar`
- http://localhost:8080
- `resources/application-default.properties` 에 설정된 datasource 가 작동

### CloudFoundry deploy 
- `cf push`
- CloudFoundry에 의해서 spring profile 이 `cloud` 로 기본 지정됨에 따라, `resources/application-cloud.properties` 의 config 가 로드됨.
- datasource 는 binding 된 postgresql appservice 가 있을 경우, 별도의 설정없이 binding 된 postgresql 에 대해서 auto-configuration 됨.


