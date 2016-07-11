package com.etsy.android.lib.models.finds;

import android.support.annotation.NonNull;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.models.BaseFieldModel;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.models.homescreen.LandingPageLink;
import com.etsy.android.uikit.cardview.ICardViewElement;
import com.fasterxml.jackson.core.JsonParser;
import java.util.ArrayList;
import java.util.List;
import org.parceler.Parcel;

@Parcel
public class FindsTwoTitledListingsModule extends FindsModule {

    @Parcel
    public class Footer extends FindsModule {
        protected String mBottomText;
        protected FindsUrl mTitleLink;
        protected String mUrl;

        public int getViewType() {
            return 25;
        }

        public boolean canDisplay() {
            if (EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.f1195N)) {
                if (this.mBottomText == null || this.mBottomText.length() <= 0 || this.mUrl == null) {
                    return false;
                }
                return true;
            } else if (this.mBottomText == null || this.mBottomText.length() <= 0 || this.mTitleLink == null) {
                return false;
            } else {
                return true;
            }
        }

        public String getBottomText() {
            return this.mBottomText;
        }

        public FindsUrl getTitleLink() {
            return this.mTitleLink;
        }

        public String getUrl() {
            return this.mUrl;
        }
    }

    @Parcel
    public class Header extends FindsHeadingModule {
        public int getViewType() {
            return 24;
        }
    }

    @Parcel
    public class Listing extends ListingCard {
        public int getViewType() {
            return 23;
        }
    }

    @Parcel
    public class Section extends BaseFieldModel {
        private static String FIELD_BOTTOM_TEXT;
        private static String FIELD_LISTINGS;
        private static String FIELD_TITLE;
        private static String FIELD_TITLE_LINK;
        private static String FIELD_URL;
        protected Footer mFooter;
        protected Header mHeader;
        protected List<Listing> mListings;

        static {
            FIELD_TITLE = FindsModule.FIELD_TITLE;
            FIELD_LISTINGS = LandingPageLink.PAGE_TYPE_LISTINGS;
            FIELD_BOTTOM_TEXT = "bottom_text";
            FIELD_TITLE_LINK = "title_link";
            FIELD_URL = ResponseConstants.URL;
        }

        public Section() {
            this.mHeader = new Header();
            this.mFooter = new Footer();
        }

        protected boolean parseField(JsonParser jsonParser, @NonNull String str) {
            if (FIELD_TITLE.equals(str)) {
                this.mHeader.mText = BaseModel.parseString(jsonParser);
            } else if (FIELD_LISTINGS.equals(str)) {
                this.mListings = BaseModel.parseArray(jsonParser, Listing.class);
            } else if (FIELD_TITLE_LINK.equals(str)) {
                this.mFooter.mTitleLink = (FindsUrl) BaseModel.parseObject(jsonParser, FindsUrl.class);
            } else if (FIELD_BOTTOM_TEXT.equals(str)) {
                this.mFooter.mBottomText = BaseModel.parseString(jsonParser);
            } else if (!FIELD_URL.equals(str)) {
                return false;
            } else {
                this.mFooter.mUrl = BaseModel.parseStringURL(jsonParser);
            }
            return true;
        }
    }

    @Parcel
    public class Spacer extends FindsModule {
        public int getViewType() {
            return 26;
        }
    }

    protected void setFromGeneric(FindsModule findsModule) {
        super.setFromGeneric(findsModule);
        this.mSections = findsModule.mSections;
    }

    public List<? extends ICardViewElement> getCardViewElements() {
        return getCardViewElements(false);
    }

    public List<? extends ICardViewElement> getCardViewElements(boolean z) {
        List<? extends ICardViewElement> arrayList = new ArrayList();
        Section section;
        if (z) {
            for (Section section2 : this.mSections) {
                arrayList.add(section2.mHeader);
                arrayList.addAll(section2.mListings);
                arrayList.add(section2.mFooter);
            }
        } else {
            section2 = (Section) this.mSections.get(0);
            Section section3 = (Section) this.mSections.get(1);
            arrayList.add(section2.mHeader);
            arrayList.add(new Spacer());
            arrayList.add(section3.mHeader);
            arrayList.addAll(section2.mListings.subList(0, 2));
            arrayList.add(new Spacer());
            arrayList.addAll(section3.mListings.subList(0, 2));
            arrayList.addAll(section2.mListings.subList(2, 4));
            arrayList.add(new Spacer());
            arrayList.addAll(section3.mListings.subList(2, 4));
            arrayList.add(section2.mFooter);
            arrayList.add(new Spacer());
            arrayList.add(section3.mFooter);
        }
        return arrayList;
    }
}
