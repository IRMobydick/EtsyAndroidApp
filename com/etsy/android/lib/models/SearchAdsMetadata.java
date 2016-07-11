package com.etsy.android.lib.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class SearchAdsMetadata extends BaseModel {
    public static final String PROLIST_PREFIX = "prolist-";
    private static final long serialVersionUID = -1781022193848431743L;
    private String mMetadataHash;
    private String mMetadataJson;
    private String mSref;

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if ("sref2".equals(currentName)) {
                    this.mSref = BaseModel.parseStringIdOrNumericValue(jsonParser);
                } else if ("impression_metadata_json".equals(currentName)) {
                    this.mMetadataJson = BaseModel.parseStringPreserveHTMLEscapeEncoding(jsonParser);
                } else if ("impression_metadata_hash".equals(currentName)) {
                    this.mMetadataHash = BaseModel.parseStringPreserveHTMLEscapeEncoding(jsonParser);
                }
            }
        }
    }

    public static SearchAdsMetadata fromPromotedLoggingKey(String str) {
        SearchAdsMetadata searchAdsMetadata = new SearchAdsMetadata();
        searchAdsMetadata.mSref = PROLIST_PREFIX + str;
        searchAdsMetadata.mMetadataJson = PROLIST_PREFIX + str;
        searchAdsMetadata.mMetadataHash = PROLIST_PREFIX + str;
        return searchAdsMetadata;
    }

    public String getSref() {
        return this.mSref;
    }

    public String getMetadataJson() {
        return this.mMetadataJson;
    }

    public String getMetadataHash() {
        return this.mMetadataHash;
    }
}
