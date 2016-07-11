package com.etsy.android.lib.config;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.ui.dialog.EtsyDialogFragment;

public class EtsyConfigKey implements IEtsyConfigValue {
    @NonNull
    private String f1151a;
    private boolean f1152b;
    private EtsyConfigOption[] f1153c;
    private EtsyConfigOption[] f1154d;
    private EtsyConfigOption[] f1155e;

    public enum Environment {
        PRODUCTION,
        PRINCESS,
        DEVELOPMENT
    }

    public enum UserState {
        IS_ADMIN
    }

    public EtsyConfigKey(@NonNull String str, @Nullable String str2) {
        this(str, str2, false);
    }

    public EtsyConfigKey(@NonNull String str, @Nullable String str2, boolean z) {
        this.f1151a = str;
        this.f1152b = z;
        m824b();
        m827a(Environment.PRODUCTION, str2);
    }

    private void m824b() {
        this.f1153c = new EtsyConfigOption[Environment.values().length];
        this.f1155e = new EtsyConfigOption[UserState.values().length];
        this.f1154d = new EtsyConfigOption[EtsyBuild.values().length];
    }

    @NonNull
    public EtsyConfigKey m827a(@NonNull Environment environment, @Nullable String str) {
        this.f1153c[environment.ordinal()] = m823a(str);
        return this;
    }

    @NonNull
    public EtsyConfigKey m826a(@NonNull EtsyBuild etsyBuild, @Nullable String str) {
        if (etsyBuild == EtsyBuild.GOOGLE_PLAY) {
            throw new IllegalArgumentException("GOOGLE_PLAY is the assumed default build state. Don't add values for it, they'll never be used.");
        }
        this.f1154d[etsyBuild.ordinal()] = m823a(str);
        return this;
    }

    @NonNull
    private EtsyConfigOption m823a(@Nullable String str) {
        if (this.f1152b) {
            return new EtsyConfigOption(m829a(), str, "mobile_dynamic_config.android." + m829a(), EtsyDialogFragment.OPT_X_BUTTON);
        }
        return new EtsyConfigOption(m829a(), str);
    }

    @NonNull
    private EtsyConfigOption m825c() {
        return this.f1153c[Environment.PRODUCTION.ordinal()];
    }

    @NonNull
    public EtsyConfigOption m828a(@NonNull Environment environment, boolean z) {
        EtsyConfigOption etsyConfigOption;
        if (this.f1153c[environment.ordinal()] != null) {
            etsyConfigOption = this.f1153c[environment.ordinal()];
        } else {
            etsyConfigOption = m825c();
        }
        EtsyBuild c = InstallInfo.m919a().m926c();
        if (!(c == EtsyBuild.GOOGLE_PLAY || this.f1154d[c.ordinal()] == null)) {
            etsyConfigOption = this.f1154d[c.ordinal()];
        }
        if (!z || this.f1155e[UserState.IS_ADMIN.ordinal()] == null) {
            return etsyConfigOption;
        }
        return this.f1155e[UserState.IS_ADMIN.ordinal()];
    }

    @NonNull
    public String m829a() {
        return this.f1151a;
    }
}
