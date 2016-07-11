package com.etsy.android.lib.requests;

import android.text.TextUtils;
import com.adjust.sdk.Constants;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.CountResult;
import com.etsy.android.lib.models.apiv3.ipp.CashPayment;
import com.etsy.android.lib.models.apiv3.ipp.CreditCardAuth;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.icht.IchtToken;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import com.etsy.android.lib.util.CrashUtil;
import com.etsy.android.lib.util.CurrencyUtil;
import com.etsy.android.lib.util.ba;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class IppRequest<Result extends BaseModel> extends EtsyRequest<Result> {
    private static final long AUTH_REQUEST_TIMEOUT = 30000;
    public static final String LOGSTASH_NAMESPACE = "ipp-post-finalize";
    public static final String PATH_CASH_PAYMENT = "/cash-payment";
    public static final String REQUEST_CASH_PAYMENT = "/in-person/cash-payment";
    public static final String REQUEST_CASH_PAYMENT_ENCRYPTED = "/in-person/cash-payment/encrypted";
    public static final String REQUEST_CREDIT_CARD_AUTH = "/in-person/auth";
    public static final String REQUEST_CREDIT_CARD_CANCEL = "/in-person/receipts/%s/cancel";
    public static final String REQUEST_CREDIT_CARD_FINALIZE = "/in-person/receipts/%s/finalize";
    public static final String REQUEST_CREDIT_CARD_FINALIZE_ENCRYPTED = "/in-person/receipts/%s/finalize/encrypted";
    public static final String REQUEST_RECEIPTS_PROCESSING = "/in-person/receipts/processing-count";
    private static final String TAG;

    static {
        TAG = EtsyDebug.m1891a(IppRequest.class);
    }

    public IppRequest(String str, RequestMethod requestMethod, Class cls, EndpointType endpointType) {
        super(str, requestMethod, cls, endpointType);
    }

    public IppRequest(String str, RequestMethod requestMethod, Class cls) {
        super(str, requestMethod, cls, EndpointType.APIv3);
    }

    public static IppRequest<CreditCardAuth> authCreditCardTransaction(boolean z, long j, EtsyId etsyId, long j2, int i, String str, IchtToken ichtToken, String str2, String str3) {
        IppRequest<CreditCardAuth> authRequest = getAuthRequest(etsyId);
        Map hashMap = new HashMap();
        hashMap.put(ResponseConstants.CART_LISTINGS, str3);
        hashMap.put(ResponseConstants.CLIENT_TIMESTAMP, truncateTimestampToSeconds(j));
        if (str2 != null) {
            hashMap.put(ResponseConstants.TAX_PROFILE_ID, str2);
        }
        if (!TextUtils.isEmpty(str) && EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.bd)) {
            hashMap.put(ResponseConstants.COUPON_ID, str);
        }
        if (j2 > 0) {
            hashMap.put(ResponseConstants.DISCOUNT_AMOUNT, String.valueOf(j2));
        }
        if (i > 0) {
            hashMap.put(ResponseConstants.DISCOUNT_PERCENT, String.valueOf(i));
        }
        hashMap.put("encrypted_data", ichtToken.getToken());
        hashMap.put("manual_entry", String.valueOf(z));
        authRequest.addBodyParams(hashMap);
        return authRequest;
    }

    public static IppRequest<CreditCardAuth> authManualCreditCardTransaction(long j, EtsyId etsyId, long j2, int i, String str, IchtToken ichtToken, String str2, String str3, int i2, int i3) {
        IppRequest<CreditCardAuth> authCreditCardTransaction = authCreditCardTransaction(true, j, etsyId, j2, i, str, ichtToken, str2, str3);
        Map hashMap = new HashMap();
        hashMap.put("exp_month", String.valueOf(i2));
        hashMap.put("exp_year", String.valueOf(i3));
        authCreditCardTransaction.setTimeout(30000);
        authCreditCardTransaction.addBodyParams(hashMap);
        return authCreditCardTransaction;
    }

    public static IppRequest<EmptyResult> cancelCreditCardTransaction(String str, String str2, boolean z) {
        IppRequest<EmptyResult> ippRequest = new IppRequest(String.format(REQUEST_CREDIT_CARD_CANCEL, new Object[]{str2}), RequestMethod.PUT, EmptyResult.class);
        APIv3Scope.SHOP.setIdentifier(str);
        ippRequest.setV3Scope(APIv3Scope.SHOP);
        ippRequest.setV3Bespoke(false);
        if (z) {
            ippRequest.addBodyParam("is_cleanup", "true");
        }
        return ippRequest;
    }

    public static IppRequest<EmptyResult> createFinalizeCreditCardRequest(String str, String str2, HashMap<String, String> hashMap, String str3, String str4) {
        boolean z = (str3 == null || str4 == null) ? false : true;
        IppRequest<EmptyResult> ippRequest = new IppRequest(String.format(z ? REQUEST_CREDIT_CARD_FINALIZE_ENCRYPTED : REQUEST_CREDIT_CARD_FINALIZE, new Object[]{str2}), RequestMethod.PUT, EmptyResult.class);
        APIv3Scope.SHOP.setIdentifier(str);
        ippRequest.setV3Scope(APIv3Scope.SHOP);
        ippRequest.setV3Bespoke(true);
        if (z) {
            ippRequest.addBodyParams(encodeAndAppendBodyParams(hashMap, str3, str4));
        } else {
            logCouldNotEncrypt(str3, str4);
            ippRequest.addBodyParams(hashMap);
        }
        return ippRequest;
    }

    public static IppRequest<CashPayment> createCashPaymentRequest(String str, HashMap<String, String> hashMap, String str2, String str3) {
        boolean z = (str2 == null || str3 == null) ? false : true;
        IppRequest<CashPayment> ippRequest = new IppRequest(z ? REQUEST_CASH_PAYMENT_ENCRYPTED : REQUEST_CASH_PAYMENT, RequestMethod.POST, CashPayment.class);
        APIv3Scope.SHOP.setIdentifier(str);
        ippRequest.setV3Scope(APIv3Scope.SHOP);
        ippRequest.setV3Bespoke(true);
        if (z) {
            ippRequest.addBodyParams(encodeAndAppendBodyParams(hashMap, str2, str3));
        } else {
            logCouldNotEncrypt(str2, str3);
            ippRequest.addBodyParams(hashMap);
        }
        return ippRequest;
    }

    private static void logCouldNotEncrypt(String str, String str2) {
        String str3 = StringUtils.EMPTY;
        if (str == null) {
            str3 = "Count not encrypt because public key was null";
        } else if (str2 == null) {
            str3 = "Count not encrypt because public key id was null";
        }
        EtsyLogger.m1966a().m1986a(LOGSTASH_NAMESPACE, str3);
        CrashUtil.m3037a().m3045a(new Throwable(str3));
    }

    private static HashMap<String, String> encodeAndAppendBodyParams(HashMap<String, String> hashMap, String str, String str2) {
        String createBody = HttpUtil.createBody((Map) hashMap, HttpUtil.JSON_CONTENT_TYPE_ENCODED);
        HashMap<String, String> hashMap2 = new HashMap();
        try {
            hashMap2.put("e", new String(ba.m3312a(str, str2, createBody), Constants.ENCODING));
            return hashMap2;
        } catch (Exception e) {
            throw e;
        }
    }

    public static IppRequest<CountResult> getProcessingReceiptsCount(String str) {
        IppRequest<CountResult> ippRequest = new IppRequest(REQUEST_RECEIPTS_PROCESSING, RequestMethod.GET, CountResult.class);
        APIv3Scope.SHOP.setIdentifier(str);
        ippRequest.setV3Scope(APIv3Scope.SHOP);
        ippRequest.setV3Bespoke(false);
        return ippRequest;
    }

    public static IppRequest<IchtToken> tokenizeSwipeCreditCard(String str, String str2, EtsyId etsyId, long j) {
        IppRequest<IchtToken> iCHTRequest = getICHTRequest();
        Map ichtCommonParams = getIchtCommonParams(etsyId, j);
        ichtCommonParams.put("encrypted_swipe", str);
        ichtCommonParams.put("ksn", str2);
        iCHTRequest.addBodyParams(ichtCommonParams);
        return iCHTRequest;
    }

    public static IppRequest<IchtToken> tokenizeManualCreditCard(String str, String str2, EtsyId etsyId, long j) {
        IppRequest<IchtToken> iCHTRequest = getICHTRequest();
        Map ichtCommonParams = getIchtCommonParams(etsyId, j);
        ichtCommonParams.put("credit_card_number", str);
        ichtCommonParams.put("cvv", str2);
        iCHTRequest.addBodyParams(ichtCommonParams);
        return iCHTRequest;
    }

    private static IppRequest<CreditCardAuth> getAuthRequest(EtsyId etsyId) {
        IppRequest<CreditCardAuth> ippRequest = new IppRequest(REQUEST_CREDIT_CARD_AUTH, RequestMethod.POST, CreditCardAuth.class);
        APIv3Scope.SHOP.setIdentifier(etsyId.getId());
        ippRequest.setV3Scope(APIv3Scope.SHOP);
        ippRequest.setV3Bespoke(true);
        return ippRequest;
    }

    private static HashMap<String, String> getIchtCommonParams(EtsyId etsyId, long j) {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put(ResponseConstants.SHOP_ID, etsyId.getId());
        hashMap.put(ResponseConstants.AMOUNT, String.valueOf(j));
        hashMap.put(EtsyRequest.PARAM_CURRENCY, CurrencyUtil.m3091i());
        return hashMap;
    }

    private static IppRequest<IchtToken> getICHTRequest() {
        IppRequest<IchtToken> ippRequest = new IppRequest("/inperson/tokenize.php", RequestMethod.POST, IchtToken.class, EndpointType.I_CAN_HAZ_TOKEN);
        ippRequest.setSigned(false);
        return ippRequest;
    }

    private static String truncateTimestampToSeconds(long j) {
        String valueOf = String.valueOf(j);
        return valueOf.substring(0, valueOf.length() - 3);
    }
}
