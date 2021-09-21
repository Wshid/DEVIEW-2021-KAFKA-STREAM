# DEVIEW-2021-KAFKA-STREAM
- DEVIEW 2021 '하루 N억개 웹툰 로그를 처리하는 Realtime Application 만들기' 발표의 DEMO APP 입니다

### App 구동 상세 스펙
- apache-kafka : kafka_2.12-2.8.0
- java : 1.8
- maven : 3.6.3

### 빌드 방법
- maven 빌드 이후, java application 실행
```bash
mvn clean package
java -jar target/deview-2021-kafka-streams-0.0.1-SNAPSHOT.jar
```