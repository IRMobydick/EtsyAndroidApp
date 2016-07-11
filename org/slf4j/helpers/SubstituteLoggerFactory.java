package org.slf4j.helpers;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

/* renamed from: org.slf4j.helpers.b */
public class SubstituteLoggerFactory implements ILoggerFactory {
    final List f5593a;

    public SubstituteLoggerFactory() {
        this.f5593a = new ArrayList();
    }

    public Logger m7509a(String str) {
        synchronized (this.f5593a) {
            this.f5593a.add(str);
        }
        return NOPLogger.NOP_LOGGER;
    }

    public List m7508a() {
        List arrayList = new ArrayList();
        synchronized (this.f5593a) {
            arrayList.addAll(this.f5593a);
        }
        return arrayList;
    }
}
