package com.etsy.android.ui.local;

import android.content.IntentSender.SendIntentException;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.EtsyJobResponse;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.HttpRequestJobBuilder;
import com.etsy.android.lib.core.ac;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.messaging.EtsyGooglePlayServicesUtil;
import com.etsy.android.lib.models.LocalMarketCard;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.LocalBrowseLandingPage;
import com.etsy.android.lib.models.apiv3.LocalBrowseModule;
import com.etsy.android.lib.models.apiv3.LocalBrowseResponse;
import com.etsy.android.lib.util.DeviceSettings;
import com.etsy.android.lib.util.bg;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.google.android.gms.common.ConnectionResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcels;

public class LocalSearchActivity extends BOENavDrawerActivity implements LocalBrowseManager, LocalLocationApiManager {
    private static final String BROWSE_HISTORY = "browse_history";
    private static final String INITIAL_LOCATION = "initial_location";
    private static final String IS_LIST_PANEL_SHOWING = "is_list_panel_showing";
    private static final String SEARCH_RESPONSE = "search_response";
    public static final String START_FULLSCREEN_MAP = "start_on_full_map";
    private static final String TAG;
    private List<LocalBrowseHistoryEntry> mBrowseHistory;
    private List<LocalBrowseUpdateListener> mBrowseUpdateListeners;
    private Button mEnableLocationButton;
    private TextView mEnableLocationMessage;
    private View mEnableLocationView;
    private ViewGroup mFilterBar;
    private LocalBrowseModule mInitialBrowseModule;
    private Location mInitialLocation;
    private boolean mIsListPanelShowing;
    private boolean mIsRequestPending;
    private LocalLocationApiManager mLocationManager;
    private List<LocalMarketCard> mSearchResponse;
    private LinearLayout mSearchingText;
    private boolean mStartOnFullMap;

    /* renamed from: com.etsy.android.ui.local.LocalSearchActivity.1 */
    class C07481 implements ac {
        final /* synthetic */ LocalSearchActivity f3052a;

        C07481(LocalSearchActivity localSearchActivity) {
            this.f3052a = localSearchActivity;
        }

        public void m4304a() {
            this.f3052a.mIsRequestPending = true;
            this.f3052a.showSearchingText();
            for (LocalBrowseUpdateListener onBrowseRequestPending : this.f3052a.mBrowseUpdateListeners) {
                onBrowseRequestPending.onBrowseRequestPending();
            }
            this.f3052a.updateFilterBar();
        }
    }

    /* renamed from: com.etsy.android.ui.local.LocalSearchActivity.2 */
    class C07492 implements EtsyJobResponse<LocalMarketCard> {
        final /* synthetic */ LocalSearchActivity f3053a;

        C07492(LocalSearchActivity localSearchActivity) {
            this.f3053a = localSearchActivity;
        }

        public void m4305a(List<LocalMarketCard> list, int i, EtsyResult<LocalMarketCard> etsyResult) {
            this.f3053a.hideSearchingText();
            this.f3053a.mIsRequestPending = false;
            this.f3053a.mSearchResponse = list;
            for (LocalBrowseUpdateListener onSearchResultsSuccess : this.f3053a.mBrowseUpdateListeners) {
                onSearchResultsSuccess.onSearchResultsSuccess(this.f3053a.mSearchResponse);
            }
            this.f3053a.updateFilterBar();
        }
    }

    /* renamed from: com.etsy.android.ui.local.LocalSearchActivity.3 */
    class C07503 implements EtsyJobResponse {
        final /* synthetic */ LocalSearchActivity f3054a;

        C07503(LocalSearchActivity localSearchActivity) {
            this.f3054a = localSearchActivity;
        }

        public void m4306a(EtsyResult etsyResult) {
            this.f3054a.hideSearchingText();
            this.f3054a.mIsRequestPending = false;
            this.f3054a.mSearchResponse = new ArrayList();
            for (LocalBrowseUpdateListener onResultsEmpty : this.f3054a.mBrowseUpdateListeners) {
                onResultsEmpty.onResultsEmpty();
            }
            this.f3054a.updateFilterBar();
        }
    }

    /* renamed from: com.etsy.android.ui.local.LocalSearchActivity.4 */
    class C07514 implements EtsyJobResponse {
        final /* synthetic */ LocalSearchActivity f3055a;

        C07514(LocalSearchActivity localSearchActivity) {
            this.f3055a = localSearchActivity;
        }

        public void m4307a(int i, String str, EtsyResult etsyResult) {
            this.f3055a.hideSearchingText();
            this.f3055a.mIsRequestPending = false;
            this.f3055a.mSearchResponse = null;
            for (LocalBrowseUpdateListener onResultsError : this.f3055a.mBrowseUpdateListeners) {
                onResultsError.onResultsError();
            }
            if (!this.f3055a.mIsListPanelShowing) {
                this.f3055a.showListPanel();
            }
        }
    }

    /* renamed from: com.etsy.android.ui.local.LocalSearchActivity.5 */
    class C07525 extends TrackingOnClickListener {
        final /* synthetic */ LocalSearchActivity f3056a;

        C07525(LocalSearchActivity localSearchActivity) {
            this.f3056a = localSearchActivity;
        }

        public void onViewClick(View view) {
            DeviceSettings.m3433a(this.f3056a);
        }
    }

    /* renamed from: com.etsy.android.ui.local.LocalSearchActivity.6 */
    class C07536 extends TrackingOnClickListener {
        final /* synthetic */ ConnectionResult f3057a;
        final /* synthetic */ LocalSearchActivity f3058b;

        C07536(LocalSearchActivity localSearchActivity, ConnectionResult connectionResult) {
            this.f3058b = localSearchActivity;
            this.f3057a = connectionResult;
        }

        public void onViewClick(View view) {
            try {
                this.f3057a.startResolutionForResult(this.f3058b, 0);
            } catch (SendIntentException e) {
                EtsyDebug.m1916d(LocalSearchActivity.TAG, "Error with resolution attempt");
            }
        }
    }

    /* renamed from: com.etsy.android.ui.local.LocalSearchActivity.7 */
    class C07547 extends TrackingOnClickListener {
        final /* synthetic */ LocalSearchActivity f3059a;

        C07547(LocalSearchActivity localSearchActivity) {
            this.f3059a = localSearchActivity;
        }

        public void onViewClick(View view) {
            EtsyGooglePlayServicesUtil.m2261c(this.f3059a);
        }
    }

    /* renamed from: com.etsy.android.ui.local.LocalSearchActivity.8 */
    class C07558 extends TrackingOnClickListener {
        final /* synthetic */ double f3060a;
        final /* synthetic */ double f3061b;
        final /* synthetic */ int f3062c;
        final /* synthetic */ LocalSearchActivity f3063d;

        C07558(LocalSearchActivity localSearchActivity, double d, double d2, int i) {
            this.f3063d = localSearchActivity;
            this.f3060a = d;
            this.f3061b = d2;
            this.f3062c = i;
        }

        public void onPreTrack() {
            addEventTrackedAttribute(AnalyticsLogAttribute.LAT, (Object) Double.valueOf(this.f3060a));
            addEventTrackedAttribute(AnalyticsLogAttribute.LON, (Object) Double.valueOf(this.f3061b));
        }

        public void onViewClick(View view) {
            LocalAnalytics.m4311a(this.f3060a, this.f3061b);
            this.f3063d.onNewSearchQuery(new LocalSearchQuery(this.f3060a, this.f3061b, this.f3062c));
        }
    }

    /* renamed from: com.etsy.android.ui.local.LocalSearchActivity.9 */
    class C07569 extends TrackingOnClickListener {
        final /* synthetic */ LocalSearchActivity f3064a;

        C07569(LocalSearchActivity localSearchActivity) {
            this.f3064a = localSearchActivity;
        }

        public void onViewClick(View view) {
            this.f3064a.expandSearchRadius();
        }
    }

    static {
        TAG = EtsyDebug.m1891a(LocalSearchActivity.class);
    }

    public LocalSearchActivity() {
        this.mBrowseUpdateListeners = new ArrayList();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903075);
        this.mEnableLocationView = findViewById(2131756332);
        this.mEnableLocationMessage = (TextView) findViewById(2131756333);
        this.mEnableLocationButton = (Button) findViewById(2131756334);
        this.mLocationManager = new LocalLocationApiManager(this, this);
        this.mInitialBrowseModule = (LocalBrowseModule) Parcels.m7495a(getIntent().getParcelableExtra("local_browse_module"));
        this.mStartOnFullMap = getIntent().getBooleanExtra(START_FULLSCREEN_MAP, false);
        if (bundle == null) {
            Nav.m4682a((FragmentActivity) this).m4684b().m4622a(2131755336).m4665q();
            if (!this.mStartOnFullMap) {
                showListPanel();
            }
        } else {
            this.mSearchResponse = (List) Parcels.m7495a(bundle.getParcelable(SEARCH_RESPONSE));
            this.mBrowseHistory = bundle.getParcelableArrayList(BROWSE_HISTORY);
            this.mIsListPanelShowing = bundle.getBoolean(IS_LIST_PANEL_SHOWING);
            this.mInitialLocation = (Location) bundle.getParcelable(INITIAL_LOCATION);
            restoreListPanelShownState();
            updateTitle();
        }
        showFilterBar(false);
        updateFilterBar();
    }

    protected void onStart() {
        super.onStart();
        this.mLocationManager.m4333a();
        bg.m3329b(false);
    }

    protected void onStop() {
        this.mLocationManager.m4335b();
        super.onStop();
        bg.m3328a(false);
    }

    public void onDestroy() {
        this.mLocationManager.m4336c();
        super.onDestroy();
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelable(SEARCH_RESPONSE, Parcels.m7494a(this.mSearchResponse));
        bundle.putParcelableArrayList(BROWSE_HISTORY, (ArrayList) this.mBrowseHistory);
        bundle.putBoolean(IS_LIST_PANEL_SHOWING, this.mIsListPanelShowing);
        bundle.putParcelable(INITIAL_LOCATION, this.mInitialLocation);
        super.onSaveInstanceState(bundle);
    }

    public boolean onCreateOptionsMenuWithIcons(Menu menu) {
        getMenuInflater().inflate(2131820551, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                navigateUpAsBack();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public void registerUpdateListener(LocalBrowseUpdateListener localBrowseUpdateListener) {
        if (this.mBrowseUpdateListeners == null) {
            this.mBrowseUpdateListeners = new ArrayList();
        }
        if (localBrowseUpdateListener != null && !this.mBrowseUpdateListeners.contains(localBrowseUpdateListener)) {
            this.mBrowseUpdateListeners.add(localBrowseUpdateListener);
        }
    }

    public void unregisterUpdateListener(LocalBrowseUpdateListener localBrowseUpdateListener) {
        if (this.mBrowseUpdateListeners != null && !this.mBrowseUpdateListeners.isEmpty()) {
            this.mBrowseUpdateListeners.remove(localBrowseUpdateListener);
        }
    }

    public void onClickMapArea() {
        LocalAnalytics.m4319a(getPageInView());
        hideListPanel();
    }

    public void onClickShowListView() {
        LocalAnalytics.m4323b(getPageInView());
        showListPanel();
    }

    public boolean isListPanelShowing() {
        return this.mIsListPanelShowing;
    }

    public void onLoadBrowseSection(@NonNull LocalBrowseModule localBrowseModule) {
        LocalBrowseLandingPage landingPage = localBrowseModule.getLandingPage();
        if (landingPage != null) {
            String title = localBrowseModule.getTitle();
            fetchSearchResults(landingPage.getApiPath());
            pushBrowseHistory(new LocalBrowseHistoryEntry(1, landingPage.getApiPath(), title));
            updateTitle();
            showFilterBar(true);
        }
    }

    public void onNewSearchQuery(@NonNull LocalSearchQuery localSearchQuery) {
        fetchSearchResults(localSearchQuery);
        LocalBrowseHistoryEntry localBrowseHistoryEntry = new LocalBrowseHistoryEntry(1, localSearchQuery, getString(R.local_browse_title_nearby));
        LocalBrowseHistoryEntry currentBrowseHistoryEntry = getCurrentBrowseHistoryEntry();
        if (currentBrowseHistoryEntry == null || currentBrowseHistoryEntry.m4283a() != 1) {
            pushBrowseHistory(localBrowseHistoryEntry);
        } else {
            replaceLastBrowseHistoryEntry(localBrowseHistoryEntry);
        }
        updateTitle();
    }

    public void onUserChangedMapPosition(double d, double d2, int i) {
        showSearchThisAreaButton(d, d2, i);
    }

    public void expandSearchRadius() {
        LocalBrowseHistoryEntry currentBrowseHistoryEntry = getCurrentBrowseHistoryEntry();
        if (!(currentBrowseHistoryEntry == null || currentBrowseHistoryEntry.m4283a() != 1 || currentBrowseHistoryEntry.m4285c() == null)) {
            LocalSearchQuery c = currentBrowseHistoryEntry.m4285c();
            onNewSearchQuery(new LocalSearchQuery(c.getLatitude(), c.getLongitude(), c.getOrDefaultRadius() * 2));
        }
        for (LocalBrowseUpdateListener onExpandSearchArea : this.mBrowseUpdateListeners) {
            onExpandSearchArea.onExpandSearchArea();
        }
    }

    public String getPageInView() {
        if (this.mInitialBrowseModule == null || this.mInitialBrowseModule.getLandingPage() == null) {
            return "local_browse_nearby";
        }
        return this.mInitialBrowseModule.getLandingPage().getAnalyticsEventName();
    }

    public void refreshLastRequest() {
        LocalBrowseHistoryEntry currentBrowseHistoryEntry = getCurrentBrowseHistoryEntry();
        if (currentBrowseHistoryEntry == null) {
            return;
        }
        if (currentBrowseHistoryEntry.m4285c() != null) {
            fetchSearchResults(currentBrowseHistoryEntry.m4285c());
        } else {
            fetchSearchResults(currentBrowseHistoryEntry.m4284b());
        }
    }

    public Location getInitialLocation() {
        return this.mInitialLocation;
    }

    public LocalBrowseResponse getCurrentBrowseResponse() {
        return null;
    }

    public List<LocalMarketCard> getCurrentSearchResponse() {
        return this.mSearchResponse;
    }

    public boolean isRequestPending() {
        return this.mIsRequestPending;
    }

    private void fetchSearchResults(LocalSearchQuery localSearchQuery) {
        if (localSearchQuery != null) {
            getRequestQueue().m1700a((Object) this);
            HttpRequestJobBuilder a = HttpRequestJobBuilder.m1712a(LocalMarketCard.class, "/etsyapps/v3/public/localmarkets/markets/search");
            Map hashMap = new HashMap();
            hashMap.put(ResponseConstants.LATITUDE, String.valueOf(localSearchQuery.getLatitude()));
            hashMap.put(ResponseConstants.LONGITUDE, String.valueOf(localSearchQuery.getLongitude()));
            hashMap.put(ResponseConstants.RADIUS, String.valueOf(localSearchQuery.getOrDefaultRadius()));
            a.m1745a(hashMap);
            addCommonHandlers(a);
            getRequestQueue().m1697a((Object) this, a.m1737a());
        }
    }

    private void fetchSearchResults(String str) {
        getRequestQueue().m1700a((Object) this);
        HttpRequestJobBuilder a = HttpRequestJobBuilder.m1712a(LocalMarketCard.class, str);
        addCommonHandlers(a);
        getRequestQueue().m1697a((Object) this, a.m1737a());
    }

    private void addCommonHandlers(HttpRequestJobBuilder httpRequestJobBuilder) {
        httpRequestJobBuilder.m1740a(new C07481(this));
        httpRequestJobBuilder.m1743a(new C07492(this));
        httpRequestJobBuilder.m1741a(new C07503(this));
        httpRequestJobBuilder.m1742a(new C07514(this));
    }

    private void showSearchingText() {
        if (this.mSearchingText != null) {
            this.mSearchingText.setVisibility(0);
        }
    }

    private void hideSearchingText() {
        if (this.mSearchingText != null) {
            this.mSearchingText.setVisibility(8);
        }
    }

    public void showEnableLocationMessage() {
        showEnableLocationDefaultView();
        this.mEnableLocationButton.setOnClickListener(new C07525(this));
    }

    public void showWaitingForLocationMessage() {
        showEnableLocationDefaultView();
        this.mEnableLocationMessage.setText(R.waiting_for_location_description);
        this.mEnableLocationButton.setVisibility(8);
    }

    public void hideEnableLocationMessage() {
        this.mEnableLocationView.setVisibility(8);
    }

    public void showGooglePlayResolution(ConnectionResult connectionResult) {
        showEnableLocationDefaultView();
        this.mEnableLocationButton.setOnClickListener(new C07536(this, connectionResult));
    }

    public void showGooglePlayErrorMessage(@StringRes int i) {
        showEnableLocationDefaultView();
        this.mEnableLocationButton.setText(i);
        this.mEnableLocationButton.setOnClickListener(new C07547(this));
    }

    public void onLocationReceived(Location location) {
        if (this.mSearchResponse == null) {
            this.mInitialLocation = location;
            for (LocalBrowseUpdateListener onInitialLocation : this.mBrowseUpdateListeners) {
                onInitialLocation.onInitialLocation(this.mInitialLocation);
            }
            if (this.mInitialBrowseModule != null) {
                onLoadBrowseSection(this.mInitialBrowseModule);
            } else {
                onNewSearchQuery(new LocalSearchQuery(this.mInitialLocation.getLatitude(), this.mInitialLocation.getLongitude()));
            }
            updateTitle();
        }
    }

    private void showEnableLocationDefaultView() {
        this.mEnableLocationView.setVisibility(0);
        this.mEnableLocationMessage.setText(R.enable_location_description);
        this.mEnableLocationButton.setVisibility(0);
        this.mEnableLocationButton.setText(R.enable_location);
    }

    private void showListPanel() {
        if (!isListPanelShowing()) {
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
            beginTransaction.setCustomAnimations(2130968601, 0);
            Fragment findFragmentById = supportFragmentManager.findFragmentById(2131755337);
            if (findFragmentById != null) {
                beginTransaction.show(findFragmentById);
            } else {
                beginTransaction.add(2131755337, new LocalSearchListFragment(), LocalBrowseListFragment.class.getSimpleName());
            }
            beginTransaction.commitAllowingStateLoss();
            this.mIsListPanelShowing = true;
            for (LocalBrowseUpdateListener onToggleListPanel : this.mBrowseUpdateListeners) {
                onToggleListPanel.onToggleListPanel();
            }
            updateFilterBar();
        }
    }

    private void hideListPanel() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Fragment findFragmentById = supportFragmentManager.findFragmentById(2131755337);
        if (findFragmentById != null) {
            FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
            beginTransaction.setCustomAnimations(0, 2130968600);
            beginTransaction.hide(findFragmentById);
            beginTransaction.commitAllowingStateLoss();
            this.mIsListPanelShowing = false;
            for (LocalBrowseUpdateListener onToggleListPanel : this.mBrowseUpdateListeners) {
                onToggleListPanel.onToggleListPanel();
            }
            updateFilterBar();
        }
    }

    private void restoreListPanelShownState() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Fragment findFragmentById = supportFragmentManager.findFragmentById(2131755337);
        if (findFragmentById != null) {
            FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
            if (this.mIsListPanelShowing) {
                beginTransaction.show(findFragmentById);
            } else {
                beginTransaction.hide(findFragmentById);
            }
            beginTransaction.commitAllowingStateLoss();
        }
    }

    private void showFilterBar(boolean z) {
        if (this.mFilterBar == null) {
            this.mFilterBar = getAppBarHelper().addExtension(z);
            this.mFilterBar.addView(getLayoutInflater().inflate(R.local_browse_filter_bar, this.mFilterBar, false));
        }
        this.mSearchingText = (LinearLayout) this.mFilterBar.findViewById(R.layout_searching_this_area);
    }

    private void updateFilterBar() {
        if (this.mFilterBar != null) {
            CharSequence quantityString;
            hideExpandRadiusButton();
            String str = StringUtils.EMPTY;
            if (this.mIsRequestPending) {
                Object obj = str;
            } else {
                int size;
                if (this.mSearchResponse != null) {
                    size = this.mSearchResponse.size();
                } else {
                    size = 0;
                }
                quantityString = getResources().getQuantityString(R.local_browse_results_summary, size, new Object[]{Integer.valueOf(size)});
                if (size == 0) {
                    showExpandRadiusButton();
                }
            }
            ((TextView) this.mFilterBar.findViewById(R.txt_filter_summary)).setText(quantityString);
            this.mFilterBar.findViewById(R.layout_filter_summary).setVisibility(0);
            this.mFilterBar.findViewById(R.txt_search_this_area).setVisibility(8);
        }
    }

    private void showSearchThisAreaButton(double d, double d2, int i) {
        if (this.mFilterBar != null) {
            this.mFilterBar.findViewById(R.layout_filter_summary).setVisibility(8);
            this.mSearchingText.setVisibility(8);
            this.mFilterBar.findViewById(R.txt_search_this_area).setVisibility(0);
            this.mFilterBar.findViewById(R.txt_search_this_area).setOnClickListener(new C07558(this, d, d2, i));
        }
    }

    private void showExpandRadiusButton() {
        if (this.mFilterBar != null) {
            this.mFilterBar.findViewById(R.txt_expand_radius).setVisibility(0);
            this.mFilterBar.findViewById(R.txt_expand_radius).setOnClickListener(new C07569(this));
        }
    }

    private void hideExpandRadiusButton() {
        if (this.mFilterBar != null) {
            this.mFilterBar.findViewById(R.txt_expand_radius).setVisibility(8);
        }
    }

    private void hideFilterBar() {
        getAppBarHelper().removeExtension(this.mFilterBar, true);
        this.mFilterBar = null;
        this.mSearchingText = null;
    }

    private void updateTitle() {
        LocalBrowseHistoryEntry currentBrowseHistoryEntry = getCurrentBrowseHistoryEntry();
        if (currentBrowseHistoryEntry != null) {
            getAppBarHelper().setTitle(currentBrowseHistoryEntry.m4286d());
        }
    }

    private void pushBrowseHistory(LocalBrowseHistoryEntry localBrowseHistoryEntry) {
        if (this.mBrowseHistory == null) {
            this.mBrowseHistory = new ArrayList();
        }
        this.mBrowseHistory.add(localBrowseHistoryEntry);
    }

    private LocalBrowseHistoryEntry popBrowseHistory() {
        if (this.mBrowseHistory == null || this.mBrowseHistory.isEmpty()) {
            return null;
        }
        return (LocalBrowseHistoryEntry) this.mBrowseHistory.remove(this.mBrowseHistory.size() - 1);
    }

    private void replaceLastBrowseHistoryEntry(LocalBrowseHistoryEntry localBrowseHistoryEntry) {
        popBrowseHistory();
        pushBrowseHistory(localBrowseHistoryEntry);
    }

    private LocalBrowseHistoryEntry getCurrentBrowseHistoryEntry() {
        if (this.mBrowseHistory == null || this.mBrowseHistory.isEmpty()) {
            return null;
        }
        return (LocalBrowseHistoryEntry) this.mBrowseHistory.get(this.mBrowseHistory.size() - 1);
    }

    private LocalBrowseHistoryEntry getPreviousBrowseHistoryEntry() {
        if (this.mBrowseHistory == null || this.mBrowseHistory.size() <= 1) {
            return null;
        }
        return (LocalBrowseHistoryEntry) this.mBrowseHistory.get(this.mBrowseHistory.size() - 2);
    }

    public void onBackPressed() {
        LocalBrowseHistoryEntry previousBrowseHistoryEntry = getPreviousBrowseHistoryEntry();
        if (this.mIsListPanelShowing && this.mStartOnFullMap) {
            hideListPanel();
        } else if (!this.mIsListPanelShowing && !this.mStartOnFullMap) {
            showListPanel();
        } else if (previousBrowseHistoryEntry != null) {
            if (previousBrowseHistoryEntry.m4285c() != null) {
                fetchSearchResults(previousBrowseHistoryEntry.m4285c());
            } else {
                fetchSearchResults(previousBrowseHistoryEntry.m4284b());
            }
            popBrowseHistory();
            updateTitle();
        } else {
            super.onBackPressed();
        }
    }
}
