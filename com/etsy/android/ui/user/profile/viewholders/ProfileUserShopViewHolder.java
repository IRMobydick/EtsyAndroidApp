package com.etsy.android.ui.user.profile.viewholders;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.models.apiv3.ShopCard;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.ClickableImageView;
import com.etsy.android.uikit.view.RatingIconView;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class ProfileUserShopViewHolder extends ViewHolder {
    private final TextView mHeader;
    private final String mHeaderText;
    private final TextView mNumberTransactions;
    private final TextView mOpenDate;
    private final SimpleDateFormat mOpenDateFormat;
    private final ClickableImageView mShopIcon;
    private final TextView mShopName;
    private final TextView mShopRatingCount;
    private final RatingIconView mShopRatingView;

    /* renamed from: com.etsy.android.ui.user.profile.viewholders.ProfileUserShopViewHolder.1 */
    class C08941 implements Runnable {
        final /* synthetic */ ShopCard f3618a;
        final /* synthetic */ ImageBatch f3619b;
        final /* synthetic */ ProfileUserShopViewHolder f3620c;

        C08941(ProfileUserShopViewHolder profileUserShopViewHolder, ShopCard shopCard, ImageBatch imageBatch) {
            this.f3620c = profileUserShopViewHolder;
            this.f3618a = shopCard;
            this.f3619b = imageBatch;
        }

        public void run() {
            this.f3620c.bindShopIcon(this.f3618a, this.f3619b);
        }
    }

    /* renamed from: com.etsy.android.ui.user.profile.viewholders.ProfileUserShopViewHolder.2 */
    class C08952 extends TrackingOnClickListener {
        final /* synthetic */ ShopCard f3621a;
        final /* synthetic */ ProfileUserShopViewHolder f3622b;

        C08952(ProfileUserShopViewHolder profileUserShopViewHolder, ShopCard shopCard) {
            this.f3622b = profileUserShopViewHolder;
            this.f3621a = shopCard;
        }

        public void onViewClick(View view) {
            Nav.m4681a((FragmentActivity) this.f3622b.itemView.getContext()).m4501b(this.f3621a.getShopId());
        }
    }

    public ProfileUserShopViewHolder(View view) {
        super(view);
        this.mOpenDateFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
        this.mShopName = (TextView) view.findViewById(R.shop_name);
        this.mShopIcon = (ClickableImageView) view.findViewById(R.shop_icon);
        this.mShopRatingView = (RatingIconView) view.findViewById(R.shop_rating);
        this.mShopRatingCount = (TextView) view.findViewById(R.rating_count);
        this.mHeader = (TextView) view.findViewById(2131756485);
        this.mOpenDate = (TextView) view.findViewById(2131756487);
        this.mNumberTransactions = (TextView) view.findViewById(2131756486);
        this.mHeaderText = view.getContext().getString(R.shop_info_header);
    }

    public void bind(ShopCard shopCard, String str, int i, boolean z, ImageBatch imageBatch) {
        this.mShopName.setText(shopCard.getShopName());
        if (z) {
            this.mHeader.setText(R.nav_shop);
        } else {
            this.mHeader.setText(String.format(this.mHeaderText, new Object[]{str}));
        }
        if (shopCard.hasIcon()) {
            this.mShopIcon.post(new C08941(this, shopCard, imageBatch));
            this.mShopIcon.setOnClickListener(new C08952(this, shopCard));
        } else {
            this.mShopIcon.setVisibility(8);
        }
        this.mNumberTransactions.setText(this.itemView.getContext().getResources().getQuantityString(R.sales_pl_nt, i, new Object[]{Integer.valueOf(i)}));
        this.mOpenDate.setText(this.itemView.getContext().getString(R.since, new Object[]{this.mOpenDateFormat.format(shopCard.getOpenDate())}));
        if (shopCard.getAverageRating() > 0.0d) {
            this.mShopRatingView.setRating((float) shopCard.getAverageRating());
            this.mShopRatingCount.setText("(" + shopCard.getNumRatings() + ")");
            return;
        }
        this.mShopRatingView.setVisibility(8);
        this.mShopRatingCount.setVisibility(8);
    }

    void bindShopIcon(ShopCard shopCard, ImageBatch imageBatch) {
        int width = this.mShopIcon.getWidth();
        int height = this.mShopIcon.getHeight();
        imageBatch.m1570a(ImageBatch.m1557a(width, height, shopCard.getIcon()), this.mShopIcon, width, height);
    }
}
