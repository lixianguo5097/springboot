#指定基础镜像，不需要另外安装jdk
FROM java:8
#维护者
MAINTAINER lxg
#将本地文件添加到容器中，并更名为myproject.jar
COPY springboot-docker-1.0-SNAPSHOT.jar myproject.jar
#指定访问端口，与yml文件中的端口一致
EXPOSE 8081
#容器启动时，运行该程序
ENTRYPOINT ["java", "-jar", "myproject.jar"]