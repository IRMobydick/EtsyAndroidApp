package com.etsy.android.lib.models.shopedit.contentrow;

import android.content.Context;
import android.support.annotation.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class ShopEditContentRow implements IShopEditContentRow {
    @NonNull
    CharSequence mContent;
    int mContentMaxLines;
    @NonNull
    CharSequence mHint;
    boolean mIncludeBottomExtraPadding;
    @NonNull
    CharSequence mTitle;
    int mViewType;

    public class Builder {
        CharSequence mContent;
        int mContentMaxLines;
        CharSequence mHint;
        boolean mIncludeBottomExtraPadding;
        CharSequence mTitle;
        int mViewType;

        public Builder(int i) {
            this.mTitle = StringUtils.EMPTY;
            this.mHint = StringUtils.EMPTY;
            this.mContent = StringUtils.EMPTY;
            this.mContentMaxLines = 2;
            this.mIncludeBottomExtraPadding = true;
            this.mViewType = i;
        }

        public Builder title(@NonNull CharSequence charSequence) {
            this.mTitle = charSequence;
            return this;
        }

        public Builder content(@NonNull CharSequence charSequence) {
            this.mContent = charSequence;
            return this;
        }

        public Builder hint(@NonNull CharSequence charSequence) {
            this.mHint = charSequence;
            return this;
        }

        public Builder contentMaxLines(int i) {
            this.mContentMaxLines = i;
            return this;
        }

        public Builder includeBottomPadding(boolean z) {
            this.mIncludeBottomExtraPadding = z;
            return this;
        }

        public ShopEditContentRow build() {
            return new ShopEditContentRow();
        }
    }

    ShopEditContentRow() {
        this.mIncludeBottomExtraPadding = true;
    }

    private ShopEditContentRow(@NonNull Builder builder) {
        this.mIncludeBottomExtraPadding = true;
        this.mContent = builder.mContent;
        this.mHint = builder.mHint;
        this.mTitle = builder.mTitle;
        this.mViewType = builder.mViewType;
        this.mContentMaxLines = builder.mContentMaxLines;
        this.mIncludeBottomExtraPadding = builder.mIncludeBottomExtraPadding;
    }

    @NonNull
    public CharSequence getContent() {
        return this.mContent;
    }

    @NonNull
    public CharSequence getHint() {
        return this.mHint;
    }

    @NonNull
    public CharSequence getTitle() {
        return this.mTitle;
    }

    public void setContent(@NonNull CharSequence charSequence) {
        this.mContent = charSequence;
    }

    public int getContentMaxLines() {
        return this.mContentMaxLines;
    }

    public boolean shouldIncludeBottomExtraPadding() {
        return this.mIncludeBottomExtraPadding;
    }

    public int getViewType() {
        return this.mViewType;
    }

    public void restoreComplexStateIfNecessary(@NonNull Context context) {
    }
}
