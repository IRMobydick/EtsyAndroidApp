package com.etsy.android.uikit.share;

import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.TextView;
import com.appboy.push.AppboyNotificationActionUtils;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import com.etsy.android.lib.models.shopshare.ShareItem;
import com.etsy.android.lib.util.bh;
import com.etsy.android.uikit.adapter.AnnotationAdapter;
import com.etsy.android.uikit.view.TaggableImageView;
import java.util.Locale;

public class SocialShareShopShareBrokerFragment extends SocialShareBrokerFragment {
    public static final int TWITTER_CHARACTER_LIMIT = 117;
    private ShareItem mShareItem;
    private String mShopName;

    /* renamed from: com.etsy.android.uikit.share.SocialShareShopShareBrokerFragment.1 */
    class C09521 implements OnGlobalLayoutListener {
        final /* synthetic */ TaggableImageView f4018a;
        final /* synthetic */ SocialShareShopShareBrokerFragment f4019b;

        C09521(SocialShareShopShareBrokerFragment socialShareShopShareBrokerFragment, TaggableImageView taggableImageView) {
            this.f4019b = socialShareShopShareBrokerFragment;
            this.f4018a = taggableImageView;
        }

        public void onGlobalLayout() {
            this.f4018a.setAdapter(new AnnotationAdapter(this.f4019b.getActivity(), this.f4018a.getImageView(), this.f4019b.mShareItem));
            this.f4018a.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        }
    }

    protected void readArguments(@NonNull Bundle bundle) {
        super.readArguments(bundle);
        this.mShareItem = (ShareItem) bundle.getSerializable("shop_share");
        this.mShopName = (String) bundle.getSerializable(ActivityFeedEntity.SHOP);
        this.mText = this.mShareItem.getText();
        this.mSubject = getString(R.post_subject, this.mShopName);
        this.mUrl = this.mShareItem.getUrl();
        this.mImageUrl = this.mShareItem.getPrimaryMedia().getImage().getUrl();
        this.mType = AppboyNotificationActionUtils.IMAGE_MIME_TYPE;
    }

    public View onCreateHeaderView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.fragment_social_share_shop_share_header, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        TaggableImageView taggableImageView = (TaggableImageView) view.findViewById(R.save_share_image);
        new ImageBatch().m1567a(this.mShareItem.getPrimaryMedia().getImage(), taggableImageView.getImageView());
        taggableImageView.getViewTreeObserver().addOnGlobalLayoutListener(new C09521(this, taggableImageView));
        ((TextView) view.findViewById(R.social_share_title)).setText(R.share_post);
        TextView textView = (TextView) view.findViewById(R.social_share_message);
        textView.setText(R.share_post_message);
        textView.setVisibility(0);
    }

    protected SocialShareData onBeforeShare(ResolveInfo resolveInfo, SocialShareData socialShareData) {
        if (ShareBrokerFragment.isFacebook(resolveInfo)) {
            socialShareData.m5402e(AppboyNotificationActionUtils.TEXT_MIME_TYPE).m5393a(null).m5395b(null).m5399d(null);
        } else if (ShareBrokerFragment.isTwitter(resolveInfo)) {
            String a = bh.m3336a(socialShareData.m5396b(), 117 - socialShareData.m5398c().length());
            socialShareData.m5402e(AppboyNotificationActionUtils.TEXT_MIME_TYPE).m5395b(String.format("%s %s", new Object[]{a, socialShareData.m5398c()}));
        } else if (isEmailLike(resolveInfo)) {
            socialShareData.m5402e(AppboyNotificationActionUtils.TEXT_MIME_TYPE).m5395b(getString(R.post_email_body, socialShareData.m5398c()));
        } else if (ShareBrokerFragment.isTumblr(resolveInfo)) {
            socialShareData.m5402e(AppboyNotificationActionUtils.TEXT_MIME_TYPE);
            if (socialShareData.m5396b().toLowerCase(Locale.US).contains("http")) {
                socialShareData.m5395b(String.format("%s %s", new Object[]{socialShareData.m5398c(), socialShareData.m5396b()}));
            } else {
                socialShareData.m5395b(String.format("%s%s", new Object[]{socialShareData.m5396b(), socialShareData.m5398c()}));
            }
        } else if (ShareBrokerFragment.isWhatsApp(resolveInfo)) {
            socialShareData.m5402e(AppboyNotificationActionUtils.TEXT_MIME_TYPE).m5395b(String.format("%s: %s", new Object[]{socialShareData.m5394a(), socialShareData.m5398c()}));
        } else if (isSms(resolveInfo)) {
            socialShareData.m5402e(AppboyNotificationActionUtils.TEXT_MIME_TYPE).m5395b(String.format("%s: %s", new Object[]{socialShareData.m5394a(), socialShareData.m5398c()})).m5393a(null);
        }
        return socialShareData;
    }

    protected void onShareComplete() {
    }

    @NonNull
    public String getTrackingName() {
        return "shop_shares_share";
    }
}
