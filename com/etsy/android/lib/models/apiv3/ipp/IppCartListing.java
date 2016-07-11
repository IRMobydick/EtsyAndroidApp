package com.etsy.android.lib.models.apiv3.ipp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.Option;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.ResponseConstants.Includes;
import com.etsy.android.lib.models.Variation;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.util.CurrencyUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONStringer;
import org.parceler.Parcel;

@Parcel
public class IppCartListing extends BaseModel {
    public static final String QUICK_LISTING = "quick-listing";
    protected static final String TAG;
    protected static final long serialVersionUID = -6133482161702202469L;
    protected long mAmount;
    protected String mCurrencyCode;
    protected String mDescription;
    protected String mImageListingId;
    protected String mImageStringBase64;
    protected String mImageUrl;
    protected boolean mIsEligible;
    protected EtsyId mListingId;
    protected boolean mNonTaxable;
    protected int mQuantity;
    protected EtsyId mQuickListingId;
    private Bitmap mQuickSaleImageBitmap;
    protected long mTaxCost;
    protected String mTitle;
    protected int mTotalListingQuantity;
    protected Map<Long, Variation> mVariations;

    static {
        TAG = EtsyDebug.m1891a(IppCartListing.class);
    }

    public IppCartListing() {
        this.mListingId = new EtsyId();
        this.mQuickListingId = new EtsyId();
        this.mQuantity = 1;
        this.mTotalListingQuantity = 99;
        this.mCurrencyCode = StringUtils.EMPTY;
        this.mTitle = StringUtils.EMPTY;
        this.mDescription = StringUtils.EMPTY;
        this.mVariations = new ConcurrentHashMap();
        this.mImageStringBase64 = StringUtils.EMPTY;
        this.mImageListingId = StringUtils.EMPTY;
        this.mIsEligible = true;
        this.mQuickSaleImageBitmap = null;
    }

    public boolean isIsEligible() {
        return this.mIsEligible;
    }

    public void setIsEligible(boolean z) {
        this.mIsEligible = z;
    }

    public boolean isNonTaxable() {
        return this.mNonTaxable;
    }

    public void setIsNonTaxable(boolean z) {
        this.mNonTaxable = z;
    }

    public boolean isQuickListing() {
        return QUICK_LISTING.equalsIgnoreCase(this.mListingId.getId());
    }

    public void makeQuickListing() {
        this.mListingId.setId(QUICK_LISTING);
        this.mQuickListingId = new EtsyId((long) ((int) (System.currentTimeMillis() / 1000)));
        this.mCurrencyCode = CurrencyUtil.m3091i();
    }

    protected void addVariations(List<Variation> list) {
        if (list != null) {
            for (Variation variation : list) {
                this.mVariations.put(Long.valueOf(variation.getPropertyId()), variation);
            }
        }
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.LISTING_ID.equals(currentName)) {
                    this.mListingId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.UNIQUE_ID.equals(currentName)) {
                    this.mQuickListingId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (Includes.VARIATIONS.equals(currentName)) {
                    setVariations(BaseModel.parseArray(jsonParser, Variation.class));
                } else if (ResponseConstants.QUANTITY.equals(currentName)) {
                    this.mQuantity = jsonParser.getValueAsInt();
                } else if (ResponseConstants.AMOUNT.equals(currentName)) {
                    this.mAmount = jsonParser.getValueAsLong();
                } else if (ResponseConstants.NON_TAXABLE.equals(currentName)) {
                    this.mNonTaxable = jsonParser.getValueAsBoolean();
                } else if (ResponseConstants.TAX_COST.equals(currentName)) {
                    this.mTaxCost = (long) jsonParser.getValueAsInt();
                } else if (EtsyRequest.PARAM_CURRENCY.equals(currentName)) {
                    this.mCurrencyCode = BaseModel.parseString(jsonParser);
                } else if (FindsModule.FIELD_TITLE.equals(currentName)) {
                    this.mTitle = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.DESCRIPTION.equals(currentName)) {
                    this.mDescription = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.IMAGE.equals(currentName)) {
                    this.mImageStringBase64 = BaseModel.parseStringURL(jsonParser);
                } else if (ResponseConstants.IMAGE_LISTING_ID.equals(currentName)) {
                    this.mImageListingId = BaseModel.parseStringIdOrNumericValue(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public void setTotalQuantity(int i) {
        this.mTotalListingQuantity = i;
    }

    public int getTotalListingQuantity() {
        return this.mTotalListingQuantity;
    }

    public int getQuantity() {
        return this.mQuantity;
    }

    public void setQuantity(int i) {
        this.mQuantity = i;
    }

    public long getAmount() {
        return this.mAmount;
    }

    public void setAmount(long j) {
        this.mAmount = j;
    }

    public long getTaxCost() {
        return this.mTaxCost;
    }

    public void setTaxCost(long j) {
        this.mTaxCost = j;
    }

    public String getCurrencyCode() {
        return this.mCurrencyCode;
    }

    public void setCurrencyCode(String str) {
        this.mCurrencyCode = str;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public String getImageStringBase64() {
        return this.mImageStringBase64;
    }

    public void setImageStringBase64(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mImageStringBase64 = str;
        }
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    public void setImageUrl(String str) {
        this.mImageUrl = str;
    }

    public Map<Long, Variation> getVariations() {
        return this.mVariations;
    }

    public void setVariations(List<Variation> list) {
        this.mVariations.clear();
        if (list != null) {
            for (Variation variation : list) {
                this.mVariations.put(Long.valueOf(variation.getPropertyId()), variation);
            }
        }
    }

    public void setVariations(HashMap<Long, Variation> hashMap) {
        this.mVariations = hashMap;
    }

    public String getVariationsAsString() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (Variation formattedName : this.mVariations.values()) {
            stringBuilder.append(formattedName.getFormattedName());
            int i2 = i + 1;
            if (i2 < this.mVariations.size()) {
                stringBuilder.append(", ");
            }
            i = i2;
        }
        return stringBuilder.toString();
    }

    public void setListingId(String str) {
        this.mListingId.setId(str);
    }

    public EtsyId getListingId() {
        return this.mListingId;
    }

    public boolean hasImage() {
        return (TextUtils.isEmpty(this.mImageListingId) && TextUtils.isEmpty(this.mImageStringBase64) && TextUtils.isEmpty(this.mImageUrl)) ? false : true;
    }

    public void setQuickListingId(EtsyId etsyId) {
        this.mQuickListingId = etsyId;
    }

    public EtsyId getQuickListingId() {
        return this.mQuickListingId;
    }

    public boolean hasVariations() {
        return !this.mVariations.isEmpty();
    }

    public String getImageListingId() {
        return this.mImageListingId;
    }

    public void setImageListingId(String str) {
        this.mImageListingId = str;
    }

    public void appendIppRequestJson(JSONStringer jSONStringer) {
        jSONStringer.object();
        jSONStringer.key(ResponseConstants.LISTING_ID).value(this.mListingId);
        jSONStringer.key(ResponseConstants.UNIQUE_ID).value(this.mQuickListingId);
        jSONStringer.key(ResponseConstants.QUANTITY).value((long) this.mQuantity);
        jSONStringer.key(ResponseConstants.AMOUNT).value(calculatePriceInPennies());
        jSONStringer.key(ResponseConstants.NON_TAXABLE).value(this.mNonTaxable);
        jSONStringer.key(ResponseConstants.TAX_COST).value(this.mTaxCost);
        jSONStringer.key(EtsyRequest.PARAM_CURRENCY).value(this.mCurrencyCode);
        if (isQuickListing()) {
            jSONStringer.key(FindsModule.FIELD_TITLE).value(this.mTitle);
            jSONStringer.key(ResponseConstants.DESCRIPTION).value(this.mDescription);
        } else {
            jSONStringer.key(FindsModule.FIELD_TITLE).value(StringUtils.EMPTY);
            jSONStringer.key(ResponseConstants.DESCRIPTION).value(StringUtils.EMPTY);
        }
        if (!isQuickListing()) {
            jSONStringer.key(ResponseConstants.IMAGE).value(StringUtils.EMPTY);
        } else if (TextUtils.isEmpty(this.mImageListingId) && TextUtils.isEmpty(this.mImageStringBase64)) {
            jSONStringer.key(ResponseConstants.IMAGE).value(StringUtils.EMPTY);
        } else if (TextUtils.isEmpty(this.mImageListingId)) {
            jSONStringer.key(ResponseConstants.IMAGE).value("data:image/jpg;base64," + this.mImageStringBase64);
        } else {
            jSONStringer.key(ResponseConstants.IMAGE_LISTING_ID).value(this.mImageListingId);
            jSONStringer.key(ResponseConstants.IMAGE).value(StringUtils.EMPTY);
        }
        jSONStringer.key(ResponseConstants.VARIATIONS);
        jSONStringer.object();
        for (Entry entry : this.mVariations.entrySet()) {
            Variation variation = (Variation) entry.getValue();
            jSONStringer.key(((Long) entry.getKey()).toString());
            jSONStringer.value(variation.getValueId());
        }
        jSONStringer.endObject();
        jSONStringer.endObject();
    }

    public String toIppRequestJsonString() {
        JSONStringer jSONStringer = new JSONStringer();
        appendIppRequestJson(jSONStringer);
        return jSONStringer.toString();
    }

    public Bitmap getQuickSaleImageBitmap() {
        if (!TextUtils.isEmpty(this.mImageStringBase64) && this.mQuickSaleImageBitmap == null) {
            try {
                byte[] decode = Base64.decode(this.mImageStringBase64, 0);
                this.mQuickSaleImageBitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);
            } catch (IllegalArgumentException e) {
                EtsyDebug.m1900a("Exception decoding Base64 image.", e);
            }
        }
        return this.mQuickSaleImageBitmap;
    }

    public long calculatePriceInPennies() {
        long amount = getAmount();
        if (!hasVariations()) {
            return amount;
        }
        long j = amount;
        for (Variation selectedOption : getVariations().values()) {
            Option selectedOption2 = selectedOption.getSelectedOption();
            if (!selectedOption2.hasPriceDiff()) {
                amount = j;
            } else if (CurrencyUtil.m3072a()) {
                amount = selectedOption2.getConvertedPrice().getAmount().multiply(new BigDecimal(100)).longValue();
            } else {
                amount = CurrencyUtil.m3073b(selectedOption2.getConvertedPrice().getAmount().toString());
            }
            j = amount;
        }
        return j;
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        if (isQuickListing()) {
            hashMap.put(AnalyticsLogAttribute.QUICK_LISTING_ID, this.mQuickListingId.getId());
        } else {
            hashMap.put(AnalyticsLogAttribute.LISTING_ID, this.mListingId.getId());
        }
        return hashMap;
    }
}
