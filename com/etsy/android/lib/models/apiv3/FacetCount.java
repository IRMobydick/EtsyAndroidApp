package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class FacetCount extends BaseModel {
    public static final Comparator<FacetCount> byDecreasingCount;
    protected List<FacetCount> mChildren;
    protected int mCount;
    protected String mId;
    protected String mName;

    /* renamed from: com.etsy.android.lib.models.apiv3.FacetCount.1 */
    final class C04791 implements Comparator<FacetCount> {
        C04791() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m2362a((FacetCount) obj, (FacetCount) obj2);
        }

        public int m2362a(FacetCount facetCount, FacetCount facetCount2) {
            return facetCount2.getCount() - facetCount.getCount();
        }
    }

    static {
        byDecreasingCount = new C04791();
    }

    public String getId() {
        return this.mId;
    }

    public String getName() {
        return this.mName;
    }

    public int getCount() {
        return this.mCount;
    }

    public List<FacetCount> getChildren() {
        return this.mChildren;
    }

    public FacetCount(String str, String str2, int i) {
        this.mId = StringUtils.EMPTY;
        this.mName = StringUtils.EMPTY;
        this.mId = str;
        this.mName = str2;
        this.mCount = i;
    }

    public FacetCount(String str, String str2, int i, List<FacetCount> list) {
        this.mId = StringUtils.EMPTY;
        this.mName = StringUtils.EMPTY;
        this.mId = str;
        this.mName = str2;
        this.mCount = i;
        this.mChildren = list;
    }

    public FacetCount() {
        this.mId = StringUtils.EMPTY;
        this.mName = StringUtils.EMPTY;
    }

    private List<FacetCount> parseChildren(JsonParser jsonParser) {
        if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
            return BaseModel.parseArray(jsonParser, FacetCount.class);
        }
        if (jsonParser.getCurrentToken() != JsonToken.START_OBJECT) {
            return null;
        }
        List<FacetCount> arrayList = new ArrayList();
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                arrayList.add(BaseModel.parseObject(jsonParser, FacetCount.class));
            }
        }
        return arrayList;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.ID.equals(currentName)) {
                    this.mId = BaseModel.parseStringIdOrNumericValue(jsonParser);
                } else if (ResponseConstants.NAME.equals(currentName)) {
                    this.mName = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.COUNT.equals(currentName)) {
                    this.mCount = jsonParser.getValueAsInt();
                } else if (ResponseConstants.CHILDREN.equals(currentName)) {
                    this.mChildren = parseChildren(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public int hashCode() {
        return getId().hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof FacetCount)) {
            return false;
        }
        FacetCount facetCount = (FacetCount) obj;
        if (getId() != null || facetCount.getId() == null) {
            return getId().equals(facetCount.getId());
        }
        return false;
    }

    public String toString() {
        return getId() + " " + getName() + " " + getCount();
    }

    public static FacetCount findFacetForTaxonomyNode(List<FacetCount> list, String str) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            FacetCount facetCount = (FacetCount) list.get(i);
            if (facetCount.getId().equals(str)) {
                return facetCount;
            }
            facetCount = findFacetForTaxonomyNode(facetCount.getChildren(), str);
            if (facetCount != null) {
                return facetCount;
            }
        }
        return null;
    }

    public static boolean buildPathToFacet(List<FacetCount> list, String str, List<FacetCount> list2) {
        int size = list.size();
        int i = 0;
        while (i < size) {
            FacetCount facetCount = (FacetCount) list.get(i);
            if (facetCount.getId().equals(str)) {
                list2.add(facetCount);
                return true;
            } else if (buildPathToFacet(facetCount.getChildren(), str, list2)) {
                list2.add(0, facetCount);
                return true;
            } else {
                i++;
            }
        }
        return false;
    }
}
