# DDO-AVRO

Avro tools for DDO Project.

## Summary

It functions as a psuedo-standalone tool for the DDO Project.
The main purpose is to define DDO Models in Avro IDL format, then generate the corresponding JVM classes in addition to Taxi-lang schemas.

The current Avro Gradle plugin has conflicts with other plugins in the DDO project.

This utility will create scala classes for the Avro IDL files. (currently in DDO-MODEL)

As a side-effect, it is insulated from the greater DDO project and does not require Scala3 or other restrictions.
