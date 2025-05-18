package io.truthencode.dal.general;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Set;

/**
 * Represents a JSON node with associated keys.
 *
 * @param node The ObjectNode containing JSON data
 * @param keys A set of keys associated with the JSON node
 */
public record KeyedJsonNode(ObjectNode node, Set<String> keys) {
}
