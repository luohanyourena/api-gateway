#FROM registry.cn-shanghai.aliyuncs.com/ddi-base/ddi-openjdk8-jdk-alpine:v1  #使用阿里云镜像仓库
#使用官服镜像仓库
FROM openjdk:8-alpine

ADD target/api-gateway-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9000

ENTRYPOINT ["java","-jar","/app.jar"]