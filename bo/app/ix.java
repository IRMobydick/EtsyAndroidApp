package bo.app;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public final class ix {
    public boolean f783a;
    private StringBuilder f784b;
    private String f785c;
    private Object f786d;
    private Object f787e;
    private final List<iu> f788f;

    public ix() {
        this((byte) 0);
    }

    private ix(byte b) {
        this.f788f = new ArrayList();
        this.f783a = true;
        this.f784b = new StringBuilder(StringUtils.EMPTY);
    }

    public final void m577a(String str) {
        this.f783a = false;
        if (this.f784b.length() == 0) {
            this.f784b.append(str);
        } else {
            this.f784b.append(" ; ").append(str);
        }
    }

    public final ix m576a(String str, Object obj, Object obj2) {
        this.f788f.add(new iu(str, obj, obj2));
        this.f785c = str;
        this.f786d = obj;
        this.f787e = obj2;
        m577a(str + "\nExpected: " + m574a(obj) + "\n     got: " + m574a(obj2) + "\n");
        return this;
    }

    public final ix m575a(String str, Object obj) {
        m577a(str + "\nExpected: " + m574a(obj) + "\n     but none found\n");
        return this;
    }

    public final ix m578b(String str, Object obj) {
        m577a(str + "\nUnexpected: " + m574a(obj) + "\n");
        return this;
    }

    private static String m574a(Object obj) {
        if (obj instanceof JSONArray) {
            return "a JSON array";
        }
        if (obj instanceof JSONObject) {
            return "a JSON object";
        }
        return obj.toString();
    }

    public final String toString() {
        return this.f784b.toString();
    }
}
