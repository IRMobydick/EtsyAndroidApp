package com.etsy.android.lib.models.shopedit;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import com.etsy.android.lib.models.EditStructuredPoliciesShopContext;
import com.etsy.android.lib.models.ShopAbout.Link;
import com.etsy.android.lib.models.ShopAboutImage;
import com.etsy.android.lib.models.ShopAboutMember;
import com.etsy.android.lib.models.ShopAboutVideo;
import com.etsy.android.lib.models.apiv3.FAQ;
import com.etsy.android.lib.models.apiv3.Image;
import com.etsy.android.lib.models.apiv3.SellerDetails;
import com.etsy.android.lib.models.apiv3.StructuredShopPolicies;
import com.etsy.android.lib.models.shopedit.switchrow.ShopEditVacationSwitchRow;
import java.util.List;

public interface ShopEditDelegate {
    void addAboutImage();

    void addFaq();

    void addLink(@NonNull List<Link> list);

    void addShopMember();

    void editAboutImage(@NonNull ShopAboutImage shopAboutImage);

    void editBasicShopField(@NonNull String str, @StringRes int i, @NonNull String str2, @NonNull String str3);

    void editCoverPhoto(@Nullable Image image, int i);

    void editFaq(@Nullable FAQ faq);

    void editLink(@NonNull Link link, @NonNull List<Link> list);

    void editSellerDetails(@Nullable SellerDetails sellerDetails);

    void editShopIcon(@Nullable Image image);

    void editShopMember(@NonNull ShopAboutMember shopAboutMember);

    void editShopVideo(@Nullable ShopAboutVideo shopAboutVideo, @NonNull ShopVideoShareData shopVideoShareData);

    void editStructuredPolicies(@Nullable StructuredShopPolicies structuredShopPolicies, @NonNull EditStructuredPoliciesShopContext editStructuredPoliciesShopContext);

    void goToShopRearrange();

    void reloadContent();

    void toggleVacationSwitch(@NonNull ShopEditVacationSwitchRow shopEditVacationSwitchRow);
}
