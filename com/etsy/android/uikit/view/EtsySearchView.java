package com.etsy.android.uikit.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnCloseListener;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.OnClickListener;
import android.view.ViewAnimationUtils;
import android.widget.FrameLayout;
import com.etsy.android.lib.R;
import org.apache.commons.lang3.StringUtils;

public class EtsySearchView extends FrameLayout {
    private SearchView mSearchView;

    /* renamed from: com.etsy.android.uikit.view.EtsySearchView.1 */
    class C10111 implements OnClickListener {
        final /* synthetic */ EtsySearchView f4204a;

        C10111(EtsySearchView etsySearchView) {
            this.f4204a = etsySearchView;
        }

        public void onClick(View view) {
            this.f4204a.showSearch(false);
        }
    }

    /* renamed from: com.etsy.android.uikit.view.EtsySearchView.2 */
    class C10122 implements OnCloseListener {
        final /* synthetic */ EtsySearchView f4205a;

        C10122(EtsySearchView etsySearchView) {
            this.f4205a = etsySearchView;
        }

        public boolean onClose() {
            this.f4205a.showSearch(false);
            return true;
        }
    }

    /* renamed from: com.etsy.android.uikit.view.EtsySearchView.3 */
    class C10133 implements OnMenuItemClickListener {
        final /* synthetic */ EtsySearchView f4206a;

        C10133(EtsySearchView etsySearchView) {
            this.f4206a = etsySearchView;
        }

        public boolean onMenuItemClick(MenuItem menuItem) {
            this.f4206a.showSearch(true);
            return true;
        }
    }

    /* renamed from: com.etsy.android.uikit.view.EtsySearchView.4 */
    class C10144 extends AnimatorListenerAdapter {
        final /* synthetic */ EtsySearchView f4207a;

        C10144(EtsySearchView etsySearchView) {
            this.f4207a = etsySearchView;
        }

        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            this.f4207a.setVisibility(4);
        }
    }

    class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        int f4208a;

        /* renamed from: com.etsy.android.uikit.view.EtsySearchView.SavedState.1 */
        final class C10151 implements Creator<SavedState> {
            C10151() {
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m5640a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m5641a(i);
            }

            public SavedState m5640a(Parcel parcel) {
                return new SavedState(null);
            }

            public SavedState[] m5641a(int i) {
                return new SavedState[i];
            }
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f4208a = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f4208a);
        }

        static {
            CREATOR = new C10151();
        }
    }

    public EtsySearchView(Context context) {
        super(context);
        init();
    }

    public EtsySearchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public EtsySearchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    @TargetApi(21)
    public EtsySearchView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout_search_view, this);
        setBackgroundResource(R.background_main_v2_glass_dark);
        setOnClickListener(new C10111(this));
        this.mSearchView = (SearchView) findViewById(R.internal_search_view);
        this.mSearchView.setOnCloseListener(new C10122(this));
    }

    public void setOnQueryTextListener(OnQueryTextListener onQueryTextListener) {
        this.mSearchView.setOnQueryTextListener(onQueryTextListener);
    }

    public void setMenuItem(MenuItem menuItem) {
        menuItem.setOnMenuItemClickListener(new C10133(this));
    }

    public boolean isShowing() {
        return getVisibility() == 0;
    }

    private void showSearch(boolean z) {
        Animator createCircularReveal;
        if (z) {
            if (VERSION.SDK_INT >= 21) {
                createCircularReveal = ViewAnimationUtils.createCircularReveal(this, getWidth(), 0, 0.0f, (float) Math.hypot((double) getWidth(), (double) getHeight()));
            } else {
                createCircularReveal = ObjectAnimator.ofFloat(this, ALPHA, new float[]{0.0f, FullImageView.ASPECT_RATIO_SQUARE});
            }
            this.mSearchView.setIconified(false);
            setVisibility(0);
        } else {
            this.mSearchView.setQuery(StringUtils.EMPTY, false);
            if (VERSION.SDK_INT >= 21) {
                createCircularReveal = ViewAnimationUtils.createCircularReveal(this, getWidth(), 0, (float) Math.hypot((double) getWidth(), (double) getHeight()), 0.0f);
            } else {
                createCircularReveal = ObjectAnimator.ofFloat(this, ALPHA, new float[]{FullImageView.ASPECT_RATIO_SQUARE, 0.0f});
            }
            createCircularReveal.addListener(new C10144(this));
        }
        createCircularReveal.start();
    }

    public void hide() {
        showSearch(false);
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.f4208a = getVisibility();
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            setVisibility(savedState.f4208a);
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }
}
