package com.etsy.android.ui.core.listingpanel;

import android.support.annotation.NonNull;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.ITrackedObject;
import com.etsy.android.lib.logger.ad;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.util.aa;
import com.etsy.android.lib.util.aj;
import com.etsy.android.lib.util.bh;
import com.etsy.android.uikit.BaseActivity;
import com.etsy.android.uikit.text.typeface.TypefaceCache;
import com.etsy.android.uikit.util.EtsyLinkify;
import com.etsy.android.uikit.util.GraphikUtil;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.MachineTranslationOneClickView;
import java.util.Locale;

/* renamed from: com.etsy.android.ui.core.listingpanel.c */
public class ListingPanelDescription extends ListingPanelBase {
    private ListingPanelDescription f2822k;
    private TextView f2823l;
    private MachineTranslationOneClickView f2824m;
    private Listing f2825n;

    /* renamed from: com.etsy.android.ui.core.listingpanel.c.1 */
    class ListingPanelDescription extends TrackingOnClickListener {
        final /* synthetic */ ListingPanelDescription f2821a;

        ListingPanelDescription(ListingPanelDescription listingPanelDescription, ITrackedObject... iTrackedObjectArr) {
            this.f2821a = listingPanelDescription;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            this.f2821a.f2822k.m3959a(this.f2821a.f2824m);
        }
    }

    public ListingPanelDescription(Listing listing, BaseActivity baseActivity, @NonNull ad adVar) {
        super(listing, baseActivity, adVar);
        m4025a(2131756141, 2131756138, 2131756139, 2131756140);
    }

    protected void m4050c() {
        this.f2823l = (TextView) this.d.findViewById(2131756142);
        this.f2824m = (MachineTranslationOneClickView) this.d.findViewById(R.machine_translation_one_click);
        m4054q();
        m4053p();
    }

    public void m4052o() {
        m4053p();
        LayoutParams layoutParams = this.d.getLayoutParams();
        layoutParams.height = -2;
        this.d.setLayoutParams(layoutParams);
        m4031a(false);
    }

    protected void m4053p() {
        if (aa.m3167a() && !GraphikUtil.m5548b()) {
            this.f2823l.setTypeface(TypefaceCache.m5414a().m5416a("sans-serif-light"));
        }
        if (bh.m3340a(this.f2825n.getDescription())) {
            if (this.j) {
                Spanned fromHtml = Html.fromHtml("<br/><b>" + this.a.getResources().getString(R.description) + ":</b><br/>");
                this.f2823l.setText(TextUtils.concat(new CharSequence[]{fromHtml, this.f2825n.getDescription()}));
            } else {
                this.f2823l.setText(this.f2825n.getDescription());
            }
            EtsyLinkify.m5482a(this.a, this.f2823l);
        } else {
            this.f2823l.setVisibility(8);
        }
        if (this.j) {
            TextView textView = (TextView) this.d.findViewById(2131756156);
            TableLayout tableLayout = (TableLayout) this.d.findViewById(2131756136);
            if (tableLayout != null && textView != null) {
                if (this.b.hasOverview()) {
                    tableLayout.setVisibility(0);
                    textView.setVisibility(0);
                    if (aa.m3167a() && !GraphikUtil.m5548b()) {
                        textView.setTypeface(TypefaceCache.m5414a().m5416a("sans-serif-light"));
                    }
                    textView.setText(Html.fromHtml("<b>" + this.a.getResources().getString(R.overview) + ":</b>"));
                    ListingPanelOverview.m4070a(this.b.getOverview(), tableLayout);
                    return;
                }
                tableLayout.setVisibility(8);
                textView.setVisibility(8);
            }
        }
    }

    public void m4048a(ListingPanelDescription listingPanelDescription) {
        this.f2822k = listingPanelDescription;
    }

    public void m4049b(Listing listing) {
        this.f2825n = listing;
    }

    public void m4051c(Listing listing) {
        this.f2824m.setListingTranslationState(listing.isMachineTranslated(), listing.isMachineTranslated() ? this.b.getOriginalLanguage() : Locale.getDefault().getLanguage());
    }

    protected void m4054q() {
        if (this.f2824m != null) {
            if ((this.b.isMachineTranslatable() || this.b.isMachineTranslated()) && aj.m3239f()) {
                ((TextView) this.f2824m.findViewById(R.translate_button)).setOnClickListener(new ListingPanelDescription(this, this.b));
                m4051c(this.f2825n);
                this.f2824m.setVisibility(0);
                this.f2824m.showDisclaimer();
                this.f2824m.showButtonElements();
                this.f2824m.hideSpinner();
                this.f2824m.hideErrorMessage();
                return;
            }
            this.f2824m.setVisibility(8);
        }
    }
}
