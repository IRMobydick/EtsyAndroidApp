package com.etsy.android.uikit.util.shop;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.CircularIntArray;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.models.ShopSection;
import com.etsy.android.lib.models.apiv3.FAQs;
import com.etsy.android.lib.models.apiv3.ListingMemberData;
import com.etsy.android.lib.models.apiv3.ShopV3;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.ShopHomeSortOption;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.models.interfaces.ShopHomeFilterOption;
import com.etsy.android.lib.models.view.shop.FAQTranslationData;
import com.etsy.android.lib.models.view.shop.ShopHomeReviewViewModel;
import com.etsy.android.lib.models.view.shop.section.ShopHomeStructuredPoliciesSectionViewModel;
import com.etsy.android.lib.util.bh;
import com.etsy.android.uikit.ui.shop.BaseShopHomeFragment;
import com.etsy.android.uikit.ui.shop.ShopHomeInitialLoadConfiguration;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
/* renamed from: com.etsy.android.uikit.util.shop.b */
public class ShopHomeStateManager {
    @NonNull
    protected String f4171a;
    @NonNull
    protected String f4172b;
    protected ShopSection f4173c;
    protected List<ShopSection> f4174d;
    protected ShopHomeSortOption f4175e;
    protected List<ShopHomeSortOption> f4176f;
    protected boolean f4177g;
    protected ArrayMap<EtsyId, ListingMemberData> f4178h;
    protected ArrayMap<EtsyId, CircularIntArray> f4179i;
    protected int f4180j;
    protected int f4181k;
    protected boolean f4182l;
    protected boolean f4183m;
    protected boolean f4184n;
    protected boolean f4185o;
    @Nullable
    private ShopHomeStateManager f4186p;

    public static ShopHomeStateManager m5574a(@NonNull List<ShopSection> list, @NonNull ShopV3 shopV3, @NonNull ShopHomeStateManager shopHomeStateManager, @NonNull Resources resources) {
        list.add(0, ShopSection.allItemsSection(resources));
        return new ShopHomeStateManager(shopV3, list, shopHomeStateManager, ShopHomeSortOption.defaultSortOptions(resources, shopV3), resources);
    }

    ShopHomeStateManager() {
        this.f4171a = StringUtils.EMPTY;
        this.f4172b = StringUtils.EMPTY;
        this.f4178h = new ArrayMap();
        this.f4179i = new ArrayMap();
        this.f4180j = -1;
        this.f4181k = -1;
    }

    private ShopHomeStateManager(@NonNull ShopV3 shopV3, @NonNull List<ShopSection> list, @NonNull ShopHomeStateManager shopHomeStateManager, @NonNull List<ShopHomeSortOption> list2, @NonNull Resources resources) {
        this.f4171a = StringUtils.EMPTY;
        this.f4172b = StringUtils.EMPTY;
        this.f4178h = new ArrayMap();
        this.f4179i = new ArrayMap();
        this.f4180j = -1;
        this.f4181k = -1;
        aj a = aj.m1101a();
        boolean z = a.m1118d() && shopV3.getUserId().equals(a.m1125k());
        this.f4185o = z;
        this.f4174d = list;
        this.f4173c = (ShopSection) list.get(0);
        this.f4176f = list2;
        this.f4175e = (ShopHomeSortOption) list2.get(0);
        this.f4184n = shopV3.isListingRearrangeEnabled();
        ShopHomeStateManager.m5575a(list, resources, R.shop_section_picker);
        ShopHomeStateManager.m5575a(list2, resources, R.shop_sort_picker);
        m5586a(shopHomeStateManager);
    }

    @NonNull
    public String m5577a() {
        return this.f4171a;
    }

    @NonNull
    public String m5592b() {
        return this.f4172b;
    }

    public void m5587a(@NonNull String str) {
        this.f4171a = str;
    }

    public void m5594b(@NonNull String str) {
        this.f4172b = str;
        this.f4171a = str;
        this.f4173c = (ShopSection) this.f4174d.get(0);
        if (this.f4186p != null) {
            this.f4186p.refreshFilterSpinners();
            this.f4186p.performSearch(this);
            this.f4186p.didSubmitSearchQuery(str);
        }
    }

    public void m5596c() {
        String str = this.f4172b;
        this.f4172b = StringUtils.EMPTY;
        this.f4171a = StringUtils.EMPTY;
        this.f4173c = (ShopSection) this.f4174d.get(0);
        this.f4175e = (ShopHomeSortOption) this.f4176f.get(0);
        if (this.f4186p != null && bh.m3343b(str)) {
            this.f4186p.performSearch(this);
            this.f4186p.refreshFilterSpinners();
            this.f4186p.didClearSearch();
        }
    }

    public void m5579a(@NonNull ShopSection shopSection) {
        ShopSection shopSection2 = this.f4173c;
        this.f4171a = StringUtils.EMPTY;
        this.f4172b = StringUtils.EMPTY;
        if ((shopSection2 == null || !shopSection2.equals(shopSection)) && this.f4174d.contains(shopSection)) {
            this.f4173c = shopSection;
            if (this.f4186p != null) {
                this.f4186p.didSelectSection(shopSection);
                this.f4186p.refreshSearchBox();
                this.f4186p.performSearch(this);
            }
        }
    }

    public List<ShopSection> m5599d() {
        return this.f4174d;
    }

    public ShopSection m5601e() {
        return this.f4173c;
    }

    public void m5586a(@NonNull ShopHomeStateManager shopHomeStateManager) {
        this.f4186p = shopHomeStateManager;
    }

    public void m5597c(@Nullable String str) {
        if (str != null) {
            int size = this.f4176f.size();
            for (int i = 0; i < size; i++) {
                ShopHomeSortOption shopHomeSortOption = (ShopHomeSortOption) this.f4176f.get(i);
                if (str.equals(shopHomeSortOption.getOptionId())) {
                    this.f4175e = shopHomeSortOption;
                    return;
                }
            }
        }
    }

    public void m5582a(@NonNull ShopHomeSortOption shopHomeSortOption) {
        ShopHomeSortOption shopHomeSortOption2 = this.f4175e;
        if (shopHomeSortOption2 == null || !shopHomeSortOption2.equals(shopHomeSortOption)) {
            this.f4175e = shopHomeSortOption;
            if (this.f4186p != null) {
                this.f4186p.didSelectSortOption(shopHomeSortOption);
                this.f4186p.performSearch(this);
            }
        }
    }

    public ShopHomeSortOption m5602f() {
        return this.f4175e;
    }

    public List<ShopHomeSortOption> m5603g() {
        if (!this.f4184n || this.f4173c == null || this.f4173c.isAllItemsSection()) {
            return this.f4176f;
        }
        return this.f4176f.subList(1, this.f4176f.size());
    }

    private static void m5575a(@NonNull List<? extends ShopHomeFilterOption> list, @NonNull Resources resources, @StringRes int i) {
        String string = resources.getString(i);
        int length = string.length();
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            ShopHomeFilterOption shopHomeFilterOption = (ShopHomeFilterOption) list.get(i2);
            CharSequence spannableString = new SpannableString(string + ": " + shopHomeFilterOption.getDropdownLabel());
            spannableString.setSpan(new ForegroundColorSpan(resources.getColor(R.blue)), length + 2, spannableString.length(), 33);
            shopHomeFilterOption.setDisplayLabel(spannableString);
        }
    }

    public int m5604h() {
        return this.f4180j;
    }

    public void m5578a(int i) {
        this.f4180j = i;
    }

    public int m5605i() {
        return this.f4181k;
    }

    public void m5593b(int i) {
        this.f4181k = i;
    }

    public void m5606j() {
        if (this.f4186p != null) {
            this.f4186p.loadMoreListings(this);
        }
    }

    public void m5583a(@NonNull ShopHomeReviewViewModel shopHomeReviewViewModel) {
        if (this.f4186p != null) {
            this.f4186p.translateReviewMessage(shopHomeReviewViewModel);
        }
    }

    public void m5584a(@NonNull ShopHomeStructuredPoliciesSectionViewModel shopHomeStructuredPoliciesSectionViewModel) {
        if (this.f4186p != null) {
            this.f4186p.translatePrivacyOther(shopHomeStructuredPoliciesSectionViewModel);
        }
    }

    public void m5580a(@NonNull FAQs fAQs, @NonNull FAQTranslationData fAQTranslationData) {
        if (this.f4186p != null) {
            this.f4186p.translateFAQs(fAQs, fAQTranslationData);
        }
    }

    public boolean m5607k() {
        return this.f4182l;
    }

    public void m5589a(boolean z) {
        this.f4182l = z;
    }

    public void m5588a(@NonNull List<ListingMemberData> list) {
        Map map = this.f4178h;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ListingMemberData listingMemberData = (ListingMemberData) list.get(i);
            map.put(listingMemberData.getListingId(), listingMemberData);
        }
    }

    @Nullable
    public ListingMemberData m5576a(@NonNull EtsyId etsyId) {
        return (ListingMemberData) this.f4178h.get(etsyId);
    }

    public void m5581a(@NonNull EtsyId etsyId, int i) {
        ArrayMap arrayMap = this.f4179i;
        CircularIntArray circularIntArray = (CircularIntArray) arrayMap.get(etsyId);
        if (circularIntArray == null) {
            circularIntArray = new CircularIntArray(2);
            arrayMap.put(etsyId, circularIntArray);
        }
        circularIntArray.addLast(i);
    }

    public void m5608l() {
        this.f4179i.clear();
    }

    public void m5609m() {
        ArrayMap arrayMap = this.f4179i;
        int i = 0;
        while (i < arrayMap.size()) {
            CircularIntArray circularIntArray = (CircularIntArray) arrayMap.valueAt(i);
            int size = circularIntArray.size();
            if (size == 1) {
                arrayMap.removeAt(i);
                i--;
            } else if (size == 2) {
                circularIntArray.popFirst();
            }
            i++;
        }
    }

    @Nullable
    public CircularIntArray m5591b(@NonNull EtsyId etsyId) {
        return (CircularIntArray) this.f4179i.get(etsyId);
    }

    @SafeVarargs
    public final void m5590a(List<? extends ListingLike>... listArr) {
        ArrayMap arrayMap = this.f4178h;
        for (List list : listArr) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                ListingLike listingLike = (ListingLike) list.get(i);
                ListingMemberData listingMemberData = (ListingMemberData) arrayMap.get(listingLike.getListingId());
                if (listingMemberData != null) {
                    listingLike.setHasCollections(listingMemberData.hasCollections());
                    listingLike.setIsFavorite(listingMemberData.isFavorite());
                }
            }
        }
    }

    public boolean m5610n() {
        return this.f4183m;
    }

    public void m5595b(boolean z) {
        this.f4183m = z;
    }

    public void m5598c(boolean z) {
        this.f4185o = z;
    }

    public boolean m5611o() {
        return this.f4185o;
    }

    public boolean m5612p() {
        return this.f4177g;
    }

    public void m5600d(boolean z) {
        this.f4177g = z;
    }

    public void m5585a(@NonNull ShopHomeInitialLoadConfiguration shopHomeInitialLoadConfiguration) {
        int i = 0;
        Map map = shopHomeInitialLoadConfiguration.f4114a;
        if (map != null) {
            String str;
            int i2;
            if (map.containsKey(BaseShopHomeFragment.SEARCH_PARAM_SECTION_ID)) {
                str = (String) map.get(BaseShopHomeFragment.SEARCH_PARAM_SECTION_ID);
                int size = this.f4174d.size();
                for (i2 = 0; i2 < size; i2++) {
                    ShopSection shopSection = (ShopSection) this.f4174d.get(i2);
                    if (shopSection.getShopSectionId().getId().equals(str)) {
                        this.f4173c = shopSection;
                        this.f4180j = shopSection.getListingActiveCount();
                        break;
                    }
                }
                if (this.f4176f.size() > 1 && this.f4175e.getOptionId().equals(ShopHomeSortOption.SORT_CUSTOM)) {
                    this.f4175e = (ShopHomeSortOption) this.f4176f.get(1);
                }
            } else if (map.containsKey(BaseShopHomeFragment.SEARCH_PARAM_SEARCH_QUERY)) {
                str = (String) map.get(BaseShopHomeFragment.SEARCH_PARAM_SEARCH_QUERY);
                this.f4172b = str;
                this.f4171a = str;
            }
            if (map.containsKey("order")) {
                str = (String) map.get("order");
                i2 = this.f4176f.size();
                while (i < i2) {
                    ShopHomeSortOption shopHomeSortOption = (ShopHomeSortOption) this.f4176f.get(i);
                    if (shopHomeSortOption.getOptionId().equals(str)) {
                        this.f4175e = shopHomeSortOption;
                        return;
                    }
                    i++;
                }
            }
        }
    }
}
