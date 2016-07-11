package com.etsy.android.ui.util;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.iconsy.views.IconDrawable;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.EventTracker;
import com.etsy.android.lib.logger.ITrackedObject;
import com.etsy.android.lib.logger.ad;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.Shop;
import com.etsy.android.lib.models.User;
import com.etsy.android.lib.models.apiv3.ShopIcon;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.util.bh;
import com.etsy.android.lib.util.fonts.EtsyFontIcons;
import com.etsy.android.ui.nav.EtsyActivityNavigator;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.util.FollowUtil;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.RatingIconView;
import java.lang.ref.WeakReference;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.etsy.android.ui.util.y */
public class OverlapHeaderHelper {
    private final int f3782a;
    private final int f3783b;
    private final String f3784c;
    private final boolean f3785d;
    private WeakReference<Activity> f3786e;
    private ImageBatch f3787f;
    private boolean f3788g;
    private FavoriteUtil f3789h;
    private final FavoriteUtil f3790i;
    private boolean f3791j;
    private FollowUtil f3792k;
    private final FollowUtil f3793l;
    private View f3794m;
    private View f3795n;
    private ImageView f3796o;
    private TextView f3797p;
    private TextView f3798q;
    private View f3799r;
    private TextView f3800s;
    private RatingIconView f3801t;
    private View f3802u;
    private View f3803v;
    private ImageView f3804w;
    private Button f3805x;

    /* renamed from: com.etsy.android.ui.util.y.1 */
    class OverlapHeaderHelper implements FavoriteUtil {
        final /* synthetic */ OverlapHeaderHelper f3766a;

        OverlapHeaderHelper(OverlapHeaderHelper overlapHeaderHelper) {
            this.f3766a = overlapHeaderHelper;
        }

        public void m5203a() {
            this.f3766a.f3788g = true;
            m5202a(2130837940);
        }

        public void m5204b() {
            this.f3766a.f3788g = false;
            m5202a(2130837894);
        }

        private void m5202a(int i) {
            if (this.f3766a.f3804w != null) {
                this.f3766a.f3804w.setImageResource(i);
            }
        }
    }

    /* renamed from: com.etsy.android.ui.util.y.2 */
    class OverlapHeaderHelper implements FollowUtil {
        final /* synthetic */ OverlapHeaderHelper f3767a;

        OverlapHeaderHelper(OverlapHeaderHelper overlapHeaderHelper) {
            this.f3767a = overlapHeaderHelper;
        }

        public void m5205a() {
            this.f3767a.f3791j = true;
            if (this.f3767a.f3805x != null) {
                this.f3767a.f3805x.setText(R.unfollow);
            }
        }

        public void m5206b() {
            this.f3767a.f3791j = false;
            if (this.f3767a.f3805x != null) {
                this.f3767a.f3805x.setText(R.follow);
            }
        }
    }

    /* renamed from: com.etsy.android.ui.util.y.3 */
    class OverlapHeaderHelper extends TrackingOnClickListener {
        final /* synthetic */ User f3768a;
        final /* synthetic */ Shop f3769b;
        final /* synthetic */ OverlapHeaderHelper f3770c;

        OverlapHeaderHelper(OverlapHeaderHelper overlapHeaderHelper, ITrackedObject[] iTrackedObjectArr, User user, Shop shop) {
            this.f3770c = overlapHeaderHelper;
            this.f3768a = user;
            this.f3769b = shop;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            if (this.f3768a == null || !this.f3768a.getUserId().hasId()) {
                Nav.m4681a((Activity) this.f3770c.f3786e.get()).m4501b(this.f3769b.getShopId());
            } else {
                Nav.m4681a((Activity) this.f3770c.f3786e.get()).m4473a(this.f3769b.getShopId(), this.f3768a.getUserId());
            }
            EventTracker.m2032b(this.f3770c.f3784c);
        }
    }

    /* renamed from: com.etsy.android.ui.util.y.4 */
    class OverlapHeaderHelper extends TrackingOnClickListener {
        final /* synthetic */ Shop f3771a;
        final /* synthetic */ OverlapHeaderHelper f3772b;

        OverlapHeaderHelper(OverlapHeaderHelper overlapHeaderHelper, ITrackedObject[] iTrackedObjectArr, Shop shop) {
            this.f3772b = overlapHeaderHelper;
            this.f3771a = shop;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            if (aj.m1101a().m1118d()) {
                this.f3772b.f3789h.m5168a(this.f3772b.f3804w, 2130837894, 2130837940, this.f3772b.f3788g);
                this.f3772b.f3789h.m5171a(this.f3771a, this.f3772b.f3790i, this.f3772b.f3788g);
                return;
            }
            ((EtsyActivityNavigator) Nav.m4681a((Activity) this.f3772b.f3786e.get()).m3012a(view)).m4453a(EtsyAction.FAVORITE, String.valueOf(this.f3771a.getUser().getUserId()));
        }
    }

    /* renamed from: com.etsy.android.ui.util.y.5 */
    class OverlapHeaderHelper extends TrackingOnClickListener {
        final /* synthetic */ User f3773a;
        final /* synthetic */ OverlapHeaderHelper f3774b;

        OverlapHeaderHelper(OverlapHeaderHelper overlapHeaderHelper, ITrackedObject[] iTrackedObjectArr, User user) {
            this.f3774b = overlapHeaderHelper;
            this.f3773a = user;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            Nav.m4681a((Activity) this.f3774b.f3786e.get()).m4511c(this.f3773a.getUserId());
        }
    }

    /* renamed from: com.etsy.android.ui.util.y.6 */
    class OverlapHeaderHelper extends TrackingOnClickListener {
        final /* synthetic */ User f3775a;
        final /* synthetic */ OverlapHeaderHelper f3776b;

        OverlapHeaderHelper(OverlapHeaderHelper overlapHeaderHelper, ITrackedObject[] iTrackedObjectArr, User user) {
            this.f3776b = overlapHeaderHelper;
            this.f3775a = user;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            if (aj.m1101a().m1118d()) {
                this.f3776b.f3805x.setText(this.f3776b.f3791j ? R.follow : R.unfollow);
                this.f3776b.f3792k.m5127a(this.f3775a.getUserId(), !this.f3776b.f3791j, this.f3776b.f3793l);
                return;
            }
            ((EtsyActivityNavigator) Nav.m4681a((Activity) this.f3776b.f3786e.get()).m3012a(view)).m4453a(EtsyAction.FOLLOW, this.f3775a.getUserId().getId());
        }
    }

    /* renamed from: com.etsy.android.ui.util.y.7 */
    class OverlapHeaderHelper extends TrackingOnClickListener {
        final /* synthetic */ User f3777a;
        final /* synthetic */ String f3778b;
        final /* synthetic */ String f3779c;
        final /* synthetic */ String f3780d;
        final /* synthetic */ OverlapHeaderHelper f3781e;

        OverlapHeaderHelper(OverlapHeaderHelper overlapHeaderHelper, ITrackedObject[] iTrackedObjectArr, User user, String str, String str2, String str3) {
            this.f3781e = overlapHeaderHelper;
            this.f3777a = user;
            this.f3778b = str;
            this.f3779c = str2;
            this.f3780d = str3;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            EventTracker.m2018a(this.f3777a.getUserId(), this.f3781e.f3784c);
            Bundle bundle = new Bundle();
            bundle.putString("username", this.f3778b);
            bundle.putString(ResponseConstants.SUBJECT, this.f3779c);
            bundle.putString(ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE, this.f3780d);
            if (aj.m1101a().m1118d()) {
                Nav.m4681a((Activity) this.f3781e.f3786e.get()).m4516d(bundle);
            } else {
                ((EtsyActivityNavigator) Nav.m4681a((Activity) this.f3781e.f3786e.get()).m3012a(view)).m4452a(EtsyAction.CONTACT_USER, bundle);
            }
        }
    }

    public OverlapHeaderHelper(Resources resources, String str, boolean z) {
        this.f3786e = null;
        this.f3788g = false;
        this.f3790i = new OverlapHeaderHelper(this);
        this.f3791j = false;
        this.f3793l = new OverlapHeaderHelper(this);
        this.f3784c = str;
        this.f3785d = z;
        this.f3782a = resources.getDimensionPixelSize(R.gen_avatar_corners_small);
        this.f3783b = resources.getDimensionPixelSize(2131361934);
    }

    public void m5227a(View view, View view2) {
        this.f3794m = view;
        this.f3795n = view2;
        this.f3796o = (ImageView) view.findViewById(2131756402);
        this.f3797p = (TextView) view.findViewById(2131755888);
        this.f3798q = (TextView) view.findViewById(2131755889);
        this.f3803v = view.findViewById(2131756404);
        this.f3805x = (Button) view.findViewById(2131756403);
        this.f3804w = (ImageView) view.findViewById(2131756405);
        this.f3802u = view.findViewById(2131756406);
        this.f3799r = view.findViewById(2131755347);
        this.f3800s = (TextView) view.findViewById(R.rating_count);
        this.f3801t = (RatingIconView) view.findViewById(R.shop_rating);
    }

    public void m5226a(Activity activity, ImageBatch imageBatch, @NonNull ad adVar) {
        this.f3786e = new WeakReference(activity);
        this.f3787f = imageBatch;
        this.f3789h = new FavoriteUtil(activity, this, this.f3784c, adVar);
        this.f3792k = new FollowUtil(activity, this, this.f3784c);
    }

    private ImageBatch m5216c() {
        return this.f3787f;
    }

    public void m5229a(User user, Shop shop, String str, String str2) {
        m5233b(user, shop);
        if (this.f3785d) {
            m5214b(user, str, str2);
        }
    }

    public void m5228a(User user, Shop shop) {
        m5233b(user, shop);
        this.f3798q.setVisibility(8);
        this.f3799r.setVisibility(0);
        this.f3800s.setText(((Activity) this.f3786e.get()).getResources().getQuantityString(R.reviews_plurals_no_brackets, shop.getNumRatings(), new Object[]{bh.m3333a((double) shop.getNumRatings())}));
        if (shop.getAverageRating() > 0.0d) {
            this.f3801t.setRating((float) shop.getAverageRating());
        } else {
            this.f3801t.setVisibility(8);
        }
    }

    public void m5233b(User user, Shop shop) {
        this.f3795n.setVisibility(0);
        this.f3794m.setVisibility(0);
        m5209a(shop, user, false);
        this.f3797p.setText(shop.getShopName());
        m5213b(user);
        if (shop.getShopId().hasId()) {
            this.f3795n.setOnClickListener(new OverlapHeaderHelper(this, new ITrackedObject[]{shop}, user, shop));
        }
        if (this.f3785d) {
            m5208a(shop);
        }
    }

    public void m5231a(EtsyId etsyId) {
        this.f3789h.m5169a(etsyId, this.f3790i);
    }

    private void m5208a(Shop shop) {
        if (shop.getUser() == null || !shop.getUser().getUserId().hasId()) {
            this.f3803v.setVisibility(8);
            return;
        }
        this.f3803v.setVisibility(0);
        this.f3803v.setOnClickListener(new OverlapHeaderHelper(this, new ITrackedObject[]{shop}, shop));
    }

    public void m5230a(User user, String str, String str2) {
        this.f3795n.setVisibility(0);
        this.f3794m.setVisibility(0);
        CharSequence a = bh.m3334a(user);
        m5209a(null, user, true);
        this.f3797p.setText(a);
        m5213b(user);
        if (user.getUserId().hasId()) {
            this.f3795n.setOnClickListener(new OverlapHeaderHelper(this, new ITrackedObject[]{user}, user));
        }
        if (this.f3785d) {
            m5210a(user);
            m5214b(user, str, str2);
        }
    }

    private void m5210a(User user) {
        this.f3803v.setVisibility(8);
        if (user != null && user.getUserId().hasId()) {
            this.f3805x.setVisibility(0);
            this.f3792k.m5126a(user.getUserId(), this.f3793l);
            this.f3805x.setOnClickListener(new OverlapHeaderHelper(this, new ITrackedObject[]{user}, user));
        }
    }

    private void m5209a(Shop shop, User user, boolean z) {
        String str = null;
        if (shop != null && bh.m3340a(shop.getIconUrl(((Integer) ShopIcon.IMG_SIZE_75.first).intValue()))) {
            str = shop.getIconUrl(((Integer) ShopIcon.IMG_SIZE_75.first).intValue());
        } else if (!(user == null || user.getProfile() == null)) {
            str = user.getProfile().getImageUrl75x75();
        }
        if (str == null) {
            return;
        }
        if (z) {
            this.f3796o.setBackgroundResource(R.bg_avatar_circle_small_borderless);
            m5216c().m1574b(str, this.f3796o, this.f3783b);
            return;
        }
        m5216c().m1576b(str, this.f3796o, this.f3782a, this.f3783b, this.f3783b);
    }

    private void m5213b(User user) {
        String str;
        String str2 = StringUtils.EMPTY;
        if (user == null || user.getProfile() == null) {
            str = str2;
        } else {
            str = bh.m3335a(user.getProfile());
        }
        if (bh.m3340a(str)) {
            IconDrawable a = IconDrawable.m775a(((Activity) this.f3786e.get()).getResources()).m780a(EtsyFontIcons.LOCATION).m779a(((Activity) this.f3786e.get()).getResources().getColor(R.light_grey)).m778a((float) ((Activity) this.f3786e.get()).getResources().getDimensionPixelSize(R.text_small));
            this.f3798q.setCompoundDrawablePadding(((Activity) this.f3786e.get()).getResources().getDimensionPixelSize(R.fixed_medium));
            this.f3798q.setCompoundDrawablesWithIntrinsicBounds(a.m777a(), null, null, null);
            this.f3798q.setText(str);
            return;
        }
        this.f3798q.setVisibility(8);
    }

    public void m5225a() {
        this.f3802u.setVisibility(4);
        this.f3795n.setOnClickListener(null);
    }

    private void m5214b(User user, String str, String str2) {
        this.f3802u.setVisibility(0);
        String loginName = user.getLoginName();
        this.f3802u.setOnClickListener(new OverlapHeaderHelper(this, new ITrackedObject[]{user}, user, loginName, str, str2));
    }

    public void m5232b() {
        this.f3794m = null;
        this.f3795n = null;
        this.f3796o = null;
        this.f3797p = null;
        this.f3798q = null;
        this.f3803v = null;
        this.f3805x = null;
        this.f3804w = null;
        this.f3802u = null;
        this.f3799r = null;
        this.f3800s = null;
        this.f3801t = null;
    }
}
