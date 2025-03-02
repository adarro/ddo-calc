package io.truthencode.ddo.etl.internal;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileStream;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericEnumSymbol;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class JsonAvroTools {
    // need to remember what this contained, guessing mapped values for the avro types
    private static final String AVRO_ARRAY = "";
    public ReadOutput avroToJson(String dataInFilePath, String dataOutFilePath, String rootName) {
        ReadOutput readOutput = new ReadOutput();
        File directoryPath = new File(dataInFilePath);
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(directoryPath))) {
            DataFileStream<GenericRecord> dataFileStream = new DataFileStream<>(dataInputStream, new GenericDatumReader<>());
            dataOutFilePath = dataOutFilePath.concat(File.separator).concat(UUID.randomUUID().toString()).concat(".json");
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(dataOutFilePath));
            // unfinished
            dataOutputStream.write("{\"".concat(rootName).concat("\":{\"").concat(AVRO_ARRAY).concat("\":[").getBytes());
            dataFileStream.forEachRemaining(gr -> {
                try {
                    dataOutputStream.write(String.valueOf(dataFileStream.next()).getBytes());
                    dataOutputStream.write(!dataFileStream.hasNext() ? "]".getBytes() : ",".getBytes());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            dataOutputStream.writeBytes("}}");
            readOutput.setOutput(Base64.getEncoder().encodeToString(dataOutFilePath.getBytes()));
            readOutput.setValid(Boolean.TRUE);
        } catch (Exception e) {
            readOutput.setErrorObject(generateError(e));
            readOutput.setValid(Boolean.FALSE);
        }
        return readOutput;
    }

    public WriteOutput jsonToAvro(String rootName, Map<String, Object> jsonDataMap, String avroSchema, String dataInFilePath, String dataOutFilePath) {
        WriteOutput writeOutput = new WriteOutput();
        try {
            Files.createDirectories(Path.of(dataOutFilePath));
            dataOutFilePath = dataOutFilePath.concat(File.separator).concat(UUID.randomUUID().toString()).concat(".avro");
            Schema schema = new Schema.Parser().parse(avroSchema);
            DatumWriter<GenericRecord> datumWriter = new SpecificDatumWriter<>(schema);
            DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(datumWriter);
            dataFileWriter.create(schema, new DataOutputStream(new FileOutputStream(new File(dataOutFilePath))));
            if (jsonDataMap != null && jsonDataMap.get(AVRO_ARRAY) != null && jsonDataMap.get(AVRO_ARRAY) instanceof List<?>) {
                List<Map<String, Object>> list = (List<Map<String, Object>>) jsonDataMap.get(AVRO_ARRAY);
                for (Map<String, Object> data : list) {
                    write(data, schema, dataFileWriter);
                }
            } else {
                DocumentContext streamedContext = JsonPath.using(Configuration.builder().build()).parse(new DataInputStream(new FileInputStream(dataInFilePath)));
                String readPath = "['" .concat(rootName).concat( "']['").concat(AVRO_ARRAY).concat("']");
                BigInteger recordsSize = new BigInteger(streamedContext.read(readPath.concat(".length()")).toString());
                for (BigInteger record = new BigInteger("0"); record.compareTo(recordsSize) < 0; record = record.add(BigInteger.ONE)) {
                    Map<String, Object> recordMap = streamedContext.read(readPath.concat("[" + record + "]"));
                    write(recordMap, schema, dataFileWriter);
                }
            }
            dataFileWriter.flush();
            writeOutput.setValid(Boolean.TRUE);
            writeOutput.setOutput(Base64.getEncoder().encodeToString(dataOutFilePath.getBytes()));
        } catch (Exception e) {
            writeOutput.setErrorObject(generateError(e));
            writeOutput.setValid(Boolean.FALSE);
        }
        return writeOutput;
    }

    private ErrorObject generateError(Exception e) {
        return new ErrorObject(e);
        
    }

    private void write(Map<String, Object> record, Schema schema, DataFileWriter<GenericRecord> writer) throws IOException {
        GenericRecord avroRecord = new GenericData.Record(schema);
        record.keySet().parallelStream().forEachOrdered(key->putData(schema, key, record.get(key), avroRecord));
        writer.append(avroRecord);
    }

    private void putData(Schema schema, String key, Object value, GenericRecord avroRecord) {
        if (schema.getField(key).schema().getType().equals(Schema.Type.ENUM)) {
            GenericEnumSymbol<GenericData.EnumSymbol> variantType = new GenericData.EnumSymbol(schema.getField(key).schema(), value);
            avroRecord.put(key, variantType);
        } else {
            avroRecord.put(key, value);
        }
    }
}
