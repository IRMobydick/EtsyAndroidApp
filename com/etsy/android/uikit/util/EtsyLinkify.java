package com.etsy.android.uikit.util;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.util.ad;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.regex.Pattern;

public class EtsyLinkify {
    private static final String f4140a;

    /* renamed from: com.etsy.android.uikit.util.EtsyLinkify.1 */
    final class C10031 implements OnClickListener {
        final /* synthetic */ Context f4128a;
        final /* synthetic */ String f4129b;

        C10031(Context context, String str) {
            this.f4128a = context;
            this.f4129b = str;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            EtsyLinkify.m5490b(this.f4128a, this.f4129b);
        }
    }

    class UnderlineURLSpan extends URLSpan {
        private final boolean f4130a;

        public UnderlineURLSpan(String str, boolean z) {
            super(str);
            this.f4130a = z;
        }

        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            if (!this.f4130a) {
                textPaint.setUnderlineText(false);
            }
        }
    }

    /* renamed from: com.etsy.android.uikit.util.EtsyLinkify.2 */
    final class C10042 extends UnderlineURLSpan {
        final /* synthetic */ String f4131a;
        final /* synthetic */ Context f4132b;
        final /* synthetic */ Builder f4133c;

        C10042(String str, boolean z, String str2, Context context, Builder builder) {
            this.f4131a = str2;
            this.f4132b = context;
            this.f4133c = builder;
            super(str, z);
        }

        public void onClick(View view) {
            try {
                URL url = new URL(this.f4131a);
                if (ad.m3192c(url.getHost())) {
                    EtsyLinkify.m5490b(this.f4132b, ad.m3189a(url));
                } else {
                    EtsyLinkify.m5491b(this.f4132b, this.f4131a, this.f4133c);
                }
            } catch (MalformedURLException e) {
                super.onClick(view);
            }
        }
    }

    /* renamed from: com.etsy.android.uikit.util.EtsyLinkify.3 */
    final class C10053 implements OnClickListener {
        C10053() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.cancel();
        }
    }

    /* renamed from: com.etsy.android.uikit.util.EtsyLinkify.5 */
    final class C10065 extends UnderlineURLSpan {
        final /* synthetic */ View.OnClickListener f4134a;
        final /* synthetic */ Context f4135b;
        final /* synthetic */ String f4136c;

        C10065(String str, boolean z, View.OnClickListener onClickListener, Context context, String str2) {
            this.f4134a = onClickListener;
            this.f4135b = context;
            this.f4136c = str2;
            super(str, z);
        }

        public void onClick(View view) {
            if (this.f4134a != null) {
                this.f4134a.onClick(view);
            } else {
                EtsyLinkify.m5490b(this.f4135b, this.f4136c);
            }
        }
    }

    class CustomColorUnderlineURLSpan extends UnderlineURLSpan {
        @ColorInt
        private final int f4137a;

        public CustomColorUnderlineURLSpan(@ColorInt int i, @Nullable String str, boolean z) {
            super(str, z);
            this.f4137a = i;
        }

        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            textPaint.setColor(this.f4137a);
        }
    }

    /* renamed from: com.etsy.android.uikit.util.EtsyLinkify.6 */
    final class C10076 extends CustomColorUnderlineURLSpan {
        final /* synthetic */ View.OnClickListener f4138a;
        final /* synthetic */ String f4139b;

        C10076(int i, String str, boolean z, View.OnClickListener onClickListener, String str2) {
            this.f4138a = onClickListener;
            this.f4139b = str2;
            super(i, str, z);
        }

        public void onClick(View view) {
            if (this.f4138a != null) {
                this.f4138a.onClick(view);
            } else {
                EtsyLinkify.m5490b(view.getContext(), this.f4139b);
            }
        }
    }

    static {
        f4140a = EtsyDebug.m1891a(EtsyLinkify.class);
    }

    public static void m5482a(Context context, TextView textView) {
        m5483a(context, textView, true);
    }

    public static void m5483a(Context context, TextView textView, boolean z) {
        m5484a(context, textView, z, 3);
    }

    public static void m5484a(Context context, TextView textView, boolean z, int i) {
        Linkify.addLinks(textView, i);
        textView.setLinkTextColor(context.getResources().getColor(R.blue));
        URLSpan[] urls = textView.getUrls();
        if (urls.length > 0) {
            Builder a = m5480a(context);
            CharSequence spannableStringBuilder = new SpannableStringBuilder(textView.getText());
            for (URLSpan uRLSpan : urls) {
                String url = uRLSpan.getURL();
                int spanStart = spannableStringBuilder.getSpanStart(uRLSpan);
                int spanEnd = spannableStringBuilder.getSpanEnd(uRLSpan);
                spannableStringBuilder.removeSpan(uRLSpan);
                spannableStringBuilder.setSpan(new C10042(url, z, url, context, a), spanStart, spanEnd, 33);
            }
            textView.setText(spannableStringBuilder);
        }
    }

    private static void m5491b(Context context, String str, Builder builder) {
        builder.setPositiveButton(R.convo_external_link_warning_open_button, new C10031(context, str));
        builder.create().show();
    }

    private static Builder m5480a(Context context) {
        Builder builder = new Builder(context);
        builder.setTitle(R.convo_external_link_warning_title);
        builder.setMessage(R.convo_external_link_warning_message).setCancelable(true).setNegativeButton(R.convo_external_link_warning_cancel_button, new C10053());
        return builder;
    }

    private static void m5490b(Context context, String str) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        String packageName = context.getPackageName();
        List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
        if (!queryIntentActivities.isEmpty()) {
            for (ResolveInfo resolveInfo : queryIntentActivities) {
                if (resolveInfo.activityInfo.packageName.equals(packageName)) {
                    intent.setPackage(resolveInfo.activityInfo.packageName);
                    break;
                }
            }
        }
        intent.putExtra("NAV_INTERNAL_LINK", true);
        context.startActivity(intent);
    }

    public static void m5487a(TextView textView, String str, String str2, boolean z, View.OnClickListener onClickListener) {
        Linkify.addLinks(textView, Pattern.compile(str), str2);
        m5492b(textView, z, onClickListener);
        textView.setLinkTextColor(textView.getResources().getColor(R.blue));
    }

    public static void m5488a(TextView textView, boolean z, View.OnClickListener onClickListener) {
        m5489a(textView, z, true, onClickListener);
    }

    public static void m5489a(TextView textView, boolean z, boolean z2, View.OnClickListener onClickListener) {
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        m5492b(textView, z, onClickListener);
        if (z2) {
            textView.setLinkTextColor(textView.getResources().getColor(R.blue));
        } else {
            textView.setLinkTextColor(textView.getTextColors().getDefaultColor());
        }
    }

    private static void m5492b(TextView textView, boolean z, View.OnClickListener onClickListener) {
        if (textView != null) {
            URLSpan[] urls = textView.getUrls();
            Context context = textView.getContext();
            if (urls.length > 0) {
                CharSequence spannableStringBuilder = new SpannableStringBuilder(textView.getText());
                for (URLSpan uRLSpan : urls) {
                    String url = uRLSpan.getURL();
                    int spanStart = spannableStringBuilder.getSpanStart(uRLSpan);
                    int spanEnd = spannableStringBuilder.getSpanEnd(uRLSpan);
                    spannableStringBuilder.removeSpan(uRLSpan);
                    spannableStringBuilder.setSpan(new C10065(url, z, onClickListener, context, url), spanStart, spanEnd, 33);
                }
                textView.setText(spannableStringBuilder);
            }
        }
    }

    public static Spanned m5481a(@NonNull Context context, @NonNull Spanned spanned, boolean z, boolean z2, @Nullable View.OnClickListener onClickListener) {
        int color = ContextCompat.getColor(context, R.blue);
        if (spanned.length() == 0) {
            return spanned;
        }
        URLSpan[] uRLSpanArr = (URLSpan[]) spanned.getSpans(0, spanned.length(), URLSpan.class);
        if (uRLSpanArr.length == 0) {
            return spanned;
        }
        Spannable spannableString = new SpannableString(spanned);
        for (URLSpan uRLSpan : uRLSpanArr) {
            int spanStart = spanned.getSpanStart(uRLSpan);
            int spanEnd = spanned.getSpanEnd(uRLSpan);
            String url = uRLSpan.getURL();
            spannableString.removeSpan(uRLSpan);
            boolean z3 = true;
            if (z2) {
                try {
                    z3 = ad.m3192c(new URL(url).getHost());
                } catch (MalformedURLException e) {
                }
            }
            if (z3) {
                spannableString.setSpan(new C10076(color, url, z, onClickListener, url), spanStart, spanEnd, 33);
            }
        }
        return spannableString;
    }
}
