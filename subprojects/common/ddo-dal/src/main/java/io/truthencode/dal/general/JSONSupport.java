package io.truthencode.dal.general;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.quarkus.logging.Log;

import java.io.IOException;
import java.util.*;

public class JSONSupport {
    static final String UPDATE_KEYS_HEADER = "X-UPDATE-FIELDS";

    static Set<String> getKeys(String... updateKeys) {
        Set<String> keys = new HashSet<>();

        for (String updateKey : updateKeys) {
            if (updateKey != null && !updateKey.isEmpty()) {
                if (updateKey.contains(",")) {
                    // Assuming this is a comma-separated list of keys.
                    Arrays.stream(updateKey.split(",")).toList().stream().map(String::trim).forEach(keys::add);
                } else {
                    keys.add(updateKey.trim());
                }
            }
        }
        return keys;
    }

    static Set<String> extractKeys(String jsonData) {
        Set<String> keys = new HashSet<>();
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getFactory();
        JsonParser jp = null;
        try {
            Log.warn("analyzing body: " + jsonData);
            jp = factory.createParser(jsonData);
            JsonNode root = mapper.readTree(jp);
            ObjectNode rootNode = (ObjectNode) root;

            rootNode.fieldNames().forEachRemaining(keys::add);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return keys;
    }
}
