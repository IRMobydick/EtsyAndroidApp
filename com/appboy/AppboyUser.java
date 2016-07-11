package com.appboy;

import bo.app.bv;
import bo.app.ci;
import bo.app.da;
import bo.app.dg;
import bo.app.ey;
import bo.app.fa;
import bo.app.fd;
import com.appboy.enums.Gender;
import com.appboy.enums.Month;
import com.appboy.enums.NotificationSubscriptionType;
import com.appboy.models.outgoing.AttributionData;
import com.appboy.models.outgoing.FacebookUser;
import com.appboy.models.outgoing.TwitterUser;
import com.appboy.support.AppboyLogger;
import com.appboy.support.ValidationUtils;

public class AppboyUser {
    private static final String f867c;
    final Object f868a;
    volatile String f869b;
    private final fa f870d;
    private final bv f871e;
    private final ey f872f;
    private final ci f873g;

    static {
        f867c = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyUser.class.getName()});
    }

    AppboyUser(fa faVar, bv bvVar, String str, ci ciVar, ey eyVar) {
        this.f868a = new Object();
        this.f869b = str;
        this.f870d = faVar;
        this.f871e = bvVar;
        this.f873g = ciVar;
        this.f872f = eyVar;
    }

    public boolean setFirstName(String str) {
        try {
            this.f870d.m314a(str);
            return true;
        } catch (Throwable e) {
            AppboyLogger.m671w(f867c, "Failed to set first name to: " + str, e);
            return false;
        }
    }

    public boolean setLastName(String str) {
        try {
            this.f870d.m319b(str);
            return true;
        } catch (Throwable e) {
            AppboyLogger.m671w(f867c, "Failed to set last name to: " + str, e);
            return false;
        }
    }

    public boolean setEmail(String str) {
        try {
            return this.f870d.m320c(str);
        } catch (Throwable e) {
            AppboyLogger.m671w(f867c, "Failed to set email to: " + str, e);
            return false;
        }
    }

    public boolean setBio(String str) {
        return false;
    }

    public boolean setGender(Gender gender) {
        try {
            this.f870d.m308a(gender);
            return true;
        } catch (Throwable e) {
            AppboyLogger.m671w(f867c, "Failed to set gender to: " + gender, e);
            return false;
        }
    }

    public boolean setDateOfBirth(int i, Month month, int i2) {
        boolean z = false;
        try {
            z = this.f870d.m315a(i, month, i2);
        } catch (Throwable e) {
            AppboyLogger.m671w(f867c, String.format("Failed to set date of birth to: %d-%d-%d", new Object[]{Integer.valueOf(i), Integer.valueOf(month.getValue()), Integer.valueOf(i2)}), e);
        }
        return z;
    }

    public boolean setCountry(String str) {
        try {
            this.f870d.m321d(str);
            return true;
        } catch (Throwable e) {
            AppboyLogger.m671w(f867c, "Failed to set country to: " + str, e);
            return false;
        }
    }

    public boolean setHomeCity(String str) {
        try {
            this.f870d.m322e(str);
            return true;
        } catch (Throwable e) {
            AppboyLogger.m671w(f867c, "Failed to set home city to: " + str, e);
            return false;
        }
    }

    public boolean setIsSubscribedToEmails(boolean z) {
        return setEmailNotificationSubscriptionType(z ? NotificationSubscriptionType.SUBSCRIBED : NotificationSubscriptionType.UNSUBSCRIBED);
    }

    public boolean setEmailNotificationSubscriptionType(NotificationSubscriptionType notificationSubscriptionType) {
        try {
            this.f870d.m309a(notificationSubscriptionType);
            return true;
        } catch (Throwable e) {
            AppboyLogger.m671w(f867c, "Failed to set email notification subscription to: " + notificationSubscriptionType, e);
            return false;
        }
    }

    public boolean setPushNotificationSubscriptionType(NotificationSubscriptionType notificationSubscriptionType) {
        try {
            this.f870d.m318b(notificationSubscriptionType);
            return true;
        } catch (Throwable e) {
            AppboyLogger.m671w(f867c, "Failed to set push notification subscription to: " + notificationSubscriptionType, e);
            return false;
        }
    }

    public boolean setPhoneNumber(String str) {
        try {
            return this.f870d.m323f(str);
        } catch (Throwable e) {
            AppboyLogger.m671w(f867c, "Failed to set phone number to: " + str, e);
            return false;
        }
    }

    public boolean setCustomUserAttribute(String str, boolean z) {
        try {
            return this.f870d.m317a(str, Boolean.valueOf(z));
        } catch (Throwable e) {
            AppboyLogger.m671w(f867c, "Failed to set custom boolean attribute " + str + ".", e);
            return false;
        }
    }

    public boolean setCustomUserAttribute(String str, int i) {
        try {
            return this.f870d.m317a(str, Integer.valueOf(i));
        } catch (Throwable e) {
            AppboyLogger.m671w(f867c, "Failed to set custom integer attribute " + str + ".", e);
            return false;
        }
    }

    public boolean setCustomUserAttribute(String str, float f) {
        try {
            return this.f870d.m317a(str, Float.valueOf(f));
        } catch (Throwable e) {
            AppboyLogger.m671w(f867c, "Failed to set custom float attribute " + str + ".", e);
            return false;
        }
    }

    public boolean setCustomUserAttribute(String str, long j) {
        try {
            return this.f870d.m317a(str, Long.valueOf(j));
        } catch (Throwable e) {
            AppboyLogger.m671w(f867c, "Failed to set custom long attribute " + str + ".", e);
            return false;
        }
    }

    public boolean setCustomUserAttribute(String str, String str2) {
        try {
            return this.f870d.m317a(str, (Object) str2);
        } catch (Throwable e) {
            AppboyLogger.m671w(f867c, "Failed to set custom string attribute " + str + ".", e);
            return false;
        }
    }

    public boolean addToCustomAttributeArray(String str, String str2) {
        try {
            if (!ValidationUtils.isValidCustomAttributeKey(str) || ValidationUtils.isBlacklistedCustomAttributeKey(str, this.f872f.m302h()) || !ValidationUtils.isValidCustomAttributeValue(str2)) {
                return false;
            }
            this.f871e.m62a(da.m174e(ValidationUtils.ensureAppboyFieldLength(str), ValidationUtils.ensureAppboyFieldLength(str2)));
            return true;
        } catch (Throwable e) {
            AppboyLogger.m671w(f867c, "Failed to add custom attribute with key '" + str + "'.", e);
            return false;
        }
    }

    public boolean removeFromCustomAttributeArray(String str, String str2) {
        try {
            if (!ValidationUtils.isValidCustomAttributeKey(str) || ValidationUtils.isBlacklistedCustomAttributeKey(str, this.f872f.m302h()) || !ValidationUtils.isValidCustomAttributeValue(str2)) {
                return false;
            }
            this.f871e.m62a(da.m176f(ValidationUtils.ensureAppboyFieldLength(str), ValidationUtils.ensureAppboyFieldLength(str2)));
            return true;
        } catch (Throwable e) {
            AppboyLogger.m671w(f867c, "Failed to remove custom attribute with key '" + str + "'.", e);
            return false;
        }
    }

    public boolean setCustomAttributeArray(String str, String[] strArr) {
        try {
            if (!ValidationUtils.isValidCustomAttributeKey(str) || ValidationUtils.isBlacklistedCustomAttributeKey(str, this.f872f.m302h())) {
                return false;
            }
            str = ValidationUtils.ensureAppboyFieldLength(str);
            if (strArr != null) {
                strArr = ValidationUtils.ensureCustomAttributeArrayValues(strArr);
            }
            this.f871e.m62a(da.m159a(str, strArr));
            return true;
        } catch (Exception e) {
            AppboyLogger.m670w(f867c, "Failed to set custom attribute array with key: '" + str + "'.");
            return false;
        }
    }

    public boolean setCustomUserAttributeToNow(String str) {
        try {
            return this.f870d.m316a(str, fd.m330a());
        } catch (Throwable e) {
            AppboyLogger.m671w(f867c, "Failed to set custom attribute " + str + " to now.", e);
            return false;
        }
    }

    public boolean setCustomUserAttributeToSecondsFromEpoch(String str, long j) {
        try {
            return this.f870d.m316a(str, j);
        } catch (Throwable e) {
            AppboyLogger.m671w(f867c, "Failed to set custom attribute " + str + " to " + j + " seconds from epoch.", e);
            return false;
        }
    }

    public boolean incrementCustomUserAttribute(String str) {
        return incrementCustomUserAttribute(str, 1);
    }

    public boolean incrementCustomUserAttribute(String str, int i) {
        try {
            if (ValidationUtils.isBlacklistedCustomAttributeKey(str, this.f872f.m302h())) {
                return false;
            }
            this.f871e.m62a(da.m152a(str, i));
            return true;
        } catch (Throwable e) {
            AppboyLogger.m671w(f867c, "Failed to increment custom attribute " + str + " by " + i + ".", e);
            return false;
        }
    }

    public boolean unsetCustomUserAttribute(String str) {
        boolean z = false;
        try {
            if (!ValidationUtils.isBlacklistedCustomAttributeKey(str, this.f872f.m302h())) {
                z = this.f870d.m326i(str);
            }
        } catch (Throwable e) {
            AppboyLogger.m671w(f867c, "Failed to unset custom attribute " + str + ".", e);
        }
        return z;
    }

    public boolean setFacebookData(FacebookUser facebookUser) {
        try {
            this.f870d.m311a(facebookUser);
            return true;
        } catch (Throwable e) {
            AppboyLogger.m671w(f867c, "Failed to set Facebook user data.", e);
            return false;
        }
    }

    public boolean setTwitterData(TwitterUser twitterUser) {
        try {
            this.f870d.m312a(twitterUser);
            return true;
        } catch (Throwable e) {
            AppboyLogger.m671w(f867c, "Failed to set Twitter user data.", e);
            return false;
        }
    }

    public boolean setAttributionData(AttributionData attributionData) {
        try {
            this.f870d.m310a(attributionData);
            return true;
        } catch (Throwable e) {
            AppboyLogger.m671w(f867c, "Failed to set attribution data.", e);
            return false;
        }
    }

    public boolean setAvatarImageUrl(String str) {
        try {
            this.f870d.m324g(str);
            return true;
        } catch (Throwable e) {
            AppboyLogger.m671w(f867c, "Failed to set the avatar image URL.", e);
            return false;
        }
    }

    public String getUserId() {
        String str;
        synchronized (this.f868a) {
            str = this.f869b;
        }
        return str;
    }

    public void setLastKnownLocation(double d, double d2, Double d3, Double d4) {
        try {
            this.f873g.m46a(new dg(d, d2, d3, d4));
        } catch (Throwable e) {
            AppboyLogger.m671w(f867c, "Failed to manually set location.", e);
        }
    }
}
