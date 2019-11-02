FROM adoptopenjdk/openjdk13
ENV APP_HOME wifi-analyzer
ENV JAVA_OPTS = ""
ENV ENTRY_JAR_NAME = "wifi-analyzer"
RUN mkdir $APP_HOME
RUN mkdir $APP_HOME/logs
RUN mkdir $APP_HOME/h2
VOLUME $APP_HOME
WORKDIR $APP_HOME
ARG JAR_FILE
COPY ${JAR_FILE} ${ENTRY_JAR_NAME}.jar
ENTRYPOINT ["java", "-jar", "${ENTRY_JAR_NAME}.jar"]
EXPOSE 8081
# example build - docker build --build-arg JAR_FILE=target/*.jar -t wifi-analyzer .
# exambple run - docker run -d -p 8081:8081 -v /wifi-analyzer/logs:/wifi-analyzer/logs -v /wifi-analyzer/h2:/wifi-analyzer/h2 --name wifi-analyzer wifi-analyzer