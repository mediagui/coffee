FROM adoptopenjdk/maven-openjdk11:latest

RUN apt-get update && apt-get install -y git

# Clonamos el proyecto de GitHub
RUN git clone https://github.com/mediagui/coffee.git
RUN cd coffee && git checkout main

# Ejecutamos el JAR
# RUN mvn clean install
# RUN mvn cd boot spring-boot:run
