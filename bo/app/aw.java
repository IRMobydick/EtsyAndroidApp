package bo.app;

import com.appboy.events.IEventSubscriber;

public final class aw implements IEventSubscriber<bj> {
    final /* synthetic */ ao f129a;

    public aw(ao aoVar) {
        this.f129a = aoVar;
    }

    public final /* synthetic */ void trigger(Object obj) {
        this.f129a.f112d.m614a(((bj) obj).f155a);
        this.f129a.f115g.sendBroadcast(this.f129a.f117i);
    }
}
