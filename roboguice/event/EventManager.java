package roboguice.event;

import android.content.Context;
import com.google.inject.Inject;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import roboguice.event.eventListener.ObserverMethodListener;
import roboguice.inject.ContextSingleton;

@ContextSingleton
public class EventManager {
    @Inject
    protected Context context;
    protected Map<Class<?>, Set<EventListener<?>>> registrations;

    public EventManager() {
        this.registrations = new HashMap();
    }

    public <T> void registerObserver(Class<T> cls, EventListener eventListener) {
        Set set = (Set) this.registrations.get(cls);
        if (set == null) {
            set = Collections.synchronizedSet(new LinkedHashSet());
            this.registrations.put(cls, set);
        }
        set.add(eventListener);
    }

    public <T> void registerObserver(Object obj, Method method, Class<T> cls) {
        registerObserver(cls, new ObserverMethodListener(obj, method));
    }

    public <T> void unregisterObserver(Class<T> cls, EventListener<T> eventListener) {
        Set set = (Set) this.registrations.get(cls);
        if (set != null) {
            synchronized (set) {
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    if (((EventListener) it.next()) == eventListener) {
                        it.remove();
                        break;
                    }
                }
            }
        }
    }

    public <T> void unregisterObserver(Object obj, Class<T> cls) {
        Set set = (Set) this.registrations.get(cls);
        if (set != null) {
            synchronized (set) {
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    EventListener eventListener = (EventListener) it.next();
                    if ((eventListener instanceof ObserverMethodListener) && ((ObserverMethodListener) eventListener).getInstance() == obj) {
                        it.remove();
                        break;
                    }
                }
            }
        }
    }

    public void fire(Object obj) {
        Set<EventListener> set = (Set) this.registrations.get(obj.getClass());
        if (set != null) {
            synchronized (set) {
                for (EventListener onEvent : set) {
                    onEvent.onEvent(obj);
                }
            }
        }
    }

    public void destroy() {
        for (Entry value : this.registrations.entrySet()) {
            ((Set) value.getValue()).clear();
        }
        this.registrations.clear();
    }
}
