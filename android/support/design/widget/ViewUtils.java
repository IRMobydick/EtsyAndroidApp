package android.support.design.widget;

import android.os.Build.VERSION;
import android.view.View;

class ViewUtils {
    static final Creator DEFAULT_ANIMATOR_CREATOR;
    private static final ViewUtilsImpl IMPL;

    /* renamed from: android.support.design.widget.ViewUtils.1 */
    final class C00511 implements Creator {
        C00511() {
        }

        public ValueAnimatorCompat createAnimator() {
            return new ValueAnimatorCompat(VERSION.SDK_INT >= 12 ? new ValueAnimatorCompatImplHoneycombMr1() : new ValueAnimatorCompatImplEclairMr1());
        }
    }

    interface ViewUtilsImpl {
        void setBoundsViewOutlineProvider(View view);
    }

    class ViewUtilsImplBase implements ViewUtilsImpl {
        private ViewUtilsImplBase() {
        }

        public void setBoundsViewOutlineProvider(View view) {
        }
    }

    class ViewUtilsImplLollipop implements ViewUtilsImpl {
        private ViewUtilsImplLollipop() {
        }

        public void setBoundsViewOutlineProvider(View view) {
            ViewUtilsLollipop.setBoundsViewOutlineProvider(view);
        }
    }

    ViewUtils() {
    }

    static {
        DEFAULT_ANIMATOR_CREATOR = new C00511();
        if (VERSION.SDK_INT >= 21) {
            IMPL = new ViewUtilsImplLollipop();
        } else {
            IMPL = new ViewUtilsImplBase();
        }
    }

    static void setBoundsViewOutlineProvider(View view) {
        IMPL.setBoundsViewOutlineProvider(view);
    }

    static ValueAnimatorCompat createAnimator() {
        return DEFAULT_ANIMATOR_CREATOR.createAnimator();
    }
}
