package com.etsy.android.lib.convos.contentprovider;

import com.etsy.android.lib.models.Listing;
import org.apache.commons.lang3.StringUtils;

public enum ConvoPaths {
    UNKNOWN(-1, StringUtils.EMPTY),
    CONVO(1, "convo"),
    CONVOS(2, "convo"),
    DRAFT(3, Listing.DRAFT_STATE),
    DRAFTS(4, Listing.DRAFT_STATE),
    CONTACT(5, "contacts"),
    CONTACTS(6, "contacts"),
    ERASE(7, "erase"),
    SNIPPET(8, "snippet"),
    SNIPPETS(9, "snippet");
    
    public final int mMatcher;
    private final String mPath;

    private ConvoPaths(int i, String str) {
        this.mMatcher = i;
        this.mPath = str;
    }

    public int getMatch() {
        return this.mMatcher;
    }

    public String getPath() {
        return this.mPath;
    }

    public static ConvoPaths matchPath(int i) {
        for (ConvoPaths convoPaths : values()) {
            if (i == convoPaths.getMatch()) {
                return convoPaths;
            }
        }
        return UNKNOWN;
    }
}
