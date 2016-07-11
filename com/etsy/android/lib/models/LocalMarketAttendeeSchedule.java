package com.etsy.android.lib.models;

import android.support.annotation.Nullable;
import com.appboy.Constants;
import com.etsy.android.lib.models.enums.WeekDay;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.gcm.Task;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class LocalMarketAttendeeSchedule extends BaseModel {
    private static final String TYPE_EVERY_WEEK = "every_week";
    private static final String TYPE_SPECIFIC_DATES = "specific_dates";
    protected List<Day> mDays;
    protected String mScheduleType;

    @Parcel
    public class Day extends BaseModel {
        private static final SimpleDateFormat DATE_PARSER;
        private static final SimpleDateFormat TIME_FORMAT_PARSER;
        protected WeekDay mDay;
        protected Date mFrom;
        protected Date mSpecificDate;
        protected Date mTo;

        static {
            DATE_PARSER = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            TIME_FORMAT_PARSER = new SimpleDateFormat(Constants.DEFAULT_TWENTY_FOUR_HOUR_TIME_FORMAT);
        }

        @Nullable
        public WeekDay getDay() {
            return this.mDay;
        }

        @Nullable
        public Date getSpecificDate() {
            return this.mSpecificDate;
        }

        @Nullable
        public Date getFrom() {
            return this.mFrom;
        }

        @Nullable
        public Date getTo() {
            return this.mTo;
        }

        public void parseData(JsonParser jsonParser) {
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                String currentName = jsonParser.getCurrentName();
                jsonParser.nextToken();
                if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                    Object obj = -1;
                    switch (currentName.hashCode()) {
                        case 3707:
                            if (currentName.equals(ResponseConstants.TO)) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 99228:
                            if (currentName.equals(ResponseConstants.DAY)) {
                                obj = null;
                                break;
                            }
                            break;
                        case 3151786:
                            if (currentName.equals(ResponseConstants.FROM)) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case Task.NETWORK_STATE_CONNECTED /*0*/:
                            try {
                                this.mSpecificDate = DATE_PARSER.parse(BaseModel.parseStringPreserveHTMLEscapeEncoding(jsonParser));
                                break;
                            } catch (ParseException e) {
                                this.mDay = WeekDay.getDay(BaseModel.parseStringPreserveHTMLEscapeEncoding(jsonParser));
                                break;
                            }
                        case Task.NETWORK_STATE_UNMETERED /*1*/:
                            this.mFrom = parseTime(jsonParser);
                            break;
                        case Task.NETWORK_STATE_ANY /*2*/:
                            this.mTo = parseTime(jsonParser);
                            break;
                        default:
                            jsonParser.skipChildren();
                            break;
                    }
                }
            }
        }

        private Date parseTime(JsonParser jsonParser) {
            try {
                return TIME_FORMAT_PARSER.parse(BaseModel.parseStringPreserveHTMLEscapeEncoding(jsonParser));
            } catch (ParseException e) {
                return null;
            }
        }
    }

    public LocalMarketAttendeeSchedule() {
        this.mScheduleType = StringUtils.EMPTY;
        this.mDays = new ArrayList();
    }

    public boolean isDaysOfWeekType() {
        return TYPE_EVERY_WEEK.equals(this.mScheduleType);
    }

    public List<Day> getDays() {
        return this.mDays;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case 110364486:
                        if (currentName.equals(ResponseConstants.TIMES)) {
                            obj = 1;
                            break;
                        }
                        break;
                    case 1677835234:
                        if (currentName.equals(ResponseConstants.SCHEDULE_TYPE)) {
                            obj = null;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mScheduleType = BaseModel.parseStringPreserveHTMLEscapeEncoding(jsonParser);
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mDays = BaseModel.parseArray(jsonParser, Day.class);
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }
}
