package com.etsy.android.lib.models.shopedit.contentrow;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ShopHomePage;
import com.etsy.android.lib.models.shopedit.ShopEditActionableItem;
import com.etsy.android.lib.models.shopedit.ShopEditDelegate;
import com.etsy.android.lib.models.shopedit.ShopEditPresentationItem;
import com.etsy.android.lib.models.shopedit.contentrow.ShopEditContentRow.Builder;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class ShopEditBasicFieldRow extends ShopEditComposedContentRow implements ShopEditActionableItem {
    public static final int FIELD_ABOUT_STORY = 5;
    public static final int FIELD_ABOUT_STORY_HEADLINE = 4;
    public static final int FIELD_MESSAGE_TO_BUYERS = 3;
    public static final int FIELD_POLICY_ADDITIONAL = 10;
    public static final int FIELD_POLICY_PAYMENT = 7;
    public static final int FIELD_POLICY_REFUND = 8;
    public static final int FIELD_POLICY_SHIPPING = 9;
    public static final int FIELD_POLICY_WELCOME = 6;
    public static final int FIELD_SHOP_ANNOUNCEMENT = 2;
    public static final int FIELD_SHOP_TITLE = 1;
    public static final int FIELD_VACATION_AUTOREPLY = 12;
    public static final int FIELD_VACATION_MESSAGE = 11;
    @NonNull
    ShopEditContentRow mContentRow;
    @NonNull
    EditInfo mEditInfo;
    int mFieldType;

    @Parcel
    public class EditInfo {
        String mApiField;
        CharSequence mContent;
        CharSequence mHint;
        String mPageInView;
        int mPageTitleRes;
        CharSequence mTitle;

        EditInfo() {
        }

        EditInfo(@NonNull ShopHomePage shopHomePage, int i, @NonNull Context context) {
            int i2;
            int i3;
            CharSequence headline;
            String str;
            String str2;
            Object storyHeadline;
            switch (i) {
                case ShopEditBasicFieldRow.FIELD_SHOP_TITLE /*1*/:
                    i2 = R.shop_title_sentence;
                    i3 = R.shop_title_hint;
                    headline = shopHomePage.getShop().getHeadline();
                    str = "shop_title_edit";
                    str2 = ResponseConstants.HEADLINE;
                    break;
                case ShopEditBasicFieldRow.FIELD_SHOP_ANNOUNCEMENT /*2*/:
                    i2 = R.shop_announcement_sentence;
                    i3 = R.shop_announcement_hint;
                    str = "shop_announcement_edit";
                    headline = shopHomePage.getShop().getMessage();
                    str2 = ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE;
                    break;
                case ShopEditBasicFieldRow.FIELD_MESSAGE_TO_BUYERS /*3*/:
                    i2 = R.message_to_buyers_sentence;
                    i3 = R.message_to_buyers_hint;
                    str = "shop_message_edit";
                    headline = shopHomePage.getShop().getMessageToBuyers();
                    str2 = ResponseConstants.MESSAGE_TO_BUYERS;
                    break;
                case ShopEditBasicFieldRow.FIELD_ABOUT_STORY_HEADLINE /*4*/:
                    i2 = R.story_headline;
                    i3 = R.story_headline_hint;
                    str = "shop_about_headline_edit";
                    storyHeadline = shopHomePage.getShopAbout() != null ? shopHomePage.getShopAbout().getStoryHeadline() : StringUtils.EMPTY;
                    str2 = ResponseConstants.STORY_HEADLINE;
                    break;
                case ShopEditBasicFieldRow.FIELD_ABOUT_STORY /*5*/:
                    i2 = R.story_title;
                    i3 = R.story_hint;
                    str = "shop_about_story_edit";
                    storyHeadline = shopHomePage.getShopAbout() != null ? shopHomePage.getShopAbout().getStory() : StringUtils.EMPTY;
                    str2 = ResponseConstants.STORY;
                    break;
                case ShopEditBasicFieldRow.FIELD_POLICY_WELCOME /*6*/:
                    i2 = R.welcome_message_sentence;
                    i3 = R.welcome_message_hint;
                    str = "shop_policy_welcome_edit";
                    storyHeadline = shopHomePage.getShopPolicy() != null ? shopHomePage.getShopPolicy().getWelcomeMessage() : StringUtils.EMPTY;
                    str2 = ResponseConstants.WELCOME;
                    break;
                case ShopEditBasicFieldRow.FIELD_POLICY_PAYMENT /*7*/:
                    i2 = R.payment_policy_sentence;
                    i3 = R.payment_policy_hint;
                    str = "shop_policy_payment_edit";
                    storyHeadline = shopHomePage.getShopPolicy() != null ? shopHomePage.getShopPolicy().getPaymentPolicy() : StringUtils.EMPTY;
                    str2 = ResponseConstants.PAYMENT;
                    break;
                case ShopEditBasicFieldRow.FIELD_POLICY_REFUND /*8*/:
                    i2 = R.refund_policy_sentence;
                    i3 = R.refund_policy_hint;
                    str = "shop_policy_refund_edit";
                    storyHeadline = shopHomePage.getShopPolicy() != null ? shopHomePage.getShopPolicy().getRefundPolicy() : StringUtils.EMPTY;
                    str2 = ResponseConstants.REFUNDS;
                    break;
                case ShopEditBasicFieldRow.FIELD_POLICY_SHIPPING /*9*/:
                    i2 = R.shipping_policy_sentence;
                    i3 = R.shipping_policy_hint;
                    str = "shop_policy_shipping_edit";
                    storyHeadline = shopHomePage.getShopPolicy() != null ? shopHomePage.getShopPolicy().getShippingPolicy() : StringUtils.EMPTY;
                    str2 = ResponseConstants.SHIPPING;
                    break;
                case ShopEditBasicFieldRow.FIELD_POLICY_ADDITIONAL /*10*/:
                    i2 = R.additional_information_sentence;
                    i3 = R.additional_information_hint;
                    str = "shop_policy_additional_edit";
                    storyHeadline = shopHomePage.getShopPolicy() != null ? shopHomePage.getShopPolicy().getAdditionalInformationMessage() : StringUtils.EMPTY;
                    str2 = ResponseConstants.ADDITIONAL;
                    break;
                case ShopEditBasicFieldRow.FIELD_VACATION_MESSAGE /*11*/:
                    i2 = R.vacation_message_sentence;
                    i3 = R.vacation_message_hint;
                    str = "shop_vacay_announcement_edit";
                    headline = shopHomePage.getShop().getVacationMessage();
                    str2 = ResponseConstants.VACATION_MESSAGE;
                    break;
                default:
                    i2 = R.vacation_reply_sentence;
                    i3 = R.vacation_reply_hint;
                    str = "shop_vacay_convo_reply_edit";
                    headline = shopHomePage.getShop().getVacationAutoReply();
                    str2 = ResponseConstants.VACATION_AUTOREPLY;
                    break;
            }
            this.mPageTitleRes = i2;
            this.mTitle = context.getString(i2);
            this.mContent = headline;
            this.mApiField = str2;
            this.mPageInView = str;
            this.mHint = context.getString(i3);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FieldType {
    }

    public /* bridge */ /* synthetic */ CharSequence getContent() {
        return super.getContent();
    }

    public /* bridge */ /* synthetic */ int getContentMaxLines() {
        return super.getContentMaxLines();
    }

    public /* bridge */ /* synthetic */ CharSequence getHint() {
        return super.getHint();
    }

    public /* bridge */ /* synthetic */ CharSequence getTitle() {
        return super.getTitle();
    }

    public /* bridge */ /* synthetic */ int getViewType() {
        return super.getViewType();
    }

    public /* bridge */ /* synthetic */ boolean shouldIncludeBottomExtraPadding() {
        return super.shouldIncludeBottomExtraPadding();
    }

    ShopEditBasicFieldRow() {
    }

    public ShopEditBasicFieldRow(@NonNull ShopHomePage shopHomePage, int i, @NonNull Context context) {
        EditInfo editInfo = new EditInfo(shopHomePage, i, context);
        this.mContentRow = new Builder(FIELD_MESSAGE_TO_BUYERS).title(editInfo.mTitle).content(editInfo.mContent).hint(editInfo.mHint).build();
        this.mEditInfo = editInfo;
        this.mFieldType = i;
    }

    @NonNull
    ShopEditContentRow getContentRow() {
        return this.mContentRow;
    }

    public void editActionInitiated(int i, @NonNull ShopEditDelegate shopEditDelegate, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter) {
        EditInfo editInfo = this.mEditInfo;
        shopEditDelegate.editBasicShopField(editInfo.mApiField, editInfo.mPageTitleRes, editInfo.mPageInView, editInfo.mContent.toString());
    }

    public void updateWithEditResult(@NonNull ShopEditDelegate shopEditDelegate, @NonNull RecyclerView recyclerView, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter, int i, int i2, @NonNull Intent intent, @NonNull Context context, int i3) {
        if (i == 1001 && i2 == 1011 && intent.hasExtra("shop_edit_field_content")) {
            CharSequence stringExtra = intent.getStringExtra("shop_edit_field_content");
            this.mContentRow.setContent(stringExtra);
            this.mEditInfo.mContent = stringExtra;
            baseRecyclerViewAdapter.notifyItemChanged(i3);
        }
    }

    public void restoreComplexStateIfNecessary(@NonNull Context context) {
    }

    public boolean isActionEnabled() {
        return true;
    }

    public boolean isOldShopPoliciesFieldRow() {
        return this.mFieldType == FIELD_POLICY_WELCOME || this.mFieldType == FIELD_POLICY_PAYMENT || this.mFieldType == FIELD_POLICY_SHIPPING || this.mFieldType == FIELD_POLICY_REFUND || this.mFieldType == FIELD_POLICY_ADDITIONAL;
    }

    public static List<ShopEditBasicFieldRow> getOldPoliciesContentItems(@NonNull ShopHomePage shopHomePage, @NonNull Context context) {
        int[] iArr = new int[]{FIELD_POLICY_WELCOME, FIELD_POLICY_PAYMENT, FIELD_POLICY_SHIPPING, FIELD_POLICY_REFUND, FIELD_POLICY_ADDITIONAL};
        List<ShopEditBasicFieldRow> arrayList = new ArrayList(iArr.length);
        int length = iArr.length;
        for (int i = 0; i < length; i += FIELD_SHOP_TITLE) {
            arrayList.add(new ShopEditBasicFieldRow(shopHomePage, iArr[i], context));
        }
        return arrayList;
    }
}
