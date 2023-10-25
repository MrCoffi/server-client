#Base
FROM openjdk:17-oracle

#Directory app in conteiner
WORKDIR /app

#Copy JAR file app in conteiner
COPY build/libs/studentclient-0.0.1-SNAPSHOT.jar app.jar

# Creating value
ENV SOCKET_SERVER_HOST=localhost
ENV SOCKET_SERVER_PORT=8888

#Comand for Running app
CMD ["java","-jar","app.jar"]