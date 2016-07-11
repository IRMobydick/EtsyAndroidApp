package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.C1129c;
import java.util.PriorityQueue;
import org.apache.commons.lang3.StringUtils;

@jw
public class dp {
    static long m6409a(int i, int i2, long j, long j2, long j3) {
        return ((((((j + 1073807359) - ((((((long) i) + 2147483647L) % 1073807359) * j2) % 1073807359)) % 1073807359) * j3) % 1073807359) + ((((long) i2) + 2147483647L) % 1073807359)) % 1073807359;
    }

    static long m6410a(long j, int i) {
        return i == 0 ? 1 : i != 1 ? i % 2 == 0 ? m6410a((j * j) % 1073807359, i / 2) % 1073807359 : ((m6410a((j * j) % 1073807359, i / 2) % 1073807359) * j) % 1073807359 : j;
    }

    static String m6411a(String[] strArr, int i, int i2) {
        if (strArr.length < i + i2) {
            C1129c.m6188b("Unable to construct shingle");
            return StringUtils.EMPTY;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i3 = i; i3 < (i + i2) - 1; i3++) {
            stringBuffer.append(strArr[i3]);
            stringBuffer.append(' ');
        }
        stringBuffer.append(strArr[(i + i2) - 1]);
        return stringBuffer.toString();
    }

    static void m6412a(int i, long j, String str, int i2, PriorityQueue<dq> priorityQueue) {
        dq dqVar = new dq(j, str, i2);
        if ((priorityQueue.size() != i || (((dq) priorityQueue.peek()).c <= dqVar.c && ((dq) priorityQueue.peek()).a <= dqVar.a)) && !priorityQueue.contains(dqVar)) {
            priorityQueue.add(dqVar);
            if (priorityQueue.size() > i) {
                priorityQueue.poll();
            }
        }
    }

    public static void m6413a(String[] strArr, int i, int i2, PriorityQueue<dq> priorityQueue) {
        if (strArr.length < i2) {
            int i3 = i;
            m6412a(i3, m6414b(strArr, 0, strArr.length), m6411a(strArr, 0, strArr.length), strArr.length, (PriorityQueue) priorityQueue);
            return;
        }
        long b = m6414b(strArr, 0, i2);
        m6412a(i, b, m6411a(strArr, 0, i2), i2, (PriorityQueue) priorityQueue);
        long a = m6410a(16785407, i2 - 1);
        int i4 = 1;
        while (i4 < (strArr.length - i2) + 1) {
            long a2 = m6409a(dn.m6399a(strArr[i4 - 1]), dn.m6399a(strArr[(i4 + i2) - 1]), b, a, 16785407);
            m6412a(i, a2, m6411a(strArr, i4, i2), strArr.length, (PriorityQueue) priorityQueue);
            i4++;
            b = a2;
        }
    }

    private static long m6414b(String[] strArr, int i, int i2) {
        long a = (((long) dn.m6399a(strArr[i])) + 2147483647L) % 1073807359;
        for (int i3 = i + 1; i3 < i + i2; i3++) {
            a = (((a * 16785407) % 1073807359) + ((((long) dn.m6399a(strArr[i3])) + 2147483647L) % 1073807359)) % 1073807359;
        }
        return a;
    }
}
