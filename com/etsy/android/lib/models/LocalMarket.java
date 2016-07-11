package com.etsy.android.lib.models;

import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.apiv3.StructuredShopShippingEstimate;
import com.etsy.android.lib.models.apiv3.Team;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.TimeRange;
import com.etsy.android.lib.models.enums.WeekDay;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.lib.models.interfaces.LocalMarketLike;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.maps.model.LatLng;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class LocalMarket extends BaseModel implements LocalMarketLike {
    private static final SimpleDateFormat DATE_FORMAT;
    private static final String HAPPENING_STATUS_BETWEEN_START_AND_END = "between_start_and_end_date";
    private static final String HAPPENING_STATUS_EXPIRED = "expired";
    private static final String HAPPENING_STATUS_NOW = "happening_now";
    private static final String HAPPENING_STATUS_UPCOMING = "upcoming";
    public static final String MARKET_TYPE_SELLER_EVENT = "seller_event";
    public static final String MARKET_TYPE_WHOLESALE_BUYER = "wholesale_buyer";
    protected String mAddress1;
    protected String mAddress2;
    protected String mAndroidMapImageUrl;
    protected List<LocalMarketCard> mChildLocalMarkets;
    protected String mCity;
    protected String mCountry;
    protected String mCountryCode;
    protected String mDescription;
    protected String mDetailsUrl;
    protected Date mEndDate;
    protected String mExternalUrl;
    protected List<Attendee> mFavoriteAttendees;
    protected String mHappeningStatus;
    protected List<ListingImage> mImages;
    protected String mLat;
    protected EtsyId mLocalMarketId;
    protected String mLon;
    protected String mNextOpenText;
    protected List<Attendee> mOtherAttendees;
    protected List<Attendee> mPastAttendees;
    protected ScheduleExpanded mScheduleExpanded;
    protected List<TimeRange> mScheduleRollups;
    protected Date mStartDate;
    protected String mState;
    protected LocalStore mStore;
    protected Team mTeam;
    protected String mTimezone;
    protected String mTitle;
    protected String mType;
    protected List<Attendee> mUpcomingAttendees;
    protected String mVenueName;
    protected String mZip;

    static {
        DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    }

    public LocalMarket() {
        this.mLat = StringUtils.EMPTY;
        this.mLon = StringUtils.EMPTY;
        this.mVenueName = StringUtils.EMPTY;
        this.mAddress1 = StringUtils.EMPTY;
        this.mAddress2 = StringUtils.EMPTY;
        this.mCity = StringUtils.EMPTY;
        this.mState = StringUtils.EMPTY;
        this.mZip = StringUtils.EMPTY;
        this.mCountry = StringUtils.EMPTY;
        this.mCountryCode = StringUtils.EMPTY;
        this.mTimezone = StringUtils.EMPTY;
        this.mTitle = StringUtils.EMPTY;
        this.mDescription = StringUtils.EMPTY;
        this.mExternalUrl = StringUtils.EMPTY;
        this.mDetailsUrl = StringUtils.EMPTY;
        this.mAndroidMapImageUrl = StringUtils.EMPTY;
        this.mHappeningStatus = StringUtils.EMPTY;
        this.mNextOpenText = StringUtils.EMPTY;
        this.mType = StringUtils.EMPTY;
        this.mLocalMarketId = new EtsyId();
        this.mScheduleRollups = new ArrayList();
        this.mOtherAttendees = new ArrayList();
        this.mFavoriteAttendees = new ArrayList();
        this.mUpcomingAttendees = new ArrayList();
        this.mPastAttendees = new ArrayList();
        this.mChildLocalMarkets = new ArrayList();
        this.mImages = new ArrayList();
    }

    public EtsyId getLocalMarketId() {
        return this.mLocalMarketId;
    }

    public Date getStartDate() {
        return this.mStartDate;
    }

    public Date getEndDate() {
        return this.mEndDate;
    }

    private Date toDate(String str) {
        try {
            return DATE_FORMAT.parse(str);
        } catch (ParseException e) {
            return null;
        }
    }

    public boolean isOneDayEvent() {
        return this.mStartDate.equals(this.mEndDate);
    }

    public String getLat() {
        return this.mLat;
    }

    public String getLon() {
        return this.mLon;
    }

    public LatLng getLatLng() {
        return new LatLng(Double.valueOf(getLat()).doubleValue(), Double.valueOf(getLon()).doubleValue());
    }

    public String getVenueName() {
        return this.mVenueName;
    }

    public String getAddress1() {
        return this.mAddress1;
    }

    public String getAddress2() {
        return this.mAddress2;
    }

    public String getCity() {
        return this.mCity;
    }

    public String getState() {
        return this.mState;
    }

    public String getZip() {
        return this.mZip;
    }

    public String getCountry() {
        return this.mCountry;
    }

    public String getCountryCode() {
        return this.mCountryCode;
    }

    public String getTimezone() {
        return this.mTimezone;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public List<TimeRange> getScheduleRollups() {
        return this.mScheduleRollups;
    }

    public String getExternalUrl() {
        return this.mExternalUrl;
    }

    public Boolean hasExternalUrl() {
        boolean z = this.mExternalUrl != null && this.mExternalUrl.length() > 0;
        return Boolean.valueOf(z);
    }

    public String getDetailsUrl() {
        return this.mDetailsUrl;
    }

    public String getMarketType() {
        return this.mType;
    }

    public String getMarketTypeLabelString(Resources resources) {
        return isWholesaleStore() ? resources.getString(R.local_store) : resources.getString(R.local_event);
    }

    public String getNextOpenText() {
        return this.mNextOpenText;
    }

    public List<ListingImage> getAttendeeListingImages() {
        return this.mImages;
    }

    public String getSellingStatusString(Resources resources) {
        boolean isEmpty = TextUtils.isEmpty(getCity());
        String str = this.mHappeningStatus;
        int i = -1;
        switch (str.hashCode()) {
            case 1306691868:
                if (str.equals(HAPPENING_STATUS_UPCOMING)) {
                    i = 2;
                    break;
                }
                break;
            case 1576643662:
                if (str.equals(HAPPENING_STATUS_BETWEEN_START_AND_END)) {
                    i = 1;
                    break;
                }
                break;
            case 1769390999:
                if (str.equals(HAPPENING_STATUS_NOW)) {
                    i = 0;
                    break;
                }
                break;
        }
        switch (i) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                if (isEmpty) {
                    return resources.getString(R.local_selling_now_in_person);
                }
                return resources.getString(R.local_selling_now, new Object[]{r3});
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                if (isEmpty) {
                    return resources.getString(R.local_selling_in_person);
                }
                return resources.getString(R.local_selling_in, new Object[]{r3});
            case Task.NETWORK_STATE_ANY /*2*/:
                if (isEmpty) {
                    return resources.getString(R.local_selling_soon_in_person);
                }
                return resources.getString(R.local_selling_soon, new Object[]{r3});
            default:
                return StringUtils.EMPTY;
        }
    }

    public Calendar getCurrentLocalEventTime() {
        if (TextUtils.isEmpty(this.mTimezone)) {
            return Calendar.getInstance();
        }
        return Calendar.getInstance(TimeZone.getTimeZone(this.mTimezone));
    }

    public TimeRange getTimeRangeForDay(Calendar calendar) {
        if (this.mScheduleExpanded == null) {
            return null;
        }
        return this.mScheduleExpanded.getTimeRange(WeekDay.getDay(calendar));
    }

    public TimeRange getTimeRangeToday() {
        return getTimeRangeForDay(getCurrentLocalEventTime());
    }

    public boolean hasDetailedSchedule() {
        return (this.mScheduleExpanded == null || this.mScheduleRollups == null) ? false : true;
    }

    public boolean isHappeningNow() {
        return this.mHappeningStatus.equals(HAPPENING_STATUS_NOW);
    }

    public boolean isBetweenStartAndEnd() {
        return this.mHappeningStatus.equals(HAPPENING_STATUS_BETWEEN_START_AND_END);
    }

    public String getAndroidMapImageUrl() {
        return this.mAndroidMapImageUrl;
    }

    public List<Attendee> getOtherAttendees() {
        return this.mOtherAttendees;
    }

    public List<Attendee> getFavoriteAttendees() {
        return this.mFavoriteAttendees;
    }

    public List<Attendee> getUpcomingAttendees() {
        return this.mUpcomingAttendees;
    }

    public List<Attendee> getPastAttendees() {
        return this.mPastAttendees;
    }

    public Team getOrganizingTeam() {
        return this.mTeam;
    }

    public boolean isOrganizedByTeam() {
        return this.mTeam != null;
    }

    public LocalStore getStore() {
        return this.mStore;
    }

    public boolean isWholesaleStore() {
        return MARKET_TYPE_WHOLESALE_BUYER.equals(this.mType);
    }

    public boolean hasCity() {
        return !TextUtils.isEmpty(this.mCity);
    }

    public List<LocalMarketCard> getChildLocalMarkets() {
        return this.mChildLocalMarkets;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.LOCAL_MARKET_ID.equals(currentName)) {
                    this.mLocalMarketId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if ("start_date_ymd".equals(currentName)) {
                    this.mStartDate = toDate(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if ("end_date_ymd".equals(currentName)) {
                    this.mEndDate = toDate(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.LATITUDE.equals(currentName)) {
                    this.mLat = BaseModel.parseStringIdOrNumericValue(jsonParser);
                } else if (ResponseConstants.LONGITUDE.equals(currentName)) {
                    this.mLon = BaseModel.parseStringIdOrNumericValue(jsonParser);
                } else if ("venue_name".equals(currentName)) {
                    this.mVenueName = BaseModel.parseString(jsonParser);
                } else if ("address_first_line".equals(currentName)) {
                    this.mAddress1 = BaseModel.parseString(jsonParser);
                } else if ("address_second_line".equals(currentName)) {
                    this.mAddress2 = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.CITY.equals(currentName)) {
                    this.mCity = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.STATE.equals(currentName)) {
                    this.mState = BaseModel.parseString(jsonParser);
                } else if ("zipcode".equals(currentName)) {
                    this.mZip = BaseModel.parseString(jsonParser);
                } else if (StructuredShopShippingEstimate.TYPE_COUNTRY.equals(currentName)) {
                    this.mCountry = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.COUNTRY_CODE.equals(currentName)) {
                    this.mCountryCode = BaseModel.parseString(jsonParser);
                } else if ("timezone".equals(currentName)) {
                    this.mTimezone = BaseModel.parseString(jsonParser);
                } else if (FindsModule.FIELD_TITLE.equals(currentName)) {
                    this.mTitle = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.MARKET_TYPE.equals(currentName)) {
                    this.mType = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.DESCRIPTION.equals(currentName)) {
                    this.mDescription = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.HAPPENING_STATUS.equals(currentName)) {
                    this.mHappeningStatus = BaseModel.parseString(jsonParser);
                } else if ("schedule_rollup".equals(currentName)) {
                    this.mScheduleRollups = BaseModel.parseArray(jsonParser, TimeRange.class);
                } else if ("schedule_expanded".equals(currentName)) {
                    this.mScheduleExpanded = (ScheduleExpanded) BaseModel.parseObject(jsonParser, ScheduleExpanded.class);
                } else if ("external_url".equals(currentName)) {
                    this.mExternalUrl = BaseModel.parseString(jsonParser);
                } else if ("details_page_url".equals(currentName)) {
                    this.mDetailsUrl = BaseModel.parseString(jsonParser);
                } else if ("next_open_text".equals(currentName)) {
                    this.mNextOpenText = BaseModel.parseString(jsonParser);
                } else if ("android_map_image_url".equals(currentName)) {
                    this.mAndroidMapImageUrl = BaseModel.parseStringURL(jsonParser);
                } else if ("attendees".equals(currentName)) {
                    this.mOtherAttendees = BaseModel.parseArray(jsonParser, Attendee.class);
                } else if (FindsModule.FIELD_IMAGES.equals(currentName)) {
                    this.mImages = BaseModel.parseArray(jsonParser, ListingImage.class);
                } else if ("favorite_attendees".equals(currentName)) {
                    this.mFavoriteAttendees = BaseModel.parseArray(jsonParser, Attendee.class);
                } else if (ResponseConstants.UPCOMING_SHOP_ATTENDEES.equals(currentName)) {
                    this.mUpcomingAttendees = BaseModel.parseArray(jsonParser, Attendee.class);
                } else if (ResponseConstants.PAST_SHOP_ATTENDEES.equals(currentName)) {
                    this.mPastAttendees = BaseModel.parseArray(jsonParser, Attendee.class);
                } else if (ResponseConstants.TEAM_ORGANIZER.equals(currentName)) {
                    this.mTeam = (Team) BaseModel.parseObject(jsonParser, Team.class);
                } else if (ResponseConstants.WHOLESALE_STORE.equals(currentName)) {
                    this.mStore = (LocalStore) BaseModel.parseObject(jsonParser, LocalStore.class);
                } else if (ResponseConstants.CHILD_LOCAL_MARKETS.equals(currentName)) {
                    this.mChildLocalMarkets = BaseModel.parseArray(jsonParser, LocalMarketCard.class);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.LOCAL_MARKET_ID, this.mLocalMarketId.getId());
        return hashMap;
    }
}
