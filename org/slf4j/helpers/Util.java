package org.slf4j.helpers;

/* renamed from: org.slf4j.helpers.c */
public class Util {
    public static final void m7511a(String str, Throwable th) {
        System.err.println(str);
        System.err.println("Reported exception:");
        th.printStackTrace();
    }

    public static final void m7510a(String str) {
        System.err.println(new StringBuffer().append("SLF4J: ").append(str).toString());
    }
}
