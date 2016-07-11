package com.etsy.android.lib.models;

import com.etsy.android.lib.logger.EtsyDebug;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.List;

public class ListingVideos extends BaseModel {
    public static final String HIGH_QUALITY = "hd";
    public static final String LOW_QUALITy = "sd";
    private static final String TAG;
    private List<ListingVideo> mVideos;

    static {
        TAG = EtsyDebug.m1891a(ListingVideos.class);
    }

    public List<ListingVideo> getVideos() {
        return this.mVideos;
    }

    public ListingVideo getHighestQualityVideo() {
        for (ListingVideo listingVideo : getVideos()) {
            if (listingVideo.getQuality().equals(HIGH_QUALITY)) {
                return listingVideo;
            }
        }
        return (ListingVideo) getVideos().get(0);
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if ("files".equals(currentName)) {
                    this.mVideos = BaseModel.parseArray(jsonParser, ListingVideo.class);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
