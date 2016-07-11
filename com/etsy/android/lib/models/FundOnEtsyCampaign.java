package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.etsy.android.lib.logger.EtsyDebug;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.Date;
import org.apache.commons.lang3.StringEscapeUtils;

public class FundOnEtsyCampaign extends BaseModel implements Parcelable {
    public static final Creator<FundOnEtsyCampaign> CREATOR;
    public static final String DRAFT = "DRAFT";
    public static final String FUNDING = "FUNDING";
    public static final String SUCCESSFUL = "COMPLETE_SUCCESSFUL_INACTIVE";
    private static final String TAG;
    public static final String UNSUCCESFFUL = "COMPLETE_UNSUCCESFUL";
    private String mAmountFunded;
    private Date mFundingEndTime;
    private String mFundingGoal;
    private Date mFundingStartTime;
    private boolean mIsOptionsSoldOut;
    private String mMission;
    private int mNumberOfFollowers;
    private int mPercentFunded;
    private String mState;
    private int mTimeLeftNumber;
    private String mTimeLeftUnit;

    /* renamed from: com.etsy.android.lib.models.FundOnEtsyCampaign.1 */
    final class C04741 implements Creator<FundOnEtsyCampaign> {
        C04741() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m2319a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m2320a(i);
        }

        public FundOnEtsyCampaign m2319a(Parcel parcel) {
            return new FundOnEtsyCampaign(parcel);
        }

        public FundOnEtsyCampaign[] m2320a(int i) {
            return new FundOnEtsyCampaign[i];
        }
    }

    static {
        TAG = EtsyDebug.m1891a(FundOnEtsyCampaign.class);
        CREATOR = new C04741();
    }

    public FundOnEtsyCampaign() {
        this.mNumberOfFollowers = 0;
    }

    public FundOnEtsyCampaign(Parcel parcel) {
        this();
        readFromParcel(parcel);
    }

    public String getFundingGoal() {
        return this.mFundingGoal;
    }

    public String getAmountFunded() {
        return this.mAmountFunded;
    }

    public int getPercentFunded() {
        return this.mPercentFunded;
    }

    public Date getFundingStartTime() {
        return this.mFundingStartTime;
    }

    public Date getFundingEndTime() {
        return this.mFundingEndTime;
    }

    public String getMission() {
        return this.mMission;
    }

    public String getState() {
        return this.mState;
    }

    public boolean getIsOptionsSoldOut() {
        return this.mIsOptionsSoldOut;
    }

    public int getNumberOfFollowers() {
        return this.mNumberOfFollowers;
    }

    public void setNumberOfFollowers(int i) {
        this.mNumberOfFollowers = i;
    }

    public String getTimeLeftUnit() {
        return this.mTimeLeftUnit;
    }

    public int getTimeLeftNumber() {
        return this.mTimeLeftNumber;
    }

    public boolean isFunding() {
        if (this.mState != null) {
            return this.mState.equals(FUNDING);
        }
        return false;
    }

    public boolean isSuccessful() {
        if (this.mState != null) {
            return this.mState.equals(SUCCESSFUL);
        }
        return false;
    }

    public boolean isDraft() {
        return DRAFT.equals(this.mState);
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if ("funding_goal".equals(currentName)) {
                    this.mFundingGoal = BaseModel.parseString(jsonParser);
                } else if ("amount_funded".equals(currentName)) {
                    this.mAmountFunded = BaseModel.parseString(jsonParser);
                } else if ("percent_funded".equals(currentName)) {
                    this.mPercentFunded = jsonParser.getValueAsInt();
                } else if ("funding_start_time".equals(currentName)) {
                    this.mFundingStartTime = new Date(((long) jsonParser.getValueAsInt()) * 1000);
                } else if ("funding_end_time".equals(currentName)) {
                    this.mFundingEndTime = new Date(((long) jsonParser.getValueAsInt()) * 1000);
                } else if ("mission".equals(currentName)) {
                    this.mMission = StringEscapeUtils.unescapeHtml4(BaseModel.parseString(jsonParser));
                } else if (ResponseConstants.STATE.equals(currentName)) {
                    this.mState = BaseModel.parseString(jsonParser);
                } else if ("is_options_sold_out".equals(currentName)) {
                    this.mIsOptionsSoldOut = jsonParser.getValueAsBoolean();
                } else if ("number_of_followers".equals(currentName)) {
                    this.mNumberOfFollowers = jsonParser.getValueAsInt();
                } else if ("time_left_number".equals(currentName)) {
                    this.mTimeLeftNumber = jsonParser.getValueAsInt();
                } else if ("time_left_unit".equals(currentName)) {
                    this.mTimeLeftUnit = BaseModel.parseString(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public int describeContents() {
        return hashCode();
    }

    private void readFromParcel(Parcel parcel) {
        Date date = null;
        this.mFundingGoal = parcel.readString();
        this.mAmountFunded = parcel.readString();
        this.mPercentFunded = parcel.readInt();
        this.mFundingStartTime = parcel.readLong() == 0 ? null : new Date(parcel.readLong());
        if (parcel.readLong() != 0) {
            date = new Date(parcel.readLong());
        }
        this.mFundingEndTime = date;
        this.mMission = parcel.readString();
        this.mState = parcel.readString();
        this.mIsOptionsSoldOut = parcel.readInt() == 1;
        this.mNumberOfFollowers = parcel.readInt();
        this.mTimeLeftNumber = parcel.readInt();
        this.mTimeLeftUnit = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        long j = 0;
        parcel.writeString(this.mFundingGoal);
        parcel.writeString(this.mAmountFunded);
        parcel.writeInt(this.mPercentFunded);
        parcel.writeLong(this.mFundingStartTime != null ? this.mFundingStartTime.getTime() : 0);
        if (this.mFundingEndTime != null) {
            j = this.mFundingEndTime.getTime();
        }
        parcel.writeLong(j);
        parcel.writeString(this.mMission);
        parcel.writeString(this.mState);
        parcel.writeInt(this.mIsOptionsSoldOut ? 1 : 0);
        parcel.writeInt(this.mNumberOfFollowers);
        parcel.writeInt(this.mTimeLeftNumber);
        parcel.writeString(this.mTimeLeftUnit);
    }
}
