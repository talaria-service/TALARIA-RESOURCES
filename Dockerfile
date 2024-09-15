FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} talaria-res.jar
ENV TZ=Asia/Seoul
ENTRYPOINT ["java", "-jar", "/talaria-res.jar"]