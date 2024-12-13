# 私有镜像
FROM swr.cn-north-4.myhuaweicloud.com/oomall-javaee/openjdk:17-alpine
# 官方镜像
#FROM openjdk:17-alpine
MAINTAINER mingqiu mingqiu@xmu.edu.cn
WORKDIR /app
ARG JAR_FILE
ADD ${JAR_FILE} /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
CMD ["--spring.data.mongodb.uri=mongodb://mongo1:27017,mongo2:27018,mongo3:27019/?replicaSet=rs0", "--spring.data.mongodb.database=oomall"]