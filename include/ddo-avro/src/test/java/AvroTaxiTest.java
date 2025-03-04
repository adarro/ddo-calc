import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.Matchers.*;
import com.google.common.io.Resources;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.List;

import lang.taxi.generators.avro.TaxiGenerator;

public class AvroTaxiTest {

    @lombok.SneakyThrows
    Path loadResource(String res) {
        return Path.of(Resources.getResource(res)
            .toURI());
    }


    /**
     * Test Avro Avdl to Avsc conversion
     * @param tempDir temporary directory for generated files
     * @throws Exception if conversion fails
     */
    @Test
    @DisplayName("Can convert Avro Avdl to avsc files")
    @java.lang.SuppressWarnings("java:S106")
    void convertAvdl(@TempDir Path tempDir) throws Exception {
        var path = loadResource("DamageInfo.avdl");
        var avroFile = path.toFile();
        assertTrue(avroFile.exists());

        var tool = new org.apache.avro.tool.IdlToSchemataTool();

        List<String> args = List.of(avroFile.getPath(), tempDir.toFile().getPath());
        var iRslt = tool.run(null, System.out, System.err, args);
        assertThat(iRslt, is(0));
        Path file = tempDir.resolve("DamageInfo.avsc");
        assertTrue(file.toFile().exists());

        var generated = new TaxiGenerator()
            .generate(file);
        assertThat(generated.getHasErrors(), is(false));
        System.out.println("results");
        generated.getTaxi().forEach(System.out::println);
        System.out.println("end");

    }
}
