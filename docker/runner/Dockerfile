FROM gradle:7.5.1-jdk17

WORKDIR /app

COPY gradle gradle/
COPY build.gradle settings.gradle gradlew gradlew.bat ./

RUN chmod +x gradlew && ./gradlew dependencies --no-daemon

COPY src src/

CMD ["./gradlew", "test", "--no-daemon"]