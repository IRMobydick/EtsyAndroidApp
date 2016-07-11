package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.TimeZone;

public class DateDeserializers {
    private static final HashSet<String> _classNames;

    @JacksonStdImpl
    public class CalendarDeserializer extends DateBasedDeserializer<Calendar> {
        public static final CalendarDeserializer gregorianInstance;
        public static final CalendarDeserializer instance;
        protected final Class<? extends Calendar> _calendarClass;

        public /* bridge */ /* synthetic */ JsonDeserializer createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) {
            return super.createContextual(deserializationContext, beanProperty);
        }

        static {
            instance = new CalendarDeserializer();
            gregorianInstance = new CalendarDeserializer(GregorianCalendar.class);
        }

        public CalendarDeserializer() {
            super(Calendar.class);
            this._calendarClass = null;
        }

        public CalendarDeserializer(Class<? extends Calendar> cls) {
            super(cls);
            this._calendarClass = cls;
        }

        public CalendarDeserializer(CalendarDeserializer calendarDeserializer, DateFormat dateFormat, String str) {
            super(calendarDeserializer, dateFormat, str);
            this._calendarClass = calendarDeserializer._calendarClass;
        }

        protected CalendarDeserializer withDateFormat(DateFormat dateFormat, String str) {
            return new CalendarDeserializer(this, dateFormat, str);
        }

        public Calendar deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            Date _parseDate = _parseDate(jsonParser, deserializationContext);
            if (_parseDate == null) {
                return null;
            }
            if (this._calendarClass == null) {
                return deserializationContext.constructCalendar(_parseDate);
            }
            try {
                Calendar calendar = (Calendar) this._calendarClass.newInstance();
                calendar.setTimeInMillis(_parseDate.getTime());
                TimeZone timeZone = deserializationContext.getTimeZone();
                if (timeZone == null) {
                    return calendar;
                }
                calendar.setTimeZone(timeZone);
                return calendar;
            } catch (Throwable e) {
                throw deserializationContext.instantiationException(this._calendarClass, e);
            }
        }
    }

    static {
        int i = 0;
        _classNames = new HashSet();
        Class[] clsArr = new Class[]{Calendar.class, GregorianCalendar.class, java.sql.Date.class, Date.class, Timestamp.class, TimeZone.class};
        int length = clsArr.length;
        while (i < length) {
            _classNames.add(clsArr[i].getName());
            i++;
        }
    }

    @Deprecated
    public static StdDeserializer<?>[] all() {
        return new StdDeserializer[]{CalendarDeserializer.instance, DateDeserializer.instance, CalendarDeserializer.gregorianInstance, SqlDateDeserializer.instance, TimestampDeserializer.instance, TimeZoneDeserializer.instance};
    }

    public static JsonDeserializer<?> find(Class<?> cls, String str) {
        if (!_classNames.contains(str)) {
            return null;
        }
        if (cls == Calendar.class) {
            return CalendarDeserializer.instance;
        }
        if (cls == Date.class) {
            return DateDeserializer.instance;
        }
        if (cls == java.sql.Date.class) {
            return SqlDateDeserializer.instance;
        }
        if (cls == Timestamp.class) {
            return TimestampDeserializer.instance;
        }
        if (cls == TimeZone.class) {
            return TimeZoneDeserializer.instance;
        }
        if (cls == GregorianCalendar.class) {
            return CalendarDeserializer.gregorianInstance;
        }
        throw new IllegalArgumentException("Internal error: can't find deserializer for " + str);
    }
}
