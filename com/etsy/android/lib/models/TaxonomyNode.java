package com.etsy.android.lib.models;

import android.support.annotation.Nullable;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.editable.EditableListing;
import com.etsy.android.lib.util.bh;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class TaxonomyNode extends BaseModel implements CategoryOrTaxonomyNode {
    private static final String TAG;
    protected List<TaxonomyNode> mChildren;
    protected boolean mIsSuppliesTopLevel;
    protected String mLongName;
    protected String mName;
    protected TaxonomyNode mParent;
    protected String mParentPath;
    protected String mPath;
    protected EtsyId mTaxonomyNodeId;

    static {
        TAG = EtsyDebug.m1891a(TaxonomyNode.class);
    }

    public TaxonomyNode() {
        this.mTaxonomyNodeId = new EtsyId();
        this.mName = StringUtils.EMPTY;
        this.mLongName = StringUtils.EMPTY;
        this.mPath = StringUtils.EMPTY;
        this.mParentPath = StringUtils.EMPTY;
    }

    public TaxonomyNode(long j, List<String> list) {
        this(j, StringUtils.join((Iterable) list, EditableListing.CATEGORY_PATH_JOIN_STRING));
    }

    public TaxonomyNode(long j, String str) {
        this(String.valueOf(j), str);
    }

    public TaxonomyNode(String str, List<String> list) {
        this(str, StringUtils.join((Iterable) list, EditableListing.CATEGORY_PATH_JOIN_STRING));
    }

    public TaxonomyNode(String str, List<String> list, List<Integer> list2) {
        boolean z;
        this(str, (List) list);
        if (((Integer) list2.get(0)).intValue() == EtsyConfig.m837a().m869d().m886d(EtsyConfigKeys.cq)) {
            z = true;
        } else {
            z = false;
        }
        this.mIsSuppliesTopLevel = z;
    }

    public TaxonomyNode(String str, String str2) {
        this.mTaxonomyNodeId = new EtsyId();
        this.mName = StringUtils.EMPTY;
        this.mLongName = StringUtils.EMPTY;
        this.mPath = StringUtils.EMPTY;
        this.mParentPath = StringUtils.EMPTY;
        this.mTaxonomyNodeId.setId(str);
        this.mLongName = str2;
    }

    public TaxonomyNode(String str, String str2, boolean z) {
        this.mTaxonomyNodeId = new EtsyId();
        this.mName = StringUtils.EMPTY;
        this.mLongName = StringUtils.EMPTY;
        this.mPath = StringUtils.EMPTY;
        this.mParentPath = StringUtils.EMPTY;
        this.mTaxonomyNodeId.setId(str);
        this.mLongName = str2;
        this.mIsSuppliesTopLevel = z;
    }

    public TaxonomyNode(String str, String str2, String str3) {
        this.mTaxonomyNodeId = new EtsyId();
        this.mName = StringUtils.EMPTY;
        this.mLongName = StringUtils.EMPTY;
        this.mPath = StringUtils.EMPTY;
        this.mParentPath = StringUtils.EMPTY;
        this.mTaxonomyNodeId.setId(str);
        this.mLongName = str3;
        this.mName = str2;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.ID.equals(currentName)) {
                    this.mTaxonomyNodeId.setId(String.valueOf(jsonParser.getValueAsLong()));
                } else if (ResponseConstants.NAME.equals(currentName)) {
                    this.mName = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.PATH.equals(currentName)) {
                    this.mPath = BaseModel.parseStringURL(jsonParser);
                } else if (ResponseConstants.CHILDREN.equals(currentName)) {
                    this.mChildren = BaseModel.parseArray(jsonParser, TaxonomyNode.class);
                } else if (ResponseConstants.PARENT.equals(currentName)) {
                    this.mParentPath = BaseModel.parseStringURL(jsonParser);
                } else if (ResponseConstants.IS_SUPPLIES_TOP_LEVEL.equals(currentName)) {
                    this.mIsSuppliesTopLevel = jsonParser.getValueAsBoolean();
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public boolean hasChildren() {
        return !this.mChildren.isEmpty();
    }

    public EtsyId getTaxonomyNodeId() {
        return this.mTaxonomyNodeId;
    }

    public String getName() {
        return this.mName;
    }

    public String getPath() {
        return this.mPath;
    }

    public List<TaxonomyNode> getChildren() {
        return this.mChildren;
    }

    public TaxonomyNode asTaxononmyNode() {
        return this;
    }

    public Category asCategory() {
        return null;
    }

    public String getLongName() {
        if (bh.m3343b(this.mLongName)) {
            return this.mLongName;
        }
        if (!bh.m3343b(this.mName)) {
            return StringUtils.EMPTY;
        }
        StringBuilder stringBuilder = new StringBuilder(this.mName);
        for (TaxonomyNode taxonomyNode = this.mParent; taxonomyNode != null; taxonomyNode = taxonomyNode.mParent) {
            stringBuilder.insert(0, EditableListing.CATEGORY_PATH_JOIN_STRING);
            stringBuilder.insert(0, taxonomyNode.mName);
        }
        return stringBuilder.toString();
    }

    private void updateParent(Map<String, TaxonomyNode> map) {
        this.mParent = (TaxonomyNode) map.get(this.mParentPath);
    }

    private static void buildPathToNodeIndex(Map<String, TaxonomyNode> map, TaxonomyNode taxonomyNode) {
        map.put(taxonomyNode.getPath(), taxonomyNode);
        for (TaxonomyNode buildPathToNodeIndex : taxonomyNode.getChildren()) {
            buildPathToNodeIndex(map, buildPathToNodeIndex);
        }
    }

    public static void updateAllParentReferences(List<TaxonomyNode> list) {
        Map hashMap = new HashMap();
        for (TaxonomyNode buildPathToNodeIndex : list) {
            buildPathToNodeIndex(hashMap, buildPathToNodeIndex);
        }
        for (TaxonomyNode buildPathToNodeIndex2 : hashMap.values()) {
            buildPathToNodeIndex2.updateParent(hashMap);
        }
    }

    public boolean isSupplies() {
        while (this != null) {
            if (this.mIsSuppliesTopLevel) {
                return true;
            }
            this = this.mParent;
        }
        return false;
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.TAXONOMY_NODE_ID, this.mTaxonomyNodeId.getId());
        return hashMap;
    }

    public int getViewType() {
        return 7;
    }

    public static String pathFromWebUrlToAPIFormat(String str) {
        return str.replace("-", "_");
    }
}
