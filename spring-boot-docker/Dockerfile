FROM openjdk:8
COPY target/spring-boot-docker-*.jar /opt/spring-boot-docker.jar
CMD ["java", "-Xmx128m", "-Xms128m", "-jar", "/opt/spring-boot-docker.jar"]