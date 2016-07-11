package bo.app;

import com.appboy.Constants;
import com.appboy.models.cards.BannerImageCard;
import com.appboy.models.cards.CaptionedImageCard;
import com.appboy.models.cards.Card;
import com.appboy.models.cards.CrossPromotionSmallCard;
import com.appboy.models.cards.ShortNewsCard;
import com.appboy.models.cards.TextAnnouncementCard;
import com.etsy.android.lib.models.finds.FindsModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class cs {
    private static final String f253a;

    static {
        f253a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, cs.class.getName()});
    }

    public static <T> List<T> m129a(JSONArray jSONArray, Class<T> cls, bv bvVar, eu euVar) {
        List<T> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            String optString;
            try {
                Object a;
                optString = jSONArray.optString(i);
                if (cls.equals(String.class)) {
                    a = fc.m329a(optString, cls);
                } else if (cls.equals(Card.class)) {
                    JSONObject jSONObject = new JSONObject(optString);
                    optString = jSONObject.getString(FindsModule.FIELD_TYPE);
                    if ("banner_image".equals(optString)) {
                        a = new BannerImageCard(jSONObject, bvVar, euVar);
                    } else if ("captioned_image".equals(optString)) {
                        a = new CaptionedImageCard(jSONObject, bvVar, euVar);
                    } else if ("cross_promotion_small".equals(optString)) {
                        a = new CrossPromotionSmallCard(jSONObject, bvVar, euVar);
                    } else if ("short_news".equals(optString)) {
                        a = new ShortNewsCard(jSONObject, bvVar, euVar);
                    } else if ("text_announcement".equals(optString)) {
                        a = new TextAnnouncementCard(jSONObject, bvVar, euVar);
                    } else {
                        throw new JSONException(String.format("Failed to construct java object of type %s from JSON [%s]", new Object[]{optString, jSONObject.toString()}));
                    }
                    a = fc.m329a(a, cls);
                } else {
                    throw new JSONException(String.format("Failed to construct java object %s, target class %s isn'tString nor Card. Please update the createObject in ModelFactory to handle extra class type.", new Object[]{optString, cls.toString()}));
                }
                if (a != null) {
                    arrayList.add(a);
                }
            } catch (JSONException e) {
                optString = f253a;
                String.format("Unable to cast JSON to [%s] in array. Ignoring.", new Object[]{cls.getName()});
            }
        }
        return arrayList;
    }
}
