package io.truthencode.ddo.schema

import com.typesafe.scalalogging.LazyLogging
import io.truthencode.ddo.model.protocol.ChangeValueInt
import org.apache.pulsar.client.api.Schema
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

import scala.language.postfixOps

class SchemaTest extends AnyFunSpec with Matchers with LazyLogging {
  describe("ChangeType") {
    it("can be converted via avro4s") {
      import com.sksamuel.avro4s.AvroSchema
      val a4sSchema = AvroSchema[ChangeValueInt]
      logger.info(a4sSchema.toString(true))
    }

    it("can be converted by Pulsar") {
      val p4sSchema = Schema.AVRO(classOf[ChangeValueInt])
      val p4sString = p4sSchema.toString
      logger.info(p4sString)
    }

    it("can use pular4sAvro") {
      import com.sksamuel.pulsar4s.avro.avroSchema
      val ap4sSchema = implicitly[Schema[ChangeValueInt]]
      val ap4sString = ap4sSchema.getSchemaInfo.toString

      logger.info(ap4sString)
    }

    it("can be converted via natively") {
      import com.sksamuel.avro4s.AvroSchema
      val a4sSchema = AvroSchema[ChangeValueInt]
      val a4sToString = a4sSchema.toString(true)
      val schemaJson =
        """
          |{
          |  "type" : "record",
          |  "name" : "ChangeValueInt",
          |  "namespace" : "io.truthencode.ddo.model.protocol",
          |  "fields" : [ {
          |    "name" : "id",
          |    "type" : "string"
          |  }, {
          |    "name" : "currentValue",
          |    "type" : "int"
          |  }, {
          |    "name" : "prevValue",
          |    "type" : "int"
          |  }, {
          |    "name" : "changeType",
          |    "type" : {
          |      "type" : "enum",
          |      "name" : "ChangeType",
          |      "symbols" : [ "INCREASE", "DECREASE", "NOCHANGE" ]
          |    }
          |  } ]
          |}""".stripMargin
      val parser = new org.apache.avro.Schema.Parser()
      val schema = parser.parse(schemaJson)
      val nativeAvroSchema: org.apache.avro.Schema = schema
      val nativeString = nativeAvroSchema.toString(true)
      logger.info(nativeString)
//      nativeString shouldEqual a4sToString

    }
  }
}
