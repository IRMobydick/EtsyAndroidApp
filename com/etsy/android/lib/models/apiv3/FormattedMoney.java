package com.etsy.android.lib.models.apiv3;

import android.support.annotation.NonNull;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.models.BaseFieldModel;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.google.android.gms.gcm.Task;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.ObjectUtils;
import org.parceler.Parcel;

@Parcel
public class FormattedMoney extends BaseFieldModel {
    public static final String TAG;
    private static final long serialVersionUID = -618920105921433188L;
    protected List<Money> mArguments;
    protected String mFormatString;

    public FormattedMoney() {
        this.mArguments = new ArrayList();
    }

    static {
        TAG = EtsyDebug.m1891a(FormattedMoney.class);
    }

    protected boolean parseField(JsonParser jsonParser, @NonNull String str) {
        boolean z = true;
        switch (str.hashCode()) {
            case -2035517098:
                if (str.equals(ResponseConstants.ARGUMENTS)) {
                    z = true;
                    break;
                }
                break;
            case -1268779017:
                if (str.equals(ResponseConstants.FORMAT)) {
                    z = false;
                    break;
                }
                break;
        }
        switch (z) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                this.mFormatString = BaseModel.parseString(jsonParser);
                break;
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                this.mArguments = BaseModel.parseArray(jsonParser, Money.class);
                break;
            default:
                return false;
        }
        return true;
    }

    public String toString() {
        try {
            return String.format(this.mFormatString.replace("%m", "%s"), this.mArguments.toArray());
        } catch (Throwable e) {
            EtsyLogger.m1966a().m1991a(TAG, e, false);
            return this.mFormatString;
        }
    }

    public boolean equals(Object obj) {
        return (obj instanceof FormattedMoney) && ObjectUtils.equals(this.mFormatString, ((FormattedMoney) obj).mFormatString) && ObjectUtils.equals(this.mArguments, ((FormattedMoney) obj).mArguments);
    }

    public int hashCode() {
        return ((ObjectUtils.hashCode(this.mFormatString) + 527) * 31) + ObjectUtils.hashCode(this.mArguments);
    }
}
