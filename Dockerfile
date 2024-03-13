# jdk 17
FROM openjdk:17

# 인자 설정
ARG JAR_FILE=build/libs/*.jar

# jar 파일 복제
COPY ${JAR_FILE} app.jar

# static 폴더 복제
COPY src/main/resources/static /app/static

# IP 개방
ENV HOST 0.0.0.0

# 8080 포트 오픈
EXPOSE 8080

# 실행 명령어
ENTRYPOINT ["java", "-jar", "app.jar"]

# 이미지 생성
#docker build -t [이미지 이름] ./

# 컨테이너 생성, -d : 백그라운드 실행, -p: 포트 연결
# docker run -d --name [컨테이너 이름] -p 8080:8080 [이미지 이름]
