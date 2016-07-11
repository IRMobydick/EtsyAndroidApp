package com.etsy.android.lib.models.datatypes;

import android.text.TextUtils;
import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class EtsyId implements Serializable {
    protected String mId;

    public EtsyId() {
        this.mId = StringUtils.EMPTY;
    }

    public EtsyId(long j) {
        this.mId = StringUtils.EMPTY;
        this.mId = String.valueOf(j);
    }

    public EtsyId(String str) {
        this.mId = StringUtils.EMPTY;
        setId(str);
    }

    public void setId(String str) {
        if (str == null) {
            this.mId = StringUtils.EMPTY;
        } else {
            this.mId = str;
        }
    }

    public long getIdAsLong() {
        try {
            return Long.parseLong(this.mId);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public boolean isNumeric() {
        return getIdAsLong() != 0;
    }

    public String getId() {
        return this.mId;
    }

    public boolean hasId() {
        return !TextUtils.isEmpty(this.mId);
    }

    public String toString() {
        return this.mId;
    }

    public int hashCode() {
        return this.mId.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof EtsyId) {
            return ((EtsyId) obj).getId().equalsIgnoreCase(getId());
        }
        return super.equals(obj);
    }
}
