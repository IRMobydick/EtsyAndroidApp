package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.util.Calendar;
import java.util.Date;

@JacksonStdImpl
final class StdKeyDeserializer$CalendarKD extends StdKeyDeserializer {
    private static final long serialVersionUID = 1;

    protected StdKeyDeserializer$CalendarKD() {
        super(Calendar.class);
    }

    public Object _parse(String str, DeserializationContext deserializationContext) {
        Date parseDate = deserializationContext.parseDate(str);
        return parseDate == null ? null : deserializationContext.constructCalendar(parseDate);
    }
}
