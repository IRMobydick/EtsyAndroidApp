package com.google.inject.util;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.spi.Dependency;
import com.google.inject.spi.ProviderWithDependencies;
import java.util.Set;
import p000a.p001a.Provider;

final class Providers$3 implements ProviderWithDependencies<T> {
    final /* synthetic */ Provider val$delegate;
    final /* synthetic */ Set val$dependencies;

    Providers$3(Provider provider, Set set) {
        this.val$delegate = provider;
        this.val$dependencies = set;
    }

    @Inject
    void initialize(Injector injector) {
        injector.injectMembers(this.val$delegate);
    }

    public Set<Dependency<?>> getDependencies() {
        return this.val$dependencies;
    }

    public T get() {
        return this.val$delegate.get();
    }

    public String toString() {
        return "guicified(" + this.val$delegate + ")";
    }
}
