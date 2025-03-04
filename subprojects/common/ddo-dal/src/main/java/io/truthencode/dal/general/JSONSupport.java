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

    static Set<String> extractKeys(String jsonData) throws IOException {
        Set<String> keys = new HashSet<>();
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getFactory();

        Log.warn("analyzing body: " + jsonData);
        JsonParser jp = factory.createParser(jsonData);
        JsonNode root = mapper.readTree(jp);
        ObjectNode rootNode = (ObjectNode) root;

        rootNode.fieldNames().forEachRemaining(keys::add);

        return keys;
    }

    /**
     * Extracts explicitly named key values from keylists or extracts JSON fields from the request body and adds the keys
     *
     * @param jsonData JSON data for updates
     * @param keyLists possible lists of keys to extract.  I.e. an array containing some Header, query-string parameters, etc.
     * @return a KeyedJsonNode containing the keys and a JSON node containing the JSoN data.
     * @throws IOException if the JSON data is invalid
     */
    static KeyedJsonNode extractKeys(String jsonData, String... keyLists) throws IOException {

        Set<String> keys = (keyLists != null) ? getKeys(keyLists) : new HashSet<>();
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getFactory();
        Log.warn("analyzing body: " + jsonData);
        JsonParser jp = factory.createParser(jsonData);
        JsonNode root = mapper.readTree(jp);
        ObjectNode rootNode = (ObjectNode) root;
        if (keys.isEmpty())
            rootNode.fieldNames().forEachRemaining(keys::add);
        return new KeyedJsonNode(rootNode, keys);

    }


}
