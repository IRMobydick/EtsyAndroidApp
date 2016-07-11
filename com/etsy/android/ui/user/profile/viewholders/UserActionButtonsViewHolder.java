package com.etsy.android.ui.user.profile.viewholders;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import com.etsy.android.iconsy.AbstractFontIcon;
import com.etsy.android.iconsy.views.IconDrawable;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.apiv3.UserProfileV3;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.util.fonts.EtsyFontIcons;
import com.etsy.android.ui.nav.EtsyActivityNavigator;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.ui.util.EtsyFollowUtil;
import com.etsy.android.uikit.util.FollowUtil;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.ProgressButton;

public class UserActionButtonsViewHolder extends ViewHolder {
    final ProgressButton mFollowButton;
    private final ProgressButton mMessageButton;

    /* renamed from: com.etsy.android.ui.user.profile.viewholders.UserActionButtonsViewHolder.1 */
    class C08961 extends TrackingOnClickListener {
        final /* synthetic */ UserProfileV3 f3623a;
        final /* synthetic */ FragmentActivity f3624b;
        final /* synthetic */ UserActionButtonsViewHolder f3625c;

        C08961(UserActionButtonsViewHolder userActionButtonsViewHolder, UserProfileV3 userProfileV3, FragmentActivity fragmentActivity) {
            this.f3625c = userActionButtonsViewHolder;
            this.f3623a = userProfileV3;
            this.f3624b = fragmentActivity;
        }

        public void onViewClick(View view) {
            String loginName = this.f3623a.getLoginName();
            if (aj.m1101a().m1118d()) {
                Bundle bundle = new Bundle();
                bundle.putString("username", loginName);
                Nav.m4682a(this.f3624b).m4683a().m4516d(bundle);
                return;
            }
            bundle = new Bundle();
            bundle.putString("username", loginName);
            ((EtsyActivityNavigator) Nav.m4682a(this.f3624b).m4683a().m3012a(view)).m4452a(EtsyAction.CONTACT_USER, bundle);
        }
    }

    /* renamed from: com.etsy.android.ui.user.profile.viewholders.UserActionButtonsViewHolder.2 */
    class C08982 extends TrackingOnClickListener {
        final /* synthetic */ EtsyFollowUtil f3627a;
        final /* synthetic */ UserProfileV3 f3628b;
        final /* synthetic */ FragmentActivity f3629c;
        final /* synthetic */ UserActionButtonsViewHolder f3630d;

        /* renamed from: com.etsy.android.ui.user.profile.viewholders.UserActionButtonsViewHolder.2.1 */
        class C08971 implements FollowUtil {
            final /* synthetic */ C08982 f3626a;

            C08971(C08982 c08982) {
                this.f3626a = c08982;
            }

            public void m5075a() {
                if (this.f3626a.f3630d.mFollowButton != null) {
                    this.f3626a.f3630d.mFollowButton.hideLoading();
                    this.f3626a.f3628b.setIsFollowing(true);
                    this.f3626a.f3630d.updateFollowButtonState(true);
                }
            }

            public void m5076b() {
                if (this.f3626a.f3630d.mFollowButton != null) {
                    this.f3626a.f3630d.mFollowButton.hideLoading();
                    this.f3626a.f3628b.setIsFollowing(false);
                    this.f3626a.f3630d.updateFollowButtonState(false);
                }
            }
        }

        C08982(UserActionButtonsViewHolder userActionButtonsViewHolder, EtsyFollowUtil etsyFollowUtil, UserProfileV3 userProfileV3, FragmentActivity fragmentActivity) {
            this.f3630d = userActionButtonsViewHolder;
            this.f3627a = etsyFollowUtil;
            this.f3628b = userProfileV3;
            this.f3629c = fragmentActivity;
        }

        public void onViewClick(View view) {
            if (aj.m1101a().m1118d()) {
                boolean z;
                EtsyFollowUtil etsyFollowUtil = this.f3627a;
                EtsyId userId = this.f3628b.getUserId();
                if (this.f3628b.isFollowing()) {
                    z = false;
                } else {
                    z = true;
                }
                etsyFollowUtil.m5130a(userId, z, new C08971(this));
                return;
            }
            ((EtsyActivityNavigator) Nav.m4682a(this.f3629c).m4683a().m3012a(view)).m4453a(EtsyAction.FOLLOW, String.valueOf(this.f3628b.getUserId()));
        }
    }

    public UserActionButtonsViewHolder(View view) {
        super(view);
        this.mFollowButton = (ProgressButton) view.findViewById(2131756474);
        this.mMessageButton = (ProgressButton) view.findViewById(2131756475);
        this.mMessageButton.setText((int) R.convo_message_hint);
        setIcon(view.getContext().getApplicationContext(), this.mMessageButton, EtsyFontIcons.CONVERSATIONS);
    }

    public void bind(UserProfileV3 userProfileV3, FragmentActivity fragmentActivity) {
        updateFollowButtonState(userProfileV3.isFollowing());
        ((View) this.mMessageButton.getParent()).setOnClickListener(new C08961(this, userProfileV3, fragmentActivity));
        ((View) this.mFollowButton.getParent()).setOnClickListener(new C08982(this, new EtsyFollowUtil(fragmentActivity, this, "people_account"), userProfileV3, fragmentActivity));
    }

    private static void setIcon(Context context, ProgressButton progressButton, AbstractFontIcon abstractFontIcon) {
        Resources resources = context.getResources();
        progressButton.setDrawableLeft(IconDrawable.m775a(resources).m780a(abstractFontIcon).m779a(resources.getColor(R.grey)).m778a((float) resources.getDimensionPixelSize(R.text_xlarge)).m777a());
        progressButton.setDrawablePadding(resources.getDimensionPixelOffset(R.padding_medium));
    }

    void updateFollowButtonState(boolean z) {
        this.mFollowButton.setText(z ? R.following : R.follow);
        setIcon(this.itemView.getContext(), this.mFollowButton, z ? EtsyFontIcons.FOLLOWING_USER : EtsyFontIcons.FOLLOW_USER);
    }
}
