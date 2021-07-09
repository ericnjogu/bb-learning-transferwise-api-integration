# exchange-rate-service

Created using

```shell
mvn archetype:generate -B \
  -DarchetypeArtifactId=core-service-archetype \
  -DarchetypeGroupId=com.backbase.archetype \
  -DarchetypeVersion=11.3.0 \
  -DserviceName=exchange-rate-service \
  -Dpackage=com.enjogu \
  -DgroupId=com.enjogu.bb.learning \
  -DartifactId=exchange-rate-service
  ```

#Getting Started
* [Extend and build](https://community.backbase.com/documentation/ServiceSDK/latest/extend_and_build)

## Dependencies

Requires a running Eureka registry, by default on port 8080.

## Configuration

Service configuration is under `src/main/resources/application.yml`.

## Running

To run the service in development mode, use:
```shell
mvn spring-boot:run \
-Dspring-boot.run.jvmArguments="-Dwise.api-key=df098f9f-eb3c-4111-bfe4-4a38acc5e2cb -Dwise.base-url=https4://api.sandbox.transferwise.tech -DSIG_SECRET_KEY=JWTSecretKeyDontUseInProduction\!"
```

To run the service from the built binaries, use:
- `java -jar target/exchange-rate-service-1.0.0-SNAPSHOT.jar`

## Authorization

http://localhost:7777/api/auth/login

Login in with admin/admin

## Functional Testing
Perform the authorization then:

http://localhost:7777/api/exchange-rate-service/client-api/v1/exchange-rates?source=EUR&target=USD&from=2019-03-31&to=2019-04-30

## Community Documentation

Add links to documentation including setup, config, etc.

## Jira Project

Add link to Jira project.

## Confluence Links
Links to relevant confluence pages (design etc).

## Support

The official exchange-rate-service support room is [#s-exchange-rate-service](https://todo).
