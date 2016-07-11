package com.adjust.sdk;

import com.etsy.android.lib.models.ResponseConstants;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class AdjustEvent {
    private static ILogger logger;
    Map<String, String> callbackParameters;
    String currency;
    String eventToken;
    Map<String, String> partnerParameters;
    Double revenue;

    static {
        logger = AdjustFactory.getLogger();
    }

    public AdjustEvent(String str) {
        if (checkEventToken(str, logger)) {
            this.eventToken = str;
        }
    }

    public void setRevenue(double d, String str) {
        if (checkRevenue(Double.valueOf(d), str)) {
            this.revenue = Double.valueOf(d);
            this.currency = str;
        }
    }

    public void addCallbackParameter(String str, String str2) {
        if (isValidParameter(str, ResponseConstants.KEY, "Callback") && isValidParameter(str2, ResponseConstants.VALUE, "Callback")) {
            if (this.callbackParameters == null) {
                this.callbackParameters = new LinkedHashMap();
            }
            if (((String) this.callbackParameters.put(str, str2)) != null) {
                logger.warn("key %s was overwritten", str);
            }
        }
    }

    public void addPartnerParameter(String str, String str2) {
        if (isValidParameter(str, ResponseConstants.KEY, "Partner") && isValidParameter(str2, ResponseConstants.VALUE, "Partner")) {
            if (this.partnerParameters == null) {
                this.partnerParameters = new LinkedHashMap();
            }
            if (((String) this.partnerParameters.put(str, str2)) != null) {
                logger.warn("key %s was overwritten", str);
            }
        }
    }

    public boolean isValid() {
        return this.eventToken != null;
    }

    private static boolean checkEventToken(String str, ILogger iLogger) {
        if (str == null) {
            iLogger.error("Missing Event Token", new Object[0]);
            return false;
        } else if (str.length() == 6) {
            return true;
        } else {
            iLogger.error("Malformed Event Token '%s'", str);
            return false;
        }
    }

    private boolean checkRevenue(Double d, String str) {
        if (d != null) {
            if (d.doubleValue() < 0.0d) {
                logger.error("Invalid amount %.5f", d);
                return false;
            } else if (str == null) {
                logger.error("Currency must be set with revenue", new Object[0]);
                return false;
            } else if (str.equals(StringUtils.EMPTY)) {
                logger.error("Currency is empty", new Object[0]);
                return false;
            }
        } else if (str != null) {
            logger.error("Revenue must be set with currency", new Object[0]);
            return false;
        }
        return true;
    }

    private boolean isValidParameter(String str, String str2, String str3) {
        if (str == null) {
            logger.error("%s parameter %s is missing", str3, str2);
            return false;
        } else if (!str.equals(StringUtils.EMPTY)) {
            return true;
        } else {
            logger.error("%s parameter %s is empty", str3, str2);
            return false;
        }
    }
}
