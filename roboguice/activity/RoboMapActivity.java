package roboguice.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import com.google.android.maps.MapActivity;
import com.google.inject.Inject;
import com.google.inject.Key;
import java.util.HashMap;
import java.util.Map;
import roboguice.RoboGuice;
import roboguice.activity.event.OnActivityResultEvent;
import roboguice.activity.event.OnConfigurationChangedEvent;
import roboguice.activity.event.OnContentChangedEvent;
import roboguice.activity.event.OnCreateEvent;
import roboguice.activity.event.OnDestroyEvent;
import roboguice.activity.event.OnNewIntentEvent;
import roboguice.activity.event.OnPauseEvent;
import roboguice.activity.event.OnRestartEvent;
import roboguice.activity.event.OnResumeEvent;
import roboguice.activity.event.OnStartEvent;
import roboguice.activity.event.OnStopEvent;
import roboguice.event.EventManager;
import roboguice.inject.ContentViewListener;
import roboguice.inject.RoboInjector;
import roboguice.util.RoboContext;

public abstract class RoboMapActivity extends MapActivity implements RoboContext {
    protected EventManager eventManager;
    @Inject
    ContentViewListener ignored;
    protected HashMap<Key<?>, Object> scopedObjects;

    public RoboMapActivity() {
        this.scopedObjects = new HashMap();
    }

    protected void onCreate(Bundle bundle) {
        RoboInjector injector = RoboGuice.getInjector(this);
        this.eventManager = (EventManager) injector.getInstance(EventManager.class);
        injector.injectMembersWithoutViews(this);
        super.onCreate(bundle);
        this.eventManager.fire(new OnCreateEvent(bundle));
    }

    protected void onRestart() {
        super.onRestart();
        this.eventManager.fire(new OnRestartEvent());
    }

    protected void onStart() {
        super.onStart();
        this.eventManager.fire(new OnStartEvent());
    }

    protected void onResume() {
        super.onResume();
        this.eventManager.fire(new OnResumeEvent());
    }

    protected void onPause() {
        super.onPause();
        this.eventManager.fire(new OnPauseEvent());
    }

    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.eventManager.fire(new OnNewIntentEvent());
    }

    protected void onStop() {
        try {
            this.eventManager.fire(new OnStopEvent());
        } finally {
            super.onStop();
        }
    }

    protected void onDestroy() {
        try {
            this.eventManager.fire(new OnDestroyEvent());
            try {
                RoboGuice.destroyInjector(this);
            } finally {
                super.onDestroy();
            }
        } catch (Throwable th) {
            RoboGuice.destroyInjector(this);
        } finally {
            super.onDestroy();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        Configuration configuration2 = getResources().getConfiguration();
        super.onConfigurationChanged(configuration);
        this.eventManager.fire(new OnConfigurationChangedEvent(configuration2, configuration));
    }

    public void onContentChanged() {
        super.onContentChanged();
        RoboGuice.getInjector(this).injectViewMembers(this);
        this.eventManager.fire(new OnContentChangedEvent());
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.eventManager.fire(new OnActivityResultEvent(i, i2, intent));
    }

    public Map<Key<?>, Object> getScopedObjectMap() {
        return this.scopedObjects;
    }
}
