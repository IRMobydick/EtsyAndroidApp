package com.etsy.android.uikit.view.shop.policies;

import android.annotation.TargetApi;
import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.uikit.util.EtsyLinkify;

public class StructuredShopDownloadsView extends StructuredShopPoliciesView {
    public StructuredShopDownloadsView(Context context) {
        super(context);
    }

    public StructuredShopDownloadsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public StructuredShopDownloadsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public StructuredShopDownloadsView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    protected void init(Context context, LinearLayout linearLayout) {
        inflate(context, R.view_structured_shop_downloads, linearLayout);
        TextView textView = (TextView) findViewById(R.txt_downloads_info);
        String b = EtsyConfig.m837a().m869d().m883b(EtsyConfigKeys.f1255a);
        textView.setText(EtsyLinkify.m5481a(context, Html.fromHtml(getResources().getString(R.structured_shipping_digital_message, new Object[]{b})), true, true, null));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
