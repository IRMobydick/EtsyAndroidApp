package com.etsy.android.ui.user;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.Button;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.EtsyJobResponse;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.HttpRequestJobBuilder;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.ShortenedUrl;
import com.etsy.android.lib.models.Transaction;
import com.etsy.android.lib.models.apiv3.AppreciationPhoto;
import com.etsy.android.lib.util.aa;
import com.etsy.android.lib.util.ab;
import com.etsy.android.lib.util.bl;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.ui.nav.EtsyEventTracker;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.util.AnimationUtil;
import com.etsy.android.uikit.util.HardwareAnimatorListener;
import com.etsy.android.uikit.util.SocialShareUtil;
import com.etsy.android.uikit.util.SocialShareUtil.ShareType;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.FullImageView;
import java.util.List;

public class ShareFeedbackFragment extends EtsyFragment {
    private AppreciationPhoto mAppreciationPhoto;
    private OnClickListener mClickListener;
    private RelativeLayout mShareFeedbackContainer;
    private TextView mShareFeedbackCta;
    private ImageView mShareFeedbackDismissButton;
    private ImageView mShareFeedbackPhoto;
    private TextView mShareFeedbackReason;
    private Button mShareFeedbackShareButton;
    private Transaction mTransaction;

    /* renamed from: com.etsy.android.ui.user.ShareFeedbackFragment.1 */
    class C08371 implements EtsyJobResponse<ShortenedUrl> {
        final /* synthetic */ ShareFeedbackFragment f3476a;

        C08371(ShareFeedbackFragment shareFeedbackFragment) {
            this.f3476a = shareFeedbackFragment;
        }

        public void m5013a(List<ShortenedUrl> list, int i, EtsyResult<ShortenedUrl> etsyResult) {
            if (this.f3476a.mAppreciationPhoto.getShortenedShareUrl() != null) {
                this.f3476a.mAppreciationPhoto.setShortenedShareUrl((ShortenedUrl) list.get(0));
            }
        }
    }

    /* renamed from: com.etsy.android.ui.user.ShareFeedbackFragment.2 */
    class C08382 implements OnPreDrawListener {
        final /* synthetic */ ShareFeedbackFragment f3477a;

        C08382(ShareFeedbackFragment shareFeedbackFragment) {
            this.f3477a = shareFeedbackFragment;
        }

        public boolean onPreDraw() {
            ((LayoutParams) this.f3477a.mShareFeedbackContainer.getLayoutParams()).setMargins(0, ((new ab(this.f3477a.getActivity()).m3183f() - ab.m3177b(this.f3477a.getActivity())) - this.f3477a.mShareFeedbackContainer.getHeight()) / 2, 0, 0);
            this.f3477a.mShareFeedbackContainer.getViewTreeObserver().removeOnPreDrawListener(this);
            this.f3477a.mShareFeedbackContainer.setVisibility(0);
            this.f3477a.startIntroductionAnimation();
            return true;
        }
    }

    /* renamed from: com.etsy.android.ui.user.ShareFeedbackFragment.3 */
    class C08393 extends HardwareAnimatorListener {
        final /* synthetic */ ShareFeedbackFragment f3478a;

        C08393(ShareFeedbackFragment shareFeedbackFragment, View view) {
            this.f3478a = shareFeedbackFragment;
            super(view);
        }

        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            this.f3478a.mShareFeedbackCta.setVisibility(8);
            ((LayoutParams) this.f3478a.mShareFeedbackContainer.getLayoutParams()).setMargins(0, 0, 0, 0);
            this.f3478a.getImageBatch().m1568a(this.f3478a.mAppreciationPhoto.getImageUrl(), this.f3478a.mShareFeedbackPhoto);
            AnimationUtil.m5521a(this.f3478a.mShareFeedbackReason, 300);
            AnimationUtil.m5521a(this.f3478a.mShareFeedbackDismissButton, 300);
            AnimationUtil.m5521a(this.f3478a.mShareFeedbackPhoto, 300);
            AnimationUtil.m5521a(this.f3478a.mShareFeedbackShareButton, 300);
        }
    }

    /* renamed from: com.etsy.android.ui.user.ShareFeedbackFragment.4 */
    class C08404 extends TrackingOnClickListener {
        final /* synthetic */ ShareFeedbackFragment f3479a;

        C08404(ShareFeedbackFragment shareFeedbackFragment) {
            this.f3479a = shareFeedbackFragment;
        }

        public void onPreTrack() {
            addEventTrackedObjects(this.f3479a.mTransaction);
        }

        public void onViewClick(View view) {
            if (view.getId() == 2131755796) {
                SocialShareUtil.m5156a(this.f3479a.getActivity().getLocalClassName(), ShareType.APPRECIATION_PHOTO, this.f3479a.mTransaction.getTransactionId().getId());
                Nav.m4682a(this.f3479a.getActivity()).m4683a().m4479a(this.f3479a.mAppreciationPhoto);
                EtsyEventTracker.m4563b(this.f3479a.mTransaction.getTransactionId());
            } else if (view.getId() == 2131755798) {
                EtsyEventTracker.m4569c(this.f3479a.mTransaction.getTransactionId());
                if (this.f3479a.getActivity() instanceof BOENavDrawerActivity) {
                    ((BOENavDrawerActivity) this.f3479a.getActivity()).popOrGoBack();
                } else {
                    this.f3479a.getActivity().finish();
                }
            }
        }
    }

    public ShareFeedbackFragment() {
        this.mClickListener = new C08404(this);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mTransaction = (Transaction) getArguments().getSerializable("transaction");
        if (bundle != null) {
            this.mAppreciationPhoto = (AppreciationPhoto) bundle.getSerializable(ResponseConstants.APPRECIATION_PHOTO);
        } else {
            this.mAppreciationPhoto = this.mTransaction.getReview().getAppreciationPhoto();
            this.mAppreciationPhoto.setShopName(getArguments().getString(ResponseConstants.SHOP_NAME, getString(R.review_share_default_shopname)));
            this.mAppreciationPhoto.setShortenedShareUrl(new ShortenedUrl(AppreciationPhoto.buildShareUrl(this.mTransaction.getTransactionId())));
            this.mAppreciationPhoto.setSellerAvatarUrl(getArguments().getString("seller_avatar_url"));
            this.mAppreciationPhoto.setListingTitle(this.mTransaction.getTitle());
        }
        if (!this.mAppreciationPhoto.getShortenedShareUrl().isShortened()) {
            getRequestQueue().m1697a((Object) this, HttpRequestJobBuilder.m1712a(ShortenedUrl.class, "/etsyapps/v3/public/shorten-url").m1744a(ResponseConstants.URL, this.mAppreciationPhoto.getShortenedShareUrl().getLongUrl()).m1743a(new C08371(this)).m1737a());
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2;
        ViewGroup viewGroup3 = (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle);
        if (viewGroup3 == null) {
            viewGroup2 = (ViewGroup) layoutInflater.inflate(2130903229, viewGroup, false);
        } else {
            viewGroup2 = viewGroup3;
        }
        this.mShareFeedbackDismissButton = (ImageView) viewGroup2.findViewById(2131755798);
        this.mShareFeedbackDismissButton.setOnClickListener(this.mClickListener);
        this.mShareFeedbackShareButton = (Button) viewGroup2.findViewById(2131755796);
        this.mShareFeedbackShareButton.setOnClickListener(this.mClickListener);
        ImageView imageView = (ImageView) viewGroup2.findViewById(2131755797);
        if (!TextUtils.isEmpty(this.mAppreciationPhoto.getSellerAvatarUrl())) {
            getImageBatch().m1568a(this.mAppreciationPhoto.getSellerAvatarUrl(), imageView);
        }
        this.mShareFeedbackPhoto = (ImageView) viewGroup2.findViewById(2131755795);
        this.mShareFeedbackReason = (TextView) viewGroup2.findViewById(2131755794);
        this.mShareFeedbackCta = (TextView) viewGroup2.findViewById(2131755793);
        this.mShareFeedbackCta.setText(String.format(getString(R.review_share_cta), new Object[]{this.mAppreciationPhoto.getShopName()}));
        if (aa.m3167a()) {
            bl.m3360a(viewGroup2);
        }
        this.mShareFeedbackContainer = (RelativeLayout) viewGroup2.findViewById(2131755792);
        this.mShareFeedbackContainer.getViewTreeObserver().addOnPreDrawListener(new C08382(this));
        return viewGroup2;
    }

    public void onDestroyView() {
        if (this.mShareFeedbackCta != null) {
            this.mShareFeedbackCta.animate().cancel();
        }
        super.onDestroyView();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable(ResponseConstants.APPRECIATION_PHOTO, this.mAppreciationPhoto);
    }

    private void startIntroductionAnimation() {
        AnimatorListener c08393 = new C08393(this, this.mShareFeedbackCta);
        this.mShareFeedbackCta.animate().cancel();
        this.mShareFeedbackCta.setAlpha(FullImageView.ASPECT_RATIO_SQUARE);
        this.mShareFeedbackCta.animate().setDuration(200).setStartDelay(3500).alpha(0.0f).setListener(c08393).start();
    }

    @NonNull
    public OnClickListener getOnClickListener() {
        return this.mClickListener;
    }
}
