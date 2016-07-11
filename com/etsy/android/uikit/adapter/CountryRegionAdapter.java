package com.etsy.android.uikit.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsSpinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.Country;
import com.etsy.android.lib.models.Region;
import com.etsy.android.lib.models.ShippingTemplateEntry;
import com.etsy.android.lib.models.editable.EditableShippingTemplateEntry;
import com.etsy.android.lib.util.CountryUtil;
import com.etsy.android.lib.util.CountryUtil.EverywhereCountry;
import com.etsy.android.lib.util.ao;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

public class CountryRegionAdapter extends ArrayAdapter<Object> {
    private static final int STATE_HAS_HEADER = 1;
    private static final int STATE_NO_HEADER = 2;
    private static final int STATE_UNKNOWN = 0;
    private List<Country> mCountries;
    private boolean mDisableEverywhere;
    private Set<Country> mDisabledCountries;
    private Set<Region> mDisabledRegions;
    private EverywhereCountry mEverywhereCountry;
    private int[] mHeaderCache;
    private final boolean mLeftAlignPrompt;
    private Set<String> mPrimaryIsoCodes;
    private List<Region> mRegions;
    private int mResource;
    private boolean mShowEverywhereCountry;
    private View mSpinnerView;
    private Country mTopCountry;

    @Deprecated
    public CountryRegionAdapter(Context context, List<Country> list, List<Region> list2, Country country) {
        this(context, (List) list, (List) list2, country, false);
    }

    public CountryRegionAdapter(Context context, List<Country> list, List<Region> list2, Country country, @LayoutRes int i) {
        this(context, (List) list, (List) list2, country, true);
        this.mResource = i;
    }

    public CountryRegionAdapter(Context context, List<Country> list, List<Region> list2, Country country, boolean z) {
        super(context, R.list_item_with_header);
        this.mHeaderCache = new int[0];
        this.mLeftAlignPrompt = z;
        if (z) {
            this.mResource = R.spinner_item_dark_grey_no_padding;
        } else {
            this.mResource = R.spinner_item_dark_grey;
        }
        this.mDisabledCountries = new HashSet();
        this.mDisabledRegions = new HashSet();
        this.mPrimaryIsoCodes = CountryUtil.m3033c();
        this.mEverywhereCountry = new EverywhereCountry(context.getString(R.shipping_everywhere_else));
        this.mTopCountry = country;
        this.mCountries = list;
        this.mRegions = list2;
        setupData();
    }

    public void setRegionsAndCountries(List<Region> list, List<Country> list2) {
        this.mCountries = list2;
        this.mRegions = list;
        clear();
        setupData();
    }

    public int getEverywherePosition() {
        return super.getPosition(this.mEverywhereCountry);
    }

    public void setTopCountry(Country country) {
        if (this.mTopCountry != null) {
            this.mCountries.add(this.mTopCountry);
        }
        this.mTopCountry = country;
        clear();
        setupData();
    }

    protected void internalAdd(Object obj) {
        super.add(obj);
    }

    protected void setupData() {
        if (!(this.mTopCountry == null || this.mCountries == null)) {
            this.mCountries.remove(this.mTopCountry);
        }
        if (this.mTopCountry != null) {
            super.add(this.mTopCountry);
        }
        if (this.mShowEverywhereCountry) {
            super.add(this.mEverywhereCountry);
        }
        if (this.mRegions != null) {
            Collections.sort(this.mRegions);
            super.addAll(this.mRegions);
        }
        if (this.mCountries != null) {
            Collections.sort(this.mCountries);
            super.addAll(this.mCountries);
        }
        this.mHeaderCache = new int[getCount()];
        notifyDataSetChanged();
    }

    public void setSelectedEntries(List<EditableShippingTemplateEntry> list) {
        if (list != null) {
            clearSelectedEntries();
            for (ShippingTemplateEntry shippingTemplateEntry : list) {
                if (shippingTemplateEntry.shipsEverywhere()) {
                    this.mDisableEverywhere = true;
                } else if (shippingTemplateEntry.isDestinationRegionSet()) {
                    this.mDisabledRegions.add(shippingTemplateEntry.getDestinationRegion());
                } else if (shippingTemplateEntry.isDestinationCountrySet()) {
                    this.mDisabledCountries.add(shippingTemplateEntry.getDestinationCountry());
                }
            }
            notifyDataSetChanged();
        }
    }

    public void clearSelectedEntries() {
        this.mDisableEverywhere = false;
        this.mDisabledCountries.clear();
        this.mDisabledRegions.clear();
    }

    public boolean isEnabled(int i) {
        Object item = getItem(i);
        if (item instanceof EverywhereCountry) {
            if (this.mDisableEverywhere) {
                return false;
            }
            return true;
        } else if (item instanceof Country) {
            if (this.mDisabledCountries.contains(item)) {
                return false;
            }
            return true;
        } else if (!(item instanceof Region)) {
            return super.isEnabled(i);
        } else {
            if (this.mDisabledRegions.contains(item)) {
                return false;
            }
            return true;
        }
    }

    public boolean areAllItemsEnabled() {
        return true;
    }

    public void add(Object obj) {
        throw new UnsupportedOperationException("This adapter is not configured to add or remove data after initialization");
    }

    public void addAll(Collection collection) {
        throw new UnsupportedOperationException("This adapter is not configured to add or remove data after initialization");
    }

    public void addAll(Object[] objArr) {
        throw new UnsupportedOperationException("This adapter is not configured to add or remove data after initialization");
    }

    public void remove(Object obj) {
        throw new UnsupportedOperationException("This adapter is not configured to add or remove data after initialization");
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (!(viewGroup instanceof AbsSpinner)) {
            return buildView(i, view, viewGroup, this.mLeftAlignPrompt);
        }
        if (this.mSpinnerView == null) {
            this.mSpinnerView = LayoutInflater.from(getContext()).inflate(this.mResource, viewGroup, false);
        }
        Object item = getItem(i);
        if (item instanceof Region) {
            ((TextView) this.mSpinnerView.findViewById(16908308)).setText(((Region) item).getRegionName());
        } else if (item instanceof Country) {
            ((TextView) this.mSpinnerView.findViewById(16908308)).setText(((Country) item).getName());
        }
        return this.mSpinnerView;
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        boolean z = false;
        View buildView = buildView(i, view, viewGroup, false);
        buildView.setEnabled(isEnabled(i));
        if (!isEnabled(i)) {
            z = true;
        }
        buildView.setClickable(z);
        buildView.findViewById(R.item_label).setEnabled(isEnabled(i));
        return buildView;
    }

    private View buildView(int i, View view, ViewGroup viewGroup, boolean z) {
        CountryRegionAdapter countryRegionAdapter;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.list_item_with_header, viewGroup, false);
            CountryRegionAdapter a = CountryRegionAdapter.m5308a(view, z);
            view.setTag(a);
            countryRegionAdapter = a;
        } else {
            countryRegionAdapter = (CountryRegionAdapter) view.getTag();
        }
        Object item = getItem(ao.m3260a(i, 0, getCount() - 1));
        if (item instanceof Country) {
            bindCountryView(countryRegionAdapter, (Country) item);
        } else if (item instanceof Region) {
            bindRegionView(countryRegionAdapter, (Region) item);
        }
        countryRegionAdapter.f3933b.setEnabled(isEnabled(i));
        if (shouldShowHeader(i)) {
            countryRegionAdapter.f3934c.setVisibility(0);
            if (item instanceof Region) {
                countryRegionAdapter.f3934c.setText(R.regions);
            } else if (item instanceof Country) {
                if (this.mPrimaryIsoCodes.contains(((Country) item).getIsoCountryCode())) {
                    countryRegionAdapter.f3934c.setText(R.countries);
                } else {
                    countryRegionAdapter.f3934c.setText(StringUtils.EMPTY);
                }
            }
        } else {
            countryRegionAdapter.f3934c.setVisibility(8);
        }
        return view;
    }

    private boolean shouldShowHeader(int i) {
        switch (this.mHeaderCache[i]) {
            case STATE_HAS_HEADER /*1*/:
                return true;
            case STATE_NO_HEADER /*2*/:
                return false;
            default:
                if (i == 0) {
                    boolean z;
                    if (getItem(i) instanceof Region) {
                        this.mHeaderCache[i] = STATE_HAS_HEADER;
                        z = true;
                    } else {
                        this.mHeaderCache[i] = STATE_NO_HEADER;
                        z = false;
                    }
                    return z;
                }
                Object item = getItem(i);
                Object item2 = getItem(i - 1);
                if ((item instanceof Region) && (item2 instanceof Country)) {
                    this.mHeaderCache[i] = STATE_HAS_HEADER;
                    return true;
                } else if ((item instanceof Country) && (item2 instanceof Region)) {
                    this.mHeaderCache[i] = STATE_HAS_HEADER;
                    return true;
                } else if (item instanceof EverywhereCountry) {
                    this.mHeaderCache[i] = STATE_NO_HEADER;
                    return false;
                } else if (!(item instanceof Country) || !(item2 instanceof Country)) {
                    this.mHeaderCache[i] = STATE_NO_HEADER;
                    return false;
                } else if (!this.mPrimaryIsoCodes.contains(((Country) item2).getIsoCountryCode()) || this.mPrimaryIsoCodes.contains(((Country) item).getIsoCountryCode())) {
                    this.mHeaderCache[i] = STATE_NO_HEADER;
                    return false;
                } else {
                    this.mHeaderCache[i] = STATE_HAS_HEADER;
                    return true;
                }
        }
    }

    private void bindRegionView(CountryRegionAdapter countryRegionAdapter, Region region) {
        countryRegionAdapter.f3933b.setText(region.getRegionName());
    }

    private void bindCountryView(CountryRegionAdapter countryRegionAdapter, Country country) {
        countryRegionAdapter.f3933b.setText(country.getName());
    }

    public void showEverywhereCountry(boolean z) {
        this.mShowEverywhereCountry = z;
        clear();
        setupData();
    }
}
