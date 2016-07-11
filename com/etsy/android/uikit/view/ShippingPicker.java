package com.etsy.android.uikit.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatSpinner;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.Country;
import com.etsy.android.lib.models.Region;
import com.etsy.android.lib.models.apiv3.StructuredShopShippingEstimate;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.util.CountryUtil;
import com.etsy.android.uikit.adapter.CountryRegionAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ShippingPicker extends LinearLayout {
    private AppCompatSpinner mCustomMax;
    private ArrayAdapter<Integer> mCustomMaxAdapter;
    private AppCompatSpinner mCustomMin;
    private ArrayAdapter<Integer> mCustomMinAdapter;
    private ArrayList<Integer> mMaxCustomValues;
    private ArrayList<Integer> mMinCustomValues;
    private List<Region> mRegions;
    private StructuredShopShippingEstimate mShippingEstimate;
    private AppCompatSpinner mSpinner;
    private SwitchToggle mUnitToggle;

    /* renamed from: com.etsy.android.uikit.view.ShippingPicker.1 */
    class C10241 implements OnItemSelectedListener {
        final /* synthetic */ ShippingPicker f4220a;

        C10241(ShippingPicker shippingPicker) {
            this.f4220a = shippingPicker;
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            Object item = adapterView.getAdapter().getItem(i);
            if (item instanceof Country) {
                this.f4220a.mShippingEstimate.setCountryId(((Country) item).getCountryId());
                this.f4220a.mShippingEstimate.setType(StructuredShopShippingEstimate.TYPE_COUNTRY);
                this.f4220a.mShippingEstimate.setDisplayName(((Country) item).getName());
            } else if (item instanceof Region) {
                this.f4220a.mShippingEstimate.setRegionCode(((Region) item).getRegionCode());
                this.f4220a.mShippingEstimate.setType(EtsyRequest.PARAM_REGION);
                this.f4220a.mShippingEstimate.setDisplayName(((Region) item).getRegionName());
            }
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    /* renamed from: com.etsy.android.uikit.view.ShippingPicker.2 */
    class C10252 implements OnItemSelectedListener {
        final /* synthetic */ ShippingPicker f4221a;

        C10252(ShippingPicker shippingPicker) {
            this.f4221a = shippingPicker;
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            int intValue = ((Integer) this.f4221a.mCustomMinAdapter.getItem(i)).intValue();
            this.f4221a.mShippingEstimate.setMin(intValue);
            if (intValue >= ((Integer) this.f4221a.mCustomMax.getSelectedItem()).intValue()) {
                this.f4221a.mCustomMax.setSelection(i);
                this.f4221a.mShippingEstimate.setMax(((Integer) this.f4221a.mCustomMax.getSelectedItem()).intValue());
            }
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    /* renamed from: com.etsy.android.uikit.view.ShippingPicker.3 */
    class C10263 implements OnItemSelectedListener {
        final /* synthetic */ ShippingPicker f4222a;

        C10263(ShippingPicker shippingPicker) {
            this.f4222a = shippingPicker;
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            int intValue = ((Integer) this.f4222a.mCustomMaxAdapter.getItem(i)).intValue();
            this.f4222a.mShippingEstimate.setMax(intValue);
            if (intValue <= ((Integer) this.f4222a.mCustomMin.getSelectedItem()).intValue()) {
                this.f4222a.mCustomMin.setSelection(i);
                this.f4222a.mShippingEstimate.setMin(((Integer) this.f4222a.mCustomMin.getSelectedItem()).intValue());
            }
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    /* renamed from: com.etsy.android.uikit.view.ShippingPicker.4 */
    class C10274 implements SwitchToggle {
        final /* synthetic */ ShippingPicker f4223a;

        C10274(ShippingPicker shippingPicker) {
            this.f4223a = shippingPicker;
        }

        public void m5645a(SwitchToggle switchToggle, boolean z) {
            this.f4223a.mShippingEstimate.setUnit(z ? StructuredShopShippingEstimate.UNIT_DAYS : StructuredShopShippingEstimate.UNIT_WEEKS);
        }
    }

    public ShippingPicker(Context context) {
        super(context);
        this.mMinCustomValues = new ArrayList(Arrays.asList(new Integer[]{Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(8), Integer.valueOf(9), Integer.valueOf(10), Integer.valueOf(11), Integer.valueOf(12), Integer.valueOf(13), Integer.valueOf(14), Integer.valueOf(15), Integer.valueOf(16), Integer.valueOf(17), Integer.valueOf(18), Integer.valueOf(19), Integer.valueOf(20), Integer.valueOf(21), Integer.valueOf(22), Integer.valueOf(23), Integer.valueOf(24), Integer.valueOf(25), Integer.valueOf(26), Integer.valueOf(27), Integer.valueOf(28), Integer.valueOf(29)}));
        this.mMaxCustomValues = new ArrayList(Arrays.asList(new Integer[]{Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(8), Integer.valueOf(9), Integer.valueOf(10), Integer.valueOf(11), Integer.valueOf(12), Integer.valueOf(13), Integer.valueOf(14), Integer.valueOf(15), Integer.valueOf(16), Integer.valueOf(17), Integer.valueOf(18), Integer.valueOf(19), Integer.valueOf(20), Integer.valueOf(21), Integer.valueOf(22), Integer.valueOf(23), Integer.valueOf(24), Integer.valueOf(25), Integer.valueOf(26), Integer.valueOf(27), Integer.valueOf(28), Integer.valueOf(29), Integer.valueOf(30)}));
        init();
    }

    public ShippingPicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMinCustomValues = new ArrayList(Arrays.asList(new Integer[]{Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(8), Integer.valueOf(9), Integer.valueOf(10), Integer.valueOf(11), Integer.valueOf(12), Integer.valueOf(13), Integer.valueOf(14), Integer.valueOf(15), Integer.valueOf(16), Integer.valueOf(17), Integer.valueOf(18), Integer.valueOf(19), Integer.valueOf(20), Integer.valueOf(21), Integer.valueOf(22), Integer.valueOf(23), Integer.valueOf(24), Integer.valueOf(25), Integer.valueOf(26), Integer.valueOf(27), Integer.valueOf(28), Integer.valueOf(29)}));
        this.mMaxCustomValues = new ArrayList(Arrays.asList(new Integer[]{Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(8), Integer.valueOf(9), Integer.valueOf(10), Integer.valueOf(11), Integer.valueOf(12), Integer.valueOf(13), Integer.valueOf(14), Integer.valueOf(15), Integer.valueOf(16), Integer.valueOf(17), Integer.valueOf(18), Integer.valueOf(19), Integer.valueOf(20), Integer.valueOf(21), Integer.valueOf(22), Integer.valueOf(23), Integer.valueOf(24), Integer.valueOf(25), Integer.valueOf(26), Integer.valueOf(27), Integer.valueOf(28), Integer.valueOf(29), Integer.valueOf(30)}));
        init();
    }

    public ShippingPicker(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mMinCustomValues = new ArrayList(Arrays.asList(new Integer[]{Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(8), Integer.valueOf(9), Integer.valueOf(10), Integer.valueOf(11), Integer.valueOf(12), Integer.valueOf(13), Integer.valueOf(14), Integer.valueOf(15), Integer.valueOf(16), Integer.valueOf(17), Integer.valueOf(18), Integer.valueOf(19), Integer.valueOf(20), Integer.valueOf(21), Integer.valueOf(22), Integer.valueOf(23), Integer.valueOf(24), Integer.valueOf(25), Integer.valueOf(26), Integer.valueOf(27), Integer.valueOf(28), Integer.valueOf(29)}));
        this.mMaxCustomValues = new ArrayList(Arrays.asList(new Integer[]{Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(8), Integer.valueOf(9), Integer.valueOf(10), Integer.valueOf(11), Integer.valueOf(12), Integer.valueOf(13), Integer.valueOf(14), Integer.valueOf(15), Integer.valueOf(16), Integer.valueOf(17), Integer.valueOf(18), Integer.valueOf(19), Integer.valueOf(20), Integer.valueOf(21), Integer.valueOf(22), Integer.valueOf(23), Integer.valueOf(24), Integer.valueOf(25), Integer.valueOf(26), Integer.valueOf(27), Integer.valueOf(28), Integer.valueOf(29), Integer.valueOf(30)}));
        init();
    }

    @TargetApi(21)
    public ShippingPicker(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mMinCustomValues = new ArrayList(Arrays.asList(new Integer[]{Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(8), Integer.valueOf(9), Integer.valueOf(10), Integer.valueOf(11), Integer.valueOf(12), Integer.valueOf(13), Integer.valueOf(14), Integer.valueOf(15), Integer.valueOf(16), Integer.valueOf(17), Integer.valueOf(18), Integer.valueOf(19), Integer.valueOf(20), Integer.valueOf(21), Integer.valueOf(22), Integer.valueOf(23), Integer.valueOf(24), Integer.valueOf(25), Integer.valueOf(26), Integer.valueOf(27), Integer.valueOf(28), Integer.valueOf(29)}));
        this.mMaxCustomValues = new ArrayList(Arrays.asList(new Integer[]{Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(8), Integer.valueOf(9), Integer.valueOf(10), Integer.valueOf(11), Integer.valueOf(12), Integer.valueOf(13), Integer.valueOf(14), Integer.valueOf(15), Integer.valueOf(16), Integer.valueOf(17), Integer.valueOf(18), Integer.valueOf(19), Integer.valueOf(20), Integer.valueOf(21), Integer.valueOf(22), Integer.valueOf(23), Integer.valueOf(24), Integer.valueOf(25), Integer.valueOf(26), Integer.valueOf(27), Integer.valueOf(28), Integer.valueOf(29), Integer.valueOf(30)}));
        init();
    }

    private void init() {
        setOrientation(1);
        LayoutInflater.from(getContext()).inflate(R.custom_shipping_spinner, this, true);
        this.mSpinner = (AppCompatSpinner) findViewById(R.shipping_spinner);
        this.mCustomMin = (AppCompatSpinner) findViewById(R.min_spinner);
        this.mCustomMax = (AppCompatSpinner) findViewById(R.max_spinner);
        this.mUnitToggle = (SwitchToggle) findViewById(R.unit_toggle);
        this.mSpinner.setOnItemSelectedListener(new C10241(this));
    }

    public void setEnabled(boolean z, List<StructuredShopShippingEstimate> list) {
        super.setEnabled(z);
        this.mSpinner.setEnabled(z);
        if (z) {
            setSpinnerAdapter(list);
        }
    }

    public void setShippingEstimate(StructuredShopShippingEstimate structuredShopShippingEstimate, List<Region> list) {
        this.mShippingEstimate = structuredShopShippingEstimate;
        if (!this.mShippingEstimate.isSet()) {
            this.mShippingEstimate.setMin(3);
            this.mShippingEstimate.setMax(5);
            this.mShippingEstimate.setUnit(StructuredShopShippingEstimate.UNIT_DAYS);
        }
        this.mCustomMinAdapter = setupCustomPickerAdapter(this.mShippingEstimate.getMin(), this.mCustomMin, this.mMinCustomValues);
        this.mCustomMaxAdapter = setupCustomPickerAdapter(this.mShippingEstimate.getMax(), this.mCustomMax, this.mMaxCustomValues);
        this.mUnitToggle.setChecked(StructuredShopShippingEstimate.UNIT_DAYS.equals(this.mShippingEstimate.getUnit()));
        this.mCustomMin.setOnItemSelectedListener(new C10252(this));
        this.mCustomMax.setOnItemSelectedListener(new C10263(this));
        this.mUnitToggle.setOnCheckedChangeListener(new C10274(this));
        this.mRegions = list;
        setSpinnerAdapter();
    }

    private void setSpinnerAdapter() {
        setSpinnerAdapter(new ArrayList());
    }

    private void setSpinnerAdapter(@NonNull List<StructuredShopShippingEstimate> list) {
        Country toCountry = this.mShippingEstimate.toCountry();
        Region toRegion = this.mShippingEstimate.toRegion();
        List arrayList = new ArrayList(CountryUtil.m3034d());
        List arrayList2 = new ArrayList(this.mRegions);
        for (StructuredShopShippingEstimate structuredShopShippingEstimate : list) {
            Region toRegion2 = structuredShopShippingEstimate.toRegion();
            if (!(toRegion2 == null || toRegion2.equals(toRegion))) {
                arrayList2.remove(toRegion2);
            }
            Country toCountry2 = structuredShopShippingEstimate.toCountry();
            if (!(toCountry2 == null || toCountry2.equals(toCountry))) {
                arrayList.remove(toCountry2);
            }
        }
        SpinnerAdapter countryRegionAdapter = new CountryRegionAdapter(getContext(), arrayList, arrayList2, null, R.spinner_item_blue_large_no_padding);
        this.mSpinner.setAdapter(countryRegionAdapter);
        if (toCountry != null) {
            this.mSpinner.setSelection(countryRegionAdapter.getPosition(toCountry));
        } else if (toRegion != null) {
            this.mSpinner.setSelection(countryRegionAdapter.getPosition(toRegion));
        }
    }

    private ArrayAdapter<Integer> setupCustomPickerAdapter(int i, AppCompatSpinner appCompatSpinner, ArrayList<Integer> arrayList) {
        if (arrayList.indexOf(Integer.valueOf(i)) == -1) {
            arrayList.add(Integer.valueOf(i));
            Collections.sort(arrayList);
        }
        SpinnerAdapter arrayAdapter = new ArrayAdapter(getContext(), R.spinner_item_blue_large_no_padding, arrayList);
        arrayAdapter.setDropDownViewResource(R.spinner_dropdown_item);
        appCompatSpinner.setAdapter(arrayAdapter);
        appCompatSpinner.setSelection(arrayAdapter.getPosition(Integer.valueOf(i)));
        return arrayAdapter;
    }
}
