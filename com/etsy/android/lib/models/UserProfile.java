package com.etsy.android.lib.models;

import com.etsy.android.lib.models.ResponseConstants.Includes;
import com.etsy.android.lib.models.apiv3.UserAvatar;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;

public class UserProfile extends BaseModel {
    private static final long serialVersionUID = -8560910552168722074L;
    private String mBio;
    private String mCity;
    private Country mCountry;
    private String mFirstName;
    private String mGender;
    private boolean mIsSeller;
    private boolean mIsTestAccount;
    private Date mJoinDate;
    private String mLastName;
    private String mLoginName;
    private int mNumFavorites;
    private UserAvatar mUserAvatar;

    public UserProfile() {
        this.mFirstName = StringUtils.EMPTY;
        this.mLastName = StringUtils.EMPTY;
        this.mLoginName = StringUtils.EMPTY;
        this.mGender = StringUtils.EMPTY;
        this.mBio = StringUtils.EMPTY;
        this.mCity = StringUtils.EMPTY;
    }

    public String getBio() {
        return this.mBio;
    }

    public String getCity() {
        return this.mCity;
    }

    public boolean isSeller() {
        return this.mIsSeller;
    }

    public int getNumFavorites() {
        return this.mNumFavorites;
    }

    public String getImageUrl75x75() {
        return this.mUserAvatar != null ? this.mUserAvatar.getImageUrl() : null;
    }

    public String getFirstName() {
        return this.mFirstName;
    }

    public String getLastName() {
        return this.mLastName;
    }

    public Country getCountry() {
        return this.mCountry;
    }

    public Date getJoinDate() {
        return this.mJoinDate;
    }

    public String getLoginName() {
        return this.mLoginName;
    }

    public String getAvailableGender() {
        return this.mGender;
    }

    public boolean isTestAccount() {
        return this.mIsTestAccount;
    }

    public String getAvatarUrl(int i) {
        if (this.mUserAvatar != null) {
            return this.mUserAvatar.getImageUrlForPixelWidth(i);
        }
        return null;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.FIRST_NAME.equals(currentName)) {
                    this.mFirstName = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.LAST_NAME.equals(currentName)) {
                    this.mLastName = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.LOGIN_NAME.equals(currentName)) {
                    this.mLoginName = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.BIO.equals(currentName)) {
                    this.mBio = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.CITY.equals(currentName)) {
                    this.mCity = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.IS_SELLER.equals(currentName)) {
                    this.mIsSeller = jsonParser.getValueAsBoolean();
                } else if ("num_favorites".equals(currentName)) {
                    this.mNumFavorites = jsonParser.getValueAsInt();
                } else if ("image_url_75x75".equals(currentName)) {
                    this.mUserAvatar = new UserAvatar(BaseModel.parseStringURL(jsonParser));
                } else if ("gender".equals(currentName)) {
                    this.mGender = BaseModel.parseString(jsonParser);
                } else if (Includes.COUNTRY.equals(currentName)) {
                    this.mCountry = (Country) BaseModel.parseObject(jsonParser, Country.class);
                } else if ("join_tsz".equals(currentName)) {
                    this.mJoinDate = BaseModel.parseIntoDate(jsonParser);
                } else if ("is_test_account".equals(currentName)) {
                    this.mIsTestAccount = jsonParser.getValueAsBoolean();
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
