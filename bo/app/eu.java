package bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import com.appboy.Constants;
import com.appboy.events.FeedUpdatedEvent;
import com.appboy.models.cards.Card;
import com.etsy.android.lib.models.ResponseConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class eu {
    public static final String f412a;
    public final SharedPreferences f413b;
    public final Set<String> f414c;
    public final Set<String> f415d;
    public bv f416e;

    static {
        f412a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, eu.class.getName()});
    }

    public eu(Context context, String str) {
        this.f413b = context.getSharedPreferences("com.appboy.storage.feedstorageprovider" + (str == null ? StringUtils.EMPTY : "." + str), 0);
        this.f414c = m273a(ev.VIEWED_CARDS);
        this.f415d = m273a(ev.READ_CARDS);
        Editor edit = this.f413b.edit();
        edit.putString("uid", str);
        edit.apply();
    }

    public final void m277a(String str) {
        if (!this.f414c.contains(str)) {
            this.f414c.add(str);
            m278a(this.f414c, ev.VIEWED_CARDS);
        }
    }

    public final FeedUpdatedEvent m276a(JSONArray jSONArray, String str, boolean z, long j) {
        List arrayList;
        if (jSONArray == null || jSONArray.length() == 0) {
            arrayList = new ArrayList();
        } else {
            arrayList = cs.m129a(jSONArray, Card.class, this.f416e, this);
        }
        for (Card card : r1) {
            if (this.f414c.contains(card.getId())) {
                card.setViewed(true);
                card.setIsRead(true);
            }
            if (this.f415d.contains(card.getId())) {
                card.setIsRead(true);
            }
        }
        return new FeedUpdatedEvent(r1, str, z, j);
    }

    private Set<String> m273a(ev evVar) {
        String str = evVar.f420c;
        String str2 = evVar.f421d;
        if (VERSION.SDK_INT < 11) {
            return m275b(this.f413b.getString(str2, null));
        }
        if (!this.f413b.contains(str2)) {
            return this.f413b.getStringSet(str, new HashSet());
        }
        Set<String> b = m275b(this.f413b.getString(str2, null));
        Editor edit = this.f413b.edit();
        edit.remove(str2);
        edit.apply();
        m278a(b, evVar);
        return b;
    }

    public final void m278a(Set<String> set, ev evVar) {
        String str = evVar.f420c;
        String str2 = evVar.f421d;
        Editor edit = this.f413b.edit();
        if (VERSION.SDK_INT < 11) {
            if (set == null || set.isEmpty()) {
                edit.remove(str2);
            } else {
                edit.putString(str2, fj.m350a((Collection) set, ";"));
            }
        } else if (set == null || set.isEmpty()) {
            edit.remove(str);
        } else {
            edit.putStringSet(str, set);
        }
        edit.apply();
    }

    private static Set<String> m275b(String str) {
        Object hashSet = new HashSet();
        if (str != null) {
            Collections.addAll(hashSet, str.split(";"));
        }
        return hashSet;
    }

    public static Set<String> m274a(JSONArray jSONArray) {
        Set<String> hashSet = new HashSet();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject.has(ResponseConstants.ID)) {
                    hashSet.add(jSONObject.getString(ResponseConstants.ID));
                }
            }
        }
        return hashSet;
    }
}
