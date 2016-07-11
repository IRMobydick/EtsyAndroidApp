package com.appboy.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;
import com.appboy.Appboy;
import com.appboy.Constants;
import com.appboy.models.cards.Card;
import com.appboy.support.AppboyLogger;
import com.appboy.support.PackageUtils;
import com.appboy.ui.C0401R;
import com.appboy.ui.actions.IAction;
import com.appboy.ui.support.FrescoLibraryUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.Observable;
import java.util.Observer;
import org.apache.commons.lang3.StringUtils;

public abstract class BaseCardView<T extends Card> extends RelativeLayout implements Observer {
    private static final String COM_APPBOY_NEWSFEED_UNREAD_VISUAL_INDICATOR_ON = "com_appboy_newsfeed_unread_visual_indicator_on";
    private static final float SQUARE_ASPECT_RATIO = 1.0f;
    private static final String TAG;
    private static Boolean unreadCardVisualIndicatorOn;
    private final boolean mCanUseFresco;
    protected T mCard;
    protected final Context mContext;
    protected ImageSwitcher mImageSwitcher;

    /* renamed from: com.appboy.ui.widget.BaseCardView.1 */
    class C04291 implements ViewFactory {
        C04291() {
        }

        public View makeView() {
            return new ImageView(BaseCardView.this.mContext.getApplicationContext());
        }
    }

    /* renamed from: com.appboy.ui.widget.BaseCardView.2 */
    class C04302 implements OnGlobalLayoutListener {
        final /* synthetic */ float val$aspectRatio;
        final /* synthetic */ ImageView val$imageView;

        C04302(ImageView imageView, float f) {
            this.val$imageView = imageView;
            this.val$aspectRatio = f;
        }

        public void onGlobalLayout() {
            int width = this.val$imageView.getWidth();
            this.val$imageView.setLayoutParams(new LayoutParams(width, (int) (((float) width) / this.val$aspectRatio)));
            BaseCardView.removeOnGlobalLayoutListenerSafe(this.val$imageView.getViewTreeObserver(), this);
        }
    }

    protected abstract int getLayoutResource();

    protected abstract void onSetCard(T t);

    static {
        TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, BaseCardView.class.getName()});
    }

    public BaseCardView(Context context) {
        super(context);
        this.mCanUseFresco = FrescoLibraryUtils.canUseFresco(context);
        this.mContext = context;
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(getLayoutResource(), this);
        this.mImageSwitcher = (ImageSwitcher) findViewById(C0401R.id.com_appboy_newsfeed_item_read_indicator_image_switcher);
        if (this.mImageSwitcher != null) {
            this.mImageSwitcher.setFactory(new C04291());
        }
        if (unreadCardVisualIndicatorOn == null) {
            int identifier = this.mContext.getResources().getIdentifier(COM_APPBOY_NEWSFEED_UNREAD_VISUAL_INDICATOR_ON, "bool", PackageUtils.getResourcePackageName(context));
            if (identifier != 0) {
                unreadCardVisualIndicatorOn = Boolean.valueOf(context.getResources().getBoolean(identifier));
            } else {
                unreadCardVisualIndicatorOn = Boolean.valueOf(true);
            }
        }
        if (!unreadCardVisualIndicatorOn.booleanValue() && this.mImageSwitcher != null) {
            this.mImageSwitcher.setVisibility(8);
        }
    }

    public void update(Observable observable, Object obj) {
        setCardViewedIndicator();
    }

    private void setCardViewedIndicator() {
        if (getCard() == null) {
            AppboyLogger.m662d(TAG, "The card is null.");
        } else if (this.mImageSwitcher != null) {
            int i;
            if (getCard().isRead()) {
                i = C0401R.drawable.icon_read;
            } else {
                i = C0401R.drawable.icon_unread;
            }
            this.mImageSwitcher.setImageResource(i);
            this.mImageSwitcher.setTag(String.valueOf(i));
        } else {
            AppboyLogger.m662d(TAG, "The imageSwitcher for the read/unread feature is null. Did you include it in your xml?");
        }
    }

    public void setCard(T t) {
        this.mCard = t;
        onSetCard(t);
        t.addObserver(this);
        setCardViewedIndicator();
    }

    public Card getCard() {
        return this.mCard;
    }

    void setOptionalTextView(TextView textView, String str) {
        if (str == null || str.trim().equals(StringUtils.EMPTY)) {
            textView.setText(StringUtils.EMPTY);
            textView.setVisibility(8);
            return;
        }
        textView.setText(str);
        textView.setVisibility(0);
    }

    void safeSetBackground(Drawable drawable) {
        if (VERSION.SDK_INT < 16) {
            setBackgroundDrawable(drawable);
        } else {
            setBackgroundNew(drawable);
        }
    }

    @TargetApi(16)
    private void setBackgroundNew(Drawable drawable) {
        setBackground(drawable);
    }

    void setImageViewToUrl(ImageView imageView, String str) {
        setImageViewToUrl(imageView, str, SQUARE_ASPECT_RATIO, false);
    }

    void setImageViewToUrl(ImageView imageView, String str, float f) {
        setImageViewToUrl(imageView, str, f, true);
    }

    void setImageViewToUrl(ImageView imageView, String str, float f, boolean z) {
        if (str == null) {
            AppboyLogger.m670w(TAG, "The image url to render is null. Not setting the card image.");
        } else if (f == 0.0f) {
            AppboyLogger.m670w(TAG, "The image aspect ratio is 0. Not setting the card image.");
        } else if (!str.equals(imageView.getTag())) {
            if (f != SQUARE_ASPECT_RATIO) {
                ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
                if (viewTreeObserver.isAlive()) {
                    viewTreeObserver.addOnGlobalLayoutListener(new C04302(imageView, f));
                }
            }
            imageView.setImageResource(17170445);
            Appboy.getInstance(getContext()).fetchAndRenderImage(str, imageView, z);
            imageView.setTag(str);
        }
    }

    @TargetApi(16)
    public static void removeOnGlobalLayoutListenerSafe(ViewTreeObserver viewTreeObserver, OnGlobalLayoutListener onGlobalLayoutListener) {
        if (VERSION.SDK_INT < 16) {
            viewTreeObserver.removeGlobalOnLayoutListener(onGlobalLayoutListener);
        } else {
            viewTreeObserver.removeOnGlobalLayoutListener(onGlobalLayoutListener);
        }
    }

    void setSimpleDraweeToUrl(SimpleDraweeView simpleDraweeView, String str, float f, boolean z) {
        if (str == null) {
            AppboyLogger.m670w(TAG, "The image url to render is null. Not setting the card image.");
        } else {
            FrescoLibraryUtils.setDraweeControllerHelper(simpleDraweeView, str, f, z);
        }
    }

    boolean canUseFresco() {
        return this.mCanUseFresco;
    }

    protected static void handleCardClick(Context context, Card card, IAction iAction, String str) {
        card.setIsRead(true);
        if (iAction != null) {
            AppboyLogger.m662d(str, String.format("Logged click for card %s", new Object[]{card.getId()}));
            card.logClick();
            iAction.execute(context);
        }
    }

    View getProperViewFromInflatedStub(int i) {
        ((ViewStub) findViewById(i)).inflate();
        if (this.mCanUseFresco) {
            return findViewById(C0401R.id.com_appboy_stubbed_feed_drawee_view);
        }
        return findViewById(C0401R.id.com_appboy_stubbed_feed_image_view);
    }
}
