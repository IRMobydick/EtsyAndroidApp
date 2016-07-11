package com.etsy.android.lib.core.http.body;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.etsy.android.lib.requests.HttpUtil;
import java.io.OutputStream;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class FormBody extends ParamBody {
    private static final long serialVersionUID = 2031493282064349051L;

    public /* bridge */ /* synthetic */ void writeBody(OutputStream outputStream) {
        super.writeBody(outputStream);
    }

    private FormBody(@NonNull FormBody formBody) {
        super(formBody);
    }

    @NonNull
    public String getContentType() {
        return HttpUtil.URL_FORM_CONTENT_TYPE;
    }

    public static void addListToFormBody(@NonNull FormBody formBody, @NonNull String str, @NonNull List<String> list) {
        if (list.size() == 0) {
            formBody.m1341b(str, StringUtils.EMPTY);
            return;
        }
        int i = 0;
        for (String str2 : list) {
            int i2;
            if (TextUtils.isEmpty(str2)) {
                i2 = i;
            } else {
                formBody.m1341b(str + "[]", str2);
                i2 = i + 1;
            }
            i = i2;
        }
        if (i == 0) {
            formBody.m1341b(str, StringUtils.EMPTY);
        }
    }
}
