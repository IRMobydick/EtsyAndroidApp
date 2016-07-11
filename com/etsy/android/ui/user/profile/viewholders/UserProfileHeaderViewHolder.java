package com.etsy.android.ui.user.profile.viewholders;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.Layout;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.iconsy.views.IconDrawable;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.AnalyticsTracker;
import com.etsy.android.lib.models.apiv3.UserProfileV3;
import com.etsy.android.lib.util.bh;
import com.etsy.android.lib.util.fonts.EtsyFontIcons;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.text.ClickableSpanTouchListener;
import com.etsy.android.uikit.util.AdHocEventCompatBuilder;
import com.etsy.android.uikit.util.EtsyLinkify;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.text.NumberFormat;

public class UserProfileHeaderViewHolder extends ViewHolder {
    private final View mAdminBadge;
    private final ImageView mAvatar;
    private final int mAvatarWidth;
    private final View mCameraLayout;
    boolean mDidInitialBind;
    private final TextView mFollowersButton;
    private final TextView mFollowingButton;
    private final TextView mLocation;
    private final IconDrawable mLocationIcon;
    private final TextView mName;
    final TextView mUserBio;
    final View mUserBioReadMore;
    private final AnalyticsTracker mViewTracker;

    /* renamed from: com.etsy.android.ui.user.profile.viewholders.UserProfileHeaderViewHolder.1 */
    class C08991 extends TrackingOnClickListener {
        final /* synthetic */ UserProfileHeaderViewHolder f3631a;

        C08991(UserProfileHeaderViewHolder userProfileHeaderViewHolder) {
            this.f3631a = userProfileHeaderViewHolder;
        }

        public void onViewClick(View view) {
        }
    }

    /* renamed from: com.etsy.android.ui.user.profile.viewholders.UserProfileHeaderViewHolder.2 */
    class C09002 extends TrackingOnClickListener {
        final /* synthetic */ FragmentActivity f3632a;
        final /* synthetic */ UserProfileV3 f3633b;
        final /* synthetic */ UserProfileHeaderViewHolder f3634c;

        C09002(UserProfileHeaderViewHolder userProfileHeaderViewHolder, FragmentActivity fragmentActivity, UserProfileV3 userProfileV3) {
            this.f3634c = userProfileHeaderViewHolder;
            this.f3632a = fragmentActivity;
            this.f3633b = userProfileV3;
        }

        public void onViewClick(View view) {
            Nav.m4682a(this.f3632a).m4683a().m4475a(this.f3633b.getUserId(), this.f3633b.getLoginName(), 1);
        }
    }

    /* renamed from: com.etsy.android.ui.user.profile.viewholders.UserProfileHeaderViewHolder.3 */
    class C09013 extends TrackingOnClickListener {
        final /* synthetic */ FragmentActivity f3635a;
        final /* synthetic */ UserProfileV3 f3636b;
        final /* synthetic */ UserProfileHeaderViewHolder f3637c;

        C09013(UserProfileHeaderViewHolder userProfileHeaderViewHolder, FragmentActivity fragmentActivity, UserProfileV3 userProfileV3) {
            this.f3637c = userProfileHeaderViewHolder;
            this.f3635a = fragmentActivity;
            this.f3636b = userProfileV3;
        }

        public void onViewClick(View view) {
            Nav.m4682a(this.f3635a).m4683a().m4475a(this.f3636b.getUserId(), this.f3636b.getLoginName(), 0);
        }
    }

    /* renamed from: com.etsy.android.ui.user.profile.viewholders.UserProfileHeaderViewHolder.4 */
    class C09034 implements Runnable {
        final /* synthetic */ FragmentActivity f3639a;
        final /* synthetic */ String f3640b;
        final /* synthetic */ UserProfileHeaderViewHolder f3641c;

        /* renamed from: com.etsy.android.ui.user.profile.viewholders.UserProfileHeaderViewHolder.4.1 */
        class C09021 extends TrackingOnClickListener {
            final /* synthetic */ C09034 f3638a;

            C09021(C09034 c09034) {
                this.f3638a = c09034;
            }

            public void onViewClick(View view) {
                Nav.m4682a(this.f3638a.f3639a).m4683a().m4489a(this.f3638a.f3639a.getResources().getString(R.about), this.f3638a.f3640b);
            }
        }

        C09034(UserProfileHeaderViewHolder userProfileHeaderViewHolder, FragmentActivity fragmentActivity, String str) {
            this.f3641c = userProfileHeaderViewHolder;
            this.f3639a = fragmentActivity;
            this.f3640b = str;
        }

        public void run() {
            boolean z = true;
            this.f3641c.mDidInitialBind = true;
            Layout layout = this.f3641c.mUserBio.getLayout();
            if ((layout == null || layout.getLineCount() <= 2) && layout != null) {
                z = false;
            }
            if (z) {
                this.f3641c.mUserBioReadMore.setVisibility(0);
                this.f3641c.mUserBioReadMore.setOnClickListener(new C09021(this));
            }
        }
    }

    public UserProfileHeaderViewHolder(View view) {
        super(view);
        this.mViewTracker = AdHocEventCompatBuilder.m5508c(view);
        this.mAvatar = (ImageView) view.findViewById(R.avatar);
        this.mName = (TextView) view.findViewById(2131756479);
        this.mLocation = (TextView) view.findViewById(2131756480);
        this.mCameraLayout = view.findViewById(2131756478);
        this.mAdminBadge = view.findViewById(2131756477);
        this.mFollowersButton = (TextView) view.findViewById(2131756482);
        this.mFollowingButton = (TextView) view.findViewById(2131756481);
        this.mUserBio = (TextView) view.findViewById(2131756483);
        this.mUserBioReadMore = view.findViewById(2131756484);
        Resources resources = view.getResources();
        this.mAvatarWidth = resources.getDimensionPixelOffset(2131362037);
        this.mLocationIcon = IconDrawable.m775a(resources).m780a(EtsyFontIcons.LOCATION).m779a(resources.getColor(R.light_grey)).m778a((float) resources.getDimensionPixelSize(R.text_medium)).m777a();
    }

    public void bind(UserProfileV3 userProfileV3, boolean z, ImageBatch imageBatch, @NonNull FragmentActivity fragmentActivity) {
        if (userProfileV3 != null) {
            CharSequence displayName = userProfileV3.getDisplayName();
            String location = userProfileV3.getLocation();
            this.mAdminBadge.setVisibility(userProfileV3.isAdmin() ? 0 : 8);
            imageBatch.m1574b(userProfileV3.getAvatarUrl(), this.mAvatar, this.mAvatarWidth);
            this.mName.setText(displayName);
            if (z && this.mViewTracker.m1847c().m885c(EtsyConfigKeys.bm)) {
                this.mCameraLayout.setVisibility(0);
                this.mCameraLayout.setOnClickListener(new C08991(this));
            } else {
                this.mCameraLayout.setVisibility(8);
            }
            NumberFormat instance = NumberFormat.getInstance();
            Resources resources = fragmentActivity.getResources();
            this.mFollowersButton.setText(resources.getQuantityString(R.follower_counts, r3, new Object[]{instance.format((long) userProfileV3.getFollowerCount())}));
            this.mFollowersButton.setOnClickListener(new C09002(this, fragmentActivity, userProfileV3));
            int followingCount = userProfileV3.getFollowingCount();
            this.mFollowingButton.setText(resources.getString(R.following_count, new Object[]{instance.format((long) followingCount)}));
            this.mFollowingButton.setOnClickListener(new C09013(this, fragmentActivity, userProfileV3));
            if (!this.mDidInitialBind) {
                if (bh.m3340a(location)) {
                    this.mLocation.setVisibility(0);
                    this.mLocation.setText(location);
                    this.mLocation.setCompoundDrawablesWithIntrinsicBounds(this.mLocationIcon, null, null, null);
                } else {
                    this.mLocation.setVisibility(8);
                }
                Object trim = userProfileV3.getBio().trim();
                if (TextUtils.isEmpty(trim)) {
                    this.mUserBio.setVisibility(8);
                    return;
                }
                this.mUserBio.setVisibility(0);
                this.mUserBio.setText(trim);
                EtsyLinkify.m5482a((Context) fragmentActivity, this.mUserBio);
                this.mUserBio.setMovementMethod(null);
                this.mUserBio.setClickable(true);
                this.mUserBio.setOnTouchListener(new ClickableSpanTouchListener());
                this.mUserBio.post(new C09034(this, fragmentActivity, trim));
            }
        }
    }
}
