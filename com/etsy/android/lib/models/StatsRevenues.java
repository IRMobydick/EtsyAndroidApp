package com.etsy.android.lib.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class StatsRevenues extends BaseModel {
    protected static final long serialVersionUID = 5672810363042332100L;
    protected String mCode;
    protected double mCount;
    protected List<RevenueItem> mRevenueItems;

    /* renamed from: com.etsy.android.lib.models.StatsRevenues.1 */
    class C04781 implements Comparator<RevenueItem> {
        final /* synthetic */ StatsRevenues f1901a;

        C04781(StatsRevenues statsRevenues) {
            this.f1901a = statsRevenues;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m2327a((RevenueItem) obj, (RevenueItem) obj2);
        }

        public int m2327a(RevenueItem revenueItem, RevenueItem revenueItem2) {
            if (revenueItem == null && revenueItem2 == null) {
                return 0;
            }
            if (revenueItem == null) {
                return 1;
            }
            if (revenueItem2 == null || revenueItem2.getTimestamp() < revenueItem.getTimestamp()) {
                return -1;
            }
            return revenueItem2.getTimestamp() == revenueItem.getTimestamp() ? 0 : 1;
        }
    }

    public StatsRevenues() {
        this.mCode = StringUtils.EMPTY;
        this.mRevenueItems = new ArrayList(0);
    }

    public String getCode() {
        return this.mCode;
    }

    public double getCount() {
        return this.mCount;
    }

    public List<RevenueItem> getRevenueItems() {
        return this.mRevenueItems;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.COUNT.equals(currentName)) {
                    this.mCount = jsonParser.getValueAsDouble();
                } else if (ResponseConstants.CODE.equals(currentName)) {
                    this.mCode = BaseModel.parseString(jsonParser);
                } else if ("revenue_items".equals(currentName)) {
                    this.mRevenueItems = BaseModel.parseArray(jsonParser, RevenueItem.class);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
        List arrayList = new ArrayList(0);
        for (RevenueItem revenueItem : this.mRevenueItems) {
            if (revenueItem.getCount() > 0.0d) {
                revenueItem.setCurrencyCode(this.mCode);
                arrayList.add(revenueItem);
            }
        }
        Collections.sort(arrayList, new C04781(this));
        this.mRevenueItems = arrayList;
    }
}
