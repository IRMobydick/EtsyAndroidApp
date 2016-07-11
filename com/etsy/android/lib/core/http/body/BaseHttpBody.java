package com.etsy.android.lib.core.http.body;

import android.support.annotation.NonNull;
import com.adjust.sdk.Constants;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Locale;

public abstract class BaseHttpBody implements Serializable {
    private static final long serialVersionUID = -793844241421917542L;

    @NonNull
    public abstract String getContentType();

    public abstract void writeBody(@NonNull OutputStream outputStream);

    @NonNull
    public String getCharSet() {
        return Constants.ENCODING;
    }

    @NonNull
    public static String getDefaultCharSet() {
        return Constants.ENCODING;
    }

    @NonNull
    public final String getContentTypeHeaderValue() {
        return String.format(Locale.US, "%s; charset=%s", new Object[]{getContentType(), getCharSet()});
    }
}
