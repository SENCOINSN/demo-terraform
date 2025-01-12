FROM openjdk:17
ADD target/demo-terraform-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "demo.jar"]