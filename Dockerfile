FROM adoptopenjdk/maven-openjdk11:latest

RUN apt-get update && apt-get install -y git


# Clonamos el proyecto de GitHub
RUN git clone https://github.com/mediagui/coffee.git

# Ejecutamos el JAR
# RUN cd coffee/boot && mvn clean install
# RUN cd coffee/boot && mvn spring-boot:run
