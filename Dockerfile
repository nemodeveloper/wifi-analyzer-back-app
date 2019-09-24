FROM adoptopenjdk/openjdk12
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} wifi-analyzer.jar
ENTRYPOINT ["java","-jar","/wifi-analyzer.jar"]
EXPOSE 8081