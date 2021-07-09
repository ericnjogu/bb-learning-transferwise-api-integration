# fee-service
Created using

```shell
mvn archetype:generate \
  -DarchetypeArtifactId=core-service-archetype \
  -DarchetypeGroupId=com.backbase.archetype \
  -DarchetypeVersion=11.3.0 \
  -DserviceName=fee-service \
  -Dpackage=com.enjogu.exchange.fee \
  -DgroupId=com.enjogu \
  -DartifactId=fee-service
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
-Dspring-boot.run.jvmArguments="-DSIG_SECRET_KEY=JWTSecretKeyDontUseInProduction\!"
```

To run the service from the built binaries, use:
- `java -jar target/fee-service-1.0.0-SNAPSHOT.jar`

## Authorization

Not configured yet

## Functional Testing
### Via exchange rates service
- start exchange rate service (along with IPS, edge, activeMQ, mysql)
- try http://localhost:7777/api/exchange-rate-service/client-api/v1/exchange-rates/fees

### Standalone
http://localhost:9916/service-api/v1/fees


## Community Documentation

Add links to documentation including setup, config, etc.

## Jira Project

Add link to Jira project.

## Confluence Links
Links to relevant confluence pages (design etc).

## Support

The official fee-service support room is [#s-fee-service](https://todo).
