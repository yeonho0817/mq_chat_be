# build 부분
FROM amazoncorretto:11-alpine-jdk AS builder

RUN mkdir chat
WORKDIR chat
COPY . .
RUN ./gradlew clean bootJar



# 동작 컨테이너로 jar 이동 부분
FROM amazoncorretto:11-alpine-jdk

RUN apk --no-cache add msttcorefonts-installer fontconfig && \
    update-ms-fonts && \
    fc-cache -f

ENV TZ=Asia/Seoul

RUN mkdir chat
WORKDIR chat
COPY --from=builder /chat/build/libs/chat-0.0.1-SNAPSHOT.jar .

CMD ["java","-jar", "-Dspring.profiles.active=${PROFILE}", "/chat/admin-0.0.1-SNAPSHOT.jar"]