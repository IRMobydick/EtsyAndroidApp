package com.etsy.android.lib.models;

import com.fasterxml.jackson.core.JsonParser;

public class EmptyResult extends BaseModel {
    private static final long serialVersionUID = 6455657745519203505L;

    public void parseData(JsonParser jsonParser) {
    }
}
