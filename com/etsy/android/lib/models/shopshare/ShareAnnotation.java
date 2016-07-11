package com.etsy.android.lib.models.shopshare;

import com.etsy.android.lib.core.AtomicIdCounter;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.interfaces.BasicListingLike;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.io.Serializable;

public class ShareAnnotation extends BaseModel {
    static final int LISTING_OBJECT_TYPE = 5;
    private Coordinates mCoordinates;
    private BaseModel mObject;
    private EtsyId mObjectId;
    private int mObjectType;
    private Long mShareAnnotationId;

    public class Coordinates implements Serializable {
        public int f1904x;
        public int f1905y;

        public Coordinates() {
            this.f1904x = 0;
            this.f1905y = 0;
        }

        public Coordinates(int i, int i2) {
            this.f1904x = 0;
            this.f1905y = 0;
            this.f1904x = i;
            this.f1905y = i2;
        }
    }

    public static ShareAnnotation at(Coordinates coordinates) {
        ShareAnnotation shareAnnotation = new ShareAnnotation();
        shareAnnotation.mCoordinates = coordinates;
        shareAnnotation.setShareAnnotationId();
        return shareAnnotation;
    }

    public ShareAnnotation() {
        this.mCoordinates = new Coordinates();
        this.mObjectId = new EtsyId();
        setShareAnnotationId();
    }

    public BaseModel getObject() {
        return this.mObject;
    }

    public EtsyId getObjectId() {
        return this.mObjectId;
    }

    public int getObjectType() {
        return this.mObjectType;
    }

    public long getShareAnnotationId() {
        if (this.mShareAnnotationId == null) {
            setShareAnnotationId();
        }
        return this.mShareAnnotationId.longValue();
    }

    public void setShareAnnotationId() {
        if (this.mShareAnnotationId == null) {
            this.mShareAnnotationId = Long.valueOf(AtomicIdCounter.m1072a());
        }
    }

    public Coordinates getCoordinates() {
        return this.mCoordinates;
    }

    public int getCoordX() {
        return this.mCoordinates.f1904x;
    }

    public int getCoordY() {
        return this.mCoordinates.f1905y;
    }

    public boolean isObjectHydrated() {
        return this.mObject != null;
    }

    public ShareAnnotation setCoord(Coordinates coordinates) {
        this.mCoordinates = coordinates;
        return this;
    }

    public ShareAnnotation setListing(BasicListingLike basicListingLike) {
        this.mObjectId = basicListingLike.getListingId();
        this.mObjectType = LISTING_OBJECT_TYPE;
        return this;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case -1489595877:
                        if (currentName.equals(ResponseConstants.OBJECT_ID)) {
                            obj = null;
                            break;
                        }
                        break;
                    case -1277176774:
                        if (currentName.equals(ResponseConstants.OBJECT_TYPE)) {
                            obj = 1;
                            break;
                        }
                        break;
                    case -1023368385:
                        if (currentName.equals(ResponseConstants.OBJECT)) {
                            obj = 4;
                            break;
                        }
                        break;
                    case 952393134:
                        if (currentName.equals(ResponseConstants.COORD_X)) {
                            obj = 2;
                            break;
                        }
                        break;
                    case 952393135:
                        if (currentName.equals(ResponseConstants.COORD_Y)) {
                            obj = 3;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mObjectId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mObjectType = jsonParser.getValueAsInt();
                        break;
                    case Task.NETWORK_STATE_ANY /*2*/:
                        this.mCoordinates.f1904x = jsonParser.getValueAsInt();
                        break;
                    case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                        this.mCoordinates.f1905y = jsonParser.getValueAsInt();
                        break;
                    case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                        this.mObject = parseObject(jsonParser, this.mObjectType);
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }

    private BaseModel parseObject(JsonParser jsonParser, int i) {
        switch (i) {
            case LISTING_OBJECT_TYPE /*5*/:
                return BaseModel.parseObject(jsonParser, ListingCard.class);
            default:
                return null;
        }
    }
}
