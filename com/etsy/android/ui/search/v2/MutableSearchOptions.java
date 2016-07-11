package com.etsy.android.ui.search.v2;

import android.content.res.Resources;
import android.os.Parcel;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.HttpRequestJobBuilder;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.etsy.android.ui.search.v2.b */
public class MutableSearchOptions implements SearchOptions {
    private String f3336a;
    private int f3337b;

    private MutableSearchOptions() {
        this.f3336a = StringUtils.EMPTY;
        this.f3337b = 0;
        m4901c();
    }

    public String m4897a(Resources resources) {
        return this.f3337b == 0 ? resources.getString(R.new_search_filter_shop_location_anywhere) : this.f3336a;
    }

    public boolean m4899a() {
        return this.f3337b == 0;
    }

    public int m4900b() {
        return this.f3337b;
    }

    public void m4898a(int i, String str) {
        if (i == 0) {
            m4901c();
            return;
        }
        this.f3337b = i;
        this.f3336a = str;
    }

    public void m4901c() {
        this.f3337b = 0;
        this.f3336a = StringUtils.EMPTY;
    }

    private <T extends BaseModel> void m4892a(HttpRequestJobBuilder<T> httpRequestJobBuilder) {
        if (!m4899a()) {
            httpRequestJobBuilder.m1744a(ResponseConstants.LOCATION, this.f3336a);
        }
    }

    private void m4891a(Parcel parcel) {
        parcel.writeString(this.f3336a);
        parcel.writeInt(this.f3337b);
    }

    private void m4895b(Parcel parcel) {
        this.f3336a = parcel.readString();
        this.f3337b = parcel.readInt();
    }
}
