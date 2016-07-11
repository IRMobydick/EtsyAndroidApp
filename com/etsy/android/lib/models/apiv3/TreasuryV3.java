package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.finds.FindsModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class TreasuryV3 extends BaseModel {
    private static final String CLICKS = "clicks";
    private static final String LISTINGS = "listings";
    private static final String LISTINGS_COUNT = "listings_count";
    private static final String OWNER = "owner";
    private static final String OWNER_ID = "owner_id";
    private static final String OWNER_NAME = "owner_name";
    private static final long serialVersionUID = -1221459116670649185L;
    protected int mClicks;
    protected String mDescription;
    protected String mId;
    protected List<ListingCard> mListings;
    protected int mListingsCount;
    protected UserCard mOwner;
    protected EtsyId mOwnerId;
    protected String mOwnerName;
    protected String mTitle;
    protected String mUrl;
    protected int mViews;

    public TreasuryV3() {
        this.mId = StringUtils.EMPTY;
        this.mTitle = StringUtils.EMPTY;
        this.mDescription = StringUtils.EMPTY;
        this.mOwnerId = new EtsyId();
        this.mOwnerName = StringUtils.EMPTY;
        this.mUrl = StringUtils.EMPTY;
        this.mListings = new ArrayList(0);
    }

    public String getId() {
        return this.mId;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public List<ListingCard> getListings() {
        return this.mListings;
    }

    public String getOwnerName() {
        return this.mOwnerName;
    }

    public int getListingsCount() {
        return this.mListingsCount;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.ID.equals(currentName)) {
                    this.mId = BaseModel.parseStringIdOrNumericValue(jsonParser);
                } else if (FindsModule.FIELD_TITLE.equals(currentName)) {
                    this.mTitle = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.DESCRIPTION.equals(currentName)) {
                    this.mDescription = BaseModel.parseString(jsonParser);
                } else if (OWNER_ID.equals(currentName)) {
                    this.mOwnerId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (OWNER_NAME.equals(currentName)) {
                    this.mOwnerName = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.VIEWS.equals(currentName)) {
                    this.mViews = jsonParser.getIntValue();
                } else if (CLICKS.equals(currentName)) {
                    this.mClicks = jsonParser.getIntValue();
                } else if (ResponseConstants.URL.equals(currentName)) {
                    this.mUrl = BaseModel.parseStringURL(jsonParser);
                } else if (OWNER.equals(currentName)) {
                    this.mOwner = (UserCard) BaseModel.parseObject(jsonParser, UserCard.class);
                } else if (LISTINGS_COUNT.equals(currentName)) {
                    this.mListingsCount = jsonParser.getIntValue();
                } else if (LISTINGS.equals(currentName)) {
                    this.mListings = BaseModel.parseArray(jsonParser, ListingCard.class);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
