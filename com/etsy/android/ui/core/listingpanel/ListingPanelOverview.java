package com.etsy.android.ui.core.listingpanel;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.ad;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.util.aa;
import com.etsy.android.uikit.BaseActivity;
import com.etsy.android.uikit.text.typeface.TypefaceCache;
import com.etsy.android.uikit.util.GraphikUtil;
import com.etsy.android.uikit.util.TabletSupportUtil;
import com.etsy.android.uikit.view.FullImageView;
import java.util.List;

/* renamed from: com.etsy.android.ui.core.listingpanel.f */
public class ListingPanelOverview extends ListingPanelBase {
    public ListingPanelOverview(Listing listing, BaseActivity baseActivity, @NonNull ad adVar) {
        super(listing, baseActivity, adVar);
        m4025a(2131756135, 2131756132, 2131756133, 2131756134);
    }

    protected void m4071c() {
        TableLayout tableLayout = (TableLayout) this.d.findViewById(2131756136);
        if (this.b.hasOverview()) {
            ListingPanelOverview.m4070a(this.b.getOverview(), tableLayout);
        }
    }

    public static void m4070a(List<String> list, TableLayout tableLayout) {
        int size = list.size();
        int dimensionPixelOffset = tableLayout.getResources().getDimensionPixelOffset(R.fixed_tiny);
        tableLayout.removeAllViews();
        for (int i = 0; i < size; i++) {
            View a = ListingPanelOverview.m4069a(tableLayout.getContext(), (String) list.get(i));
            if (i != 0) {
                a.setPadding(0, dimensionPixelOffset, 0, 0);
            }
            tableLayout.addView(a);
        }
    }

    private static TableRow m4069a(Context context, String str) {
        TableRow tableRow = new TableRow(context);
        tableRow.setGravity(48);
        TabletSupportUtil tabletSupportUtil = new TabletSupportUtil(context);
        Resources resources = context.getResources();
        float dimension = tabletSupportUtil.m5621a() ? resources.getDimension(R.text_large) : resources.getDimension(R.text_larger);
        LayoutParams layoutParams = new TableRow.LayoutParams();
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.weight = FullImageView.ASPECT_RATIO_SQUARE;
        View textView = new TextView(context);
        textView.setText(Html.fromHtml("&#8226; "));
        textView.setTextSize(0, dimension);
        textView.setTextColor(resources.getColor(R.text_grey));
        textView.setLineSpacing(0.0f, 1.25f);
        View textView2 = new TextView(context);
        textView2.setText(str);
        textView2.setTextSize(0, dimension);
        textView2.setTextColor(resources.getColor(R.text_grey));
        textView2.setLineSpacing(0.0f, 1.25f);
        if (aa.m3167a() && !GraphikUtil.m5548b()) {
            textView2.setTypeface(TypefaceCache.m5414a().m5416a("sans-serif-light"));
            textView.setTypeface(TypefaceCache.m5414a().m5416a("sans-serif-light"));
        }
        tableRow.addView(textView, -2, -2);
        tableRow.addView(textView2, layoutParams);
        return tableRow;
    }
}
