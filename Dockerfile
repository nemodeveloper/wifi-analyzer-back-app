FROM adoptopenjdk/openjdk12
ENV APP_HOME wifi-analyzer
ENV JAVA_OPTS = ""
RUN mkdir $APP_HOME
RUN mkdir $APP_HOME/logs
RUN mkdir $APP_HOME/h2
VOLUME $APP_HOME
WORKDIR $APP_HOME
ARG JAR_FILE
COPY ${JAR_FILE} wifi-analyzer.jar
ENTRYPOINT ["java", "-jar", "wifi-analyzer.jar"]
EXPOSE 8081
# example build - docker build --build-arg JAR_FILE=target/*.jar -t wifi-analyzer .
# exambple run - docker run -d -p 8081:8081 -v /wifi-analyzer/logs:/wifi-analyzer/logs -v /wifi-analyzer/h2:/wifi-analyzer/h2 --name wifi-analyzer wifi-analyzer