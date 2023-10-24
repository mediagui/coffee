FROM adoptopenjdk/maven-openjdk11:latest

RUN apt-get update && apt-get install -y git

# Clonamos el proyecto de GitHub
RUN git clone https://github.com/mediagui/coffee.git
RUN cd /coffee && git checkout main && git pull

# Compilamos el proyecto
RUN cd /coffee && mvn clean install
#Ejecutamos el jar
CMD ["/opt/java/openjdk/bin/java","-jar","/coffee/boot/target/coffee-shop.jar"]
