package com.etsy.android.lib.models.view.shop;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import com.etsy.android.iconsy.views.IconDrawable;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.ShopAbout.Link;
import com.etsy.android.lib.models.ShopAbout.Link.LinkType;

public class ShopHomeRelatedLinkViewModel {
    private final Link mLink;
    private final String mLinkDisplayTitle;
    private final Drawable mLinkDrawable;
    private final boolean mShowDivider;

    public ShopHomeRelatedLinkViewModel(@NonNull Link link, boolean z, @NonNull Resources resources) {
        this.mShowDivider = z;
        this.mLinkDrawable = IconDrawable.m775a(resources).m781b(R.text_grey).m782c(R.text_large).m780a(link.getTypeIcon()).m777a();
        this.mLink = link;
        this.mLinkDisplayTitle = resources.getString(LinkType.displayTitleResFromFieldName(link.getTitle()));
    }

    public Link getLink() {
        return this.mLink;
    }

    public String getDisplayTitle() {
        return this.mLinkDisplayTitle;
    }

    public Drawable getLinkDrawable() {
        return this.mLinkDrawable;
    }

    public boolean shouldShowDivider() {
        return this.mShowDivider;
    }
}
