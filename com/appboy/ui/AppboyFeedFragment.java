package com.appboy.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.ListFragment;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.appboy.Appboy;
import com.appboy.Constants;
import com.appboy.enums.CardCategory;
import com.appboy.events.FeedUpdatedEvent;
import com.appboy.events.IEventSubscriber;
import com.appboy.support.AppboyLogger;
import com.appboy.ui.adapters.AppboyListAdapter;
import java.util.ArrayList;
import java.util.EnumSet;

public class AppboyFeedFragment extends ListFragment implements OnRefreshListener {
    private static final long AUTO_HIDE_REFRESH_INDICATOR_DELAY_MS = 2500;
    private static final int MAX_FEED_TTL_SECONDS = 60;
    private static final int NETWORK_PROBLEM_WARNING_MS = 5000;
    private static final String TAG;
    private int currentCardIndexAtBottomOfScreen;
    private AppboyListAdapter mAdapter;
    private Appboy mAppboy;
    private EnumSet<CardCategory> mCategories;
    private LinearLayout mEmptyFeedLayout;
    private RelativeLayout mFeedRootLayout;
    private SwipeRefreshLayout mFeedSwipeLayout;
    private IEventSubscriber<FeedUpdatedEvent> mFeedUpdatedSubscriber;
    private GestureDetectorCompat mGestureDetector;
    private ProgressBar mLoadingSpinner;
    private final Handler mMainThreadLooper;
    private LinearLayout mNetworkErrorLayout;
    private final Runnable mShowNetworkError;
    private boolean mSkipCardImpressionsReset;
    private View mTransparentFullBoundsContainerView;
    private int previousVisibleHeadCardIndex;

    /* renamed from: com.appboy.ui.AppboyFeedFragment.1 */
    class C03811 implements Runnable {
        C03811() {
        }

        public void run() {
            if (AppboyFeedFragment.this.mLoadingSpinner != null) {
                AppboyFeedFragment.this.mLoadingSpinner.setVisibility(8);
            }
            if (AppboyFeedFragment.this.mNetworkErrorLayout != null) {
                AppboyFeedFragment.this.mNetworkErrorLayout.setVisibility(0);
            }
        }
    }

    /* renamed from: com.appboy.ui.AppboyFeedFragment.2 */
    class C03822 implements OnTouchListener {
        C03822() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return AppboyFeedFragment.this.mGestureDetector.onTouchEvent(motionEvent);
        }
    }

    /* renamed from: com.appboy.ui.AppboyFeedFragment.3 */
    class C03833 implements OnScrollListener {
        C03833() {
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            AppboyFeedFragment.this.mFeedSwipeLayout.setEnabled(i == 0);
            if (i2 != 0) {
                int i4 = i - 1;
                if (i4 > AppboyFeedFragment.this.previousVisibleHeadCardIndex) {
                    AppboyFeedFragment.this.mAdapter.batchSetCardsToRead(AppboyFeedFragment.this.previousVisibleHeadCardIndex, i4);
                }
                AppboyFeedFragment.this.previousVisibleHeadCardIndex = i4;
                AppboyFeedFragment.this.currentCardIndexAtBottomOfScreen = i + i2;
            }
        }
    }

    /* renamed from: com.appboy.ui.AppboyFeedFragment.4 */
    class C03844 implements OnTouchListener {
        C03844() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return view.getVisibility() == 0;
        }
    }

    /* renamed from: com.appboy.ui.AppboyFeedFragment.5 */
    class C03865 implements IEventSubscriber<FeedUpdatedEvent> {
        final /* synthetic */ ListView val$listView;

        /* renamed from: com.appboy.ui.AppboyFeedFragment.5.1 */
        class C03851 implements Runnable {
            final /* synthetic */ FeedUpdatedEvent val$event;

            C03851(FeedUpdatedEvent feedUpdatedEvent) {
                this.val$event = feedUpdatedEvent;
            }

            public void run() {
                AppboyLogger.m662d(AppboyFeedFragment.TAG, "Updating feed views in response to FeedUpdatedEvent: " + this.val$event);
                AppboyFeedFragment.this.mMainThreadLooper.removeCallbacks(AppboyFeedFragment.this.mShowNetworkError);
                AppboyFeedFragment.this.mNetworkErrorLayout.setVisibility(8);
                if (this.val$event.getCardCount(AppboyFeedFragment.this.mCategories) == 0) {
                    C03865.this.val$listView.setVisibility(8);
                    AppboyFeedFragment.this.mAdapter.clear();
                } else {
                    AppboyFeedFragment.this.mEmptyFeedLayout.setVisibility(8);
                    AppboyFeedFragment.this.mLoadingSpinner.setVisibility(8);
                    AppboyFeedFragment.this.mTransparentFullBoundsContainerView.setVisibility(8);
                }
                if (this.val$event.isFromOfflineStorage() && (this.val$event.lastUpdatedInSecondsFromEpoch() + 60) * 1000 < System.currentTimeMillis()) {
                    AppboyLogger.m666i(AppboyFeedFragment.TAG, String.format("Feed received was older than the max time to live of %d seconds, displaying it for now, but requesting an updated view from the server.", new Object[]{Integer.valueOf(AppboyFeedFragment.MAX_FEED_TTL_SECONDS)}));
                    AppboyFeedFragment.this.mAppboy.requestFeedRefresh();
                    if (this.val$event.getCardCount(AppboyFeedFragment.this.mCategories) == 0) {
                        AppboyLogger.m662d(AppboyFeedFragment.TAG, String.format("Old feed was empty, putting up a network spinner and registering the network error message on a delay of %dms.", new Object[]{Integer.valueOf(AppboyFeedFragment.NETWORK_PROBLEM_WARNING_MS)}));
                        AppboyFeedFragment.this.mEmptyFeedLayout.setVisibility(8);
                        AppboyFeedFragment.this.mLoadingSpinner.setVisibility(0);
                        AppboyFeedFragment.this.mTransparentFullBoundsContainerView.setVisibility(0);
                        AppboyFeedFragment.this.mMainThreadLooper.postDelayed(AppboyFeedFragment.this.mShowNetworkError, 5000);
                        return;
                    }
                }
                if (this.val$event.getCardCount(AppboyFeedFragment.this.mCategories) == 0) {
                    AppboyFeedFragment.this.mLoadingSpinner.setVisibility(8);
                    AppboyFeedFragment.this.mEmptyFeedLayout.setVisibility(0);
                    AppboyFeedFragment.this.mTransparentFullBoundsContainerView.setVisibility(0);
                } else {
                    AppboyFeedFragment.this.mAdapter.replaceFeed(this.val$event.getFeedCards(AppboyFeedFragment.this.mCategories));
                    C03865.this.val$listView.setVisibility(0);
                }
                AppboyFeedFragment.this.mFeedSwipeLayout.setRefreshing(false);
            }
        }

        C03865(ListView listView) {
            this.val$listView = listView;
        }

        public void trigger(FeedUpdatedEvent feedUpdatedEvent) {
            Activity activity = AppboyFeedFragment.this.getActivity();
            if (activity != null) {
                activity.runOnUiThread(new C03851(feedUpdatedEvent));
            }
        }
    }

    /* renamed from: com.appboy.ui.AppboyFeedFragment.6 */
    class C03876 implements Runnable {
        C03876() {
        }

        public void run() {
            AppboyFeedFragment.this.mFeedSwipeLayout.setRefreshing(false);
        }
    }

    public class FeedGestureListener extends SimpleOnGestureListener {
        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            AppboyFeedFragment.this.getListView().smoothScrollBy((int) f2, 0);
            return true;
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            long eventTime = (motionEvent2.getEventTime() - motionEvent.getEventTime()) * 2;
            AppboyFeedFragment.this.getListView().smoothScrollBy(-((int) ((((float) eventTime) * f2) / 1000.0f)), (int) (eventTime * 2));
            return true;
        }
    }

    static {
        TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyFeedFragment.class.getName()});
    }

    public AppboyFeedFragment() {
        this.mMainThreadLooper = new Handler(Looper.getMainLooper());
        this.mShowNetworkError = new C03811();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mAppboy = Appboy.getInstance(activity);
        if (this.mAdapter == null) {
            this.mAdapter = new AppboyListAdapter(activity, C0401R.id.tag, new ArrayList());
            this.mCategories = CardCategory.ALL_CATEGORIES;
        }
        setRetainInstance(true);
        this.mGestureDetector = new GestureDetectorCompat(activity, new FeedGestureListener());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C0401R.layout.com_appboy_feed, viewGroup, false);
        this.mNetworkErrorLayout = (LinearLayout) inflate.findViewById(C0401R.id.com_appboy_feed_network_error);
        this.mLoadingSpinner = (ProgressBar) inflate.findViewById(C0401R.id.com_appboy_feed_loading_spinner);
        this.mEmptyFeedLayout = (LinearLayout) inflate.findViewById(C0401R.id.com_appboy_feed_empty_feed);
        this.mFeedRootLayout = (RelativeLayout) inflate.findViewById(C0401R.id.com_appboy_feed_root);
        this.mFeedSwipeLayout = (SwipeRefreshLayout) inflate.findViewById(C0401R.id.appboy_feed_swipe_container);
        this.mFeedSwipeLayout.setOnRefreshListener(this);
        this.mFeedSwipeLayout.setEnabled(false);
        this.mFeedSwipeLayout.setColorSchemeResources(C0401R.color.com_appboy_newsfeed_swipe_refresh_color_1, C0401R.color.com_appboy_newsfeed_swipe_refresh_color_2, C0401R.color.com_appboy_newsfeed_swipe_refresh_color_3, C0401R.color.com_appboy_newsfeed_swipe_refresh_color_4);
        this.mTransparentFullBoundsContainerView = inflate.findViewById(C0401R.id.com_appboy_feed_transparent_full_bounds_container_view);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.mSkipCardImpressionsReset) {
            this.mSkipCardImpressionsReset = false;
        } else {
            this.mAdapter.resetCardImpressionTracker();
            AppboyLogger.m662d(TAG, "Resetting card impressions.");
        }
        LayoutInflater from = LayoutInflater.from(getActivity());
        ListView listView = getListView();
        listView.addHeaderView(from.inflate(C0401R.layout.com_appboy_feed_header, null));
        listView.addFooterView(from.inflate(C0401R.layout.com_appboy_feed_footer, null));
        this.mFeedRootLayout.setOnTouchListener(new C03822());
        listView.setOnScrollListener(new C03833());
        this.mTransparentFullBoundsContainerView.setOnTouchListener(new C03844());
        this.mAppboy.removeSingleSubscription(this.mFeedUpdatedSubscriber, FeedUpdatedEvent.class);
        this.mFeedUpdatedSubscriber = new C03865(listView);
        this.mAppboy.subscribeToFeedUpdates(this.mFeedUpdatedSubscriber);
        listView.setAdapter(this.mAdapter);
        this.mAppboy.requestFeedRefreshFromCache();
    }

    public void onResume() {
        super.onResume();
        Appboy.getInstance(getActivity()).logFeedDisplayed();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mAppboy.removeSingleSubscription(this.mFeedUpdatedSubscriber, FeedUpdatedEvent.class);
        setOnScreenCardsToRead();
    }

    public void onPause() {
        super.onPause();
        setOnScreenCardsToRead();
    }

    private void setOnScreenCardsToRead() {
        this.mAdapter.batchSetCardsToRead(this.previousVisibleHeadCardIndex, this.currentCardIndexAtBottomOfScreen);
    }

    public void onDetach() {
        super.onDetach();
        setListAdapter(null);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (isVisible()) {
            this.mSkipCardImpressionsReset = true;
        }
    }

    public EnumSet<CardCategory> getCategories() {
        return this.mCategories;
    }

    public void setCategory(CardCategory cardCategory) {
        setCategories(EnumSet.of(cardCategory));
    }

    public void setCategories(EnumSet<CardCategory> enumSet) {
        if (enumSet == null) {
            AppboyLogger.m666i(TAG, "The categories passed into setCategories are null, AppboyFeedFragment is going to display all the cards in cache.");
            this.mCategories = CardCategory.ALL_CATEGORIES;
        } else if (enumSet.isEmpty()) {
            AppboyLogger.m670w(TAG, "The categories set had no elements and have been ignored. Please pass a valid EnumSet of CardCategory.");
            return;
        } else if (!enumSet.equals(this.mCategories)) {
            this.mCategories = enumSet;
        } else {
            return;
        }
        if (this.mAppboy != null) {
            this.mAppboy.requestFeedRefreshFromCache();
        }
    }

    public void onRefresh() {
        this.mAppboy.requestFeedRefresh();
        this.mMainThreadLooper.postDelayed(new C03876(), AUTO_HIDE_REFRESH_INDICATOR_DELAY_MS);
    }
}
