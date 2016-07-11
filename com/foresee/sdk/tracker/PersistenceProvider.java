package com.foresee.sdk.tracker;

public interface PersistenceProvider {
    void persistState(PersistedState persistedState);
}
