package bo.app;

import com.appboy.events.IEventSubscriber;
import com.appboy.support.AppboyLogger;

public final class at implements IEventSubscriber<dn> {
    final /* synthetic */ ao f126a;

    public at(ao aoVar) {
        this.f126a = aoVar;
    }

    public final /* synthetic */ void trigger(Object obj) {
        AppboyLogger.m666i(ao.f109a, "Place IQ dispatch failed for: " + ((dn) obj).f335a.m206b());
    }
}
