package com.etsy.android.lib.models;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.EtsyMoney;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.ResponseConstants.Includes;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.enums.EtsyReceiptType;
import com.etsy.android.lib.models.interfaces.ReceiptShippingStatus;
import com.etsy.android.lib.util.bh;
import com.etsy.android.lib.util.bk;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class Receipt extends BaseModel implements ReceiptShippingStatus {
    public static final String ADDRESS_FIELDS = "first_line,second_line,city,state,zip,verification_state";
    public static final String ADDRESS_INCLUDES = "Country(name)";
    public static final int ADDRESS_VERIFICATION_STATE_AUTOMATICALLY_VERIFIED = 1;
    public static final int ADDRESS_VERIFICATION_STATE_BUYER_KEPT_ORIGINAL_ADDRESS = 6;
    public static final int ADDRESS_VERIFICATION_STATE_SELLER_KEPT_ORIGINAL_ADDRESS = 7;
    public static final int ADDRESS_VERIFICATION_STATE_UNABLE_TO_VERIFY = 5;
    public static final int ADDRESS_VERIFICATION_STATE_UNVERIFIED = 0;
    public static final int ADDRESS_VERIFICATION_STATE_VERIFIED_BY_BUYER = 2;
    public static final int ADDRESS_VERIFICATION_STATE_VERIFIED_BY_SELLER = 3;
    private static final String IN_PERSON_PAYMENT_TYPE_CASH = "CASH";
    private static final String IN_PERSON_PAYMENT_TYPE_MANUAL = "MANUAL";
    private static final String IN_PERSON_PAYMENT_TYPE_NA = "NA";
    private static final String IN_PERSON_PAYMENT_TYPE_SWIPE = "SWIPE";
    private static final String PAYMENT_BANK_TRANSFER = "bt";
    private static final String PAYMENT_CHECK = "ck";
    private static final String PAYMENT_DIRECT_CHECKOUT = "cc";
    private static final String PAYMENT_MONEYORDER = "mo";
    private static final String PAYMENT_PAYPAL = "pp";
    private static final long serialVersionUID = 2848879465714259053L;
    protected int mAddressVerificationState;
    protected User mBuyer;
    protected String mBuyerEmail;
    protected String mCity;
    protected Country mCountry;
    protected Coupon mCoupon;
    protected Date mCreationDate;
    protected EtsyMoney mDiscountAmt;
    protected Date mEstimatedShippedDate;
    protected String mFirstLine;
    protected EtsyMoney mGrandTotal;
    protected boolean mHasLocalDelivery;
    protected String mInPersonPaymentType;
    protected boolean mIsAnonymousBuyer;
    protected boolean mIsInPerson;
    protected List<Listing> mListings;
    protected LocalDeliveryDetails mLocalDeliveryDetails;
    protected Location mLocation;
    protected String mMessageFromBuyer;
    protected String mMessageFromSeller;
    protected String mName;
    protected Payment mPayment;
    protected String mPaymentEmail;
    protected PaymentMethod mPaymentMethod;
    protected EtsyId mReceiptId;
    protected EtsyReceiptType mReceiptType;
    protected String mSecondLine;
    protected User mSeller;
    protected String mSellerEmail;
    protected List<ReceiptShipment> mShipments;
    protected Date mShippedDate;
    protected String mShippingCarrier;
    protected ReceiptShippingDetails mShippingDetails;
    protected String mShippingNote;
    protected Date mShippingNotificationDate;
    protected String mShippingTrackingUrl;
    protected String mState;
    protected EtsyMoney mTotalPrice;
    protected EtsyMoney mTotalShippingCost;
    protected EtsyMoney mTotalTaxCost;
    protected EtsyMoney mTotalVatCost;
    protected List<Transaction> mTransactions;
    protected String mTransparentPriceMessage;
    protected List<UserNote> mUserNotes;
    protected List<String> mVatCreditNoteIds;
    protected boolean mWasGiftcardBalanceApplied;
    protected boolean mWasPaid;
    protected boolean mWasShipped;
    protected String mZip;

    public Receipt() {
        this.mName = StringUtils.EMPTY;
        this.mFirstLine = StringUtils.EMPTY;
        this.mSecondLine = StringUtils.EMPTY;
        this.mCity = StringUtils.EMPTY;
        this.mState = StringUtils.EMPTY;
        this.mZip = StringUtils.EMPTY;
        this.mAddressVerificationState = ADDRESS_VERIFICATION_STATE_UNVERIFIED;
        this.mPaymentEmail = StringUtils.EMPTY;
        this.mMessageFromSeller = StringUtils.EMPTY;
        this.mMessageFromBuyer = StringUtils.EMPTY;
        this.mHasLocalDelivery = false;
        this.mDiscountAmt = new EtsyMoney();
        this.mGrandTotal = new EtsyMoney();
        this.mTotalTaxCost = new EtsyMoney();
        this.mTotalPrice = new EtsyMoney();
        this.mTotalShippingCost = new EtsyMoney();
        this.mTotalVatCost = new EtsyMoney();
        this.mTransparentPriceMessage = StringUtils.EMPTY;
        this.mBuyerEmail = StringUtils.EMPTY;
        this.mSellerEmail = StringUtils.EMPTY;
        this.mShippingCarrier = StringUtils.EMPTY;
        this.mShippingNote = StringUtils.EMPTY;
        this.mShippingTrackingUrl = StringUtils.EMPTY;
        this.mReceiptId = new EtsyId();
        this.mTransactions = new ArrayList(ADDRESS_VERIFICATION_STATE_UNVERIFIED);
        this.mShipments = new ArrayList(ADDRESS_VERIFICATION_STATE_UNVERIFIED);
        this.mListings = new ArrayList(ADDRESS_VERIFICATION_STATE_UNVERIFIED);
        this.mUserNotes = new ArrayList(ADDRESS_VERIFICATION_STATE_UNVERIFIED);
        this.mVatCreditNoteIds = new ArrayList(ADDRESS_VERIFICATION_STATE_UNVERIFIED);
    }

    public EtsyId getReceiptId() {
        return this.mReceiptId;
    }

    public Date getCreationDate() {
        return this.mCreationDate;
    }

    public Date getShippingNotificationDate() {
        return this.mShippingNotificationDate;
    }

    public String getName() {
        return this.mName;
    }

    public String getFirstLine() {
        return this.mFirstLine;
    }

    public String getSecondLine() {
        return this.mSecondLine;
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

    public int getAddressVerificationState() {
        return this.mAddressVerificationState;
    }

    public PaymentMethod getPaymentMethod() {
        return this.mPaymentMethod;
    }

    public String getPaymentEmail() {
        return this.mPaymentEmail;
    }

    public String getMessageFromSeller() {
        return this.mMessageFromSeller;
    }

    public String getMessageFromBuyer() {
        return this.mMessageFromBuyer;
    }

    public boolean wasPaid() {
        return this.mWasPaid;
    }

    public boolean wasGiftcardBalanceApplied() {
        return this.mWasGiftcardBalanceApplied;
    }

    public EtsyMoney getTotalTaxCost() {
        return this.mTotalTaxCost;
    }

    public EtsyMoney getTotalVatCost() {
        return this.mTotalVatCost;
    }

    public EtsyMoney getTotalPrice() {
        return this.mTotalPrice;
    }

    public EtsyMoney getTotalShippingCost() {
        return this.mTotalShippingCost;
    }

    public boolean wasShipped() {
        return this.mWasShipped;
    }

    public boolean isInPerson() {
        return this.mIsInPerson;
    }

    public boolean isAnonymousBuyer() {
        return this.mIsAnonymousBuyer;
    }

    public String getBuyerEmail() {
        return this.mBuyerEmail;
    }

    public String getSellerEmail() {
        return this.mSellerEmail;
    }

    public EtsyMoney getDiscountAmt() {
        return this.mDiscountAmt;
    }

    public EtsyMoney getGrandtotal() {
        return this.mGrandTotal;
    }

    public boolean getHasLocalDelivery() {
        return this.mHasLocalDelivery;
    }

    public String getShippingCarrier() {
        return this.mShippingCarrier;
    }

    public String getShippingNote() {
        return this.mShippingNote;
    }

    public String getShippingTrackingUrl() {
        return this.mShippingTrackingUrl;
    }

    public Coupon getCoupon() {
        return this.mCoupon;
    }

    public Country getCountry() {
        return this.mCountry;
    }

    public User getBuyer() {
        return this.mBuyer;
    }

    public User getSeller() {
        return this.mSeller;
    }

    public LocalDeliveryDetails getLocalDeliveryDetails() {
        return this.mLocalDeliveryDetails;
    }

    public Location getLocation() {
        return this.mLocation;
    }

    public ReceiptShippingDetails getShippingDetails() {
        return this.mShippingDetails;
    }

    public List<Transaction> getTransactions() {
        return this.mTransactions;
    }

    public List<Listing> getListings() {
        return this.mListings;
    }

    public List<ReceiptShipment> getShipments() {
        return this.mShipments;
    }

    public Payment getPayment() {
        return this.mPayment;
    }

    public List<String> getVatCreditNoteIds() {
        return this.mVatCreditNoteIds;
    }

    @NonNull
    public String getTransparentPriceMessage() {
        return this.mTransparentPriceMessage;
    }

    public boolean hasTransparentPriceMessage() {
        return bh.m3343b(this.mTransparentPriceMessage);
    }

    public EtsyReceiptType getReceiptType() {
        return this.mReceiptType;
    }

    public boolean isGiftCardReceipt() {
        if (this.mTransactions != null) {
            for (Transaction isGiftCard : this.mTransactions) {
                if (isGiftCard.isGiftCard()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Nullable
    public Date getShipDate() {
        if (allTransactionsAreDigital()) {
            return null;
        }
        if (this.mWasShipped && this.mShippedDate != null && this.mShippedDate.getTime() > 0) {
            return this.mShippedDate;
        }
        if (this.mEstimatedShippedDate == null || this.mEstimatedShippedDate.getTime() <= 0) {
            return null;
        }
        return this.mEstimatedShippedDate;
    }

    public int daysUntilShipped() {
        Date shipDate = getShipDate();
        if (shipDate == null) {
            return ADDRESS_VERIFICATION_STATE_UNVERIFIED;
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(shipDate);
        bk.m3351a(instance);
        Calendar instance2 = Calendar.getInstance();
        bk.m3351a(instance2);
        return bk.m3348a(instance.getTimeInMillis() - instance2.getTimeInMillis());
    }

    public SpannableStringBuilder getFormattedAddressWithLineBreaks() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (bh.m3340a(this.mFirstLine)) {
            spannableStringBuilder.append(this.mFirstLine);
        }
        if (bh.m3340a(this.mSecondLine)) {
            if (spannableStringBuilder.length() > 0) {
                spannableStringBuilder.append("\n");
            }
            spannableStringBuilder.append(this.mSecondLine);
        }
        if (bh.m3340a(this.mCity)) {
            if (spannableStringBuilder.length() > 0) {
                spannableStringBuilder.append("\n");
            }
            spannableStringBuilder.append(this.mCity);
        }
        if (bh.m3340a(this.mState)) {
            if (bh.m3340a(this.mCity)) {
                spannableStringBuilder.append(", ");
            } else if (spannableStringBuilder.length() > 0) {
                spannableStringBuilder.append("\n");
            }
            spannableStringBuilder.append(this.mState);
        }
        if (bh.m3340a(this.mZip)) {
            if (spannableStringBuilder.length() > 0) {
                spannableStringBuilder.append("\n");
            }
            spannableStringBuilder.append(this.mZip);
        }
        if (this.mCountry != null && bh.m3340a(this.mCountry.getName())) {
            if (spannableStringBuilder.length() > 0) {
                spannableStringBuilder.append("\n");
            }
            spannableStringBuilder.append(this.mCountry.getName());
        }
        return spannableStringBuilder;
    }

    public String getStringForPaymentMethod(Resources resources, boolean z) {
        if (this.mIsInPerson) {
            if (IN_PERSON_PAYMENT_TYPE_CASH.equals(this.mInPersonPaymentType)) {
                return resources.getString(R.ipp_payment_method_label_cash);
            }
            if (IN_PERSON_PAYMENT_TYPE_SWIPE.equals(this.mInPersonPaymentType)) {
                return resources.getString(R.ipp_payment_method_label_swipe);
            }
            if (IN_PERSON_PAYMENT_TYPE_MANUAL.equals(this.mInPersonPaymentType)) {
                return resources.getString(R.ipp_payment_method_label_manual);
            }
        }
        if (this.mPaymentMethod == null || this.mPaymentMethod.getName() == null) {
            return StringUtils.EMPTY;
        }
        StringBuilder stringBuilder;
        if (this.mPaymentMethod.isGoogleWallet()) {
            stringBuilder = new StringBuilder(this.mPaymentMethod.getName());
        } else if (!this.mPaymentMethod.isDirectCheckout() || PAYMENT_PAYPAL.equals(this.mPaymentMethod.getType())) {
            stringBuilder = new StringBuilder(this.mPaymentMethod.getName());
        } else {
            stringBuilder = new StringBuilder(resources.getString(R.payment_method_label_direct_checkout));
        }
        if (this.mWasGiftcardBalanceApplied) {
            stringBuilder.append("\n");
            stringBuilder.append(resources.getString(R.giftcard_applied));
        }
        for (String str : this.mPaymentMethod.getDetails()) {
            stringBuilder.append("\n");
            stringBuilder.append(str);
        }
        if (z) {
            bh.m3338a(stringBuilder);
        }
        return stringBuilder.toString();
    }

    public boolean isDirectCheckout() {
        if (this.mPaymentMethod != null) {
            return this.mPaymentMethod.isDirectCheckout();
        }
        return false;
    }

    @Nullable
    public UserNote getSellerPrivateNote() {
        for (UserNote userNote : this.mUserNotes) {
            if (userNote.isPrivateReceiptNote()) {
                return userNote;
            }
        }
        return null;
    }

    @NonNull
    public ArrayList<UserNote> getSellerPrivateNotes() {
        ArrayList<UserNote> arrayList = new ArrayList();
        for (UserNote userNote : this.mUserNotes) {
            if (userNote.isPrivateReceiptNote()) {
                arrayList.add(userNote);
            }
        }
        return arrayList;
    }

    @NonNull
    public List<UserNote> getUserNotes() {
        return this.mUserNotes;
    }

    public void clearAllPrivateReceipts() {
        Collection arrayList = new ArrayList();
        for (UserNote userNote : this.mUserNotes) {
            if (!userNote.isPrivateReceiptNote()) {
                arrayList.add(userNote);
            }
        }
        this.mUserNotes.clear();
        this.mUserNotes.addAll(arrayList);
    }

    public void setPayment(Payment payment) {
        this.mPayment = payment;
    }

    public boolean hasRefund() {
        return this.mPayment != null && this.mPayment.hasRefund();
    }

    public boolean isFullyRefunded() {
        return hasRefund() && this.mPayment.isFullRefund();
    }

    public List<Transaction> getQuickListings() {
        List<Transaction> arrayList = new ArrayList(ADDRESS_VERIFICATION_STATE_UNVERIFIED);
        for (Transaction transaction : this.mTransactions) {
            if (transaction.isQuickListing()) {
                arrayList.add(transaction);
            }
        }
        return arrayList;
    }

    public String getFormattedGrandTotal() {
        return getGrandtotal().format();
    }

    public boolean allTransactionsAreDigital() {
        if (this.mTransactions.size() <= 0) {
            return false;
        }
        for (Transaction isDigitalDownload : this.mTransactions) {
            if (!isDigitalDownload.isDigitalDownload()) {
                return false;
            }
        }
        return true;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.RECEIPT_ID.equals(currentName)) {
                    this.mReceiptId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.RECEIPT_TYPE.equals(currentName)) {
                    this.mReceiptType = EtsyReceiptType.resolveReceiptType(jsonParser.getIntValue());
                } else if (ResponseConstants.PAYMENT_METHOD_OBJ.equals(currentName)) {
                    this.mPaymentMethod = (PaymentMethod) BaseModel.parseObject(jsonParser, PaymentMethod.class);
                } else if (ResponseConstants.PAYMENT_EMAIL.equals(currentName)) {
                    this.mPaymentEmail = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.WAS_PAID.equals(currentName)) {
                    this.mWasPaid = jsonParser.getValueAsBoolean();
                } else if (ResponseConstants.WAS_GIFTCARD_BALANCE_APPLIED.equals(currentName)) {
                    this.mWasGiftcardBalanceApplied = jsonParser.getValueAsBoolean();
                } else if (ResponseConstants.TOTAL_TAX_COST.equals(currentName)) {
                    this.mTotalTaxCost = this.mTotalTaxCost.withAmount(BaseModel.parseString(jsonParser));
                } else if (ResponseConstants.TOTAL_VAT_COST.equals(currentName)) {
                    this.mTotalVatCost = this.mTotalVatCost.withAmount(BaseModel.parseString(jsonParser));
                } else if (ResponseConstants.VAT_CREDIT_NOTE_IDS.equals(currentName)) {
                    this.mVatCreditNoteIds = BaseModel.parseStringArray(jsonParser);
                } else if (ResponseConstants.TOTAL_PRICE.equals(currentName)) {
                    this.mTotalPrice = this.mTotalPrice.withAmount(BaseModel.parseString(jsonParser));
                } else if (ResponseConstants.TOTAL_SHIPPING_COST.equals(currentName)) {
                    this.mTotalShippingCost = this.mTotalShippingCost.withAmount(BaseModel.parseString(jsonParser));
                } else if (ResponseConstants.DISCOUNT_AMT.equals(currentName)) {
                    this.mDiscountAmt = this.mDiscountAmt.withAmount(BaseModel.parseString(jsonParser));
                } else if (ResponseConstants.GRANDTOTAL.equals(currentName)) {
                    this.mGrandTotal = this.mGrandTotal.withAmount(BaseModel.parseString(jsonParser));
                } else if (ResponseConstants.CURRENCY_CODE.equals(currentName)) {
                    this.mTotalTaxCost = this.mTotalTaxCost.withCurrency(BaseModel.parseString(jsonParser));
                    this.mTotalVatCost = this.mTotalVatCost.withCurrency(BaseModel.parseString(jsonParser));
                    this.mTotalPrice = this.mTotalPrice.withCurrency(BaseModel.parseString(jsonParser));
                    this.mTotalShippingCost = this.mTotalShippingCost.withCurrency(BaseModel.parseString(jsonParser));
                    this.mDiscountAmt = this.mDiscountAmt.withCurrency(BaseModel.parseString(jsonParser));
                    this.mGrandTotal = this.mGrandTotal.withCurrency(BaseModel.parseString(jsonParser));
                } else if (ResponseConstants.TRANSPARENT_PRICE_MESSAGE.equals(currentName)) {
                    this.mTransparentPriceMessage = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.WAS_SHIPPED.equals(currentName)) {
                    this.mWasShipped = jsonParser.getValueAsBoolean();
                } else if (ResponseConstants.IS_IN_PERSON.equals(currentName)) {
                    this.mIsInPerson = jsonParser.getValueAsBoolean();
                } else if (ResponseConstants.HAS_LOCAL_DELIVERY.equals(currentName)) {
                    this.mHasLocalDelivery = jsonParser.getValueAsBoolean();
                } else if (ResponseConstants.IS_ANONYMOUS_BUYER.equals(currentName)) {
                    this.mIsAnonymousBuyer = jsonParser.getValueAsBoolean();
                } else if (ResponseConstants.IN_PERSON_PAYMENT_TYPE.equals(currentName)) {
                    this.mInPersonPaymentType = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.BUYER_EMAIL.equals(currentName)) {
                    this.mBuyerEmail = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.SELLER_EMAIL.equals(currentName)) {
                    this.mSellerEmail = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.SHIPPING_CARRIER.equals(currentName)) {
                    this.mShippingCarrier = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.NAME.equals(currentName)) {
                    this.mName = BaseModel.parseString(jsonParser).trim();
                } else if (ResponseConstants.FIRST_LINE.equals(currentName)) {
                    this.mFirstLine = BaseModel.parseString(jsonParser).trim();
                } else if (ResponseConstants.SECOND_LINE.equals(currentName)) {
                    this.mSecondLine = BaseModel.parseString(jsonParser).trim();
                } else if (ResponseConstants.CITY.equals(currentName)) {
                    this.mCity = BaseModel.parseString(jsonParser).trim();
                } else if (ResponseConstants.STATE.equals(currentName)) {
                    this.mState = BaseModel.parseString(jsonParser).trim();
                } else if (ResponseConstants.ZIP.equals(currentName)) {
                    this.mZip = BaseModel.parseString(jsonParser).trim();
                } else if (ResponseConstants.VERIFICATION_STATE.equals(currentName)) {
                    this.mAddressVerificationState = jsonParser.getValueAsInt();
                } else if (ResponseConstants.MESSAGE_FROM_SELLER.equals(currentName)) {
                    this.mMessageFromSeller = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.MESSAGE_FROM_BUYER.equals(currentName)) {
                    this.mMessageFromBuyer = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.SHIPPING_NOTE.equals(currentName)) {
                    this.mShippingNote = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.SHIPPING_TRACKING_URL.equals(currentName)) {
                    this.mShippingTrackingUrl = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.SHIPPING_DETAILS.equals(currentName)) {
                    this.mShippingDetails = (ReceiptShippingDetails) BaseModel.parseObject(jsonParser, ReceiptShippingDetails.class);
                } else if (ResponseConstants.CREATION_TSZ.equals(currentName)) {
                    this.mCreationDate = BaseModel.parseIntoDate(jsonParser);
                } else if (ResponseConstants.SHIPPING_NOTIFICATION_DATE.equals(currentName)) {
                    this.mShippingNotificationDate = BaseModel.parseIntoDate(jsonParser);
                } else if (ResponseConstants.SHIPPED_TSZ.equals(currentName)) {
                    this.mShippedDate = BaseModel.parseIntoDate(jsonParser);
                } else if (ResponseConstants.ESTIMATED_SHIPPED_TSZ.equals(currentName)) {
                    this.mEstimatedShippedDate = BaseModel.parseIntoDate(jsonParser);
                } else if (Includes.COUPON.equals(currentName)) {
                    this.mCoupon = (Coupon) BaseModel.parseObject(jsonParser, Coupon.class);
                } else if (Includes.COUNTRY.equals(currentName)) {
                    this.mCountry = (Country) BaseModel.parseObject(jsonParser, Country.class);
                } else if (ResponseConstants.LOCAL_DELIVERY_DETAILS.equals(currentName)) {
                    this.mLocalDeliveryDetails = (LocalDeliveryDetails) BaseModel.parseObject(jsonParser, LocalDeliveryDetails.class);
                } else if (Includes.BUYER.equals(currentName)) {
                    this.mBuyer = (User) BaseModel.parseObject(jsonParser, User.class);
                } else if (Includes.SELLER.equals(currentName)) {
                    this.mSeller = (User) BaseModel.parseObject(jsonParser, User.class);
                } else if (Includes.LOCATION.equals(currentName)) {
                    this.mLocation = (Location) BaseModel.parseObject(jsonParser, Location.class);
                } else if (Includes.TRANSACTIONS.equals(currentName)) {
                    this.mTransactions = BaseModel.parseArray(jsonParser, Transaction.class);
                } else if (ResponseConstants.SHIPMENTS.equals(currentName)) {
                    this.mShipments = BaseModel.parseArray(jsonParser, ReceiptShipment.class);
                } else if (Includes.LISTINGS.equals(currentName)) {
                    this.mListings = BaseModel.parseArray(jsonParser, Listing.class);
                } else if (Includes.USERNOTES.equals(currentName)) {
                    this.mUserNotes = BaseModel.parseArray(jsonParser, UserNote.class);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(ADDRESS_VERIFICATION_STATE_AUTOMATICALLY_VERIFIED);
        hashMap.put(AnalyticsLogAttribute.RECEIPT_ID, this.mReceiptId.getId());
        return hashMap;
    }
}
