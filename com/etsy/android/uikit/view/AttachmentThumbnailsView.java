package com.etsy.android.uikit.view;

import android.text.TextUtils;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.etsy.android.uikit.view.a */
class AttachmentThumbnailsView {
    ClickableImageView f4238a;
    String f4239b;
    AttachmentType f4240c;

    public AttachmentThumbnailsView(ClickableImageView clickableImageView) {
        this.f4238a = clickableImageView;
        this.f4240c = AttachmentType.UNKNOWN;
        this.f4239b = null;
    }

    public void m5654a() {
        this.f4240c = AttachmentType.UNKNOWN;
        this.f4239b = StringUtils.EMPTY;
        this.f4238a.setImageDrawable(null);
        this.f4238a.setVisibility(8);
    }

    public boolean m5655b() {
        return (this.f4240c == AttachmentType.UNKNOWN || TextUtils.isEmpty(this.f4239b)) ? false : true;
    }
}
