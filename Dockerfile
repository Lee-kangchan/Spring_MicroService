#도커에 사용될 도커 이미지
FROM openjdk:11-slim as build

LABEL maintainer="Lee-kangchan <lsc4237@gmail.com>"

# JAR_FILE 정의
ARG JAR_FILE

COPY ${JAR_FILE} app.jar

# unpackage
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /app.jar)

#stage2

FROM openjdk:11-slim

VOLUME /tmp

ARG DEPENDENCY=/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","com.example.msa.MsaApplication"]