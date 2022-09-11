FROM bellsoft/liberica-openjdk-alpine:11.0.3
ADD /build/libs/diary-0.0.1-SNAPSHOT.jar backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]
EXPOSE 8080