package com.etsy.android.lib.models.homescreen;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.cardviewelement.PageLink;
import com.etsy.android.lib.util.Closure;
import com.fasterxml.jackson.core.JsonParser;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class LandingPageLink extends PageLink implements LandingPageInfo {
    public static final String FUND_ON_ETSY = "fund_on_etsy";
    public static final int LAYOUT_GRID = 2;
    public static final int LAYOUT_LINEAR = 1;
    public static final int LAYOUT_STAGGERED = 0;
    public static final String PAGE_TYPE_LISTINGS = "listings";
    public static final String PAGE_TYPE_RECENTLY_VIEWED_LISTINGS = "recently_viewed_listings";
    public static final String PAGE_TYPE_SEGMENTS = "segments";
    public static final String PAGE_TYPE_SHOPS = "shops";
    public static final String PAGE_TYPE_SHOP_SHARES = "shopShareCard";
    String apiPath;
    int layout;
    Map<String, String> options;
    String pageType;
    HashMap<String, String> params;
    int requestMethod;

    /* renamed from: com.etsy.android.lib.models.homescreen.LandingPageLink.1 */
    class C04841 extends Closure<JsonParser, String> {
        final /* synthetic */ LandingPageLink f1902a;

        C04841(LandingPageLink landingPageLink) {
            this.f1902a = landingPageLink;
        }

        public String m2777a(JsonParser jsonParser) {
            try {
                return jsonParser.getCurrentName();
            } catch (Exception e) {
                return StringUtils.EMPTY;
            }
        }
    }

    /* renamed from: com.etsy.android.lib.models.homescreen.LandingPageLink.2 */
    class C04852 extends Closure<JsonParser, String> {
        final /* synthetic */ LandingPageLink f1903a;

        C04852(LandingPageLink landingPageLink) {
            this.f1903a = landingPageLink;
        }

        public String m2779a(JsonParser jsonParser) {
            try {
                return jsonParser.getValueAsString();
            } catch (Exception e) {
                return StringUtils.EMPTY;
            }
        }
    }

    public LandingPageLink() {
        this.apiPath = null;
        this.pageType = PAGE_TYPE_LISTINGS;
        this.layout = LAYOUT_STAGGERED;
        this.params = new HashMap();
        this.options = new HashMap();
        this.requestMethod = LAYOUT_STAGGERED;
    }

    public LandingPageLink(String str) {
        this.apiPath = null;
        this.pageType = PAGE_TYPE_LISTINGS;
        this.layout = LAYOUT_STAGGERED;
        this.params = new HashMap();
        this.options = new HashMap();
        this.requestMethod = LAYOUT_STAGGERED;
        this.mPageTitle = str;
    }

    protected boolean parseField(JsonParser jsonParser, String str) {
        if (ResponseConstants.API_PATH.equals(str)) {
            this.apiPath = BaseModel.parseStringURL(jsonParser);
        } else if (ResponseConstants.METHOD.equals(str)) {
            r0 = BaseModel.parseString(jsonParser);
            if (r0.equalsIgnoreCase("post")) {
                this.requestMethod = LAYOUT_LINEAR;
            } else if (r0.equalsIgnoreCase("delete")) {
                this.requestMethod = 3;
            } else if (r0.equalsIgnoreCase("put")) {
                this.requestMethod = LAYOUT_GRID;
            } else if (r0.equalsIgnoreCase("get")) {
                this.requestMethod = LAYOUT_STAGGERED;
            }
        } else if (ResponseConstants.PARAMS.equals(str)) {
            jsonParser.skipChildren();
        } else if (ResponseConstants.LAYOUT.equals(str)) {
            r0 = BaseModel.parseStringURL(jsonParser);
            if (ResponseConstants.STAGGERED.equals(r0)) {
                this.layout = LAYOUT_STAGGERED;
            } else if (ResponseConstants.LINEAR.equals(r0)) {
                this.layout = LAYOUT_LINEAR;
            } else if (ResponseConstants.GRID.equals(r0)) {
                this.layout = LAYOUT_GRID;
            }
        } else if (ResponseConstants.PAGE_TYPE.equals(str)) {
            setPageType(BaseModel.parseString(jsonParser));
        } else if (ResponseConstants.OPTIONS.equals(str)) {
            this.options = BaseModel.parseMap(jsonParser, new C04841(this), new C04852(this));
        }
        return super.parseField(jsonParser, str);
    }

    public void setApiPath(String str) {
        this.apiPath = str;
    }

    public void setPageType(String str) {
        this.pageType = str;
    }

    public void setLayout(int i) {
        this.layout = i;
    }

    public void setOptions(Map<String, String> map) {
        this.options = map;
    }

    public void setOption(String str, String str2) {
        if (this.options != null) {
            this.options.put(str, str2);
        }
    }

    public void setOption(String str, boolean z) {
        setOption(str, Boolean.toString(z));
    }

    public String getAPIPath() {
        return this.apiPath;
    }

    public String getPageType() {
        return this.pageType;
    }

    public int getLayout() {
        return this.layout;
    }

    public HashMap<String, String> getParams() {
        return this.params;
    }

    public Map<String, String> getOptions() {
        return this.options;
    }

    public boolean getBooleanOption(String str) {
        try {
            return Boolean.parseBoolean((String) getOptions().get(str));
        } catch (Exception e) {
            return false;
        }
    }

    public int getRequestMethod() {
        return this.requestMethod;
    }

    public void setRequestMethod(int i) {
        this.requestMethod = i;
    }
}
