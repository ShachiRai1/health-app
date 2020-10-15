FROM openjdk:8-jre
USER root
LABEL maintainer="S R"
 
# Dir for my app
RUN mkdir -p /app
# Copy the MicroProfile starter app
COPY target/health-app-0.0.1-SNAPSHOT.jar /app/
# Expose port to listen to
EXPOSE 8080
EXPOSE 5005
 
#CMD ["-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"]
# Finally, run the script
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005" ,"-jar","/app/health-app-0.0.1-SNAPSHOT.jar"]
