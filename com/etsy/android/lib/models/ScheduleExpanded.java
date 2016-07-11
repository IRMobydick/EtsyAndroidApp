package com.etsy.android.lib.models;

import com.etsy.android.lib.models.datatypes.TimeRange;
import com.etsy.android.lib.models.enums.WeekDay;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.HashMap;
import org.parceler.Parcel;

@Parcel
public class ScheduleExpanded extends BaseModel {
    protected HashMap<WeekDay, TimeRange> mDailySchedule;

    public ScheduleExpanded() {
        this.mDailySchedule = new HashMap();
    }

    public TimeRange getTimeRange(WeekDay weekDay) {
        return (TimeRange) this.mDailySchedule.get(weekDay);
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                WeekDay day = WeekDay.getDay(currentName);
                if (day != null) {
                    this.mDailySchedule.put(day, BaseModel.parseObject(jsonParser, TimeRange.class));
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
