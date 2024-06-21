# dev-backend

This project uses Quarkus, the Supersonic Subatomic Java Framework, to provide various frequently used APIs for development and testing.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_** Quarkus now ships with a Dev UI, which is available in dev mode only at [http://localhost:8080/q/dev/](http://localhost:8080/q/dev/)

## Swagger UI / OpenAPI document

After you started the app in dev mode, you can access the Swagger UI at [http://localhost:8080/q/swagger-ui](http://localhost:8080/q/swagger-ui)

The OpenAPI document is served by the endpoint [http://localhost:8080/q/openapi](http://localhost:8080/q/openapi)

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/dev-backend-1.0.0-SNAPSHOT-runner`

## Author

Raphael Tholl - [GitHub](https://github.com/RapTho)
