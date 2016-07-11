package com.etsy.android.uikit.messaging;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.etsy.android.lib.R;
import com.etsy.android.uikit.ui.toast.PersistentToastView;

public class EasyOptOutToastView extends PersistentToastView {
    private View mBtnDismiss;
    private OnClickListener mOptOutClickListener;
    private TextView mTxtMessage;

    /* renamed from: com.etsy.android.uikit.messaging.EasyOptOutToastView.1 */
    class C09351 extends ClickableSpan {
        final /* synthetic */ EasyOptOutToastView f3982a;

        C09351(EasyOptOutToastView easyOptOutToastView) {
            this.f3982a = easyOptOutToastView;
        }

        public void onClick(View view) {
            if (this.f3982a.mOptOutClickListener != null) {
                this.f3982a.mOptOutClickListener.onClick(view);
            }
        }

        public void updateDrawState(@NonNull TextPaint textPaint) {
            textPaint.setUnderlineText(true);
        }
    }

    public EasyOptOutToastView(Context context) {
        super(context);
    }

    public EasyOptOutToastView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public EasyOptOutToastView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mTxtMessage = (TextView) findViewById(R.message);
        this.mBtnDismiss = findViewById(R.dismiss_icon);
    }

    void setTextContent(CharSequence charSequence, CharSequence charSequence2) {
        new SpannableStringBuilder(charSequence2).setSpan(new C09351(this), 0, charSequence2.length(), 0);
        CharSequence concat = TextUtils.concat(new CharSequence[]{charSequence, " ", r0, " "});
        this.mTxtMessage.setMovementMethod(LinkMovementMethod.getInstance());
        this.mTxtMessage.setText(concat, BufferType.SPANNABLE);
    }

    protected void setActionClickListener(OnClickListener onClickListener) {
        this.mOptOutClickListener = onClickListener;
    }

    protected void setDismissClickListener(OnClickListener onClickListener) {
        this.mBtnDismiss.setOnClickListener(onClickListener);
    }
}
