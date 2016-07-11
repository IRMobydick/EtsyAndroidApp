package com.etsy.android.lib.models.datatypes;

import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class EtsyDeepLinkId extends EtsyId {
    protected String mName;

    public EtsyDeepLinkId(String str) {
        super(str);
        this.mName = StringUtils.EMPTY;
    }

    public EtsyDeepLinkId() {
        this.mName = StringUtils.EMPTY;
    }

    public EtsyDeepLinkId(long j) {
        super(j);
        this.mName = StringUtils.EMPTY;
    }

    public void setName(String str) {
        if (str != null) {
            this.mName = str;
        }
    }

    public void checkIdTypeAndSet(String str) {
        if (str != null) {
            try {
                Long.parseLong(str);
                this.mId = str;
            } catch (NumberFormatException e) {
                this.mName = str;
            }
        }
    }

    public boolean hasId() {
        return super.hasId() || !this.mName.isEmpty();
    }

    public String getId() {
        if (super.hasId()) {
            return super.getId();
        }
        return this.mName;
    }

    public String getName() {
        return this.mName;
    }

    public String toString() {
        return getId();
    }

    public int hashCode() {
        return getId().hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof EtsyDeepLinkId) {
            return ((EtsyDeepLinkId) obj).getId().equalsIgnoreCase(getId());
        }
        return super.equals(obj);
    }
}
