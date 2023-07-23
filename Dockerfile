# Docker images can be inherited from other images.

# Choose base image JDK (含 linux/amd64 及 JDK 軟體)
# 230MB    FROM eclipse-temurin:17.0.7_7-jdk-focal
# 181.71MB FROM openjdk:17-jdk-alpine
# 55.14MB  FROM eclipse-temurin:17-jre-alpine -> 運行時會報錯
# [ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.11.0:compile (default-compile) on project eks-svc-idver: Compilation failure
# [ERROR] No compiler is provided in this environment. Perhaps you are running on a JRE rather than a JDK?
# 如果要使用 jre 作為 baseimage 需要先將程式打包為 jar
FROM FROM openjdk:17-jdk-alpine

# Set the image’s working directory
WORKDIR /app

# When project management using Maven，install dependencies
# The first parameter tells Docker what file(s) you would like to copy into the image. The second parameter tells Docker where you want that file(s) to be copied to
# We’ll copy all those files and directories into our working directory - /app .
# Dockerfile 放到程式根目錄下，將程式根目錄下的 .mvn 資料夾複製到容器內 /app/.mvn
COPY .mvn/ .mvn
# Dockerfile 放到程式根目錄下，將程式根目錄下的 mvnw 跟 pom.xml 資料夾複製到容器內 /app/mvnw、/app/pom.xml
COPY mvnw pom.xml ./

# 使用 /app 根目錄下的 mvnw 執行命令 dependency:resolve 查看該程式所使用的依賴並下載下來
RUN ./mvnw dependency:resolve

# Dockerfile 放到程式根目錄下，將程式根目錄下的 src 資料夾複製到容器內 /app/src
COPY src ./src

# 使用 /app/mnvw 執行命令 spring-boot:run
CMD ["./mvnw", "spring-boot:run"]