package com.foresee.sdk.configuration;

import android.util.Log;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import com.google.gson.e;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.TreeSet;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Configuration implements Serializable {
    public static final int DEFAULT_EXIT_EXPIRY_DAYS = 7;
    public static final String DEFAULT_LOGGING_URL = "https://events.foreseeresults.com/rec/process?event=logit";
    public static final String DEFAULT_SERVICE_URL = "https://survey.foreseeresults.com/survey/";
    public static final String DEFAULT_SURVEY_URL_BASE = "https://survey.foreseeresults.com/survey/display?";
    public static final String NOTIFICATION_ICON_NAME = "ic_notification";
    public static final String NOTIFICATION_LAYOUT_NAME = "notification";
    private static e gson;
    private static Logger logger;
    private String clientId;
    private Map<String, String> cpps;
    private String customLogoPath;
    private boolean debugLoggingEnabled;
    private int exitExpiryDays;
    private String loggingUrl;
    private List<MeasureConfiguration> measureConfigs;
    private String notificationIconName;
    private String notificationLayoutName;
    public NotificationType notificationType;
    private boolean perfLoggingEnabled;
    private Map<String, String> queryStringParams;
    private int repeatDaysAfterComplete;
    private int repeatDaysAfterDecline;
    private String serviceUrl;
    private boolean sessionReplayEnabled;
    private boolean shouldPresentOnExit;
    private boolean shouldUseLocalNotification;
    private boolean skipPooling;
    private String surveyUrlBase;
    private TreeSet<String> whiteListedDomains;

    static {
        gson = new e();
        logger = Logger.getLogger(Configuration.class.getName());
    }

    public void setDebugLoggingEnabled(boolean z) {
        this.debugLoggingEnabled = z;
        Log.d("FORESEE_SDK_COMMON", String.format("Setting debugLogEnabled = %b", new Object[]{Boolean.valueOf(this.debugLoggingEnabled)}));
    }

    public void setSkipPooling(boolean z) {
        this.skipPooling = z;
        Log.d("FORESEE_SDK_COMMON", String.format("Setting skipPoolingCheck = %b", new Object[]{Boolean.valueOf(this.skipPooling)}));
    }

    private Configuration(String str) {
        this.loggingUrl = DEFAULT_LOGGING_URL;
        this.serviceUrl = DEFAULT_SERVICE_URL;
        this.surveyUrlBase = DEFAULT_SURVEY_URL_BASE;
        this.shouldUseLocalNotification = false;
        this.measureConfigs = new ArrayList();
        this.notificationLayoutName = NOTIFICATION_LAYOUT_NAME;
        this.notificationIconName = NOTIFICATION_ICON_NAME;
        this.cpps = new HashMap();
        this.queryStringParams = new HashMap();
        this.whiteListedDomains = new TreeSet();
        this.exitExpiryDays = DEFAULT_EXIT_EXPIRY_DAYS;
        this.clientId = str;
    }

    public Configuration() {
        this.loggingUrl = DEFAULT_LOGGING_URL;
        this.serviceUrl = DEFAULT_SERVICE_URL;
        this.surveyUrlBase = DEFAULT_SURVEY_URL_BASE;
        this.shouldUseLocalNotification = false;
        this.measureConfigs = new ArrayList();
        this.notificationLayoutName = NOTIFICATION_LAYOUT_NAME;
        this.notificationIconName = NOTIFICATION_ICON_NAME;
        this.cpps = new HashMap();
        this.queryStringParams = new HashMap();
        this.whiteListedDomains = new TreeSet();
        this.exitExpiryDays = DEFAULT_EXIT_EXPIRY_DAYS;
    }

    public NotificationType getNotificationType() {
        if (this.shouldPresentOnExit) {
            return NotificationType.ON_EXIT;
        }
        if (this.shouldUseLocalNotification) {
            return NotificationType.LOCAL;
        }
        return NotificationType.IMMEDIATE;
    }

    public void setNotificationType(NotificationType notificationType) {
        switch (1.$SwitchMap$com$foresee$sdk$configuration$Configuration$NotificationType[notificationType.ordinal()]) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                shouldPresentImmediate();
            case Task.NETWORK_STATE_ANY /*2*/:
                shouldPresentOnExit();
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                shouldPresentOnExitLocal();
            default:
        }
    }

    public void setWhitelistedHosts(List<String> list) {
        for (String addWhiteListedHost : list) {
            addWhiteListedHost(addWhiteListedHost);
        }
    }

    public void setCPPParameters(Map<String, String> map) {
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            addCpp((String) entry.getKey(), (String) entry.getValue());
            it.remove();
        }
    }

    public static Configuration baseConfiguration(String str) {
        Configuration configuration = new Configuration(str);
        configuration.addDefaultWhitelistedHosts();
        return configuration;
    }

    public static Configuration defaultConfiguration(String str) {
        return baseConfiguration(str);
    }

    public Configuration addMeasure(MeasureConfiguration measureConfiguration) {
        this.measureConfigs.add(measureConfiguration);
        return this;
    }

    public Configuration clearMeasures() {
        this.measureConfigs.clear();
        return this;
    }

    public List<MeasureConfiguration> getMeasureConfigs() {
        return this.measureConfigs;
    }

    public void setMeasureConfigs(List<MeasureConfiguration> list) {
        for (MeasureConfiguration addMeasure : list) {
            addMeasure(addMeasure);
        }
    }

    public Configuration repeatAfterDecline(int i) {
        this.repeatDaysAfterDecline = i;
        return this;
    }

    public Configuration shouldRepeatSurveyAfterDays(int i) {
        this.repeatDaysAfterComplete = i;
        return this;
    }

    public Configuration withCustomLogo(String str) {
        this.customLogoPath = str;
        return this;
    }

    public Configuration shouldPresentOnExit() {
        this.shouldPresentOnExit = true;
        this.shouldUseLocalNotification = false;
        return this;
    }

    public Configuration shouldPresentOnExitLocal() {
        this.shouldPresentOnExit = true;
        this.shouldUseLocalNotification = true;
        return this;
    }

    public Configuration withNotificationLayout(String str) {
        this.notificationLayoutName = str;
        return this;
    }

    public Configuration withNotificationIcon(String str) {
        this.notificationIconName = str;
        return this;
    }

    public Configuration shouldPresentImmediate() {
        this.shouldPresentOnExit = false;
        this.shouldUseLocalNotification = false;
        return this;
    }

    public Configuration addWhiteListedHost(String str) {
        this.whiteListedDomains.add(str);
        return this;
    }

    public Configuration withOnExitExpiryDays(int i) {
        this.exitExpiryDays = i;
        return this;
    }

    public int getRepeatDaysAfterDecline() {
        return this.repeatDaysAfterDecline;
    }

    public int getRepeatDaysAfterComplete() {
        return this.repeatDaysAfterComplete;
    }

    public int getExitExpiryDays() {
        return this.exitExpiryDays;
    }

    public TreeSet<String> getWhiteListedDomains() {
        return this.whiteListedDomains;
    }

    public String getLoggingUrl() {
        return this.loggingUrl;
    }

    public String getServiceUrl() {
        return this.serviceUrl;
    }

    public String getClientId() {
        return this.clientId;
    }

    public String getSurveyUrlBase() {
        return this.surveyUrlBase;
    }

    public String getCustomLogoPath() {
        return this.customLogoPath;
    }

    public boolean shouldInviteOnExit() {
        return this.shouldPresentOnExit;
    }

    public boolean supportsReinvite() {
        return this.repeatDaysAfterComplete > 0;
    }

    public boolean isSessionReplayEnabled() {
        return this.sessionReplayEnabled;
    }

    public boolean isPerfLoggingEnabled() {
        return this.perfLoggingEnabled;
    }

    public void setPerfLoggingEnabled(boolean z) {
        this.perfLoggingEnabled = z;
    }

    public MeasureConfiguration findMeasureByName(String str) {
        for (MeasureConfiguration measureConfiguration : this.measureConfigs) {
            if (measureConfiguration.getSurveyId().equals(str)) {
                return measureConfiguration;
            }
        }
        return null;
    }

    public MeasureConfiguration findMeasureBySid(String str) {
        for (MeasureConfiguration measureConfiguration : this.measureConfigs) {
            if (measureConfiguration.getSurveyId().equals(str)) {
                return measureConfiguration;
            }
        }
        return null;
    }

    public boolean shouldUseLocalNotification() {
        return this.shouldUseLocalNotification;
    }

    public String getNotificationLayoutName() {
        return this.notificationLayoutName;
    }

    public String getNotificationIconName() {
        return this.notificationIconName;
    }

    public Configuration addCpp(String str, String str2) {
        this.cpps.put(str, str2);
        return this;
    }

    public Configuration addCpp(String str, Integer num) {
        this.cpps.put(str, num.toString());
        return this;
    }

    public Configuration removeCpp(String str) {
        this.cpps.remove(str);
        return this;
    }

    public Configuration clearCpps() {
        this.cpps.clear();
        return this;
    }

    public Map<String, String> getCpps() {
        return this.cpps;
    }

    public Configuration addQueryStringParam(String str, String str2) {
        this.queryStringParams.put(str, str2);
        return this;
    }

    public Configuration removeQueryStringParam(String str, String str2) {
        this.queryStringParams.remove(str);
        return this;
    }

    public Map<String, String> getQueryStringParams() {
        return this.queryStringParams;
    }

    public boolean shouldSkipPoolingCheck() {
        return this.skipPooling;
    }

    public boolean isDebugLoggingEnabled() {
        return this.debugLoggingEnabled;
    }

    public boolean isDomainWhiteListed(String str) {
        Matcher matcher = Pattern.compile("([\\w]*.[\\w]*$)").matcher(URI.create(str).getHost());
        if (matcher.find()) {
            return this.whiteListedDomains.contains(matcher.group());
        }
        return false;
    }

    void addDefaultWhitelistedHosts() {
        Properties properties = new Properties();
        try {
            InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("default-whitelist.properties");
            if (resourceAsStream != null) {
                properties.load(resourceAsStream);
                for (String addWhiteListedHost : properties.getProperty("whiteListedHosts").split(",")) {
                    addWhiteListedHost(addWhiteListedHost);
                }
            } else {
                logger.warning("Unable to load white-listed hosts from properties file");
            }
            resourceAsStream.close();
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Configuration)) {
            return false;
        }
        Configuration configuration = (Configuration) obj;
        if (this.debugLoggingEnabled != configuration.debugLoggingEnabled) {
            return false;
        }
        if (this.exitExpiryDays != configuration.exitExpiryDays) {
            return false;
        }
        if (this.repeatDaysAfterDecline != configuration.repeatDaysAfterDecline) {
            return false;
        }
        if (this.perfLoggingEnabled != configuration.perfLoggingEnabled) {
            return false;
        }
        if (this.sessionReplayEnabled != configuration.sessionReplayEnabled) {
            return false;
        }
        if (this.shouldPresentOnExit != configuration.shouldPresentOnExit) {
            return false;
        }
        if (this.shouldUseLocalNotification != configuration.shouldUseLocalNotification) {
            return false;
        }
        if (this.skipPooling != configuration.skipPooling) {
            return false;
        }
        if (this.repeatDaysAfterComplete != configuration.repeatDaysAfterComplete) {
            return false;
        }
        if (!this.clientId.equals(configuration.clientId)) {
            return false;
        }
        if (this.cpps == null ? configuration.cpps != null : !this.cpps.equals(configuration.cpps)) {
            return false;
        }
        if (this.customLogoPath == null ? configuration.customLogoPath != null : !this.customLogoPath.equals(configuration.customLogoPath)) {
            return false;
        }
        if (this.loggingUrl == null ? configuration.loggingUrl != null : !this.loggingUrl.equals(configuration.loggingUrl)) {
            return false;
        }
        if (this.measureConfigs == null ? configuration.measureConfigs != null : !this.measureConfigs.equals(configuration.measureConfigs)) {
            return false;
        }
        if (this.notificationIconName == null ? configuration.notificationIconName != null : !this.notificationIconName.equals(configuration.notificationIconName)) {
            return false;
        }
        if (this.notificationLayoutName == null ? configuration.notificationLayoutName != null : !this.notificationLayoutName.equals(configuration.notificationLayoutName)) {
            return false;
        }
        if (this.notificationType != configuration.notificationType) {
            return false;
        }
        if (this.queryStringParams == null ? configuration.queryStringParams != null : !this.queryStringParams.equals(configuration.queryStringParams)) {
            return false;
        }
        if (this.serviceUrl == null ? configuration.serviceUrl != null : !this.serviceUrl.equals(configuration.serviceUrl)) {
            return false;
        }
        if (this.surveyUrlBase == null ? configuration.surveyUrlBase != null : !this.surveyUrlBase.equals(configuration.surveyUrlBase)) {
            return false;
        }
        if (this.whiteListedDomains != null) {
            if (this.whiteListedDomains.equals(configuration.whiteListedDomains)) {
                return true;
            }
        } else if (configuration.whiteListedDomains == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 1;
        int hashCode2 = (((this.notificationType != null ? this.notificationType.hashCode() : 0) * 31) + this.repeatDaysAfterDecline) * 31;
        if (this.loggingUrl != null) {
            hashCode = this.loggingUrl.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.serviceUrl != null) {
            hashCode = this.serviceUrl.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.surveyUrlBase != null) {
            hashCode = this.surveyUrlBase.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.customLogoPath != null) {
            hashCode = this.customLogoPath.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.shouldPresentOnExit) {
            hashCode = 1;
        } else {
            hashCode = 0;
        }
        hashCode2 = (((((hashCode + hashCode2) * 31) + this.clientId.hashCode()) * 31) + this.repeatDaysAfterComplete) * 31;
        if (this.shouldUseLocalNotification) {
            hashCode = 1;
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.measureConfigs != null) {
            hashCode = this.measureConfigs.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.notificationLayoutName != null) {
            hashCode = this.notificationLayoutName.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.notificationIconName != null) {
            hashCode = this.notificationIconName.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.cpps != null) {
            hashCode = this.cpps.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.queryStringParams != null) {
            hashCode = this.queryStringParams.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.whiteListedDomains != null) {
            hashCode = this.whiteListedDomains.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (((hashCode + hashCode2) * 31) + this.exitExpiryDays) * 31;
        if (this.sessionReplayEnabled) {
            hashCode = 1;
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.skipPooling) {
            hashCode = 1;
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.debugLoggingEnabled) {
            hashCode = 1;
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + hashCode2) * 31;
        if (!this.perfLoggingEnabled) {
            i = 0;
        }
        return hashCode + i;
    }
}
