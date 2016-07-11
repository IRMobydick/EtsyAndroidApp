package com.etsy.android.lib.models;

import com.etsy.android.lib.models.datatypes.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.text.Collator;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

public class Category extends BaseModel implements CategoryOrTaxonomyNode, Comparable<Category> {
    public static final String SUPPLIES_NAME = "supplies";
    public static final String VINTAGE_NAME = "vintage";
    private static final long serialVersionUID = -2588123803228986579L;
    private EtsyId mCategoryId;
    private String mCategoryName;
    private String mLongName;
    private int mNumChildren;
    private String mShortName;

    public Category() {
        this.mCategoryName = StringUtils.EMPTY;
        this.mShortName = StringUtils.EMPTY;
        this.mLongName = StringUtils.EMPTY;
        this.mCategoryId = new EtsyId();
    }

    public Category(String str, String str2, String str3, String str4, int i) {
        this.mCategoryName = StringUtils.EMPTY;
        this.mShortName = StringUtils.EMPTY;
        this.mLongName = StringUtils.EMPTY;
        this.mCategoryId = new EtsyId(str);
        this.mCategoryName = str2;
        this.mShortName = str3;
        this.mLongName = str4;
        this.mNumChildren = i;
    }

    public Category(String str, String str2) {
        this.mCategoryName = StringUtils.EMPTY;
        this.mShortName = StringUtils.EMPTY;
        this.mLongName = StringUtils.EMPTY;
        this.mCategoryId = new EtsyId(str);
        this.mLongName = str2;
    }

    public EtsyId getCategoryId() {
        return this.mCategoryId;
    }

    public String getCategoryName() {
        return this.mCategoryName;
    }

    public String getShortName() {
        return this.mShortName;
    }

    public String getLongName() {
        return this.mLongName;
    }

    public int getNumChildren() {
        return this.mNumChildren;
    }

    public int compareTo(Category category) {
        return Collator.getInstance(Locale.getDefault()).compare(this.mShortName, category.mShortName);
    }

    public boolean hasChildren() {
        return this.mNumChildren > 0;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if ("num_children".equals(currentName)) {
                    this.mNumChildren = jsonParser.getValueAsInt();
                } else if ("short_name".equals(currentName)) {
                    this.mShortName = BaseModel.parseString(jsonParser);
                } else if ("category_name".equals(currentName)) {
                    this.mCategoryName = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.CATEGORY_ID.equals(currentName)) {
                    this.mCategoryId.setId(BaseModel.parseString(jsonParser));
                } else if ("long_name".equals(currentName)) {
                    this.mLongName = BaseModel.parseString(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public Category asCategory() {
        return this;
    }

    public TaxonomyNode asTaxononmyNode() {
        return null;
    }
}
