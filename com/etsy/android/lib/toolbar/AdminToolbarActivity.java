package com.etsy.android.lib.toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils.TruncateAt;
import android.text.style.ForegroundColorSpan;
import android.text.util.Linkify;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.util.bh;
import com.etsy.android.uikit.BaseActivity;
import java.util.Iterator;

public class AdminToolbarActivity extends BaseActivity {
    private static final String ANALYTICS_TYPE_EVENT = "Event";
    private static final String ANALYTICS_TYPE_PAGEVIEW = "PageView";
    private int mGreenColor;
    private int mMargin;
    private int mRedColor;

    /* renamed from: com.etsy.android.lib.toolbar.AdminToolbarActivity.1 */
    class C05011 implements OnClickListener {
        final /* synthetic */ AdminToolbarNetworkResponse f1931a;
        final /* synthetic */ AdminToolbarActivity f1932b;

        C05011(AdminToolbarActivity adminToolbarActivity, AdminToolbarNetworkResponse adminToolbarNetworkResponse) {
            this.f1932b = adminToolbarActivity;
            this.f1931a = adminToolbarNetworkResponse;
        }

        public void onClick(View view) {
            Intent intent = new Intent(this.f1932b, AdminToolbarJSONActivity.class);
            intent.putExtra(AdminToolbarJSONActivity.EXTRA_RESPONSE, this.f1931a);
            this.f1932b.startActivity(intent);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.activity_admin_toolbar);
        this.mGreenColor = getResources().getColor(R.green);
        this.mRedColor = getResources().getColor(R.red);
        this.mMargin = getResources().getDimensionPixelOffset(R.margin_small);
        getAppBarHelper().setTitle(AdminToolbar.m2994b());
        ((TextView) findViewById(R.fragment)).setText(AdminToolbar.m2998c());
        ((TextView) findViewById(R.activity)).setText(AdminToolbar.m3000d());
        ((TextView) findViewById(R.analytics_title)).setText(String.format("Last %d Analytics Events:", new Object[]{Integer.valueOf(5)}));
        ((TextView) findViewById(R.responses_title)).setText(String.format("JSON for Last %d Network Responses:", new Object[]{Integer.valueOf(3)}));
        ((TextView) findViewById(R.requests_title)).setText(String.format("Last %d Network Requests:", new Object[]{Integer.valueOf(5)}));
        TextView textView = (TextView) findViewById(R.web_url);
        if (bh.m3340a(AdminToolbar.m3007i())) {
            textView.setVisibility(0);
            textView.setText("Last Web Link:\n" + AdminToolbar.m3007i());
            Linkify.addLinks(textView, 15);
        } else {
            textView.setVisibility(8);
        }
        CharSequence spannableStringBuilder = new SpannableStringBuilder();
        Iterator descendingIterator = AdminToolbar.m3002e().descendingIterator();
        while (descendingIterator.hasNext()) {
            spannableStringBuilder.append(coloredAnalyticsSpan((String) descendingIterator.next()));
            spannableStringBuilder.append("\n");
        }
        ((TextView) findViewById(R.analytics)).setText(spannableStringBuilder);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.responses_layout);
        descendingIterator = AdminToolbar.m3005g().descendingIterator();
        while (descendingIterator.hasNext()) {
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.setMargins(0, this.mMargin, 0, this.mMargin);
            linearLayout.addView(getJSONResponseButton((AdminToolbarNetworkResponse) descendingIterator.next()), layoutParams);
        }
        textView = (TextView) findViewById(R.requests);
        descendingIterator = AdminToolbar.m3006h().descendingIterator();
        while (descendingIterator.hasNext()) {
            textView.append(((String) descendingIterator.next()) + "\n\n");
        }
        Linkify.addLinks(textView, 15);
        textView = (TextView) findViewById(R.ab_tests);
        descendingIterator = AdminToolbar.m3004f().iterator();
        while (descendingIterator.hasNext()) {
            textView.append(((String) descendingIterator.next()) + "\n\n");
        }
    }

    protected void initAdminToolbar() {
    }

    private Spannable coloredAnalyticsSpan(String str) {
        Spannable spannableString = new SpannableString(str);
        int indexOf = str.indexOf(ANALYTICS_TYPE_EVENT);
        if (indexOf >= 0) {
            spannableString.setSpan(new ForegroundColorSpan(this.mGreenColor), indexOf, ANALYTICS_TYPE_EVENT.length() + indexOf, 33);
        }
        indexOf = str.indexOf(ANALYTICS_TYPE_PAGEVIEW);
        if (indexOf >= 0) {
            spannableString.setSpan(new ForegroundColorSpan(this.mRedColor), indexOf, ANALYTICS_TYPE_PAGEVIEW.length() + indexOf, 33);
        }
        return spannableString;
    }

    private Button getJSONResponseButton(AdminToolbarNetworkResponse adminToolbarNetworkResponse) {
        Button button = new Button(this);
        button.setText(adminToolbarNetworkResponse.getUrl());
        button.setTextColor(getResources().getColor(R.blue));
        button.setTextSize(0, (float) getResources().getDimensionPixelSize(R.text_medium));
        button.setMaxLines(3);
        button.setEllipsize(TruncateAt.END);
        button.setBackgroundResource(R.btn_white_selector_v2);
        button.setOnClickListener(new C05011(this, adminToolbarNetworkResponse));
        return button;
    }
}
