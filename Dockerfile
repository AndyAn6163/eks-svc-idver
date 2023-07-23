# Docker images can be inherited from other images.

# Choose base image JDK (含 linux/amd64 及 JDK 軟體)
# 230MB    FROM eclipse-temurin:17.0.7_7-jdk-focal
# 181.71MB FROM openjdk:17-jdk-alpine
# 55.14MB  FROM eclipse-temurin:17-jre-alpine -> 運行時會報錯
# [ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.11.0:compile (default-compile) on project eks-svc-idver: Compilation failure
# [ERROR] No compiler is provided in this environment. Perhaps you are running on a JRE rather than a JDK?
# 如果要使用 jre 作為 baseimage 需要先將程式打包為 jar
FROM eclipse-temurin:17-jre-alpine
COPY target/eks-svc-idver-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]