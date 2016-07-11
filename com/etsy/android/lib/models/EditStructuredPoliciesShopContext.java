package com.etsy.android.lib.models;

import android.support.annotation.NonNull;
import com.etsy.android.lib.models.apiv3.ShopHomePage;
import com.etsy.android.lib.models.apiv3.ShopPolicy;
import com.etsy.android.lib.models.apiv3.ShopV3;
import com.etsy.android.lib.models.datatypes.EtsyId;
import org.parceler.Parcel;

@Parcel
public class EditStructuredPoliciesShopContext {
    int mDigitalListingCount;
    boolean mHasOnlyDigitalListings;
    boolean mHasPrivateReceiptInfo;
    boolean mHasUnstructuredPolicies;
    boolean mIsUsingStructuredPolicies;
    EtsyId mShopId;
    String mShopName;

    EditStructuredPoliciesShopContext() {
    }

    public static EditStructuredPoliciesShopContext newInstance(@NonNull Shop shop) {
        EditStructuredPoliciesShopContext editStructuredPoliciesShopContext = new EditStructuredPoliciesShopContext();
        editStructuredPoliciesShopContext.mIsUsingStructuredPolicies = shop.isUsingStructuredPolicies();
        editStructuredPoliciesShopContext.mHasPrivateReceiptInfo = shop.hasPrivateReceiptInfo();
        editStructuredPoliciesShopContext.mHasUnstructuredPolicies = shop.hasUnstructuredPolicies();
        editStructuredPoliciesShopContext.mDigitalListingCount = shop.getDigitalListingCount();
        editStructuredPoliciesShopContext.mHasOnlyDigitalListings = shop.hasOnlyDigitalListings();
        editStructuredPoliciesShopContext.mShopId = shop.getShopId();
        editStructuredPoliciesShopContext.mShopName = shop.getShopName();
        return editStructuredPoliciesShopContext;
    }

    public static EditStructuredPoliciesShopContext newInstance(@NonNull ShopHomePage shopHomePage) {
        ShopV3 shop = shopHomePage.getShop();
        EditStructuredPoliciesShopContext editStructuredPoliciesShopContext = new EditStructuredPoliciesShopContext();
        editStructuredPoliciesShopContext.mIsUsingStructuredPolicies = shop.isUsingStructuredPolicies();
        editStructuredPoliciesShopContext.mHasPrivateReceiptInfo = shop.hasPrivateReceiptInfo();
        editStructuredPoliciesShopContext.mShopId = shop.getShopId();
        editStructuredPoliciesShopContext.mDigitalListingCount = shop.getDigitalListingCount();
        editStructuredPoliciesShopContext.mHasOnlyDigitalListings = shop.hasOnlyDigitalListings();
        editStructuredPoliciesShopContext.mShopName = shop.getShopName();
        ShopPolicy shopPolicy = shopHomePage.getShopPolicy();
        boolean z = (shopPolicy == null || shopPolicy.isEmpty()) ? false : true;
        editStructuredPoliciesShopContext.mHasUnstructuredPolicies = z;
        return editStructuredPoliciesShopContext;
    }

    public int getDigitalListingCount() {
        return this.mDigitalListingCount;
    }

    public boolean hasOnlyDigitalListings() {
        return this.mHasOnlyDigitalListings;
    }

    public boolean hasPrivateReceiptInfo() {
        return this.mHasPrivateReceiptInfo;
    }

    public boolean hasUnstructuredPolicies() {
        return this.mHasUnstructuredPolicies;
    }

    public boolean isUsingStructuredPolicies() {
        return this.mIsUsingStructuredPolicies;
    }

    public EtsyId getShopId() {
        return this.mShopId;
    }

    public String getShopName() {
        return this.mShopName;
    }

    public void setUsingStructuredPolicies(boolean z) {
        this.mIsUsingStructuredPolicies = z;
    }
}
