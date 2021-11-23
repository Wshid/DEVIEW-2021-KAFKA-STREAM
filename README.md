# DEVIEW-2021-KAFKA-STREAM
- DEVIEW 2021 '하루 N억개 웹툰 로그를 처리하는 Realtime Application 만들기' 발표의 DEMO APP 입니다

### App 구동 상세 스펙
- apache-kafka : kafka_2.12-2.8.0
- java : 1.8
- maven : 3.6.3

### FLOW
![image](https://user-images.githubusercontent.com/10006290/143026013-4ac715b9-b85e-4a92-9d77-533feeac1204.png)

### 사전 설정
- `application.yml`에서 개별 구축해주신 kafka의 정보를 입력해주셔야 합니다.
- ![image](https://user-images.githubusercontent.com/10006290/143025663-e71f66bc-2e3e-4eeb-9661-dcc2264e144a.png)


### APP 빌드 방법
- maven 빌드 이후, java application 실행
```bash
mvn clean package
java -jar target/deview-2021-kafka-streams-0.0.1-SNAPSHOT.jar
```

### 테스트 방법
- kafka 로컬 구동
- 위 방법에 따른 demo app 빌드 및 수행
- local-kafka에서 다음과 같은 명령 수행
  ```bash
  # src-topic produce, 임의의 문자열 유입
  $KAFKA_HOME/bin/kafka-console-producer.sh --broker-list test-kafka:9092 --topic src-topic
  
  # src-topic consume, src-topic 데이터 확인
  $KAFKA_HOME/apps/kafka/bin/kafka-console-consumer.sh --bootstrap-server test-kafka:9092 --topic src-topic
  
  # dest-topic consume, demo-app에 대해 가공된 데이터 확인
  $KAFKA_HOME/apps/kafka/bin/kafka-console-consumer.sh --bootstrap-server test-kafka:9092 --topic dest-topic
  ```
