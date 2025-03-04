package io.truthencode.dal.general;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Set;

public record KeyedJsonNode(ObjectNode node, Set<String> keys) {
}
