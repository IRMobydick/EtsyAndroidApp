package bo.app;

import com.appboy.Constants;
import com.appboy.IAppboy;
import com.appboy.enums.Gender;
import com.appboy.enums.Month;
import com.appboy.models.outgoing.AppboyProperties;
import com.appboy.support.AppboyLogger;
import com.etsy.android.lib.R;
import com.etsy.android.uikit.ui.shop.ShopHomeAdapter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.wearable.DataMap;
import java.math.BigDecimal;
import org.json.JSONObject;

public class fm {
    private static final String f467a;

    static {
        f467a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, fm.class.getName()});
    }

    public static dj m362a(DataMap dataMap) {
        dj djVar = null;
        if (m365b(dataMap) == an.SEND_WEAR_DEVICE) {
            try {
                djVar = dj.m194a(new JSONObject(dataMap.getString("v0")));
            } catch (Throwable e) {
                AppboyLogger.m665e(f467a, "Wear device couldn't be recreated.", e);
            }
        }
        return djVar;
    }

    public static an m365b(DataMap dataMap) {
        return an.m18a(dataMap.getString(Constants.APPBOY_PUSH_TITLE_KEY));
    }

    public static boolean m364a(DataMap dataMap, IAppboy iAppboy) {
        AppboyProperties a;
        an b = m365b(dataMap);
        boolean z = dataMap.getBoolean("h");
        if (z) {
            a = m363a(dataMap.getString(Constants.APPBOY_PUSH_PRIORITY_KEY));
        } else {
            a = null;
        }
        String string;
        switch (fn.f468a[b.ordinal()]) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                string = dataMap.getString("v0");
                if (!z || a == null) {
                    return iAppboy.logCustomEvent(string);
                }
                return iAppboy.logCustomEvent(string, a);
            case Task.NETWORK_STATE_ANY /*2*/:
                string = dataMap.getString("v0");
                String string2 = dataMap.getString("v1");
                BigDecimal bigDecimal = new BigDecimal(dataMap.getString("v2"));
                if (!z || a == null) {
                    return iAppboy.logPurchase(string, string2, bigDecimal);
                }
                return iAppboy.logPurchase(string, string2, bigDecimal, a);
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                return iAppboy.logPushNotificationOpened(dataMap.getString("v0"));
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                return iAppboy.submitFeedback(dataMap.getString("v0"), dataMap.getString("v1"), dataMap.getBoolean("v2"));
            case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                return iAppboy.getCurrentUser().addToCustomAttributeArray(dataMap.getString("k"), dataMap.getString("v0"));
            case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
                return iAppboy.getCurrentUser().incrementCustomUserAttribute(dataMap.getString("k"), dataMap.getInt("v0"));
            case CommonStatusCodes.NETWORK_ERROR /*7*/:
                return iAppboy.getCurrentUser().setCustomAttributeArray(dataMap.getString("k"), dataMap.getStringArray("v0"));
            case CommonStatusCodes.INTERNAL_ERROR /*8*/:
                String string3 = dataMap.getString("k");
                int i = dataMap.getInt("v1");
                if (i == 1) {
                    return iAppboy.getCurrentUser().setCustomUserAttribute(string3, dataMap.getBoolean("v0"));
                }
                if (i == 3) {
                    return iAppboy.getCurrentUser().setCustomUserAttribute(string3, dataMap.getFloat("v0"));
                }
                if (i == 4) {
                    return iAppboy.getCurrentUser().setCustomUserAttribute(string3, dataMap.getInt("v0"));
                }
                if (i == 5) {
                    return iAppboy.getCurrentUser().setCustomUserAttribute(string3, dataMap.getLong("v0"));
                }
                if (i == 2) {
                    return iAppboy.getCurrentUser().setCustomUserAttribute(string3, dataMap.getString("v0"));
                }
                return false;
            case CommonStatusCodes.SERVICE_INVALID /*9*/:
                return iAppboy.getCurrentUser().setCustomUserAttributeToNow(dataMap.getString("k"));
            case CommonStatusCodes.DEVELOPER_ERROR /*10*/:
                return iAppboy.getCurrentUser().unsetCustomUserAttribute(dataMap.getString("k"));
            case CommonStatusCodes.LICENSE_CHECK_FAILED /*11*/:
                return iAppboy.getCurrentUser().setCustomUserAttributeToSecondsFromEpoch(dataMap.getString("k"), dataMap.getLong("v0"));
            case ShopHomeAdapter.TYPE_BUTTON_BLUE_WITH_DESCRIPTION /*12*/:
                Double valueOf;
                Double valueOf2;
                double d = dataMap.getDouble("v0");
                double d2 = dataMap.getDouble("v1");
                if (dataMap.containsKey("v2")) {
                    valueOf = Double.valueOf(dataMap.getDouble("v2"));
                } else {
                    valueOf = null;
                }
                if (dataMap.containsKey("v3")) {
                    valueOf2 = Double.valueOf(dataMap.getDouble("v3"));
                } else {
                    valueOf2 = null;
                }
                iAppboy.getCurrentUser().setLastKnownLocation(d, d2, valueOf2, valueOf);
                return true;
            case CommonStatusCodes.ERROR /*13*/:
                return iAppboy.getCurrentUser().setAvatarImageUrl(dataMap.getString("v0"));
            case CommonStatusCodes.INTERRUPTED /*14*/:
                return iAppboy.getCurrentUser().setCountry(dataMap.getString("v0"));
            case CommonStatusCodes.TIMEOUT /*15*/:
                return iAppboy.getCurrentUser().setEmail(dataMap.getString("v0"));
            case CommonStatusCodes.CANCELED /*16*/:
                return iAppboy.getCurrentUser().setFirstName(dataMap.getString("v0"));
            case CommonStatusCodes.API_NOT_CONNECTED /*17*/:
                return iAppboy.getCurrentUser().setHomeCity(dataMap.getString("v0"));
            case ConnectionResult.SERVICE_UPDATING /*18*/:
                return iAppboy.getCurrentUser().setLastName(dataMap.getString("v0"));
            case ConnectionResult.SERVICE_MISSING_PERMISSION /*19*/:
                return iAppboy.getCurrentUser().setPhoneNumber(dataMap.getString("v0"));
            case ConnectionResult.RESTRICTED_PROFILE /*20*/:
                return iAppboy.getCurrentUser().setGender(Gender.valueOf(dataMap.getString("v0")));
            case R.Toolbar_navigationContentDescription /*21*/:
                return iAppboy.getCurrentUser().setDateOfBirth(dataMap.getInt("v0"), Month.valueOf(dataMap.getString("v1")), dataMap.getInt("v2"));
            case ShopHomeAdapter.TYPE_REVIEW_RATING /*22*/:
                AppboyLogger.m666i(f467a, "Got an unsupported wearable sdk action. DataMap: " + dataMap.toString());
                return false;
            default:
                AppboyLogger.m666i(f467a, "No current implementation for action in DataMap: " + dataMap.toString());
                return false;
        }
    }

    private static AppboyProperties m363a(String str) {
        try {
            return new AppboyProperties(new JSONObject(str));
        } catch (Throwable e) {
            AppboyLogger.m665e(f467a, "Failed to create properties object from string: " + str, e);
            return null;
        }
    }
}
