package com.etsy.android.lib.models.apiv3.cart;

import android.support.annotation.NonNull;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.EtsyAssociativeArray;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.FormattedMoney;
import com.etsy.android.lib.models.apiv3.Money;
import com.etsy.android.lib.models.finds.FindsModule;
import com.fasterxml.jackson.core.JsonParser;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class SimpleKVPCartGroupItem extends EtsyAssociativeArray {
    private static final long serialVersionUID = -5398516197928395169L;
    protected FormattedMoney mFormattedMoney;
    protected Money mMoney;

    protected boolean parseField(JsonParser jsonParser, @NonNull String str) {
        if (ResponseConstants.MONEY.equals(str)) {
            this.mMoney = (Money) BaseModel.parseObject(jsonParser, Money.class);
            return true;
        } else if (!ResponseConstants.FORMATTED_MONEY.equals(str)) {
            return super.parseField(jsonParser, str);
        } else {
            this.mFormattedMoney = (FormattedMoney) BaseModel.parseObject(jsonParser, FormattedMoney.class);
            return true;
        }
    }

    public String getTitle() {
        return optString(FindsModule.FIELD_TITLE, StringUtils.EMPTY);
    }

    public String getValue() {
        return optString(ResponseConstants.VALUE, StringUtils.EMPTY);
    }

    public String getText() {
        return optString(FindsModule.FIELD_TEXT, StringUtils.EMPTY);
    }

    public String getButtonText() {
        return optString(ResponseConstants.BUTTON_TEXT, StringUtils.EMPTY);
    }

    public Money getMoney() {
        return this.mMoney;
    }

    public FormattedMoney getFormattedMoney() {
        return this.mFormattedMoney;
    }

    public String getMoneyString() {
        if (this.mMoney != null) {
            return this.mMoney.toString();
        }
        return StringUtils.EMPTY;
    }

    public String getFormattedMoneyString() {
        if (this.mFormattedMoney != null) {
            return this.mFormattedMoney.toString();
        }
        return StringUtils.EMPTY;
    }
}
