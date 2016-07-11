package com.foresee.sdk.configuration;

import com.google.gson.p020a.SerializedName;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MeasureConfiguration implements Serializable {
    public static final String DEFAULT_SURVEY_ID = "app_test_1";
    public static final int DISABLED = Integer.MAX_VALUE;
    private static final long serialVersionUID = 1;
    @SerializedName(a = "daysSinceLaunch")
    private int maxDaysSinceLaunch;
    @SerializedName(a = "launchCount")
    private int maxLaunchCount;
    private String name;
    private Map<String, Integer> significantEventThresholds;
    private String surveyId;

    public MeasureConfiguration() {
        this.maxLaunchCount = DISABLED;
        this.maxDaysSinceLaunch = DISABLED;
        this.significantEventThresholds = new HashMap();
    }

    private MeasureConfiguration(String str) {
        this.maxLaunchCount = DISABLED;
        this.maxDaysSinceLaunch = DISABLED;
        this.significantEventThresholds = new HashMap();
        this.surveyId = str;
    }

    public void setSurveyId(String str) {
        this.surveyId = str;
    }

    public void setSignificantEventThresholds(Map<String, Integer> map) {
        this.significantEventThresholds = map;
    }

    public Map<String, Integer> getSignificantEventThresholds() {
        return this.significantEventThresholds;
    }

    public static MeasureConfiguration defaultConfig(String str) {
        MeasureConfiguration base = base(str);
        base.maxLaunchCount = DISABLED;
        base.maxDaysSinceLaunch = DISABLED;
        return base;
    }

    public static MeasureConfiguration base(String str) {
        return new MeasureConfiguration(str);
    }

    public MeasureConfiguration withMaxLaunchCount(int i) {
        this.maxLaunchCount = i;
        return this;
    }

    public MeasureConfiguration withMaxDaysSinceLaunch(int i) {
        this.maxDaysSinceLaunch = i;
        return this;
    }

    public MeasureConfiguration addSignificantEventThreshold(String str, int i) {
        this.significantEventThresholds.put(str, Integer.valueOf(i));
        return this;
    }

    public int getSignificantEventThreshold(String str) {
        if (this.significantEventThresholds.containsKey(str)) {
            return ((Integer) this.significantEventThresholds.get(str)).intValue();
        }
        return 0;
    }

    public int getMaxLaunchCount() {
        return this.maxLaunchCount;
    }

    public int getMaxDaysSinceLaunch() {
        return this.maxDaysSinceLaunch;
    }

    public String getSurveyId() {
        return this.surveyId;
    }

    public boolean isEligible(int i, Date date, Map<String, SignificantEvent> map) {
        return (meetsFirstLaunchDateThreshold(date) | meetsLaunchCountThreshold(i)) | meetsSignificantEventsThreshold(map);
    }

    private boolean meetsFirstLaunchDateThreshold(Date date) {
        if (!(date == null || this.maxDaysSinceLaunch == 0)) {
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            instance.add(5, this.maxDaysSinceLaunch);
            Date date2 = new Date();
            if (date2.equals(instance.getTime()) || date2.after(instance.getTime())) {
                return true;
            }
        }
        return false;
    }

    private boolean meetsLaunchCountThreshold(int i) {
        return i >= this.maxLaunchCount;
    }

    private boolean meetsSignificantEventsThreshold(Map<String, SignificantEvent> map) {
        for (Entry entry : map.entrySet()) {
            if (this.significantEventThresholds.containsKey(entry.getKey()) && ((SignificantEvent) entry.getValue()).getCount() >= ((Integer) this.significantEventThresholds.get(entry.getKey())).intValue()) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MeasureConfiguration measureConfiguration = (MeasureConfiguration) obj;
        if (this.maxDaysSinceLaunch != measureConfiguration.maxDaysSinceLaunch) {
            return false;
        }
        if (this.maxLaunchCount != measureConfiguration.maxLaunchCount) {
            return false;
        }
        if (this.significantEventThresholds == null ? measureConfiguration.significantEventThresholds != null : !this.significantEventThresholds.equals(measureConfiguration.significantEventThresholds)) {
            return false;
        }
        if (this.surveyId.equals(measureConfiguration.surveyId)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.significantEventThresholds != null ? this.significantEventThresholds.hashCode() : 0) + (((((this.maxLaunchCount * 31) + this.maxDaysSinceLaunch) * 31) + this.surveyId.hashCode()) * 31);
    }
}
