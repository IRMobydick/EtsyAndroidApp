package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class FundOnEtsyCampaign extends BaseModel implements Parcelable {
    public static final Creator<FundOnEtsyCampaign> CREATOR;
    public static final String FUNDING = "FUNDING";
    public static final String SUCCESSFUL = "COMPLETE_SUCCESSFUL_INACTIVE";
    private static final String TAG;
    public static final String UNSUCCESFFUL = "COMPLETE_UNSUCCESFUL";
    private String mAmountFunded;
    private String mFundingGoal;
    private int mNumberOfFollowers;
    private int mPercentFunded;
    private String mState;
    private int mTimeLeftNumber;
    private String mTimeLeftUnit;

    /* renamed from: com.etsy.android.lib.models.apiv3.FundOnEtsyCampaign.1 */
    final class C04811 implements Creator<FundOnEtsyCampaign> {
        C04811() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m2367a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m2368a(i);
        }

        public FundOnEtsyCampaign m2367a(Parcel parcel) {
            FundOnEtsyCampaign fundOnEtsyCampaign = new FundOnEtsyCampaign();
            fundOnEtsyCampaign.readFromParcel(parcel);
            return fundOnEtsyCampaign;
        }

        public FundOnEtsyCampaign[] m2368a(int i) {
            return new FundOnEtsyCampaign[i];
        }
    }

    static {
        TAG = EtsyDebug.m1891a(FundOnEtsyCampaign.class);
        CREATOR = new C04811();
    }

    public FundOnEtsyCampaign() {
        this.mNumberOfFollowers = 0;
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

    public String getState() {
        return this.mState;
    }

    public int getNumberOfFollowers() {
        return this.mNumberOfFollowers;
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
                } else if (ResponseConstants.STATE.equals(currentName)) {
                    this.mState = BaseModel.parseString(jsonParser);
                } else if ("number_followers".equals(currentName)) {
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
        return 0;
    }

    private void readFromParcel(Parcel parcel) {
        this.mFundingGoal = parcel.readString();
        this.mAmountFunded = parcel.readString();
        this.mPercentFunded = parcel.readInt();
        this.mState = parcel.readString();
        this.mNumberOfFollowers = parcel.readInt();
        this.mTimeLeftNumber = parcel.readInt();
        this.mTimeLeftUnit = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mFundingGoal);
        parcel.writeString(this.mAmountFunded);
        parcel.writeInt(this.mPercentFunded);
        parcel.writeString(this.mState);
        parcel.writeInt(this.mNumberOfFollowers);
        parcel.writeInt(this.mTimeLeftNumber);
        parcel.writeString(this.mTimeLeftUnit);
    }
}
