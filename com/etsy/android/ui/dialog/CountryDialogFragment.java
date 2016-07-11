package com.etsy.android.ui.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout.LayoutParams;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.models.Country;
import com.etsy.android.lib.util.CountryUtil;
import com.etsy.android.lib.util.CountryUtil.EverywhereCountry;
import com.etsy.android.lib.util.bl;
import com.etsy.android.ui.EtsyCommonListFragment;
import com.etsy.android.uikit.adapter.CountriesAdapter;
import com.etsy.android.uikit.ui.dialog.IDialogFragment;
import com.etsy.android.uikit.ui.dialog.IDialogFragment.WindowMode;
import java.util.ArrayList;
import java.util.Collections;
import org.parceler.Parcels;

public class CountryDialogFragment extends EtsyCommonListFragment implements OnItemClickListener, CountryUtil {
    private static final String COUNTRIES = "countries";
    private static final String ENABLED_COUNTRIES = "enabled_countries";
    private ArrayList<Country> mCountries;
    private CountriesAdapter mCountriesAdapter;
    private int mDividerCountryPosition;
    private ArrayList<Country> mEnabledCountries;
    private CountryUtil mListener;
    private IDialogFragment mParentDialog;

    public static CountryDialogFragment newInstance(CountryUtil countryUtil, ArrayList<Country> arrayList) {
        CountryDialogFragment countryDialogFragment = new CountryDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(COUNTRIES, Parcels.m7494a((Object) arrayList));
        countryDialogFragment.setArguments(bundle);
        countryDialogFragment.setDialogSelectedListener(countryUtil);
        return countryDialogFragment;
    }

    public static CountryDialogFragment newInstance(CountryUtil countryUtil, ArrayList<Country> arrayList, ArrayList<Country> arrayList2) {
        CountryDialogFragment countryDialogFragment = new CountryDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(COUNTRIES, Parcels.m7494a((Object) arrayList));
        bundle.putParcelable(ENABLED_COUNTRIES, Parcels.m7494a((Object) arrayList2));
        countryDialogFragment.setArguments(bundle);
        countryDialogFragment.setDialogSelectedListener(countryUtil);
        return countryDialogFragment;
    }

    private void setDialogSelectedListener(CountryUtil countryUtil) {
        this.mListener = countryUtil;
    }

    public void setCountries(ArrayList<Country> arrayList) {
        setDialogCountries(arrayList);
    }

    public void setEnabledCountries(ArrayList<Country> arrayList) {
        this.mEnabledCountries = arrayList;
        setDialogCountries(this.mCountries);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCountries((ArrayList) Parcels.m7495a(getArguments().getParcelable(COUNTRIES)));
        setEnabledCountries((ArrayList) Parcels.m7495a(getArguments().getParcelable(ENABLED_COUNTRIES)));
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.mCountries == null) {
            setDialogCountries(CountryUtil.m3034d());
        }
        if (this.mCountries.size() == 0) {
            getRequestQueue().m1699a(new CountryUtil(this));
            showLoadingView();
        } else if (this.mCountries.size() == 1 && (this.mCountries.get(0) instanceof EverywhereCountry)) {
            if (CountryUtil.m3029a()) {
                this.mCountries.addAll(CountryUtil.m3034d());
                setDialogCountries(this.mCountries);
            } else {
                getRequestQueue().m1699a(new CountryUtil(this));
                showLoadingView();
            }
        }
        return onCreateView;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mParentDialog = (IDialogFragment) getParentFragment();
        this.mParentDialog.setWindowMode(WindowMode.WRAP);
        this.mCountriesAdapter = new CountriesAdapter(getActivity(), getAnalyticsContext(), this.mCountries, this.mDividerCountryPosition, this.mEnabledCountries);
        this.mListView.setAdapter(this.mCountriesAdapter);
        this.mListView.setOnItemClickListener(this);
        this.mListView.setDivider(getResources().getDrawable(R.list_divider_padded));
        this.mListView.setDividerHeight(getResources().getDimensionPixelSize(R.divider_height));
        this.mListView.setLayoutParams(new LayoutParams(-1, -2));
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.mListener.m3421a((Country) this.mCountriesAdapter.getItem(i));
        this.mParentDialog.dismissAllowingStateLoss();
    }

    public void onCountriesLoaded(ArrayList<Country> arrayList) {
        setDialogCountries(arrayList);
        if (getConfigMap().m885c(EtsyConfigKeys.f1226s)) {
            this.mCountriesAdapter.clear();
        }
        this.mCountriesAdapter.addAll(this.mCountries);
        this.mCountriesAdapter.notifyDataSetChanged();
        showListView();
    }

    public void onCountriesError() {
        if (this.mActivity != null) {
            bl.m3365b(this.mActivity.getApplicationContext(), (int) R.country_whoops);
        }
        if (this.mParentDialog != null && !this.mParentDialog.isDetached()) {
            this.mParentDialog.dismissAllowingStateLoss();
        }
    }

    private void setDialogCountries(ArrayList<Country> arrayList) {
        if (getConfigMap().m885c(EtsyConfigKeys.f1226s) && arrayList != null) {
            Collections.sort(arrayList);
            this.mDividerCountryPosition = CountryUtil.m3021a((ArrayList) arrayList);
            if (this.mCountriesAdapter != null) {
                this.mCountriesAdapter.setDividerCountryPosition(this.mDividerCountryPosition);
                this.mCountriesAdapter.setEnabledCountries(this.mEnabledCountries);
            }
        }
        this.mCountries = arrayList;
    }
}
