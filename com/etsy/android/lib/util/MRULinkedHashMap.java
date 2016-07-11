package com.etsy.android.lib.util;

import com.etsy.android.uikit.view.ListingFullImageView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

public class MRULinkedHashMap<K, V> extends LinkedHashMap<K, V> {
    protected final int mMaxItems;

    public MRULinkedHashMap(int i) {
        super((i * 4) / 3, ListingFullImageView.ASPECT_RATIO_STANDARD, true);
        this.mMaxItems = i;
    }

    public Collection<V> values() {
        if (isEmpty()) {
            return new ArrayList(0);
        }
        int size = size();
        Object[] toArray = super.values().toArray();
        ArrayList arrayList = new ArrayList(size);
        for (size--; size >= 0; size--) {
            arrayList.add(toArray[size]);
        }
        return arrayList;
    }

    public Set<Entry<K, V>> entrySet() {
        if (isEmpty()) {
            return new HashSet(0);
        }
        int size = size();
        Entry[] entryArr = (Entry[]) entrySet().toArray();
        HashSet hashSet = new HashSet(size);
        for (size--; size >= 0; size--) {
            hashSet.add(entryArr[size]);
        }
        return hashSet;
    }

    public Set<K> keySet() {
        if (isEmpty()) {
            return new HashSet(0);
        }
        int size = size();
        Object[] toArray = keySet().toArray();
        HashSet hashSet = new HashSet(size);
        for (size--; size >= 0; size--) {
            hashSet.add(toArray[size]);
        }
        return hashSet;
    }

    protected boolean removeEldestEntry(Entry<K, V> entry) {
        return size() > this.mMaxItems;
    }
}
