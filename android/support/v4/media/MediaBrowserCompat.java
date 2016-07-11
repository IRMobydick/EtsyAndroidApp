package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.BundleCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.support.v4.os.ResultReceiver;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public final class MediaBrowserCompat {
    public static final String EXTRA_PAGE = "android.media.browse.extra.PAGE";
    public static final String EXTRA_PAGE_SIZE = "android.media.browse.extra.PAGE_SIZE";
    private static final String TAG = "MediaBrowserCompat";
    private final MediaBrowserImpl mImpl;

    class CallbackHandler extends Handler {
        private final MediaBrowserServiceCallbackImpl mCallbackImpl;
        private WeakReference<Messenger> mCallbacksMessengerRef;

        CallbackHandler(MediaBrowserServiceCallbackImpl mediaBrowserServiceCallbackImpl) {
            this.mCallbackImpl = mediaBrowserServiceCallbackImpl;
        }

        public void handleMessage(Message message) {
            if (this.mCallbacksMessengerRef != null) {
                Bundle data = message.getData();
                data.setClassLoader(MediaSessionCompat.class.getClassLoader());
                switch (message.what) {
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mCallbackImpl.onServiceConnected((Messenger) this.mCallbacksMessengerRef.get(), data.getString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID), (Token) data.getParcelable(MediaBrowserProtocol.DATA_MEDIA_SESSION_TOKEN), data.getBundle(MediaBrowserProtocol.DATA_ROOT_HINTS));
                    case Task.NETWORK_STATE_ANY /*2*/:
                        this.mCallbackImpl.onConnectionFailed((Messenger) this.mCallbacksMessengerRef.get());
                    case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                        this.mCallbackImpl.onLoadChildren((Messenger) this.mCallbacksMessengerRef.get(), data.getString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID), data.getParcelableArrayList(MediaBrowserProtocol.DATA_MEDIA_ITEM_LIST), data.getBundle(MediaBrowserProtocol.DATA_OPTIONS));
                    default:
                        Log.w(MediaBrowserCompat.TAG, "Unhandled message: " + message + "\n  Client version: " + 1 + "\n  Service version: " + message.arg1);
                }
            }
        }

        void setCallbacksMessenger(Messenger messenger) {
            this.mCallbacksMessengerRef = new WeakReference(messenger);
        }
    }

    public class ConnectionCallback {
        private ConnectionCallbackInternal mConnectionCallbackInternal;
        final Object mConnectionCallbackObj;

        interface ConnectionCallbackInternal {
            void onConnected();

            void onConnectionFailed();

            void onConnectionSuspended();
        }

        class StubApi21 implements ConnectionCallback {
            private StubApi21() {
            }

            public void onConnected() {
                if (ConnectionCallback.this.mConnectionCallbackInternal != null) {
                    ConnectionCallback.this.mConnectionCallbackInternal.onConnected();
                }
                ConnectionCallback.this.onConnected();
            }

            public void onConnectionSuspended() {
                if (ConnectionCallback.this.mConnectionCallbackInternal != null) {
                    ConnectionCallback.this.mConnectionCallbackInternal.onConnectionSuspended();
                }
                ConnectionCallback.this.onConnectionSuspended();
            }

            public void onConnectionFailed() {
                if (ConnectionCallback.this.mConnectionCallbackInternal != null) {
                    ConnectionCallback.this.mConnectionCallbackInternal.onConnectionFailed();
                }
                ConnectionCallback.this.onConnectionFailed();
            }
        }

        public ConnectionCallback() {
            if (VERSION.SDK_INT >= 21) {
                this.mConnectionCallbackObj = MediaBrowserCompatApi21.createConnectionCallback(new StubApi21());
            } else {
                this.mConnectionCallbackObj = null;
            }
        }

        public void onConnected() {
        }

        public void onConnectionSuspended() {
        }

        public void onConnectionFailed() {
        }

        void setInternalConnectionCallback(ConnectionCallbackInternal connectionCallbackInternal) {
            this.mConnectionCallbackInternal = connectionCallbackInternal;
        }
    }

    public abstract class ItemCallback {
        final Object mItemCallbackObj;

        class StubApi23 implements ItemCallback {
            private StubApi23() {
            }

            public void onItemLoaded(Parcel parcel) {
                parcel.setDataPosition(0);
                MediaItem mediaItem = (MediaItem) MediaItem.CREATOR.createFromParcel(parcel);
                parcel.recycle();
                ItemCallback.this.onItemLoaded(mediaItem);
            }

            public void onError(@NonNull String str) {
                ItemCallback.this.onError(str);
            }
        }

        public ItemCallback() {
            if (VERSION.SDK_INT >= 23) {
                this.mItemCallbackObj = MediaBrowserCompatApi23.createItemCallback(new StubApi23());
            } else {
                this.mItemCallbackObj = null;
            }
        }

        public void onItemLoaded(MediaItem mediaItem) {
        }

        public void onError(@NonNull String str) {
        }
    }

    class ItemReceiver extends ResultReceiver {
        private final ItemCallback mCallback;
        private final String mMediaId;

        ItemReceiver(String str, ItemCallback itemCallback, Handler handler) {
            super(handler);
            this.mMediaId = str;
            this.mCallback = itemCallback;
        }

        protected void onReceiveResult(int i, Bundle bundle) {
            bundle.setClassLoader(MediaBrowserCompat.class.getClassLoader());
            if (i == 0 && bundle != null && bundle.containsKey(MediaBrowserServiceCompat.KEY_MEDIA_ITEM)) {
                Parcelable parcelable = bundle.getParcelable(MediaBrowserServiceCompat.KEY_MEDIA_ITEM);
                if (parcelable instanceof MediaItem) {
                    this.mCallback.onItemLoaded((MediaItem) parcelable);
                    return;
                } else {
                    this.mCallback.onError(this.mMediaId);
                    return;
                }
            }
            this.mCallback.onError(this.mMediaId);
        }
    }

    interface MediaBrowserImpl {
        void connect();

        void disconnect();

        @Nullable
        Bundle getExtras();

        void getItem(@NonNull String str, @NonNull ItemCallback itemCallback);

        @NonNull
        String getRoot();

        ComponentName getServiceComponent();

        @NonNull
        Token getSessionToken();

        boolean isConnected();

        void subscribe(@NonNull String str, Bundle bundle, @NonNull SubscriptionCallback subscriptionCallback);

        void unsubscribe(@NonNull String str, Bundle bundle);
    }

    interface MediaBrowserServiceCallbackImpl {
        void onConnectionFailed(Messenger messenger);

        void onLoadChildren(Messenger messenger, String str, List list, Bundle bundle);

        void onServiceConnected(Messenger messenger, String str, Token token, Bundle bundle);
    }

    class MediaBrowserImplApi21 implements ConnectionCallbackInternal, MediaBrowserImpl, MediaBrowserServiceCallbackImpl {
        private static final boolean DBG = false;
        protected Object mBrowserObj;
        private Messenger mCallbacksMessenger;
        private final CallbackHandler mHandler;
        private ServiceBinderWrapper mServiceBinderWrapper;
        private final ComponentName mServiceComponent;
        private final ArrayMap<String, Subscription> mSubscriptions;

        /* renamed from: android.support.v4.media.MediaBrowserCompat.MediaBrowserImplApi21.1 */
        class C00921 implements Runnable {
            final /* synthetic */ ItemCallback val$cb;
            final /* synthetic */ String val$mediaId;

            C00921(ItemCallback itemCallback, String str) {
                this.val$cb = itemCallback;
                this.val$mediaId = str;
            }

            public void run() {
                this.val$cb.onError(this.val$mediaId);
            }
        }

        /* renamed from: android.support.v4.media.MediaBrowserCompat.MediaBrowserImplApi21.2 */
        class C00932 implements Runnable {
            final /* synthetic */ ItemCallback val$cb;

            C00932(ItemCallback itemCallback) {
                this.val$cb = itemCallback;
            }

            public void run() {
                this.val$cb.onItemLoaded(null);
            }
        }

        /* renamed from: android.support.v4.media.MediaBrowserCompat.MediaBrowserImplApi21.3 */
        class C00943 implements Runnable {
            final /* synthetic */ ItemCallback val$cb;
            final /* synthetic */ String val$mediaId;

            C00943(ItemCallback itemCallback, String str) {
                this.val$cb = itemCallback;
                this.val$mediaId = str;
            }

            public void run() {
                this.val$cb.onError(this.val$mediaId);
            }
        }

        public MediaBrowserImplApi21(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
            this.mHandler = new CallbackHandler(this);
            this.mSubscriptions = new ArrayMap();
            this.mServiceComponent = componentName;
            connectionCallback.setInternalConnectionCallback(this);
            this.mBrowserObj = MediaBrowserCompatApi21.createBrowser(context, componentName, connectionCallback.mConnectionCallbackObj, bundle);
        }

        public void connect() {
            MediaBrowserCompatApi21.connect(this.mBrowserObj);
        }

        public void disconnect() {
            if (!(this.mServiceBinderWrapper == null || this.mCallbacksMessenger == null)) {
                try {
                    this.mServiceBinderWrapper.unregisterCallbackMessenger(this.mCallbacksMessenger);
                } catch (RemoteException e) {
                    Log.i(MediaBrowserCompat.TAG, "Remote error unregistering client messenger.");
                }
            }
            MediaBrowserCompatApi21.disconnect(this.mBrowserObj);
        }

        public boolean isConnected() {
            return MediaBrowserCompatApi21.isConnected(this.mBrowserObj);
        }

        public ComponentName getServiceComponent() {
            return MediaBrowserCompatApi21.getServiceComponent(this.mBrowserObj);
        }

        @NonNull
        public String getRoot() {
            return MediaBrowserCompatApi21.getRoot(this.mBrowserObj);
        }

        @Nullable
        public Bundle getExtras() {
            return MediaBrowserCompatApi21.getExtras(this.mBrowserObj);
        }

        @NonNull
        public Token getSessionToken() {
            return Token.fromToken(MediaBrowserCompatApi21.getSessionToken(this.mBrowserObj));
        }

        public void subscribe(@NonNull String str, Bundle bundle, @NonNull SubscriptionCallback subscriptionCallback) {
            SubscriptionCallback subscriptionCallbackApi21 = new SubscriptionCallbackApi21(subscriptionCallback, bundle);
            Subscription subscription = (Subscription) this.mSubscriptions.get(str);
            if (subscription == null) {
                subscription = new Subscription();
                this.mSubscriptions.put(str, subscription);
            }
            subscription.setCallbackForOptions(subscriptionCallbackApi21, bundle);
            if (!MediaBrowserCompatApi21.isConnected(this.mBrowserObj)) {
                return;
            }
            if (bundle == null || this.mServiceBinderWrapper == null) {
                MediaBrowserCompatApi21.subscribe(this.mBrowserObj, str, subscriptionCallbackApi21.mSubscriptionCallbackObj);
                return;
            }
            try {
                this.mServiceBinderWrapper.addSubscription(str, bundle, this.mCallbacksMessenger);
            } catch (RemoteException e) {
                Log.i(MediaBrowserCompat.TAG, "Remote error subscribing media item: " + str);
            }
        }

        public void unsubscribe(@NonNull String str, Bundle bundle) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("parentId is empty.");
            }
            Subscription subscription = (Subscription) this.mSubscriptions.get(str);
            if (subscription != null && subscription.remove(bundle)) {
                if (bundle == null || this.mServiceBinderWrapper == null) {
                    if (this.mServiceBinderWrapper != null || subscription.isEmpty()) {
                        MediaBrowserCompatApi21.unsubscribe(this.mBrowserObj, str);
                    }
                } else if (this.mServiceBinderWrapper == null) {
                    try {
                        this.mServiceBinderWrapper.removeSubscription(str, bundle, this.mCallbacksMessenger);
                    } catch (RemoteException e) {
                        Log.d(MediaBrowserCompat.TAG, "removeSubscription failed with RemoteException parentId=" + str);
                    }
                }
            }
            if (subscription != null && subscription.isEmpty()) {
                this.mSubscriptions.remove(str);
            }
        }

        public void getItem(@NonNull String str, @NonNull ItemCallback itemCallback) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("mediaId is empty.");
            } else if (itemCallback == null) {
                throw new IllegalArgumentException("cb is null.");
            } else if (!MediaBrowserCompatApi21.isConnected(this.mBrowserObj)) {
                Log.i(MediaBrowserCompat.TAG, "Not connected, unable to retrieve the MediaItem.");
                this.mHandler.post(new C00921(itemCallback, str));
            } else if (this.mServiceBinderWrapper == null) {
                this.mHandler.post(new C00932(itemCallback));
            } else {
                try {
                    this.mServiceBinderWrapper.getMediaItem(str, new ItemReceiver(str, itemCallback, this.mHandler));
                } catch (RemoteException e) {
                    Log.i(MediaBrowserCompat.TAG, "Remote error getting media item: " + str);
                    this.mHandler.post(new C00943(itemCallback, str));
                }
            }
        }

        public void onConnected() {
            Bundle extras = MediaBrowserCompatApi21.getExtras(this.mBrowserObj);
            if (extras != null) {
                IBinder binder = BundleCompat.getBinder(extras, MediaBrowserProtocol.EXTRA_MESSENGER_BINDER);
                if (binder != null) {
                    this.mServiceBinderWrapper = new ServiceBinderWrapper(binder);
                    this.mCallbacksMessenger = new Messenger(this.mHandler);
                    this.mHandler.setCallbacksMessenger(this.mCallbacksMessenger);
                    try {
                        this.mServiceBinderWrapper.registerCallbackMessenger(this.mCallbacksMessenger);
                    } catch (RemoteException e) {
                        Log.i(MediaBrowserCompat.TAG, "Remote error registering client messenger.");
                    }
                    onServiceConnected(this.mCallbacksMessenger, null, null, null);
                }
            }
        }

        public void onConnectionSuspended() {
            this.mServiceBinderWrapper = null;
            this.mCallbacksMessenger = null;
            this.mHandler.setCallbacksMessenger(null);
        }

        public void onConnectionFailed() {
        }

        public void onServiceConnected(Messenger messenger, String str, Token token, Bundle bundle) {
            for (Entry entry : this.mSubscriptions.entrySet()) {
                String str2 = (String) entry.getKey();
                Subscription subscription = (Subscription) entry.getValue();
                List optionsList = subscription.getOptionsList();
                List callbacks = subscription.getCallbacks();
                for (int i = 0; i < optionsList.size(); i++) {
                    if (optionsList.get(i) == null) {
                        MediaBrowserCompatApi21.subscribe(this.mBrowserObj, str2, ((SubscriptionCallbackApi21) callbacks.get(i)).mSubscriptionCallbackObj);
                    } else {
                        try {
                            this.mServiceBinderWrapper.addSubscription(str2, (Bundle) optionsList.get(i), this.mCallbacksMessenger);
                        } catch (RemoteException e) {
                            Log.d(MediaBrowserCompat.TAG, "addSubscription failed with RemoteException parentId=" + str2);
                        }
                    }
                }
            }
        }

        public void onConnectionFailed(Messenger messenger) {
        }

        public void onLoadChildren(Messenger messenger, String str, List list, @NonNull Bundle bundle) {
            if (this.mCallbacksMessenger == messenger) {
                Subscription subscription = (Subscription) this.mSubscriptions.get(str);
                if (subscription != null) {
                    subscription.getCallback(bundle).onChildrenLoaded(str, list, bundle);
                }
            }
        }
    }

    class MediaBrowserImplApi23 extends MediaBrowserImplApi21 {
        public MediaBrowserImplApi23(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
            super(context, componentName, connectionCallback, bundle);
        }

        public void getItem(@NonNull String str, @NonNull ItemCallback itemCallback) {
            MediaBrowserCompatApi23.getItem(this.mBrowserObj, str, itemCallback.mItemCallbackObj);
        }
    }

    class MediaBrowserImplBase implements MediaBrowserImpl, MediaBrowserServiceCallbackImpl {
        private static final int CONNECT_STATE_CONNECTED = 2;
        private static final int CONNECT_STATE_CONNECTING = 1;
        private static final int CONNECT_STATE_DISCONNECTED = 0;
        private static final int CONNECT_STATE_SUSPENDED = 3;
        private static final boolean DBG = false;
        private final ConnectionCallback mCallback;
        private Messenger mCallbacksMessenger;
        private final Context mContext;
        private Bundle mExtras;
        private final CallbackHandler mHandler;
        private Token mMediaSessionToken;
        private final Bundle mRootHints;
        private String mRootId;
        private ServiceBinderWrapper mServiceBinderWrapper;
        private final ComponentName mServiceComponent;
        private MediaServiceConnection mServiceConnection;
        private int mState;
        private final ArrayMap<String, Subscription> mSubscriptions;

        /* renamed from: android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.1 */
        class C00951 implements Runnable {
            final /* synthetic */ ServiceConnection val$thisConnection;

            C00951(ServiceConnection serviceConnection) {
                this.val$thisConnection = serviceConnection;
            }

            public void run() {
                if (this.val$thisConnection == MediaBrowserImplBase.this.mServiceConnection) {
                    MediaBrowserImplBase.this.forceCloseConnection();
                    MediaBrowserImplBase.this.mCallback.onConnectionFailed();
                }
            }
        }

        /* renamed from: android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.2 */
        class C00962 implements Runnable {
            final /* synthetic */ ItemCallback val$cb;
            final /* synthetic */ String val$mediaId;

            C00962(ItemCallback itemCallback, String str) {
                this.val$cb = itemCallback;
                this.val$mediaId = str;
            }

            public void run() {
                this.val$cb.onError(this.val$mediaId);
            }
        }

        /* renamed from: android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.3 */
        class C00973 implements Runnable {
            final /* synthetic */ ItemCallback val$cb;
            final /* synthetic */ String val$mediaId;

            C00973(ItemCallback itemCallback, String str) {
                this.val$cb = itemCallback;
                this.val$mediaId = str;
            }

            public void run() {
                this.val$cb.onError(this.val$mediaId);
            }
        }

        class MediaServiceConnection implements ServiceConnection {

            /* renamed from: android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection.1 */
            class C00981 implements Runnable {
                final /* synthetic */ IBinder val$binder;
                final /* synthetic */ ComponentName val$name;

                C00981(ComponentName componentName, IBinder iBinder) {
                    this.val$name = componentName;
                    this.val$binder = iBinder;
                }

                public void run() {
                    if (MediaServiceConnection.this.isCurrent("onServiceConnected")) {
                        MediaBrowserImplBase.this.mServiceBinderWrapper = new ServiceBinderWrapper(this.val$binder);
                        MediaBrowserImplBase.this.mCallbacksMessenger = new Messenger(MediaBrowserImplBase.this.mHandler);
                        MediaBrowserImplBase.this.mHandler.setCallbacksMessenger(MediaBrowserImplBase.this.mCallbacksMessenger);
                        MediaBrowserImplBase.this.mState = MediaBrowserImplBase.CONNECT_STATE_CONNECTING;
                        try {
                            MediaBrowserImplBase.this.mServiceBinderWrapper.connect(MediaBrowserImplBase.this.mContext, MediaBrowserImplBase.this.mRootHints, MediaBrowserImplBase.this.mCallbacksMessenger);
                        } catch (RemoteException e) {
                            Log.w(MediaBrowserCompat.TAG, "RemoteException during connect for " + MediaBrowserImplBase.this.mServiceComponent);
                        }
                    }
                }
            }

            /* renamed from: android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection.2 */
            class C00992 implements Runnable {
                final /* synthetic */ ComponentName val$name;

                C00992(ComponentName componentName) {
                    this.val$name = componentName;
                }

                public void run() {
                    if (MediaServiceConnection.this.isCurrent("onServiceDisconnected")) {
                        MediaBrowserImplBase.this.mServiceBinderWrapper = null;
                        MediaBrowserImplBase.this.mCallbacksMessenger = null;
                        MediaBrowserImplBase.this.mHandler.setCallbacksMessenger(null);
                        MediaBrowserImplBase.this.mState = MediaBrowserImplBase.CONNECT_STATE_SUSPENDED;
                        MediaBrowserImplBase.this.mCallback.onConnectionSuspended();
                    }
                }
            }

            private MediaServiceConnection() {
            }

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                postOrRun(new C00981(componentName, iBinder));
            }

            public void onServiceDisconnected(ComponentName componentName) {
                postOrRun(new C00992(componentName));
            }

            private void postOrRun(Runnable runnable) {
                if (Thread.currentThread() == MediaBrowserImplBase.this.mHandler.getLooper().getThread()) {
                    runnable.run();
                } else {
                    MediaBrowserImplBase.this.mHandler.post(runnable);
                }
            }

            private boolean isCurrent(String str) {
                if (MediaBrowserImplBase.this.mServiceConnection == this) {
                    return true;
                }
                if (MediaBrowserImplBase.this.mState != 0) {
                    Log.i(MediaBrowserCompat.TAG, str + " for " + MediaBrowserImplBase.this.mServiceComponent + " with mServiceConnection=" + MediaBrowserImplBase.this.mServiceConnection + " this=" + this);
                }
                return false;
            }
        }

        public MediaBrowserImplBase(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
            this.mHandler = new CallbackHandler(this);
            this.mSubscriptions = new ArrayMap();
            this.mState = CONNECT_STATE_DISCONNECTED;
            if (context == null) {
                throw new IllegalArgumentException("context must not be null");
            } else if (componentName == null) {
                throw new IllegalArgumentException("service component must not be null");
            } else if (connectionCallback == null) {
                throw new IllegalArgumentException("connection callback must not be null");
            } else {
                this.mContext = context;
                this.mServiceComponent = componentName;
                this.mCallback = connectionCallback;
                this.mRootHints = bundle;
            }
        }

        public void connect() {
            if (this.mState != 0) {
                throw new IllegalStateException("connect() called while not disconnected (state=" + getStateLabel(this.mState) + ")");
            } else if (this.mServiceBinderWrapper != null) {
                throw new RuntimeException("mServiceBinderWrapper should be null. Instead it is " + this.mServiceBinderWrapper);
            } else if (this.mCallbacksMessenger != null) {
                throw new RuntimeException("mCallbacksMessenger should be null. Instead it is " + this.mCallbacksMessenger);
            } else {
                this.mState = CONNECT_STATE_CONNECTING;
                Intent intent = new Intent(MediaBrowserServiceCompat.SERVICE_INTERFACE);
                intent.setComponent(this.mServiceComponent);
                ServiceConnection mediaServiceConnection = new MediaServiceConnection();
                this.mServiceConnection = mediaServiceConnection;
                boolean z = false;
                try {
                    z = this.mContext.bindService(intent, this.mServiceConnection, CONNECT_STATE_CONNECTING);
                } catch (Exception e) {
                    Log.e(MediaBrowserCompat.TAG, "Failed binding to service " + this.mServiceComponent);
                }
                if (!z) {
                    this.mHandler.post(new C00951(mediaServiceConnection));
                }
            }
        }

        public void disconnect() {
            if (this.mCallbacksMessenger != null) {
                try {
                    this.mServiceBinderWrapper.disconnect(this.mCallbacksMessenger);
                } catch (RemoteException e) {
                    Log.w(MediaBrowserCompat.TAG, "RemoteException during connect for " + this.mServiceComponent);
                }
            }
            forceCloseConnection();
        }

        private void forceCloseConnection() {
            if (this.mServiceConnection != null) {
                this.mContext.unbindService(this.mServiceConnection);
            }
            this.mState = CONNECT_STATE_DISCONNECTED;
            this.mServiceConnection = null;
            this.mServiceBinderWrapper = null;
            this.mCallbacksMessenger = null;
            this.mHandler.setCallbacksMessenger(null);
            this.mRootId = null;
            this.mMediaSessionToken = null;
        }

        public boolean isConnected() {
            return this.mState == CONNECT_STATE_CONNECTED;
        }

        @NonNull
        public ComponentName getServiceComponent() {
            if (isConnected()) {
                return this.mServiceComponent;
            }
            throw new IllegalStateException("getServiceComponent() called while not connected (state=" + this.mState + ")");
        }

        @NonNull
        public String getRoot() {
            if (isConnected()) {
                return this.mRootId;
            }
            throw new IllegalStateException("getRoot() called while not connected(state=" + getStateLabel(this.mState) + ")");
        }

        @Nullable
        public Bundle getExtras() {
            if (isConnected()) {
                return this.mExtras;
            }
            throw new IllegalStateException("getExtras() called while not connected (state=" + getStateLabel(this.mState) + ")");
        }

        @NonNull
        public Token getSessionToken() {
            if (isConnected()) {
                return this.mMediaSessionToken;
            }
            throw new IllegalStateException("getSessionToken() called while not connected(state=" + this.mState + ")");
        }

        public void subscribe(@NonNull String str, Bundle bundle, @NonNull SubscriptionCallback subscriptionCallback) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("parentId is empty.");
            } else if (subscriptionCallback == null) {
                throw new IllegalArgumentException("callback is null");
            } else {
                Subscription subscription = (Subscription) this.mSubscriptions.get(str);
                if (subscription == null) {
                    subscription = new Subscription();
                    this.mSubscriptions.put(str, subscription);
                }
                subscription.setCallbackForOptions(subscriptionCallback, bundle);
                if (this.mState == CONNECT_STATE_CONNECTED) {
                    try {
                        this.mServiceBinderWrapper.addSubscription(str, bundle, this.mCallbacksMessenger);
                    } catch (RemoteException e) {
                        Log.d(MediaBrowserCompat.TAG, "addSubscription failed with RemoteException parentId=" + str);
                    }
                }
            }
        }

        public void unsubscribe(@NonNull String str, Bundle bundle) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("parentId is empty.");
            }
            Subscription subscription = (Subscription) this.mSubscriptions.get(str);
            if (subscription != null && subscription.remove(bundle) && this.mState == CONNECT_STATE_CONNECTED) {
                try {
                    this.mServiceBinderWrapper.removeSubscription(str, bundle, this.mCallbacksMessenger);
                } catch (RemoteException e) {
                    Log.d(MediaBrowserCompat.TAG, "removeSubscription failed with RemoteException parentId=" + str);
                }
            }
            if (subscription != null && subscription.isEmpty()) {
                this.mSubscriptions.remove(str);
            }
        }

        public void getItem(@NonNull String str, @NonNull ItemCallback itemCallback) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("mediaId is empty.");
            } else if (itemCallback == null) {
                throw new IllegalArgumentException("cb is null.");
            } else if (this.mState != CONNECT_STATE_CONNECTED) {
                Log.i(MediaBrowserCompat.TAG, "Not connected, unable to retrieve the MediaItem.");
                this.mHandler.post(new C00962(itemCallback, str));
            } else {
                try {
                    this.mServiceBinderWrapper.getMediaItem(str, new ItemReceiver(str, itemCallback, this.mHandler));
                } catch (RemoteException e) {
                    Log.i(MediaBrowserCompat.TAG, "Remote error getting media item.");
                    this.mHandler.post(new C00973(itemCallback, str));
                }
            }
        }

        public void onServiceConnected(Messenger messenger, String str, Token token, Bundle bundle) {
            if (!isCurrent(messenger, "onConnect")) {
                return;
            }
            if (this.mState != CONNECT_STATE_CONNECTING) {
                Log.w(MediaBrowserCompat.TAG, "onConnect from service while mState=" + getStateLabel(this.mState) + "... ignoring");
                return;
            }
            this.mRootId = str;
            this.mMediaSessionToken = token;
            this.mExtras = bundle;
            this.mState = CONNECT_STATE_CONNECTED;
            this.mCallback.onConnected();
            try {
                for (Entry entry : this.mSubscriptions.entrySet()) {
                    String str2 = (String) entry.getKey();
                    for (Bundle addSubscription : ((Subscription) entry.getValue()).getOptionsList()) {
                        this.mServiceBinderWrapper.addSubscription(str2, addSubscription, this.mCallbacksMessenger);
                    }
                }
            } catch (RemoteException e) {
                Log.d(MediaBrowserCompat.TAG, "addSubscription failed with RemoteException.");
            }
        }

        public void onConnectionFailed(Messenger messenger) {
            Log.e(MediaBrowserCompat.TAG, "onConnectFailed for " + this.mServiceComponent);
            if (!isCurrent(messenger, "onConnectFailed")) {
                return;
            }
            if (this.mState != CONNECT_STATE_CONNECTING) {
                Log.w(MediaBrowserCompat.TAG, "onConnect from service while mState=" + getStateLabel(this.mState) + "... ignoring");
                return;
            }
            forceCloseConnection();
            this.mCallback.onConnectionFailed();
        }

        public void onLoadChildren(Messenger messenger, String str, List list, Bundle bundle) {
            if (isCurrent(messenger, "onLoadChildren")) {
                Subscription subscription = (Subscription) this.mSubscriptions.get(str);
                if (subscription != null) {
                    SubscriptionCallback callback = subscription.getCallback(bundle);
                    if (callback == null) {
                        return;
                    }
                    if (bundle == null) {
                        callback.onChildrenLoaded(str, list);
                    } else {
                        callback.onChildrenLoaded(str, list, bundle);
                    }
                }
            }
        }

        private static String getStateLabel(int i) {
            switch (i) {
                case CONNECT_STATE_DISCONNECTED /*0*/:
                    return "CONNECT_STATE_DISCONNECTED";
                case CONNECT_STATE_CONNECTING /*1*/:
                    return "CONNECT_STATE_CONNECTING";
                case CONNECT_STATE_CONNECTED /*2*/:
                    return "CONNECT_STATE_CONNECTED";
                case CONNECT_STATE_SUSPENDED /*3*/:
                    return "CONNECT_STATE_SUSPENDED";
                default:
                    return "UNKNOWN/" + i;
            }
        }

        private boolean isCurrent(Messenger messenger, String str) {
            if (this.mCallbacksMessenger == messenger) {
                return true;
            }
            if (this.mState != 0) {
                Log.i(MediaBrowserCompat.TAG, str + " for " + this.mServiceComponent + " with mCallbacksMessenger=" + this.mCallbacksMessenger + " this=" + this);
            }
            return false;
        }

        void dump() {
            Log.d(MediaBrowserCompat.TAG, "MediaBrowserCompat...");
            Log.d(MediaBrowserCompat.TAG, "  mServiceComponent=" + this.mServiceComponent);
            Log.d(MediaBrowserCompat.TAG, "  mCallback=" + this.mCallback);
            Log.d(MediaBrowserCompat.TAG, "  mRootHints=" + this.mRootHints);
            Log.d(MediaBrowserCompat.TAG, "  mState=" + getStateLabel(this.mState));
            Log.d(MediaBrowserCompat.TAG, "  mServiceConnection=" + this.mServiceConnection);
            Log.d(MediaBrowserCompat.TAG, "  mServiceBinderWrapper=" + this.mServiceBinderWrapper);
            Log.d(MediaBrowserCompat.TAG, "  mCallbacksMessenger=" + this.mCallbacksMessenger);
            Log.d(MediaBrowserCompat.TAG, "  mRootId=" + this.mRootId);
            Log.d(MediaBrowserCompat.TAG, "  mMediaSessionToken=" + this.mMediaSessionToken);
        }
    }

    public class MediaItem implements Parcelable {
        public static final Creator<MediaItem> CREATOR;
        public static final int FLAG_BROWSABLE = 1;
        public static final int FLAG_PLAYABLE = 2;
        private final MediaDescriptionCompat mDescription;
        private final int mFlags;

        /* renamed from: android.support.v4.media.MediaBrowserCompat.MediaItem.1 */
        final class C01001 implements Creator<MediaItem> {
            C01001() {
            }

            public MediaItem createFromParcel(Parcel parcel) {
                return new MediaItem(null);
            }

            public MediaItem[] newArray(int i) {
                return new MediaItem[i];
            }
        }

        @Retention(RetentionPolicy.SOURCE)
        public @interface Flags {
        }

        public MediaItem(@NonNull MediaDescriptionCompat mediaDescriptionCompat, int i) {
            if (mediaDescriptionCompat == null) {
                throw new IllegalArgumentException("description cannot be null");
            } else if (TextUtils.isEmpty(mediaDescriptionCompat.getMediaId())) {
                throw new IllegalArgumentException("description must have a non-empty media id");
            } else {
                this.mFlags = i;
                this.mDescription = mediaDescriptionCompat;
            }
        }

        private MediaItem(Parcel parcel) {
            this.mFlags = parcel.readInt();
            this.mDescription = (MediaDescriptionCompat) MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mFlags);
            this.mDescription.writeToParcel(parcel, i);
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("MediaItem{");
            stringBuilder.append("mFlags=").append(this.mFlags);
            stringBuilder.append(", mDescription=").append(this.mDescription);
            stringBuilder.append('}');
            return stringBuilder.toString();
        }

        static {
            CREATOR = new C01001();
        }

        public int getFlags() {
            return this.mFlags;
        }

        public boolean isBrowsable() {
            return (this.mFlags & FLAG_BROWSABLE) != 0;
        }

        public boolean isPlayable() {
            return (this.mFlags & FLAG_PLAYABLE) != 0;
        }

        @NonNull
        public MediaDescriptionCompat getDescription() {
            return this.mDescription;
        }

        @NonNull
        public String getMediaId() {
            return this.mDescription.getMediaId();
        }
    }

    class ServiceBinderWrapper {
        private Messenger mMessenger;

        public ServiceBinderWrapper(IBinder iBinder) {
            this.mMessenger = new Messenger(iBinder);
        }

        void connect(Context context, Bundle bundle, Messenger messenger) {
            Bundle bundle2 = new Bundle();
            bundle2.putString(MediaBrowserProtocol.DATA_PACKAGE_NAME, context.getPackageName());
            bundle2.putBundle(MediaBrowserProtocol.DATA_ROOT_HINTS, bundle);
            sendRequest(1, bundle2, messenger);
        }

        void disconnect(Messenger messenger) {
            sendRequest(2, null, messenger);
        }

        void addSubscription(String str, Bundle bundle, Messenger messenger) {
            Bundle bundle2 = new Bundle();
            bundle2.putString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID, str);
            bundle2.putBundle(MediaBrowserProtocol.DATA_OPTIONS, bundle);
            sendRequest(3, bundle2, messenger);
        }

        void removeSubscription(String str, Bundle bundle, Messenger messenger) {
            Bundle bundle2 = new Bundle();
            bundle2.putString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID, str);
            bundle2.putBundle(MediaBrowserProtocol.DATA_OPTIONS, bundle);
            sendRequest(4, bundle2, messenger);
        }

        void getMediaItem(String str, ResultReceiver resultReceiver) {
            Bundle bundle = new Bundle();
            bundle.putString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID, str);
            bundle.putParcelable(MediaBrowserProtocol.DATA_RESULT_RECEIVER, resultReceiver);
            sendRequest(5, bundle, null);
        }

        void registerCallbackMessenger(Messenger messenger) {
            sendRequest(6, null, messenger);
        }

        void unregisterCallbackMessenger(Messenger messenger) {
            sendRequest(7, null, messenger);
        }

        private void sendRequest(int i, Bundle bundle, Messenger messenger) {
            Message obtain = Message.obtain();
            obtain.what = i;
            obtain.arg1 = 1;
            obtain.setData(bundle);
            obtain.replyTo = messenger;
            this.mMessenger.send(obtain);
        }
    }

    class Subscription {
        private final List<SubscriptionCallback> mCallbacks;
        private final List<Bundle> mOptionsList;

        public Subscription() {
            this.mCallbacks = new ArrayList();
            this.mOptionsList = new ArrayList();
        }

        public boolean isEmpty() {
            return this.mCallbacks.isEmpty();
        }

        public List<Bundle> getOptionsList() {
            return this.mOptionsList;
        }

        public List<SubscriptionCallback> getCallbacks() {
            return this.mCallbacks;
        }

        public void setCallbackForOptions(SubscriptionCallback subscriptionCallback, Bundle bundle) {
            for (int i = 0; i < this.mOptionsList.size(); i++) {
                if (MediaBrowserCompatUtils.areSameOptions((Bundle) this.mOptionsList.get(i), bundle)) {
                    this.mCallbacks.set(i, subscriptionCallback);
                    return;
                }
            }
            this.mCallbacks.add(subscriptionCallback);
            this.mOptionsList.add(bundle);
        }

        public boolean remove(Bundle bundle) {
            for (int i = 0; i < this.mOptionsList.size(); i++) {
                if (MediaBrowserCompatUtils.areSameOptions((Bundle) this.mOptionsList.get(i), bundle)) {
                    this.mCallbacks.remove(i);
                    this.mOptionsList.remove(i);
                    return true;
                }
            }
            return false;
        }

        public SubscriptionCallback getCallback(Bundle bundle) {
            for (int i = 0; i < this.mOptionsList.size(); i++) {
                if (MediaBrowserCompatUtils.areSameOptions((Bundle) this.mOptionsList.get(i), bundle)) {
                    return (SubscriptionCallback) this.mCallbacks.get(i);
                }
            }
            return null;
        }
    }

    public abstract class SubscriptionCallback {
        public void onChildrenLoaded(@NonNull String str, List<MediaItem> list) {
        }

        public void onChildrenLoaded(@NonNull String str, List<MediaItem> list, @NonNull Bundle bundle) {
        }

        public void onError(@NonNull String str) {
        }

        public void onError(@NonNull String str, @NonNull Bundle bundle) {
        }
    }

    class SubscriptionCallbackApi21 extends SubscriptionCallback {
        private Bundle mOptions;
        SubscriptionCallback mSubscriptionCallback;
        private final Object mSubscriptionCallbackObj;

        class StubApi21 implements SubscriptionCallback {
            private StubApi21() {
            }

            public void onChildrenLoaded(@NonNull String str, List<Parcel> list) {
                List list2 = null;
                if (list != null) {
                    List arrayList = new ArrayList();
                    for (Parcel parcel : list) {
                        parcel.setDataPosition(0);
                        arrayList.add(MediaItem.CREATOR.createFromParcel(parcel));
                        parcel.recycle();
                    }
                    list2 = arrayList;
                }
                if (SubscriptionCallbackApi21.this.mOptions != null) {
                    SubscriptionCallbackApi21.this.onChildrenLoaded(str, MediaBrowserCompatUtils.applyOptions(list2, SubscriptionCallbackApi21.this.mOptions), SubscriptionCallbackApi21.this.mOptions);
                } else {
                    SubscriptionCallbackApi21.this.onChildrenLoaded(str, list2);
                }
            }

            public void onError(@NonNull String str) {
                if (SubscriptionCallbackApi21.this.mOptions != null) {
                    SubscriptionCallbackApi21.this.onError(str, SubscriptionCallbackApi21.this.mOptions);
                } else {
                    SubscriptionCallbackApi21.this.onError(str);
                }
            }
        }

        public SubscriptionCallbackApi21(SubscriptionCallback subscriptionCallback, Bundle bundle) {
            this.mSubscriptionCallback = subscriptionCallback;
            this.mOptions = bundle;
            this.mSubscriptionCallbackObj = MediaBrowserCompatApi21.createSubscriptionCallback(new StubApi21());
        }

        public void onChildrenLoaded(@NonNull String str, List<MediaItem> list) {
            this.mSubscriptionCallback.onChildrenLoaded(str, list);
        }

        public void onChildrenLoaded(@NonNull String str, List<MediaItem> list, @NonNull Bundle bundle) {
            this.mSubscriptionCallback.onChildrenLoaded(str, list, bundle);
        }

        public void onError(@NonNull String str) {
            this.mSubscriptionCallback.onError(str);
        }

        public void onError(@NonNull String str, @NonNull Bundle bundle) {
            this.mSubscriptionCallback.onError(str, bundle);
        }
    }

    public MediaBrowserCompat(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
        if (VERSION.SDK_INT >= 23) {
            this.mImpl = new MediaBrowserImplApi23(context, componentName, connectionCallback, bundle);
        } else if (VERSION.SDK_INT >= 21) {
            this.mImpl = new MediaBrowserImplApi21(context, componentName, connectionCallback, bundle);
        } else {
            this.mImpl = new MediaBrowserImplBase(context, componentName, connectionCallback, bundle);
        }
    }

    public void connect() {
        this.mImpl.connect();
    }

    public void disconnect() {
        this.mImpl.disconnect();
    }

    public boolean isConnected() {
        return this.mImpl.isConnected();
    }

    @NonNull
    public ComponentName getServiceComponent() {
        return this.mImpl.getServiceComponent();
    }

    @NonNull
    public String getRoot() {
        return this.mImpl.getRoot();
    }

    @Nullable
    public Bundle getExtras() {
        return this.mImpl.getExtras();
    }

    @NonNull
    public Token getSessionToken() {
        return this.mImpl.getSessionToken();
    }

    public void subscribe(@NonNull String str, @NonNull SubscriptionCallback subscriptionCallback) {
        this.mImpl.subscribe(str, null, subscriptionCallback);
    }

    public void subscribe(@NonNull String str, @NonNull Bundle bundle, @NonNull SubscriptionCallback subscriptionCallback) {
        if (bundle == null) {
            throw new IllegalArgumentException("options are null");
        }
        this.mImpl.subscribe(str, bundle, subscriptionCallback);
    }

    public void unsubscribe(@NonNull String str) {
        this.mImpl.unsubscribe(str, null);
    }

    public void unsubscribe(@NonNull String str, @NonNull Bundle bundle) {
        if (bundle == null) {
            throw new IllegalArgumentException("options are null");
        }
        this.mImpl.unsubscribe(str, bundle);
    }

    public void getItem(@NonNull String str, @NonNull ItemCallback itemCallback) {
        this.mImpl.getItem(str, itemCallback);
    }
}
