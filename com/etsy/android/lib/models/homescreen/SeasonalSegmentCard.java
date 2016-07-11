package com.etsy.android.lib.models.homescreen;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.Segment;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.fasterxml.jackson.core.JsonParser;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class SeasonalSegmentCard extends Segment {
    String mLinkTitle;
    List<ListingCard> mListingCards;

    public SeasonalSegmentCard() {
        this.mLinkTitle = StringUtils.EMPTY;
    }

    protected void parseField(JsonParser jsonParser, String str) {
        if (ResponseConstants.DISPLAY_LISTINGS.equals(str)) {
            this.mListingCards = BaseModel.parseArray(jsonParser, ListingCard.class);
        } else if (ResponseConstants.LINK_TITLE.equals(str)) {
            this.mLinkTitle = BaseModel.parseString(jsonParser);
        } else {
            super.parseField(jsonParser, str);
        }
    }

    public String getLinkTitle() {
        return this.mLinkTitle;
    }

    public List<? extends ListingLike> getListings() {
        return this.mListingCards;
    }
}
