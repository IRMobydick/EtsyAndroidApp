package com.etsy.android.lib.models;

import com.etsy.android.lib.models.datatypes.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.commons.lang3.StringUtils;

public class ShippingInfo extends BaseModel {
    private static final long serialVersionUID = -1028306612152637887L;
    private String mConvertedCurrencyCode;
    private float mConvertedPrimaryCost;
    private float mConvertedSecondaryCost;
    private String mCurrencyCode;
    private Country mDestinationCountry;
    private String mDestinationCountryName;
    private EtsyId mId;
    private Country mOriginCountry;
    private String mOriginCountryName;
    private float mPrimaryCost;
    private Region mRegion;
    private int mRegionId;
    private float mSecondaryCost;

    public ShippingInfo() {
        this.mId = new EtsyId();
        this.mCurrencyCode = StringUtils.EMPTY;
        this.mConvertedCurrencyCode = StringUtils.EMPTY;
        this.mOriginCountryName = StringUtils.EMPTY;
        this.mDestinationCountryName = StringUtils.EMPTY;
    }

    public String getCurrencyCode() {
        return this.mConvertedCurrencyCode.equals(StringUtils.EMPTY) ? this.mCurrencyCode : this.mConvertedCurrencyCode;
    }

    public float getPrimaryCost() {
        return this.mConvertedCurrencyCode.equals(StringUtils.EMPTY) ? this.mPrimaryCost : this.mConvertedPrimaryCost;
    }

    public float getSecondaryCost() {
        return this.mConvertedCurrencyCode.equals(StringUtils.EMPTY) ? this.mSecondaryCost : this.mConvertedSecondaryCost;
    }

    public boolean isConverted() {
        return !this.mConvertedCurrencyCode.equals(StringUtils.EMPTY);
    }

    public int getRegionId() {
        return this.mRegionId;
    }

    public String getOriginCountryName() {
        return this.mOriginCountryName;
    }

    public String getDestinationCountryName() {
        return this.mDestinationCountryName;
    }

    public Region getRegion() {
        return this.mRegion;
    }

    public Country getDestinationCountry() {
        return this.mDestinationCountry;
    }

    public Country getOriginCountry() {
        return this.mOriginCountry;
    }

    public boolean shipsEverywhere() {
        return this.mDestinationCountry == null;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if ("shipping_info_id".equals(currentName)) {
                    this.mId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.CURRENCY_CODE.equals(currentName)) {
                    this.mCurrencyCode = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.CONVERTED_CURRENCY.equals(currentName)) {
                    this.mConvertedCurrencyCode = BaseModel.parseString(jsonParser);
                } else if ("primary_cost".equals(currentName)) {
                    this.mPrimaryCost = (float) jsonParser.getValueAsDouble();
                } else if ("secondary_cost".equals(currentName)) {
                    this.mSecondaryCost = (float) jsonParser.getValueAsDouble();
                } else if ("converted_primary_cost".equals(currentName)) {
                    this.mConvertedPrimaryCost = (float) jsonParser.getValueAsDouble();
                } else if ("converted_secondary_cost".equals(currentName)) {
                    this.mConvertedSecondaryCost = (float) jsonParser.getValueAsDouble();
                } else if ("region_id".equals(currentName)) {
                    this.mRegionId = jsonParser.getValueAsInt();
                } else if ("origin_country_name".equals(currentName)) {
                    this.mOriginCountryName = BaseModel.parseString(jsonParser);
                } else if ("destination_country_name".equals(currentName)) {
                    this.mDestinationCountryName = BaseModel.parseString(jsonParser).trim();
                } else if ("Region".equals(currentName)) {
                    this.mRegion = (Region) BaseModel.parseObject(jsonParser, Region.class);
                } else if ("DestinationCountry".equals(currentName)) {
                    this.mDestinationCountry = (Country) BaseModel.parseObject(jsonParser, Country.class);
                } else if ("OriginCountry".equals(currentName)) {
                    this.mOriginCountry = (Country) BaseModel.parseObject(jsonParser, Country.class);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
