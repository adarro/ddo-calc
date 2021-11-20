List of technologies used or considered for use.

Build quality / development tools
These are generally implemented via gradle plugins or triggered of CI/CD builds to improve code quality / security and
or mind map or visualize concepts.

InspectCode <https://inspecode.rocro.com> - runs style guides / linters and can fix some basic typos.

Diagrams / Mindmap <https://gitmind.com> - FlowCharts / Mindmapping.  Would like to use InfoRapid's product when cash allows.


Kafka / Avro / Camel
Kafa comes in as both a front end and back end data tool.

Initial Data population / object builder
1. Leveraging Avro as an evolving schema, we can evolve and build entities such as effects, items etc.
Because we are using Avro, manipulating and reading data becomes typesafe using Avro to Java / Scala libraries.

These entities can be persisted in one or more databases.

Front End
Using Kafka streams, we can update running totals and properties in an event-driven manner for character builders such
 as feats possessed, stances / abilities toggled, items equipped to dynamically show average damage, DR breaking, 
 crit profiles, hit-points etc without having to re-evaluate every effect in place.
 
 In building characters, Feat selection can be improved by streaming / filtering available options asynchronously filtering
 by requirements as they are discovered.
 
 Camel - 
 camel can route these topics in the background to update the databases, run further more process intensive operations etc
 without affecting the flow of the builder.
 
 Vert.x
 Vert.x can expose the API as a RESTful service and react via the event bus with Camel. 
