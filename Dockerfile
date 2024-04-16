FROM openjdk:17

WORKDIR /app

COPY infrastructure/build/libs/route-metrics-backend.jar /app/route-metrics-backend.jar

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "route-metrics-backend.jar"]

# docker build -t [username]/[image_name]:[version] .
# docker push [username]/[image_name]:[version]