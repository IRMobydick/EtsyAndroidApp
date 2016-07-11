package com.etsy.android.lib.models.apiv3;

import android.support.annotation.Nullable;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.User;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.finds.FindsModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class Collection extends BaseModel implements Comparable<Collection> {
    public static final String PRIVACY_LEVEL_PRIVATE = "private";
    public static final String PRIVACY_LEVEL_PUBLIC = "public";
    private static final String TAG;
    public static final String TYPE_COLLECTION = "collection";
    public static final String TYPE_FAVORITES = "favorites";
    private static final long serialVersionUID = -7526871588189118812L;
    private EtsyId mCollectionId;
    private User mCreator;
    private boolean mIsNew;
    private String mKey;
    private int mListingsCount;
    private String mName;
    private String mPrivacyLevel;
    private List<Listing> mRepresentativeListings;
    private String mSlug;
    private String mType;
    private String mUrl;

    static {
        TAG = EtsyDebug.m1891a(Collection.class);
    }

    public Collection() {
        this.mPrivacyLevel = StringUtils.EMPTY;
        this.mType = StringUtils.EMPTY;
        this.mName = StringUtils.EMPTY;
        this.mSlug = StringUtils.EMPTY;
        this.mKey = StringUtils.EMPTY;
        this.mUrl = StringUtils.EMPTY;
        this.mCreator = null;
        this.mIsNew = false;
        this.mListingsCount = 0;
        this.mCollectionId = new EtsyId();
        this.mRepresentativeListings = new ArrayList(0);
    }

    public Collection(Collection collection) {
        this.mPrivacyLevel = StringUtils.EMPTY;
        this.mType = StringUtils.EMPTY;
        this.mName = StringUtils.EMPTY;
        this.mSlug = StringUtils.EMPTY;
        this.mKey = StringUtils.EMPTY;
        this.mUrl = StringUtils.EMPTY;
        this.mCreator = null;
        this.mIsNew = false;
        this.mListingsCount = 0;
        this.mCollectionId = collection.getCollectionId();
        this.mPrivacyLevel = collection.mPrivacyLevel;
        this.mType = collection.mType;
        this.mName = collection.mName;
        this.mSlug = collection.mSlug;
        this.mKey = collection.mKey;
        this.mUrl = collection.mUrl;
        this.mCreator = collection.mCreator;
        this.mIsNew = collection.mIsNew;
        this.mListingsCount = collection.mListingsCount;
        this.mRepresentativeListings = collection.mRepresentativeListings;
    }

    public EtsyId getCollectionId() {
        return this.mCollectionId;
    }

    public String getPrivacyLevel() {
        return this.mPrivacyLevel;
    }

    public String getType() {
        return this.mType;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public String getSlug() {
        return this.mSlug;
    }

    public String getKey() {
        return this.mKey;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public User getCreator() {
        return this.mCreator;
    }

    public boolean getIsNew() {
        return this.mIsNew;
    }

    public void setIsNew(boolean z) {
        this.mIsNew = z;
    }

    public List<Listing> getRepresentativeListings() {
        return this.mRepresentativeListings;
    }

    public int getListingsCount() {
        return this.mListingsCount;
    }

    public boolean isPrivate() {
        return PRIVACY_LEVEL_PRIVATE.equalsIgnoreCase(this.mPrivacyLevel);
    }

    public boolean isPublic() {
        return PRIVACY_LEVEL_PUBLIC.equalsIgnoreCase(this.mPrivacyLevel);
    }

    public void setPrivacy(String str) {
        if (str.equals(PRIVACY_LEVEL_PUBLIC)) {
            this.mPrivacyLevel = PRIVACY_LEVEL_PUBLIC;
        } else {
            this.mPrivacyLevel = PRIVACY_LEVEL_PRIVATE;
        }
    }

    public boolean isTypeCollection() {
        return TYPE_COLLECTION.equalsIgnoreCase(this.mType);
    }

    public boolean isTypeFavorites() {
        return TYPE_FAVORITES.equalsIgnoreCase(this.mType);
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                parseCollectionField(jsonParser, currentName);
            }
        }
    }

    protected void parseCollectionField(JsonParser jsonParser, String str) {
        if (ResponseConstants.ID.equals(str) || "collection_id".equals(str)) {
            this.mCollectionId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
        } else if ("privacy_level".equals(str)) {
            this.mPrivacyLevel = BaseModel.parseString(jsonParser);
        } else if (FindsModule.FIELD_TYPE.equals(str)) {
            this.mType = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.NAME.equals(str)) {
            this.mName = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.SLUG.equals(str)) {
            this.mSlug = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.KEY.equals(str)) {
            this.mKey = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.URL.equals(str)) {
            this.mUrl = BaseModel.parseStringURL(jsonParser);
        } else if ("listings_count".equals(str)) {
            this.mListingsCount = jsonParser.getValueAsInt();
        } else if ("representative_listings".equals(str)) {
            this.mRepresentativeListings = BaseModel.parseArray(jsonParser, Listing.class);
        } else if ("creator".equals(str)) {
            this.mCreator = (User) BaseModel.parseObject(jsonParser, User.class);
        } else {
            EtsyDebug.m1908b(TAG, "Field %s not found on Collection Model", str);
            jsonParser.skipChildren();
        }
    }

    public int compareTo(Collection collection) {
        if (this == collection) {
            return 0;
        }
        if (this.mCreator == null && collection.mCreator != null) {
            return -1;
        }
        if (this.mCreator != null && collection.mCreator == null) {
            return 1;
        }
        if (this.mCreator == null || collection.mCreator == null || this.mCreator.getUserId().equals(collection.getCreator().getUserId())) {
            return this.mCollectionId.getId().compareTo(collection.getCollectionId().getId());
        }
        return this.mCreator.getUserId().getId().compareTo(collection.getCreator().getUserId().getId());
    }

    public int hashCode() {
        return this.mKey.hashCode();
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof Collection) && this.mKey.equals(((Collection) obj).getKey());
    }

    public void update(Collection collection) {
        update(collection, false);
    }

    public void update(Collection collection, boolean z) {
        this.mName = collection.mName;
        this.mPrivacyLevel = collection.mPrivacyLevel;
        this.mUrl = collection.mUrl;
        this.mSlug = collection.mSlug;
        if (z) {
            this.mListingsCount = collection.mListingsCount;
        }
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.COLLECTION_KEY, this.mKey);
        return hashMap;
    }
}
