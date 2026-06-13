FROM gradle:7.5.1-jdk17

WORKDIR /app

COPY gradle gradle/
COPY build.gradle settings.gradle gradlew gradlew.bat ./

# Исправляем права и формат файла gradlew
RUN chmod +x gradlew && \
    sed -i 's/\r$//' gradlew && \
    ./gradlew dependencies --no-daemon

COPY src src/

CMD ["./gradlew", "test", "--no-daemon"]