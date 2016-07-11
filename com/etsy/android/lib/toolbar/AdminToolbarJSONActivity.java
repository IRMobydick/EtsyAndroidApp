package com.etsy.android.lib.toolbar;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.util.Linkify;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.etsy.android.lib.R;

public class AdminToolbarJSONActivity extends Activity {
    public static final String EXTRA_RESPONSE = "extra_response";

    /* renamed from: com.etsy.android.lib.toolbar.AdminToolbarJSONActivity.1 */
    class C05021 implements OnClickListener {
        final /* synthetic */ String f1933a;
        final /* synthetic */ AdminToolbarJSONActivity f1934b;

        C05021(AdminToolbarJSONActivity adminToolbarJSONActivity, String str) {
            this.f1934b = adminToolbarJSONActivity;
            this.f1933a = str;
        }

        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.SEND");
            intent.putExtra("android.intent.extra.TEXT", this.f1933a);
            intent.setType("message/rfc822");
            this.f1934b.startActivity(intent);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.activity_admin_toolbar_json);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Json Viewer");
            actionBar.setDisplayShowTitleEnabled(true);
        }
        AdminToolbarNetworkResponse adminToolbarNetworkResponse = (AdminToolbarNetworkResponse) getIntent().getSerializableExtra(EXTRA_RESPONSE);
        TextView textView = (TextView) findViewById(R.url);
        textView.setText("URL: " + adminToolbarNetworkResponse.getUrl());
        Linkify.addLinks(textView, 15);
        textView = (TextView) findViewById(R.headers);
        textView.setText(Html.fromHtml(adminToolbarNetworkResponse.getHeadersString()));
        String prettyJsonString = adminToolbarNetworkResponse.getPrettyJsonString();
        TextView textView2 = (TextView) findViewById(R.json);
        ((TextView) findViewById(R.status_code)).setText("Status code: " + adminToolbarNetworkResponse.getStatusCode());
        textView2.setText("Body:\n" + prettyJsonString);
        findViewById(R.send).setOnClickListener(new C05021(this, textView.getText() + "\n\n" + textView2.getText()));
    }
}
