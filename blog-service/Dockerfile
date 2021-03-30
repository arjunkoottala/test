FROM java:8

EXPOSE 9002

ADD target/blog-service.jar blog-service.jar

ENTRYPOINT ["java", "-jar", "blog-service.jar"]