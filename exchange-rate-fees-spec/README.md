#  Exchange Rate Fees Specification
This is a 'Spec' project that defines an interface to the  Exchange Rate Fees Spec service.

Created by
```shell
  mvn archetype:generate \
  -DarchetypeArtifactId=raml-specifications-archetype \
  -DarchetypeGroupId=com.backbase.archetype \
  -DarchetypeVersion=11.3.0 \
  "-DserviceName= Exchange Rate Fees Spec" \
  -DservicePackageName=com.enjogu.exchange.fee \
  -DgroupId=com.enjogu \
  -DartifactId=exchange-rate-fees
```

## API Documentation
[RAML](src/main/resources/service-api.raml)

## Build the Specification
Run the following command to build, package and install the spec project to your local maven cache:  
`mvn clean install`

## Community Documentation

Add links to documentation including setup, config, etc.

## Jira Project

Add link to Jira project.

## Confluence Links
Links to relevant confluence pages (design etc).

## Support

Slack, Email, Jira etc.