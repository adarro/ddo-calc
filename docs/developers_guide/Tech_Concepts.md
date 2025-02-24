# List of technologies used or considered for use

Build quality / development tools These are generally implemented via gradle plugins or triggered of CI/CD builds to
improve code quality / security and or mind map or visualize concepts.

Diagrams / Mindmap <https://gitmind.com> - FlowCharts / Mindmapping. Would like to use InfoRapid's product when cash
allows.

Kafka / Avro / Camel Kafa comes in as both a front end and back end data tool.

Initial Data population / object builder

1. Leveraging Avro as an evolving schema, we can evolve and build entities such as effects, items etc. Because we are
   using Avro, manipulating and reading data becomes typesafe using Avro to Java / Scala libraries.

These entities can be persisted in one or more databases.

## Validation

### [Wix-Accord](https://github.com/wix-incubator/accord)

My first choice for object validation (Scala).  
It has since been retired.
Migrating to [ZIO Validation](https://zio.dev/zio-prelude/functional-data-types/validation/)

### [Octopus](https://github.com/krzemin/octopus)

Appears similar with integration with Catz / Scalaz but not scala 3 and last active 3 years ago.

### [Parisksha](https://github.com/ayushworks/pariksha)

Same as above with Catz and 4 years since last active.

### [Fields](https://jap-company.github.io/fields/)

Scala 3 and uses Final Tagless

## Transformation

### Chimney (https://github.com/scalalandio/chimney)

Scala Object mangler / mapper to assist going from similar forms.
Useful where User DTO ~= User Db Entity ~= User Login etc

## Packaging

### Layrry - A Launcher and API for Modularized Java Applications

https://github.com/moditect/layrry/

## Publishing

### JReleaser Gradle Plugin

https://jreleaser.org/guide/latest/tools/jreleaser-gradle.html
(Uses kordamp so we'll need ot fix the license / jar packaging)

## Documentation

I would really like some living documentation.  
In addition to the technical specifications (scala / javadoc) a nice getting started / how you can help is a big
priority. I would also like to include concordion's test results as they seem the most user-friendly code to spec to
result comparison. Scala3 has integrated features similar to docusaurus, so likely depend on that once we complete
Scala3 upgrade. Possible alternatives are [Znai](https://testingisdocumenting.org/znai/introduction/getting-started/) or
Docusaurus. We can go aDoc if needed but would prefer Markdown.

### Allure

[Allue](https://github.com/allure-framework) is a reporting aggregation service that works with Junit4 , Junit5, Scalatest, Cucumber and more.

### OpenAPI

Embedding a Swagger UI for the OpenAPI spec in the docs

### Jupyter

Would eventually like to integrate a Jupyter notebook in the doc site.
This would be useful for a living calculator, quick web checks and possibly even spark integration.
The default is very Python focused, but R / Scala kernels exist. Ideally embedding a Graal Scala Jupyter.
There are also a handful of Proof of concept polygot notebooks out there, mostly built in Smalltalk.

## Front End

### Endpoints

[Tapir](https://github.com/softwaremill/tapir)
allows defining and documentation of http endpoints that can be implemented via other technologies
such as Vert.x or Akka.

Front End Using Kafka streams, we can update running totals and properties in an event-driven manner for character
builders such as feats possessed, stances / abilities toggled, items equipped to dynamically show average damage, DR
breaking, crit profiles, hit-points etc. without having to re-evaluate every effect in place.

In building characters, Feat selection can be improved by streaming / filtering available options asynchronously
filtering by requirements as they are discovered.

Camel - camel can route these topics in the background to update the databases, run further more process intensive
operations etc without affecting the flow of the builder.

Vert.x Vert.x can expose the API as a RESTful service and react via the event bus with Camel.

#### REST Quarkus

[Quarkus Renarde](https://docs.quarkiverse.io/quarkus-renarde/dev/advanced.html) - useful for basic front-end data view with full HTML5 +bootstrap htmx and Qute templates.
Pros:

- Works well with Hibernate ORM (Panache)
- ease to add / create entities

Cons:

- not currently compatible with reactive, so entities can not be easily shared between the two using Active Record Pattern.
- BackOffice extension is not compatible with reactive repositories or Kotlin Panache

Assuming base entities are created via JPA @Entity without directly extending PanacheEntity, it is possible to use the repository pattern to create both a reactive and non-reactive repository. This however, prevents use of the Backoffice extension as it requires the entity to extend PanacheEntityBase.

## Apache Isis

Rapid prototyping where Domain Objects become the UI.
[Apache ~~Isis~~ Causeway](https://causeway.apache.org)
May have potential direct-integration issues as a JPMS Java app, but as a RESTful DTO Front-end it could be extremely useful.

## Messaging

### MQTT

Mosquitto - MQTT Broker - https://mosquitto.org/ Lightweight for inter object messaging and perhaps some chat.

### Bayeux

Cometd - Bayeux - https://www.cometd.org/ - Websockets for real time updates. Link to Restful API. / Database updates.

## Database

MongoDB - MongoDb is a document database that stores data in JSON-like documents.
Use for persistent storage of data. Possibly for master data and / or user data.

Hibernate / Quarkus Config https://quarkus.io/guides/datasource

### Xodus

Xodus - Jetbrains Xodus is a transactional embedded database for JVM.
Use for Session Storage, Caching, and other data storage needs.

### SpiceDB

SpiceDB - https://spicedb.dev/ - A database for managing fine-grained permissions across many services.

## Authorization and Authentication

### Keycloak

Keycloak - https://www.keycloak.org/ - Open Source Identity and Access Management.

Dex - https://dexidp.io/ - OpenID Connect for Kubernetes.
