package com.etsy.android.ui.adapters;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.ad;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.models.apiv3.ShopCard;
import com.etsy.android.lib.models.apiv3.TreasuryV3;
import com.etsy.android.lib.models.apiv3.UserProfilePage;
import com.etsy.android.lib.models.apiv3.UserProfileV3;
import com.etsy.android.lib.models.cardviewelement.ListSection;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.ui.cardview.p014a.ListingCardClickHandler;
import com.etsy.android.ui.cardview.viewholders.HorizontalListSectionViewHolder;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.ui.user.profile.viewholders.ProfileUserShopViewHolder;
import com.etsy.android.ui.user.profile.viewholders.UserActionButtonsViewHolder;
import com.etsy.android.ui.user.profile.viewholders.UserProfileHeaderViewHolder;
import com.etsy.android.ui.view.viewholders.ProfileCardViewHolder;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import com.etsy.android.uikit.cardview.ICardViewAdapter;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.viewholder.ButtonViewHolder;
import com.etsy.android.uikit.viewholder.ListingCardViewHolder;
import com.etsy.android.uikit.viewholder.MarginsItemDecoration;
import com.etsy.android.uikit.viewholder.TextViewHolder;
import com.etsy.android.uikit.viewholder.TitleAndDescriptionViewHolder;
import com.etsy.android.uikit.viewholder.b;
import com.etsy.android.uikit.viewholder.h;
import com.etsy.android.uikit.viewholder.k;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserProfileAdapter extends BaseRecyclerViewAdapter<Pair<?, Integer>> implements ICardViewAdapter {
    private static final int SHOP_LISTING_DISPLAY_COUNT = 4;
    private static final int TYPE_APPRECIATION_PHOTO_SECTION = 506;
    static final int TYPE_BUTTON_BLUE = 508;
    static final int TYPE_LISTING_CARD = 504;
    private static final int TYPE_SHOP_INFO = 503;
    static final int TYPE_USER_ACTION_BUTTONS = 510;
    static final int TYPE_USER_HEADER = 501;
    private static final int TYPE_USER_LIST_DESCRIPTION = 505;
    static final int TYPE_USER_PROFILE_CARD = 507;
    static final int TYPE_VIEW_MORE_IN_FAVORITE_LIST = 509;
    final UserProfileAdapter mConfig;
    private UserProfilePage mData;
    private boolean mDidMapListings;
    private final boolean mIsYou;
    private final ListingCardClickHandler mListingCardListener;
    private final HashMap<String, ArrayList<Integer>> mListingIdsToPositionsMap;
    private final BroadcastReceiver mListingStateReceiver;
    private final MarginsItemDecoration mMarginsDecoration;

    /* renamed from: com.etsy.android.ui.adapters.UserProfileAdapter.10 */
    class AnonymousClass10 extends GridLayoutManager {
        final /* synthetic */ UserProfileAdapter f2127a;

        AnonymousClass10(UserProfileAdapter userProfileAdapter, Context context, int i) {
            this.f2127a = userProfileAdapter;
            super(context, i);
        }

        protected int getExtraLayoutSpace(State state) {
            return 60;
        }
    }

    /* renamed from: com.etsy.android.ui.adapters.UserProfileAdapter.1 */
    class C05301 extends MarginsItemDecoration {
        final /* synthetic */ UserProfileAdapter f2128a;

        C05301(UserProfileAdapter userProfileAdapter, int i, int i2, int i3, int i4) {
            this.f2128a = userProfileAdapter;
            super(i, i2, i3, i4);
        }

        protected boolean isDecorated(View view, RecyclerView recyclerView) {
            return recyclerView.getChildViewHolder(view).getItemViewType() == UserProfileAdapter.TYPE_USER_LIST_DESCRIPTION;
        }
    }

    /* renamed from: com.etsy.android.ui.adapters.UserProfileAdapter.2 */
    /* synthetic */ class C05312 {
        static final /* synthetic */ int[] f2129a;

        static {
            f2129a = new int[UserListType.values().length];
            try {
                f2129a[UserListType.LIST_TYPE_COLLECTION.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f2129a[UserListType.LIST_TYPE_FAVORITE_SHOP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f2129a[UserListType.LIST_TYPE_TREASURY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f2129a[UserListType.LIST_TYPE_APPRECIATION_PHOTO.ordinal()] = UserProfileAdapter.SHOP_LISTING_DISPLAY_COUNT;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    /* renamed from: com.etsy.android.ui.adapters.UserProfileAdapter.3 */
    class C05323 extends BroadcastReceiver {
        final /* synthetic */ UserProfileAdapter f2130a;

        C05323(UserProfileAdapter userProfileAdapter) {
            this.f2130a = userProfileAdapter;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(EtsyAction.STATE_CHANGE.getAction())) {
                this.f2130a.listingStateChanged(intent.getExtras());
            }
        }
    }

    /* renamed from: com.etsy.android.ui.adapters.UserProfileAdapter.4 */
    class C05334 extends TrackingOnClickListener {
        final /* synthetic */ ShopCard f2131a;
        final /* synthetic */ UserProfileAdapter f2132b;

        C05334(UserProfileAdapter userProfileAdapter, ShopCard shopCard) {
            this.f2132b = userProfileAdapter;
            this.f2131a = shopCard;
        }

        public void onViewClick(View view) {
            Nav.m4681a((Activity) this.f2132b.mContext).m4501b(this.f2131a.getShopId());
        }
    }

    /* renamed from: com.etsy.android.ui.adapters.UserProfileAdapter.5 */
    class C05345 extends TrackingOnClickListener {
        final /* synthetic */ UserProfileV3 f2133a;
        final /* synthetic */ UserProfileAdapter f2134b;

        C05345(UserProfileAdapter userProfileAdapter, UserProfileV3 userProfileV3) {
            this.f2134b = userProfileAdapter;
            this.f2133a = userProfileV3;
        }

        public void onViewClick(View view) {
            Nav.m4682a((FragmentActivity) this.f2134b.mContext).m4683a().m4469a(this.f2133a.getUserId(), 0, this.f2133a.getLoginName());
        }
    }

    /* renamed from: com.etsy.android.ui.adapters.UserProfileAdapter.6 */
    class C05356 extends TrackingOnClickListener {
        final /* synthetic */ UserProfileV3 f2135a;
        final /* synthetic */ UserProfileAdapter f2136b;

        C05356(UserProfileAdapter userProfileAdapter, UserProfileV3 userProfileV3) {
            this.f2136b = userProfileAdapter;
            this.f2135a = userProfileV3;
        }

        public void onViewClick(View view) {
            Nav.m4682a((FragmentActivity) this.f2136b.mContext).m4683a().m4469a(this.f2135a.getUserId(), 1, this.f2135a.getLoginName());
        }
    }

    /* renamed from: com.etsy.android.ui.adapters.UserProfileAdapter.7 */
    class C05367 extends TrackingOnClickListener {
        final /* synthetic */ UserProfileV3 f2137a;
        final /* synthetic */ UserProfileAdapter f2138b;

        C05367(UserProfileAdapter userProfileAdapter, UserProfileV3 userProfileV3) {
            this.f2138b = userProfileAdapter;
            this.f2137a = userProfileV3;
        }

        public void onViewClick(View view) {
            Nav.m4682a((FragmentActivity) this.f2138b.mContext).m4683a().m4469a(this.f2137a.getUserId(), 2, this.f2137a.getLoginName());
        }
    }

    /* renamed from: com.etsy.android.ui.adapters.UserProfileAdapter.8 */
    class C05378 extends TrackingOnClickListener {
        final /* synthetic */ UserProfileV3 f2139a;
        final /* synthetic */ UserProfileAdapter f2140b;

        C05378(UserProfileAdapter userProfileAdapter, UserProfileV3 userProfileV3) {
            this.f2140b = userProfileAdapter;
            this.f2139a = userProfileV3;
        }

        public void onViewClick(View view) {
            Nav.m4682a((FragmentActivity) this.f2140b.mContext).m4683a().m4530g(this.f2139a.getUserId());
        }
    }

    /* renamed from: com.etsy.android.ui.adapters.UserProfileAdapter.9 */
    class C05389 extends SpanSizeLookup {
        final /* synthetic */ UserProfileAdapter f2141a;

        C05389(UserProfileAdapter userProfileAdapter) {
            this.f2141a = userProfileAdapter;
        }

        public int getSpanSize(int i) {
            switch (this.f2141a.getListItemViewType(i)) {
                case UserProfileAdapter.TYPE_USER_HEADER /*501*/:
                    return this.f2141a.mConfig.f2227f;
                case UserProfileAdapter.TYPE_LISTING_CARD /*504*/:
                    return 1;
                case UserProfileAdapter.TYPE_USER_PROFILE_CARD /*507*/:
                    return this.f2141a.mConfig.f2224c;
                case UserProfileAdapter.TYPE_USER_ACTION_BUTTONS /*510*/:
                    return this.f2141a.mConfig.f2226e;
                default:
                    return this.f2141a.mConfig.f2222a;
            }
        }
    }

    enum UserListType {
        LIST_TYPE_APPRECIATION_PHOTO,
        LIST_TYPE_COLLECTION,
        LIST_TYPE_FAVORITE_SHOP,
        LIST_TYPE_TREASURY
    }

    public UserProfileAdapter(FragmentActivity fragmentActivity, ImageBatch imageBatch, @NonNull ad adVar, boolean z) {
        super(fragmentActivity, imageBatch);
        this.mListingIdsToPositionsMap = new HashMap();
        this.mMarginsDecoration = new C05301(this, 0, this.mContext.getResources().getDimensionPixelOffset(R.fixed_medium), 0, 0);
        this.mListingStateReceiver = new C05323(this);
        this.mListingCardListener = new ListingCardClickHandler(z ? "your_account" : "people_account", fragmentActivity, this, adVar);
        this.mIsYou = z;
        this.mConfig = UserProfileAdapter.m3557a(fragmentActivity, z);
    }

    public void setData(UserProfilePage userProfilePage) {
        int i;
        UserProfileV3 userProfile = userProfilePage.getUserProfile();
        this.mItems.add(new Pair(userProfilePage.getUserProfile(), Integer.valueOf(TYPE_USER_HEADER)));
        if (!this.mIsYou) {
            this.mItems.add(new Pair(userProfile, Integer.valueOf(TYPE_USER_ACTION_BUTTONS)));
        }
        if (userProfile.isSeller()) {
            ShopCard shopCard = userProfilePage.getShopCard();
            if (shopCard != null) {
                this.mItems.add(new Pair(shopCard, Integer.valueOf(TYPE_SHOP_INFO)));
                List cardListings = shopCard.getCardListings();
                int size = shopCard.getCardListings().size();
                if (size > 0 && size % SHOP_LISTING_DISPLAY_COUNT == 0) {
                    for (i = 0; i < size; i++) {
                        this.mItems.add(new Pair(cardListings.get(i), Integer.valueOf(TYPE_LISTING_CARD)));
                    }
                }
                this.mItems.add(new Pair(new b(this.mContext.getString(R.visit_shop), new C05334(this, shopCard)), Integer.valueOf(TYPE_BUTTON_BLUE)));
            }
        }
        for (UserListType configureForUserListType : UserListType.values()) {
            configureForUserListType(configureForUserListType, userProfilePage);
        }
        this.mData = userProfilePage;
        notifyItemRangeChanged(0, this.mItems.size());
    }

    public void clear() {
        this.mData = null;
        this.mItems.clear();
        this.mDidMapListings = false;
        this.mListingIdsToPositionsMap.clear();
    }

    private int configureForUserListType(UserListType userListType, UserProfilePage userProfilePage) {
        int favoriteListingsCount;
        int size;
        List filterCollections;
        CharSequence string;
        CharSequence quantityString;
        String string2;
        TrackingOnClickListener buildClickListener;
        int i = 0;
        UserProfileV3 userProfile = userProfilePage.getUserProfile();
        Resources resources = this.mContext.getResources();
        switch (C05312.f2129a[userListType.ordinal()]) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                favoriteListingsCount = userProfile.getFavoriteListingsCount();
                if (favoriteListingsCount > 0) {
                    size = userProfilePage.getCollections().size();
                    filterCollections = filterCollections(userProfilePage.getCollections(), this.mConfig.f2223b, this.mIsYou);
                    string = resources.getString(R.favorite_items);
                    quantityString = resources.getQuantityString(R.item_titlecase_quantity, favoriteListingsCount, new Object[]{Integer.valueOf(favoriteListingsCount)});
                    string2 = resources.getString(R.see_all_favorite_items);
                    buildClickListener = buildClickListener(userListType, userProfile);
                    break;
                }
                return 0;
            case Task.NETWORK_STATE_ANY /*2*/:
                size = userProfile.getFavoriteShopsCount();
                if (size > 0) {
                    filterCollections = userProfilePage.getFavoriteShops();
                    string = resources.getString(R.favorite_shops);
                    quantityString = size + " " + resources.getQuantityString(R.shops_plurals_nt, size);
                    string2 = resources.getString(R.see_all_favorite_shops);
                    buildClickListener = buildClickListener(userListType, userProfile);
                    break;
                }
                return 0;
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                size = userProfile.getFavoriteTreasuriesCount();
                if (size > 0) {
                    filterCollections = userProfilePage.getFavoriteTreasuries();
                    string = this.mContext.getString(R.favorite_treasuries);
                    quantityString = size + " " + resources.getQuantityString(R.treasuries_plurals_nt, size);
                    string2 = resources.getString(R.see_all_favorite_treasuries);
                    buildClickListener = buildClickListener(userListType, userProfile);
                    break;
                }
                return 0;
            case SHOP_LISTING_DISPLAY_COUNT /*4*/:
                size = userProfile.getAppreciationPhotoCount();
                if (size > 0) {
                    filterCollections = userProfilePage.getAppreciationPhotos();
                    string = resources.getString(R.photos);
                    quantityString = size + " " + resources.getQuantityString(R.photos_plurals_nt, size);
                    string2 = resources.getString(R.see_all_photos);
                    buildClickListener = buildClickListener(userListType, userProfile);
                    break;
                }
                return 0;
            default:
                return 0;
        }
        this.mItems.add(new Pair(new k(string, quantityString), Integer.valueOf(TYPE_USER_LIST_DESCRIPTION)));
        favoriteListingsCount = filterCollections.size();
        if (userListType != UserListType.LIST_TYPE_APPRECIATION_PHOTO) {
            while (i < favoriteListingsCount) {
                this.mItems.add(new Pair(filterCollections.get(i), Integer.valueOf(TYPE_USER_PROFILE_CARD)));
                i++;
            }
        } else {
            ListSection listSection = new ListSection();
            listSection.setItems(filterCollections);
            this.mItems.add(new Pair(listSection, Integer.valueOf(TYPE_APPRECIATION_PHOTO_SECTION)));
        }
        if (size == favoriteListingsCount) {
            return favoriteListingsCount;
        }
        this.mItems.add(new Pair(new b(string2, buildClickListener), Integer.valueOf(TYPE_BUTTON_BLUE)));
        return favoriteListingsCount;
    }

    private TrackingOnClickListener buildClickListener(UserListType userListType, UserProfileV3 userProfileV3) {
        switch (C05312.f2129a[userListType.ordinal()]) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                return new C05345(this, userProfileV3);
            case Task.NETWORK_STATE_ANY /*2*/:
                return new C05356(this, userProfileV3);
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                return new C05367(this, userProfileV3);
            default:
                return new C05378(this, userProfileV3);
        }
    }

    private static List<Collection> filterCollections(List<Collection> list, int i, boolean z) {
        List<Collection> arrayList = new ArrayList();
        int size = list.size();
        int i2 = 0;
        int i3 = 0;
        while (i3 < i && i2 < size) {
            int i4;
            Collection collection = (Collection) list.get(i2);
            if (collection.getListingsCount() <= 0 || !(z || collection.isPublic())) {
                i4 = i3;
            } else {
                arrayList.add(collection);
                i4 = i3 + 1;
            }
            i2++;
            i3 = i4;
        }
        return arrayList;
    }

    public ViewHolder onCreateListItemViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE_USER_HEADER /*501*/:
                return new UserProfileHeaderViewHolder(this.mInflater.inflate(2130903573, viewGroup, false));
            case TYPE_SHOP_INFO /*503*/:
                return new ProfileUserShopViewHolder(this.mInflater.inflate(2130903553, viewGroup, false));
            case TYPE_LISTING_CARD /*504*/:
                return new ListingCardViewHolder(this.mInflater, viewGroup, this.mListingCardListener, this.mImageBatch, false, false);
            case TYPE_USER_LIST_DESCRIPTION /*505*/:
                return new TitleAndDescriptionViewHolder(this.mInflater.inflate(R.layout_heading_and_description, viewGroup, false));
            case TYPE_APPRECIATION_PHOTO_SECTION /*506*/:
                return new HorizontalListSectionViewHolder(this.mInflater, viewGroup, this.mImageBatch, ResponseConstants.USER_PROFILE, false);
            case TYPE_USER_PROFILE_CARD /*507*/:
                return new ProfileCardViewHolder(this.mInflater.inflate(2130903572, viewGroup, false), this.mImageBatch, this.mConfig.f2223b, this.mConfig.f2228g);
            case TYPE_BUTTON_BLUE /*508*/:
                return new ButtonViewHolder(this.mInflater.inflate(R.button_blue, viewGroup, false));
            case TYPE_VIEW_MORE_IN_FAVORITE_LIST /*509*/:
                return new TextViewHolder(this.mInflater, viewGroup, 2130903452, 2131755943);
            case TYPE_USER_ACTION_BUTTONS /*510*/:
                return new UserActionButtonsViewHolder(this.mInflater.inflate(2130903571, viewGroup, false));
            default:
                return null;
        }
    }

    public void onBindListItemViewHolder(ViewHolder viewHolder, int i) {
        if (this.mData != null) {
            Pair pair = (Pair) getItem(i);
            switch (((Integer) pair.second).intValue()) {
                case TYPE_USER_HEADER /*501*/:
                    ((UserProfileHeaderViewHolder) viewHolder).bind((UserProfileV3) pair.first, this.mIsYou, this.mImageBatch, (FragmentActivity) this.mContext);
                case TYPE_SHOP_INFO /*503*/:
                    UserProfileV3 userProfile = this.mData.getUserProfile();
                    ((ProfileUserShopViewHolder) viewHolder).bind((ShopCard) pair.first, userProfile.getFirstName(), userProfile.getTransactionsSoldCount(), this.mIsYou, this.mImageBatch);
                case TYPE_LISTING_CARD /*504*/:
                    ((ListingCardViewHolder) viewHolder).bind((ListingCard) pair.first, true, this.mIsYou);
                case TYPE_USER_LIST_DESCRIPTION /*505*/:
                    ((TitleAndDescriptionViewHolder) viewHolder).bind((k) pair.first);
                case TYPE_APPRECIATION_PHOTO_SECTION /*506*/:
                    ((HorizontalListSectionViewHolder) viewHolder).bind((ListSection) pair.first);
                case TYPE_USER_PROFILE_CARD /*507*/:
                    bindUserProfileCard((ProfileCardViewHolder) viewHolder, pair.first);
                case TYPE_BUTTON_BLUE /*508*/:
                    ((ButtonViewHolder) viewHolder).bind((b) pair.first);
                case TYPE_VIEW_MORE_IN_FAVORITE_LIST /*509*/:
                    ((TextViewHolder) viewHolder).bind((h) pair.first);
                case TYPE_USER_ACTION_BUTTONS /*510*/:
                    ((UserActionButtonsViewHolder) viewHolder).bind((UserProfileV3) pair.first, (FragmentActivity) this.mContext);
                default:
            }
        }
    }

    public void onViewRecycled(ViewHolder viewHolder) {
        super.onViewRecycled(viewHolder);
        switch (viewHolder.getItemViewType()) {
            case TYPE_LISTING_CARD /*504*/:
                ((ListingCardViewHolder) viewHolder).recycle();
            case TYPE_APPRECIATION_PHOTO_SECTION /*506*/:
                ((HorizontalListSectionViewHolder) viewHolder).recycle();
            case TYPE_USER_PROFILE_CARD /*507*/:
                ((ProfileCardViewHolder) viewHolder).recycle();
            default:
        }
    }

    private static void bindUserProfileCard(ProfileCardViewHolder profileCardViewHolder, Object obj) {
        if (obj instanceof Collection) {
            profileCardViewHolder.bind((Collection) obj);
        } else if (obj instanceof ShopCard) {
            profileCardViewHolder.bind((ShopCard) obj);
        } else if (obj instanceof TreasuryV3) {
            profileCardViewHolder.bind((TreasuryV3) obj);
        }
    }

    public int getListItemViewType(int i) {
        return ((Integer) ((Pair) this.mItems.get(i)).second).intValue();
    }

    public boolean canRemoveItems() {
        return false;
    }

    public void onRemoveItem(int i) {
    }

    public void onItemChanged(int i) {
        notifyItemChanged(i);
    }

    public SpanSizeLookup spanSizeLookup() {
        SpanSizeLookup c05389 = new C05389(this);
        c05389.setSpanIndexCacheEnabled(true);
        return c05389;
    }

    public UserProfilePage getData() {
        return this.mData;
    }

    public MarginsItemDecoration getMarginDividerDecoration() {
        return this.mMarginsDecoration;
    }

    public GridLayoutManager getLayoutManager(Context context) {
        return new AnonymousClass10(this, context, this.mConfig.f2222a);
    }

    void listingStateChanged(Bundle bundle) {
        if (!this.mDidMapListings) {
            mapListings(bundle);
            this.mDidMapListings = true;
        }
        CharSequence string = bundle.getString(ResponseConstants.ID);
        if (!TextUtils.isEmpty(string) && this.mListingIdsToPositionsMap.containsKey(string)) {
            for (Integer intValue : (List) this.mListingIdsToPositionsMap.get(string)) {
                int intValue2 = intValue.intValue();
                if (((Pair) this.mItems.get(intValue2)).first instanceof ListingLike) {
                    updateListingState((ListingLike) ((Pair) this.mItems.get(intValue2)).first, bundle);
                    notifyItemChanged(intValue2);
                }
            }
        }
    }

    private void mapListings(Bundle bundle) {
        for (int i = 0; i < this.mItems.size(); i++) {
            Object obj = ((Pair) this.mItems.get(i)).first;
            if (obj instanceof ListingLike) {
                String etsyId = ((ListingLike) obj).getListingId().toString();
                ArrayList arrayList = (ArrayList) this.mListingIdsToPositionsMap.get(etsyId);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    this.mListingIdsToPositionsMap.put(etsyId, arrayList);
                }
                arrayList.add(Integer.valueOf(i));
            }
        }
    }

    private void updateListingState(ListingLike listingLike, Bundle bundle) {
        if (bundle.containsKey(EtsyAction.STATE_FAVORITE)) {
            listingLike.setIsFavorite(bundle.getBoolean(EtsyAction.STATE_FAVORITE));
        }
        if (bundle.containsKey(EtsyAction.STATE_COLLECTIONS)) {
            listingLike.setHasCollections(bundle.getBoolean(EtsyAction.STATE_COLLECTIONS));
        }
    }

    public BroadcastReceiver getListingStateChangedReceiver() {
        return this.mListingStateReceiver;
    }
}
