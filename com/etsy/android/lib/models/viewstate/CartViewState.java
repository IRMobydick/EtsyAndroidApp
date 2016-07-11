package com.etsy.android.lib.models.viewstate;

import java.io.Serializable;
import org.parceler.Parcel;

@Parcel
public class CartViewState implements Serializable {
    protected boolean mIsEditingShippingCosts;
    protected boolean mIsLoading;
    protected boolean mIsRequestingShippingCosts;
    protected boolean mIsViewingShippingCosts;

    public CartViewState() {
        this.mIsLoading = false;
        this.mIsEditingShippingCosts = false;
        this.mIsViewingShippingCosts = false;
        this.mIsRequestingShippingCosts = false;
    }

    public boolean isLoading() {
        return this.mIsLoading;
    }

    public void setIsLoading(boolean z) {
        this.mIsLoading = z;
    }

    public boolean isEditingShippingCosts() {
        return this.mIsEditingShippingCosts;
    }

    public void setEditingShippingCosts(boolean z) {
        this.mIsEditingShippingCosts = z;
    }

    public boolean isViewingShippingCosts() {
        return this.mIsViewingShippingCosts;
    }

    public void setViewingShippingCosts(boolean z) {
        this.mIsViewingShippingCosts = z;
    }

    public boolean isRequestingShippingCosts() {
        return this.mIsRequestingShippingCosts;
    }

    public void setRequestingShippingCosts(boolean z) {
        this.mIsRequestingShippingCosts = z;
    }
}
