package com.etsy.android.lib.models.apiv3;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.aj;
import com.etsy.android.uikit.util.MachineTranslationViewState;
import com.etsy.android.uikit.view.MachineTranslationButton;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.gcm.Task;
import org.apache.commons.lang3.ObjectUtils;
import org.parceler.Parcel;

@Parcel
public class StructuredShopPrivacy extends BaseModel implements MachineTranslationButton {
    private static final long serialVersionUID = -9054900251304900568L;
    protected PrivacyFlags mFlags;
    protected String mHeader;
    protected MachineTranslationViewState mOtherTranslationState;
    protected String mTranslatedOtherText;

    public StructuredShopPrivacy() {
        this.mFlags = new PrivacyFlags();
        this.mOtherTranslationState = new MachineTranslationViewState();
    }

    public StructuredShopPrivacy(StructuredShopPrivacy structuredShopPrivacy) {
        this.mFlags = new PrivacyFlags();
        this.mOtherTranslationState = new MachineTranslationViewState();
        setFlags(new PrivacyFlags(structuredShopPrivacy.getFlags()));
        setHeader(structuredShopPrivacy.getHeader());
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case -1221270899:
                        if (currentName.equals(ResponseConstants.HEADER)) {
                            obj = 1;
                            break;
                        }
                        break;
                    case 97513095:
                        if (currentName.equals(ResponseConstants.FLAGS)) {
                            obj = null;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mFlags = (PrivacyFlags) BaseModel.parseObject(jsonParser, PrivacyFlags.class);
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mHeader = BaseModel.parseString(jsonParser);
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }

    @JsonIgnore
    public PrivacyFlag getCommunication() {
        return this.mFlags.getCommunication();
    }

    @JsonIgnore
    public PrivacyFlag getFulfillment() {
        return this.mFlags.getFulfillment();
    }

    @JsonIgnore
    public PrivacyFlag getLegal() {
        return this.mFlags.getLegal();
    }

    @JsonIgnore
    public PrivacyFlag getOther() {
        return this.mFlags.getOther();
    }

    @NonNull
    public PrivacyFlags getFlags() {
        return this.mFlags;
    }

    public void setOtherTranslation(@NonNull String str) {
        this.mTranslatedOtherText = str;
    }

    @JsonIgnore
    @NonNull
    public String getTranslatedOtherText() {
        return this.mTranslatedOtherText;
    }

    @JsonIgnore
    public boolean isTranslationEligible() {
        return EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.f1259e) && getOther().isEnabled() && aj.m3232a(getOther().getLabel(), getOther().getLanguage());
    }

    @JsonIgnore
    public MachineTranslationViewState getTranslationState() {
        return this.mOtherTranslationState;
    }

    public void setFlags(@NonNull PrivacyFlags privacyFlags) {
        this.mFlags = privacyFlags;
    }

    public boolean hasAnyEnabledFlags() {
        return this.mFlags != null && (this.mFlags.getCommunication().isEnabled() || this.mFlags.getFulfillment().isEnabled() || this.mFlags.getLegal().isEnabled() || this.mFlags.getOther().isEnabled());
    }

    @Nullable
    public String getHeader() {
        return this.mHeader;
    }

    public void setHeader(@Nullable String str) {
        this.mHeader = str;
    }

    public boolean equals(Object obj) {
        return (obj instanceof StructuredShopPrivacy) && ObjectUtils.equals(this.mHeader, ((StructuredShopPrivacy) obj).getHeader()) && ObjectUtils.equals(this.mFlags, ((StructuredShopPrivacy) obj).getFlags());
    }

    public int hashCode() {
        return ((ObjectUtils.hashCode(this.mHeader) + 527) * 31) + ObjectUtils.hashCode(this.mFlags);
    }
}
