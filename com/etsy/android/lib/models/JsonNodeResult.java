package com.etsy.android.lib.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;

public class JsonNodeResult extends BaseModel {
    private JsonNode mJsonNode;

    public JsonNode getData() {
        return this.mJsonNode;
    }

    public void parseData(JsonParser jsonParser) {
        this.mJsonNode = (JsonNode) jsonParser.readValueAsTree();
    }
}
