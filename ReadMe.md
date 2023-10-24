# Coffee Shop

An amazing coffee shop backend dispenser.

## Getting Started

The project can be downloaded from [https://github.com/mediagui/coffee.git](https://github.com/mediagui/coffee.git)

The commits are not as clear as they should be, because of the time constraints. (Sorry about that) :pray:

### Prerequisites

#### With Docker/Podman
1. [Docker](https://docs.docker.com/install/) or [Podman](https://podman.io/getting-started/installation)
2. Execute the dockerfile contained in the project root folder
```bash
docker build -t coffee .
```
```bash
podman build -t coffee .
```
3. Run the docker image
```bash 
docker run -p 8080:8080 coffee
```
```bash 
podman run -p 8080:8080 coffee
```
4. Open the browser and go to [http://localhost:8080](http://localhost:8080)
5. Enjoy the coffee :coffee:

The endpoints are available at [http://localhost:8080/swagger-ui/](http://localhost:8080/swagger-ui/)

#### Without Docker/Podman
1. [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
2. [Maven](https://maven.apache.org/install.html)
3. Execute the following command in the project boot folder
```bash
mvn spring-boot:run
```

For both options the H2 console is located in [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
To see the database content, the JDBC URL is `jdbc:h2:mem:testdb` and the user is `sa` with no password.

If you have any question, this info is available in the application.yml file in the boot module of the proyect.


## Running the tests

For running the test you can execute the following command in the project boot folder
```bash
  mvn clean test verify
```
With this option you will be able to see the test coverage in the following file
```bash
  coffeeshop/agregate-repor/target/site/jacoco/index.html
```

## External libraries used

 - Spring Boot 2.7.17
 - Lombok 1.18.30
 - Mockito 4.5.1
 - Mapstruct 1.4.2
 - Swagger 3.0
 - Itext 5.5.13.3

To see a complete dependency tree for each module, you can execute the following command in the project boot folder
```bash
  mvn dependency:tree
``` 
to get a result similar to this one
```bash
[INFO] --------------------------< com.inatlas:boot >--------------------------
[INFO] Building boot 2.0-SNAPSHOT                                         [5/6]
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:3.3.0:tree (default-cli) @ boot ---
[INFO] com.inatlas:boot:jar:2.0-SNAPSHOT
[INFO] +- com.inatlas:domain:jar:1.0-SNAPSHOT:compile
[INFO] +- com.inatlas:application:jar:2.0-SNAPSHOT:compile
[INFO] |  \- junit:junit:jar:4.13.2:compile
[INFO] |     \- org.hamcrest:hamcrest-core:jar:2.2:compile
[INFO] |        \- org.hamcrest:hamcrest:jar:2.2:compile
[INFO] +- com.inatlas:infrastructure:jar:1.0-SNAPSHOT:compile
[INFO] |  +- org.mapstruct:mapstruct:jar:1.4.2.Final:compile
[INFO] |  +- io.springfox:springfox-boot-starter:jar:3.0.0:compile
[INFO] |  |  +- io.springfox:springfox-oas:jar:3.0.0:compile
[INFO] |  |  |  +- io.swagger.core.v3:swagger-models:jar:2.1.2:compile
[INFO] |  |  |  +- io.springfox:springfox-spi:jar:3.0.0:compile
```

I tried to use [Semantic Versioning](http://semver.org/) for versioning. For the versions
available

## Authors
- **Miguel Angel Medina** 

