package com.etsy.android.ui.local;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.ui.cardview.viewholders.LocalBrowseBaseHeaderViewHolder;
import com.etsy.android.uikit.adapter.SectionedRecyclerViewAdapter;

/* renamed from: com.etsy.android.ui.local.d */
class LocalBrowseHomescreenFragment extends SectionedRecyclerViewAdapter {
    final /* synthetic */ LocalBrowseHomescreenFragment f3066a;

    public LocalBrowseHomescreenFragment(LocalBrowseHomescreenFragment localBrowseHomescreenFragment, Context context, ImageBatch imageBatch) {
        this.f3066a = localBrowseHomescreenFragment;
        super(context, imageBatch);
    }

    public ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup, int i) {
        return new LocalBrowseHomescreenFragment(this.f3066a, this.mInflater, viewGroup);
    }

    public void onBindHeaderViewHolder(ViewHolder viewHolder, int i) {
        ((LocalBrowseBaseHeaderViewHolder) viewHolder).bind(true);
    }
}
r;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.EtsyJobResponse;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.HttpRequestJobBuilder;
import com.etsy.android.lib.core.ac;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.core.al;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.messaging.EtsyGooglePlayServicesUtil;
import com.etsy.android.lib.models.LocalMarketCard;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.LocalBrowseModule;
import com.etsy.android.lib.models.apiv3.LocalBrowseResponse;
import com.etsy.android.lib.models.interfaces.LocalMarketLike;
import com.etsy.android.lib.util.DeviceSettings;
import com.etsy.android.lib.util.av;
import com.etsy.android.lib.util.aw;
import com.etsy.android.lib.util.bg;
import com.etsy.android.lib.util.p013b.LocalMarketsUtil;
import com.etsy.android.ui.cardview.viewholders.LocalBrowseBaseHeaderViewHolder;
import com.etsy.android.ui.cardview.viewholders.LocalBrowseMarketViewHolder;
import com.etsy.android.ui.cardview.viewholders.LocalBrowseSectionFooterViewHolder;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.BaseRecyclerViewListFragment;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import com.etsy.android.uikit.util.TabletSupportUtil;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.ViewTreeObserverHelper;
import com.foresee.mobileReplay.recorder.ScreenRecorder;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.maps.C0739p;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.b;
import com.google.android.gms.maps.c;
import com.google.android.gms.maps.f;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.d;
import com.google.android.gms.maps.o;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.parceler.Parcels;

public class LocalBrowseHomescreenFragment extends BaseRecyclerViewListFragment implements OnRequestPermissionsResultCallback, LoaderCallbacks<Cursor>, al, LocalLocationApiManager, C0739p {
    private static final int BROWSE_RADIUS_SERVER_DEFAULT = -1;
    private static final String BROWSE_RESPONSE = "browse_response";
    private static final float DEFAULT_MAP_ZOOM_LEVEL = 10.0f;
    private static final String HISTORY_IDS = "history_ids";
    private static final String INITIAL_LOCATION = "initial_location";
    private static final String MAPVIEW_SAVEDINSTANCESTATE = "mapview_savedinstancestate";
    private static final int PERMISSIONS_RESULT = 0;
    private static final String TAG;
    private LocalBrowseResponse mBrowseResponse;
    private Button mEnableLocationButton;
    private TextView mEnableLocationMessage;
    private View mEnableLocationView;
    private c mGoogleMap;
    protected LocalBrowseBaseHeaderViewHolder mHeaderListener;
    private Location mInitialLocation;
    private View mListBg;
    private View mListBgForOverlap;
    private LocalLocationApiManager mLocationManager;
    private OnGlobalLayoutListener mMapLayoutListener;
    private MapView mMapView;
    private ViewTreeObserver mMapViewTreeObserver;
    private LocalMarketCardMarginDecoration mMarketCardDecoration;
    protected LocalBrowseMarketViewHolder mMarketListener;
    private ImageView mPlaceholder;
    private ArrayList<String> mRecentlyViewedMarketIds;
    private LocalBrowseSectionFooterViewHolder mSectionListener;

    /* renamed from: com.etsy.android.ui.local.LocalBrowseHomescreenFragment.15 */
    class AnonymousClass15 extends SpanSizeLookup {
        final /* synthetic */ BaseRecyclerViewAdapter f3029a;
        final /* synthetic */ int f3030b;
        final /* synthetic */ LocalBrowseHomescreenFragment f3031c;

        AnonymousClass15(LocalBrowseHomescreenFragment localBrowseHomescreenFragment, BaseRecyclerViewAdapter baseRecyclerViewAdapter, int i) {
            this.f3031c = localBrowseHomescreenFragment;
            this.f3029a = baseRecyclerViewAdapter;
            this.f3030b = i;
        }

        public int getSpanSize(int i) {
            return this.f3029a.getSpanSize(i, this.f3030b);
        }
    }

    /* renamed from: com.etsy.android.ui.local.LocalBrowseHomescreenFragment.1 */
    class C07301 implements LocalBrowseBaseHeaderViewHolder {
        final /* synthetic */ LocalBrowseHomescreenFragment f3034a;

        C07301(LocalBrowseHomescreenFragment localBrowseHomescreenFragment) {
            this.f3034a = localBrowseHomescreenFragment;
        }

        public void m4291a() {
            Nav.m4682a(this.f3034a.getActivity()).m4683a().m4466a(null, true);
        }
    }

    /* renamed from: com.etsy.android.ui.local.LocalBrowseHomescreenFragment.2 */
    class C07312 implements EtsyJobResponse {
        final /* synthetic */ LocalBrowseHomescreenFragment f3035a;

        C07312(LocalBrowseHomescreenFragment localBrowseHomescreenFragment) {
            this.f3035a = localBrowseHomescreenFragment;
        }

        public void m4292a(EtsyResult etsyResult) {
            this.f3035a.mBrowseResponse = new LocalBrowseResponse();
        }
    }

    /* renamed from: com.etsy.android.ui.local.LocalBrowseHomescreenFragment.3 */
    class C07323 implements EtsyJobResponse {
        final /* synthetic */ LocalBrowseHomescreenFragment f3036a;

        C07323(LocalBrowseHomescreenFragment localBrowseHomescreenFragment) {
            this.f3036a = localBrowseHomescreenFragment;
        }

        public void m4293a(int i, String str, EtsyResult etsyResult) {
            this.f3036a.mBrowseResponse = null;
            this.f3036a.onLoadFailure();
        }
    }

    /* renamed from: com.etsy.android.ui.local.LocalBrowseHomescreenFragment.4 */
    class C07334 implements OnGlobalLayoutListener {
        final /* synthetic */ LocalBrowseHomescreenFragment f3037a;

        C07334(LocalBrowseHomescreenFragment localBrowseHomescreenFragment) {
            this.f3037a = localBrowseHomescreenFragment;
        }

        public void onGlobalLayout() {
            ViewTreeObserverHelper.m5639b(this.f3037a.mMapViewTreeObserver, this.f3037a.mMapLayoutListener);
            this.f3037a.mMapViewTreeObserver = null;
            this.f3037a.mMapLayoutListener = null;
            this.f3037a.setupMap();
        }
    }

    /* renamed from: com.etsy.android.ui.local.LocalBrowseHomescreenFragment.5 */
    class C07345 implements f {
        final /* synthetic */ LocalBrowseHomescreenFragment f3038a;

        C07345(LocalBrowseHomescreenFragment localBrowseHomescreenFragment) {
            this.f3038a = localBrowseHomescreenFragment;
        }

        public void m4294a(CameraPosition cameraPosition) {
            this.f3038a.hidePlaceholder();
        }
    }

    /* renamed from: com.etsy.android.ui.local.LocalBrowseHomescreenFragment.6 */
    class C07356 extends TrackingOnClickListener {
        final /* synthetic */ LocalBrowseHomescreenFragment f3039a;

        C07356(LocalBrowseHomescreenFragment localBrowseHomescreenFragment) {
            this.f3039a = localBrowseHomescreenFragment;
        }

        public void onViewClick(View view) {
            DeviceSettings.m3433a(this.f3039a.getActivity());
        }
    }

    /* renamed from: com.etsy.android.ui.local.LocalBrowseHomescreenFragment.7 */
    class C07367 extends TrackingOnClickListener {
        final /* synthetic */ ConnectionResult f3040a;
        final /* synthetic */ LocalBrowseHomescreenFragment f3041b;

        C07367(LocalBrowseHomescreenFragment localBrowseHomescreenFragment, ConnectionResult connectionResult) {
            this.f3041b = localBrowseHomescreenFragment;
            this.f3040a = connectionResult;
        }

        public void onViewClick(View view) {
            try {
                this.f3040a.startResolutionForResult(this.f3041b.getActivity(), 0);
            } catch (SendIntentException e) {
                EtsyDebug.m1916d(LocalBrowseHomescreenFragment.TAG, "Error with resolution attempt");
            }
        }
    }

    /* renamed from: com.etsy.android.ui.local.LocalBrowseHomescreenFragment.8 */
    class C07378 extends TrackingOnClickListener {
        final /* synthetic */ LocalBrowseHomescreenFragment f3042a;

        C07378(LocalBrowseHomescreenFragment localBrowseHomescreenFragment) {
            this.f3042a = localBrowseHomescreenFragment;
        }

        public void onViewClick(View view) {
            EtsyGooglePlayServicesUtil.m2261c(this.f3042a.getActivity());
        }
    }

    /* renamed from: com.etsy.android.ui.local.LocalBrowseHomescreenFragment.9 */
    class C07389 extends TrackingOnClickListener {
        final /* synthetic */ LocalBrowseHomescreenFragment f3043a;

        C07389(LocalBrowseHomescreenFragment localBrowseHomescreenFragment) {
            this.f3043a = localBrowseHomescreenFragment;
        }

        public void onViewClick(View view) {
            if (this.f3043a.getActivity() == null || !aw.m3277b(this.f3043a.getActivity(), "android.permission.ACCESS_FINE_LOCATION")) {
                this.f3043a.requestPermissions();
            } else {
                Nav.m4682a(this.f3043a.getActivity()).m4686d().m4415a("android.permission.ACCESS_FINE_LOCATION");
            }
        }
    }

    static {
        TAG = EtsyDebug.m1891a(LocalBrowseHomescreenFragment.class);
    }

    public LocalBrowseHomescreenFragment() {
        this.mHeaderListener = new C07301(this);
        this.mMarketListener = new LocalBrowseMarketViewHolder() {
            final /* synthetic */ LocalBrowseHomescreenFragment f3024a;

            {
                this.f3024a = r1;
            }

            public void m4287a(LocalMarketCard localMarketCard) {
                LocalAnalytics.m4316a(localMarketCard, "local_browse");
                Nav.m4681a(this.f3024a.getActivity()).m4476a(localMarketCard.getLocalMarketId(), false);
            }
        };
        this.mSectionListener = new LocalBrowseSectionFooterViewHolder() {
            final /* synthetic */ LocalBrowseHomescreenFragment f3025a;

            {
                this.f3025a = r1;
            }

            public void m4288a(LocalBrowseModule localBrowseModule) {
                Nav.m4682a(this.f3025a.getActivity()).m4683a().m4466a(localBrowseModule, false);
            }
        };
        this.mRecentlyViewedMarketIds = new ArrayList();
    }

    @LayoutRes
    public int getLayoutId() {
        return 2130903215;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mAdapter = new LocalBrowseHomescreenFragment(this, getActivity(), getImageBatch());
        aw.m3274a(0, (OnRequestPermissionsResultCallback) this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle bundle2;
        bg.m3329b(false);
        setUpLayoutManager(this.mAdapter);
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mLocationManager = new LocalLocationApiManager(getActivity(), this);
        if (bundle != null) {
            bundle2 = bundle.getBundle(MAPVIEW_SAVEDINSTANCESTATE);
            this.mInitialLocation = (Location) bundle.getParcelable(INITIAL_LOCATION);
            this.mBrowseResponse = (LocalBrowseResponse) Parcels.m7495a(bundle.getParcelable(BROWSE_RESPONSE));
            bundle.remove(BROWSE_RESPONSE);
            this.mRecentlyViewedMarketIds = bundle.getStringArrayList(HISTORY_IDS);
        } else {
            bundle2 = null;
        }
        this.mMapView = (MapView) onCreateView.findViewById(R.map);
        this.mMapView.onCreate(bundle2);
        o.a(getActivity());
        onCreateView.findViewById(R.empty_button).setOnClickListener(new TrackingOnClickListener() {
            final /* synthetic */ LocalBrowseHomescreenFragment f3026a;

            {
                this.f3026a = r1;
            }

            public void onViewClick(View view) {
                if (this.f3026a.mBrowseResponse != null) {
                    this.f3026a.fetchBrowseResults(this.f3026a.mBrowseResponse.getRadius() * 2);
                } else {
                    this.f3026a.fetchBrowseResults(LocalBrowseHomescreenFragment.BROWSE_RADIUS_SERVER_DEFAULT);
                }
            }
        });
        OnClickListener anonymousClass13 = new TrackingOnClickListener() {
            final /* synthetic */ LocalBrowseHomescreenFragment f3027a;

            {
                this.f3027a = r1;
            }

            public void onViewClick(View view) {
                Nav.m4682a(this.f3027a.getActivity()).m4683a().m4466a(null, true);
            }
        };
        onCreateView.findViewById(2131756324).setOnClickListener(anonymousClass13);
        onCreateView.findViewById(2131756344).setOnClickListener(anonymousClass13);
        this.mRecyclerView.addItemDecoration(this.mMarketCardDecoration);
        this.mRecyclerView.addOnScrollListener(new OnScrollListener() {
            final /* synthetic */ LocalBrowseHomescreenFragment f3028a;

            {
                this.f3028a = r1;
            }

            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                if (((BaseRecyclerViewAdapter) recyclerView.getAdapter()).getHeaderCount() <= 0 || recyclerView.getChildAdapterPosition(recyclerView.getChildAt(0)) != 0) {
                    recyclerView.setVerticalScrollBarEnabled(true);
                } else {
                    recyclerView.setVerticalScrollBarEnabled(false);
                }
            }
        });
        aj.m1101a().m1112a((al) this);
        return onCreateView;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.orange));
        this.mEnableLocationView = view.findViewById(2131756332);
        this.mEnableLocationMessage = (TextView) view.findViewById(2131756333);
        this.mEnableLocationButton = (Button) view.findViewById(2131756334);
        this.mPlaceholder = (ImageView) view.findViewById(2131755723);
        if (this.mBrowseResponse == null) {
            this.mPlaceholder.setVisibility(0);
        }
        this.mListBg = view.findViewById(2131755721);
        this.mListBgForOverlap = view.findViewById(2131755724);
        if (getActivity() != null && aw.m3273a(getActivity(), "android.permission.ACCESS_FINE_LOCATION") != 0) {
            showEnablePermissionsView();
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (new TabletSupportUtil(getActivity().getApplicationContext()).m5622b()) {
            this.mListBg.setVisibility(0);
            this.mListBgForOverlap.setVisibility(8);
        }
        if (bundle == null) {
            getActivity().getSupportLoaderManager().initLoader(2, null, this);
        }
        this.mMapView.getMapAsync(this);
        if (this.mBrowseResponse != null && this.mBrowseResponse.getSections() != null) {
            fillAdapter(this.mBrowseResponse.getSections());
        }
    }

    public void onStart() {
        super.onStart();
        this.mLocationManager.m4333a();
    }

    public void onStop() {
        this.mLocationManager.m4335b();
        super.onStop();
    }

    public void onDestroyView() {
        aj.m1101a().m1115b((al) this);
        this.mLocationManager.m4336c();
        super.onDestroyView();
        bg.m3328a(false);
        if (!(this.mMapViewTreeObserver == null || this.mMapLayoutListener == null)) {
            ViewTreeObserverHelper.m5639b(this.mMapViewTreeObserver, this.mMapLayoutListener);
        }
        this.mMapViewTreeObserver = null;
        this.mMapLayoutListener = null;
        this.mMapView = null;
        this.mGoogleMap = null;
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (this.mMapView != null) {
            Bundle bundle2 = new Bundle(bundle);
            this.mMapView.onSaveInstanceState(bundle2);
            bundle.putBundle(MAPVIEW_SAVEDINSTANCESTATE, bundle2);
        }
        bundle.putParcelable(INITIAL_LOCATION, this.mInitialLocation);
        bundle.putParcelable(BROWSE_RESPONSE, Parcels.m7494a(this.mBrowseResponse));
        bundle.putStringArrayList(HISTORY_IDS, this.mRecentlyViewedMarketIds);
        super.onSaveInstanceState(bundle);
    }

    private void setUpLayoutManager(BaseRecyclerViewAdapter baseRecyclerViewAdapter) {
        int integer = getResources().getInteger(2131558410);
        LayoutManager gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), integer);
        SpanSizeLookup anonymousClass15 = new AnonymousClass15(this, baseRecyclerViewAdapter, integer);
        anonymousClass15.setSpanIndexCacheEnabled(true);
        gridLayoutManager.setSpanSizeLookup(anonymousClass15);
        this.mMarketCardDecoration = new LocalMarketCardMarginDecoration(getActivity(), anonymousClass15, false);
        this.mLayoutManager = gridLayoutManager;
    }

    protected void onLoadContent() {
        if (isRefreshing()) {
            refreshLocation();
        } else if (this.mBrowseResponse != null) {
            fillAdapter(this.mBrowseResponse.getSections());
        } else if (this.mInitialLocation != null) {
            fetchBrowseResults(BROWSE_RADIUS_SERVER_DEFAULT);
        }
    }

    private void fetchBrowseResults(int i) {
        if (this.mInitialLocation != null) {
            HttpRequestJobBuilder a = HttpRequestJobBuilder.m1712a(LocalBrowseResponse.class, aj.m1101a().m1118d() ? "/etsyapps/v3/bespoke/member/localmarkets/browse" : "/etsyapps/v3/bespoke/public/localmarkets/browse");
            Map hashMap = new HashMap();
            hashMap.put(ResponseConstants.LATITUDE, String.valueOf(this.mInitialLocation.getLatitude()));
            hashMap.put(ResponseConstants.LONGITUDE, String.valueOf(this.mInitialLocation.getLongitude()));
            if (i > 0) {
                hashMap.put(ResponseConstants.RADIUS, String.valueOf(i));
            }
            if (getActivity() != null) {
                hashMap.put("is_tablet", String.valueOf(new TabletSupportUtil(getActivity()).m5621a()));
            }
            a.m1745a(addRecentlyViewedMarkets(hashMap));
            a.m1740a(new ac() {
                final /* synthetic */ LocalBrowseHomescreenFragment f3032a;

                {
                    this.f3032a = r1;
                }

                public void m4289a() {
                    this.f3032a.showLoadingView();
                }
            });
            a.m1743a(new EtsyJobResponse<LocalBrowseResponse>() {
                final /* synthetic */ LocalBrowseHomescreenFragment f3033a;

                {
                    this.f3033a = r1;
                }

                public void m4290a(List<LocalBrowseResponse> list, int i, EtsyResult<LocalBrowseResponse> etsyResult) {
                    LocalBrowseResponse localBrowseResponse = (LocalBrowseResponse) list.get(0);
                    this.f3033a.mBrowseResponse = localBrowseResponse;
                    this.f3033a.fillMapMarkers();
                    if (this.f3033a.mBrowseResponse != null && this.f3033a.mBrowseResponse.getSections() != null) {
                        this.f3033a.fillAdapter(localBrowseResponse.getSections());
                    }
                }
            });
            a.m1741a(new C07312(this));
            a.m1742a(new C07323(this));
            getRequestQueue().m1697a((Object) this, a.m1737a());
        }
    }

    private Map<String, String> addRecentlyViewedMarkets(Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.mRecentlyViewedMarketIds.size(); i++) {
            stringBuilder.append((String) this.mRecentlyViewedMarketIds.get(i));
            if (i < this.mRecentlyViewedMarketIds.size() + BROWSE_RADIUS_SERVER_DEFAULT) {
                stringBuilder.append(",");
            }
        }
        if (stringBuilder.toString().length() > 0) {
            map.put("recent_market_ids", stringBuilder.toString());
        }
        return map;
    }

    private void fillAdapter(@NonNull List<LocalBrowseModule> list) {
        this.mAdapter.clear();
        this.mAdapter.addHeader(ScreenRecorder.ORIENTATION_SAMPLING_FREQ);
        List arrayList = new ArrayList();
        for (LocalBrowseModule localBrowseModule : list) {
            if (!(localBrowseModule.getLocalMarkets() == null || localBrowseModule.getLocalMarkets().isEmpty())) {
                LocalBrowseAdapter localBrowseAdapter = new LocalBrowseAdapter(getActivity(), getImageBatch(), this.mMarketListener, this.mSectionListener);
                localBrowseAdapter.addSectionData(localBrowseModule);
                localBrowseAdapter.addItems(localBrowseModule.getLocalMarkets());
                arrayList.add(localBrowseAdapter);
            }
        }
        onLoadSuccess(arrayList, 0);
    }

    public void onSignedInChanged(Context context, boolean z) {
        onRefresh();
    }

    public void onMapReady(c cVar) {
        this.mGoogleMap = cVar;
        if (this.mMapView != null) {
            this.mMapViewTreeObserver = this.mMapView.getViewTreeObserver();
            this.mMapLayoutListener = new C07334(this);
            ViewTreeObserverHelper.m5636a(this.mMapViewTreeObserver, this.mMapLayoutListener);
        }
    }

    private void setupMap() {
        if (this.mGoogleMap != null) {
            if (this.mInitialLocation != null) {
                this.mGoogleMap.a(b.a(new LatLng(this.mInitialLocation.getLatitude(), this.mInitialLocation.getLongitude()), DEFAULT_MAP_ZOOM_LEVEL));
            }
            if (this.mBrowseResponse != null) {
                fillMapMarkers();
            }
            this.mGoogleMap.a(new C07345(this));
        }
    }

    private void hidePlaceholder() {
        if (this.mBrowseResponse != null && this.mGoogleMap != null && this.mPlaceholder != null) {
            this.mPlaceholder.setVisibility(8);
        }
    }

    private void fillMapMarkers() {
        if (this.mGoogleMap != null) {
            this.mGoogleMap.c();
            if (this.mBrowseResponse != null && this.mBrowseResponse.getNearbyMarkets() != null && !this.mBrowseResponse.getNearbyMarkets().isEmpty()) {
                d dVar = new d();
                for (int i = 0; i < this.mBrowseResponse.getNearbyMarkets().size(); i++) {
                    LocalMarketLike localMarketLike = (LocalMarketCard) this.mBrowseResponse.getNearbyMarkets().get(i);
                    try {
                        LatLng latLng = new LatLng(Double.valueOf(localMarketLike.getLat()).doubleValue(), Double.valueOf(localMarketLike.getLon()).doubleValue());
                        this.mGoogleMap.a(new MarkerOptions().position(latLng).icon(LocalMarketsUtil.m3306a(localMarketLike)));
                        dVar.a(latLng);
                    } catch (Throwable e) {
                        EtsyDebug.m1917d(TAG, "Bad latitude and longitude for local market with ID " + localMarketLike.getLocalMarketId(), e);
                        EtsyLogger.m1966a().m1986a(TAG, "NumberFormatException in latitude / longitude for local market with ID " + localMarketLike.getLocalMarketId());
                    }
                }
                try {
                    this.mGoogleMap.a(b.a(dVar.a(), getResources().getDimensionPixelSize(2131362294)));
                } catch (Throwable e2) {
                    EtsyDebug.m1917d(TAG, "Bad argument when building bounds for Google map", e2);
                }
            } else if (this.mBrowseResponse != null) {
                hidePlaceholder();
            }
        }
    }

    public void showEnableLocationMessage() {
        showEnableLocationDefaultView();
        this.mEnableLocationButton.setOnClickListener(new C07356(this));
    }

    public void showWaitingForLocationMessage() {
        showEnableLocationDefaultView();
        this.mEnableLocationMessage.setText(R.waiting_for_location_description);
        this.mEnableLocationButton.setVisibility(8);
    }

    public void onLocationReceived(Location location) {
        this.mInitialLocation = location;
        setupMap();
        if (isRefreshing() || this.mBrowseResponse == null) {
            fetchBrowseResults(BROWSE_RADIUS_SERVER_DEFAULT);
        }
    }

    public void showGooglePlayResolution(ConnectionResult connectionResult) {
        showEnableLocationDefaultView();
        this.mEnableLocationButton.setOnClickListener(new C07367(this, connectionResult));
    }

    public void showGooglePlayErrorMessage(@StringRes int i) {
        showEnableLocationDefaultView();
        this.mEnableLocationButton.setText(i);
        this.mEnableLocationButton.setOnClickListener(new C07378(this));
    }

    public void hideEnableLocationMessage() {
        this.mEnableLocationView.setVisibility(8);
    }

    private void showEnableLocationDefaultView() {
        this.mEnableLocationView.setVisibility(0);
        this.mEnableLocationMessage.setText(R.enable_location_description);
        this.mEnableLocationButton.setVisibility(0);
        this.mEnableLocationButton.setText(R.enable_location);
    }

    private void showEnablePermissionsView() {
        this.mEnableLocationView.setVisibility(0);
        this.mEnableLocationButton.setVisibility(0);
        this.mEnableLocationButton.setText(R.enable_location);
        this.mEnableLocationMessage.setText(R.enable_location_description);
        this.mEnableLocationButton.setOnClickListener(new C07389(this));
    }

    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(getActivity(), LocalHistoryQuery.f3080a, LocalHistoryQuery.f3081b, null, null, "view_time DESC LIMIT 12");
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        this.mRecentlyViewedMarketIds.clear();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                this.mRecentlyViewedMarketIds.add(cursor.getString(1));
            }
        }
    }

    public void onLoaderReset(Loader<Cursor> loader) {
    }

    private void requestPermissions() {
        aw.m3276a(getActivity(), new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 0, (OnRequestPermissionsResultCallback) this);
    }

    private void refreshLocation() {
        if (getActivity() == null) {
            return;
        }
        if (aw.m3273a(getActivity(), "android.permission.ACCESS_FINE_LOCATION") == 0) {
            this.mLocationManager.m4337d();
        } else {
            requestPermissions();
        }
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (i != 0) {
            super.onRequestPermissionsResult(i, strArr, iArr);
        } else if (av.m3272a(iArr)) {
            refreshLocation();
        } else {
            showEnablePermissionsView();
        }
    }

    @NonNull
    public String getTrackingName() {
        return "local_browse";
    }
}
