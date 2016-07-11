package com.etsy.android.ui.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.etsy.android.iconsy.views.IconDrawable;
import com.etsy.android.iconsy.views.IconView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.logger.ITrackedObject;
import com.etsy.android.lib.logger.ad;
import com.etsy.android.lib.models.FavoriteList;
import com.etsy.android.lib.models.IFullImage;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.Treasury;
import com.etsy.android.lib.models.User;
import com.etsy.android.lib.models.UserProfile;
import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.lib.models.apiv3.ShopIcon;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.models.interfaces.ShopLike;
import com.etsy.android.lib.util.bh;
import com.etsy.android.lib.util.fonts.EtsyFontIcons;
import com.etsy.android.lib.util.fonts.StandardFontIcon;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.FullImageView;
import com.etsy.android.uikit.view.ListingFullImageView;
import com.etsy.android.uikit.view.RatingIconView;
import com.etsy.android.util.EtsyBuildHelper;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.text.NumberFormat;
import java.util.List;

/* renamed from: com.etsy.android.ui.util.i */
public class EtsyCardUtil {
    private final int f3692a;
    private final int f3693b;
    private Drawable f3694c;
    private Drawable f3695d;
    private Reference<Activity> f3696e;
    private ImageBatch f3697f;
    @NonNull
    private final ad f3698g;

    /* renamed from: com.etsy.android.ui.util.i.1 */
    class EtsyCardUtil extends TrackingOnClickListener {
        final /* synthetic */ FavoriteList f3680a;
        final /* synthetic */ EtsyCardUtil f3681b;

        EtsyCardUtil(EtsyCardUtil etsyCardUtil, FavoriteList favoriteList) {
            this.f3681b = etsyCardUtil;
            this.f3680a = favoriteList;
        }

        public void onViewClick(View view) {
            Nav.m4681a(this.f3681b.m5109b()).m4469a(this.f3680a.getUserId(), 0, this.f3680a.getLoginName());
        }
    }

    /* renamed from: com.etsy.android.ui.util.i.2 */
    class EtsyCardUtil extends TrackingOnClickListener {
        final /* synthetic */ boolean f3682a;
        final /* synthetic */ ShopLike f3683b;
        final /* synthetic */ EtsyCardUtil f3684c;

        EtsyCardUtil(EtsyCardUtil etsyCardUtil, AnalyticsLogAttribute analyticsLogAttribute, EtsyId etsyId, boolean z, ShopLike shopLike) {
            this.f3684c = etsyCardUtil;
            this.f3682a = z;
            this.f3683b = shopLike;
            super(analyticsLogAttribute, etsyId);
        }

        public void onViewClick(View view) {
            if (this.f3682a) {
                EtsyLogger.m1966a().m1997b("local_shop_tapped", "your_favorite_shops", new EtsyCardUtil$2$1(this));
            }
            if (this.f3683b.getUserId() == null || !this.f3683b.getUserId().hasId()) {
                Nav.m4681a(this.f3684c.m5109b()).m4501b(this.f3683b.getShopId());
            } else {
                Nav.m4681a(this.f3684c.m5109b()).m4473a(this.f3683b.getShopId(), this.f3683b.getUserId());
            }
        }
    }

    /* renamed from: com.etsy.android.ui.util.i.3 */
    class EtsyCardUtil extends TrackingOnClickListener {
        final /* synthetic */ User f3685a;
        final /* synthetic */ EtsyCardUtil f3686b;

        EtsyCardUtil(EtsyCardUtil etsyCardUtil, ITrackedObject[] iTrackedObjectArr, User user) {
            this.f3686b = etsyCardUtil;
            this.f3685a = user;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            Nav.m4681a(this.f3686b.m5109b()).m4511c(this.f3685a.getUserId());
        }
    }

    /* renamed from: com.etsy.android.ui.util.i.4 */
    class EtsyCardUtil extends TrackingOnClickListener {
        final /* synthetic */ Treasury f3687a;
        final /* synthetic */ EtsyCardUtil f3688b;

        EtsyCardUtil(EtsyCardUtil etsyCardUtil, ITrackedObject[] iTrackedObjectArr, Treasury treasury) {
            this.f3688b = etsyCardUtil;
            this.f3687a = treasury;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            Nav.m4681a(this.f3688b.m5109b()).m4506b(this.f3687a.getId());
        }
    }

    /* renamed from: com.etsy.android.ui.util.i.5 */
    class EtsyCardUtil extends TrackingOnClickListener {
        final /* synthetic */ Fragment f3689a;
        final /* synthetic */ Collection f3690b;
        final /* synthetic */ EtsyCardUtil f3691c;

        EtsyCardUtil(EtsyCardUtil etsyCardUtil, ITrackedObject[] iTrackedObjectArr, Fragment fragment, Collection collection) {
            this.f3691c = etsyCardUtil;
            this.f3689a = fragment;
            this.f3690b = collection;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            Nav.m4681a(this.f3691c.m5109b()).m4444a(600, this.f3689a).m4464a(this.f3690b);
        }
    }

    public static String m5106a(int i) {
        return "FavoriteListings(listing_id):active:" + i + "/Listing(" + ResponseConstants.LISTING_ID + ")/" + "Images(url_170x135,red,green,blue)";
    }

    public static String m5110b(int i) {
        return "DisplayedFeaturedListings(listing_id):active:" + i + "/" + "Images(url_170x135,red,green,blue)";
    }

    public static String m5113c(int i) {
        return "Listings(listing_id):active:" + i + "/" + "Images(url_170x135,red,green,blue)";
    }

    public EtsyCardUtil(Activity activity, ImageBatch imageBatch, @NonNull ad adVar) {
        this.f3696e = new WeakReference(activity);
        this.f3697f = imageBatch;
        this.f3698g = adVar;
        this.f3692a = m5112c().getDimensionPixelOffset(R.card_avatar_small);
        this.f3693b = m5112c().getDimensionPixelOffset(R.gen_avatar_corners_small);
        IconDrawable a = IconDrawable.m775a(m5112c()).m780a(EtsyFontIcons.LOCATION).m779a(m5112c().getColor(R.light_grey)).m778a((float) m5112c().getDimensionPixelSize(R.text_medium));
        this.f3694c = a.m777a();
        a.m779a(m5112c().getColor(R.blue));
        this.f3695d = a.m777a();
    }

    private ImageBatch m5104a() {
        return this.f3697f;
    }

    private Activity m5109b() {
        return (Activity) this.f3696e.get();
    }

    private Resources m5112c() {
        return ((Activity) this.f3696e.get()).getResources();
    }

    public static EtsyCardUtil m5105a(View view) {
        EtsyCardUtil etsyCardUtil = new EtsyCardUtil();
        etsyCardUtil.f3699a = view;
        etsyCardUtil.f3700b = view.findViewById(R.click_region);
        etsyCardUtil.f3701c = (TextView) view.findViewById(R.title);
        etsyCardUtil.f3702d = (IconView) view.findViewById(R.title_icon);
        etsyCardUtil.f3703e = (TextView) view.findViewById(R.subtitle);
        etsyCardUtil.f3706h = (RatingIconView) view.findViewById(R.rating);
        etsyCardUtil.f3707i = (TextView) view.findViewById(R.rating_count);
        etsyCardUtil.f3704f = (LinearLayout) view.findViewById(R.image_layout);
        etsyCardUtil.f3705g = (ImageView) view.findViewById(R.avatar);
        return etsyCardUtil;
    }

    private void m5108a(LinearLayout linearLayout, List<? extends ListingLike> list, int i) {
        linearLayout.removeAllViews();
        for (int i2 = 0; i2 < i; i2++) {
            IFullImage iFullImage = null;
            if (list.size() > i2) {
                iFullImage = ((ListingLike) list.get(i2)).getListingImage();
            }
            View listingFullImageView = new ListingFullImageView(m5109b());
            listingFullImageView.setScaleType(ScaleType.CENTER_CROP);
            listingFullImageView.setUseStandardRatio(true);
            listingFullImageView.setLayoutParams(new LayoutParams(0, 0, FullImageView.ASPECT_RATIO_SQUARE));
            if (iFullImage != null) {
                listingFullImageView.setImageInfo(iFullImage, m5104a());
            } else if (i2 == i - 1) {
                listingFullImageView.setBackgroundResource(R.bg_empty_image);
            } else {
                listingFullImageView.setBackgroundResource(R.bg_empty_image_right_divider);
            }
            linearLayout.addView(listingFullImageView);
        }
    }

    public void m5116a(EtsyCardUtil etsyCardUtil, FavoriteList favoriteList, int i) {
        if (favoriteList != null) {
            etsyCardUtil.f3701c.setText(m5109b().getString(R.list_items_i_love));
            OnClickListener etsyCardUtil2 = new EtsyCardUtil(this, favoriteList);
            etsyCardUtil.f3703e.setText(bh.m3333a((double) favoriteList.getNumFavorites()) + " " + m5109b().getString(R.items));
            etsyCardUtil.f3700b.setOnClickListener(etsyCardUtil2);
            if (i > 0) {
                m5108a(etsyCardUtil.f3704f, favoriteList.getListings(), i);
            }
        }
    }

    public static String m5107a(Context context, int i) {
        String string = context.getString(R.shops_found, new Object[]{NumberFormat.getInstance().format((long) i)});
        if (i == 0) {
            return context.getString(R.shops_found_none);
        }
        if (i == 1) {
            return context.getString(R.shops_found_single);
        }
        return string;
    }

    public void m5120a(EtsyCardUtil etsyCardUtil, ShopLike shopLike, int i, boolean z) {
        if (shopLike != null) {
            String iconUrl;
            CharSequence shopName = shopLike.getShopName();
            boolean z2 = shopLike.hasUpcomingLocalEvent() && (EtsyBuildHelper.m5709d() || this.f3698g.m1864f().m885c(EtsyConfigKeys.aa));
            etsyCardUtil.f3701c.setText(shopName);
            etsyCardUtil.f3700b.setOnClickListener(new EtsyCardUtil(this, AnalyticsLogAttribute.SHOP_ID, shopLike.getShopId(), z2, shopLike));
            if (i > 0) {
                m5108a(etsyCardUtil.f3704f, shopLike.getCardListings(), i);
            }
            String location = shopLike.getLocation();
            etsyCardUtil.f3705g.setVisibility(0);
            if (bh.m3340a(shopLike.getIconUrl(((Integer) ShopIcon.IMG_SIZE_75.first).intValue()))) {
                iconUrl = shopLike.getIconUrl(((Integer) ShopIcon.IMG_SIZE_75.first).intValue());
            } else {
                iconUrl = shopLike.getAvatarUrl();
            }
            m5104a().m1576b(iconUrl, etsyCardUtil.f3705g, this.f3693b, this.f3692a, this.f3692a);
            if (z) {
                etsyCardUtil.f3703e.setVisibility(8);
                if (shopLike.hasRatings() && shopLike.getAverageRating() > 0.0d) {
                    etsyCardUtil.f3707i.setVisibility(0);
                    etsyCardUtil.f3706h.setVisibility(0);
                    etsyCardUtil.f3706h.setRating((float) shopLike.getAverageRating());
                    etsyCardUtil.f3707i.setText("(" + bh.m3333a((double) shopLike.getNumRatings()) + ")");
                    return;
                } else if (shopLike.hasRatings()) {
                    etsyCardUtil.f3707i.setVisibility(0);
                    etsyCardUtil.f3706h.setVisibility(8);
                    etsyCardUtil.f3707i.setText(m5112c().getQuantityString(R.reviews_plurals_no_brackets, shopLike.getNumRatings(), new Object[]{bh.m3333a((double) shopLike.getNumRatings())}));
                    return;
                } else {
                    etsyCardUtil.f3706h.setVisibility(8);
                    etsyCardUtil.f3707i.setVisibility(8);
                    return;
                }
            }
            etsyCardUtil.f3706h.setVisibility(8);
            etsyCardUtil.f3707i.setVisibility(8);
            if (z2) {
                etsyCardUtil.f3703e.setText(shopLike.getUpcomingLocalEvent().getSellingStatusString(m5112c()));
                etsyCardUtil.f3703e.setTextColor(m5112c().getColor(R.blue));
                etsyCardUtil.f3703e.setCompoundDrawablesWithIntrinsicBounds(this.f3695d, null, null, null);
                etsyCardUtil.f3703e.setVisibility(0);
            } else if (bh.m3340a(location)) {
                etsyCardUtil.f3703e.setText(location);
                etsyCardUtil.f3703e.setTextColor(m5112c().getColor(R.light_grey));
                etsyCardUtil.f3703e.setCompoundDrawablesWithIntrinsicBounds(this.f3694c, null, null, null);
                etsyCardUtil.f3703e.setVisibility(0);
            } else {
                etsyCardUtil.f3703e.setVisibility(8);
            }
        }
    }

    public static String m5111b(Context context, int i) {
        String string = context.getString(R.users_found, new Object[]{NumberFormat.getInstance().format((long) i)});
        if (i == 0) {
            return context.getString(R.users_found_none);
        }
        if (i == 1) {
            return context.getString(R.users_found_single);
        }
        return string;
    }

    public static String m5114d(int i) {
        return "Profile(image_url_75x75,first_name,last_name,login_name,num_favorites)," + EtsyCardUtil.m5106a(i);
    }

    public void m5118a(EtsyCardUtil etsyCardUtil, User user, int i) {
        if (user != null) {
            UserProfile profile = user.getProfile();
            String str = null;
            if (profile != null) {
                etsyCardUtil.f3701c.setText(bh.m3334a(user));
                str = profile.getImageUrl75x75();
            }
            etsyCardUtil.f3703e.setText(m5112c().getString(R.followers) + ": " + bh.m3333a((double) user.getFollowerCount()));
            etsyCardUtil.f3705g.setBackgroundResource(R.bg_avatar_circle_small_borderless);
            etsyCardUtil.f3705g.setVisibility(0);
            m5104a().m1574b(str, etsyCardUtil.f3705g, this.f3692a);
            etsyCardUtil.f3700b.setOnClickListener(new EtsyCardUtil(this, new ITrackedObject[]{user}, user));
            m5108a(etsyCardUtil.f3704f, user.getFavoritesAsListings(), i);
        }
    }

    public void m5117a(EtsyCardUtil etsyCardUtil, Treasury treasury, int i) {
        if (treasury != null) {
            etsyCardUtil.f3701c.setText(treasury.getTitle());
            OnClickListener etsyCardUtil2 = new EtsyCardUtil(this, new ITrackedObject[]{treasury}, treasury);
            if (bh.m3340a(treasury.getDescription())) {
                etsyCardUtil.f3703e.setVisibility(0);
                etsyCardUtil.f3703e.setText(treasury.getDescription());
            } else {
                etsyCardUtil.f3703e.setVisibility(8);
            }
            etsyCardUtil.f3700b.setOnClickListener(etsyCardUtil2);
            if (i > 0) {
                m5108a(etsyCardUtil.f3704f, treasury.getListings(), i);
            }
        }
    }

    public void m5119a(EtsyCardUtil etsyCardUtil, Collection collection, int i, Fragment fragment) {
        if (collection != null) {
            etsyCardUtil.f3701c.setText(collection.getName());
            if (collection.isPrivate()) {
                etsyCardUtil.f3702d.setIcon(StandardFontIcon.PRIVATE);
                etsyCardUtil.f3702d.setVisibility(0);
            } else {
                etsyCardUtil.f3702d.setVisibility(8);
            }
            OnClickListener etsyCardUtil2 = new EtsyCardUtil(this, new ITrackedObject[]{collection}, fragment, collection);
            int listingsCount = collection.getListingsCount();
            etsyCardUtil.f3703e.setText(m5112c().getQuantityString(R.item_titlecase_quantity, listingsCount, new Object[]{bh.m3333a((double) listingsCount)}));
            etsyCardUtil.f3700b.setOnClickListener(etsyCardUtil2);
            if (i > 0) {
                m5108a(etsyCardUtil.f3704f, collection.getRepresentativeListings(), i);
            }
        }
    }

    @CallSuper
    public void m5115a(Activity activity) {
        this.f3696e = new WeakReference(activity);
    }
}
