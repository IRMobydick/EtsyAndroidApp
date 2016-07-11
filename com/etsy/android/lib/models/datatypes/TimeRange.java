package com.etsy.android.lib.models.datatypes;

import com.appboy.Constants;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.enums.WeekDay;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.parceler.Parcel;

@Parcel
public class TimeRange extends BaseModel {
    private static final String TAG;
    private static final SimpleDateFormat TIME_FORMAT;
    protected WeekDay mEndDay;
    protected Calendar mEndTime;
    protected WeekDay mStartDay;
    protected Calendar mStartTime;

    static {
        TIME_FORMAT = new SimpleDateFormat(Constants.DEFAULT_TWENTY_FOUR_HOUR_TIME_FORMAT);
        TAG = EtsyDebug.m1891a(TimeRange.class);
    }

    public Calendar getStartTime() {
        return this.mStartTime;
    }

    public Calendar getEndTime() {
        return this.mEndTime;
    }

    public WeekDay getStartDay() {
        return this.mStartDay;
    }

    public WeekDay getEndDay() {
        return this.mEndDay;
    }

    @Deprecated
    public int getStartDayString() {
        return this.mStartDay.getString();
    }

    @Deprecated
    public int getEndDayString() {
        return this.mEndDay.getString();
    }

    public boolean isOpen(Calendar calendar) {
        return this.mStartTime != null && this.mEndTime != null && this.mStartTime.get(11) <= calendar.get(11) && this.mStartTime.get(12) <= calendar.get(12) && this.mEndTime.get(11) > calendar.get(11) && this.mEndTime.get(12) <= calendar.get(12);
    }

    public boolean isStartTimeOfDayAfter(Calendar calendar) {
        if (this.mStartTime == null) {
            return false;
        }
        int i = this.mStartTime.get(11);
        int i2 = this.mStartTime.get(12);
        int i3 = this.mStartTime.get(13);
        if (i > calendar.get(11) || ((i == calendar.get(11) && i2 > calendar.get(12)) || (i == calendar.get(11) && i2 == calendar.get(12) && i3 > calendar.get(13)))) {
            return true;
        }
        return false;
    }

    public boolean isOneDay() {
        return getStartDay() == getEndDay();
    }

    private Calendar parseTime(String str) {
        try {
            Calendar instance = Calendar.getInstance();
            instance.setTime(TIME_FORMAT.parse(str));
            return instance;
        } catch (ParseException e) {
            EtsyDebug.m1919e(TAG, "Cannot parse hours in TimeRange " + str);
            return null;
        }
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if ("start_time".equals(currentName)) {
                    this.mStartTime = parseTime(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if ("end_time".equals(currentName)) {
                    this.mEndTime = parseTime(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if ("start_day".equals(currentName)) {
                    this.mStartDay = WeekDay.getDay(jsonParser.getValueAsInt());
                } else if ("end_day".equals(currentName)) {
                    this.mEndDay = WeekDay.getDay(jsonParser.getValueAsInt());
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
