package com.etsy.android.lib.models.cardviewelement;

import android.support.annotation.NonNull;
import com.etsy.android.lib.models.BaseFieldModel;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.uikit.cardview.ICardViewElement;
import com.fasterxml.jackson.core.JsonParser;
import java.io.Serializable;
import org.parceler.Parcel;

@Parcel
public class BasicSectionHeader extends BaseFieldModel implements ICardViewElement, Serializable {
    private final String TYPE_CATEGORY;
    private final String TYPE_SEARCH;
    protected int mViewType;
    protected String subtitle;
    protected String title;

    public BasicSectionHeader() {
        this.title = null;
        this.subtitle = null;
        this.mViewType = 2;
        this.TYPE_SEARCH = "search";
        this.TYPE_CATEGORY = "category";
    }

    public BasicSectionHeader(String str, String str2) {
        this.title = null;
        this.subtitle = null;
        this.mViewType = 2;
        this.TYPE_SEARCH = "search";
        this.TYPE_CATEGORY = "category";
        this.title = str;
        this.subtitle = str2;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setSubtitle(String str) {
        this.subtitle = str;
    }

    protected boolean parseField(JsonParser jsonParser, @NonNull String str) {
        if (FindsModule.FIELD_TITLE.equals(str)) {
            setTitle(BaseModel.parseString(jsonParser));
        } else if (ResponseConstants.SUB_TITLE.equals(str)) {
            setSubtitle(BaseModel.parseString(jsonParser));
        } else if (!ResponseConstants.VIEW_TYPE.equals(str)) {
            return false;
        } else {
            String parseString = BaseModel.parseString(jsonParser);
            if ("category".equals(parseString)) {
                this.mViewType = 19;
            } else if ("search".equals(parseString)) {
                this.mViewType = 13;
            }
        }
        return true;
    }

    public void setViewType(int i) {
        this.mViewType = i;
    }

    public int getViewType() {
        return this.mViewType;
    }
}
