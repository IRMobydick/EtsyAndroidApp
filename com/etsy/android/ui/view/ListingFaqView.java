package com.etsy.android.ui.view;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.iconsy.views.IconDrawable;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.apiv3.FAQ;
import com.etsy.android.lib.util.ab;
import com.etsy.android.lib.util.fonts.StandardFontIcon;
import com.etsy.android.uikit.p016a.EtsyAnimator;
import com.etsy.android.uikit.util.HardwareAnimatorListener;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.ViewTreeObserverHelper;

public class ListingFaqView extends LinearLayout {
    private static final String TAG;
    private int mAnswerExpandedHeight;
    @Nullable
    private View mFaqDivider;
    private View mFaqHeader;
    private ImageView mFaqIcon;
    private boolean mIsExpanded;
    private TextView mTxtFaqAnswer;
    private TextView mTxtFaqQuestion;

    /* renamed from: com.etsy.android.ui.view.ListingFaqView.1 */
    class C09071 implements OnGlobalLayoutListener {
        final /* synthetic */ ListingFaqView f3841a;

        C09071(ListingFaqView listingFaqView) {
            this.f3841a = listingFaqView;
        }

        public void onGlobalLayout() {
            ViewTreeObserverHelper.m5639b(this.f3841a.getViewTreeObserver(), (OnGlobalLayoutListener) this);
            this.f3841a.mAnswerExpandedHeight = this.f3841a.mTxtFaqAnswer.getMeasuredHeight();
            this.f3841a.setExpanded(this.f3841a.mIsExpanded);
        }
    }

    /* renamed from: com.etsy.android.ui.view.ListingFaqView.2 */
    class C09082 extends TrackingOnClickListener {
        final /* synthetic */ ListingFaqView f3842a;

        C09082(ListingFaqView listingFaqView) {
            this.f3842a = listingFaqView;
        }

        public void onViewClick(View view) {
            this.f3842a.mFaqHeader.setOnClickListener(null);
            this.f3842a.animateExpanded(false);
        }
    }

    /* renamed from: com.etsy.android.ui.view.ListingFaqView.3 */
    class C09093 extends TrackingOnClickListener {
        final /* synthetic */ ListingFaqView f3843a;

        C09093(ListingFaqView listingFaqView) {
            this.f3843a = listingFaqView;
        }

        public void onViewClick(View view) {
            this.f3843a.mFaqHeader.setOnClickListener(null);
            this.f3843a.animateExpanded(true);
        }
    }

    /* renamed from: com.etsy.android.ui.view.ListingFaqView.4 */
    class C09104 extends HardwareAnimatorListener {
        final /* synthetic */ boolean f3844a;
        final /* synthetic */ ListingFaqView f3845b;

        C09104(ListingFaqView listingFaqView, View view, boolean z) {
            this.f3845b = listingFaqView;
            this.f3844a = z;
            super(view);
        }

        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            this.f3845b.setIcon(this.f3844a);
            if (this.f3844a) {
                this.f3845b.mTxtFaqAnswer.setHeight(0);
                this.f3845b.mTxtFaqAnswer.setVisibility(0);
            }
        }

        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            this.f3845b.setExpanded(this.f3844a);
        }
    }

    static {
        TAG = EtsyDebug.m1891a(ListingFaqView.class);
    }

    public ListingFaqView(Context context) {
        super(context);
        this.mIsExpanded = false;
        this.mAnswerExpandedHeight = 0;
        init(context, null, 0, 0);
    }

    public ListingFaqView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsExpanded = false;
        this.mAnswerExpandedHeight = 0;
        init(context, attributeSet, 0, 0);
    }

    public ListingFaqView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mIsExpanded = false;
        this.mAnswerExpandedHeight = 0;
        init(context, attributeSet, i, 0);
    }

    @TargetApi(21)
    public ListingFaqView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mIsExpanded = false;
        this.mAnswerExpandedHeight = 0;
        init(context, attributeSet, i, i2);
    }

    private void init(Context context, AttributeSet attributeSet, int i, int i2) {
        inflate(context, 2130903558, this);
        this.mFaqDivider = findViewById(2131756491);
        this.mFaqHeader = findViewById(2131756490);
        this.mTxtFaqQuestion = (TextView) findViewById(2131756493);
        this.mTxtFaqAnswer = (TextView) findViewById(2131756494);
        this.mFaqIcon = (ImageView) findViewById(2131756492);
        ViewTreeObserverHelper.m5636a(getViewTreeObserver(), new C09071(this));
    }

    public void setFaq(@NonNull FAQ faq, boolean z) {
        if (this.mFaqDivider != null) {
            this.mFaqDivider.setVisibility(z ? 0 : 8);
        }
        this.mTxtFaqQuestion.setText(faq.getQuestion());
        this.mTxtFaqAnswer.setText(faq.getAnswer());
    }

    private void setExpanded(boolean z) {
        this.mIsExpanded = z;
        if (z) {
            this.mFaqHeader.setOnClickListener(new C09082(this));
            this.mTxtFaqAnswer.setVisibility(0);
        } else {
            this.mFaqHeader.setOnClickListener(new C09093(this));
            this.mTxtFaqAnswer.setVisibility(8);
        }
        setIcon(z);
    }

    private void animateExpanded(boolean z) {
        int i = 0;
        if (this.mTxtFaqAnswer.getHeight() > new ab(getContext()).m3183f()) {
            EtsyDebug.m1912c(TAG, "Not animating since the View is larger than the window. In some cases this will cause a crash on texture size");
            setExpanded(false);
            return;
        }
        int measuredHeight = z ? 0 : this.mTxtFaqAnswer.getMeasuredHeight();
        if (z) {
            i = this.mAnswerExpandedHeight;
        }
        EtsyAnimator.m5285a(this.mTxtFaqAnswer).m5289a(measuredHeight, i).m5290a(new C09104(this, this.mTxtFaqAnswer, z)).m5292b();
    }

    private void setIcon(boolean z) {
        Drawable a;
        Resources resources = getResources();
        IconDrawable a2 = IconDrawable.m775a(resources).m779a(resources.getColor(R.actionbar_bottom_line)).m778a((float) resources.getDimensionPixelSize(R.expand_collapse_icon));
        if (z) {
            a = a2.m780a(StandardFontIcon.NAVIGATE_UP).m777a();
        } else {
            a = a2.m780a(StandardFontIcon.NAVIGATE_DOWN).m777a();
        }
        this.mFaqIcon.setImageDrawable(a);
    }
}
