# build 부분
FROM amazoncorretto:11-alpine-jdk AS builder

RUN mkdir mq_chat_be
WORKDIR mq_chat_be
COPY . .
RUN ./gradlew clean bootJar



# 동작 컨테이너로 jar 이동 부분
FROM amazoncorretto:11-alpine-jdk

RUN apk --no-cache add msttcorefonts-installer fontconfig && \
    update-ms-fonts && \
    fc-cache -f

ENV TZ=Asia/Seoul

RUN mkdir mq_chat_be
WORKDIR mq_chat_be
COPY --from=builder /mq_chat_be/build/libs/mq_chat_be-0.0.1-SNAPSHOT.jar .

CMD ["java","-jar", "-Dspring.profiles.active=${PROFILE}", "/mq_chat_be/mq_chat_be-0.0.1-SNAPSHOT.jar"]