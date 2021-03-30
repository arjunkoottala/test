FROM java:8

EXPOSE 9001

ADD target/comment-service.jar comment-service.jar

ENTRYPOINT ["java", "-jar", "comment-service.jar"]