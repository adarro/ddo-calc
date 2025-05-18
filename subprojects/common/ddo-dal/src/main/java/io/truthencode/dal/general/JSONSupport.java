package io.truthencode.dal.general;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.quarkus.logging.Log;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.util.*;

/**
 * Utility class for JSON-related operations.
 */
@UtilityClass
public class JSONSupport {
    /**
     * HTTP-HEADER for specifying update keys.
     */
    static final String UPDATE_KEYS_HEADER = "X-UPDATE-FIELDS";

    /**
     * Keys to update.
     * Reads the keys and attempts to extract sanitized values from the header.
     * @param updateKeys the keys to update.
     * @return the keys to update.
     */
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

    /**
     * Extracts explicitly named key values from keylists or extracts JSON fields from the request body and adds the keys
     * @param jsonData the json data to extract keys from
     * @return the keys to update.
     * @throws IOException if the JSON data cannot be parsed
     */
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
