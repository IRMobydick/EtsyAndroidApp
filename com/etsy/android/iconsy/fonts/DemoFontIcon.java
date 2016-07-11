package com.etsy.android.iconsy.fonts;

import com.etsy.android.iconsy.AbstractFontIcon;

public enum DemoFontIcon implements AbstractFontIcon {
    EXAMPLE("H");
    
    private String mIcon;

    private DemoFontIcon(String str) {
        this.mIcon = str;
    }

    public String toString() {
        return this.mIcon;
    }
}
