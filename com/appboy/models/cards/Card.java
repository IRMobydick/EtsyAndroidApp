package com.appboy.models.cards;

import bo.app.ce;
import bo.app.da;
import bo.app.eu;
import bo.app.ev;
import bo.app.fd;
import bo.app.fg;
import bo.app.fj;
import com.appboy.Constants;
import com.appboy.enums.CardCategory;
import com.appboy.models.IPutIntoJson;
import com.appboy.support.AppboyLogger;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import org.json.JSONArray;
import org.json.JSONObject;

public class Card extends Observable implements IPutIntoJson<JSONObject> {
    public static final String CATEGORIES = "categories";
    public static final String CREATED = "created";
    public static final long DEFAULT_EXPIRES_AT = -1;
    public static final String EXPIRES_AT = "expires_at";
    public static final String ID = "id";
    public static final String UPDATED = "updated";
    public static final String VIEWED = "viewed";
    private static final String f950j;
    protected final JSONObject f951a;
    protected final Map<String, String> f952b;
    protected final String f953c;
    protected boolean f954d;
    protected boolean f955e;
    protected final long f956f;
    protected final long f957g;
    protected final long f958h;
    protected final EnumSet<CardCategory> f959i;
    private final ce f960k;
    private final eu f961l;

    static {
        f950j = String.format("%s.%s", new Object[]{Constants.APPBOY, Card.class.getName()});
    }

    public Card(JSONObject jSONObject) {
        this(jSONObject, null, null);
    }

    public Card(JSONObject jSONObject, ce ceVar, eu euVar) {
        this.f951a = jSONObject;
        this.f952b = fg.m341a(jSONObject.optJSONObject("extras"), new HashMap());
        this.f960k = ceVar;
        this.f961l = euVar;
        this.f953c = jSONObject.getString(ID);
        this.f954d = jSONObject.getBoolean(VIEWED);
        this.f955e = this.f954d;
        this.f956f = jSONObject.getLong(CREATED);
        this.f957g = jSONObject.getLong(UPDATED);
        this.f958h = jSONObject.optLong(EXPIRES_AT, DEFAULT_EXPIRES_AT);
        JSONArray optJSONArray = jSONObject.optJSONArray(CATEGORIES);
        if (optJSONArray == null || optJSONArray.length() == 0) {
            this.f959i = EnumSet.of(CardCategory.NO_CATEGORY);
            return;
        }
        this.f959i = EnumSet.noneOf(CardCategory.class);
        for (int i = 0; i < optJSONArray.length(); i++) {
            CardCategory cardCategory = CardCategory.get(optJSONArray.getString(i));
            if (cardCategory != null) {
                this.f959i.add(cardCategory);
            }
        }
    }

    public boolean logImpression() {
        try {
            if (!(this.f960k == null || this.f961l == null || !m654a())) {
                this.f960k.m57a(da.m168c(this.f953c));
                this.f961l.m277a(this.f953c);
                return true;
            }
        } catch (Throwable e) {
            AppboyLogger.m671w(f950j, "Failed to log feed card impression.", e);
        }
        return false;
    }

    public boolean logClick() {
        try {
            if (this.f960k != null && m654a()) {
                this.f960k.m57a(da.m170d(this.f953c));
                return true;
            }
        } catch (Throwable e) {
            AppboyLogger.m671w(f950j, "Failed to log feed card clicked.", e);
        }
        return false;
    }

    public boolean isEqualToCard(Card card) {
        return this.f953c.equals(card.getId()) && this.f957g == card.getUpdated() && this.f960k == card.f960k;
    }

    public String getId() {
        return this.f953c;
    }

    public Map<String, String> getExtras() {
        return this.f952b;
    }

    public boolean getViewed() {
        return this.f954d;
    }

    public void setViewed(boolean z) {
        this.f954d = z;
    }

    public boolean isRead() {
        return this.f955e;
    }

    public void setIsRead(boolean z) {
        this.f955e = z;
        setChanged();
        notifyObservers();
        if (z) {
            try {
                eu euVar = this.f961l;
                String str = this.f953c;
                if (!euVar.f415d.contains(str)) {
                    euVar.f415d.add(str);
                    euVar.m278a(euVar.f415d, ev.READ_CARDS);
                }
            } catch (Exception e) {
                String str2 = f950j;
            }
        }
    }

    public long getCreated() {
        return this.f956f;
    }

    public long getUpdated() {
        return this.f957g;
    }

    public long getExpiresAt() {
        return this.f958h;
    }

    public boolean isExpired() {
        return getExpiresAt() != DEFAULT_EXPIRES_AT && getExpiresAt() <= fd.m330a();
    }

    public EnumSet<CardCategory> getCategories() {
        return this.f959i;
    }

    public boolean isInCategorySet(EnumSet<CardCategory> enumSet) {
        Iterator it = enumSet.iterator();
        while (it.hasNext()) {
            if (this.f959i.contains((CardCategory) it.next())) {
                return true;
            }
        }
        return false;
    }

    private boolean m654a() {
        if (!fj.m354c(this.f953c)) {
            return true;
        }
        AppboyLogger.m664e(f950j, "Card ID cannot be null");
        return false;
    }

    public JSONObject forJsonPut() {
        return this.f951a;
    }
}
