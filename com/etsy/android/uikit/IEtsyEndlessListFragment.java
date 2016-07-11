package com.etsy.android.uikit;

/* renamed from: com.etsy.android.uikit.f */
public interface IEtsyEndlessListFragment extends IEtsyCommonListFragment {
    int getApiOffset();

    void removeEndlessError();

    void showEndlessError();

    void startEndless();

    void stopEndless();
}
