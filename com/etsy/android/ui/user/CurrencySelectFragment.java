package com.etsy.android.ui.user;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.EtsyNetworkJob;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.EtsyCurrency;
import com.etsy.android.lib.util.CurrencyUtil;
import com.etsy.android.lib.util.CurrencyUtil.DefaultCurrency;
import com.etsy.android.lib.util.NetworkUtils;
import com.etsy.android.lib.util.bl;
import com.etsy.android.ui.EtsyCommonListFragment;
import com.etsy.android.ui.adapters.CurrencyAdapter;
import com.etsy.android.uikit.ui.dialog.IDialogFragment;
import java.util.Collection;
import java.util.List;

public class CurrencySelectFragment extends EtsyCommonListFragment implements CurrencyUtil {
    private static final String TAG;
    private CurrencyAdapter mAdapter;
    private CurrencySelectFragment mCallback;

    static {
        TAG = EtsyDebug.m1891a(CurrencySelectFragment.class);
    }

    public void setCurrencySelectedCallback(CurrencySelectFragment currencySelectFragment) {
        this.mCallback = currencySelectFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        setEmptyText(this.mActivity.getString(R.prefs_currency_empty));
        this.mListView.setBackgroundColor(getResources().getColor(R.white));
        this.mAdapter = new CurrencyAdapter(this.mActivity);
        this.mListView.setAdapter(this.mAdapter);
        getCurrencies();
        return onCreateView;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getParentFragment() instanceof IDialogFragment) {
            ((IDialogFragment) getParentFragment()).setTitle(this.mActivity.getString(R.prefs_set_currency_title));
        }
    }

    public void onListItemClick(ListView listView, View view, int i, long j) {
        EtsyCurrency etsyCurrency = (EtsyCurrency) this.mAdapter.getItem(i);
        updateUserPrefs(etsyCurrency);
        if (this.mCallback != null) {
            this.mCallback.m5001a(etsyCurrency);
        }
        goBack();
    }

    protected void onRetryClicked() {
        getCurrencies();
    }

    private void getCurrencies() {
        showLoadingView();
        EtsyNetworkJob a = CurrencyUtil.m3054a((CurrencyUtil) this);
        if (a != null) {
            getRequestQueue().m1699a(a);
        }
    }

    private void updateUserPrefs(EtsyCurrency etsyCurrency) {
        if (aj.m1101a().m1118d() && etsyCurrency != null) {
            if (NetworkUtils.m3107a().m3114b()) {
                getRequestQueue().m1699a(new CurrencyUtil(this.mActivity.getApplicationContext(), etsyCurrency.getCode()));
            } else {
                bl.m3365b(this.mActivity, (int) R.currency_save_error);
            }
        }
        CurrencyUtil.m3068a(getActivity(), etsyCurrency);
    }

    public boolean handleBackPressed() {
        goBack();
        if (this.mCallback != null) {
            this.mCallback.m5000a();
        }
        return true;
    }

    private void goBack() {
        if (getParentFragment() != null && getParentFragment().getChildFragmentManager() != null) {
            getParentFragment().getChildFragmentManager().popBackStack();
        }
    }

    public void onCurrencyLoaded(List<EtsyCurrency> list) {
        this.mAdapter.addAll((Collection) list);
        if (this.mActivity != null) {
            this.mAdapter.insert(new DefaultCurrency(this.mActivity), 0);
        }
        showListView();
    }

    public void onCurrencyNoInternet() {
        showErrorView();
    }

    public void onCurrencyError() {
        showEmptyView();
    }

    @NonNull
    public String getTrackingName() {
        return "view_locale_preferences";
    }
}
